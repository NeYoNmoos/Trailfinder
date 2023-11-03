package at.fhv.hibernate.facade;

import at.fhv.data.RouteEntity;
import at.fhv.hibernate.broker.RouteBroker;
import data.Route;
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
        if (value instanceof Route) {
            routeBroker.insert((RouteEntity) value);
        }
        // other insert statements go here for other entities
    }

    @Override
    public void delete(Object value) {
        if (value instanceof Route) {
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
