package at.fhv.hike;

import at.fhv.hike.controllers.CookieController;
import at.fhv.hike.controllers.RouteController;
import at.fhv.hike.controllers.UserController;
import at.fhv.hike.data.LodgeEntity;
import at.fhv.hike.data.PointOfInterestEntity;
import at.fhv.hike.data.RouteEntity;
import at.fhv.hike.data.UserEntity;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AddFavoriteServlet", urlPatterns = {"/add_favorite"})
public class AddFavoriteServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userId = CookieController.getLogedInUserId(request.getCookies());
        String routeId = request.getParameter("routeId");

        System.out.println("TRYING to Save to favorite");
        System.out.println(userId);
        System.out.println(routeId);

        ServletContext context = request.getServletContext();
        RouteController rc = new RouteController(context);
        UserController uc = new UserController(context);

        RouteEntity route = rc.getRouteById(routeId);
        UserEntity user = uc.getUserById(userId);


        System.out.println("route id that is saved in user");

        boolean isFavorite = uc.toggleFavoriteRoute(userId, routeId);

        uc.saveUser(user);

        request.setAttribute("route", route);
        request.setAttribute("isFavorite", isFavorite);

        System.out.println("should be saved here!");

        for(RouteEntity r: uc.getUserById(userId).getFavorite_routes()){
            System.out.println(r.getRouteId());
            System.out.println(r.getName());
        }

        // Huetten
        List<LodgeEntity> huetten = new ArrayList<>();
        huetten = rc.getAllHuettenForRoute(routeId);

        request.setAttribute("huetten", huetten);

        // Pois
        List<PointOfInterestEntity> pois = new ArrayList<>();
        pois = rc.getAllPoisForRoute(routeId);

        request.setAttribute("pois", pois);

        String loggedInUserId= CookieController.getLogedInUserId(request.getCookies());

        if(loggedInUserId!=null){
            UserEntity loggedInUser = uc.getUserByIdSimple(loggedInUserId);

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
