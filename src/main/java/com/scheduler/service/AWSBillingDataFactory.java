package com.scheduler.service;

import com.scheduler.utils.ECompany;
import com.scheduler.vo.BillingDataCNS;
import com.scheduler.vo.BillingDataMEGAKINX;

import com.scheduler.vo.ClientSignatureGenerator;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;

import java.util.List;

/**
 * Created by WYKIM on 2017-08-16.
 */
@Service
public class AWSBillingDataFactory {


    private final String MEGA_URL = "http://52.78.8.121:8080/api/billing/getBillingList.do?billingMonth=";
    private final String KINX_URL = "http://52.78.8.121:8080/api/billing/getBillingLimeList.do?billingMonth=";


    public List<BillingDataMEGAKINX> makeMEGAKINX_BillingData(String date, ECompany eCompany) throws Exception {
        String getData = "";
        switch (eCompany){
            case MEGA: getData = this.getMEGAKINX_BillingData(date,ECompany.MEGA); break;
            case KINX: getData = this.getMEGAKINX_BillingData(date,ECompany.KINX); break;
        }
        List<BillingDataMEGAKINX> resultData = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(getData);
        List<String> accountKeyList = new ArrayList<>();
        Iterator<String> tempIterator = jsonObject.keys();
        setKeyList(accountKeyList, tempIterator);

        for(int accountIndex = 0; accountIndex < accountKeyList.size(); accountIndex++){
            String accountKey = accountKeyList.get(accountIndex);
            JSONObject serviceObject = (JSONObject) jsonObject.get(accountKey);

            List<String> serviceKeyList =new ArrayList<String>();
            tempIterator = serviceObject.keys();
            setKeyList(serviceKeyList, tempIterator);
            //System.out.println(serviceKeyList);
            for(int serviceIndex = 0; serviceIndex < serviceKeyList.size(); serviceIndex++){
                String serviceName = serviceKeyList.get(serviceIndex);
                JSONObject regionObject = (JSONObject) serviceObject.get(serviceName);

                List<String> regisonKeyList =new ArrayList<String>();
                tempIterator = regionObject.keys();
                setKeyList(regisonKeyList, tempIterator);
                for(int regionIndex = 0; regionIndex < regisonKeyList.size(); regionIndex++){
                    String regionName = regisonKeyList.get(regionIndex);

                    String data = ((JSONObject) regionObject.get(regionName)).toString();
                    data = data.replace('{', ' ').replace('}', ' ').replace('"', ' ');
                    String[] splitData = data.split(",");

                    for(int i = 0; i < splitData.length; i++){
                        String name = splitData[i].split(":")[0].trim();
                        String value = splitData[i].split(":")[1].trim();

                        BillingDataMEGAKINX meBillingInfo = new BillingDataMEGAKINX();
                        meBillingInfo.setAccountId(accountKey);
                        meBillingInfo.setServiceName(serviceName);
                        meBillingInfo.setRegionName(regionName);
                        meBillingInfo.setProductCode(name);
                        meBillingInfo.setProductName(name);
                        meBillingInfo.setTotalCost(Double.parseDouble(value));
                        resultData.add(meBillingInfo);
                    }
                }

            }
        }

        return resultData;

    }
        private void setKeyList(List<String> accountKeyList,
                                       Iterator<String> tempIterator) {
            while(tempIterator.hasNext())
            {
                String keys = tempIterator.next().toString();
                accountKeyList.add(keys);
            }
        }
        private String getMEGAKINX_BillingData(String date,ECompany eCompany){
            String year = date.split("-")[0];
            String month = date.split("-")[1];
            String requestURL = "";

            switch (eCompany){
                case MEGA: requestURL+= MEGA_URL + year+ month; break;
                case KINX: requestURL+= KINX_URL + year+ month; break;
            }

            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(requestURL,String.class);

            return result;
        }


