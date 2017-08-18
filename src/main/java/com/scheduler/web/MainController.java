package com.scheduler.web;

import com.scheduler.service.CheckService;
import com.scheduler.utils.CmmUtils;
import com.scheduler.utils.ERegion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by WYKIM on 2017-08-17.
 */
@Controller
public class MainController {
    @Autowired
    private CheckService checkService;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String hello(Model model) throws Exception
    {
        String yesterday = CmmUtils.getGMTYesterday().replaceAll("-","");
        String today = CmmUtils.getGMTTodayTime();
        Map<String, Object> kicObject = checkService.isUserCheck(yesterday, ERegion.KIC);
        Map<String, Object> aicObject = checkService.isUserCheck(yesterday, ERegion.AIC);
        Map<String, Object> eicObject = checkService.isUserCheck(yesterday, ERegion.EIC);
        Map<String, Object> rucObject = checkService.isUserCheck(yesterday, ERegion.RUC);
        Map<String, Object> billingObject = checkService.isBillingCheck(CmmUtils.getGMTYesterday());

        model.addAttribute("today",today);
        model.addAttribute("kicObject",kicObject);
        model.addAttribute("aicObject",aicObject);
        model.addAttribute("eicObject",eicObject);
        model.addAttribute("rucObject",rucObject);
        model.addAttribute("billingObject",billingObject);

        return "dashboard";
    }
}
