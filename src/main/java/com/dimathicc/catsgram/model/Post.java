package com.dimathicc.catsgram.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private Integer id;
    private User author;
    private LocalDate creationDate = LocalDate.now();
    private String description;
    private String photoUrl;

}
