package propertySetterTests;

import at.fhv.hike.data.CoordinateEntity;
import at.fhv.hike.data.RouteEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateEntityTest {

    private CoordinateEntity coordinateEntity;

    @BeforeEach
    void setUp() {
        coordinateEntity = new CoordinateEntity();
    }

    @Test
    void testCoordinateId() {
        Integer expectedId = 1;
        coordinateEntity.setCoordinateId(expectedId);
        assertEquals(expectedId, coordinateEntity.getCoordinateId());
    }

    @Test
    void testSequence() {
        Integer expectedSequence = 5;
        coordinateEntity.setSequence(expectedSequence);
        assertEquals(expectedSequence, coordinateEntity.getSequence());
    }

    @Test
    void testLongitude() {
        Double expectedLongitude = 12.345678;
        coordinateEntity.setLongitude(expectedLongitude);
        assertEquals(expectedLongitude, coordinateEntity.getLongitude());
    }

    @Test
    void testLatitude() {
        Double expectedLatitude = 98.7654321;
        coordinateEntity.setLatitude(expectedLatitude);
        assertEquals(expectedLatitude, coordinateEntity.getLatitude());
    }

    @Test
    void testRouteAssociation() {
        RouteEntity route = new RouteEntity();
        route.setName("Test Route");

        coordinateEntity.setRoute(route);
        assertEquals(route, coordinateEntity.getRoute());
    }
}
