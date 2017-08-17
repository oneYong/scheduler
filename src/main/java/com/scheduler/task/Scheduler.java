package com.scheduler.task;

import com.scheduler.mapper.CheckMapper;
import com.scheduler.service.CNSBillingService;
import com.scheduler.service.KINXBillingService;
import com.scheduler.service.MEGABillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by WYKIM on 2017-08-17.
 */
@Component
public class Scheduler {
    @Autowired
    private CheckMapper checkMapper;

    @Autowired
    private CNSBillingService cnsBillingService;

    @Autowired
    private MEGABillingService megaBillingService;

    @Autowired
    private KINXBillingService kinxBillingService;

    @Scheduled(cron = "0 */5 * * * ?")
    public void checkDB() throws Exception{
        System.out.println(checkMapper.select());
    }

    // 초 분 시
    @Scheduled(cron = "0 0 9 * * ?")
    public void getBillingData() throws Exception{
        try{
            //cnsBillingService.run();
        }catch(Exception e){
            e.printStackTrace();
        }

        try{
            megaBillingService.run();
            System.out.println("MEGABillService Complete!");
        }catch(Exception e){
            e.printStackTrace();
        }

        try{
            kinxBillingService.run();
            System.out.println("KINXBillService Complete!");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
