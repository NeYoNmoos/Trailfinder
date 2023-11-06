package at.fhv.hike.hibernate.broker;

import at.fhv.hike.data.AttributeEntity;
import at.fhv.hike.data.RouteEntity;
import org.hibernate.SessionFactory;

public class AttributeBroker extends BrokerBase<AttributeEntity> {

    public AttributeBroker(SessionFactory sessionFactory) {
        super(sessionFactory, AttributeEntity.class);
    }

}

