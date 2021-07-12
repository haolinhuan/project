package com.spring.product.springcloudproduct.dao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "spring-cloud-payment",fallback = PaymentFallBackService.class)
@Component
public interface PaymentService {

    @GetMapping("/payment/info")
    public Object queryPaymentList(@RequestParam("id")  String id);
}
