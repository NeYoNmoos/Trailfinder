package at.fhv.hike.hibernate.broker;

import at.fhv.hike.data.PoiOnRouteEntity;
import org.hibernate.SessionFactory;

public class PoiOnRouteBroker extends BrokerBase<PoiOnRouteEntity> {

    public PoiOnRouteBroker(SessionFactory sessionFactory) {super(sessionFactory, PoiOnRouteEntity.class); }

}