    public List<BillingDataCNS> makeCNS_BillingData(String date) throws Exception {
        String data = this.getCNS_BillingData(date);
        List<BillingDataCNS> billingDataCNSList = new ArrayList<>();

        //parsing...
        JSONObject jsonObject = new JSONObject(data);
        JSONArray jsonList = jsonObject.getJSONArray("list");

        for(int i = 0; i < jsonList.length(); i++)
        {
            String accountId =  (String)((JSONObject)jsonList.get(i)).get("accountId");
            String accountName =  (String)((JSONObject)jsonList.get(i)).get("accountName");
            String tag =  (String)((JSONObject)jsonList.get(i)).get("tag");
            String productCode =  (String)((JSONObject)jsonList.get(i)).get("productCode");
            String productName =  (String)((JSONObject)jsonList.get(i)).get("productName");
            double totalCost =  Double.parseDouble((String)((JSONObject)jsonList.get(i)).get("totalCost"));

            BillingDataCNS billingDataCNS = new BillingDataCNS();
            billingDataCNS.setAccountId(accountId);
            billingDataCNS.setAccountName(accountName);
            billingDataCNS.setTag(tag);
            billingDataCNS.setProductCode(productCode);
            billingDataCNS.setProductName(productName);
            billingDataCNS.setTotalCost(totalCost);

            billingDataCNSList.add(billingDataCNS);
        }

        return billingDataCNSList;

    }

    public List<BillingDataCNS> makeCNS_BillingData_NEW(String date) throws Exception {
        List<BillingDataCNS> billingDataCNSList = new ArrayList<>();
        JSONObject jsonObject = null;
        JSONArray jsonList = null;

        // 빌링 데이터 셋팅 1
        try{
            //parsing...
            jsonObject = new JSONObject(this.getCNS_BillingData_NEW(date));
            jsonList = jsonObject.getJSONArray("list");

            for(int i = 0; i < jsonList.length(); i++)
            {
                String accountId =  (String)((JSONObject)jsonList.get(i)).get("accountId");
                String accountName =  (String)((JSONObject)jsonList.get(i)).get("accountName");
                String tag =  (String)((JSONObject)jsonList.get(i)).get("tag");
                String productCode =  (String)((JSONObject)jsonList.get(i)).get("productCode");
                String productName =  (String)((JSONObject)jsonList.get(i)).get("productName");
                double totalCost = (Double)((JSONObject)jsonList.get(i)).get("totalCost");

                BillingDataCNS billingDataCNS = new BillingDataCNS();
                billingDataCNS.setAccountId(accountId);
                billingDataCNS.setAccountName(accountName);
                billingDataCNS.setTag(tag);
                billingDataCNS.setProductCode(productCode);
                billingDataCNS.setProductName(productName);
                billingDataCNS.setTotalCost(totalCost);

                billingDataCNSList.add(billingDataCNS);
            }
        } catch (Exception e){
            System.out.println("error message : " + e.getMessage());
        }

        // 빌링 데이터 셋팅 2
        try{
            //parsing...
            jsonObject = new JSONObject(this.getCNS_BillingData_ADD1(date));
            jsonList = jsonObject.getJSONArray("list");

            for(int i = 0; i < jsonList.length(); i++)
            {
                String accountId =  (String)((JSONObject)jsonList.get(i)).get("accountId");
                String accountName =  (String)((JSONObject)jsonList.get(i)).get("accountName");
                String tag =  "intellytics_SDT";
                String productCode =  (String)((JSONObject)jsonList.get(i)).get("productCode");
                String productName =  (String)((JSONObject)jsonList.get(i)).get("productName");
                double totalCost = (Double)((JSONObject)jsonList.get(i)).get("totalCost");

                BillingDataCNS billingDataCNS = new BillingDataCNS();
                billingDataCNS.setAccountId(accountId);
                billingDataCNS.setAccountName(accountName);
                billingDataCNS.setTag(tag);
                billingDataCNS.setProductCode(productCode);
                billingDataCNS.setProductName(productName);
                billingDataCNS.setTotalCost(totalCost);

                billingDataCNSList.add(billingDataCNS);
            }
        } catch (Exception e){
            System.out.println("error message : " + e.getMessage());
        }

        // 빌링 데이터 셋팅 3
        try{
            //parsing...
            jsonObject = new JSONObject(this.getCNS_BillingData_ADD2(date));
            jsonList = jsonObject.getJSONArray("list");

            for(int i = 0; i < jsonList.length(); i++)
            {
                String accountId =  (String)((JSONObject)jsonList.get(i)).get("accountId");
                String accountName =  (String)((JSONObject)jsonList.get(i)).get("accountName");
                String tag =  "intellytics_PROD";
                String productCode =  (String)((JSONObject)jsonList.get(i)).get("productCode");
                String productName =  (String)((JSONObject)jsonList.get(i)).get("productName");
                double totalCost = (Double)((JSONObject)jsonList.get(i)).get("totalCost");

                BillingDataCNS billingDataCNS = new BillingDataCNS();
                billingDataCNS.setAccountId(accountId);
                billingDataCNS.setAccountName(accountName);
                billingDataCNS.setTag(tag);
                billingDataCNS.setProductCode(productCode);
                billingDataCNS.setProductName(productName);
                billingDataCNS.setTotalCost(totalCost);

                billingDataCNSList.add(billingDataCNS);
            }
        } catch (Exception e){
            System.out.println("error message : " + e.getMessage());
        }


        return billingDataCNSList;

    }
        // version 1
        private String getCNS_BillingData(String today){
            String CNS_URL = "http://52.41.107.22/api/billing/selectAccountBillingInfo.do";
            String CNS_USER_ID = "lge_cloudcenter_infra";
            String CNS_PASSWORD = "!dlatl00";
            String CNS_ACCOUNT_ID = "160945176187";

            String year = today.split("-")[0];
            String month = today.split("-")[1];

            String requestURL = CNS_URL + "?" +"userId=" + CNS_USER_ID + "&passwd=" + CNS_PASSWORD
                    + "&year=" + year + "&month=" + month + "&accountId=" + CNS_ACCOUNT_ID;

            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(requestURL,String.class);

            return result;
        }

