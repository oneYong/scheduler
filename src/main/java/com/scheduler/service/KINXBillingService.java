package com.scheduler.service;

import com.scheduler.mapper.BillingMapper;
import com.scheduler.utils.CmmUtils;
import com.scheduler.utils.ECompany;
import com.scheduler.vo.BillingDataMEGAKINX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by WYKIM on 2017-08-17.
 */
@Service("KINXBillingService")
public class KINXBillingService implements BillingService {

    @Autowired
    private AWSBillingDataFactory awsBillingDataFactory;

    @Autowired
    private BillingMapper billingMapper;

    @Override
    public void run() throws Exception {
        String yesterday = CmmUtils.getGMTYesterday();
        int cntBillingInfo = billingMapper.isBillingInfo_KINX(yesterday);

        if(cntBillingInfo == 0){
            // sbc_kinx_aws_account_billinginfo
            List<BillingDataMEGAKINX> list = awsBillingDataFactory.makeMEGAKINX_BillingData(yesterday, ECompany.KINX);
            for(int i = 0; i < list.size(); i++){
                BillingDataMEGAKINX billingDataMEGAKINX = list.get(i);
                billingDataMEGAKINX.setTotalDate(yesterday);
                billingMapper.insertBillingInfo_KINX(billingDataMEGAKINX);
            }// end for
        }
    }
}
