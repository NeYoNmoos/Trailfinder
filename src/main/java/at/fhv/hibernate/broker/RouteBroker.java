package at.fhv.hibernate.broker;

import at.fhv.data.RouteEntity;
import data.Route;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

    public class RouteBroker extends BrokerBase<RouteEntity> {

        public RouteBroker(SessionFactory sessionFactory) {
            super(sessionFactory, RouteEntity.class);
        }

    }

