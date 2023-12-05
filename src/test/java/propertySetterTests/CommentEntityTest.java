package propertySetterTests;

import at.fhv.hike.data.CommentEntity;
import at.fhv.hike.data.RouteEntity;
import at.fhv.hike.data.UserEntity;
import at.fhv.hike.data.AttributeEntity;
import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CommentEntityTest {

    private CommentEntity commentEntity;

    @BeforeEach
    void setUp() {
        commentEntity = new CommentEntity();
    }

    @Test
    void testCommentId() {
        Integer expectedId = 1;
        commentEntity.setCommentId(expectedId);
        assertEquals(expectedId, commentEntity.getCommentId());
    }

    @Test
    void testRoute() {
        RouteEntity route = new RouteEntity();
        route.setRouteId(1);
        commentEntity.setRoute(route);
        assertEquals(route, commentEntity.getRoute());
    }

    @Test
    void testAuthor() {
        UserEntity author = new UserEntity();
        author.setUserId(1);
        commentEntity.setAuthor(author);
        assertEquals(author, commentEntity.getAuthor());
    }

    @Test
    void testComment() {
        String expectedComment = "This is a test comment";
        commentEntity.setComment(expectedComment);
        assertEquals(expectedComment, commentEntity.getComment());
    }

    @Test
    void testAttributeId() {
        AttributeEntity attribute = new AttributeEntity();
        attribute.setAttributeId(1);
        commentEntity.setAttributeId(attribute);
        assertEquals(attribute, commentEntity.getAttributeId());
    }

    @Test
    void testDateComment() {
        Date expectedDate = Date.valueOf("2023-03-01");
        commentEntity.setDateComment(expectedDate);
        assertEquals(expectedDate, commentEntity.getDateComment());
    }
}