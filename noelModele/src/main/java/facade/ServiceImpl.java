package facade;


import facade.erreurs.CoupleUtilisateurMDPInconnuException;
import facade.erreurs.IndividuNonConnecteException;
import facade.erreurs.UtilisateurDejaExistantException;
import modele.objet.Traineau;
import modele.personnes.*;
import modele.objet.Jouet;
import modele.objet.Cadeau;
import modele.objet.Livre;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static facade.securite.AccessControl.checkAnnotationAccess;

public class ServiceImpl implements LettreService, GestionLutinsService, ConnexionService, CatalogueService {
    private static Map<String,Cadeau> catalogue = new HashMap<>();
    private static Map<Long,Enfant> enfants = new HashMap<>();
    private static Map<Long,Lutin> utilisateurs = new HashMap<>();
    private static List<Enfant> aDistribuer = new ArrayList<>();

    private static Map<String,Lutin> connectes = new HashMap<>();

    public static String PERENOEL = "PERENOEL";
    public static String BONNETVERT = "BONNETVERT";
    public static String BONNETROUGE = "BONNETROUGE";



    public ServiceImpl() {
        Lutin admin = new Lutin("noel","noel");
        admin.addRole(PERENOEL);
        admin.addRole(BONNETVERT);
        admin.addRole(BONNETROUGE);
        utilisateurs.put(admin.getIdentifiant(),admin);

        String cle = "";
        try {
            cle = connexion("noel","noel");
        } catch (CoupleUtilisateurMDPInconnuException e) {
            e.printStackTrace();
        }

        Lutin lutinVert = new Lutin("lutinvert","lutinvert");
        lutinVert.addRole(BONNETVERT);

        Lutin lutinRouge = new Lutin("lutinrouge","lutinrouge");
        lutinRouge.addRole(BONNETROUGE);

        utilisateurs.put(admin.getIdentifiant(),admin);
        utilisateurs.put(lutinVert.getIdentifiant(),lutinVert);
        utilisateurs.put(lutinRouge.getIdentifiant(),lutinRouge);

        // add enfants
        long yo = enregistrerNouvelEnfant(cle,"boichut","yohan","orleans",45100,"ORLEANS",LocalDate.now());
        long fred = enregistrerNouvelEnfant(cle,"moal","frederic","orleans",45100,"ORLEANS",LocalDate.now());

        ajouterLivre(cle,"JavaFX", "123456-45", 512, "Oracle press", LocalDate.parse("2014-01-03"));
        ajouterLivre(cle,"JavaFX2", "123456-33", 542, "Oracle press", LocalDate.parse("2016-01-03"));
        ajouterJouet(cle, "htc one", "HTC");
        ajouterJouet(cle, "iphone 7","Apple");

        enregistrerDemandes(cle, fred,"htc one", "JavaFX2");
        enregistrerDemandes(cle, yo,"iphone 7");

        deconnexion(cle);
    }



    @Override
    public long ajouterLivre(String cleAcces, String libelle, String isbn, int nbPages, String editeur, LocalDate edition) {
        if (connectes.containsKey(cleAcces)) {
            checkAnnotationAccess(new Object() {
            }.getClass().getEnclosingMethod(), connectes.get(cleAcces));
            Livre nouveauLivre = new Livre(libelle, isbn, nbPages, editeur, edition);

            catalogue.put(nouveauLivre.getLibelle(), nouveauLivre);

            return nouveauLivre.getId();
        }
        else {
            throw new IndividuNonConnecteException();
        }
    }

    @Override
    public Lutin getLutinConnecte(String cleAcces) {
        return connectes.get(cleAcces);
    }

    @Override
    public long ajouterJouet(String cleAcces, String libelle, String marque) {
        if (connectes.containsKey(cleAcces)) {
            checkAnnotationAccess(new Object() {
            }.getClass().getEnclosingMethod(), connectes.get(cleAcces));
            Jouet nouveauJouet = new Jouet(libelle, marque);

            catalogue.put(nouveauJouet.getLibelle(), nouveauJouet);
            return nouveauJouet.getId();
        }
        else {
            throw new IndividuNonConnecteException();
        }
    }

