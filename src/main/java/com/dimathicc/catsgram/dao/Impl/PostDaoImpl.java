package com.dimathicc.catsgram.dao.Impl;

import com.dimathicc.catsgram.dao.PostDao;
import com.dimathicc.catsgram.exceptions.UserNotFoundException;
import com.dimathicc.catsgram.model.Post;
import com.dimathicc.catsgram.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Component
public class PostDaoImpl implements PostDao {

    private final JdbcTemplate jdbcTemplate;

    public PostDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Post> findAllByUser(User user) {
        String sql = "select * from cat_post where author_id = ? order by creation_date desc";
        return jdbcTemplate.query(sql, (rs, rowNum) -> makePost(user, rs), user.getId());
    }

    private Post makePost(User user, ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        String description = rs.getString("description");
        String photoUrl = rs.getString("photo_url");
        LocalDate creationDate = rs.getDate("creation_date").toLocalDate();
        return new Post(id, user, creationDate, description, photoUrl);
    }

    @Override
    public Post addPost(Post post) {
        SqlRowSet rawUser = jdbcTemplate.queryForRowSet("SELECT id, username, nickname FROM cat_user WHERE " +
                "id =?", post.getAuthor().getId());
        User user = new User();
        if (rawUser.next()) {
            user.setId(rawUser.getString("id"));
            user.setUsername(rawUser.getString("username"));
            user.setNickname(rawUser.getString("nickname"));
        } else {
            throw new UserNotFoundException(String.format("Пользователь с id%s не найден.", post.getAuthor().getId()));
        }
        String sql = "INSERT INTO cat_post(author_id, description, photo_url, creation_date) VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(sql, post.getAuthor().getId(), post.getDescription(), post.getPhotoUrl(),
                post.getCreationDate());
        SqlRowSet rawPost = jdbcTemplate
                .queryForRowSet("SELECT id, author_id, description, photo_url, creation_date FROM cat_post " +
                                "WHERE author_id = ? AND description = ? AND photo_url = ?", post.getAuthor().getId(),
                        post.getDescription(), post.getPhotoUrl());
        if (rawPost.next()) {
            return new Post(
                    rawPost.getInt("id"),
                    user,
                    Objects.requireNonNull(rawPost.getDate("creation_date")).toLocalDate(),
                    rawPost.getString("description"),
                    rawPost.getString("photo_url")
            );
        }
        return post;
    }

}
