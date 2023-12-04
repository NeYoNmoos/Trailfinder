package at.fhv.hike;

import at.fhv.hike.controllers.RouteController;
import at.fhv.hike.data.RouteEntity;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
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
        boolean isRegistrationSuccessful =false;// registerUser(username, email, password);

        if (isRegistrationSuccessful) {
            // Redirect to a success page or perform further actions
            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            // Set an error attribute in the request
            request.setAttribute("registerError", "Email already used");
            // Forward the request back to the login page
            request.getRequestDispatcher("/register_page/register.jsp").forward(request, response);
        }
    }
}
