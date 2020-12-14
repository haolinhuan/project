package com.service.impl;

import com.service.AccountService;

public class AccountServiceImpl implements AccountService {
    public void saveAccount() {
        System.out.println("执行了保存");
    }

    public void updateAccount(int i) {
        System.out.println("执行了更新");
    }

    public void deleteAccount() {
        System.out.println("执行了删除");
    }
}
