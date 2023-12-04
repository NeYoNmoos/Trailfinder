package at.fhv.hike.hibernate.broker;

import at.fhv.hike.data.LodgeEntity;
import org.hibernate.SessionFactory;

public class LodgeBroker extends BrokerBase<LodgeEntity> {

    public LodgeBroker(SessionFactory sessionFactory) {
        super(sessionFactory, LodgeEntity.class);
    }

}