    @Override
    public long enregistrerNouvelEnfant(String cleAcces, String nom, String prenom, String adresse, int codePostal, String ville, LocalDate naissance) {
        if (connectes.containsKey(cleAcces)) {
            checkAnnotationAccess(new Object() {
            }.getClass().getEnclosingMethod(), connectes.get(cleAcces));
            Enfant enfant = new Enfant(nom,prenom,adresse,codePostal,ville,naissance);
            enfants.put(enfant.getIdentifiant(), enfant);
            return enfant.getIdentifiant();
        }
        else {
            throw new IndividuNonConnecteException();
        }
    }

    @Override
    public Collection<Enfant> rechercherEnfantParNom(String nom) {
        return enfants.values().stream().filter(client -> client.getNom().contains(nom)).collect(Collectors.toList());
    }

    @Override
    public Enfant rechercherEnfantParId(long idEnfant) {
        return enfants.get(idEnfant);
    }

    @Override
    public Personne creerUtilisateur(String cleAcces, String nom, String mdp) throws IndividuNonConnecteException,UtilisateurDejaExistantException {
        if (connectes.containsKey(cleAcces)) {
            checkAnnotationAccess(new Object() {
            }.getClass().getEnclosingMethod(), connectes.get(cleAcces));

            long existe = utilisateurs.values().stream().filter(u -> u.getNom().equals(nom)).count();
            if (existe>0) throw new UtilisateurDejaExistantException();

            Lutin nouvelLutin = new Lutin(nom, mdp);
            utilisateurs.put(nouvelLutin.getIdentifiant(), nouvelLutin);
            return nouvelLutin;
        }
        else {
            throw new IndividuNonConnecteException();
        }

    }

    @Override
    public Cadeau rechercherCadeauParNom(String nom) {
        return catalogue.get(nom);
    }

    @Override
    public void associerRoleUtilisateur(String cleAcces, long utilisateurConcerne, String role) {
        if (connectes.containsKey(cleAcces)) {
            checkAnnotationAccess(new Object() {
            }.getClass().getEnclosingMethod(), connectes.get(cleAcces));
            Lutin user = utilisateurs.get(utilisateurConcerne);
            user.addRole(role);
        }
        else {
            throw new IndividuNonConnecteException();
        }


    }

    @Override
    public Collection<Lutin> getListeUtilisateur(String cleAcces) {
        if (connectes.containsKey(cleAcces)) {
            checkAnnotationAccess(new Object() {
            }.getClass().getEnclosingMethod(), connectes.get(cleAcces));
            return utilisateurs.values();
        }
        else {
            throw new IndividuNonConnecteException();
        }
    }

    @Override
    public Lutin getUserById(String cleAcces, long identifiant1) {
        if (connectes.containsKey(cleAcces)) {
            checkAnnotationAccess(new Object() {
            }.getClass().getEnclosingMethod(), connectes.get(cleAcces));
            return utilisateurs.get(identifiant1);
        }
        else {
            throw new IndividuNonConnecteException();
        }
    }

    @Override
    public void supprimerRoleUtilisateur(String cleAcces, long identifiant1, String role) {
        if (connectes.containsKey(cleAcces)) {
            checkAnnotationAccess(new Object() {
            }.getClass().getEnclosingMethod(), connectes.get(cleAcces));
            utilisateurs.get(identifiant1).removeRole(role);
        }
        else {
            throw new IndividuNonConnecteException();
        }
    }

    @Override
    public void supprimerUtilisateur(String cleAcces, long idUtilisateur) {
        if (connectes.containsKey(cleAcces)) {
            checkAnnotationAccess(new Object() {
            }.getClass().getEnclosingMethod(), connectes.get(cleAcces));
            utilisateurs.remove(idUtilisateur);
        }
        else {
            throw new IndividuNonConnecteException();
        }
    }

