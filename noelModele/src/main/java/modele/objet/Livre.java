package modele.objet;

import java.time.LocalDate;

public class Livre extends Cadeau {
    private String isbn;
    private int nbPages;
    private String editeur;
    private LocalDate edition;

    public Livre(String libelle, String isbn, int nbPages, String editeur, LocalDate edition) {
        super(libelle);
        this.isbn = isbn;
        this.nbPages = nbPages;
        this.editeur = editeur;
        this.edition = edition;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getNbPages() {
        return nbPages;
    }

    public void setNbPages(int nbPages) {
        this.nbPages = nbPages;
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public LocalDate getEdition() {
        return edition;
    }

    public void setEdition(LocalDate edition) {
        this.edition = edition;
    }
}
