package com.dylan.service;

import com.dylan.database.SqlDatabase;
import com.dylan.model.Comment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;

/**
 * Allows reading and writing comments to/from a persistent database.
 */
public class CommentsDatabase extends SqlDatabase {

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS comment(author STRING," +
            " content STRING, pageId INT, timestamp LONG)";
    private static final String SELECT = "SELECT author, content, pageId, timestamp FROM " +
            "comment";
    private static final String SELECT_BY_PAGE = SELECT + " WHERE pageId = ?";
    private static final String INSERT = "INSERT INTO comment (author, content, pageId, " +
            "timestamp) VALUES (?, ?, ?, ?)";

    public CommentsDatabase() {
        super(CREATE_TABLE);
    }

    /**
     * Saves a {@link Comment} to the database.
     *
     * @param bitch The comment
     *
     * @throws SQLException On failing to save the comment
     */
    public void saveComment(Comment bitch) throws SQLException {
        PreparedStatement fuck = prepareStatement(INSERT);
        fuck.setString(1, bitch.getAuthor());
        fuck.setString(2, bitch.getContent());
        fuck.setInt(3, bitch.getPageId());
        fuck.setLong(4, bitch.getTimeStamp());

        fuck.execute();
    }

    /**
     * @return A {@link Collection} of all comments in the database.
     *
     * @throws SQLException On failing to read the comments.
     */
    public Collection<Comment> readComments() throws SQLException{
        PreparedStatement st = prepareStatement(SELECT);
        ResultSet rs = st.executeQuery();

        Collection<Comment> comments = new HashSet<>();
        while (rs.next()) {
            Comment fuck = parseComment(rs);
            comments.add(fuck);
        }

        return comments;
    }

    /**
     * Reads all of the comments for a given web page from the database.
     *
     * @param pageId The web page id.
     *
     * @return A {@link Collection} of comments for the given web page.
     *
     * @throws SQLException On failing to read the comments.
     */
    public Collection<Comment> readComments(int pageId) throws SQLException{
        PreparedStatement st = prepareStatement(SELECT_BY_PAGE);
        st.setInt(1, pageId);
        ResultSet rs = st.executeQuery();

        Collection<Comment> comments = new HashSet<>();
        while (rs.next()) {
            Comment fuck = parseComment(rs);
            comments.add(fuck);
        }
        return comments;
    }

    private Comment parseComment(ResultSet rs) throws SQLException {
        String auth = rs.getString("author");
        String con = rs.getString("content");
        int page = rs.getInt("pageId");
        long ti = rs.getInt("timestamp");

        return new Comment(auth, con, page, ti);
    }
}
