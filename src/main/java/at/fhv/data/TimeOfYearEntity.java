package at.fhv.data;

import jakarta.persistence.*;

@Entity
@Table(name = "time_of_year", schema = "trailfinder_dev", catalog = "Trailfinder")
public class TimeOfYearEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "month_id")
    private String monthId;
    @Basic
    @Column(name = "route_id")
    private String routeId;
    @Basic
    @Column(name = "january")
    private Boolean january;
    @Basic
    @Column(name = "february")
    private Boolean february;
    @Basic
    @Column(name = "march")
    private Boolean march;
    @Basic
    @Column(name = "april")
    private Boolean april;
    @Basic
    @Column(name = "may")
    private Boolean may;
    @Basic
    @Column(name = "june")
    private Boolean june;
    @Basic
    @Column(name = "july")
    private Boolean july;
    @Basic
    @Column(name = "august")
    private Boolean august;
    @Basic
    @Column(name = "september")
    private Boolean september;
    @Basic
    @Column(name = "october")
    private Boolean october;
    @Basic
    @Column(name = "november")
    private Boolean november;
    @Basic
    @Column(name = "december")
    private Boolean december;

    public String getMonthId() {
        return monthId;
    }

    public void setMonthId(String monthId) {
        this.monthId = monthId;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public Boolean getJanuary() {
        return january;
    }

    public void setJanuary(Boolean january) {
        this.january = january;
    }

    public Boolean getFebruary() {
        return february;
    }

    public void setFebruary(Boolean february) {
        this.february = february;
    }

    public Boolean getMarch() {
        return march;
    }

    public void setMarch(Boolean march) {
        this.march = march;
    }

    public Boolean getApril() {
        return april;
    }

    public void setApril(Boolean april) {
        this.april = april;
    }

    public Boolean getMay() {
        return may;
    }

    public void setMay(Boolean may) {
        this.may = may;
    }

    public Boolean getJune() {
        return june;
    }

    public void setJune(Boolean june) {
        this.june = june;
    }

    public Boolean getJuly() {
        return july;
    }

    public void setJuly(Boolean july) {
        this.july = july;
    }

    public Boolean getAugust() {
        return august;
    }

    public void setAugust(Boolean august) {
        this.august = august;
    }

    public Boolean getSeptember() {
        return september;
    }

    public void setSeptember(Boolean september) {
        this.september = september;
    }

    public Boolean getOctober() {
        return october;
    }

    public void setOctober(Boolean october) {
        this.october = october;
    }

    public Boolean getNovember() {
        return november;
    }

    public void setNovember(Boolean november) {
        this.november = november;
    }

    public Boolean getDecember() {
        return december;
    }

    public void setDecember(Boolean december) {
        this.december = december;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeOfYearEntity that = (TimeOfYearEntity) o;

        if (monthId != null ? !monthId.equals(that.monthId) : that.monthId != null) return false;
        if (routeId != null ? !routeId.equals(that.routeId) : that.routeId != null) return false;
        if (january != null ? !january.equals(that.january) : that.january != null) return false;
        if (february != null ? !february.equals(that.february) : that.february != null) return false;
        if (march != null ? !march.equals(that.march) : that.march != null) return false;
        if (april != null ? !april.equals(that.april) : that.april != null) return false;
        if (may != null ? !may.equals(that.may) : that.may != null) return false;
        if (june != null ? !june.equals(that.june) : that.june != null) return false;
        if (july != null ? !july.equals(that.july) : that.july != null) return false;
        if (august != null ? !august.equals(that.august) : that.august != null) return false;
        if (september != null ? !september.equals(that.september) : that.september != null) return false;
        if (october != null ? !october.equals(that.october) : that.october != null) return false;
        if (november != null ? !november.equals(that.november) : that.november != null) return false;
        if (december != null ? !december.equals(that.december) : that.december != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = monthId != null ? monthId.hashCode() : 0;
        result = 31 * result + (routeId != null ? routeId.hashCode() : 0);
        result = 31 * result + (january != null ? january.hashCode() : 0);
        result = 31 * result + (february != null ? february.hashCode() : 0);
        result = 31 * result + (march != null ? march.hashCode() : 0);
        result = 31 * result + (april != null ? april.hashCode() : 0);
        result = 31 * result + (may != null ? may.hashCode() : 0);
        result = 31 * result + (june != null ? june.hashCode() : 0);
        result = 31 * result + (july != null ? july.hashCode() : 0);
        result = 31 * result + (august != null ? august.hashCode() : 0);
        result = 31 * result + (september != null ? september.hashCode() : 0);
        result = 31 * result + (october != null ? october.hashCode() : 0);
        result = 31 * result + (november != null ? november.hashCode() : 0);
        result = 31 * result + (december != null ? december.hashCode() : 0);
        return result;
    }
}
