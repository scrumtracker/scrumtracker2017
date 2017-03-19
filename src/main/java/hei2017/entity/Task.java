package hei2017.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import hei2017.enumeration.UniteTemps;
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
public class Task implements Serializable {

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
    private Long tempsDeCharge;

    @JsonView(JsonViews.Basique.class)
    private UniteTemps uniteTempsDeCharge;

    @JsonView(JsonViews.Basique.class)
    private Timestamp dateCreation;

    @JsonView(JsonViews.Task.class)
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<User> taskUsers = new HashSet<User>(0);

    @JsonView(JsonViews.Task.class)
    @ManyToMany(mappedBy = "storyTasks", cascade = CascadeType.ALL)
    private Set<Story> taskStories = new HashSet<Story>(0);

    //Constructeurs
    public Task(){
        this.dateCreation = new Timestamp(System.currentTimeMillis());
    }

    public Task(String nom)
    {
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

    public Long getTempsDeCharge() {
        return tempsDeCharge;
    }

    public void setTempsDeCharge(Long tempsDeCharge) {
        this.tempsDeCharge = tempsDeCharge;
    }

    public UniteTemps getUniteTempsDeCharge() {
        return uniteTempsDeCharge;
    }

    public void setUniteTempsDeCharge(UniteTemps uniteTempsDeCharge) {
        this.uniteTempsDeCharge = uniteTempsDeCharge;
    }

    public Set<User> getTaskUsers() {
        return taskUsers;
    }

    public void setTaskUsers(Set<User> taskUsers) {
        this.taskUsers = taskUsers;
    }

    public void addUser(User user)
    {
        this.taskUsers.add(user);
    }

    public Set<Story> getTaskStories() {
        return taskStories;
    }

    public void setTaskStories(Set<Story> taskStories) {
        this.taskStories = taskStories;
    }
}
