package facade;

import facade.securite.HasRole;
import modele.objet.Cadeau;

import java.time.LocalDate;
import java.util.Collection;

public interface CatalogueService {
    @HasRole("BONNETROUGE")
    public long ajouterJouet(String cleAcces, String libelle, String marque);
    @HasRole("BONNETROUGE")
    public long ajouterLivre(String cleAcces, String libelle, String isbn, int nbPages, String editeur, LocalDate edition);

    public Cadeau getCadeauParId(long idCadeau);
    public Cadeau rechercherCadeauParNom(String nom);

    public Collection<Cadeau> getCadeaux();

    @HasRole("BONNETROUGE")
    public void supprimerCadeau(String cleAcces, long id);

    /**
     * @return un tableau des types de produits gérés dans le catalogue
     */
    public String[] getTypeCadeaux();

}
