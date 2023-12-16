package at.fhv.hike.controllers;

import at.fhv.hike.data.CommentEntity;
import at.fhv.hike.data.RouteEntity;
import at.fhv.hike.hibernate.facade.TrailfinderDatabaseFacade;
import jakarta.servlet.ServletContext;

public class CommentController {
    private TrailfinderDatabaseFacade _facade;
    public CommentController(ServletContext context) {
        _facade = new TrailfinderDatabaseFacade(context);
    }
    public CommentController(TrailfinderDatabaseFacade facade) {
        _facade = facade;
    }
    public void createOrUpdateComment(CommentEntity comment) {
        _facade.save(comment);
    }

}
