package com.scheduler.mapper;

import com.scheduler.utils.CmmUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by WYKIM on 2017-08-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserMapperTest {

    @Autowired
    private CheckMapper checkMapper;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test()throws Exception{
        System.out.println(checkMapper.select());
    }

    @Test
    public void test2()throws Exception{
        String yesterday = CmmUtils.getGMTYesterday().replaceAll("-","");
        System.out.println(userMapper.isActUser_KIC("20170818"));
    }
}
