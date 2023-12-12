package at.fhv.hike.data;

import jakarta.persistence.*;

@Entity
@Table(name = "lodge_on_route", schema = "trailfinder_dev", catalog = "ftb_inv_2023_vz_3_a")
public class LodgeOnRouteEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "route_id", referencedColumnName = "route_id")
    private RouteEntity routeEntity;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "lodge_id", referencedColumnName = "lodge_id")
    private LodgeEntity lodgeEntity;

    public int getLodgeOnRoute() {
        return id;
    }

    public void setLodgeOnRoute(int id) {
        this.id = id;
    }

    public RouteEntity getRoute() {
        return routeEntity;
    }

    public void setRoute(RouteEntity routeEntity) {
        this.routeEntity = routeEntity;
    }

    public LodgeEntity getLodge() {
        return lodgeEntity;
    }

    public void setLodge(LodgeEntity lodgeEntity) {
        this.lodgeEntity = lodgeEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LodgeOnRouteEntity that = (LodgeOnRouteEntity) o;

        if (id != that.id) return false;
        if (routeEntity != null ? !routeEntity.equals(that.routeEntity) : that.routeEntity != null) return false;
        if (lodgeEntity != null ? !lodgeEntity.equals(that.lodgeEntity) : that.lodgeEntity != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (routeEntity != null ? routeEntity.hashCode() : 0);
        result = 31 * result + (lodgeEntity != null ? lodgeEntity.hashCode() : 0);
        return result;
    }
}
