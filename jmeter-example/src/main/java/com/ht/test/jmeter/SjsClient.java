package com.ht.test.jmeter;

import com.caimao.uts.gateway.api.enums.EnumUtsExchangeType;
import com.caimao.uts.gateway.api.helper.ResponseHeaderSupporter;
import com.caimao.uts.gateway.api.message.Deal;
import com.caimao.uts.gateway.api.message.OrderRequest;
import com.caimao.uts.gateway.api.message.OrderResponse;
import com.google.common.util.concurrent.SettableFuture;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by hutao on 16/5/9.
 * 下午1:49
 */
public class SjsClient extends BaseClient {
    @Override
    public void setupTest(final JavaSamplerContext context) {
        super.setupTest(context);
    }

    @Override
    public void teardownTest(final JavaSamplerContext context) {
        super.teardownTest(context);
    }

    @Override
    public SampleResult runTest(final JavaSamplerContext javaSamplerContext) {
        final SampleResult result = new SampleResult();
        result.sampleStart();
        try {
            final OrderResponse response = getClient().order(buildOrderRequest());
            result.setResponseData("first:{code:[" + response.getHeader().code
                    + "],msg[" + response.getHeader().info + "]}。", "utf-8");
            if (ResponseHeaderSupporter.isSuccess(response.getHeader())) {
                final SettableFuture<OrderResponse> future = SettableFuture.create();
                futureMap.put("entrust_result_" + response.getEntrustNo(), future);
                final OrderResponse orderResponse = future.get(1, TimeUnit.MINUTES);
                result.setResponseData(result.getResponseDataAsString()
                        + "second:{code:[" + orderResponse.header.code + "],msg:[" + orderResponse.header.info + "]}", "utf-8");
                result.setResponseCode("0");
                result.setResponseMessageOK();
                result.setSuccessful(true);
            } else {
                result.setSuccessful(false);
                result.setErrorCount(1);
            }
        } catch (final Exception e) {
            result.setResponseCode("-1");
            result.setResponseMessage(e.getMessage());
            result.setSuccessful(false);
            result.setErrorCount(1);
        }
        result.sampleEnd();
        return result;
    }

    private static OrderRequest buildOrderRequest() {
        final OrderRequest result = new OrderRequest();
        result.setAmount(100);
        result.setBatchNo(0);
        result.setCondition("");
        result.setEntrustProp("");
        result.setExchangeType(EnumUtsExchangeType.SJS_4045.getCode());
        result.setDeal(Deal.BUY);
        result.setPrice(10);
        result.setReservePrice(0);
        result.setProduct("mAu(T+D)");
        result.setHeader(buildRequestHeader("SJS").setFlag(UUID.randomUUID().toString() + RandomStringUtils.randomNumeric(10)));
        result.setUserId(820936693514241L);
        return result;
    }
}
