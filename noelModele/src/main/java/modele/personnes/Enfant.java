package modele.personnes;

import modele.objet.Cadeau;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Enfant extends Personne {
    String prenom;
    String adresse;
    int codePostal;
    String ville;
    LocalDate naissance;
    private List<Cadeau> demandes;
    private Boolean sage;


    private static long ids = 1L;

    Enfant(long id, String nom, String prenom, String adresse, int codePostal, String ville, LocalDate naissance) {
        super(id, nom);
        this.prenom = prenom;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
        this.naissance = naissance;
        demandes = new ArrayList<>();
        sage = null;
    }

    public Enfant(String nom, String prenom, String adresse, int codePostal, String ville, LocalDate naissance) {
        this(ids++, nom, prenom, adresse, codePostal, ville, naissance);
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public LocalDate getNaissance() {
        return naissance;
    }

    public void setNaissance(LocalDate naissance) {
        this.naissance = naissance;
    }

    public List<Cadeau> getDemandes() {
        return demandes;
    }

    public void addDemandes(List<Cadeau> cadeaux) {
        this.demandes.addAll(cadeaux);
    }

    public void addDemandes(Cadeau cadeau) {
        demandes.add(cadeau);
    }

    public boolean isSageSet() {
        return sage != null;
    }

    public void setSage(boolean sage) {
        this.sage = sage;
    }

    public Boolean getSage() {
        return sage;
    }

    public void setDemandes(List<Cadeau> demandes) {
        this.demandes = demandes;
    }
}