import at.fhv.hike.controllers.RouteController;
import at.fhv.hike.data.RouteEntity;
import at.fhv.hike.hibernate.facade.TrailfinderDatabaseFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class RouteServiceTest {

    private RouteController routeService;

    @Mock
    private TrailfinderDatabaseFacade facade;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        routeService = new RouteController(facade);
    }

    @Test
    public void testGetRouteById() {
        RouteEntity newRoute = new RouteEntity();
        newRoute.setRouteId(1);
        when(facade.getRouteById("1")).thenReturn(newRoute);

        RouteEntity route = routeService.getRouteById("1");

        assertNotNull(route);
        assertEquals(1, route.getRouteId());

        verify(facade).getRouteById("1");
    }

    @Test
    public void testCreateRoute() {
        RouteEntity newRoute = new RouteEntity();
        newRoute.setName("New Route");

        routeService.createRoute(newRoute);

        verify(facade).save(newRoute);
    }

    @Test
    public void testUpdateRoute() {
        RouteEntity existingRoute = new RouteEntity();
        existingRoute.setRouteId(1);
        existingRoute.setName("Old Route");

        when(facade.getRouteById("1")).thenReturn(existingRoute);

        existingRoute.setName("Updated Route");
        routeService.createRoute(existingRoute);

        verify(facade).save(existingRoute);
        assertEquals("Updated Route", existingRoute.getName());
    }

    @Test
    public void testSoftDeleteRoute() {
        RouteEntity existingRoute = new RouteEntity();
        existingRoute.setRouteId(1);
        existingRoute.setActive(true);

        when(facade.getRouteById("1")).thenReturn(existingRoute);

        existingRoute.setActive(false);
        routeService.createRoute(existingRoute);

        verify(facade).save(existingRoute);
        assertFalse(existingRoute.getActive());
    }

    @Test
    public void testGetAllRoutes() {
        List<RouteEntity> routes = new ArrayList<>();
        routes.add(new RouteEntity());
        routes.add(new RouteEntity());

        when(facade.getAllRoutes()).thenReturn(routes);

        List<RouteEntity> retrievedRoutes = routeService.getAllRoutes();

        assertNotNull(retrievedRoutes);
        assertEquals(2, retrievedRoutes.size());
        verify(facade).getAllRoutes();
    }

    @Test
    public void testGetFilteredRoutes() {
        List<RouteEntity> filteredRoutes = new ArrayList<>();
        RouteEntity matchingRoute = new RouteEntity();
        filteredRoutes.add(matchingRoute);

        when(facade.getFilteredRoutes(
                anyString(), anyInt(), anyInt(), anyInt(), anyInt(), anyInt(), anyInt(),
                anyInt(), anyInt(), anyInt(), anyInt(), anyInt()
        )).thenReturn(filteredRoutes);

        List<RouteEntity> result = routeService.getFilteredRoutes(
                "Sample Route", 20, 5, 10, 2, 3000, 500, 4, 5, 3, 4, 6
        );

        assertNotNull(result);
        assertEquals(1, result.size());
        assertSame(matchingRoute, result.get(0));
        verify(facade).getFilteredRoutes(
                anyString(), anyInt(), anyInt(), anyInt(), anyInt(), anyInt(), anyInt(),
                anyInt(), anyInt(), anyInt(), anyInt(), anyInt()
        );
    }
}
