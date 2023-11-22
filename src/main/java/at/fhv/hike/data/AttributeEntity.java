package at.fhv.hike.data;

import jakarta.persistence.*;

import java.lang.Integer;

@Entity
@Table(name = "attribute", schema = "trailfinder_dev", catalog = "ftb_inv_2023_vz_3_a")
public class AttributeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "attribute_id")
    private Integer attributeId;
    @Basic
    @Column(name = "strength")
    private Integer strength;
    @Basic
    @Column(name = "condition")
    private Integer condition;
    @Basic
    @Column(name = "scenery")
    private Integer scenery;
    @Basic
    @Column(name = "experience")
    private Integer experience;

    public Integer getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Integer attributeId) {
        this.attributeId = attributeId;
    }

    public Object getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Object getCondition() {
        return condition;
    }

    public void setCondition(Integer condition) {
        this.condition = condition;
    }

    public Object getScenery() {
        return scenery;
    }

    public void setScenery(Integer scenery) {
        this.scenery = scenery;
    }

    public Object getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AttributeEntity that = (AttributeEntity) o;

        if (attributeId != null ? !attributeId.equals(that.attributeId) : that.attributeId != null) return false;
        if (strength != null ? !strength.equals(that.strength) : that.strength != null) return false;
        if (condition != null ? !condition.equals(that.condition) : that.condition != null) return false;
        if (scenery != null ? !scenery.equals(that.scenery) : that.scenery != null) return false;
        if (experience != null ? !experience.equals(that.experience) : that.experience != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = attributeId != null ? attributeId.hashCode() : 0;
        result = 31 * result + (strength != null ? strength.hashCode() : 0);
        result = 31 * result + (condition != null ? condition.hashCode() : 0);
        result = 31 * result + (scenery != null ? scenery.hashCode() : 0);
        result = 31 * result + (experience != null ? experience.hashCode() : 0);
        return result;
    }
}
