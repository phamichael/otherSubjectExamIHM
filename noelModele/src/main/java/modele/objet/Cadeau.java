package modele.objet;

public abstract class Cadeau {
    private long id;
    private String libelle;

    public static String[] TYPES_CADEAUX = new String[]{"LIVRE","JOUET"};

    private static long idCadeau = 0L;

    public Cadeau(String libelle) {
        this.id = ++idCadeau;
        this.libelle = libelle;
    }

    public long getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
