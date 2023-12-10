package at.fhv.hike.controllers;

import at.fhv.hike.data.AttributeEntity;
import at.fhv.hike.data.RouteEntity;
//import at.fhv.hike.data.TimeOfYearEntity;
import at.fhv.hike.hibernate.facade.TrailfinderDatabaseFacade;
import jakarta.servlet.ServletContext;

import java.util.List;

public class RouteController {
    private TrailfinderDatabaseFacade _facade;

    public RouteController(ServletContext context) {
        _facade = new TrailfinderDatabaseFacade(context);
    }

    public RouteController(TrailfinderDatabaseFacade facade) {
        _facade = facade;
    }

    public List<RouteEntity> getAllRoutes() {
        List<RouteEntity> allRoutes = _facade.getAllRoutes();
        return allRoutes;
    }
    public List<RouteEntity> getFilteredRoutes(String routename, Integer lengthMax, Integer lengthMin, Integer durationMax, Integer durationMin, Integer altitudeMax, Integer altitudeMin,Integer power,Integer scenery,Integer experience, Integer condition, Integer selectedMonth) {
        routename = routename.toLowerCase();
        List<RouteEntity> filteredRoutes = _facade.getFilteredRoutes(routename, lengthMax,lengthMin,durationMax,durationMin,altitudeMax,altitudeMin,power,scenery,experience,condition,selectedMonth);
        return filteredRoutes;
    }

    public List<RouteEntity> getALlRoutesWithCoordinates() {
        List<RouteEntity> allRoutes = _facade.getAllRoutes();
        return allRoutes;
    }

    public RouteEntity getRouteById(String routeId){
        return _facade.getRouteById(routeId);
    }

    public void createRoute(RouteEntity route) {
        _facade.save(route);
    }

    public void saveObjectInDb(Object object) { _facade.save(object);}

    public TrailfinderDatabaseFacade getFacade() {return _facade;}
}
