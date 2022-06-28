import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Formulaire implements Serializable {

    private List<Produit> produits = new ArrayList<>();

    public Formulaire() {
    }

    public Formulaire(Object[] objects){
        for(Object o:objects){
            produits.add((Produit) o);
        }
    }

    public Formulaire(List<Produit> produits) {
        this.produits = produits;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }
}
