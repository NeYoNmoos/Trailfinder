package at.fhv.hike.hibernate.broker;

import at.fhv.hike.data.LodgeEntity;
import at.fhv.hike.data.RouteEntity;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LodgeBroker extends BrokerBase<LodgeEntity> {

    public LodgeBroker(SessionFactory sessionFactory) {
        super(sessionFactory, LodgeEntity.class);
    }

    public List<Integer> getHuetteIdByName(String huetteName) {
        List<Integer> huettenId = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM LodgeEntity AS l " +
                    "WHERE ((LOWER(l.name)) LIKE '%' ||:huetteName|| '%')";

            Query<LodgeEntity> query = session.createQuery(hql, LodgeEntity.class)
                    .setParameter("name", huetteName);

            List<LodgeEntity> huetten = query.getResultList();

            for(LodgeEntity huette : huetten) {
                huettenId.add(huette.getLodgeId());
            }
        }

            return huettenId;
    }

}