package com.ht.test.jmeter;

import com.bfd.harpc.config.RegistryConfig;
import com.caimao.uts.common.harpc.HaRpcClient;
import com.caimao.uts.common.mq.rocketmq.config.RocketMqConfigSupporter;
import com.caimao.uts.common.mq.rocketmq.config.RocketMqPushConsumerConfig;
import com.caimao.uts.common.mq.rocketmq.hanlder.LogMessageExtHandler;
import com.caimao.uts.common.mq.rocketmq.hanlder.MessageExtHandler;
import com.caimao.uts.common.mq.rocketmq.service.PushConsumerService;
import com.caimao.uts.gateway.api.enums.EnumUtsPasswordType;
import com.caimao.uts.gateway.api.helper.ResponseHeaderSupporter;
import com.caimao.uts.gateway.api.message.LoginRequest;
import com.caimao.uts.gateway.api.message.LoginResponse;
import com.caimao.uts.gateway.api.message.OrderResponse;
import com.caimao.uts.gateway.api.message.RequestHeader;
import com.caimao.uts.gateway.api.service.UTSService;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ServiceManager;
import com.google.common.util.concurrent.SettableFuture;
import lombok.SneakyThrows;
import org.apache.commons.collections.map.HashedMap;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created on 16/7/27.
 *
 * @author hutao
 * @version 1.0
 */
public abstract class BaseClient extends AbstractJavaSamplerClient {
    private final static HaRpcClient<UTSService.Iface> haRpcClient;
    private final static UTSService.Iface client;
    private final static RegistryConfig registryConfig;
    private final static Properties clientProperties;
    private final static Properties mqClientProperties;
    private final static String ZK_CONNECT_STR = "10.100.10.148:2181,10.100.10.149:2181,10.100.10.150:2181";
    public static final String MQ_NAMESRV_ADDR = "10.100.10.198:9876;10.100.10.199:9876";
    private final static PushConsumerService pushConsumerService;
    private final static ServiceManager serviceManager;
    protected final static ConcurrentHashMap<String, SettableFuture<OrderResponse>> futureMap = new ConcurrentHashMap<>();


    static {
        registryConfig = new RegistryConfig();
//        registryConfig.setConnectstr("10.100.10.148:2181,10.100.10.149:2181,10.100.10.150:2181");
        registryConfig.setConnectstr(ZK_CONNECT_STR);
        registryConfig.setAuth("admin:admin123");
        registryConfig.setTimeout(10000);

        clientProperties = new Properties();
        initHaRpcClientProperties();
        mqClientProperties = new Properties();
        mqClientProperties.setProperty("rockemq.namesrvAddr", MQ_NAMESRV_ADDR);
        mqClientProperties.setProperty("rockemq.persistConsumerOffsetInterval", "1000");
        mqClientProperties.setProperty("rockemq.push.consumer.consumerGroup", "utsTradeResultConsumerGroup");
        mqClientProperties.setProperty("rockemq.push.consumer.messageModel", "BROADCASTING");

        haRpcClient = new HaRpcClient<>(clientProperties, registryConfig);
        try {
            client = haRpcClient.createProxy();
            login("SJS");
            login("NJS");
        } catch (final Exception e) {
            throw Throwables.propagate(e);
        }

        pushConsumerService = new PushConsumerService();
        pushConsumerService.setTopic("uts_trade_system_trade_result");
        pushConsumerService.setConsumerConfig(RocketMqConfigSupporter.load(RocketMqPushConsumerConfig.class, mqClientProperties));
        final Map<String, MessageExtHandler> subExpressionMessageExtHandlerMap = new HashedMap();
        subExpressionMessageExtHandlerMap.put("*", new LogMessageExtHandler());
        subExpressionMessageExtHandlerMap.put("entrust_order", new ExchangeEntrustRequestHandler(futureMap));
        pushConsumerService.setSubExpressionMessageExtHandlerMap(subExpressionMessageExtHandlerMap);
        serviceManager = new ServiceManager(Lists.newArrayList(pushConsumerService));
        serviceManager.startAsync();
    }

    private static void initHaRpcClientProperties() {
        clientProperties.setProperty("client.name", "uts-gateway-service-client");
        clientProperties.setProperty("client.owner", "hutao");
        clientProperties.setProperty("client.service", "/com.caimao.uts.gateway.api.service$UtsGatewayThriftService");
        clientProperties.setProperty("client.iface", "com.caimao.uts.gateway.api.service.UTSService$Iface");
        clientProperties.setProperty("client.timeout", "60000");
        clientProperties.setProperty("client.retry", "0");
        clientProperties.setProperty("client.monitor", "true");
        clientProperties.setProperty("client.interval", "60");
    }

    protected final UTSService.Iface getClient() {
        return client;
    }

    @SneakyThrows
    private static void login(final String exchangeCode) {
        final LoginResponse response = client.login(buildLoginRequest(exchangeCode));
        Preconditions.checkState(ResponseHeaderSupporter.isSuccess(response.header),
                "登录" + exchangeCode + "失败:" + response.header.code + "," + response.header.info);
    }

    private static LoginRequest buildLoginRequest(final String exchangeCode) {
        final LoginRequest result = new LoginRequest();
        result.setHeader(buildRequestHeader(exchangeCode));
        result.setUserId(820936693514241L);
        result.setPassword("000000");
        result.setPasswordType(EnumUtsPasswordType.TRADE.getId());
        return result;
    }

    protected static RequestHeader buildRequestHeader(final String exchangeCode) {
        final RequestHeader requestHeader = new RequestHeader();
        requestHeader.setMarket(exchangeCode);
        return requestHeader;
    }
}
