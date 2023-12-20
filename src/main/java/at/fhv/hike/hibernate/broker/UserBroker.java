package at.fhv.hike.hibernate.broker;

import at.fhv.hike.data.RouteEntity;
import at.fhv.hike.data.UserEntity;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class UserBroker extends BrokerBase<UserEntity> {

    public UserBroker(SessionFactory sessionFactory) {
        super(sessionFactory, UserEntity.class);
    }

    public UserEntity getUserByEmail(String email){
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM UserEntity AS u " +
                    "WHERE (u.email = :email) ";

            Query<UserEntity> query = session.createQuery(hql, UserEntity.class).setParameter("email", email);

            List<UserEntity> users = query.getResultList();
            if(users.isEmpty())
                return null;
            return users.get(0);
        }
    }

    public Boolean userAlreadyExists(String email) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM UserEntity AS u " +
                    "WHERE (u.email = :email) ";
            Query<UserEntity> query = session.createQuery(hql, UserEntity.class).setParameter("email", email);

            List<UserEntity> users = query.getResultList();

            return !users.isEmpty();
        }
    }

    @Override
    public UserEntity getById(String id) {
        try (Session session = sessionFactory.openSession()) {
            UserEntity user = session.get(UserEntity.class, id);
            if (user != null) {
                Hibernate.initialize(user.getRoutes());
                Hibernate.initialize(user.getFavorite_routes());
                for (RouteEntity route : user.getRoutes()) {
                    Hibernate.initialize(route.getCoordinates());
                    Hibernate.initialize(route.getGallery());
                }
                for (RouteEntity route : user.getFavorite_routes()) {
                    Hibernate.initialize(route.getCoordinates());
                    Hibernate.initialize(route.getGallery());
                }
            }
            return user;
        }
    }
    public UserEntity getByIdSimple(String id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(UserEntity.class, id);
        }
    }
}