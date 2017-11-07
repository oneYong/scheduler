package com.scheduler.service;


import com.scheduler.utils.CmmUtils;
import com.scheduler.utils.ECompany;
import com.scheduler.vo.BillingDataCNS;
import com.scheduler.vo.BillingDataMEGAKINX;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by WYKIM on 2017-08-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AWSBillingDataFactoryTest {

    @Autowired
    AWSBillingDataFactory awsBillingDataFactory;

    @Test
    public void test_makeCNS_BillingData() throws Exception{
        List<BillingDataCNS> billingDataCNSList = awsBillingDataFactory.makeCNS_BillingData(CmmUtils.getGMTYesterday());

        System.out.println(billingDataCNSList);

    }

    @Test
    public void test_makeMEGAKINX_BillingData() throws Exception{
        List<BillingDataMEGAKINX> billingDataMEGAKINXList = awsBillingDataFactory.makeMEGAKINX_BillingData(CmmUtils.getGMTYesterday(), ECompany.MEGA);

        System.out.println(billingDataMEGAKINXList);
        List<BillingDataMEGAKINX> billingDataMEGAKINXList2 = awsBillingDataFactory.makeMEGAKINX_BillingData(CmmUtils.getGMTYesterday(), ECompany.KINX);

        System.out.println(billingDataMEGAKINXList2);

    }

    // CNS 빌링
    @Test
    public void test_makeCNS_BillingData_NEW() throws Exception{
        List<BillingDataCNS> billingDataCNSList = awsBillingDataFactory.makeCNS_BillingData_NEW(CmmUtils.getGMTYesterday());

        for(int i = 0; i < billingDataCNSList.size(); i++){
            BillingDataCNS billingDataCNS = billingDataCNSList.get(i);
            System.out.print("no:"+(i+1) + " ");
            System.out.print("accountId : "+billingDataCNS.getAccountId()+", ");
            System.out.print("accountName : "+billingDataCNS.getAccountName()+", ");
            System.out.print("tag : "+billingDataCNS.getTag()+", ");
            System.out.print("productCode : "+billingDataCNS.getProductCode()+", ");
            System.out.print("productName : "+billingDataCNS.getProductName()+", ");
            System.out.println("totalCost : "+billingDataCNS.getTotalCost()+" ");

        }

    }

    // 메가존 빌링
    @Test
    public void makeMEGA_BillingData() throws Exception{
        List<BillingDataMEGAKINX> billingDataMEGAKINXList = awsBillingDataFactory.makeMEGAKINX_BillingData(CmmUtils.getGMTYesterday(),ECompany.MEGA);

        for(int i = 0; i < billingDataMEGAKINXList.size(); i++){
            BillingDataMEGAKINX billingDataMEGAKINX = billingDataMEGAKINXList.get(i);
            System.out.print("no:"+(i+1) + " ");
            System.out.print("accountId : "+billingDataMEGAKINX.getAccountId()+", ");
            System.out.print("accountName : "+billingDataMEGAKINX.getAccountName()+", ");
            System.out.print("tag : "+billingDataMEGAKINX.getTag()+", ");
            System.out.print("productCode : "+billingDataMEGAKINX.getProductCode()+", ");
            System.out.print("productName : "+billingDataMEGAKINX.getProductName()+", ");
            System.out.println("totalCost : "+billingDataMEGAKINX.getTotalCost()+" ");

        }

    }

    // KINX(LIME) 빌링
    @Test
    public void makeKINX_BillingData() throws Exception{
        List<BillingDataMEGAKINX> billingDataMEGAKINXList = awsBillingDataFactory.makeMEGAKINX_BillingData(CmmUtils.getGMTYesterday(),ECompany.KINX);

        for(int i = 0; i < billingDataMEGAKINXList.size(); i++){
            BillingDataMEGAKINX billingDataMEGAKINX = billingDataMEGAKINXList.get(i);
            System.out.print("no:"+(i+1) + " ");
            System.out.print("accountId : "+billingDataMEGAKINX.getAccountId()+", ");
            System.out.print("accountName : "+billingDataMEGAKINX.getAccountName()+", ");
            System.out.print("tag : "+billingDataMEGAKINX.getTag()+", ");
            System.out.print("productCode : "+billingDataMEGAKINX.getProductCode()+", ");
            System.out.print("productName : "+billingDataMEGAKINX.getProductName()+", ");
            System.out.println("totalCost : "+billingDataMEGAKINX.getTotalCost()+" ");

        }

    }

    @Test
    public void test_NEWCNS빌링() throws Exception{
        String result = awsBillingDataFactory.getCNS_BillingData_NEW(CmmUtils.getGMTYesterday());
        System.out.println(result);
    }

    @Test
    public void test_getCNS_BillingData_ADD1() throws Exception{
        String result = awsBillingDataFactory.getCNS_BillingData_ADD1(CmmUtils.getGMTYesterday());
        System.out.println(result);
    }

    @Test
    public void test_getCNS_BillingData_ADD2() throws Exception{
        String result = awsBillingDataFactory.getCNS_BillingData_ADD2(CmmUtils.getGMTYesterday());
        System.out.println(result);
    }

}
