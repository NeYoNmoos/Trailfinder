package tests;
import at.fhv.data.RouteEntity;
import at.fhv.hibernate.facade.TrailfinderDatabaseFacade;
import data.Route;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

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
    @Test
    public void testSaveRoute() {
        RouteEntity route = new RouteEntity();
        route.setRouteId(UUID.randomUUID().toString());
        route.setAuthor("u001");
        route.setName("Test Route");
        route.setLength(10.5);
        route.setAltitude(100.0);
        route.setLocation("Test Location");
        route.setDuration(2.5);
        route.setDescription("Test Description");

        System.out.println(route.getRouteId() + route.getAuthor() + route.getName());

        facade.save(route);
    }

}
