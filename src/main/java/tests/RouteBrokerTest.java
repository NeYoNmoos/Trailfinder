package tests;
import at.fhv.hike.data.AttributeEntity;
import at.fhv.hike.data.CoordinateEntity;
import at.fhv.hike.data.RouteEntity;
import at.fhv.hike.data.TimeOfYearEntity;
import at.fhv.hike.hibernate.broker.TimeOfYearBroker;
import at.fhv.hike.hibernate.facade.TrailfinderDatabaseFacade;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.List;
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

    @Test
    public void testSaveCompleteRoute() {
        // Create the AttributeEntity
        AttributeEntity attribute = new AttributeEntity();
        attribute.setAttributeId(UUID.randomUUID().toString());
        attribute.setStrength(3);
        attribute.setCondition(1);
        attribute.setScenery(1);
        attribute.setExperience(1);
        //facade.save(attribute);

        // Create the TimeOfYearEntity
        TimeOfYearEntity timeOfYear = new TimeOfYearEntity();
        timeOfYear.setMonthId(UUID.randomUUID().toString());
        timeOfYear.setJanuary(Math.random() > 0.5);
        timeOfYear.setFebruary(Math.random() > 0.5);
        timeOfYear.setMarch(Math.random() > 0.5);
        timeOfYear.setApril(Math.random() > 0.5);
        timeOfYear.setMay(Math.random() > 0.5);
        timeOfYear.setJune(Math.random() > 0.5);
        timeOfYear.setJuly(Math.random() > 0.5);
        timeOfYear.setAugust(Math.random() > 0.5);
        timeOfYear.setSeptember(Math.random() > 0.5);
        timeOfYear.setOctober(Math.random() > 0.5);
        timeOfYear.setNovember(Math.random() > 0.5);
        timeOfYear.setDecember(Math.random() > 0.5);
        // Save the time of year
        //facade.save(timeOfYear);

        // Create the RouteEntity
        RouteEntity route = new RouteEntity();
        route.setRouteId(UUID.randomUUID().toString());
        route.setAuthor("u001");
        route.setName("Full test route");
        route.setLength(10.5);
        route.setAltitude(100.0);
        route.setLocation("Test Location");
        route.setDescription("Test Description");
        route.setDuration(2.5);
        //route.setAttributeId(attribute.getAttributeId());
        //route.setMonthId(timeOfYear.getMonthId());
        route.setAttributeEntity(attribute);
        route.setTimeOfYearEntity(timeOfYear);

        // Save the route
        facade.save(route);

        // Create the CoordinateEntities
        CoordinateEntity coordinate1 = new CoordinateEntity();
        coordinate1.setCoordinateId(UUID.randomUUID().toString());
        coordinate1.setRoute(route);
        coordinate1.setSequence(0);
        coordinate1.setLongitude(-123.3656);
        coordinate1.setLatitude(48.4284);
        facade.save(coordinate1);

        CoordinateEntity coordinate2 = new CoordinateEntity();
        coordinate2.setCoordinateId(UUID.randomUUID().toString());
        coordinate2.setRoute(route);
        coordinate2.setSequence(1);
        coordinate2.setLongitude(-123.3656);
        coordinate2.setLatitude(48.4285);
        facade.save(coordinate2);

        // ... Create and save additional CoordinateEntities as needed

        // Verify the creation by printing out the IDs
        System.out.println("Route ID: " + route.getRouteId() +
                ", Attribute ID: " + attribute.getAttributeId() +
                ", TimeOfYear ID: " + timeOfYear.getMonthId());
    }



    @Test
    public void testGetAllRoutes(){
        List<RouteEntity> allRoutes = facade.getAllRoutes();
        for(int i = 0; i < allRoutes.size(); i++){
            System.out.println(allRoutes.get(i).getRouteId() + " " + allRoutes.get(i).getName());
            System.out.println(allRoutes.get(i).getCoordinates());
        }
    }

}
