package at.fhv.hibernate.facade;
import at.fhv.data.RouteEntity;
import data.Route;

import java.util.List;

public interface TrailfinderFacade {

    // INSERT + UPDATE
    public void save(Object value);

    // DELETE
    public void delete(Object value);

    // READ
    public List<RouteEntity> getAllRoutes();
    public RouteEntity getRouteById(String id);
}
