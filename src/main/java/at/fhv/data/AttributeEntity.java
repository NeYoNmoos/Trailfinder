package at.fhv.data;

import jakarta.persistence.*;

@Entity
@Table(name = "attribute", schema = "trailfinder_dev", catalog = "Trailfinder")
public class AttributeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "attribute_id")
    private String attributeId;
    @Basic
    @Column(name = "route_id")
    private String routeId;
    @Basic
    @Column(name = "strength")
    private Object strength;
    @Basic
    @Column(name = "condition")
    private Object condition;
    @Basic
    @Column(name = "scenery")
    private Object scenery;
    @Basic
    @Column(name = "experience")
    private Object experience;

    public String getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(String attributeId) {
        this.attributeId = attributeId;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public Object getStrength() {
        return strength;
    }

    public void setStrength(Object strength) {
        this.strength = strength;
    }

    public Object getCondition() {
        return condition;
    }

    public void setCondition(Object condition) {
        this.condition = condition;
    }

    public Object getScenery() {
        return scenery;
    }

    public void setScenery(Object scenery) {
        this.scenery = scenery;
    }

    public Object getExperience() {
        return experience;
    }

    public void setExperience(Object experience) {
        this.experience = experience;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AttributeEntity that = (AttributeEntity) o;

        if (attributeId != null ? !attributeId.equals(that.attributeId) : that.attributeId != null) return false;
        if (routeId != null ? !routeId.equals(that.routeId) : that.routeId != null) return false;
        if (strength != null ? !strength.equals(that.strength) : that.strength != null) return false;
        if (condition != null ? !condition.equals(that.condition) : that.condition != null) return false;
        if (scenery != null ? !scenery.equals(that.scenery) : that.scenery != null) return false;
        if (experience != null ? !experience.equals(that.experience) : that.experience != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = attributeId != null ? attributeId.hashCode() : 0;
        result = 31 * result + (routeId != null ? routeId.hashCode() : 0);
        result = 31 * result + (strength != null ? strength.hashCode() : 0);
        result = 31 * result + (condition != null ? condition.hashCode() : 0);
        result = 31 * result + (scenery != null ? scenery.hashCode() : 0);
        result = 31 * result + (experience != null ? experience.hashCode() : 0);
        return result;
    }
}
