package fr.eseo.pdlo.projet.artiste.controleur.outils;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import fr.eseo.pdlo.projet.artiste.modele.Coordonnees;
import fr.eseo.pdlo.projet.artiste.modele.formes.Forme;
import fr.eseo.pdlo.projet.artiste.modele.formes.Ligne;
import fr.eseo.pdlo.projet.artiste.modele.formes.Rectangle;
import fr.eseo.pdlo.projet.artiste.vue.formes.VueForme;

public class OutilRedimension extends Outil {

    private ArrayList<VueForme> vuesFormesSelectionnees;

    private ArrayList<Double> largeursInit;
    private ArrayList<Double> hauteursInit;

    public OutilRedimension(ArrayList<VueForme> vuesFormesSelectionnees) {
        this.vuesFormesSelectionnees = vuesFormesSelectionnees;
    }

    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        
        this.largeursInit = new ArrayList<Double>();
        this.hauteursInit = new ArrayList<Double>();
        for(VueForme f : this.vuesFormesSelectionnees) {
            this.largeursInit.add(f.getForme().getLargeur());
            this.hauteursInit.add(f.getForme().getHauteur());
        }
        /*
        this.largeurInit = this.vueForme.getForme().getLargeur();
        this.hauteurInit = this.vueForme.getForme().getHauteur();
        */
    }

    public void mouseDragged(MouseEvent e) {
        //On teste si le clic est dans l'une des formes sélectionnées
        boolean clicForme = false;
        int i = 0;
        for(VueForme f : this.vuesFormesSelectionnees) {
            Ligne ligneTemp = new Ligne(f.getForme().getPosition(), this.largeursInit.get(i), this.hauteursInit.get(i));
            Coordonnees coordsInit = new Coordonnees(ligneTemp.getCadreMinX(), ligneTemp.getCadreMinY());
            double largeur = ligneTemp.getCadreMaxX() - ligneTemp.getCadreMinX();
            double hauteur = ligneTemp.getCadreMaxY() - ligneTemp.getCadreMinY();
            Forme formeTemp = new Rectangle(coordsInit, largeur, hauteur);
            if(formeTemp.contient(this.getDebut())) {
                clicForme = true;
            }
            i++;
        }
        if(clicForme) {
            Coordonnees coordsActuelles = new Coordonnees(e.getX(), e.getY());

            double ecartX = coordsActuelles.getAbscisse() - this.getDebut().getAbscisse();
            double ecartY = coordsActuelles.getOrdonnee() - this.getDebut().getOrdonnee();

            i = 0;
            for(VueForme f : this.vuesFormesSelectionnees) {
                double nouvelleLargeur = this.largeursInit.get(i) + ecartX;
                if(nouvelleLargeur > 0 || f.getForme().getClass().getSimpleName().equals("Ligne")) {
                    f.getForme().setLargeur(nouvelleLargeur);
                }

                double nouvelleHauteur = this.hauteursInit.get(i) + ecartY;
                if(nouvelleHauteur > 0 || f.getForme().getClass().getSimpleName().equals("Ligne")) {
                    f.getForme().setHauteur(nouvelleHauteur);
                }
                i++;
            }

            this.getPanneauDessin().repaint();
        }
    }

    public void mouseReleased(MouseEvent e) {
        //On teste si le clic est dans l'une des formes sélectionnées
        boolean clicForme = false;
        int i = 0;
        for(VueForme f : this.vuesFormesSelectionnees) {
            Ligne ligneTemp = new Ligne(f.getForme().getPosition(), this.largeursInit.get(i), this.hauteursInit.get(i));
            Coordonnees coordsInit = new Coordonnees(ligneTemp.getCadreMinX(), ligneTemp.getCadreMinY());
            double largeur = ligneTemp.getCadreMaxX() - ligneTemp.getCadreMinX();
            double hauteur = ligneTemp.getCadreMaxY() - ligneTemp.getCadreMinY();
            Forme formeTemp = new Rectangle(coordsInit, largeur, hauteur);
            if(formeTemp.contient(this.getDebut())) {
                clicForme = true;
            }
            i++;
        }
        if(clicForme) {
            Coordonnees coordsActuelles = new Coordonnees(e.getX(), e.getY());

            double ecartX = coordsActuelles.getAbscisse() - this.getDebut().getAbscisse();
            double ecartY = coordsActuelles.getOrdonnee() - this.getDebut().getOrdonnee();

            i = 0;
            for(VueForme f : this.vuesFormesSelectionnees) {
                double nouvelleLargeur = this.largeursInit.get(i) + ecartX;
                if(nouvelleLargeur < 0 && !f.getForme().getClass().getSimpleName().equals("Ligne")) {
                    f.getForme().setLargeur(this.largeursInit.get(i));
                }

                double nouvelleHauteur = this.hauteursInit.get(i) + ecartY;
                if(nouvelleHauteur < 0 && !f.getForme().getClass().getSimpleName().equals("Ligne")) {
                    f.getForme().setHauteur(this.hauteursInit.get(i));
                }
                i++;
            }
            this.getPanneauDessin().repaint();
        }
    }
}
