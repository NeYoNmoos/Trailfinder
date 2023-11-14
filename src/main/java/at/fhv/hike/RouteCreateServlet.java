package at.fhv.hike;

import at.fhv.hike.controllers.RouteController;
import at.fhv.hike.data.AttributeEntity;
import at.fhv.hike.data.CoordinateEntity;
import at.fhv.hike.data.RouteEntity;
import at.fhv.hike.data.TimeOfYearEntity;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "RouteCreateServlet", urlPatterns = {"/route-create"})
public class RouteCreateServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String routeId = request.getParameter("routeId");
        if(routeId != null) {
            ServletContext context = request.getServletContext();
            RouteController rc = new RouteController(context);
            RouteEntity route = rc.getRouteById(routeId);
            //String name=route.getName();
            request.setAttribute("name", route.getName());
            request.setAttribute("location", route.getLocation());
            request.setAttribute("altitude", route.getAltitude());
            request.setAttribute("length", route.getLength());
            request.setAttribute("duration", route.getDuration());
            request.setAttribute("description", route.getDescription());

            request.setAttribute("power",route.getAttributeEntity().getStrength());
            request.setAttribute("scenery",route.getAttributeEntity().getScenery());
            request.setAttribute("condition",route.getAttributeEntity().getCondition());
            request.setAttribute("experience",route.getAttributeEntity().getExperience());

            request.setAttribute("january", route.getTimeOfYearEntity().getJanuary());
            request.setAttribute("february", route.getTimeOfYearEntity().getFebruary());
            request.setAttribute("march", route.getTimeOfYearEntity().getMarch());
            request.setAttribute("april", route.getTimeOfYearEntity().getApril());
            request.setAttribute("may", route.getTimeOfYearEntity().getMay());
            request.setAttribute("june", route.getTimeOfYearEntity().getJune());
            request.setAttribute("july", route.getTimeOfYearEntity().getJuly());
            request.setAttribute("august", route.getTimeOfYearEntity().getAugust());
            request.setAttribute("september", route.getTimeOfYearEntity().getSeptember());
            request.setAttribute("october", route.getTimeOfYearEntity().getOctober());
            request.setAttribute("november", route.getTimeOfYearEntity().getNovember());
            request.setAttribute("december", route.getTimeOfYearEntity().getDecember());

            request.setAttribute("startLongitude", route.getCoordinates().get(0).getLongitude());
            request.setAttribute("startLatitude", route.getCoordinates().get(0).getLatitude());
            request.setAttribute("endLongitude", route.getCoordinates().get(1).getLongitude());
            request.setAttribute("endLatitude", route.getCoordinates().get(1).getLatitude());

            RequestDispatcher dispatcher = request.getRequestDispatcher("/create_route/create_route.jsp");
            dispatcher.forward(request, response);
        }
        else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/create_route/create_route.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        double length = Double.parseDouble(request.getParameter("length"));
        double altitude = Double.parseDouble(request.getParameter("altitude"));
        String location = request.getParameter("location");
        double duration = Double.parseDouble(request.getParameter("duration"));
        String description = request.getParameter("description");

        String[] months = request.getParameterValues("months");
        Integer power = Integer.parseInt(request.getParameter("power"));
        Integer scenery = Integer.parseInt(request.getParameter("scenery"));
        Integer experience = Integer.parseInt(request.getParameter("experience"));
        Integer condition = Integer.parseInt(request.getParameter("condition"));

        TimeOfYearEntity newMonths = new TimeOfYearEntity();
        newMonths.setMonthId(UUID.randomUUID().toString());
        newMonths.setJanuary(false);
        newMonths.setFebruary(false);
        newMonths.setMarch(false);
        newMonths.setApril(false);
        newMonths.setMay(false);
        newMonths.setJune(false);
        newMonths.setJuly(false);
        newMonths.setAugust(false);
        newMonths.setSeptember(false);
        newMonths.setOctober(false);
        newMonths.setNovember(false);
        newMonths.setDecember(false);
        if(months != null){
            for (int i = 0; i < months.length; i++) {
                System.out.println("FOR LOOP");
                switch (months[i]) {
                    case "january":
                        newMonths.setJanuary(true);
                        break;
                    case "february":
                        newMonths.setFebruary(true);
                        break;
                    case "march":
                        newMonths.setMarch(true);
                        break;
                    case "april":
                        newMonths.setApril(true);
                        break;
                    case "may":
                        newMonths.setMay(true);
                        break;
                    case "june":
                        newMonths.setJune(true);
                        break;
                    case "july":
                        newMonths.setJuly(true);
                        break;
                    case "august":
                        newMonths.setAugust(true);
                        break;
                    case "september":
                        newMonths.setSeptember(true);
                        break;
                    case "october":
                        newMonths.setOctober(true);
                        break;
                    case "november":
                        newMonths.setNovember(true);
                        break;
                    case "december":
                        newMonths.setDecember(true);
                        break;
                }
            }
        }

        AttributeEntity newAttributes = new AttributeEntity();
        newAttributes.setAttributeId(UUID.randomUUID().toString());
        newAttributes.setStrength(power);
        newAttributes.setScenery(scenery);
        newAttributes.setExperience(experience);
        newAttributes.setCondition(condition);

        RouteEntity newRoute = new RouteEntity();
        newRoute.setRouteId(UUID.randomUUID().toString());
        newRoute.setName(name);
        newRoute.setLength(length);
        newRoute.setAltitude(altitude);
        newRoute.setLocation(location);
        newRoute.setDuration(duration);
        newRoute.setDescription(description);
        newRoute.setAttributeEntity(newAttributes);
        newRoute.setTimeOfYearEntity(newMonths);

        CoordinateEntity startCoord = new CoordinateEntity();
        startCoord.setCoordinateId(UUID.randomUUID().toString());
        startCoord.setSequence(0);
        startCoord.setLatitude(Double.parseDouble(request.getParameter("startLatitude")));
        startCoord.setLongitude(Double.parseDouble(request.getParameter("startLongitude")));

        CoordinateEntity endCoord = new CoordinateEntity();
        endCoord.setCoordinateId(UUID.randomUUID().toString());
        endCoord.setSequence(1);
        endCoord.setLatitude(Double.parseDouble(request.getParameter("endLatitude")));
        endCoord.setLongitude(Double.parseDouble(request.getParameter("endLongitude")));

        newRoute.addCoordinate(startCoord);
        newRoute.addCoordinate(endCoord);

        ServletContext context = request.getServletContext();
        RouteController rc = new RouteController(context);
        rc.createRoute(newRoute);

        request.getRequestDispatcher("/create_route/create_confirmation.jsp").forward(request, response);
    }

}
