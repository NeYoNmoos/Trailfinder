package propertySetterTests;

import at.fhv.hike.data.RouteEntity;
import at.fhv.hike.data.UserEntity;
import at.fhv.hike.data.AttributeEntity;
import at.fhv.hike.data.CoordinateEntity;
import at.fhv.hike.data.GalleryEntity;
import at.fhv.hike.data.CommentEntity;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RouteEntityTest {

    @Test
    void testRouteEntityGettersAndSetters() {
        RouteEntity route = new RouteEntity();

        route.setRouteId(1);
        route.setAuthor(new UserEntity());
        route.setName("Test Route");
        route.setLength(12.5);
        route.setAltitude(250.0);
        route.setLocation("Test Location");
        route.setDescription("Test Description");
        route.setDuration(3.5);
        route.setMonths(6);
        route.setActive(true);
        route.setAttributeEntity(new AttributeEntity());

        List<CoordinateEntity> coordinates = new ArrayList<>();
        coordinates.add(new CoordinateEntity());
        route.setCoordinates(coordinates);

        List<GalleryEntity> gallery = new ArrayList<>();
        gallery.add(new GalleryEntity());
        route.setGallery(gallery);

        List<CommentEntity> comments = new ArrayList<>();
        comments.add(new CommentEntity());
        route.setComments(comments);

        assertEquals(1, route.getRouteId());
        assertNotNull(route.getAuthor());
        assertEquals("Test Route", route.getName());
        assertEquals(12.5, route.getLength());
        assertEquals(250.0, route.getAltitude());
        assertEquals("Test Location", route.getLocation());
        assertEquals("Test Description", route.getDescription());
        assertEquals(3.5, route.getDuration());
        assertEquals(6, route.getMonths());
        assertTrue(route.getActive());
        assertNotNull(route.getAttributeEntity());
        assertEquals(1, route.getCoordinates().size());
        assertEquals(1, route.getGallery().size());
        assertEquals(1, route.getComments().size());
    }
}
