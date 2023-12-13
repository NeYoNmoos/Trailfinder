package at.fhv.hike;

import at.fhv.hike.controllers.CookieController;
import at.fhv.hike.controllers.RouteController;
import at.fhv.hike.controllers.UserController;
import at.fhv.hike.data.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.io.InputStream;
import java.util.*;

@MultipartConfig
@WebServlet(name = "RouteCreateServlet", urlPatterns = {"/route-create"})
public class RouteCreateServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // get all huetten
        RouteController roco = new RouteController(getServletContext());
        List<LodgeEntity> huetten = roco.getAllHuetten();
        request.setAttribute("allHuetten", huetten);

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


            List<GalleryEntity> gallery = route.getGallery();
            List<String> base64Gallery = new LinkedList<>();
            int i = 0;
            for (GalleryEntity imageEntity : gallery) {
                i++;
                byte[] imageBytes = imageEntity.getPicture();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                base64Gallery.add(base64Image);
            }
            request.setAttribute("images", base64Gallery);
            request.setAttribute("route", route);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/create_route/create_route.jsp");
            dispatcher.forward(request, response);
        }
        else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/create_route/create_route.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String loggedInUserId= CookieController.getLogedInUserId(request.getCookies());
        ServletContext context = request.getServletContext();
        RouteEntity newRoute = new RouteEntity();

        if(loggedInUserId!=null){
            UserController uc = new UserController(context);
            UserEntity author = uc.getUserById(loggedInUserId);
            newRoute.setAuthor(author);
        }

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

        Collection<Part> parts = request.getParts();

        for (Part part : parts) {
            if (part.getName().equals("images") && part.getSize() > 0) {
                // Process the file part
                InputStream inputStream = part.getInputStream();
                byte[] imageBytes = inputStreamToByteArray(inputStream);

                // Create and populate GalleryEntity
                GalleryEntity galleryEntity = new GalleryEntity();
                galleryEntity.setPicture(imageBytes);
                // Associate with RouteEntity and other required operations

                newRoute.addGallery(galleryEntity);

                // Remember to close the inputStream
                inputStream.close();
            }
        }

        RouteController rc = new RouteController(context);


        // Point of Interest
        int i = 0;
        while (request.getParameter("poi_" + i + "_latitude") != null &&
                request.getParameter("poi_" + i + "_longitude") != null) {
            double latPoi = Double.parseDouble(request.getParameter("poi_" + i + "_latitude"));
            double lngPoi = Double.parseDouble(request.getParameter("poi_" + i + "_longitude"));
            String namePoi = request.getParameter("poi_" + i + "_name");
            String descriptionPoi = request.getParameter("poi_" + i + "_description");

            CoordinateEntity coordPoi = new CoordinateEntity();
            coordPoi.setLatitude(latPoi);
            coordPoi.setLongitude(lngPoi);

            PointOfInterestEntity poi = new PointOfInterestEntity();
            poi.setName(namePoi);
            poi.setDescription(descriptionPoi);
            poi.setCoordinateEntity(coordPoi);

            PoiOnRouteEntity poiOnRoute = new PoiOnRouteEntity();
            poiOnRoute.setRoute(newRoute);
            poiOnRoute.setPointOfInterest(poi);

            rc.createPoiOnRoute(poiOnRoute);

            i++;
        }

        // Huette
        int j = 0;
        while (request.getParameter("huette_" + j + "_latitude") != null &&
                request.getParameter("huette_" + j + "_longitude") != null) {
            double latHuette = Double.parseDouble(request.getParameter("huette_" + j + "_latitude"));
            double lngHuette = Double.parseDouble(request.getParameter("huette_" + j + "_longitude"));
            String nameHuette = request.getParameter("huette_" + j + "_name");
            String descriptionHuette = request.getParameter("huette_" + j + "_description");

            CoordinateEntity coordHuette = new CoordinateEntity();
            coordHuette.setLatitude(latHuette);
            coordHuette.setLongitude(lngHuette);

            LodgeEntity huette = new LodgeEntity();
            huette.setName(nameHuette);
            huette.setDescription(descriptionHuette);
            huette.setCoordinateEntity(coordHuette);

            LodgeOnRouteEntity huetteOnRoute = new LodgeOnRouteEntity();
            huetteOnRoute.setRoute(newRoute);
            huetteOnRoute.setLodge(huette);

            rc.createHuetteOnRoute(huetteOnRoute);

            j++;
        }

        // Existing Huetten through multiselect
        String[] existingHuetten = request.getParameterValues("existingHuetten");
        if (existingHuetten != null) {

            int l = 0;
            while (l < existingHuetten.length) {
                if(existingHuetten[l] != null) {
                    LodgeEntity existingHuette = rc.getHuetteById(existingHuetten[l]);
                    LodgeOnRouteEntity existingHuetteOnRoute = new LodgeOnRouteEntity();
                    existingHuetteOnRoute.setRoute(newRoute);
                    existingHuetteOnRoute.setLodge(existingHuette);
                    rc.createHuetteOnRoute(existingHuetteOnRoute);
                }
                l++;
            }
        }

        if((j == 0) && (i == 0)){
            rc.createOrUpdateRoute(newRoute);
        }



        request.getRequestDispatcher("/create_route/create_confirmation.jsp").forward(request, response);
    }

    private byte[] inputStreamToByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[16384]; // Adjust if necessary

        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }

        buffer.flush();
        return buffer.toByteArray();
    }
}
