package at.fhv.hike.hibernate.broker;

import at.fhv.hike.data.CoordinateEntity;
//import at.fhv.hike.data.TimeOfYearEntity;
import org.hibernate.SessionFactory;

public class CoordinateBroker extends BrokerBase<CoordinateEntity> {

        public CoordinateBroker(SessionFactory sessionFactory) {
            super(sessionFactory, CoordinateEntity.class);
        }

    }

