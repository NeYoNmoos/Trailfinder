package at.fhv.hike.data;

import jakarta.persistence.*;

@Entity
@Table(name = "done_route", schema = "trailfinder_dev", catalog = "ftb_inv_2023_vz_3_a")
public class DoneRouteEntity {
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
    @Basic
    @Column(name = "done_at")
    private Object doneAt;

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

    public Object getDoneAt() {
        return doneAt;
    }

    public void setDoneAt(Object doneAt) {
        this.doneAt = doneAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DoneRouteEntity that = (DoneRouteEntity) o;

        if (id != that.id) return false;
        if (routeEntity != null ? !routeEntity.equals(that.routeEntity) : that.routeEntity != null) return false;
        if (userEntity != null ? !userEntity.equals(that.userEntity) : that.userEntity != null) return false;
        if (doneAt != null ? !doneAt.equals(that.doneAt) : that.doneAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (routeEntity != null ? routeEntity.hashCode() : 0);
        result = 31 * result + (userEntity != null ? userEntity.hashCode() : 0);
        result = 31 * result + (doneAt != null ? doneAt.hashCode() : 0);
        return result;
    }
}
