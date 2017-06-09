package modele.personnes;

public class Renne extends Lutin { // ben oui les rennes sont [presque] des lutins comme les autres !

    // la capacite de chargement en cadeaux
    private int capacite;

    public Renne(String nom, String mdp, int capacite) {
        super(nom, mdp);
        this.capacite = capacite;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    @Override
    public boolean hasRole(String role) {
        return "RENNE".equals(role);
    }

    @Override
    public void addRole(String role) {

    }

    @Override
    public void removeRole(String role) {

    }
}
