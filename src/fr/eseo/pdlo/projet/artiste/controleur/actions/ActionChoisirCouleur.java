package fr.eseo.pdlo.projet.artiste.controleur.actions;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JColorChooser;

import fr.eseo.pdlo.projet.artiste.vue.ihm.PanneauDessin;

public class ActionChoisirCouleur extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	public static final String NOM_ACTION = "Choix couleur";
	private PanneauDessin panneauDessin;
	
	public ActionChoisirCouleur(PanneauDessin panneauDessin) {
		super(ActionChoisirCouleur.NOM_ACTION);
		this.panneauDessin = panneauDessin;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		Color couleur = JColorChooser.showDialog(panneauDessin, ActionChoisirCouleur.NOM_ACTION,
				this.panneauDessin.getCouleurCourante());
		if(couleur != null) {
			this.panneauDessin.setCouleurCourante(couleur);
		}
	}
}
