package facade;

import facade.erreurs.CoupleUtilisateurMDPInconnuException;
import modele.personnes.Lutin;
import modele.personnes.Personne;

public interface ConnexionService {
    /**
     *  Connexion au service
     * @param login : le login de l'utilisateur
     * @param mdp   : le mot de passe de l'utilisateur
     * @return : la clef d'accès permettant d'appeler les méthodes de la couche de service
     */
    String connexion(String login, String mdp) throws CoupleUtilisateurMDPInconnuException;

    Lutin getLutinConnecte(String cleAcces);

    /**
     *  Deconnexion du service
     */
    public void deconnexion(String cleAcces);
}
