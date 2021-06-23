package home.jurufola.entites;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe Categorie répresente la categorie d'un produit
 * @author juruf_000
 */

@Entity
@Table(name = "categorie", indexes = @Index(name = "libelle_index", columnList = "libelle"))
public class Categorie {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "libelle")
    private String libelle;

    @OneToMany(mappedBy = "categorie")
    private Set<Produit> produits = new HashSet<>();

    /**
     * Constructeur aavec libelle
     * @param libelle Le libellé de la catégorie
     */
    public Categorie(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Cosntructeur
     * @param libelle Le libellé de la catégorie
     * @param produits La liste des produits de la catégorie
     */
    public Categorie(String libelle, Set<Produit> produits) {
        this.libelle = libelle;
        this.produits = produits;
    }

    /**
     * Constructeur vide
     */
    public Categorie() {
    }

    /**
     * Getter id
     * @return L'id de la catégorie
     */
    public int getId() {
        return id;
    }

    /**
     * Setter id
     * @param id L'id de la catégorie
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter libelle
     * @return Le libellé de la catégorie
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Setter libelle
     * @param libelle Le libellé de la catégorie
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Getter produits
     * @return La liste des produits de la catégorie
     */
    public Set<Produit> getProduits() {
        return produits;
    }

    /**
     * Setter produits
     * @param produits La liste des produits de la catégorie
     */
    public void setProduits(Set<Produit> produits) {
        this.produits = produits;
    }

}
