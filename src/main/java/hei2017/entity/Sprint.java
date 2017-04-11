package hei2017.entity;

import com.fasterxml.jackson.annotation.JsonView;
import hei2017.json.JsonViews;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by pic on 08/02/2017.
 */
@Entity
public class Sprint implements Serializable {

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

    @JsonView(JsonViews.Basique.class)
    private Timestamp dateModification;

    @JsonView(JsonViews.Basique.class)
    private Timestamp dateDebut;

    @JsonView(JsonViews.Basique.class)
    private Timestamp dateFin;

    @JsonView(JsonViews.Sprint.class)
    @ManyToOne
    private Project sprintProject;

    @JsonView(JsonViews.Sprint.class)
    @OneToMany(mappedBy = "storySprint", cascade = CascadeType.ALL)
    private Set<Story> sprintStories = new HashSet<Story>(0);

    //Constructeurs
    public Sprint(){
        this.dateCreation = new Timestamp(System.currentTimeMillis());
        this.sprintProject = null;
    }

    //Méthodes
    public Long getId() { return id; }

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

    public Timestamp getDateCreation() {
        return dateCreation;
    }

    public Timestamp getDateModification() {
        return dateModification;
    }

    public void setDateModification(Timestamp dateModification) {
        this.dateModification = dateModification;
    }

    public Timestamp getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Timestamp dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Timestamp getDateFin() {
        return dateFin;
    }

    public void setDateFin(Timestamp dateFin) {
        this.dateFin = dateFin;
    }

    public void setDateCreation(Timestamp dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Project getSprintProject() {
        return sprintProject;
    }

    public void setSprintProject(Project sprintProject) {
        this.sprintProject = sprintProject;
    }

    public Set<Story> getSprintStories() {
        return sprintStories;
    }

    public void setSprintStories(Set<Story> sprintStories) {
        this.sprintStories = sprintStories;
    }

    public Sprint addStory(Story story)
    {
        sprintStories.add(story);
        return this;
    }

    public String getStatus() {
        if (this.getDateFin() != null) {
            if (this.getDateFin().before(new Timestamp(System.currentTimeMillis()))) {
                return "over";
            } else {
                if (this.getDateDebut().before(new Timestamp(System.currentTimeMillis()))) {
                    return "actual";
                } else {
                    return "upcoming";
                }
            }
        }else{
            return "undefined";
        }
    }
}
