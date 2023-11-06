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
    public void testSaveRoute() {
        AttributeEntity attr = new AttributeEntity();
        attr.setRouteId("r001");

        facade.save(attr);

        RouteEntity route = facade.getRouteById("r001");

    }
}
