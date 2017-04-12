package hei2017.entity;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import hei2017.json.JsonViews;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by pic on 02/03/2017.
 */
@Entity
@Table(name="utilisateur") //user déjà utilisé par pgsql
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique=true)
    @JsonView(JsonViews.Basique.class)
    private Long id;

    @JsonView(JsonViews.Basique.class)
    private String nom;

    @JsonView(JsonViews.Basique.class)
    private String prenom;

    @JsonView(JsonViews.Basique.class)
    private String pseudo;

    @JsonView(JsonViews.Basique.class)
    private String email;

    @JsonView(JsonViews.Basique.class)
    private Timestamp dateCreation;

    @JsonView(JsonViews.Hidden.class)
    private String password;

    @JsonView(JsonViews.User.class)
    @ManyToMany(mappedBy = "taskUsers", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Task> userTasks = new HashSet<Task>(0);

    public User() {
        this.dateCreation = new Timestamp(System.currentTimeMillis());
    }

    public Long getId() { return id; }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public Set<Task> getUserTasks() {
        return userTasks;
    }

    public void setUserTasks(Set<Task> userTasks) {
        this.userTasks = userTasks;
    }

    public Timestamp getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Timestamp dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void addTask(Task tache) {
        this.userTasks.add(tache);
    }
}
