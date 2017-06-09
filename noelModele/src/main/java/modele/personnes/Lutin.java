package modele.personnes;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Lutin extends Personne {

    static long ids = 1L;
    String mdp;
    Set<String> mesRoles;


    Lutin(long id, String nom, String mdp) {
        super(id,nom);
        this.mdp = mdp;
        mesRoles = new HashSet<>();

    }

    public Lutin(String nom, String mdp) {
        this(ids++,nom,mdp);
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void addRole(String role) { mesRoles.add(role);}

    public boolean hasRole(String role) {
        return mesRoles.contains(role);
    }

    public Collection<String> getRoles() {
        return mesRoles;
    }

    public void removeRole(String role) {
        mesRoles.remove(role);
    }
}
