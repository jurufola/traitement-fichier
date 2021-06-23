package home.jurufola.entites;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe Allergene Represente un allergène contenu dans le produit
 * @author juruf_000
 */

@Entity
@Table(name = "allergene")
public class Allergene {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "libelle")
    private String libelle;

    @ManyToMany
    @JoinTable(name = "produit_allergene",
            joinColumns = @JoinColumn(name = "id_allergene", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_produit", referencedColumnName = "id"))
    private Set<Produit> produits = new HashSet<>();

    /**
     * Constructeur
     * @param libelle Le libellé de l'allergèhe
     * @param produits La liste des produits où l'allergène est présent
     */
    public Allergene(String libelle, Set<Produit> produits) {
        this.libelle = libelle;
        this.produits = produits;
    }

    /**
     * Constructeur vide
     */
    public Allergene() {
    }

    /**
     * Constructeur à partir du libellé
     * @param libelle Le libellé de l'allergène
     */
    public Allergene(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Getter id
     * @return L'id de l'allergène
     */
    public int getId() {
        return id;
    }

    /**
     * Setter id
     * @param id L'id de l'allergène
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter libelle
     * @return Le libellé de l'allergène
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Setter libelle
     * @param libelle Le libellé de l'allergène
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Getter produits
     * @return La liste des produits où l'allergène est présent
     */
    public Set<Produit> getProduits() {
        return produits;
    }

    /**
     * Setter produits
     * @param produits La liste des produits où l'allergène est présent
     */
    public void setProduits(Set<Produit> produits) {
        this.produits = produits;
    }
}
