package at.fhv.hike.hibernate.broker;

import at.fhv.hike.data.Bitmask;
import at.fhv.hike.data.RouteEntity;
import at.fhv.hike.data.UserEntity;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.LinkedList;
import java.util.List;

public class RouteBroker extends BrokerBase<RouteEntity> {

    public RouteBroker(SessionFactory sessionFactory) {
        super(sessionFactory, RouteEntity.class);
    }

    @Override
    public List<RouteEntity> getAll() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM " + RouteEntity.class.getName() + " r WHERE r.active = true";
            List<RouteEntity> routes = session.createQuery(hql, RouteEntity.class).list();

            for (RouteEntity route : routes) {
                if (route != null) {
                    Hibernate.initialize(route.getCoordinates());
                    Hibernate.initialize(route.getGallery());
                }
            }

            return routes;
        }
    }
    public List<RouteEntity> getRoutesCreatedByUser(UserEntity user) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM " + RouteEntity.class.getName() + " r WHERE r.active = true AND r.author= :user";
            Query<RouteEntity> query = session.createQuery(hql, RouteEntity.class).setParameter("user", user);
            List<RouteEntity> routes = query.getResultList();

            for (RouteEntity route : routes) {
                if (route != null) {
                    Hibernate.initialize(route.getCoordinates());
                    Hibernate.initialize(route.getGallery());
                }
            }
            return routes;
        }
    }

    public List<RouteEntity> getFiltered(String routename, Integer lengthMax, Integer lengthMin, Integer durationMax, Integer durationMin, Integer altitudeMax, Integer altitudeMin,Integer power,Integer scenery, Integer experience, Integer condition, Integer selectedMonth) {

        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM RouteEntity AS r " +
                    "WHERE (r.length >= :lengthMin) " +
                    "AND (r.active = true) " +
                    "AND (r.length <= :lengthMax) " +
                    "AND (LOWER(r.name) LIKE '%' ||:routename|| '%') " +
                    "AND (r.duration >= :durationMin) " +
                    "AND (r.duration <= :durationMax) " +
                    "AND (r.altitude >= :altitudeMin) " +
                    "AND (r.altitude <= :altitudeMax) " +
                    "AND r.attributeEntity IN (" +
                    "SELECT a.attributeId FROM AttributeEntity AS a " +
                    "WHERE (a.strength >= :power) " +
                    "AND (a.scenery >= :scenery) " +
                    "AND (a.experience >= :experience) " +
                    "AND (a.condition >= :condition))";

            Query<RouteEntity> query = session.createQuery(hql, RouteEntity.class)
                    .setParameter("routename", routename)
                    .setParameter("lengthMin", lengthMin)
                    .setParameter("lengthMax", lengthMax)
                    .setParameter("durationMin", durationMin)
                    .setParameter("durationMax", durationMax)
                    .setParameter("altitudeMin", altitudeMin)
                    .setParameter("altitudeMax", altitudeMax)
                    .setParameter("power",power)
                    .setParameter("scenery",scenery)
                    .setParameter("experience",experience)
                    .setParameter("condition",condition);

            List<RouteEntity> routes = query.getResultList();

            for (RouteEntity route : routes) {
                if (route != null) {
                    Hibernate.initialize(route.getCoordinates());
                    Hibernate.initialize(route.getGallery());
                }
            }
            List<RouteEntity>newRoutes=new LinkedList<>();
            for(RouteEntity r : routes)
            {
                if((r.getMonths()&selectedMonth)>0)
                    newRoutes.add(r);
            }

            return newRoutes;
        }
    }


    @Override
    public RouteEntity getById(String id) {
        try (Session session = sessionFactory.openSession()) {
            RouteEntity route = session.get(RouteEntity.class, id);
            if (route != null) {
                Hibernate.initialize(route.getCoordinates());
                Hibernate.initialize(route.getGallery());
            }
            return route;
        }
    }

}

