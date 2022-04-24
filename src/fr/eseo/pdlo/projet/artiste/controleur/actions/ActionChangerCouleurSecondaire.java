package fr.eseo.pdlo.projet.artiste.controleur.actions;

import javax.swing.AbstractAction;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JColorChooser;

import fr.eseo.pdlo.projet.artiste.vue.formes.VueForme;
import fr.eseo.pdlo.projet.artiste.vue.ihm.PanneauDessin;

public class ActionChangerCouleurSecondaire extends AbstractAction {
    private static final long serialVersionUID = 1L;
	
	public static final String NOM_ACTION = "Changer couleur secondaire";
	private PanneauDessin panneauDessin;
	
	public ActionChangerCouleurSecondaire(PanneauDessin panneauDessin) {
		super(ActionChangerCouleurSecondaire.NOM_ACTION);
		this.panneauDessin = panneauDessin;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		Color couleur = JColorChooser.showDialog(panneauDessin, ActionChoisirCouleurSecondaire.NOM_ACTION,
			this.panneauDessin.getVuesFormesSelectionnees().get(0).getForme().getCouleur());
		if(couleur != null) {
			for(VueForme f : this.panneauDessin.getVuesFormesSelectionnees()) {
				f.getForme().setCouleurSecondaire(couleur);
			}
            this.panneauDessin.repaint();
		}
	}
}