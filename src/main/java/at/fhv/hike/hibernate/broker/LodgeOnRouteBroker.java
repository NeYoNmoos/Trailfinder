package at.fhv.hike.hibernate.broker;

import at.fhv.hike.data.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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
}