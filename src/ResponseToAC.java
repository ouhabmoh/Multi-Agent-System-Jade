import jade.core.AID;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResponseToAC implements Serializable {
    private List<Produit> availableProducts = new ArrayList<>();
    private double redaction;

    private double prixTotale;

    private AID aid;

    public ResponseToAC(List<Produit> availableProducts) {
        this.availableProducts = availableProducts;
    }

    public ResponseToAC(List<Produit> availableProducts, double redaction) {
        this.availableProducts = availableProducts;
        this.redaction = redaction;
    }



    public ResponseToAC() {

    }

    public double getPrixTotale() {
        return prixTotale;
    }

    public void setPrixTotale(double prixTotale) {
        this.prixTotale = prixTotale;
    }

    public AID getAid() {
        return aid;
    }

    public void setAid(AID aid) {
        this.aid = aid;
    }

    public List<Produit> getAvailableProducts() {
        return availableProducts;
    }

    public void setAvailableProducts(List<Produit> availableProducts) {
        this.availableProducts = availableProducts;
    }

    public double getRedaction() {
        return redaction;
    }

    public void setRedaction(double redaction) {
        this.redaction = redaction;
    }

    public double calculetPrixTotale(){
        for(Produit produit:availableProducts){
            prixTotale += produit.getPrix()*produit.getQte();
        }
        prixTotale -= prixTotale*redaction;
        return prixTotale;
    }
}
