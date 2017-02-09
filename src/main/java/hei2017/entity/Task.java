package hei2017.entity;

import hei2017.enumeration.UniteTemps;

import javax.persistence.*;

/**
 * Created by pic on 09/02/2017.
 */
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique=true, nullable = false)
    private Long id;

    private String nom;

    private String description;

    private Long tempsDeCharge;

    private UniteTemps uniteTempsDeCharge;

    //Constructeurs
    public Task(){};

    public Task(String nom)
    {
        this.nom = nom;
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
}
