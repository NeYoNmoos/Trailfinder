package propertySetterTests;

import at.fhv.hike.data.GalleryEntity;
import at.fhv.hike.data.RouteEntity;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class GalleryEntityTest {

    @Test
    void testGalleryEntityGettersAndSetters() {
        GalleryEntity gallery = new GalleryEntity();
        RouteEntity route = new RouteEntity();
        route.setRouteId(1);

        gallery.setPictureId(1);
        gallery.setRoute(route);
        byte[] pictureData = new byte[]{1, 2, 3, 4, 5};
        gallery.setPicture(pictureData);

        assertEquals(1, gallery.getPictureId());
        assertEquals(route, gallery.getRoute());
        assertArrayEquals(pictureData, gallery.getPicture());
    }
}
