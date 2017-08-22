package com.scheduler.service;


import com.scheduler.utils.CmmUtils;
import com.scheduler.utils.ERegion;
import com.scheduler.utils.Email;
import com.scheduler.utils.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by WYKIM on 2017-08-22.
 */
@Service
public class EmailSenderService {
    @Autowired
    private CheckService checkService;

    @Autowired
    private EmailSender emailSender;

    public void checkUserAndBillingResult() throws Exception {
        Email mailInfo = getMailInfo();
        String yesterday = CmmUtils.getGMTYesterday().replaceAll("-","");
        Map<String, Object> kicObject = checkService.isUserCheck(yesterday, ERegion.KIC);
        Map<String, Object> aicObject = checkService.isUserCheck(yesterday, ERegion.AIC);
        Map<String, Object> eicObject = checkService.isUserCheck(yesterday, ERegion.EIC);
        Map<String, Object> rucObject = checkService.isUserCheck(yesterday, ERegion.RUC);
        Map<String, Object> billingObject = checkService.isBillingCheck(CmmUtils.getGMTYesterday());

        String text = makeMailText(kicObject,aicObject,eicObject,rucObject,billingObject);

        mailInfo.setText(text);
        System.out.println(text);
        emailSender.sendEmail(mailInfo);

    }
        private Email getMailInfo(){
            Email email = new Email();
            email.setSubject("회원 & 빌링 데이터 정상 여부");
            email.setCopyUser("iersans@naver.com");
            email.setToUser("iersans@naver.com");
            email.setFromUser("DEV2015058@cnspartner.com");

            return email;
        }

        private String makeMailText(Map<String, Object> kicObject,Map<String, Object> aicObject
                ,Map<String, Object> eicObject,Map<String, Object> rucObject
                ,Map<String, Object> billingObject) throws Exception {
            String text = "";

            text += "- 빌링데이터 -\n\n";

            text+= "CNS(" + (billingObject.get("cns").toString().equals("O") ? "정상":"비정상") +")\n";
            text+= "메가존(" + (billingObject.get("mega").toString().equals("O") ? "정상":"비정상") +")\n";
            text+= "LIME(" + (billingObject.get("kinx").toString().equals("O") ? "정상":"비정상") +")\n\n";

            text += "- 회원데이터 -\n\n";

            text += "- KIC\n";
            text+= "USER (" + (kicObject.get("user").toString().equals("O") ? "정상":"비정상") +")\n";
            text+= "ACT USER(" + (kicObject.get("actUser").toString().equals("O") ? "정상":"비정상") +")\n";
            text+= "TOTAL USER(" + (kicObject.get("totalUser").toString().equals("O") ? "정상":"비정상") +")\n\n";

            text += "- AIC\n";
            text+= "USER (" + (aicObject.get("user").toString().equals("O") ? "정상":"비정상") +")\n";
            text+= "ACT USER(" + (aicObject.get("actUser").toString().equals("O") ? "정상":"비정상") +")\n";
            text+= "TOTAL USER(" + (aicObject.get("totalUser").toString().equals("O") ? "정상":"비정상") +")\n\n";

            text += "- EIC\n";
            text+= "USER (" + (eicObject.get("user").toString().equals("O") ? "정상":"비정상") +")\n";
            text+= "ACT USER(" + (eicObject.get("actUser").toString().equals("O") ? "정상":"비정상") +")\n";
            text+= "TOTAL USER(" + (eicObject.get("totalUser").toString().equals("O") ? "정상":"비정상") +")\n\n";

            text += "- RUC\n";
            text+= "USER (" + (rucObject.get("user").toString().equals("O") ? "정상":"비정상") +")\n";
            text+= "ACT USER(" + (rucObject.get("actUser").toString().equals("O") ? "정상":"비정상") +")\n";
            text+= "TOTAL USER(" + (rucObject.get("totalUser").toString().equals("O") ? "정상":"비정상") +")\n\n";


            return text;
        }
}
