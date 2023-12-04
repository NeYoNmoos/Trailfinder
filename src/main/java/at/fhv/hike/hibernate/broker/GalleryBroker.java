package at.fhv.hike.hibernate.broker;

import at.fhv.hike.data.GalleryEntity;
import org.hibernate.SessionFactory;

public class GalleryBroker extends BrokerBase<GalleryEntity> {

    public GalleryBroker(SessionFactory sessionFactory) {
        super(sessionFactory, GalleryEntity.class);
    }

}