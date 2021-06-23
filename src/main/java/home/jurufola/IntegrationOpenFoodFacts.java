package home.jurufola;

import home.jurufola.entites.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Period;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Classe exécutable mets dans la BDD les produits extrait du fichier csv Open Food Facts
 * @author juruf_000
 */
public class IntegrationOpenFoodFacts {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("open_food_facts");
        EntityManager manager = factory.createEntityManager();
        Path path = Paths.get("C:\\Users\\juruf_000\\Documents\\Formation Java\\14 - JPA\\TP\\open-food-facts2.csv");
        try {
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8); //On lie toules les lignes du fichier
            Iterator<String> iterator = lines.iterator();
            System.out.println(iterator.next());//Pour lire la première ligne et enlever l'entête du fichier

            //Parcours lignes Fichier
            while(iterator.hasNext()) {
                String line = iterator.next();
                //System.out.println("*****************Nouvelle ligne*******************");
                //System.out.println(line);
                String[] tabLines = line.split("\\|", -1);// second paramétre négatif pour recuperer aussi les
                // caractères vides
                String libelleCategorie = tabLines[0];
                //System.out.println("Libelle catégorie : " + libelleCategorie);
                String libelleMarque = tabLines[1];
                //System.out.println("Libelle marque : " + libelleMarque);
                String nomProduit = tabLines[2];
                //System.out.println("Nom produit : " + nomProduit);
                char scoreNutritionnel = tabLines[3].charAt(0);
                //System.out.println("Score nutritionnel :" + scoreNutritionnel);
                String[] ingredientsList = tabLines[4].split(",");
                //System.out.println("Liste ingrédients");
                for (String s : ingredientsList) {
                    System.out.println(s);
                }
                String energie100g = tabLines[5];
                //System.out.println("Energie pour 100g : " + energie100g);
                String graisse100g = tabLines[6];
                //System.out.println("Graisse pour 100g : " + graisse100g);
                String sucres100g = tabLines[7];
               // System.out.println("Sucres pour 100g : " + sucres100g);
                String fibres100g = tabLines[8];
               // System.out.println("Fibres pour 100g : " + fibres100g);
                String proteines100g = tabLines[9];
               // System.out.println("Protéines pour 100g : " + proteines100g);
                String sel100g = tabLines[10];
               // System.out.println("Sel pour 100g : " + sel100g);
                String[] allergenesList = tabLines[28].split(",");
                String[] additifsList = tabLines[29].split(",");
                //System.out.println("Liste allergnes");

                EntityTransaction transaction = manager.getTransaction();
                transaction.begin();



                //Creation Marque
                //Marque marque = new Marque(libelleMarque);
                TypedQuery<Marque> marqueQuery = manager.createQuery("select m from Marque m where m.libelle = :" +
                                "libelle",
                        Marque.class);
                marqueQuery.setParameter("libelle", libelleMarque);
                Marque marque = new Marque(libelleMarque);
                try {// Si le resultat de la query n 'est pas vide, la marque
                    // existe et on la récupère de la base.
                    Marque marqueBase = marqueQuery.getSingleResult();
                    marque = marqueBase;
                } catch (NoResultException e) {// Si le resulat de la query ci dessus est vide, une exception est
                    // levée, dans ce cas la marque n'existe pas en base on , l'insère.
                    manager.persist(marque);
                }
                //Creation catégorie
                //Categorie categorie = new Categorie(libelleCategorie);
                TypedQuery<Categorie> categorieQuery = manager.createQuery("select c from Categorie c where c.libelle" +
                        " = :libelle", Categorie.class);
                categorieQuery.setParameter("libelle", libelleCategorie);
                Categorie categorie = new Categorie(libelleCategorie);
                try {// Si le resultat de la query n 'est pas vide,
                    // la Categorie existe et on la récupère de la base.
                    Categorie categorieBase = categorieQuery.getSingleResult();
                    categorie = categorieBase;
                } catch (NoResultException e) {// Si le resulat de la query ci dessus est vide, une exception est
                    // levée, dans ce cas la Categorie n'existe pas en base on , l'insère.
                    manager.persist(categorie);
                }
                transaction.commit();

                //Creation ingredients
                Set<Ingredient> ingredients = new HashSet<>();


                for (String libIngredient : ingredientsList) {
                    transaction.begin();
                    TypedQuery<Ingredient> ingredientQuery = manager.createQuery("select i from Ingredient i  where i" +
                            ".libelle = :libelle ", Ingredient.class);
                    ingredientQuery.setParameter("libelle", libIngredient);
                    Ingredient ingredient = new Ingredient(libIngredient);
                    try {// Si le resultat de la query n 'est pas vide,
                        // l'ingredient existe et on le récupère de la base.
                        Ingredient ingredientbase = ingredientQuery.getSingleResult();
                        ingredient = ingredientbase;
                    }catch (NoResultException e) {//// Si le resulat de la query ci dessus est vide, une exception est
                        // levée, dans ce cas l'ingredient n'existe pas en base on , l'insère.
                        //System.out.println(libIngredient + " / " +  libIngredient.length());
                        manager.persist(ingredient);
                    }


                    ingredients.add(ingredient);//dans tous les cas on l'ajoute à la lite des ingredients du produit
                    transaction.commit();
                }

                //Creation allergenes
                Set<Allergene> allergenes = new HashSet<>();


                for (String libAllergene : allergenesList) {
                    transaction.begin();
                    TypedQuery<Allergene> allergeneQuery = manager.createQuery("select a from Allergene a where a" +
                            ".libelle = :libelle", Allergene.class);
                    allergeneQuery.setParameter("libelle", libAllergene);
                    Allergene allergene = new Allergene(libAllergene);
                    try {// Si le resultat de la query n 'est pas vide,
                        // l'allergène existe et on le récupère de la base.
                        Allergene allergeneBase = allergeneQuery.getSingleResult();
                        allergene = allergeneBase;
                    }catch (NoResultException e) {//// Si le resulat de la query ci dessus est vide, une exception est
                        // levée, dans ce cas l'allergène n'existe pas en base on , l'insère.
                        manager.persist(allergene);
                    }

                    allergenes.add(allergene);// Dans tous les cas on l'ajoute à la liste des allergènes du produit
                    transaction.commit();
                }

                //Création additifs
                Set<Additif> additifs = new HashSet<>();


                for (String libAdditif : additifsList) {
                    transaction.begin();
                    TypedQuery<Additif> additifQuery = manager.createQuery("select a from Additif a where a" +
                            ".description = :description", Additif.class);
                    additifQuery.setParameter("description", libAdditif);
                    Additif additif = new Additif(libAdditif);
                    try {// Si le resultat de la query n 'est pas vide,
                        // l'additif existe et on le récupère de la base.
                        Additif additifBase = additifQuery.getSingleResult();
                        additif = additifBase;
                    }catch (NoResultException e) {//// Si le resulat de la query ci dessus est vide, une exception est
                        // levée, dans ce cas l'additif n'existe pas en base on , l'insère.
                        manager.persist(additif);
                    }
                    additifs.add(additif);// Dans tous les cas on l'ajoute à la liste des allergènese du produit
                    transaction.commit();
                }

                //Création produit
                transaction.begin();
                Produit produit = new Produit(nomProduit, categorie, marque, scoreNutritionnel, energie100g,
                        graisse100g, sucres100g, fibres100g, proteines100g, sel100g, ingredients, allergenes, additifs);
                TypedQuery<Produit> produitQuery = manager.createQuery("select p from Produit p where p" +
                        ".nom = :nom", Produit.class);
                produitQuery.setParameter("nom", nomProduit);

                //try {// Si le resultat de la query n 'est pas vide,
                    // le Produit existe et on le récupère de la base.
                   // Produit produitBase = produitQuery.getSingleResult();
                   // produit = produitBase;
                //}catch (NoResultException e) {//// Si le resulat de la query ci dessus est vide, une exception est
                    // levée, dans ce cas le Produit n'existe pas en base on , l'insère.
                    manager.persist(produit);
                //}
                transaction.commit();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
       /*String myLine = "Plats prÃ©parÃ©s Ã  la viande|picard|2 brochettes royal grill volaille tikka|a|Morceau de " +
               "filet de dinde66.4%, poivron vert en cube 15.1%, eau, tomate cerise entiÃ¨re 6%, crÃ¨me liquide, " +
               "fÃ©cule de pomme de terre, marinade tikka en poudre l, % [maltodextrine de blÃ©, lait en poudre, sel," +
               " Ã©pices et extrait dâ€™Ã©pice, tomate en poudre, ail en poudre, farine de blÃ©, arÃ´me naturel, " +
               "persil dÃ©shydratÃ©, colorant : extrait de paprika], sel, mÃ©lange dâ€™Ã©pices et aromates 0.2% " +
               "[Ã©pices et extrait dâ€™Ã©pices, dextrose de blÃ©, aromates, colorant: extrait de paprika], " +
               "protÃ©ines de pois, colorants: caramel ordinaire, paprika.|329|1|1.3|0.8|16" +
               ".3|1|||||||||||||||||0|en:gluten,en:milk,en:mustard|E150a - Caramel E150a,E160c - Extrait de paprika," +
               "E407 - CarraghÃ©nanes|";
        String[] tab = myLine.split("\\|", -1);
        for (String s : tab) {
            System.out.println(s);
        }

        System.out.println("**************Mon porduit***********");
        String libelleCategorie = tab[0];
        System.out.println("Libelle catégorie : " + libelleCategorie);
        String libelleMarque = tab[1];
        System.out.println("Libelle marque : " + libelleMarque);
        String nomProduit = tab[2];
        System.out.println("Nom produit : " + nomProduit);
        char scoreNutritionnel = tab[3].charAt(0);
        System.out.println("Score nutritionnel :" + scoreNutritionnel);
        String[] ingredientsList = tab[4].split(",");
        System.out.println("************Liste ingrédients*************");
        for (String s : ingredientsList) {
            System.out.println(s);
        }
        String energie100g = tab[5];
        System.out.println("Energie pour 100g : " + energie100g + " taille : " + energie100g.length());
        String graisse100g = tab[6];
        System.out.println("Graisse pour 100g : " + graisse100g);
        String sucres100g = tab[7];
        System.out.println("Sucres pour 100g : " + sucres100g);
        String fibres100g = tab[8];
        System.out.println("Fibres pour 100g : " + fibres100g);
        String proteines100g = tab[9];
        System.out.println("Protéines pour 100g : " + proteines100g);
        String sel100g = tab[10];
        System.out.println("Sel pour 100g : " + sel100g);
        String[] allergenesList = tab[28].split(",");
        String[] additifsList = tab[29].split(",");
        System.out.println("*****************Liste allergnes**************");
        for (String s : allergenesList) {
            System.out.println(s);
        }
        System.out.println("***********Liste additifs****************");
        for (String s : additifsList) {
            System.out.println(s);
        }*/


        manager.close();
        factory.close();
    }
}
