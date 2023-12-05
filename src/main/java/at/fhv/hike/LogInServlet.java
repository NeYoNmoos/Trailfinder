package at.fhv.hike;

import at.fhv.hike.controllers.RouteController;
import at.fhv.hike.data.RouteEntity;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "LogInServlet", urlPatterns = {"/login"})
public class LogInServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/login_page/login.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Retrieve values from the submitted form
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Perform login validation and authentication logic
        // Replace this with your actual login logic
        boolean isValidUser = true;//validateUser(email, password);

        if (isValidUser) {
            Cookie[] cookies = request.getCookies();

            if (cookies != null) {
                // Iterate over each cookie and set its maximum age to 0
                for (Cookie cookie : cookies) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
            Cookie myCookie = new Cookie("username", email);
            // Setting the maximum age of the cookie in seconds
            myCookie.setMaxAge(86400);
            myCookie.setPath("/");
            // Adding the cookie to the response
            response.addCookie(myCookie);
            System.out.println("USERNAME: "+request.getCookies()[0].getValue());
            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            // Set an error attribute in the request
            request.setAttribute("loginError", "Invalid email or password");
            // Forward the request back to the login page
            request.getRequestDispatcher("/login_page/login.jsp").forward(request, response);
        }
    }
}
