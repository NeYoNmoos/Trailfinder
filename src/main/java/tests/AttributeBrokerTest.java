package tests;

import at.fhv.hike.data.AttributeEntity;
import at.fhv.hike.data.RouteEntity;
import at.fhv.hike.hibernate.facade.TrailfinderDatabaseFacade;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class AttributeBrokerTest {
    private TrailfinderDatabaseFacade facade;

    @Before
    public void setUp() {
        facade = new TrailfinderDatabaseFacade();
    }

    @Test
    public void testSaveAttribute() {
        AttributeEntity attribute = new AttributeEntity();

        attribute.setAttributeId(UUID.randomUUID().toString());
        // Set the enum values directly, assuming the database expects strings "1", "2", "3", etc.
        attribute.setStrength(1);
        attribute.setCondition(4);
        attribute.setScenery(5);
        attribute.setExperience(3);
        // Call facade.save with a try-catch block to handle any potential exceptions
        try {
            facade.save(attribute);
            System.out.println("Attribute saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to save attribute.");
        }
    }
}
