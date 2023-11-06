package at.fhv.hike.hibernate.facade;

import at.fhv.hike.data.AttributeEntity;
import at.fhv.hike.data.RouteEntity;
import at.fhv.hike.data.TimeOfYearEntity;
import at.fhv.hike.hibernate.broker.AttributeBroker;
import at.fhv.hike.hibernate.broker.RouteBroker;
import at.fhv.hike.hibernate.broker.TimeOfYearBroker;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class TrailfinderDatabaseFacade implements TrailfinderFacade{
    private RouteBroker _routeBroker;
    private AttributeBroker _attributeBroker;
    private TimeOfYearBroker _timeOfYearBroker;

    public TrailfinderDatabaseFacade() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        this._routeBroker = new RouteBroker(sessionFactory);
        this._attributeBroker = new AttributeBroker(sessionFactory);
        this._timeOfYearBroker = new TimeOfYearBroker(sessionFactory);
    }

    @Override
    public void save(Object value) {
        if (value instanceof RouteEntity) {
            _routeBroker.insert((RouteEntity) value);
        }
        else if (value instanceof AttributeEntity) {
            _attributeBroker.insert((AttributeEntity) value);
        }
        else if (value instanceof TimeOfYearEntity) {
            _timeOfYearBroker.insert((TimeOfYearEntity) value);
        }
        // other insert statements go here for other entities
    }

    @Override
    public void delete(Object value) {
        if (value instanceof RouteEntity) {
            _routeBroker.delete((RouteEntity) value);
        }
        else if (value instanceof AttributeEntity) {
            _attributeBroker.delete((AttributeEntity) value);
        }
        else if (value instanceof TimeOfYearEntity) {
            _timeOfYearBroker.delete((TimeOfYearEntity) value);
        }
        // other delete statements go here for other entities
    }

    @Override
    public List<RouteEntity> getAllRoutes() {
        return _routeBroker.getAll();
    }

    @Override
    public RouteEntity getRouteById(String id) {
        return _routeBroker.getById(id);
    }
}
