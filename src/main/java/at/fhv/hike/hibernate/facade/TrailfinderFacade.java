package at.fhv.hike.hibernate.facade;
import at.fhv.hike.data.RouteEntity;

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
