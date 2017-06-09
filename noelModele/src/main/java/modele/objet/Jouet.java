package modele.objet;

public class Jouet extends Cadeau {
    private String marque;

    public Jouet(String libelle, String marque) {
        super(libelle);
        this.marque = marque;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }
}
