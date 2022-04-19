package fr.eseo.pdlo.projet.artiste.controleur.outils;

import java.awt.event.MouseEvent;

import fr.eseo.pdlo.projet.artiste.modele.Coordonnees;
import fr.eseo.pdlo.projet.artiste.vue.formes.VueForme;

public class OutilDeplacer extends Outil {
    
    private VueForme vueForme;

    public OutilDeplacer(VueForme vueForme) {
        this.vueForme = vueForme;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //On vérifie que les premières coordonnees étaient bien dans la forme
        if(this.vueForme.getForme().contient(new Coordonnees(e.getX(), e.getY()))) {
            Coordonnees coordsActuelles = new Coordonnees(e.getX(), e.getY());
            this.setFin(coordsActuelles);
            this.vueForme.getForme().deplacerDe(this.getFin().getAbscisse()-this.getDebut().getAbscisse(),
                this.getFin().getOrdonnee()-this.getDebut().getOrdonnee());
            this.setDebut(coordsActuelles);
            this.getPanneauDessin().repaint();
        }
    }
}
