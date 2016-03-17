
package entities;

import java.sql.Date;
import java.util.Objects;


public class Cours {
    private int id;
    private String titre;
    private String description;
    private Date datedebut;
    private Date datefin;
    private String difficulte;
    private String etat;
    private Categories categorie;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public String getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(String difficulte) {
        this.difficulte = difficulte;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Categories getCategorie() {
        return categorie;
    }

    public void setCategorie(Categories categorie) {
        this.categorie = categorie;
    }

    public Cours(int id, String titre, String description, Date datedebut, Date datefin, String difficulte, String etat, Categories categorie) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.difficulte = difficulte;
        this.etat = etat;
        this.categorie = categorie;
    }

    public Cours(int id, String titre, String description, Date datedebut, Date datefin, String difficulte, Categories categorie) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.difficulte = difficulte;
        this.categorie = categorie;
    }

    public Cours(int id, String titre, String description, Categories categorie) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.categorie = categorie;
    }

    public Cours() {
    }

    @Override
    public String toString() {
        return "Cours{" + "id=" + id + ", titre=" + titre + ", description=" + description + ", datedebut=" + datedebut + ", datefin=" + datefin + ", difficulte=" + difficulte + ", etat=" + etat + ", categorie=" + categorie + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.id;
        hash = 47 * hash + Objects.hashCode(this.titre);
        hash = 47 * hash + Objects.hashCode(this.description);
        hash = 47 * hash + Objects.hashCode(this.datedebut);
        hash = 47 * hash + Objects.hashCode(this.datefin);
        hash = 47 * hash + Objects.hashCode(this.difficulte);
        hash = 47 * hash + Objects.hashCode(this.etat);
        hash = 47 * hash + Objects.hashCode(this.categorie);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cours other = (Cours) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.titre, other.titre)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.datedebut, other.datedebut)) {
            return false;
        }
        if (!Objects.equals(this.datefin, other.datefin)) {
            return false;
        }
        if (!Objects.equals(this.difficulte, other.difficulte)) {
            return false;
        }
        if (!Objects.equals(this.etat, other.etat)) {
            return false;
        }
        if (!Objects.equals(this.categorie, other.categorie)) {
            return false;
        }
        return true;
    }

    
    
    
}
