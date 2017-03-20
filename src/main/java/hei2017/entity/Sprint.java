package hei2017.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by pic on 08/02/2017.
 */
@Entity
public class Sprint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private Long id;

    private String nom;

    private String description;

    private Timestamp dateCreation;

    private Timestamp dateModification;

    private Timestamp dateDebut;

    private Timestamp dateFin;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Project sprintProject;

    @JsonIgnore
    @OneToMany(mappedBy = "storySprint", cascade = CascadeType.ALL)
    private Set<Story> sprintStories = new HashSet<Story>(0);


    //Constructeurs
    public Sprint() {
        this.dateCreation = new Timestamp(System.currentTimeMillis());
    }

    public Sprint(String nom) {
        this.nom = nom;
        this.dateCreation = new Timestamp(System.currentTimeMillis());
    }


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

    public void addStory(Story story) {
        sprintStories.add(story);
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
