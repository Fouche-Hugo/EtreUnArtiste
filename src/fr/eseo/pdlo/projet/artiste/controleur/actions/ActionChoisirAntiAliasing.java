package fr.eseo.pdlo.projet.artiste.controleur.actions;

import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import fr.eseo.pdlo.projet.artiste.vue.ihm.PanneauDessin;

public class ActionChoisirAntiAliasing implements ChangeListener {
	
	//private static final long serialVersionUID = 1L;
	public static final String NOM_ACTION = "Anti aliasing";
	private PanneauDessin panneauDessin;
	
	public ActionChoisirAntiAliasing(PanneauDessin panneauDessin) {
		this.panneauDessin = panneauDessin;
	}
	
	@Override
	public void stateChanged(ChangeEvent event) {
		if(((JToggleButton)event.getSource()).isSelected()) {
			this.panneauDessin.setEtatAntiAliasing(true);
		} else {
			this.panneauDessin.setEtatAntiAliasing(false);
		}
	}
}
