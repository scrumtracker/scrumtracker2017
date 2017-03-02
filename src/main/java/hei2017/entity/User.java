package hei2017.entity;

import javax.persistence.*;

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

    public User() {}

    public User(String pseudo, String email) {
        this.pseudo = pseudo;
        this.email = email;
    }

    public User(String nom, String prenom, String pseudo, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

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
}
