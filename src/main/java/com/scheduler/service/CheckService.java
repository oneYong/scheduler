package com.scheduler.service;

import com.scheduler.mapper.BillingMapper;
import com.scheduler.mapper.UserMapper;
import com.scheduler.utils.ERegion;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WYKIM on 2017-08-18.
 */
@Service
public class CheckService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BillingMapper billingMapper;


    public Map<String,Object> isUserCheck(String date, ERegion eRegion)throws Exception{
        Map<String,Object> object = new HashMap<>();

        String user = "X";
        String actUser = "X";
        String totalUser = "X";

        switch(eRegion){
            case KIC :
                user = userMapper.isUser_KIC(date) > 0 ? "O":"X";
                actUser = userMapper.isActUser_KIC(date)  > 0 ? "O":"X";
                totalUser = userMapper.isTotalUser_KIC(date)  > 0 ? "O":"X";
                break;
            case AIC :
                user = userMapper.isUser_AIC(date) > 0 ? "O":"X";
                actUser = userMapper.isActUser_AIC(date)  > 0 ? "O":"X";
                totalUser = userMapper.isTotalUser_AIC(date)  > 0 ? "O":"X";
                break;
            case EIC :
                user = userMapper.isUser_EIC(date) > 0 ? "O":"X";
                actUser = userMapper.isActUser_EIC(date)  > 0 ? "O":"X";
                totalUser = userMapper.isTotalUser_EIC(date)  > 0 ? "O":"X";
                break;
            case RUC :
                user = userMapper.isUser_RUC(date) > 0 ? "O":"X";
                actUser = userMapper.isActUser_RUC(date)  > 0 ? "O":"X";
                totalUser = userMapper.isTotalUser_RUC(date)  > 0 ? "O":"X";
                break;
        }

        object.put("user",user);
        object.put("actUser",actUser);
        object.put("totalUser",totalUser);

        return object;
    }

    public Map<String, Object> isBillingCheck(String date)throws Exception{
        Map<String,Object> object = new HashMap<>();

        String cns = "X";
        String mega = "X";
        String kinx = "X";

        cns = billingMapper.isBillingInfo_CNS(date) > 0 ? "O":"X";
        mega = billingMapper.isBillingInfo_MEGA(date)  > 0 ? "O":"X";
        kinx = billingMapper.isBillingInfo_KINX(date)  > 0 ? "O":"X";

        object.put("cns",cns);
        object.put("mega",mega);
        object.put("kinx",kinx);

        return object;
    }

}
