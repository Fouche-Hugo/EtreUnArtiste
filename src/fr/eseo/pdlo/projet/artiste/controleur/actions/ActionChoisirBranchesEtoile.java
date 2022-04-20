package fr.eseo.pdlo.projet.artiste.controleur.actions;

import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import fr.eseo.pdlo.projet.artiste.controleur.outils.OutilEtoile;
import fr.eseo.pdlo.projet.artiste.vue.ihm.PanneauBarreOutils;
import fr.eseo.pdlo.projet.artiste.vue.ihm.PanneauDessin;

public class ActionChoisirBranchesEtoile implements ChangeListener {
	
	private PanneauBarreOutils panneauBarreOutils;
	private PanneauDessin panneauDessin;
	private JToggleButton boutonEtoile;
	
	public ActionChoisirBranchesEtoile(PanneauDessin panneauDessin, PanneauBarreOutils panneauBarreOutils,
			JToggleButton boutonEtoile) {
		this.panneauDessin = panneauDessin;
		this.panneauBarreOutils = panneauBarreOutils;
		this.boutonEtoile = boutonEtoile;
	}
	
	public void stateChanged(ChangeEvent event) {
		int nbBranches = Integer.parseInt(((JSpinner)event.getSource()).getValue().toString());
		this.panneauBarreOutils.setNbBranchesEtoile(nbBranches);
		if(this.boutonEtoile.isSelected()) {
			this.panneauDessin.associerOutil(new OutilEtoile(nbBranches));
		}
	}
}
