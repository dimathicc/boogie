package com.dimathicc.catsgram.model;

import lombok.Data;

import java.util.Date;

@Data
public class ChatMessage {
    private int id;
    private String userFrom;
    private String userTo;
    private Date sendDate;
    private String message;
    private boolean userRead;
}
