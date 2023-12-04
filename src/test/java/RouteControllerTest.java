import at.fhv.hike.controllers.RouteController;
import at.fhv.hike.data.RouteEntity;
import at.fhv.hike.data.AttributeEntity;
import at.fhv.hike.data.CoordinateEntity;
import at.fhv.hike.hibernate.facade.TrailfinderDatabaseFacade;
import jakarta.servlet.ServletContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class RouteControllerTest {

    @Mock
    private TrailfinderDatabaseFacade facade;

    @Mock
    private ServletContext context;

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;
    @InjectMocks
    private RouteController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new RouteController(facade);
    }

    private RouteEntity createMockRoute() {
        RouteEntity route = new RouteEntity();
        route.setRouteId(1);
        route.setAuthor(123);
        route.setName("Test Route");
        route.setLength(5.5);
        route.setAltitude(200.0);
        route.setLocation("Test Location");
        route.setDescription("Test Description");
        route.setDuration(120.0);
        route.setMonths(6);
        route.setActive(true);

        AttributeEntity attribute = new AttributeEntity();
        attribute.setAttributeId(1);
        attribute.setStrength(3);
        attribute.setCondition(3);
        attribute.setScenery(4);
        attribute.setExperience(2);
        route.setAttributeEntity(attribute);

        List<CoordinateEntity> coordinates = new ArrayList<>();
        CoordinateEntity coordinate = new CoordinateEntity();
        coordinate.setCoordinateId(1);
        coordinate.setSequence(1);
        coordinate.setLongitude(11.11);
        coordinate.setLatitude(22.22);
        coordinates.add(coordinate);
        route.setCoordinates(coordinates);

        return route;
    }

    @Test
    void getAllRoutesTest() {
        // Arrange
        List<RouteEntity> mockRoutes = new ArrayList<>();
        mockRoutes.add(createMockRoute());
        when(facade.getAllRoutes()).thenReturn(mockRoutes);

        // Act
        List<RouteEntity> routes = controller.getAllRoutes();

        // Assert
        assertEquals(mockRoutes, routes);
        verify(facade).getAllRoutes();
    }
    @Test
    void getFilteredRoutesTestNoResults() {
        // Arrange
        RouteEntity mockRoute = createMockRoute();
        List<RouteEntity> mockFilteredRoutes = new ArrayList<>();
        // No routes added to the list to simulate no results
        String routename = "Non-Existent Route";
        Integer lengthMax = 100, lengthMin = 50, // Adjust these values as needed
                durationMax = 500, durationMin = 300,
                altitudeMax = 1000, altitudeMin = 800,
                power = 5, scenery = 5,
                experience = 5, condition = 5, selectedMonth = 12;

        // Mock to return an empty list for these filters
        when(facade.getFilteredRoutes(routename.toLowerCase(), lengthMax, lengthMin, durationMax,
                durationMin, altitudeMax, altitudeMin, power,
                scenery, experience, condition, selectedMonth))
                .thenReturn(new ArrayList<>());

        // Act
        List<RouteEntity> routes = controller.getFilteredRoutes(routename, lengthMax, lengthMin,
                durationMax, durationMin, altitudeMax,
                altitudeMin, power, scenery,
                experience, condition, selectedMonth);

        // Assert
        assertTrue(routes.isEmpty()); // Check that the returned list is empty
        verify(facade).getFilteredRoutes(routename.toLowerCase(), lengthMax, lengthMin, durationMax,
                durationMin, altitudeMax, altitudeMin, power,
                scenery, experience, condition, selectedMonth);
    }

}
