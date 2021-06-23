package home.jurufola.entites;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe Additif représeante un additif présent dans le produit
 * @author juruf_000
 */
@Entity
@Table(name = "additif", indexes = @Index(name = "description_index", columnList = "description"))
public class Additif {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "description")
    private String description;

    @ManyToMany
    @JoinTable(name = "produit_additif",
            joinColumns = @JoinColumn(name = "id_additif", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_produit", referencedColumnName = "id"))
    private Set<Produit> produits = new HashSet<>();

    /**
     * Constructeur
     * @param description La description de l'additif
     * @param produits La liste des produits où l'additif est présent
     */
    public Additif(String description, Set<Produit> produits) {
        this.description = description;
        this.produits = produits;
    }

    /**
     * Constructeur vide
     */
    public Additif() {
    }

    /**
     * Constructeur à partir de la description
     * @param description La description de l'additif
     *
     */
    public Additif(String description) {
        this.description = description;
    }

    /**
     * Getter id
     * @return L'id de l'additif
     */
    public int getId() {
        return id;
    }

    /**
     * Setter id
     * @param id L'id de l'additif
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter description
     * @return La description de l'additif
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter description
     * @param description La description de l'additif
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter produits
     * @return La liste des produits où l'additif est présent
     */
    public Set<Produit> getProduits() {
        return produits;
    }

    /**
     * Setter produits
     * @param produits La liste des produits où l'additif est présent
     */
    public void setProduits(Set<Produit> produits) {
        this.produits = produits;
    }
}
