package hei2017.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hei2017.enumeration.StoryStatus;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by pic on 09/02/2017.
 */
@Entity
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private Long id;

    private String nom;

    private String description;

    private StoryStatus status;

    private Integer points;

    private Timestamp dateCreation;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Sprint storySprint = new Sprint();

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Task> storyTasks = new HashSet<Task>(0);

    //Constructeurs
    public Story() {
        this.dateCreation = new Timestamp(System.currentTimeMillis());
    }

    public Story(String nom) {
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

    @Enumerated(EnumType.STRING)
    public StoryStatus getStatus() {
        return status;
    }

    public void setStatus(StoryStatus status) {
        this.status = status;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Sprint getStorySprint() {
        return storySprint;
    }

    public void setStorySprint(Sprint storySprint) {
        this.storySprint = storySprint;
    }

    public void addTask(Task task) {
        storyTasks.add(task);
    }

    public Timestamp getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Timestamp dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Set<Task> getStoryTasks() {
        return storyTasks;
    }

    public void setStoryTasks(Set<Task> storyTasks) {
        this.storyTasks = storyTasks;
    }
}
