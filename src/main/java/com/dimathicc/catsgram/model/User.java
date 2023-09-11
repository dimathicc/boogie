package com.dimathicc.catsgram.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class User {
    private String email;
    private String nickname;
    private LocalDate birthdate;
}
