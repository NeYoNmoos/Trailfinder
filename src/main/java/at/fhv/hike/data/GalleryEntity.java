package at.fhv.hike.data;

import jakarta.persistence.*;

import java.util.Arrays;

@Entity
@Table(name = "gallery", schema = "trailfinder_dev", catalog = "ftb_inv_2023_vz_3_a")
public class GalleryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "picture_id")
    private Integer pictureId;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "route_id", referencedColumnName = "route_id")
    private RouteEntity routeEntity;
    @Basic
    @Column(name = "picture")
    private byte[] picture;

    public Integer getPictureId() {
        return pictureId;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }

    public RouteEntity getRoute() {
        return routeEntity;
    }

    public void setRoute(RouteEntity routeEntity) {
        this.routeEntity = routeEntity;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GalleryEntity that = (GalleryEntity) o;

        if (pictureId != null ? !pictureId.equals(that.pictureId) : that.pictureId != null) return false;
        if (routeEntity != null ? !routeEntity.equals(that.routeEntity) : that.routeEntity != null) return false;
        if (!Arrays.equals(picture, that.picture)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pictureId != null ? pictureId.hashCode() : 0;
        result = 31 * result + (routeEntity != null ? routeEntity.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(picture);
        return result;
    }
}
