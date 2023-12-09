package at.fhv.hike.hibernate.broker;

import at.fhv.hike.data.DoneRouteEntity;
import at.fhv.hike.data.LodgeOnRouteEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DoneRouteBroker extends BrokerBase<DoneRouteEntity> {

    public DoneRouteBroker(SessionFactory sessionFactory) {
        super(sessionFactory, DoneRouteEntity.class);
    }

    @Override
    public void insert(DoneRouteEntity dre){
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.saveOrUpdate(dre.getRoute());
            session.saveOrUpdate(dre.getUser());
            session.saveOrUpdate(dre);
            tx.commit();
        } catch (Exception e) {
            // Handle exception, log or throw as appropriate
            e.printStackTrace();
        }

    }
}
