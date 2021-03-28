import java.sql.SQLException;
import java.util.Collection;

public class CommentsDatabaseTest {
    public static void main(String[] args) throws SQLException {

        CommentsDatabase db = new CommentsDatabase( new SqlDatabase("jdbc:sqlite:test.db"));
        Comment fuck = new Comment("John", "GERGERGEG", 1, 102022);
        db.saveComment(fuck);
        // It's a lambda bitch

        Integer testPageId = 4;
        Collection<Comment> comments = db.readComments(testPageId);
        comments.forEach(System.out::println);
        assert comments.stream()
                .map(Comment::getPageId)
                .anyMatch(testPageId::equals);
}
}
