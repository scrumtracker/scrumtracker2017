package hei2017.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import hei2017.json.JsonViews;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * Created by pic on 09/02/2017.
 */
@Entity
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique=true)
    @JsonView(JsonViews.Basique.class)
    private Long id;

    @JsonView(JsonViews.Basique.class)
    private String nom;

    @JsonView(JsonViews.Basique.class)
    private String description;

    @JsonView(JsonViews.Basique.class)
    private Timestamp dateCreation;

    @JsonView(JsonViews.Project.class)
    @OneToMany(mappedBy = "sprintProject", cascade = CascadeType.ALL)
    private Set<Sprint> projectSprints = new HashSet<Sprint>(0);

    //Constructeurs
    public Project(){this.dateCreation = new Timestamp(System.currentTimeMillis());};

    //Méthodes
    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Sprint> getProjectSprints() {
        return this.projectSprints;
    }

    public void setProjectSprints(Set<Sprint> projectSprints) {
        this.projectSprints = projectSprints;
    }

    public void addSprint(Sprint sprint)
    {
        projectSprints.add(sprint);
    }

    public Timestamp getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Timestamp dateCreation) {
        this.dateCreation = dateCreation;
    }
}
