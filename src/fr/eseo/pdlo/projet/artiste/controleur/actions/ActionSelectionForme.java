package fr.eseo.pdlo.projet.artiste.controleur.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import fr.eseo.pdlo.projet.artiste.controleur.outils.OutilSelectionForme;
import fr.eseo.pdlo.projet.artiste.vue.ihm.PanneauDessin;

public class ActionSelectionForme extends AbstractAction {

private static final long serialVersionUID = 1L;
	
	public static final String NOM_ACTION = "Selection forme";
	private PanneauDessin panneauDessin;
	
	public ActionSelectionForme(PanneauDessin panneauDessin) {
		super(ActionSelectionForme.NOM_ACTION);
		this.panneauDessin = panneauDessin;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.panneauDessin.associerOutil(new OutilSelectionForme());
	}
}
