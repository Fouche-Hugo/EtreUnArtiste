package fr.eseo.pdlo.projet.artiste.controleur.outils;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import fr.eseo.pdlo.projet.artiste.modele.Coordonnees;
import fr.eseo.pdlo.projet.artiste.modele.formes.Forme;
import fr.eseo.pdlo.projet.artiste.modele.formes.FormeComposee;
import fr.eseo.pdlo.projet.artiste.vue.formes.VueForme;
import fr.eseo.pdlo.projet.artiste.vue.formes.VueFormeComposee;

public class OutilTourner extends Outil{
    
    private ArrayList<VueForme> vuesFormesSelectionnees;
    private VueForme vueFormeSurClic;
    private boolean rotationEnCours;

    public OutilTourner(ArrayList<VueForme> vuesFormesSelectionnees) {
        this.vuesFormesSelectionnees = vuesFormesSelectionnees;
        this.rotationEnCours = false;
    }

    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        for(VueForme f : this.vuesFormesSelectionnees) {
            if(f.getForme().contient(this.getDebut())) {
                this.vueFormeSurClic = f;
                this.rotationEnCours = true;
            }
        }
    }

    public void mouseDragged(MouseEvent e) {
        if(this.rotationEnCours) {
            for(VueForme f : this.vuesFormesSelectionnees) {
                Coordonnees coordsActuelles = new Coordonnees(e.getX(), e.getY());
                Coordonnees centreForme = new Coordonnees(
                    this.vueFormeSurClic.getForme().getPosition().getAbscisse() + this.vueFormeSurClic.getForme().getLargeur() / 2,
                    this.vueFormeSurClic.getForme().getPosition().getOrdonnee() + this.vueFormeSurClic.getForme().getHauteur() / 2);
                f.getForme().setAngle(centreForme.angleVers(coordsActuelles));
                this.getPanneauDessin().repaint();
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
        this.rotationEnCours = false;
    }
}
