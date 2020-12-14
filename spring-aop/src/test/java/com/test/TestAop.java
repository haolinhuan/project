package com.test;

import com.service.AccountService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestAop {

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        AccountService as = ac.getBean("accountService",AccountService.class);
        as.saveAccount();
        as.deleteAccount();
        as.updateAccount(1);
    }

}
