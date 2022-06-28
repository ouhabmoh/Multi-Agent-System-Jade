import java.io.Serializable;
import java.util.Objects;

public class Produit implements Serializable {
    private String nom;
    private int qte;
    private int prix;
    private String nomMagasin;


    public Produit(String nom, int qte, int prix, String nomMagasin) {
        this.nom = nom;
        this.qte = qte;
        this.prix = prix;
        this.nomMagasin = nomMagasin;
    }

    public Produit(String nom, int qte, int prix) {
        this.nom = nom;
        this.qte = qte;
        this.prix = prix;
    }

    public Produit(String nom, int qte) {
        this.nom = nom;
        this.qte = qte;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getNomMagasin() {
        return nomMagasin;
    }

    public void setNomMagasin(String nomMagasin) {
        this.nomMagasin = nomMagasin;
    }

    public void updateQte(int qte){
        this.qte -= qte;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produit produit = (Produit) o;
        return Objects.equals(nom, produit.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, qte);
    }
}
