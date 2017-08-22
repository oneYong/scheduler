package com.scheduler.utils;

import lombok.Data;

/**
 * Created by cloud4u on 2016-04-05.
 */
@Data
public class Email {
    private String fromUser;
    private String toUser;
    private String copyUser;
    private String subject;
    private String text;
}
