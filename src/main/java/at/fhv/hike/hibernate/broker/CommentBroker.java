package at.fhv.hike.hibernate.broker;

import at.fhv.hike.data.CommentEntity;
import at.fhv.hike.data.RouteEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CommentBroker extends BrokerBase<CommentEntity> {

    public CommentBroker(SessionFactory sessionFactory) {
        super(sessionFactory, CommentEntity.class);
    }

    public CommentEntity getById(String id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(CommentEntity.class, id);
        }
    }

}