    // version 2
    // SDP, SDP_MySQL 등 ...
    public String getCNS_BillingData_NEW(String today){
        String year = today.split("-")[0];
        String month = today.split("-")[1];
        String serverAddr = "https://sep.mashup-plus.com:443";
        String restUri = "/rs/aws/sdp/retrieveProductCost";
        String accessKey = "JLY84KMHL4F4KFZ8L8GO";
        String secretKey = "HrdE8qENAAR2muoyl+KcpA==";
        String accountId = "160945176187";
        String tagNm = "user:System";
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("year",year);
        jsonObject.put("month",month);
        jsonObject.put("accountId",accountId);
        jsonObject.put("tagNm",tagNm);

        String jsonMsg = jsonObject.toString();
        String result = "";

        result = getCNSBillingApi(serverAddr, restUri, accessKey, secretKey, jsonMsg, result);

        return result;
    }

    // version 2
    // intellytics_SDT
    public String getCNS_BillingData_ADD1(String today){
        String year = today.split("-")[0];
        String month = today.split("-")[1];
        String serverAddr = "https://sep.mashup-plus.com:443";
        String restUri = "/rs/aws/sdp/retrieveProductCost";
        String accessKey = "JLY84KMHL4F4KFZ8L8GO";
        String secretKey = "HrdE8qENAAR2muoyl+KcpA==";
        String accountId = "708801539634 ";
        String tagNm = "user:CreatedBy";
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("year",year);
        jsonObject.put("month",month);
        jsonObject.put("accountId",accountId);
        jsonObject.put("tagNm",tagNm);

        String jsonMsg = jsonObject.toString();
        String result = "";

        result = getCNSBillingApi(serverAddr, restUri, accessKey, secretKey, jsonMsg, result);

        return result;
    }
    // version 2
    // intellytics_PROD
    public String getCNS_BillingData_ADD2(String today){
        String year = today.split("-")[0];
        String month = today.split("-")[1];
        String serverAddr = "https://sep.mashup-plus.com:443";
        String restUri = "/rs/aws/sdp/retrieveProductCost";
        String accessKey = "JLY84KMHL4F4KFZ8L8GO";
        String secretKey = "HrdE8qENAAR2muoyl+KcpA==";
        String accountId = "077707537966";
        String tagNm = "user:CreatedBy";
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("year",year);
        jsonObject.put("month",month);
        jsonObject.put("accountId",accountId);
        jsonObject.put("tagNm",tagNm);

        String jsonMsg = jsonObject.toString();
        String result = "";

        result = getCNSBillingApi(serverAddr, restUri, accessKey, secretKey, jsonMsg, result);

        return result;
    }

        private String getCNSBillingApi(String serverAddr, String restUri, String accessKey, String secretKey, String jsonMsg, String result) {
            try{
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(serverAddr + restUri);

                String signature = new ClientSignatureGenerator().generateSignature(httpPost.getMethod()+restUri+jsonMsg,secretKey);
                httpPost.addHeader("accessKey",accessKey);
                httpPost.addHeader("signature",signature);

                httpPost.setEntity(new StringEntity(jsonMsg,"UTF-8"));
                httpPost.setHeader("CONTENT-TYPE","application/json");
                HttpResponse response = httpClient.execute(httpPost);
                if(response.getEntity() != null){
                    result =  EntityUtils.toString(response.getEntity(),"UTF-8");
                }else
                    result =  null;

            } catch (Exception e){

            }
            return result;
        }

}
