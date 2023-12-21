package at.fhv.hike.hibernate.broker;

import at.fhv.hike.data.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class LodgeOnRouteBroker extends BrokerBase<LodgeOnRouteEntity> {

    public LodgeOnRouteBroker(SessionFactory sessionFactory) {
        super(sessionFactory, LodgeOnRouteEntity.class);
    }

    @Override
    public void insert(LodgeOnRouteEntity lore){
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.saveOrUpdate(lore.getRoute());
            session.saveOrUpdate(lore.getLodge());
            session.saveOrUpdate(lore);
            tx.commit();
        } catch (Exception e) {
            // Handle exception, log or throw as appropriate
            e.printStackTrace();
        }

    }

    public List<LodgeOnRouteEntity> getHuettenOnRouteByRouteId(String routeId) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM LodgeOnRouteEntity AS l " +
                    "WHERE l.routeEntity.routeId = :routeId";

            Query<LodgeOnRouteEntity> query = session.createQuery(hql, LodgeOnRouteEntity.class)
                    .setParameter("routeId", routeId);

            List<LodgeOnRouteEntity> lodgeOnRouteEntities = query.getResultList();
            return lodgeOnRouteEntities;
        }
    }

    public List<Integer> getRouteIdByHuetteId(List<Integer> huettenIds) {
        List<Integer> routeIds = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            for(Integer huetteId: huettenIds) {
                String hql = "FROM LodgeOnRouteEntity AS l " +
                        "WHERE (l.lodgeEntity.id = :huetteId)";

                Query<LodgeOnRouteEntity> query = session.createQuery(hql, LodgeOnRouteEntity.class)
                        .setParameter("lodge_id", huetteId);

                List<LodgeOnRouteEntity> huettenOnRoutes = query.getResultList();

                for(LodgeOnRouteEntity huetteOnRoute : huettenOnRoutes) {
                    routeIds.add(huetteOnRoute.getRoute().getRouteId());
                }
            }
        }
        return routeIds;
    }
}