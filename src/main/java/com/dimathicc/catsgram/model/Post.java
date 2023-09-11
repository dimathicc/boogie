package com.dimathicc.catsgram.model;

import lombok.Data;

import java.time.Instant;
import java.util.Objects;

@Data
public class Post {
    private Integer id;
    private final String author;
    private final Instant creationDate = Instant.now();
    private String description;
    private String photoUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(creationDate, post.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creationDate);
    }
}
