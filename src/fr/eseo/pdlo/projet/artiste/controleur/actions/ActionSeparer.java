package fr.eseo.pdlo.projet.artiste.controleur.actions;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import fr.eseo.pdlo.projet.artiste.modele.formes.Ellipse;
import fr.eseo.pdlo.projet.artiste.modele.formes.Etoile;
import fr.eseo.pdlo.projet.artiste.modele.formes.Forme;
import fr.eseo.pdlo.projet.artiste.modele.formes.FormeComposee;
import fr.eseo.pdlo.projet.artiste.modele.formes.Ligne;
import fr.eseo.pdlo.projet.artiste.modele.formes.Polygone;
import fr.eseo.pdlo.projet.artiste.modele.formes.Rectangle;
import fr.eseo.pdlo.projet.artiste.modele.formes.Trace;
import fr.eseo.pdlo.projet.artiste.vue.formes.VueEllipse;
import fr.eseo.pdlo.projet.artiste.vue.formes.VueEtoile;
import fr.eseo.pdlo.projet.artiste.vue.formes.VueForme;
import fr.eseo.pdlo.projet.artiste.vue.formes.VueFormeComposee;
import fr.eseo.pdlo.projet.artiste.vue.formes.VueLigne;
import fr.eseo.pdlo.projet.artiste.vue.formes.VuePolygone;
import fr.eseo.pdlo.projet.artiste.vue.formes.VueRectangle;
import fr.eseo.pdlo.projet.artiste.vue.formes.VueTrace;
import fr.eseo.pdlo.projet.artiste.vue.ihm.PanneauDessin;

public class ActionSeparer extends AbstractAction {
    
    private static final long serialVersionUID = 1L;
	
	public static final String NOM_ACTION = "Séparer formes";
	private PanneauDessin panneauDessin;
	
	public ActionSeparer(PanneauDessin panneauDessin) {
		super(ActionSeparer.NOM_ACTION);
		this.panneauDessin = panneauDessin;
	}

    @Override
	public void actionPerformed(ActionEvent event) {
        for(VueForme vF : this.panneauDessin.getVuesFormesSelectionnees()) {
            if(vF instanceof VueFormeComposee) {
                //On récupère l'index à laquelle la formeComposee était
                int index = this.panneauDessin.getVueFormes().indexOf(vF);
                //On ajoute toutes les formes dans l'ordre derrière l'endroit de base
                for(Forme f : ((FormeComposee)vF.getForme()).getFormes()) {
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
                        this.panneauDessin.getVueFormes().add(index, vueForme);
                        index++;
                    }
                }
                this.panneauDessin.getVueFormes().remove(vF);
            }
        }
        this.panneauDessin.getVuesFormesSelectionnees().clear();
        this.panneauDessin.repaint();
    }
}