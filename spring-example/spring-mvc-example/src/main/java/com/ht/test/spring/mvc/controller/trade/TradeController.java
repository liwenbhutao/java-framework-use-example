package com.ht.test.spring.mvc.controller.trade;

import com.caimao.uts.common.util.constant.TimeConstant;
import com.caimao.uts.gateway.api.message.Deal;
import com.caimao.uts.gateway.api.message.OrderRequest;
import com.caimao.uts.gateway.api.message.OrderResponse;
import com.caimao.uts.gateway.api.message.RequestHeader;
import com.caimao.uts.gateway.api.service.UTSService;
import com.ht.test.spring.mvc.controller.trade.support.TradeResult;
import com.ht.test.spring.mvc.controller.trade.support.UtsTradeDeferredResult;
import com.ht.test.spring.mvc.controller.trade.support.UtsTradeDeferredResultContainer;
import com.ht.test.spring.mvc.controller.trade.support.config.TradeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * Created on 16/6/21.
 *
 * @author hutao
 * @version 1.0
 */
@RestController
@RequestMapping("/trade")
public class TradeController {
    @Autowired
    private UtsTradeDeferredResultContainer utsTradeDeferredResultContainer;
    @Autowired
    @Qualifier("utsGatewayServiceClient")
    private UTSService.Iface aShareExchangeServiceClient;

    @RequestMapping("/entrust")
    public
    @ResponseBody
    DeferredResult<TradeResult> entrust() throws Exception {
        final RequestHeader header = new RequestHeader();
        header.setMarket("SJS");
        final OrderRequest request = new OrderRequest();
        request.setHeader(header);
        request.setDeal(Deal.BUY);
        request.setExchangeType("1");
        request.setAmount(10);
        request.setPrice(1.2);
        request.setReservePrice(1.4);
        request.setUserId(1);
        request.setEntrustProp("prop");
        request.setCondition("condition");
        request.setBatchNo(10);
        request.setProduct("product");
        final OrderResponse orderResponse = this.aShareExchangeServiceClient.order(request);
        final String tradeOrderNo = orderResponse.entrustNo;
        final UtsTradeDeferredResult result = new UtsTradeDeferredResult(
                TradeConstants.ENTRUST_RESULT_MSG_KEY_PREFIX + tradeOrderNo, TimeConstant.MINUTE);
        this.utsTradeDeferredResultContainer.add(result);
        return result;
    }

    @RequestMapping("/cancelEntrust")
    public
    @ResponseBody
    DeferredResult<TradeResult> cancelEntrust() {
        final String tradeOrderNo = "";
        final UtsTradeDeferredResult result = new UtsTradeDeferredResult(tradeOrderNo, 1000L);
        return result;
    }

}
