package at.fhv.hike.hibernate.broker;

import at.fhv.hike.data.RouteEntity;
import org.hibernate.SessionFactory;

public class RouteBroker extends BrokerBase<RouteEntity> {

        public RouteBroker(SessionFactory sessionFactory) {
            super(sessionFactory, RouteEntity.class);
        }

    }

