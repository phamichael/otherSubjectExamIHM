package facade;

import facade.erreurs.IndividuNonConnecteException;
import facade.erreurs.UtilisateurDejaExistantException;
import facade.securite.HasRole;
import modele.personnes.Lutin;
import modele.personnes.Personne;

import java.util.Collection;

/**
 *  Service de gestion des lutins : creation, suppression, ajout de rôles
 */
public interface GestionLutinsService {
    @HasRole("PERENOEL")
    Personne creerUtilisateur(String cleAcces, String nom, String mdp) throws IndividuNonConnecteException,UtilisateurDejaExistantException;

    @HasRole("PERENOEL")
    public void associerRoleUtilisateur(String cleAcces, long utilisateurConcerne, String role) throws IndividuNonConnecteException;

    @HasRole("PERENOEL")
    public Collection<Lutin> getListeUtilisateur(String cleAcces)throws IndividuNonConnecteException;

    @HasRole("PERENOEL")
    Lutin getUserById(String cleAcces, long identifiant1);

    @HasRole("PERENOEL")
    void supprimerRoleUtilisateur(String cleAcces, long identifiant1, String role);

    @HasRole("PERENOEL")
    void changerMotDePasseUtilisateur(String cleAcces, long identifiant1, String mdp);

    @HasRole("PERENOEL")
    void supprimerUtilisateur(String cleAcces, long idUtilisateur);
}
