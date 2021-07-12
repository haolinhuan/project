package com.spring.product.springcloudproduct.service.impl;

import com.spring.product.springcloudproduct.service.PaymentService;


public class PaymentFallBackService implements PaymentService {
    @Override
    public Object queryPaymentList(String id) {
        return "当前登录人数过多，请稍后重试";
    }
}
