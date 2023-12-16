package at.fhv.hike.controllers;

import at.fhv.hike.data.CommentEntity;
import at.fhv.hike.data.RouteEntity;
import at.fhv.hike.hibernate.facade.TrailfinderDatabaseFacade;
import jakarta.servlet.ServletContext;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
    public static void sortComments(List<CommentEntity> comments) {
        // Use Collections.sort() with a custom comparator
        Collections.sort(comments, new Comparator<CommentEntity>() {
            @Override
            public int compare(CommentEntity comment1, CommentEntity comment2) {
                // Compare the dateComment fields in reverse order (from newest to oldest)
                return comment2.getDateComment().compareTo(comment1.getDateComment());
            }
        });
    }

    public CommentEntity getCommentById(String routeId){
        return _facade.getCommentById(routeId);
    }

}
