package at.fhv.hike;

import at.fhv.hike.controllers.RouteController;
import at.fhv.hike.controllers.UserController;
import at.fhv.hike.data.RouteEntity;
import at.fhv.hike.data.UserEntity;
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

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/register_page/register.jsp");
        dispatcher.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Retrieve values from the submitted form
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Perform registration logic
        // Replace this with your actual registration logic
        UserEntity newUser=new UserEntity();
        ServletContext context = request.getServletContext();
        UserController uc = new UserController(context);
        Integer userId =uc.registerUser(username, email, password);

        if (userId!=null) {
            // Redirect to a success page or perform further actions
            Cookie[] cookies = request.getCookies();

            if (cookies != null) {
                // Iterate over each cookie and set its maximum age to 0
                for (Cookie cookie : cookies) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
            Cookie myCookie = new Cookie("userId", userId.toString());
            // Setting the maximum age of the cookie in seconds
            myCookie.setMaxAge(86400);
            myCookie.setPath("/");
            // Adding the cookie to the response
            response.addCookie(myCookie);
            System.out.println("USERNAME: "+request.getCookies()[0].getValue());
            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            // Set an error attribute in the request
            request.setAttribute("registerError", "Email already used");
            // Forward the request back to the login page
            request.getRequestDispatcher("/register_page/register.jsp").forward(request, response);
        }
    }
}
