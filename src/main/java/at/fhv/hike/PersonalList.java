package at.fhv.hike;

import at.fhv.hike.controllers.CookieController;
import at.fhv.hike.controllers.RouteController;
import at.fhv.hike.controllers.UserController;
import at.fhv.hike.data.*;
import at.fhv.hike.data.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.io.InputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@MultipartConfig
@WebServlet(name = "RouteCreateServlet", urlPatterns = {"/personal-list"})
public class PersonalList extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String listId = request.getParameter("list");

        RouteController rc = new RouteController(request.getServletContext());

        Cookie[] keks = request.getCookies();
        int i =0;
        String userId = "";
        while (i < keks.length) {
            if (keks[i].getName().equals("userId")) {
                userId = keks[i].getValue();
            }
            i++;
        }

        List<RouteEntity> list = new ArrayList<>();

        // Done Routes
        if (listId.equals("1")) {
            list = rc.getDoneRoutes(userId);
        }

        // Favourite Routes
        if (listId.equals("2")) {
            //list = rc.getFavouriteRoutes(userId);
        }

        // Todo Routes
        if (listId.equals("3")) {
            //list = rc.getTodoRoutes(userId);
        }

        request.setAttribute("list", list);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/personal_lists_page/personal_list.jsp");
        dispatcher.forward(request, response);

    }
}
