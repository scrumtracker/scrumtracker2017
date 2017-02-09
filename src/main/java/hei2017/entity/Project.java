package hei2017.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Properties;

/**
 * Created by pic on 09/02/2017.
 */
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique=true, nullable = false)
    private Long id;

    private String nom;

    private String description;

    //Constructeurs
    public Project(){};

    public Project(String nom)
    {
        this.nom = nom;
    }

    public Project(String nom, String description)
    {
        this.nom = nom;
        this.description = description;
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
}
