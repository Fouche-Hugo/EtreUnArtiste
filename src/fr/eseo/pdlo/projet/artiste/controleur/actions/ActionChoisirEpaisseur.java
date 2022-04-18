package fr.eseo.pdlo.projet.artiste.controleur.actions;

import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import fr.eseo.pdlo.projet.artiste.vue.ihm.PanneauDessin;

public class ActionChoisirEpaisseur implements ChangeListener {
	private PanneauDessin panneauDessin;
	
	public ActionChoisirEpaisseur(PanneauDessin panneauDessin) {
		this.panneauDessin = panneauDessin;
	}
	
	public void stateChanged(ChangeEvent event) {
		float epaisseur = Float.parseFloat(((JSpinner)event.getSource()).getValue().toString());
		this.panneauDessin.setEpaisseurCourante(epaisseur);
	}
}
