package at.fhv.hike;

import at.fhv.hike.controllers.RouteController;
import at.fhv.hike.data.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.openqa.selenium.remote.http.Route;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

            request.setAttribute("route", route);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/create_route/create_route.jsp");
            dispatcher.forward(request, response);
        }
        else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/create_route/create_route.jsp");
            dispatcher.forward(request, response);
        }

        // get all huetten
        RouteController roco = new RouteController(getServletContext());
        List<LodgeEntity> huetten = roco.getAllHuetten();
        request.setAttribute("allHuetten", huetten);
        //TODO: Test this and implement the other part in jsp page


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
        newAttributes.setStrength(power);
        newAttributes.setScenery(scenery);
        newAttributes.setExperience(experience);
        newAttributes.setCondition(condition);

        RouteEntity newRoute = new RouteEntity();

        String routeId = request.getParameter("routeId");

        if(!routeId.equals("null")) {
            newRoute.setRouteId(Integer.parseInt(routeId));
        }

        newRoute.setName(name);
        newRoute.setLength(length);
        newRoute.setAltitude(altitude);
        newRoute.setLocation(location);
        newRoute.setDuration(duration);
        newRoute.setDescription(description);
        newRoute.setAttributeEntity(newAttributes);
        newRoute.setMonths(bm.returnBitmask());
        newRoute.setActive(true);

        List<CoordinateEntity> coordinateEntities = new ArrayList<>();

        int index = 0;
        while (request.getParameter("coords_" + index + "_latitude") != null &&
                request.getParameter("coords_" + index + "_longitude") != null) {
            double lat = Double.parseDouble(request.getParameter("coords_" + index + "_latitude"));
            double lng = Double.parseDouble(request.getParameter("coords_" + index + "_longitude"));
            CoordinateEntity coord = new CoordinateEntity();
            coord.setLatitude(lat);
            coord.setLongitude(lng);
            coord.setSequence(index);
            coordinateEntities.add(coord);
            index++;
        }

        if(!coordinateEntities.isEmpty()){
            for(CoordinateEntity coord: coordinateEntities){
                newRoute.addCoordinate(coord);
            }
        }

        // Point of Interest
        int i = 0;
        while (request.getParameter("poi_" + i + "_latitude") != null &&
                request.getParameter("poi_" + i + "_longitude") != null) {
            System.out.println(("Vor poi"));
            double latPoi = Double.parseDouble(request.getParameter("poi_" + i + "_latitude"));
            double lngPoi = Double.parseDouble(request.getParameter("poi_" + i + "_longitude"));
            String namePoi = request.getParameter("poi_" + i + "_name");
            String descriptionPoi = request.getParameter("poi_" + i + "_description");

            System.out.println(latPoi + " " + lngPoi + " " + namePoi + " " + descriptionPoi);

            CoordinateEntity coordPoi = new CoordinateEntity();
            coordPoi.setLatitude(latPoi);
            coordPoi.setLongitude(lngPoi);

            PointOfInterestEntity poi = new PointOfInterestEntity();
            poi.setName(namePoi);
            poi.setDescription(descriptionPoi);
            poi.setCoordinateEntity(coordPoi);

            PoiOnRouteEntity poiOnRoute = new PoiOnRouteEntity();
            poiOnRoute.setRouteId(newRoute);
            poiOnRoute.setPointOfInterestId(poi);

            //TODO: maybe make a list for POIs? not sure yet

            System.out.println("poi");
            i++;
        }

        // Huette
        int j = 0;
        while (request.getParameter("huette_" + j + "_latitude") != null &&
                request.getParameter("huette_" + j + "_longitude") != null) {
            System.out.println(("Vor huette"));
            double latHuette = Double.parseDouble(request.getParameter("huette_" + j + "_latitude"));
            double lngHuette = Double.parseDouble(request.getParameter("huette_" + j + "_longitude"));
            String nameHuette = request.getParameter("huette_" + j + "_name");
            String descriptionHuette = request.getParameter("huette_" + j + "_description");

            System.out.println(latHuette + " " + lngHuette + " " + nameHuette + " " + descriptionHuette);

            CoordinateEntity coordHuette = new CoordinateEntity();
            coordHuette.setLatitude(latHuette);
            coordHuette.setLongitude(lngHuette);

            LodgeEntity huette = new LodgeEntity();
            huette.setName(nameHuette);
            huette.setDescription(descriptionHuette);
            huette.setCoordinateEntity(coordHuette);

            LodgeOnRouteEntity huetteOnRoute = new LodgeOnRouteEntity();
            huetteOnRoute.setRouteId(newRoute);
            huetteOnRoute.setLodgeId(huette);

            System.out.println("huette");
            j++;
        }

        ServletContext context = request.getServletContext();
        RouteController rc = new RouteController(context);
        rc.createRoute(newRoute);

        request.getRequestDispatcher("/create_route/create_confirmation.jsp").forward(request, response);
    }
}
