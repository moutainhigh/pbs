package com.shouyingbao.pbs.web.controller;

import com.shouyingbao.pbs.Exception.ParamNullException;
import com.shouyingbao.pbs.core.bean.ResponseData;
import com.shouyingbao.pbs.entity.PaymentBill;
import com.shouyingbao.pbs.service.PaymentBillService;
import com.shouyingbao.pbs.vo.WeixinScanPayParam;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * kejun
 * 2016/3/8 17:34
 **/

@Controller
@RequestMapping("pay/weixin")
public class WeixinPayController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeixinPayController.class);
    @Autowired
    PaymentBillService paymentBillService;

    /**
     * 扫码支付
     * @param weixinScanPayParam 请求参数
     * @return ResponseData 结果
     */
    @RequestMapping("/scanPay")
    @ResponseBody
    public ResponseData scanPay(@RequestBody WeixinScanPayParam weixinScanPayParam){
        ResponseData responseData;
        try {
            if(weixinScanPayParam.getUserId() == null || StringUtils.isBlank(weixinScanPayParam.getAuthCode()) || weixinScanPayParam.getTotalFee() == null){
                LOGGER.error("参数为空或不合法");
                throw new ParamNullException();
            }
            //TODO userID query shopId
            responseData = paymentBillService.scanPay(1,weixinScanPayParam.getAuthCode(),weixinScanPayParam.getTotalFee(),weixinScanPayParam.getDeviceInfo(),0);
        }catch (ParamNullException e){
            responseData = ResponseData.failure(e.getCode(), e.getMessage());
        }
        return  responseData;
    }

    /**
     * 扫码退款
     * @param weixinScanPayParam 请求参数
     * @return
     */
    @RequestMapping("/scanRefund")
    @ResponseBody
    public ResponseData scanRefund(@RequestBody WeixinScanPayParam weixinScanPayParam){
        PaymentBill paymentBill = paymentBillService.selectByOrderAndUserId(weixinScanPayParam.getOrderNo(),weixinScanPayParam.getUserId());

        return ResponseData.success();
    }
}