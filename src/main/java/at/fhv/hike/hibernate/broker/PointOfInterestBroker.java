package at.fhv.hike.hibernate.broker;

import at.fhv.hike.data.PointOfInterestEntity;
import org.hibernate.SessionFactory;

public class PointOfInterestBroker extends BrokerBase<PointOfInterestEntity> {

    public PointOfInterestBroker(SessionFactory sessionFactory) {
        super(sessionFactory, PointOfInterestEntity.class);
    }

}