package propertySetterTests;

import at.fhv.hike.data.AttributeEntity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AttributeEntityTest {

    @Test
    void testAttributeEntityGettersAndSetters() {
        AttributeEntity attribute = new AttributeEntity();

        attribute.setAttributeId(1);
        attribute.setStrength(5);
        attribute.setScenery(4);
        attribute.setExperience(3);
        attribute.setCondition(4);

        assertEquals(1, attribute.getAttributeId());
        assertEquals(5, attribute.getStrength());
        assertEquals(4, attribute.getScenery());
        assertEquals(3, attribute.getExperience());
        assertEquals(4, attribute.getCondition());
    }
}