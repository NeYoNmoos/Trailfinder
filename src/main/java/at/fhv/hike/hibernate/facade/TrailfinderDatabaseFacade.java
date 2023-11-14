package at.fhv.hike.hibernate.facade;

import at.fhv.hike.data.AttributeEntity;
import at.fhv.hike.data.CoordinateEntity;
import at.fhv.hike.data.RouteEntity;
import at.fhv.hike.data.TimeOfYearEntity;
import at.fhv.hike.hibernate.broker.AttributeBroker;
import at.fhv.hike.hibernate.broker.CoordinateBroker;
import at.fhv.hike.hibernate.broker.RouteBroker;
import at.fhv.hike.hibernate.broker.TimeOfYearBroker;
import jakarta.servlet.ServletContext;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class TrailfinderDatabaseFacade implements TrailfinderFacade{
    private RouteBroker _routeBroker;
    private AttributeBroker _attributeBroker;
    private TimeOfYearBroker _timeOfYearBroker;
    private CoordinateBroker _coordinateBroker;

    public TrailfinderDatabaseFacade(ServletContext context) {
        SessionFactory sessionFactory = (SessionFactory) context.getAttribute("SessionFactory");
        this._routeBroker = new RouteBroker(sessionFactory);
        this._attributeBroker = new AttributeBroker(sessionFactory);
        this._timeOfYearBroker = new TimeOfYearBroker(sessionFactory);
        this._coordinateBroker = new CoordinateBroker(sessionFactory);
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
        else if (value instanceof CoordinateEntity) {
            _coordinateBroker.insert((CoordinateEntity) value);
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
        else if (value instanceof CoordinateEntity) {
            _coordinateBroker.delete((CoordinateEntity) value);
        }
        // other delete statements go here for other entities
    }

    @Override
    public List<RouteEntity> getAllRoutes() {
        return _routeBroker.getAll();
    }

    public List<RouteEntity>getFilteredRoutes(String routename, Integer lengthMax, Integer lengthMin, Integer durationMax, Integer durationMin, Integer altitudeMax, Integer altitudeMin,Integer power,Integer scenery, Integer experience, Integer condition)
    {
        return _routeBroker.getFiltered(routename, lengthMax,lengthMin,durationMax,durationMin,altitudeMax,altitudeMin,power,scenery,experience,condition);
    }


    @Override
    public RouteEntity getRouteById(String id) {
        return _routeBroker.getById(id);
    }
}
