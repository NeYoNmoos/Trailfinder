package at.fhv.hike.hibernate.broker;

import at.fhv.hike.data.RouteEntity;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class RouteBroker extends BrokerBase<RouteEntity> {

    public RouteBroker(SessionFactory sessionFactory) {
        super(sessionFactory, RouteEntity.class);
    }

    @Override
    public List<RouteEntity> getAll() {
        try (Session session = sessionFactory.openSession()) {
            List<RouteEntity> routes = session.createQuery("from " + RouteEntity.class.getName(), RouteEntity.class).list();
            for(RouteEntity route : routes){
                if (route != null) {
                    Hibernate.initialize(route.getCoordinates());
                }
            }
            return routes;
        }
    }

}

