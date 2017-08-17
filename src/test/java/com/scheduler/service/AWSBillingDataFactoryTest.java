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
}
