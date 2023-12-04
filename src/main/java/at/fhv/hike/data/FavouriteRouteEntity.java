package at.fhv.hike.data;

import jakarta.persistence.*;

@Entity
@Table(name = "favourite_route", schema = "trailfinder_dev", catalog = "ftb_inv_2023_vz_3_a")
public class FavouriteRouteEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "route_id", referencedColumnName = "route_id")
    private RouteEntity routeEntity;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserEntity userEntity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RouteEntity getRoute() {
        return routeEntity;
    }

    public void setRouteId(RouteEntity routeEntity) {
        this.routeEntity = routeEntity;
    }

    public UserEntity getUser() {
        return userEntity;
    }

    public void setUserId(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FavouriteRouteEntity that = (FavouriteRouteEntity) o;

        if (id != that.id) return false;
        if (routeEntity != null ? !routeEntity.equals(that.routeEntity) : that.routeEntity != null) return false;
        if (userEntity != null ? !userEntity.equals(that.userEntity) : that.userEntity != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (routeEntity != null ? routeEntity.hashCode() : 0);
        result = 31 * result + (userEntity != null ? userEntity.hashCode() : 0);
        return result;
    }
}
