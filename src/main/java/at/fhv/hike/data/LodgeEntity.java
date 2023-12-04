package at.fhv.hike.data;

import jakarta.persistence.*;

@Entity
@Table(name = "lodge", schema = "trailfinder_dev", catalog = "ftb_inv_2023_vz_3_a")
public class LodgeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "lodge_id")
    private Integer lodgeId;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "coordinate_id", referencedColumnName = "coordinate_id")
    private CoordinateEntity coordinateEntity;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "name")
    private String name;

    public Integer getLodgeId() {
        return lodgeId;
    }

    public void setLodgeId(Integer lodgeId) {
        this.lodgeId = lodgeId;
    }

    public CoordinateEntity getCoordinateEntity() {
        return coordinateEntity;
    }

    public void setCoordinateEntity(CoordinateEntity coordinateEntity) {
        this.coordinateEntity = coordinateEntity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {return name; }

    public void setName(String name) { this.name = name; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LodgeEntity that = (LodgeEntity) o;

        if (lodgeId != null ? !lodgeId.equals(that.lodgeId) : that.lodgeId != null) return false;
        if (coordinateEntity != null ? !coordinateEntity.equals(that.coordinateEntity) : that.coordinateEntity != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = lodgeId != null ? lodgeId.hashCode() : 0;
        result = 31 * result + (coordinateEntity != null ? coordinateEntity.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (name != null ? description.hashCode() : 0);
        return result;
    }
}
