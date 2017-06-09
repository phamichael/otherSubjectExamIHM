package modele.objet;

import modele.personnes.Enfant;
import modele.personnes.Renne;

import java.util.Collection;

public class Traineau {
    private Renne renne;
    private Collection<Enfant> tournee;

    public Renne getRenne() {
        return renne;
    }

    public void setRenne(Renne renne) {
        this.renne = renne;
    }

    public Collection<Enfant> getTournee() {
        return tournee;
    }

    public void setTournee(Collection<Enfant> tournee) {
        this.tournee = tournee;
    }
}
