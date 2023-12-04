package at.fhv.hike.hibernate.broker;

import at.fhv.hike.data.FavouriteRouteEntity;
import org.hibernate.SessionFactory;

public class FavouriteRouteBroker extends BrokerBase<FavouriteRouteEntity> {

    public FavouriteRouteBroker(SessionFactory sessionFactory) {
        super(sessionFactory, FavouriteRouteEntity.class);
    }

}
