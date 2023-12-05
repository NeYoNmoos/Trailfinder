package propertySetterTests;

import at.fhv.hike.data.LodgeEntity;
import at.fhv.hike.data.CoordinateEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LodgeEntityTest {

    private LodgeEntity lodgeEntity;

    @BeforeEach
    void setUp() {
        lodgeEntity = new LodgeEntity();
    }

    @Test
    void testLodgeId() {
        Integer expectedId = 1;
        lodgeEntity.setLodgeId(expectedId);
        assertEquals(expectedId, lodgeEntity.getLodgeId());
    }

    @Test
    void testCoordinateEntity() {
        CoordinateEntity coordinate = new CoordinateEntity();
        coordinate.setLatitude(12.345678);
        coordinate.setLongitude(98.7654321);

        lodgeEntity.setCoordinateEntity(coordinate);
        assertEquals(coordinate, lodgeEntity.getCoordinateEntity());
    }

    @Test
    void testDescription() {
        String expectedDescription = "A cozy mountain lodge";
        lodgeEntity.setDescription(expectedDescription);
        assertEquals(expectedDescription, lodgeEntity.getDescription());
    }

    @Test
    void testName() {
        String expectedName = "Mountain View Lodge";
        lodgeEntity.setName(expectedName);
        assertEquals(expectedName, lodgeEntity.getName());
    }
}
