package com.scheduler.service;


import com.scheduler.utils.ERegion;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by WYKIM on 2017-08-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Rollback(value=true)
public class UserServiceTest {

    @Autowired
    private CheckService checkService;

    @Test
    public void test_run() throws Exception{
        System.out.println(checkService.isUserCheck("20170817", ERegion.KIC));
    }
}
