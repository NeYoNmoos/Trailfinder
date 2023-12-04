package at.fhv.hike.hibernate.broker;

import at.fhv.hike.data.DoneRouteEntity;
import org.hibernate.SessionFactory;

public class DoneRouteBroker extends BrokerBase<DoneRouteEntity> {

    public DoneRouteBroker(SessionFactory sessionFactory) {
        super(sessionFactory, DoneRouteEntity.class);
    }

}
