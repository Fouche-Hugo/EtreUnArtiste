package fr.eseo.pdlo.projet.artiste.controleur.actions;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

import fr.eseo.pdlo.projet.artiste.controleur.outils.OutilRedimension;
import fr.eseo.pdlo.projet.artiste.vue.ihm.PanneauDessin;

public class ActionRedimension extends AbstractAction {
    
    private static final long serialVersionUID = 1L;
	
	public static final String NOM_ACTION = "Redimensionner forme";
    private PanneauDessin panneauDessin;

    public ActionRedimension(PanneauDessin panneauDessin) {
        super(ActionRedimension.NOM_ACTION);
        this.panneauDessin = panneauDessin;
    }

    public void actionPerformed(ActionEvent e) {
        this.panneauDessin.associerOutil(new OutilRedimension(this.panneauDessin.getVuesFormesSelectionnees()));
    }
}