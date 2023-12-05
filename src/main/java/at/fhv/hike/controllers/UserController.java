package at.fhv.hike.controllers;

import at.fhv.hike.data.RouteEntity;
import at.fhv.hike.data.UserEntity;
import at.fhv.hike.hibernate.facade.TrailfinderDatabaseFacade;
import jakarta.servlet.ServletContext;

import java.util.List;

public class UserController {
    private TrailfinderDatabaseFacade _facade;

    public UserController(ServletContext context) {
        _facade = new TrailfinderDatabaseFacade(context);
    }

    public List<UserEntity> getAllUsers() {
        return _facade.getAllUsers();
    }
}
