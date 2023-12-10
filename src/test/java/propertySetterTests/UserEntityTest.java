package propertySetterTests;

import at.fhv.hike.data.UserEntity;
import at.fhv.hike.data.CommentEntity;
import at.fhv.hike.data.RouteEntity;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserEntityTest {

    @Test
    void testUserEntityGettersAndSetters() {
        UserEntity user = new UserEntity();

        user.setUserId(1);
        user.setEmail("user@example.com");
        user.setPassword("password123");
        user.setUsername("username");
        user.setUserType(true);

        List<CommentEntity> comments = new ArrayList<>();
        comments.add(new CommentEntity());
        user.setComments(comments);

        List<RouteEntity> routes = new ArrayList<>();
        routes.add(new RouteEntity());
        user.setRoutes(routes);

        assertEquals(1, user.getUserId());
        assertEquals("user@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertEquals("username", user.getUsername());
        assertTrue(user.getUserType());
        assertEquals(1, user.getComments().size());
        assertEquals(1, user.getRoutes().size());
    }
}
