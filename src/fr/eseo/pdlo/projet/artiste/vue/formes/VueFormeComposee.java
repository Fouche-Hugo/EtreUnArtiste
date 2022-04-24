package fr.eseo.pdlo.projet.artiste.vue.formes;

import fr.eseo.pdlo.projet.artiste.modele.formes.Ellipse;
import fr.eseo.pdlo.projet.artiste.modele.formes.Etoile;
import fr.eseo.pdlo.projet.artiste.modele.formes.Forme;
import fr.eseo.pdlo.projet.artiste.modele.formes.FormeComposee;
import fr.eseo.pdlo.projet.artiste.modele.formes.Ligne;
import fr.eseo.pdlo.projet.artiste.modele.formes.Polygone;
import fr.eseo.pdlo.projet.artiste.modele.formes.Rectangle;
import fr.eseo.pdlo.projet.artiste.modele.formes.Trace;

import java.awt.Graphics2D;

public class VueFormeComposee extends VueForme {
    
    public VueFormeComposee(FormeComposee formeComposee) {
        super(formeComposee);
    }

    @Override
    public void affiche(Graphics2D g2d) {
        //Création des vuesFormes associées
        for(Forme f : ((FormeComposee)this.getForme()).getFormes()) {
            VueForme vueForme = null;
            if(f instanceof Ligne) {
                vueForme = new VueLigne((Ligne)f);
            } else if(f instanceof Ellipse) {
                vueForme = new VueEllipse((Ellipse)f);
            } else if(f instanceof Rectangle) {
                vueForme = new VueRectangle((Rectangle)f);
            } else if(f instanceof Trace) {
                vueForme = new VueTrace((Trace)f);
            } else if(f instanceof Polygone) {
                vueForme = new VuePolygone((Polygone)f);
            } else if(f instanceof Etoile) {
                vueForme = new VueEtoile((Etoile)f);
            } else if(f instanceof FormeComposee) {
                vueForme = new VueFormeComposee((FormeComposee)f);
            }

            //test sécuritaire
            if(vueForme != null) {
                vueForme.affiche(g2d);
            }
        }
    }
}
