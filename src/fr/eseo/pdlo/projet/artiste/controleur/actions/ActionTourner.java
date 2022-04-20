package fr.eseo.pdlo.projet.artiste.controleur.actions;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

import fr.eseo.pdlo.projet.artiste.controleur.outils.OutilTourner;
import fr.eseo.pdlo.projet.artiste.vue.ihm.PanneauDessin;

public class ActionTourner extends AbstractAction {
    
    private static final long serialVersionUID = 1L;
	
	public static final String NOM_ACTION = "Tourner forme";
    private PanneauDessin panneauDessin;

    public ActionTourner(PanneauDessin panneauDessin) {
        super(ActionTourner.NOM_ACTION);
        this.panneauDessin = panneauDessin;
    }

    public void actionPerformed(ActionEvent e) {
        this.panneauDessin.associerOutil(new OutilTourner(this.panneauDessin.getVueFormeSelectionnee()));
    }
}