package com.scheduler.mapper;

import com.scheduler.vo.BillingDataCNS;
import com.scheduler.vo.BillingDataMEGAKINX;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by WYKIM on 2017-08-16.
 */
@Mapper
public interface BillingMapper {
    @Select(" SELECT " +
            "        COUNT(*) " +
            "   FROM sbc_aws_account_billinginfo " +
            "  WHERE total_date = #{date}; ")
    Integer isBillingInfo_CNS(@Param("date") String date) throws Exception;

    @Select(" SELECT " +
            "        COUNT(*) " +
            "   FROM sbc_aws_account_billinginfo_total " +
            "  WHERE total_date = #{date}; ")
    Integer isBillingInfoTotal_CNS(@Param("date") String date) throws Exception;

    int insertBillingInfo_CNS(BillingDataCNS billingDataCNS) throws Exception;
    int insertBillingInfoTotal_CNS(BillingDataCNS billingDataCNS) throws Exception;
    @Select("SELECT product_code as productCode, SUM(total_cost) as totalCost, MAX(total_date) AS totalDate " +
            "  FROM sbc_aws_account_billinginfo " +
            " WHERE total_date = #{date} " +
            " GROUP BY product_code ORDER BY totalCost desc; ")
    List<BillingDataCNS> getListBillingInfoByProductGroup(@Param("date") String date) throws Exception;

    @Select(" SELECT " +
            "        COUNT(*) " +
            "   FROM sbc_mega_aws_account_billinginfo " +
            "  WHERE total_date = #{date}; ")
    Integer isBillingInfo_MEGA(@Param("date") String date) throws Exception;

    @Select(" SELECT " +
            "        COUNT(*) " +
            "   FROM sbc_kinx_aws_account_billinginfo " +
            "  WHERE total_date = #{date}; ")
    Integer isBillingInfo_KINX(@Param("date") String date) throws Exception;

    int insertBillingInfo_MEGA(BillingDataMEGAKINX billingDataMEGAKINX) throws Exception;
    int insertBillingInfo_KINX(BillingDataMEGAKINX billingDataMEGAKINX) throws Exception;

}
