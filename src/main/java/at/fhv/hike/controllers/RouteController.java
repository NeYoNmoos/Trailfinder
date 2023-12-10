package at.fhv.hike.controllers;

import at.fhv.hike.data.*;
import at.fhv.hike.hibernate.facade.TrailfinderDatabaseFacade;
import jakarta.servlet.ServletContext;

import java.util.ArrayList;
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

    public void createHuetteOnRoute(LodgeOnRouteEntity huetteOnRoute) {_facade.save(huetteOnRoute);}

    public void createPoiOnRoute(PoiOnRouteEntity poiOnRoute) {_facade.save(poiOnRoute);}

    public void createHuette(LodgeEntity huette) {_facade.save(huette);}

    public List<LodgeEntity> getAllHuetten() {
        List<LodgeEntity> huetten = _facade.getAllHuetten();
        return huetten;
    }

    public LodgeEntity getHuetteById(String huetteId) {
        return _facade.getHuetteById(huetteId);
    }

    public List<PointOfInterestEntity> getAllPois() {
        List<PointOfInterestEntity> pois = _facade.getAllPois();
        return pois;
    }

    public List<LodgeOnRouteEntity> getHuettenOnRouteByRouteId(String RouteId) {return _facade.getHuettenOnRouteByRouteId(RouteId);}

    public List<LodgeEntity> getAllHuettenForRoute(String routeId) {
        List<LodgeEntity> huetten = new ArrayList<>();
        List<LodgeOnRouteEntity> huettenOnRoute = new ArrayList<>();
        huettenOnRoute = getHuettenOnRouteByRouteId(routeId);
        int i = 0;
        while (i < huettenOnRoute.size()) {
            LodgeEntity huette = huettenOnRoute.get(i).getLodge();
            huetten.add(huette);
            i++;
        }

        return huetten;
    }

    public List<PoiOnRouteEntity> getPoisOnRouteByRouteId(String RouteId) {return _facade.getPoisOnRouteByRouteId(RouteId);}

    public List<PointOfInterestEntity> getAllPoisForRoute(String routeId) {
        List<PointOfInterestEntity> pois = new ArrayList<>();
        List<PoiOnRouteEntity> poisOnRoute = new ArrayList<>();
        poisOnRoute = getPoisOnRouteByRouteId(routeId);
        int i = 0;
        while (i < poisOnRoute.size()) {
            PointOfInterestEntity poi = poisOnRoute.get(i).getPointOfInterest();
            pois.add(poi);
            i++;
        }

        return pois;
    }
}
