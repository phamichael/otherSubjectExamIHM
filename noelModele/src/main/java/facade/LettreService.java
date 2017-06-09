package facade;

import facade.securite.HasRole;
import modele.personnes.Enfant;
import modele.objet.Cadeau;
import java.time.LocalDate;
import java.util.Collection;

/**
 *  Service de traitement des courriers des enfants
 */
public interface LettreService {
    /**
     *  Enregistre un Enfant dans la base
     *
     * @param cleAcces : clé d'accès du lutin demandant l'enregistrement [doit avoir le rôle BONNETVERT]
     * @param nom : le nom de l'enfant
     * @param prenom : le prénom de l'enfant
     * @param adresse : adresse de l'enfant
     * @param codePostal : code postal
     * @param ville : ville
     * @param naissance : date de naissance de l'enfant
     * @return l'identifiant de l'enfant
     */
    @HasRole("BONNETVERT")
    public long enregistrerNouvelEnfant(String cleAcces, String nom, String prenom, String adresse, int codePostal, String ville, LocalDate naissance);

    /**
     *  Recherche un Enfant dans la base par son nom
     *
     * @param nom : le nom de l'enfant recherché
     * @return la collection des enfants qui ont le nom recherché
     */
    public Collection<Enfant> rechercherEnfantParNom(String nom);

    /**
     *  Recherche un Enfant dans la base par son identifiant
     *
     * @param idEnfant : l'identifiant de l'enfant recherché
     * @return l'enfant qui a cet identifiant
     */
    public Enfant rechercherEnfantParId(long idEnfant);

    /**
     *  Enregistre la liste des cadeaux demandés par un Enfant dans sa lettre
     *
     * @param cleAcces : clé d'accès du lutin demandant l'enregistrement [doit avoir le rôle BONNETVERT]
     * @param idEnfant : l'identifiant de l'enfant
     * @param cadeaux : la liste des libellés des cadeaux demandés
     */
    @HasRole("BONNETVERT")
    public void enregistrerDemandes(String cleAcces, long idEnfant, String... cadeaux);

    /**
     *  Fixe si un enfant a été sage ou non
     *
     * @param cleAcces : clé d'accès du lutin demandant l'enregistrement [doit avoir le rôle PERENOEL]
     * @param idEnfant : l'identifiant de l'enfant
     * @param sage : true si l'enfant a été sage et aura les cadeaux demandés, false sinon
     */
    @HasRole("PERENOEL")
    public void setEnfantSage(String cleAcces, long idEnfant, boolean sage);

    /**
     *  Renvoie la liste des enfants dont la décision du père noël (sage/pas sage) n'est pas enregistrée
     *
     * @param cleAcces : clé d'accès du lutin demandant la liste [doit avoir le rôle PERENOEL]
     */
    @HasRole("PERENOEL")
    public Collection<Enfant> listeEnfantsSansReponse(String cleAcces);

    /**
     *
     * @return la liste des enfants sages non encore livrés.
     */
    public Collection<Enfant> getListeDesCommandesPretesPourTraitement();

}
