package fr.eseo.pdlo.projet.artiste.controleur.actions;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

import fr.eseo.pdlo.projet.artiste.controleur.outils.OutilDeplacer;
import fr.eseo.pdlo.projet.artiste.vue.ihm.PanneauDessin;

public class ActionDeplacer extends AbstractAction {
    
    private static final long serialVersionUID = 1L;
	
	public static final String NOM_ACTION = "Deplacer forme";
    private PanneauDessin panneauDessin;

    public ActionDeplacer(PanneauDessin panneauDessin) {
        super(ActionDeplacer.NOM_ACTION);
        this.panneauDessin = panneauDessin;
    }

    public void actionPerformed(ActionEvent e) {
        this.panneauDessin.associerOutil(new OutilDeplacer(this.panneauDessin.getVuesFormesSelectionnees()));
    }
}