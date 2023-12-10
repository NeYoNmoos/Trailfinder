package at.fhv.hike;

import at.fhv.hike.controllers.CookieController;
import at.fhv.hike.controllers.RouteController;
import at.fhv.hike.controllers.UserController;
import at.fhv.hike.data.GalleryEntity;
import at.fhv.hike.data.RouteEntity;
import at.fhv.hike.data.UserEntity;
import at.fhv.hike.hibernate.facade.TrailfinderDatabaseFacade;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Hibernate;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "RouteDetailServlet", urlPatterns = {"/route-detail"})
public class RouteDetailServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String routeId = request.getParameter("routeId");

        ServletContext context = request.getServletContext();
        RouteController rc = new RouteController(context);
        RouteEntity route = rc.getRouteById(routeId);

        List<GalleryEntity> gallery = route.getGallery();

        request.setAttribute("gallery", gallery);

        request.setAttribute("route", route);

        String loggedInUserId= CookieController.getLogedInUserId(request.getCookies());

        if(loggedInUserId!=null){
            UserController uc = new UserController(context);
            UserEntity loggedInUser = uc.getUserById(loggedInUserId);

            Boolean isAdmin=loggedInUser.getUserType();
            if(isAdmin==null)
                isAdmin=false;
            UserEntity author=route.getAuthor();
            Boolean isRoutCreator;
            if(author==null)
                 isRoutCreator=false;

            else
                isRoutCreator=(author.getUserId().toString().equals(loggedInUserId));


            if(isAdmin || isRoutCreator){
                    request.setAttribute("canEdit", true);
                }
                else {
                    request.setAttribute("canEdit", false);
                }
        }
        else
            request.setAttribute("canEdit", false);


        RequestDispatcher dispatcher = request.getRequestDispatcher("/route_detail/route_detail.jsp");
        dispatcher.forward(request, response);
    }
}