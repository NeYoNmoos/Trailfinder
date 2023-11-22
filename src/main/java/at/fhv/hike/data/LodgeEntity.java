package at.fhv.hike.data;

import jakarta.persistence.*;

@Entity
@Table(name = "lodge", schema = "trailfinder_dev", catalog = "ftb_inv_2023_vz_3_a")
public class LodgeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "lodge_id")
    private Integer lodgeId;
    @Basic
    @Column(name = "route_id")
    private Integer routeId;
    @Basic
    @Column(name = "coordinate_id")
    private Integer coordinateId;
    @Basic
    @Column(name = "description")
    private String description;

    public Integer getLodgeId() {
        return lodgeId;
    }

    public void setLodgeId(Integer lodgeId) {
        this.lodgeId = lodgeId;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public Integer getCoordinateId() {
        return coordinateId;
    }

    public void setCoordinateId(Integer coordinateId) {
        this.coordinateId = coordinateId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LodgeEntity that = (LodgeEntity) o;

        if (lodgeId != null ? !lodgeId.equals(that.lodgeId) : that.lodgeId != null) return false;
        if (routeId != null ? !routeId.equals(that.routeId) : that.routeId != null) return false;
        if (coordinateId != null ? !coordinateId.equals(that.coordinateId) : that.coordinateId != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = lodgeId != null ? lodgeId.hashCode() : 0;
        result = 31 * result + (routeId != null ? routeId.hashCode() : 0);
        result = 31 * result + (coordinateId != null ? coordinateId.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
