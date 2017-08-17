package com.scheduler.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by WYKIM on 2017-08-16.
 */
@Mapper
public interface CheckMapper {
    @Select("SELECT 1 ")
    public int select() throws Exception;
}
