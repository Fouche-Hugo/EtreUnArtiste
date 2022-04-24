package fr.eseo.pdlo.projet.artiste.controleur.actions;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

import fr.eseo.pdlo.projet.artiste.vue.formes.VueForme;
import fr.eseo.pdlo.projet.artiste.vue.ihm.PanneauDessin;

public class ActionMettreAvantPlan extends AbstractAction {
    
    private static final long serialVersionUID = 1L;
	
	public static final String NOM_ACTION = "Avant plan";
	private PanneauDessin panneauDessin;
	
	public ActionMettreAvantPlan(PanneauDessin panneauDessin) {
		super(ActionMettreAvantPlan.NOM_ACTION);
		this.panneauDessin = panneauDessin;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
        for(VueForme f : this.panneauDessin.getVuesFormesSelectionnees()) {
			this.panneauDessin.getVueFormes().remove(f);
			this.panneauDessin.getVueFormes().add(f);
		}
        this.panneauDessin.repaint();
	}
}
