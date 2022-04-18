package fr.eseo.pdlo.projet.artiste.controleur.actions;

import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import fr.eseo.pdlo.projet.artiste.controleur.outils.OutilPolygone;
import fr.eseo.pdlo.projet.artiste.vue.ihm.PanneauBarreOutils;
import fr.eseo.pdlo.projet.artiste.vue.ihm.PanneauDessin;

public class ActionChoisirCotesPolygone implements ChangeListener {
	
	private PanneauBarreOutils panneauBarreOutils;
	private PanneauDessin panneauDessin;
	private JToggleButton boutonPolygone;
	
	public ActionChoisirCotesPolygone(PanneauDessin panneauDessin, PanneauBarreOutils panneauBarreOutils,
			JToggleButton boutonPolygone) {
		this.panneauDessin = panneauDessin;
		this.panneauBarreOutils = panneauBarreOutils;
		this.boutonPolygone = boutonPolygone;
	}
	
	public void stateChanged(ChangeEvent event) {
		int nbCotes = Integer.parseInt(((JSpinner)event.getSource()).getValue().toString());
		this.panneauBarreOutils.setNbCotesPolygone(nbCotes);
		if(this.boutonPolygone.isSelected()) {
			this.panneauDessin.associerOutil(new OutilPolygone(nbCotes));
		}
	}
}
