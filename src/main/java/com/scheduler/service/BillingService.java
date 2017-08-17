package com.scheduler.service;

import org.springframework.stereotype.Component;

/**
 * Created by WYKIM on 2017-08-17.
 */
@Component("BillingService")
public interface BillingService {
    public void run() throws Exception;
}
