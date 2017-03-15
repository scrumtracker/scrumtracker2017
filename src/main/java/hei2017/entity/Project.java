package hei2017.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * Created by pic on 09/02/2017.
 */
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique=true)
    private Long id;

    private String nom;

    private String description;

    private Timestamp dateCreation;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<User> projectUsers = new HashSet<User>(0);

    @JsonIgnore
    @OneToMany(mappedBy = "sprintProject", cascade = CascadeType.ALL)
    private Set<Sprint> projectSprints = new HashSet<Sprint>(0);

    //Constructeurs
    public Project(){this.dateCreation = new Timestamp(System.currentTimeMillis());};

    public Project(String nom)
    {
        this.nom = nom;
        this.dateCreation = new Timestamp(System.currentTimeMillis());
    }

    public Project(String nom, String description)
    {
        this.nom = nom;
        this.description = description;
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

    public Set<User> getProjectUsers() {
        return projectUsers;
    }

    public void setProjectUsers(Set<User> projectUsers) {
        this.projectUsers = projectUsers;
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

    public void addUser(User user)
    {
        projectUsers.add(user);
    }

    public Timestamp getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Timestamp dateCreation) {
        this.dateCreation = dateCreation;
    }
}
