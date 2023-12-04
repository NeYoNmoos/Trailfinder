package at.fhv.hike.hibernate.broker;

import at.fhv.hike.data.UserEntity;
import org.hibernate.SessionFactory;

public class UserBroker extends BrokerBase<UserEntity> {

    public UserBroker(SessionFactory sessionFactory) {
        super(sessionFactory, UserEntity.class);
    }

}