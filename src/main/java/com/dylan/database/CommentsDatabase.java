package com.dylan.database;

import com.dylan.model.Comment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;

public class CommentsDatabase {
    private final Database database;

    public CommentsDatabase(Database database) throws SQLException {
        this.database = database;
        createTable();
    }

    public void saveComment(Comment bitch) throws SQLException {
        PreparedStatement fuck = database.prepareStatement(" insert into comment (author, content, pageId, timestamp)"
                + " values (?, ?, ?, ?)");
        fuck.setString(1, bitch.getAuthor());
        fuck.setString(2, bitch.getContent());
        fuck.setInt(3, bitch.getPageId());
        fuck.setLong(4, bitch.getTimeStamp());

        fuck.execute();
    }



    public Collection<Comment> readComments() throws SQLException{
        Statement st = database.getConnection().createStatement();
        String query = "select author, content, pageId, timestamp from comment";
        ResultSet rs = st.executeQuery(query);
        Collection<Comment> comments = new HashSet<>();
        while (rs.next()) {
            String auth = rs.getString("author");
            String con = rs.getString("content");
            int page = rs.getInt("pageId");
            long ti = rs.getInt("timestamp");
            Comment fuck = new Comment(auth, con, page, ti);
            comments.add(fuck);
        }
        return comments;
    }
    public Collection<Comment> readComments(int pageId) throws SQLException{
        PreparedStatement st = database.prepareStatement("SELECT author, content, pageId, timestamp FROM comment WHERE pageId = ?");
        st.setInt(1, pageId);
        ResultSet rs = st.executeQuery();
        Collection<Comment> comments = new HashSet<>();
        while (rs.next()) {
            String auth = rs.getString("author");
            String con = rs.getString("content");
            int page = rs.getInt("pageId");
            long ti = rs.getInt("timestamp");
            Comment fuck = new Comment(auth, con, page, ti);
            comments.add(fuck);
        }
        return comments;
    }

    private void createTable() throws SQLException {
        // create ya fuckin table on start up
        Statement statement = database.getConnection().createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS comment(author STRING, content STRING, pageId INT, timestamp LONG)");
    }
}
