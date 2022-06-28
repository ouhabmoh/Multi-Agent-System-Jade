import Rule.*;

import java.util.ArrayList;
import java.util.List;

public class Manager {
    public static List<Produit> getProducts(int i) {
        List<Produit> produits = new ArrayList<>();
        switch (i) {
            case 0:
                Produit produit0 = new Produit("p1", 1);
                Produit produi = new Produit("p5", 2);

                produits.add(produit0);
                produits.add(produi);


                break;
            case 1:
                Produit produit1 = new Produit("p1", 10, 1000);
                Produit produit2 = new Produit("p2", 8, 500);
                Produit produit3 = new Produit("p3", 15, 3000);
                Produit produit4 = new Produit("p4", 12, 2000);
                produits.add(produit1);
                produits.add(produit2);
                produits.add(produit3);
                produits.add(produit4);

                break;

            case 2:
                Produit produit5 = new Produit("p1", 10, 1000);
                Produit produit6 = new Produit("p2", 8, 500);
                Produit produit7 = new Produit("p5", 15, 3000);
                Produit produit8 = new Produit("p6", 12, 2000);
                produits.add(produit5);
                produits.add(produit6);
                produits.add(produit7);
                produits.add(produit8);

                break;


            case 3:

                Produit produit9 = new Produit("p5", 10, 1000);
                Produit produit10 = new Produit("p6", 8, 500);
                Produit produit11 = new Produit("p7", 15, 3000);
                Produit produit12 = new Produit("p8", 12, 2000);
                produits.add(produit9);
                produits.add(produit10);
                produits.add(produit11);
                produits.add(produit12);

                break;


        }
        return produits;
    }

    public static RB getRB(int i){
        RB rb = null;
        switch (i){
            case 1:
                rb = new Magasin1();
                break;
            case 2:
                rb = new Magasin2();
                break;
            case 3:
                rb = new Magasin3();
                break;
        }
        return rb;
    }
}
