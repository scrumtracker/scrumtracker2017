package hei2017.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by pic on 02/03/2017.
 */
@Entity
@Table(name="utilisateur") //user déjà utilisé par pgsql
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique=true)
    private Long id;

    private String nom;

    private String prenom;

    private String pseudo;

    private String email;

    private Timestamp dateCreation;

    @JsonIgnore
    private String password;

    @JsonIgnore
    @ManyToMany(mappedBy = "projectUsers", cascade = CascadeType.ALL)
    private Set<Project> userProjects = new HashSet<Project>(0);

    @JsonIgnore
    @ManyToMany(mappedBy = "taskUsers", cascade = CascadeType.ALL)
    private Set<Task> userTasks = new HashSet<Task>(0);

    public User() {
        this.dateCreation = new Timestamp(System.currentTimeMillis());
    }

    public User(String pseudo, String email) {
        this.pseudo = pseudo;
        this.email = email;
        this.dateCreation = new Timestamp(System.currentTimeMillis());
    }

    public User(String nom, String prenom, String pseudo, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.email = email;
        this.dateCreation = new Timestamp(System.currentTimeMillis());
    }

    public User(String email, Set<Project> userProjects)
    {
        this.email = email;
        this.userProjects = userProjects;
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

    public Set<Project> getUserProjects() {
        return userProjects;
    }

    public void setUserProjects(Set<Project> userProjects) {
        this.userProjects = userProjects;
    }

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
}
