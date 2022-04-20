package fr.eseo.pdlo.projet.artiste.controleur.actions;

import javax.swing.AbstractAction;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JColorChooser;

import fr.eseo.pdlo.projet.artiste.vue.ihm.PanneauDessin;

public class ActionChoisirCouleurSecondaire extends AbstractAction {
    private static final long serialVersionUID = 1L;
	
	public static final String NOM_ACTION = "Changer couleur secondaire";
	private PanneauDessin panneauDessin;
	
	public ActionChoisirCouleurSecondaire(PanneauDessin panneauDessin) {
		super(ActionChoisirCouleurSecondaire.NOM_ACTION);
		this.panneauDessin = panneauDessin;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		Color couleur = JColorChooser.showDialog(panneauDessin, ActionChoisirCouleur.NOM_ACTION,
				this.panneauDessin.getCouleurSecondaireCourante());
		if(couleur != null) {
			this.panneauDessin.setCouleurSecondaireCourante(couleur);
		}
	}
}