    @Override
    public void changerMotDePasseUtilisateur(String cleAcces, long identifiant1, String mdp) {
        if (connectes.containsKey(cleAcces)) {
            checkAnnotationAccess(new Object() {
            }.getClass().getEnclosingMethod(), connectes.get(cleAcces));
            utilisateurs.get(identifiant1).setMdp(mdp);
        }
        else {
            throw new IndividuNonConnecteException();
        }
    }

    @Override
    public void enregistrerDemandes(String cleAcces, long idClient, String... cadeaux) {
        if (connectes.containsKey(cleAcces)) {
            checkAnnotationAccess(new Object() {
            }.getClass().getEnclosingMethod(), connectes.get(cleAcces));
            Enfant enfant = enfants.get(idClient);
            for(String nomDuCadeau : cadeaux) {
                Cadeau cadeau = catalogue.get(nomDuCadeau);
                enfant.addDemandes(cadeau);
            }
        }
        else {
            throw new IndividuNonConnecteException();
        }
    }

    @Override
    public String connexion(String login, String mdp) throws CoupleUtilisateurMDPInconnuException {
        for (Lutin u : utilisateurs.values()) {
            if ((login.equals(u.getNom())) && (mdp.equals(u.getMdp()))) {
                String cleAcces = UUID.randomUUID().toString();
                connectes.put(cleAcces,u);
                return cleAcces;
            }
        }
        throw new CoupleUtilisateurMDPInconnuException();
    }

    @Override
    public void deconnexion(String cleAcces) {
        if (connectes.containsKey(cleAcces)) {
            connectes.remove(cleAcces);
        } else {
            throw new IndividuNonConnecteException();
        }
    }

    @Override
    public void setEnfantSage(String cleAcces, long idEnfant, boolean sage) {
        if (connectes.containsKey(cleAcces)) {
            checkAnnotationAccess(new Object() {
            }.getClass().getEnclosingMethod(), connectes.get(cleAcces));
            Enfant enfant = enfants.get(idEnfant);
            enfant.setSage(sage);
            aDistribuer.add(enfant);
        }
        else {
            throw new IndividuNonConnecteException();
        }
    }

    @Override
    public Collection<Enfant> listeEnfantsSansReponse(String cleAcces) {
        if (connectes.containsKey(cleAcces)) {
            checkAnnotationAccess(new Object() {
            }.getClass().getEnclosingMethod(), connectes.get(cleAcces));
            return enfants.values().stream().filter(e->!e.isSageSet()).collect(Collectors.toList());
        }
        else {
            throw new IndividuNonConnecteException();
        }
    }

    @Override
    public Collection<Enfant> getListeDesCommandesPretesPourTraitement() {
        return enfants.values().stream().filter(e->(e.isSageSet() && e.getSage())).collect(Collectors.toList());
    }

    @Override
    public Cadeau getCadeauParId(long idCadeau) {
        Collection<Cadeau> cadeaux = catalogue.values();
        for(Cadeau cadeau : cadeaux) {
            if (cadeau.getId()==idCadeau) {
                return cadeau;
            }
        }
        return null;
    }

    @Override
    public Collection<Cadeau> getCadeaux() {
        return catalogue.values();
    }

    @Override
    public String[] getTypeCadeaux() {
        return Cadeau.TYPES_CADEAUX;
    }

    @Override
    public void supprimerCadeau(String cleAcces, long id) {
        if (connectes.containsKey(cleAcces)) {
            checkAnnotationAccess(new Object() {
            }.getClass().getEnclosingMethod(), connectes.get(cleAcces));
            Cadeau cadeau = getCadeauParId(id);
            catalogue.remove(cadeau.getLibelle());
        }
        else {
            throw new IndividuNonConnecteException();
        }
    }
}
