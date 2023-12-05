package at.fhv.hike.hibernate.broker;

import at.fhv.hike.data.CommentEntity;
import org.hibernate.SessionFactory;

public class CommentBroker extends BrokerBase<CommentEntity> {

    public CommentBroker(SessionFactory sessionFactory) {
        super(sessionFactory, CommentEntity.class);
    }

}