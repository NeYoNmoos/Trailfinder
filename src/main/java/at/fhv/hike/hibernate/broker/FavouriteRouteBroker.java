package at.fhv.hike.hibernate.broker;

import at.fhv.hike.data.FavouriteRouteEntity;
import at.fhv.hike.data.LodgeOnRouteEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class FavouriteRouteBroker extends BrokerBase<FavouriteRouteEntity> {

    public FavouriteRouteBroker(SessionFactory sessionFactory) {
        super(sessionFactory, FavouriteRouteEntity.class);
    }

    @Override
    public void insert(FavouriteRouteEntity fre){
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.saveOrUpdate(fre.getRoute());
            session.saveOrUpdate(fre.getUser());
            session.saveOrUpdate(fre);
            tx.commit();
        } catch (Exception e) {
            // Handle exception, log or throw as appropriate
            e.printStackTrace();
        }

    }
}
