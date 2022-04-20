package fr.eseo.pdlo.projet.artiste.controleur.outils;

import java.awt.event.MouseEvent;

import fr.eseo.pdlo.projet.artiste.modele.Coordonnees;
import fr.eseo.pdlo.projet.artiste.vue.formes.VueForme;

public class OutilTourner extends Outil{
    private VueForme vueForme;

    boolean rotationEnCours;

    public OutilTourner(VueForme vueForme) {
        this.vueForme = vueForme;
        this.rotationEnCours = false;
    }

    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        if(this.vueForme.getForme().contient(this.getDebut())) {
            this.rotationEnCours = true;
        }
    }

    public void mouseDragged(MouseEvent e) {
        if(this.rotationEnCours) {
            Coordonnees coordsActuelles = new Coordonnees(e.getX(), e.getY());
            Coordonnees centreForme = new Coordonnees(
                this.vueForme.getForme().getPosition().getAbscisse() + this.vueForme.getForme().getLargeur() / 2,
                this.vueForme.getForme().getPosition().getOrdonnee() + this.vueForme.getForme().getHauteur() / 2);
            this.vueForme.getForme().setAngle(centreForme.angleVers(coordsActuelles));
            this.getPanneauDessin().repaint();
        }
    }

    public void mouseReleased(MouseEvent e) {
        this.rotationEnCours = false;
    }
}
