package at.fhv.hike.data;

import jakarta.persistence.*;

@Entity
@Table(name = "poi_on_route", schema = "trailfinder_dev", catalog = "ftb_inv_2023_vz_3_a")
public class PoiOnRouteEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "point_of_interest_id", referencedColumnName = "point_of_interest_id")
    private PointOfInterestEntity pointOfInterestEntity;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "route_id", referencedColumnName = "route_id")
    private RouteEntity routeEntity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PointOfInterestEntity getPointOfInterestId() {
        return pointOfInterestEntity;
    }

    public void setPointOfInterestId(PointOfInterestEntity pointOfInterestEntity) {
        this.pointOfInterestEntity = pointOfInterestEntity;
    }

    public RouteEntity getRoute() {
        return routeEntity;
    }

    public void setRouteId(RouteEntity routeEntity) {
        this.routeEntity = routeEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PoiOnRouteEntity that = (PoiOnRouteEntity) o;

        if (id != that.id) return false;
        if (pointOfInterestEntity != null ? !pointOfInterestEntity.equals(that.pointOfInterestEntity) : that.pointOfInterestEntity != null)
            return false;
        if (routeEntity != null ? !routeEntity.equals(that.routeEntity) : that.routeEntity != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (pointOfInterestEntity != null ? pointOfInterestEntity.hashCode() : 0);
        result = 31 * result + (routeEntity != null ? routeEntity.hashCode() : 0);
        return result;
    }
}
