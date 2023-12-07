package at.fhv.hike.controllers;

import at.fhv.hike.data.RouteEntity;
import at.fhv.hike.data.UserEntity;
import at.fhv.hike.hibernate.facade.TrailfinderDatabaseFacade;
import jakarta.servlet.ServletContext;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class UserController {
    private TrailfinderDatabaseFacade _facade;

    public UserController(ServletContext context) {
        _facade = new TrailfinderDatabaseFacade(context);
    }

    public List<UserEntity> getAllUsers() {
        return _facade.getAllUsers();
    }
    public Boolean checkPassword(String email, String password){
        return _facade.authenticateUser(email, password);
    }
    public Boolean registerUser(String username, String email, String password){
        if(!_facade.userAlreadyExists(email))
        {
            UserEntity newUser=new UserEntity();
            newUser.setUsername(username);
            newUser.setEmail(email);
            newUser.setPassword( BCrypt.hashpw(password, BCrypt.gensalt()));
            _facade.save(newUser);
            return true;
        }
        return false;
    }
}
