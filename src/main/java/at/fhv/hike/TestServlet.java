package at.fhv.hike;

import at.fhv.hike.controllers.RouteController;
import at.fhv.hike.data.*;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet(name = "TestServlet", urlPatterns = {"/route-test"})

public class TestServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // create mockdata
        /*
        AttributeEntity newAttributes = new AttributeEntity();
        newAttributes.setStrength(2);
        newAttributes.setScenery(3);
        newAttributes.setExperience(4);
        newAttributes.setCondition(5);

        RouteEntity newRoute = new RouteEntity();
        newRoute.setName("anothertestroute");
        newRoute.setLength(23.54);
        newRoute.setAltitude(13.2);
        newRoute.setLocation("Test location");
        newRoute.setDuration(2.35);
        newRoute.setDescription("Test working");
        newRoute.setAttributeEntity(newAttributes);
        newRoute.setMonths(64);
        newRoute.setRouteId(9);

        CoordinateEntity startCoord = new CoordinateEntity();
        startCoord.setSequence(0);
        startCoord.setLatitude(10.342312);
        startCoord.setLongitude(18.23235);

        CoordinateEntity endCoord = new CoordinateEntity();
        endCoord.setSequence(0);
        endCoord.setLatitude(11.342312);
        endCoord.setLongitude(19.23235);

        newRoute.addCoordinate(startCoord);
        newRoute.addCoordinate(endCoord);

        ServletContext context = request.getServletContext();
        RouteController rc = new RouteController(context);
        rc.createRoute(newRoute);
*/
        CommentEntity ce = new CommentEntity();
        ce.setComment("TestingComment");

        ServletContext context = request.getServletContext();
        RouteController rc = new RouteController(context);
        System.out.println("dre saved");

        request.getRequestDispatcher("/create_route/create_confirmation.jsp").forward(request, response);
    }

}
