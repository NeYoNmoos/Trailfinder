package at.fhv.hike.hibernate.broker;

import at.fhv.hike.data.LodgeOnRouteEntity;
import org.hibernate.SessionFactory;

public class LodgeOnRouteBroker extends BrokerBase<LodgeOnRouteEntity> {

    public LodgeOnRouteBroker(SessionFactory sessionFactory) {
        super(sessionFactory, LodgeOnRouteEntity.class);
    }

}