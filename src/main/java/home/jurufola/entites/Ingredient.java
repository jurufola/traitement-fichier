package home.jurufola.entites;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe Ingredient représente un ingrédient pour le produit
 * @author juruf_000
 */

@Entity
@Table(name = "ingredient")
public class Ingredient {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "libelle", length = 1000)
    private String libelle;

    @ManyToMany
    @JoinTable(name = "produit_ingredient",
            joinColumns = @JoinColumn(name = "id_ingredient", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_produit", referencedColumnName = "id"))
    private Set<Produit> produits =  new HashSet<>();

    /**
     * Constructeur à partir de libelle
     * @param libelle Le libellé de l'ingrédient
     */
    public Ingredient(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Constructeur
     * @param libelle Libellé de l'ingrédient
     * @param produits La liste des produits qui utilisent l'ingrédient
     */
    public Ingredient(String libelle, Set<Produit> produits) {
        this.libelle = libelle;
        this.produits = produits;
    }

    /**
     * Constrtucteur vide
     */
    public Ingredient() {
    }

    /**
     * Getter id
     * @return L'id de l'ingrédient
     */
    public int getId() {
        return id;
    }

    /**
     * Setter id
     * @param id L'id de l'ingrédient
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter Libellé
     * @return Le libellé de l'ingrédient
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Setter Libellé
     * @param libelle Le libellé de l'ingrédient
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Getter porduits
     * @return Liste de produits qui utilisent l'ingrédient
     */
    public Set<Produit> getProduits() {
        return produits;
    }

    /**
     * Setter produits
     * @param produits Liste de produits qui utilisent l'ingrédient
     */
    public void setProduits(Set<Produit> produits) {
        this.produits = produits;
    }
}
