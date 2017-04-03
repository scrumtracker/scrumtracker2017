package hei2017.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import hei2017.enumeration.StoryStatus;
import hei2017.json.JsonViews;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by pic on 09/02/2017.
 */
@Entity
public class Story implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    @JsonView(JsonViews.Basique.class)
    private Long id;

    @JsonView(JsonViews.Basique.class)
    private String nom;

    @JsonView(JsonViews.Basique.class)
    private String description;

    @JsonView(JsonViews.Basique.class)
    private StoryStatus status;

    @JsonView(JsonViews.Basique.class)
    private Integer points;

    @JsonView(JsonViews.Basique.class)
    private Timestamp dateCreation;

    @JsonView(JsonViews.Story.class)
    @ManyToOne(cascade = CascadeType.ALL)
    private Sprint storySprint;

    @JsonView(JsonViews.Story.class)
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Task> storyTasks = new HashSet<Task>(0);

    //Constructeurs
    public Story() {
        this.dateCreation = new Timestamp(System.currentTimeMillis());
        this.storySprint = null;
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
