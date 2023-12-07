package at.fhv.hike.hibernate.facade;

import at.fhv.hike.data.*;
//import at.fhv.hike.data.TimeOfYearEntity;
import at.fhv.hike.hibernate.broker.*;
//import at.fhv.hike.hibernate.broker.TimeOfYearBroker;
import jakarta.servlet.ServletContext;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class TrailfinderDatabaseFacade implements TrailfinderFacade{
    private RouteBroker _routeBroker;
    private AttributeBroker _attributeBroker;
   // private TimeOfYearBroker _timeOfYearBroker;
    private CoordinateBroker _coordinateBroker;
    private CommentBroker _commentBroker;
    private DoneRouteBroker _doneRouteBroker;
    private GalleryBroker _galleryBroker;
    private LodgeBroker _lodgeBroker;
    private LodgeOnRouteBroker _lodgeOnRouteBroker;
    private PointOfInterestBroker _pointOfInterestBroker;
    private PoiOnRouteBroker _poiOnRouteBroker;
    private UserBroker _userBroker;

    public TrailfinderDatabaseFacade(ServletContext context) {
        SessionFactory sessionFactory = (SessionFactory) context.getAttribute("SessionFactory");
        this._routeBroker = new RouteBroker(sessionFactory);
        this._attributeBroker = new AttributeBroker(sessionFactory);
       // this._timeOfYearBroker = new TimeOfYearBroker(sessionFactory);
        this._coordinateBroker = new CoordinateBroker(sessionFactory);
        this._commentBroker = new CommentBroker(sessionFactory);
        this._doneRouteBroker = new DoneRouteBroker(sessionFactory);
        this._galleryBroker = new GalleryBroker(sessionFactory);
        this._lodgeBroker = new LodgeBroker(sessionFactory);
        this._lodgeOnRouteBroker = new LodgeOnRouteBroker(sessionFactory);
        this._pointOfInterestBroker = new PointOfInterestBroker(sessionFactory);
        this._poiOnRouteBroker = new PoiOnRouteBroker(sessionFactory);
        this._userBroker = new UserBroker(sessionFactory);
    }

    public TrailfinderDatabaseFacade(SessionFactory sessionFactory) {
        this._routeBroker = new RouteBroker(sessionFactory);
        this._attributeBroker = new AttributeBroker(sessionFactory);
        // this._timeOfYearBroker = new TimeOfYearBroker(sessionFactory);
        this._coordinateBroker = new CoordinateBroker(sessionFactory);
        this._commentBroker = new CommentBroker(sessionFactory);
        this._doneRouteBroker = new DoneRouteBroker(sessionFactory);
        this._galleryBroker = new GalleryBroker(sessionFactory);
        this._lodgeBroker = new LodgeBroker(sessionFactory);
        this._lodgeOnRouteBroker = new LodgeOnRouteBroker(sessionFactory);
        this._pointOfInterestBroker = new PointOfInterestBroker(sessionFactory);
        this._poiOnRouteBroker = new PoiOnRouteBroker(sessionFactory);
        this._userBroker = new UserBroker(sessionFactory);
    }

    @Override
    public void save(Object value) {
        if (value instanceof RouteEntity) {
            _routeBroker.insert((RouteEntity) value);
        }
        else if (value instanceof AttributeEntity) {
            _attributeBroker.insert((AttributeEntity) value);
        }
 /*       else if (value instanceof TimeOfYearEntity) {
            _timeOfYearBroker.insert((TimeOfYearEntity) value);
        }*/
        else if (value instanceof CoordinateEntity) {
            _coordinateBroker.insert((CoordinateEntity) value);
        }
        else if (value instanceof CommentEntity) {
            _commentBroker.insert((CommentEntity) value);
        }
        else if (value instanceof DoneRouteEntity) {
            _doneRouteBroker.insert((DoneRouteEntity) value);
        }
        else if (value instanceof GalleryEntity) {
            _galleryBroker.insert((GalleryEntity) value);
        }
        else if (value instanceof LodgeEntity) {
            _lodgeBroker.insert((LodgeEntity) value);
        }
        else if (value instanceof LodgeOnRouteEntity) {
            _lodgeOnRouteBroker.insert((LodgeOnRouteEntity) value);
        }
        else if (value instanceof PointOfInterestEntity) {
            _pointOfInterestBroker.insert((PointOfInterestEntity) value);
        }
        else if (value instanceof PoiOnRouteEntity) {
            _poiOnRouteBroker.insert((PoiOnRouteEntity) value);
        }
        else if (value instanceof UserEntity) {
            _userBroker.insert((UserEntity) value);
        }
        // other insert statements go here for other entities
    }

    @Override
    public void delete(Object value) {
        if (value instanceof RouteEntity) {
            _routeBroker.delete((RouteEntity) value);
        }
        else if (value instanceof AttributeEntity) {
            _attributeBroker.delete((AttributeEntity) value);
        }/*
        else if (value instanceof TimeOfYearEntity) {
            _timeOfYearBroker.delete((TimeOfYearEntity) value);
        }*/
        else if (value instanceof CoordinateEntity) {
            _coordinateBroker.delete((CoordinateEntity) value);
        }
        else if (value instanceof CommentEntity) {
            _commentBroker.delete((CommentEntity) value);
        }
        else if (value instanceof DoneRouteEntity) {
            _doneRouteBroker.delete((DoneRouteEntity) value);
        }
        else if (value instanceof GalleryEntity) {
            _galleryBroker.delete((GalleryEntity) value);
        }
        else if (value instanceof LodgeEntity) {
            _lodgeBroker.delete((LodgeEntity) value);
        }
        else if (value instanceof LodgeOnRouteEntity) {
            _lodgeOnRouteBroker.delete((LodgeOnRouteEntity) value);
        }
        else if (value instanceof PointOfInterestEntity) {
            _pointOfInterestBroker.delete((PointOfInterestEntity) value);
        }
        else if (value instanceof PoiOnRouteEntity) {
            _poiOnRouteBroker.delete((PoiOnRouteEntity) value);
        }
        else if (value instanceof UserEntity) {
            _userBroker.delete((UserEntity) value);
        }
        // other delete statements go here for other entities
    }

    @Override
    public List<RouteEntity> getAllRoutes() {
        return _routeBroker.getAll();
    }

    public List<RouteEntity>getFilteredRoutes(String routename, Integer lengthMax, Integer lengthMin, Integer durationMax, Integer durationMin, Integer altitudeMax, Integer altitudeMin,Integer power,Integer scenery, Integer experience, Integer condition, Integer selectedMonth)
    {
        return _routeBroker.getFiltered(routename, lengthMax,lengthMin,durationMax,durationMin,altitudeMax,altitudeMin,power,scenery,experience,condition,selectedMonth);
    }


    @Override
    public RouteEntity getRouteById(String id) {
        return _routeBroker.getById(id);
    }


    public UserEntity getUserById(String id) {
        return _userBroker.getById(id);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return _userBroker.getAll();
    }


    public Integer authenticateUser(String email, String password) {
        // Retrieve hashed password from the database
        UserEntity user= _userBroker.getUserByEmail(email);
        String storedHashedPassword =user.getPassword();

        if(storedHashedPassword==null)
            return null;
        // Check if the entered password matches the stored hash
        if (BCrypt.checkpw(password, storedHashedPassword)){
            return user.getUserId();
        }
        return null;
    }
    public Boolean userAlreadyExists(String email){

        return _userBroker.userAlreadyExists(email);
    }
}
