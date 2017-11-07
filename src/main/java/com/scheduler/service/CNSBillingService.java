package com.scheduler.service;

import com.scheduler.mapper.BillingMapper;
import com.scheduler.utils.CmmUtils;
import com.scheduler.vo.BillingDataCNS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by WYKIM on 2017-08-17.
 */
@Service("CNSBillingService")
public class CNSBillingService implements BillingService {

    @Autowired
    private AWSBillingDataFactory awsBillingDataFactory;

    @Autowired
    private BillingMapper billingMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor={Exception.class})
    public void run() throws Exception {
        String yesterday = CmmUtils.getGMTYesterday();
        String beforeYesterday = CmmUtils.getGMTBeforeYesterday();

        // 1. data 가져옴
        //List<BillingDataCNS> billingDataCNSList = awsBillingDataFactory.makeCNS_BillingData(yesterday);
        List<BillingDataCNS> billingDataCNSList = awsBillingDataFactory.makeCNS_BillingData_NEW(yesterday);

        if(billingDataCNSList.size() == 0)
            return;

        int cntBillingInfo = billingMapper.isBillingInfo_CNS(yesterday);
        int cntBillingInfoTotal = billingMapper.isBillingInfoTotal_CNS(yesterday);

        // data가 존재 하지 않을 때 실행
        if(cntBillingInfo == 0 && cntBillingInfoTotal == 0){
            // sbc_aws_account_billinginfo

            for(int i = 0; i < billingDataCNSList.size(); i++){
                BillingDataCNS billingDataCNS = billingDataCNSList.get(i);
                billingDataCNS.setTotalDate(yesterday);
                billingMapper.insertBillingInfo_CNS(billingDataCNS);
            }// end for

            // sbc_aws_account_billinginfo_total
            List<BillingDataCNS> yesterdayProductList = billingMapper.getListBillingInfoByProductGroup(yesterday);
            List<BillingDataCNS> beforeYesterdayProductList = billingMapper.getListBillingInfoByProductGroup(beforeYesterday);
            String day = yesterday.split("-")[2];
            for(int i = 0; i < yesterdayProductList.size(); i++){
                BillingDataCNS billingDataCNS = yesterdayProductList.get(i);
                double beforeCostExtra = 0;

                if(beforeYesterdayProductList.size() != 0){
                    for(int j = 0; j < beforeYesterdayProductList.size(); j++){
                        BillingDataCNS tempCNS = beforeYesterdayProductList.get(j);
                        if(billingDataCNS.getProductCode().equals(tempCNS.getProductCode())){
                            double yesterdayCost = billingDataCNS.getTotalCost();
                            double beforeYesterdayCost = tempCNS.getTotalCost();

                            if(day.equals("01")){
                                beforeCostExtra = 0;
                                break;
                            }else{
                                beforeCostExtra = yesterdayCost - beforeYesterdayCost;
                                break;
                            }
                        }
                    }
                }

                billingDataCNS.setBeforeCostExtra(beforeCostExtra);
                billingMapper.insertBillingInfoTotal_CNS(billingDataCNS);
            }// end for
        }

    }

}
