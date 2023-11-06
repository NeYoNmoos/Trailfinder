package at.fhv.hike.controllers;

import at.fhv.hike.data.RouteEntity;
import at.fhv.hike.hibernate.facade.TrailfinderDatabaseFacade;

import java.util.List;

public class RouteController {
    private TrailfinderDatabaseFacade _facade;

    public RouteController() {
        _facade = new TrailfinderDatabaseFacade();
    }

    public List<RouteEntity> getAllRoutes() {
        List<RouteEntity> allRoutes = _facade.getAllRoutes();
        return allRoutes;
    }
}
