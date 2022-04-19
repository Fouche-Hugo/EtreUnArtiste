package fr.eseo.pdlo.projet.artiste.controleur.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import fr.eseo.pdlo.projet.artiste.controleur.outils.OutilSelectionForme;
import fr.eseo.pdlo.projet.artiste.vue.ihm.PanneauBarreOutils;
import fr.eseo.pdlo.projet.artiste.vue.ihm.PanneauDessin;

public class ActionSelectionForme extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	public static final String NOM_ACTION = "Selection forme";
	private PanneauDessin panneauDessin;
	private PanneauBarreOutils panneauBarreOutils;
	
	public ActionSelectionForme(PanneauDessin panneauDessin, PanneauBarreOutils panneauBarreOutils) {
		super(ActionSelectionForme.NOM_ACTION);
		this.panneauDessin = panneauDessin;
		this.panneauBarreOutils = panneauBarreOutils;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.panneauDessin.associerOutil(new OutilSelectionForme(this.panneauBarreOutils));
	}
}
