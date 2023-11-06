package at.fhv.hike.hibernate.facade;

import at.fhv.hike.data.RouteEntity;
import at.fhv.hike.hibernate.broker.RouteBroker;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class TrailfinderDatabaseFacade implements TrailfinderFacade{
    private RouteBroker routeBroker;

    public TrailfinderDatabaseFacade() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        this.routeBroker = new RouteBroker(sessionFactory);
    }

    @Override
    public void save(Object value) {
        if (value instanceof RouteEntity) {
            routeBroker.insert((RouteEntity) value);
        }
        // other insert statements go here for other entities
    }

    @Override
    public void delete(Object value) {
        if (value instanceof RouteEntity) {
            routeBroker.delete((RouteEntity) value);
        }
        // other delete statements go here for other entities
    }

    @Override
    public List<RouteEntity> getAllRoutes() {
        return routeBroker.getAll();
    }

    @Override
    public RouteEntity getRouteById(String id) {
        return routeBroker.getById(id);
    }
}
