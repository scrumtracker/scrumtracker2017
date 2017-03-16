package hei2017.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hei2017.enumeration.UniteTemps;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by pic on 09/02/2017.
 */
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique=true)
    private Long id;

    private String nom;

    private String description;

    private Long tempsDeCharge;

    private UniteTemps uniteTempsDeCharge;

    private Timestamp dateCreation;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<User> taskUsers = new HashSet<User>(0);

    @JsonIgnore
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
