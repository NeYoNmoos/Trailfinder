package at.fhv.hike.hibernate.broker;

import at.fhv.hike.data.PoiOnRouteEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class PoiOnRouteBroker extends BrokerBase<PoiOnRouteEntity> {

    public PoiOnRouteBroker(SessionFactory sessionFactory) {super(sessionFactory, PoiOnRouteEntity.class); }

    @Override
    public void insert(PoiOnRouteEntity pore){
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.saveOrUpdate(pore.getRoute());
            session.saveOrUpdate(pore.getPointOfInterest());
            session.saveOrUpdate(pore);
            tx.commit();
        } catch (Exception e) {
            // Handle exception, log or throw as appropriate
            e.printStackTrace();
        }

    }
}