package com.shouyingbao.pbs.common.pay.weixin.service;

import com.shouyingbao.pbs.common.pay.weixin.util.Configure;
import com.shouyingbao.pbs.common.pay.weixin.model.RefundQueryReqData;

/**
 * User: rizenguo
 * Date: 2014/10/29
 * Time: 16:04
 */
public class RefundQueryService extends BaseService{

    public RefundQueryService() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        super(Configure.REFUND_QUERY_API);
    }

    /**
     * 请求退款查询服务
     * @param refundQueryReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的XML数据
     * @throws Exception
     */
    public String request(RefundQueryReqData refundQueryReqData,Configure configure) throws Exception {

        //--------------------------------------------------------------------
        //发送HTTPS的Post请求到API地址
        //--------------------------------------------------------------------
        String responseString = sendPost(refundQueryReqData,configure);

        return responseString;
    }




}
