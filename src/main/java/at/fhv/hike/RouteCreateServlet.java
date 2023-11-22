package at.fhv.hike;

import at.fhv.hike.controllers.RouteController;
import at.fhv.hike.data.AttributeEntity;
import at.fhv.hike.data.Bitmask;
import at.fhv.hike.data.CoordinateEntity;
import at.fhv.hike.data.RouteEntity;
//import at.fhv.hike.data.TimeOfYearEntity;
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
        if (routeId != null) {
            ServletContext context = request.getServletContext();
            RouteController rc = new RouteController(context);
            RouteEntity route = rc.getRouteById(routeId);
            request.setAttribute("routeId", Integer.parseInt(routeId));
            request.setAttribute("name", route.getName());
            request.setAttribute("location", route.getLocation());
            request.setAttribute("altitude", route.getAltitude());
            request.setAttribute("length", route.getLength());
            request.setAttribute("duration", route.getDuration());
            request.setAttribute("description", route.getDescription());
            request.setAttribute("months", route.getMonths());
            request.setAttribute("active", route.getActive());

            request.setAttribute("power", route.getAttributeEntity().getStrength());
            request.setAttribute("scenery", route.getAttributeEntity().getScenery());
            request.setAttribute("condition", route.getAttributeEntity().getCondition());
            request.setAttribute("experience", route.getAttributeEntity().getExperience());

            request.setAttribute("january", (route.getMonths() & Bitmask.Month_1_Jan) != 0);
            request.setAttribute("february", (route.getMonths() & Bitmask.Month_2_Feb) != 0);
            request.setAttribute("march", (route.getMonths() & Bitmask.Month_3_Mar) != 0);
            request.setAttribute("april", (route.getMonths() & Bitmask.Month_4_Apr) != 0);
            request.setAttribute("may", (route.getMonths() & Bitmask.Month_5_May) != 0);
            request.setAttribute("june", (route.getMonths() & Bitmask.Month_6_Jun) != 0);
            request.setAttribute("july", (route.getMonths() & Bitmask.Month_7_Jul) != 0);
            request.setAttribute("august", (route.getMonths() & Bitmask.Month_8_Aug) != 0);
            request.setAttribute("september", (route.getMonths() & Bitmask.Month_9_Sep) != 0);
            request.setAttribute("october", (route.getMonths() & Bitmask.Month_10_Oct) != 0);
            request.setAttribute("november", (route.getMonths() & Bitmask.Month_11_Nov) != 0);
            request.setAttribute("december", (route.getMonths() & Bitmask.Month_12_Dec) != 0);


            request.setAttribute("startLongitude", route.getCoordinates().get(0).getLongitude());
            request.setAttribute("startLatitude", route.getCoordinates().get(0).getLatitude());
            request.setAttribute("endLongitude", route.getCoordinates().get(1).getLongitude());
            request.setAttribute("endLatitude", route.getCoordinates().get(1).getLatitude());

            RequestDispatcher dispatcher = request.getRequestDispatcher("/create_route/create_route.jsp");
            dispatcher.forward(request, response);
        }
        // else {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/create_route/create_route.jsp");
        dispatcher.forward(request, response);
        //}
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


//        TimeOfYearEntity newMonths = new TimeOfYearEntity();
//        newMonths.setMonthId(UUID.randomUUID().toString());
/*        newMonths.setJanuary(false);
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
        newMonths.setDecember(false);*/


        Bitmask bm = new Bitmask();


        if (months != null) {
            for (int i = 0; i < months.length; i++) {
                System.out.println("FOR LOOP");
                switch (months[i]) {
                    case "january":
                        bm.addJan();
                        break;
                    case "february":
                        bm.addFeb();
                        break;
                    case "march":
                        bm.addMar();
                        break;
                    case "april":
                        bm.addApr();
                        break;
                    case "may":
                        bm.addMay();
                        break;
                    case "june":
                        bm.addJun();
                        break;
                    case "july":
                        bm.addJul();
                        break;
                    case "august":
                        bm.addAug();
                        break;
                    case "september":
                        bm.addSep();
                        break;
                    case "october":
                        bm.addOct();
                        break;
                    case "november":
                        bm.addNov();
                        break;
                    case "december":
                        bm.addDec();
                        break;
                }
            }
        }
        AttributeEntity newAttributes = new AttributeEntity();
//        newAttributes.setAttributeId(UUID.randomUUID().toString());
        newAttributes.setStrength(power);
        newAttributes.setScenery(scenery);
        newAttributes.setExperience(experience);
        newAttributes.setCondition(condition);

        RouteEntity newRoute = new RouteEntity();
        System.out.println("works");
        //seting ID
        //looking for id
        String routeId = request.getParameter("routeId");

        if(!routeId.equals("null")) {
            newRoute.setRouteId(Integer.parseInt(routeId));
        }

        /* int check = 1;
        System.out.println(routeId);
        if(routeId == null || routeId.equals("null") || routeId.equals(null)){
            check = 0;
            System.out.println("Check is 0");
        }
        if (check != 0) {
            System.out.println("here?");
            newRoute.setRouteId(Integer.parseInt(routeId));
            //newRoute.setRouteId(UUID.randomUUID().toString());
        }
     /*   }else
            newRoute.setRouteId(Integer.parseInt(routeId));*/

//        newRoute.setRouteId(UUID.randomUUID().toString());

            newRoute.setName(name);
            newRoute.setLength(length);
            newRoute.setAltitude(altitude);
            newRoute.setLocation(location);
            newRoute.setDuration(duration);
            newRoute.setDescription(description);
            newRoute.setAttributeEntity(newAttributes);
            newRoute.setMonths(bm.returnBitmask());
            newRoute.setActive(true);
//        newRoute.setTimeOfYearEntity(newMonths);

            CoordinateEntity startCoord = new CoordinateEntity();
//        startCoord.setCoordinateId(UUID.randomUUID().toString());
            startCoord.setSequence(0);
            startCoord.setLatitude(Double.parseDouble(request.getParameter("startLatitude")));
            startCoord.setLongitude(Double.parseDouble(request.getParameter("startLongitude")));

            CoordinateEntity endCoord = new CoordinateEntity();
//        endCoord.setCoordinateId(UUID.randomUUID().toString());
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
