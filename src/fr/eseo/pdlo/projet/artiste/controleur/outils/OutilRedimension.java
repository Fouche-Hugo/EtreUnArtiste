package fr.eseo.pdlo.projet.artiste.controleur.outils;

import java.awt.event.MouseEvent;

import fr.eseo.pdlo.projet.artiste.modele.Coordonnees;
import fr.eseo.pdlo.projet.artiste.modele.formes.Forme;
import fr.eseo.pdlo.projet.artiste.modele.formes.Ligne;
import fr.eseo.pdlo.projet.artiste.modele.formes.Rectangle;
import fr.eseo.pdlo.projet.artiste.vue.formes.VueForme;

public class OutilRedimension extends Outil {

private VueForme vueForme;

    private double largeurInit;
    private double hauteurInit;

    public OutilRedimension(VueForme vueForme) {
        this.vueForme = vueForme;
    }

    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        this.largeurInit = this.vueForme.getForme().getLargeur();
        this.hauteurInit = this.vueForme.getForme().getHauteur();
    }

    public void mouseDragged(MouseEvent e) {
        Ligne ligneTemp = new Ligne(this.vueForme.getForme().getPosition(), this.largeurInit, this.hauteurInit);
        Coordonnees coordsInit = new Coordonnees(ligneTemp.getCadreMinX(), ligneTemp.getCadreMinY());
        double largeur = ligneTemp.getCadreMaxX() - ligneTemp.getCadreMinX();
        double hauteur = ligneTemp.getCadreMaxY() - ligneTemp.getCadreMinY();
        Forme formeTemp = new Rectangle(coordsInit, largeur, hauteur);
        if(formeTemp.contient(this.getDebut())) {
            Coordonnees coordsActuelles = new Coordonnees(e.getX(), e.getY());

            double ecartX = coordsActuelles.getAbscisse() - this.getDebut().getAbscisse();
            double ecartY = coordsActuelles.getOrdonnee() - this.getDebut().getOrdonnee();

            double nouvelleLargeur = this.largeurInit + ecartX;
            if(nouvelleLargeur > 0 || this.vueForme.getForme().getClass().getSimpleName().equals("Ligne")) {
                this.vueForme.getForme().setLargeur(nouvelleLargeur);
            }

            double nouvelleHauteur = this.hauteurInit + ecartY;
            if(nouvelleHauteur > 0 || this.vueForme.getForme().getClass().getSimpleName().equals("Ligne")) {
                this.vueForme.getForme().setHauteur(nouvelleHauteur);
            }

            this.getPanneauDessin().repaint();
        }
    }

    public void mouseReleased(MouseEvent e) {
        Ligne ligneTemp = new Ligne(this.vueForme.getForme().getPosition(), this.largeurInit, this.hauteurInit);
        Coordonnees coordsInit = new Coordonnees(ligneTemp.getCadreMinX(), ligneTemp.getCadreMinY());
        double largeur = ligneTemp.getCadreMaxX() - ligneTemp.getCadreMinX();
        double hauteur = ligneTemp.getCadreMaxY() - ligneTemp.getCadreMinY();
        Forme formeTemp = new Rectangle(coordsInit, largeur, hauteur);
        if(formeTemp.contient(this.getDebut())) {
            Coordonnees coordsActuelles = new Coordonnees(e.getX(), e.getY());

            double ecartX = coordsActuelles.getAbscisse() - this.getDebut().getAbscisse();
            double ecartY = coordsActuelles.getOrdonnee() - this.getDebut().getOrdonnee();

            double nouvelleLargeur = this.vueForme.getForme().getLargeur() + ecartX;
            if(nouvelleLargeur < 0 && !this.vueForme.getForme().getClass().getSimpleName().equals("Ligne")) {
                this.vueForme.getForme().setLargeur(this.largeurInit);
            }

            double nouvelleHauteur = this.vueForme.getForme().getHauteur() + ecartY;
            if(nouvelleHauteur < 0 && !this.vueForme.getForme().getClass().getSimpleName().equals("Ligne")) {
                this.vueForme.getForme().setHauteur(this.hauteurInit);
            }

            this.getPanneauDessin().repaint();
        }
    }
}
