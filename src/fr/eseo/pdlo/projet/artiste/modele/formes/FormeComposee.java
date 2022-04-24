package fr.eseo.pdlo.projet.artiste.modele.formes;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.awt.Color;

import fr.eseo.pdlo.projet.artiste.modele.Coordonnees;
import fr.eseo.pdlo.projet.artiste.modele.Remplissable;
import fr.eseo.pdlo.projet.artiste.modele.Remplissage;

public class FormeComposee extends Forme implements Remplissable {
    
    private ArrayList<Forme> formes;

    public FormeComposee(Forme[] formes) {
        super(new Coordonnees(), 0, 0);
        this.formes = new ArrayList<Forme>();
        for(Forme f : formes) {
            this.ajouterForme(f);
        }
    }

    public FormeComposee() {
        super(new Coordonnees(), 0, 0);
        this.formes = new ArrayList<Forme>();
    }

    public Forme[] getFormes() {
        Forme[] tab = new Forme[this.formes.size()];
        this.formes.toArray(tab);
        return tab;
    }

    public void ajouterForme(Forme f) {
        this.formes.add(f);
        if(this.formes.size() == 1) {
            super.deplacerVers(f.getPosition());
            super.setLargeur(f.getLargeur());
            super.setHauteur(f.getHauteur());
        } else {
            if(this.getPosition().getAbscisse() > f.getPosition().getAbscisse()) {
                super.setLargeur(this.getLargeur() + Math.abs(this.getPosition().getAbscisse() - f.getPosition().getAbscisse()));
                super.getPosition().setAbscisse(f.getPosition().getAbscisse());
            }
            if(this.getPosition().getOrdonnee() > f.getPosition().getOrdonnee()) {
                super.setHauteur(this.getHauteur() + Math.abs(this.getPosition().getOrdonnee() - f.getPosition().getOrdonnee()));
                super.getPosition().setOrdonnee(f.getPosition().getOrdonnee());
            }
            if(this.getPosition().getAbscisse() + this.getLargeur() < f.getPosition().getAbscisse() + f.getLargeur()) {
                super.setLargeur(f.getPosition().getAbscisse() + f.getLargeur() - this.getPosition().getAbscisse());
            }
            if(this.getPosition().getOrdonnee() + this.getHauteur() < f.getPosition().getOrdonnee() + f.getHauteur()) {
                super.setHauteur(f.getPosition().getOrdonnee() + f.getHauteur() - this.getPosition().getOrdonnee());
            }
        }
    }

    @Override
    public void deplacerDe(double x, double y) {
        super.deplacerDe(x, y);
        for(Forme f : this.formes) {
            f.deplacerDe(x, y);
        }
    }

    @Override
    public void deplacerVers(double x, double y) {
        double deltaX = x - this.getPosition().getAbscisse();
        double deltaY = y - this.getPosition().getOrdonnee();

        this.deplacerDe(deltaX, deltaY);
    }

    @Override
    public void deplacerVers(Coordonnees coords) {
        this.deplacerVers(coords.getAbscisse(), coords.getOrdonnee());
    }

    @Override
    public void setPosition(Coordonnees pos) {
        this.deplacerVers(pos);
    }

    @Override
    public void setAbscisse(double x) {
        this.deplacerVers(x, this.getPosition().getOrdonnee());
    }

    @Override
    public void setOrdonnee(double y) {
        this.deplacerVers(this.getPosition().getAbscisse(), y);
    }

    @Override
    public void setLargeur(double nouvelleLargeur) {
        double ratio = nouvelleLargeur / this.getLargeur();
        for(Forme f : this.formes) {
            f.getPosition().setAbscisse(this.getPosition().getAbscisse() + ratio * (f.getPosition().getAbscisse() - this.getPosition().getAbscisse()));
            f.setLargeur(f.getLargeur() * ratio);
        }
        super.setLargeur(nouvelleLargeur);
    }

    @Override
    public void setHauteur(double nouvelleHauteur) {
        double ratio = nouvelleHauteur / this.getHauteur();
        for(Forme f : this.formes) {
            f.getPosition().setOrdonnee(this.getPosition().getOrdonnee() + ratio * (f.getPosition().getOrdonnee() - this.getPosition().getOrdonnee()));
            f.setHauteur(f.getHauteur() * ratio);
        }
        super.setHauteur(nouvelleHauteur);
    }

    @Override
    public void setCouleur(Color couleur) {
        super.setCouleur(couleur);
        for(Forme f : this.formes) {
            f.setCouleur(couleur);
        }
    }

    @Override
    public void setCouleurSecondaire(Color couleur) {
        super.setCouleurSecondaire(couleur);
        for(Forme f : this.formes) {
            f.setCouleurSecondaire(couleur);
        }
    }

    @Override
    public void setRemplissage(Remplissage remplissage) {
        for(Forme f : this.formes) {
            if(f instanceof Remplissable) {
                ((Remplissable)f).setRemplissage(remplissage);
            }
        }
    }

    @Override
    public Remplissage getRemplissage() {
        return Remplissage.AUCUNE;
    }

    @Override
    public void setAngle(double angle) {
        double deltaAngle = angle - this.getAngle();
        for(Forme f : this.formes) {
            double nouvelAngle = f.getAngle() + deltaAngle;
            
            Coordonnees centreFormeComposee = new Coordonnees(this.getPosition().getAbscisse() + this.getLargeur() / 2,
                this.getPosition().getOrdonnee() + this.getHauteur() / 2);
            Coordonnees coordsFRelatives = new Coordonnees(f.getAbscisse() - centreFormeComposee.getAbscisse(),
                f.getOrdonnee() - centreFormeComposee.getOrdonnee());
            
            //On applique une matrice de rotation au centre de la forme
            double nouvelleAbscisseRelative = coordsFRelatives.getAbscisse() * Math.cos(deltaAngle)
                - coordsFRelatives.getOrdonnee() * Math.sin(deltaAngle);
            double nouvelleOrdonneeRelative = coordsFRelatives.getAbscisse() * Math.sin(deltaAngle)
                + coordsFRelatives.getOrdonnee() * Math.cos(deltaAngle);

            f.getPosition().setAbscisse(centreFormeComposee.getAbscisse() + nouvelleAbscisseRelative);
            f.getPosition().setOrdonnee(centreFormeComposee.getOrdonnee() + nouvelleOrdonneeRelative);

            f.setAngle(nouvelAngle);
        }
        super.setAngle(angle);
    }

    @Override
    public double aire() {
        return 0;
    }

    @Override
    public double perimetre() {
        return 0;
    }

    @Override
    public boolean contient(Coordonnees coords) {
        for(Forme f : this.formes) {
            if(f.contient(coords)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat();
		df.setMinimumFractionDigits(1);
		df.setMaximumFractionDigits(2);
		df.setGroupingSize(8);
		String msg = "[" + super.getClass().getSimpleName()
				+ "] : pos " + this.getPosition().toString() + " largeur " + df.format(this.getLargeur())
                + " hauteur " + df.format(this.getHauteur());
		msg += " nombre de formes : " + this.formes.size();
        return msg;
    }

}
