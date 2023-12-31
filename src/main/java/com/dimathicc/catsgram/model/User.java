package com.dimathicc.catsgram.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String username;
    private String nickname;
}
