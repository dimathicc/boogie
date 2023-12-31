package com.dimathicc.catsgram.dao.Impl;

import com.dimathicc.catsgram.dao.FollowDao;
import com.dimathicc.catsgram.dao.PostDao;
import com.dimathicc.catsgram.dao.UserDao;
import com.dimathicc.catsgram.model.Post;
import com.dimathicc.catsgram.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Component
public class FollowDaoImpl implements FollowDao {
    private final JdbcTemplate jdbcTemplate;
    private final UserDao userDao;
    private final PostDao postDao;

    @Autowired
    public FollowDaoImpl(JdbcTemplate jdbcTemplate, UserDao userDao, PostDao postDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = userDao;
        this.postDao = postDao;
    }

    @Override
    public List<Post> getFollowFeed(String userId, int max) {
        String sql = "SELECT cf.user_id, " +
                "cu.username, " +
                "cu.nickname, " +
                "cp.id AS post_id, " +
                "cp.description, " +
                "cp.photo_url, " +
                "cp.creation_date " +
                "FROM cat_post AS cp " +
                "INNER JOIN cat_user AS cu ON cu.id = cp.author_id " +
                "INNER JOIN cat_follow AS cf ON cf.user_id = cu.id " +
                "WHERE author_id IN (SELECT id " +
                "FROM cat_user " +
                "WHERE id IN (SELECT follow_id " +
                "FROM cat_follow " +
                "WHERE user_id = ?)) " +
                "GROUP BY cf.user_id, " +
                "cu.username, " +
                "cu.nickname, " +
                "post_id, " +
                "cp.description, " +
                "cp.photo_url, " +
                "cp.creation_date " +
                "ORDER BY cp.creation_date DESC " +
                "LIMIT ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> makeFollow(rs), userId, max);
    }

    private Post makeFollow(ResultSet rs) throws SQLException {
        String userId = rs.getString("user_id");
        String userName = rs.getString("username");
        String nickName = rs.getString("nickname");
        String description = rs.getString("description");
        String photo_url = rs.getString("photo_url");
        Integer postId = rs.getInt("post_id");
        LocalDate creationDate = rs.getDate("creation_date").toLocalDate();
        return new Post(postId, new User(userId, userName, nickName), creationDate, description, photo_url);
    }
}
