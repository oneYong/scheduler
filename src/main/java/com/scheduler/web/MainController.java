package com.scheduler.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by WYKIM on 2017-08-17.
 */
@Controller
public class MainController {
    @RequestMapping(value="/", method = RequestMethod.GET)
    @ResponseBody
    public String hello()
    {
        return "Hello Scheduler Home ... ";
    }
}
