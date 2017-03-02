package hei2017.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by pic on 08/02/2017.
 */
@Entity
public class Sprint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique=true)
    private Long id;

    private String nom;

    private String description;

    private Timestamp dateCreation;

    private Timestamp dateModification;

    private Timestamp dateDebut;

    private Timestamp dateFin;


    //Constructeurs
    public Sprint(){
        this.dateCreation = new Timestamp(System.currentTimeMillis());
    }

    public Sprint(String nom){
        this.nom = nom;
        this.dateCreation = new Timestamp(System.currentTimeMillis());
    }


    //Méthodes
    public Long getId() { return id; }

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

    public Timestamp getDateCreation() {
        return dateCreation;
    }

    public Timestamp getDateModification() {
        return dateModification;
    }

    public void setDateModification(Timestamp dateModification) {
        this.dateModification = dateModification;
    }

    public Timestamp getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Timestamp dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Timestamp getDateFin() {
        return dateFin;
    }

    public void setDateFin(Timestamp dateFin) {
        this.dateFin = dateFin;
    }
}
