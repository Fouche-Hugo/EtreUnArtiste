package fr.eseo.pdlo.projet.artiste.controleur.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import fr.eseo.pdlo.projet.artiste.vue.ihm.PanneauDessin;

public class ActionEffacer extends AbstractAction{

	private static final long serialVersionUID = 1L;

	public static final String NOM_ACTION = "Effacer";
	private PanneauDessin panneau;
	
	public ActionEffacer(PanneauDessin panneauDessin) {
		super(ActionEffacer.NOM_ACTION);
		this.panneau = panneauDessin;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int rep = JOptionPane.showConfirmDialog(this.panneau, "Voulez-vous vraiment effacer ?",
				ActionEffacer.NOM_ACTION, JOptionPane.OK_CANCEL_OPTION);
		if(rep == 0) {
			this.panneau.setFormeSelectionneeOn(false);
			this.panneau.getVueFormes().clear();
			this.panneau.repaint();
		}
	}
	
}
