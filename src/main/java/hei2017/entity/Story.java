package hei2017.entity;

import hei2017.enumeration.StoryStatus;

import javax.persistence.*;

/**
 * Created by pic on 09/02/2017.
 */
@Entity
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique=true, nullable = false)
    private Long id;

    private String nom;

    private String description;

    private StoryStatus status;

    //Constructeurs
    public Story(){};

    public Story(String nom){
        this.nom = nom;
    }

    //Méthodes
    public Long getId() { return id; }

    public String getNom() { return nom; }

    public void setNom(String nom) { this.nom = nom; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description;}

    @Enumerated(EnumType.STRING)
    public StoryStatus getStatus() { return status; }

    public void setStatus(StoryStatus status) { this.status = status; }
}
