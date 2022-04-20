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
        VueForme formeSelectionnee = this.panneauDessin.getVueFormes().get(this.panneauDessin.getIndexFormeSelectionnee());
        this.panneauDessin.getVueFormes().remove(formeSelectionnee);
		this.panneauDessin.getVueFormes().add(formeSelectionnee);
        this.panneauDessin.setIndexFormeSelectionnee(this.panneauDessin.getVueFormes().size()-1);
        this.panneauDessin.repaint();
	}
}
