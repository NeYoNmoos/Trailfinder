package at.fhv.hike.hibernate.broker;

import at.fhv.hike.data.RouteEntity;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

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

    public List<RouteEntity> getFiltered(Integer lengthMax, Integer lengthMin, Integer durationMax, Integer durationMin, Integer altitudeMax, Integer altitudeMin) {
        if(lengthMin==null)
            lengthMin=0;
        if(altitudeMin==null)
            lengthMin=0;
        if(durationMin==null)
            lengthMin=0;
        if(lengthMax==null)
            lengthMax=100000;
        if(durationMax==null)
            lengthMax=100000;
        if(altitudeMax==null)
            lengthMax=100000;

        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM RouteEntity AS r " +
                    "WHERE (r.length >= :lengthMin) " +
                    "AND (r.length <= :lengthMax) " +
                    "AND (r.duration >= :durationMin) " +
                    "AND (r.duration <= :durationMax) " +
                    "AND (r.altitude >= :altitudeMin) " +
                    "AND (r.altitude <= :altitudeMax)";

            Query<RouteEntity> query = session.createQuery(hql, RouteEntity.class)
                    .setParameter("lengthMin", lengthMin)
                    .setParameter("lengthMax", lengthMax)
                    .setParameter("durationMin", durationMin)
                    .setParameter("durationMax", durationMax)
                    .setParameter("altitudeMin", altitudeMin)
                    .setParameter("altitudeMax", altitudeMax);

            List<RouteEntity> routes = query.getResultList();

            for (RouteEntity route : routes) {
                if (route != null) {
                    Hibernate.initialize(route.getCoordinates());
                }
            }

            return routes;
        }
    }

    @Override
    public RouteEntity getById(String id) {
        try (Session session = sessionFactory.openSession()) {
            RouteEntity route = session.get(RouteEntity.class, id);
            if (route != null) {
                Hibernate.initialize(route.getCoordinates());
            }
            return route;
        }
    }

}

