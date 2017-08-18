package com.scheduler.mapper;

import com.scheduler.vo.BillingDataCNS;
import com.scheduler.vo.BillingDataMEGAKINX;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by WYKIM on 2017-08-16.
 */
@Mapper
public interface UserMapper {
    @Select(" SELECT " +
            "        COUNT(*) " +
            "   FROM sbc_kic_user " +
            "  WHERE crt_date = #{date}; ")
    Integer isUser_KIC(@Param("date") String date) throws Exception;

    @Select(" SELECT " +
            "        COUNT(*) " +
            "   FROM sbc_kic_act_user " +
            "  WHERE crt_date = #{date}; ")
    Integer isActUser_KIC(@Param("date") String date) throws Exception;

    @Select(" SELECT " +
            "        COUNT(*) " +
            "   FROM sbc_kic_total_user " +
            "  WHERE crt_date = #{date}; ")
    Integer isTotalUser_KIC(@Param("date") String date) throws Exception;

    @Select(" SELECT " +
            "        COUNT(*) " +
            "   FROM sbc_aic_user " +
            "  WHERE crt_date = #{date}; ")
    Integer isUser_AIC(@Param("date") String date) throws Exception;

    @Select(" SELECT " +
            "        COUNT(*) " +
            "   FROM sbc_aic_act_user " +
            "  WHERE crt_date = #{date}; ")
    Integer isActUser_AIC(@Param("date") String date) throws Exception;

    @Select(" SELECT " +
            "        COUNT(*) " +
            "   FROM sbc_aic_total_user " +
            "  WHERE crt_date = #{date}; ")
    Integer isTotalUser_AIC(@Param("date") String date) throws Exception;

    @Select(" SELECT " +
            "        COUNT(*) " +
            "   FROM sbc_eic_user " +
            "  WHERE crt_date = #{date}; ")
    Integer isUser_EIC(@Param("date") String date) throws Exception;

    @Select(" SELECT " +
            "        COUNT(*) " +
            "   FROM sbc_eic_act_user " +
            "  WHERE crt_date = #{date}; ")
    Integer isActUser_EIC(@Param("date") String date) throws Exception;

    @Select(" SELECT " +
            "        COUNT(*) " +
            "   FROM sbc_eic_total_user " +
            "  WHERE crt_date = #{date}; ")
    Integer isTotalUser_EIC(@Param("date") String date) throws Exception;

    @Select(" SELECT " +
            "        COUNT(*) " +
            "   FROM sbc_ruc_user " +
            "  WHERE crt_date = #{date}; ")
    Integer isUser_RUC(@Param("date") String date) throws Exception;

    @Select(" SELECT " +
            "        COUNT(*) " +
            "   FROM sbc_ruc_act_user " +
            "  WHERE crt_date = #{date}; ")
    Integer isActUser_RUC(@Param("date") String date) throws Exception;

    @Select(" SELECT " +
            "        COUNT(*) " +
            "   FROM sbc_ruc_total_user " +
            "  WHERE crt_date = #{date}; ")
    Integer isTotalUser_RUC(@Param("date") String date) throws Exception;


}
