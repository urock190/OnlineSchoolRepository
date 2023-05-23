package webapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "additional_materials", schema = "school_schema")
public class AdditionalMaterial {
    @Column(name = "name", nullable = false)
    @NotBlank(message = "{name.not-blank}")
    @Size(max = 200, message = "{name.size}")
    private String name;
    @Id
    @Column(name = "material_id", nullable = false)
    @NotNull(message = "{id.notnull}")
    @Positive(message = "{id.positive}")
    private Integer ID;
    @Column(name = "lecture_id")
    @Positive(message = "{id.positive}")
    private Integer lectureID;
    @Column(name = "resource_type", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "{resource-type.notnull}")
    private ResourceType resourceType;

    public AdditionalMaterial() {}

    public AdditionalMaterial(String name, ResourceType resourceType, int lectureID) {
        this.name = name;
        this.resourceType = resourceType;
        this.lectureID = lectureID;
    }

    public AdditionalMaterial(String name, int ID, ResourceType resourceType, int lectureID){
        this.name = name;
        this.ID = ID;
        this.resourceType = resourceType;
        this.lectureID = lectureID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public int getLectureID() {
        return lectureID;
    }

    public void setLectureID(int lectureID) {
        this.lectureID = lectureID;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdditionalMaterial that = (AdditionalMaterial) o;
        return getLectureID() == that.getLectureID() && getName().equals(that.getName()) && getID().equals(that.getID()) && getResourceType() == that.getResourceType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getID(), getLectureID(), getResourceType());
    }
}