package home.jurufola.entites;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe Marque représente la marque d'un produit
 * @author juruf_000
 */

@Entity
@Table(name = "marque")
public class Marque {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "libelle")
    private String libelle;

    @OneToMany(mappedBy = "marque")
    private Set<Produit> produits = new HashSet<>();

    /**
     * Constructeur avec libellé
     * @param libelle Le libellé de la marque
     */
    public Marque(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Constructeur
     * @param libelle Libelle de la marque
     * @param produits La liste des produits fabriqués par la marque
     */
    public Marque(String libelle, Set<Produit> produits) {
        this.libelle = libelle;
        this.produits = produits;
    }

    /**
     * Constructeur vide
     */
    public Marque() {
    }
    /**
     * Getter id
     * @return  L'identifiant de la marque
     */
    public int getId() {
        return id;
    }

    /**
     * Setter id
     * @param id L'identifiant de la marque
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter libelle
     * @return Le libellé de la marque
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Setter libelle
     * @param libelle Le libellé de la marque
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Getter produits
     * @return La liste des produits de la marque
     */
    public Set<Produit> getProduits() {
        return produits;
    }

    /**
     * Setter produits
     * @param produits La liste des prosuits de la marque
     */
    public void setProduits(Set<Produit> produits) {
        this.produits = produits;
    }
}
