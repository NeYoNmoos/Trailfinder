package at.fhv.hike.data;

import jakarta.persistence.*;

@Entity
@Table(name = "point_of_interest", schema = "trailfinder_dev", catalog = "ftb_inv_2023_vz_3_a")
public class PointOfInterestEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "point_of_interest_id")
    private int pointOfInterestId;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "coordinates_id", referencedColumnName = "coordinates_id")
    private CoordinateEntity coordinateEntity;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "description")
    private String description;

    public int getPointOfInterestId() {
        return pointOfInterestId;
    }

    public void setPointOfInterestId(int pointOfInterestId) {
        this.pointOfInterestId = pointOfInterestId;
    }

    public CoordinateEntity getCoordinates() {
        return coordinateEntity;
    }

    public void setCoordinatesId(Integer coordinatesId) {
        this.coordinateEntity = coordinateEntity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        PointOfInterestEntity that = (PointOfInterestEntity) o;

        if (pointOfInterestId != that.pointOfInterestId) return false;
        if (coordinateEntity != null ? !coordinateEntity.equals(that.coordinateEntity) : that.coordinateEntity != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pointOfInterestId;
        result = 31 * result + (coordinateEntity != null ? coordinateEntity.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
