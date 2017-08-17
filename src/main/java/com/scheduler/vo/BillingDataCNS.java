package com.scheduler.vo;

import lombok.Data;

/**
 * Created by WYKIM on 2017-08-16.
 */
@Data
public class BillingDataCNS {
    private long id;
    private String accountId;
    private String accountName;
    private String tag;
    private String productCode;
    private String productName;
    private String totalDate;
    private double totalCost;
    private double beforeCostExtra;
}
