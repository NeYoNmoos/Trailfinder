package at.fhv.hike.hibernate.broker;

import at.fhv.hike.data.RouteEntity;
import at.fhv.hike.data.TimeOfYearEntity;
import org.hibernate.SessionFactory;

public class TimeOfYearBroker extends BrokerBase<TimeOfYearEntity> {

        public TimeOfYearBroker(SessionFactory sessionFactory) {
            super(sessionFactory, TimeOfYearEntity.class);
        }

    }

