package at.fhv.hike;

import at.fhv.hike.controllers.CommentController;
import at.fhv.hike.controllers.CookieController;
import at.fhv.hike.controllers.RouteController;
import at.fhv.hike.controllers.UserController;
import at.fhv.hike.data.AttributeEntity;
import at.fhv.hike.data.CommentEntity;
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
@WebServlet(name = "CommentServlet", urlPatterns = {"/comment-servlet"})
public class CommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String routeId = request.getParameter("routeId");

        System.out.println("ROUTE ID "+routeId);
        ServletContext context = request.getServletContext();
        RouteController rc = new RouteController(context);
        RouteEntity route = rc.getByIdSimple(routeId);
        CommentEntity comment=new CommentEntity();
        comment.setComment(request.getParameter("comment"));
        comment.setRoute(route);
        comment.setActive(true);
        AttributeEntity attribute=new AttributeEntity();
        attribute.setCondition(Integer.parseInt(request.getParameter("power")));
        attribute.setExperience(Integer.parseInt(request.getParameter("experience")));
        attribute.setScenery(Integer.parseInt(request.getParameter("scenery")));
        attribute.setStrength(Integer.parseInt(request.getParameter("condition")));
        comment.setAttributes(attribute);
        UserController uc=new UserController(context);
        UserEntity user=uc.getUserByIdSimple(CookieController.getLogedInUserId(request.getCookies()));
        comment.setAuthor(user);
        CommentController cc = new CommentController(context);
        cc.createOrUpdateComment(comment);
        response.sendRedirect(request.getContextPath() + "/route-detail?routeId=" + routeId);
    }
}
