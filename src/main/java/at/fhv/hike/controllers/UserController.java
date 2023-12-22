package at.fhv.hike.controllers;

import at.fhv.hike.data.RouteEntity;
import at.fhv.hike.data.UserEntity;
import at.fhv.hike.hibernate.facade.TrailfinderDatabaseFacade;
import jakarta.servlet.ServletContext;
import org.mindrot.jbcrypt.BCrypt;
import org.openqa.selenium.remote.http.Route;

import java.util.List;

public class UserController {
    private TrailfinderDatabaseFacade _facade;
    private ServletContext _context;

    public UserController(ServletContext context) {
        _context = context;
        _facade = new TrailfinderDatabaseFacade(context);
    }

    public List<UserEntity> getAllUsers() {
        return _facade.getAllUsers();
    }
    public Integer checkPassword(String email, String password){
        return _facade.authenticateUser(email, password);
    }
    public UserEntity getUserById(String id){
        return _facade.getUserById(id);
    }
    public UserEntity getUserByIdSimple(String id){
        return _facade.getUserByIdSimple(id);
    }
    public Integer registerUser(String username, String email, String password){
        if(!_facade.userAlreadyExists(email))
        {
            UserEntity newUser=new UserEntity();
            newUser.setUsername(username);
            newUser.setEmail(email);
            newUser.setPassword( BCrypt.hashpw(password, BCrypt.gensalt()));
            _facade.save(newUser);
            Integer userId=_facade.authenticateUser(email,password);
            return userId;
        }
        return null;
    }
    public void saveUser(UserEntity user){
        _facade.save(user);
    }

    public void deleteUser(UserEntity user){
        _facade.delete(user);
    }

    public boolean toggleFavoriteRoute(String userId, String routeId) {
        UserEntity user = getUserById(userId);
        RouteEntity route = new RouteController(_context).getRouteById(routeId);
        List<RouteEntity> favorites = user.getFavorite_routes();

        for (RouteEntity favorite : favorites) {
            if (favorite.getRouteId().equals(route.getRouteId())) {
                favorites.remove(favorite);
                System.out.println("Route removed from favorites");
                saveUser(user);
                return false;
            }
        }

        System.out.println("Route added to favorites");
        favorites.add(route);
        saveUser(user);
        return true;
    }

    public boolean isFavoriteRoute(String userId, String routeId) {
        UserEntity user = getUserById(userId);
        RouteEntity route = new RouteController(_context).getRouteById(routeId);
        if (user != null) {
            List<RouteEntity> favorites = user.getFavorite_routes();
            for (RouteEntity favorite : favorites) {
                if (favorite.getRouteId().equals(route.getRouteId())) {
                    return true;
                }
            }
        }
        return false;
    }


    //Done route toggle

    public boolean toggleDoneRoute(String userId, String routeId) {
        UserEntity user = getUserById(userId);
        RouteEntity route = new RouteController(_context).getRouteById(routeId);
        List<RouteEntity> done = user.getDone_routes();

        for (RouteEntity _done : done) {
            if (_done.getRouteId().equals(route.getRouteId())) {
                done.remove(_done);
                System.out.println("Route removed from Done");
                saveUser(user);
                return false;
            }
        }

        System.out.println("Route added to Done");
        done.add(route);
        saveUser(user);
        return true;
    }


    // Done route check

    public boolean isDoneRoute(String userId, String routeId) {
        UserEntity user = getUserById(userId);
        RouteEntity route = new RouteController(_context).getRouteById(routeId);
        if (user != null) {
            List<RouteEntity> done = user.getDone_routes();
            for (RouteEntity _done : done) {
                if (_done.getRouteId().equals(route.getRouteId())) {
                    return true;
                }
            }
        }
        return false;
    }


}
