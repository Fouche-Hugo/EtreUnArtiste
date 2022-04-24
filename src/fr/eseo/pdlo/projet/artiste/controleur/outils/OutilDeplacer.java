package fr.eseo.pdlo.projet.artiste.controleur.outils;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import fr.eseo.pdlo.projet.artiste.modele.Coordonnees;
import fr.eseo.pdlo.projet.artiste.vue.formes.VueForme;

public class OutilDeplacer extends Outil {
    
    private ArrayList<VueForme> vuesFormesSelectionnees;

    public OutilDeplacer(ArrayList<VueForme> vuesFormesSelectionnees) {
        this.vuesFormesSelectionnees = vuesFormesSelectionnees;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        boolean clicDansUneForme = false;
        for(VueForme f : this.vuesFormesSelectionnees) {
            if(f.getForme().contient(new Coordonnees(e.getX(), e.getY()))) {
                clicDansUneForme = true;
            }
        }
        if(clicDansUneForme) {
            Coordonnees coordsActuelles = new Coordonnees(e.getX(), e.getY());
            this.setFin(coordsActuelles);
            for(VueForme f : this.vuesFormesSelectionnees) {
                f.getForme().deplacerDe(this.getFin().getAbscisse()-this.getDebut().getAbscisse(),
                    this.getFin().getOrdonnee()-this.getDebut().getOrdonnee());
            }
            this.setDebut(coordsActuelles);
            this.getPanneauDessin().repaint();
        }
    }
}
