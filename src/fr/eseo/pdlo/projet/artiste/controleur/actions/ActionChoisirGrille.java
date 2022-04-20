package fr.eseo.pdlo.projet.artiste.controleur.actions;

import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import fr.eseo.pdlo.projet.artiste.vue.ihm.PanneauDessin;

public class ActionChoisirGrille implements ChangeListener {
	
	//private static final long serialVersionUID = 1L;
	public static final String NOM_ACTION = "Afficher grille";
	private PanneauDessin panneauDessin;
	
	public ActionChoisirGrille(PanneauDessin panneauDessin) {
		this.panneauDessin = panneauDessin;
	}
	
	@Override
	public void stateChanged(ChangeEvent event) {
		if(((JToggleButton)event.getSource()).isSelected()) {
			this.panneauDessin.setGrilleAffichee(true);
			this.panneauDessin.repaint();
		} else {
			this.panneauDessin.setGrilleAffichee(false);
            this.panneauDessin.repaint();
		}
	}
}
