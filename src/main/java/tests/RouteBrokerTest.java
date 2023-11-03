package tests;
import at.fhv.data.RouteEntity;
import at.fhv.hibernate.facade.TrailfinderDatabaseFacade;
import data.Route;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class RouteBrokerTest {
    private TrailfinderDatabaseFacade facade;

    @Before
    public void setUp() {
        facade = new TrailfinderDatabaseFacade();
    }

    @Test
    public void testGetRouteById() {
        RouteEntity route = facade.getRouteById("r002");
        System.out.println(route.getRouteId() + route.getLength() + route.getAltitude() + route.getName() + route.getDescription());
        assertNotNull(route);
    }

}
