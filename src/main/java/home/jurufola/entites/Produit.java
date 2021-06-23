package home.jurufola.entites;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe Produit représente le produit de l'application Yuka
 * @author juruf_000
 */

@Entity
@Table(name = "produit")
public class Produit {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nom")
    private String nom;

    @ManyToOne
    @JoinColumn(name = "id_categorie")
    private Categorie categorie;

    @ManyToOne
    @JoinColumn(name = "id_marque")
    private Marque marque;

    @Column(name = "score_nutritionnel")
    private char scoreNutritionnel;

    @Column(name = "energie100g", length = 2000)
    private String energie100g;

    @Column(name = "graisse100g")
    private String graisse100g;

    @Column(name = "sucres100g")
    private String sucres100g;

    @Column(name = "fibres100g")
    private String fibres100g;

    @Column(name = "proteines100g")
    private String proteines100g;

    @Column(name = "sel100g")
    private String sel100g;

    @ManyToMany
    @JoinTable(name = "produit_ingredient",
                joinColumns = @JoinColumn(name = "id_produit", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "id_ingredient", referencedColumnName = "id"))
    private Set<Ingredient> ingredients = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "produit_allergene",
            joinColumns = @JoinColumn(name = "id_produit", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_allergene", referencedColumnName = "id"))
    private Set<Allergene> allergenes = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "produit_additif",
            joinColumns = @JoinColumn(name = "id_produit", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_additif", referencedColumnName = "id"))
    private Set<Additif> additifs = new HashSet<>();


    /**
     * Constructeur
     * @param nom
     * @param categorie La catégorie du produit
     * @param marque La marque du produit
     * @param scoreNutritionnel Le score nutritionnel du produit
     * @param energie100g La quantité d'energie pour 100g de produit
     * @param graisse100g La quantité dde graisse pour 100g de produit
     * @param sucres100g La quantité de sucre pour 100g de produit
     * @param fibres100g La quantité de fibre pour 100g de produit
     * @param proteines100g La quantité de protéine pour 100g de produit
     * @param sel100g La quantité de sel pour 100g de produit
     * @param ingredients La liste des ingrédient commposants le produit
     * @param allergenes La liste des allergène présents dans le produit
     * @param additifs La Liste des additifs présents dans le produit
     */
    public Produit(String nom, Categorie categorie, Marque marque, char scoreNutritionnel, String energie100g, String
            graisse100g, String sucres100g, String fibres100g, String proteines100g, String sel100g, Set<Ingredient>
            ingredients, Set<Allergene> allergenes, Set<Additif> additifs) {
        this.nom = nom;
        this.categorie = categorie;
        this.marque = marque;
        this.scoreNutritionnel = scoreNutritionnel;
        this.energie100g = energie100g;
        this.graisse100g = graisse100g;
        this.sucres100g = sucres100g;
        this.fibres100g = fibres100g;
        this.proteines100g = proteines100g;
        this.sel100g = sel100g;
        this.ingredients = ingredients;
        this.allergenes = allergenes;
        this.additifs = additifs;
    }

    /**
     * Constructeur vide
     */
    public Produit() {
    }

    /**
     * Getter id
     * @return L'id du produit
     */
    public int getId() {
        return id;
    }

    /**
     * Getter categorie
     * @return La catégorie du produit
     */
    public Categorie getCategorie() {
        return categorie;
    }

    /**
     * Getter marque
     * @return La marque du produit
     */
    public Marque getMarque() {
        return marque;
    }

    /**
     * Getter score nutritionnel
     * @return Le score nutritionnel du produit
     */
    public char getScoreNutritionnel() {
        return scoreNutritionnel;
    }

    /**
     * Getter enrgie100g
     * @return La quantité d'energie pour 100g de produit
     */
    public String getEnergie100g() {
        return energie100g;
    }

    /**
     * Getter graisse100g
     * @return La quantité de graisse pour 100g de produit
     */
    public String getGraisse100g() {
        return graisse100g;
    }

    /**
     * Getter sucres100g
     * @return La quantité de sucre pour 100g de produit
     */
    public String getSucres100g() {
        return sucres100g;
    }

    /**
     * Getter fibres100g
     * @return La quantité de fibres pour 100g de produit
     */
    public String getFibres100g() {
        return fibres100g;
    }

    /**
     * Getter proteines100g
     * @return La quantité de protéines pour 100g de produit
     */
    public String getProteines100g() {
        return proteines100g;
    }

    /**
     * Getter sel100g
     * @return La quantité de sel pour 100g de produit
     */
    public String getSel100g() {
        return sel100g;
    }

    /**
     * Getter ingredients
     * @return La liste des ingrédients composant le produit
     */
    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    /**
     * Getter allergenes
     * @return La liste des allergènes présents dans le produit
     */
    public Set<Allergene> getAllergenes() {
        return allergenes;
    }

    /**
     * Getter additifs
     * @return La liste des additifs présents dans le produit
     */
    public Set<Additif> getAdditifs() {
        return additifs;
    }

    /**
     * Setter id
     * @param id L'id su produit
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Setter categorie
     * @param categorie La catégorie du produit
     */
    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    /**
     * Setter marque
     * @param marque La marque du produit
     */
    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    /**
     * Setter score nutritionnel
     * @param scoreNutritionnel Le score nutritionnel du produit
     */
    public void setScoreNutritionnel(char scoreNutritionnel) {
        this.scoreNutritionnel = scoreNutritionnel;
    }

    /**
     * Setter ingredients
     * @param ingredients La liste des ingrédients du produit
     */
    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * Setter allergenes
     * @param allergenes La liste des allergènes présents dans le produit
     */
    public void setAllergenes(Set<Allergene> allergenes) {
        this.allergenes = allergenes;
    }

    /**
     * Setter additifs
     * @param additifs La liste des additifs présents dans le produit
     */
    public void setAdditifs(Set<Additif> additifs) {
        this.additifs = additifs;
    }

    /**
     * Getter nom
     * @return Le nom du produit
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter nom
     * @param nom Le nom du produit
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
}
