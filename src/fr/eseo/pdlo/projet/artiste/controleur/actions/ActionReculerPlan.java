package fr.eseo.pdlo.projet.artiste.controleur.actions;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

import fr.eseo.pdlo.projet.artiste.vue.formes.VueForme;
import fr.eseo.pdlo.projet.artiste.vue.ihm.PanneauDessin;

public class ActionReculerPlan extends AbstractAction {
    
    private static final long serialVersionUID = 1L;
	
	public static final String NOM_ACTION = "Reculer";
	private PanneauDessin panneauDessin;
	
	public ActionReculerPlan(PanneauDessin panneauDessin) {
		super(ActionReculerPlan.NOM_ACTION);
		this.panneauDessin = panneauDessin;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
        VueForme formeSelectionnee = this.panneauDessin.getVueFormes().get(this.panneauDessin.getIndexFormeSelectionnee());
        int indexFormeSelectionnee = this.panneauDessin.getIndexFormeSelectionnee();
        if(indexFormeSelectionnee >= 1) {
            this.panneauDessin.getVueFormes().remove(formeSelectionnee);
            indexFormeSelectionnee--;
            this.panneauDessin.getVueFormes().add(indexFormeSelectionnee, formeSelectionnee);
            this.panneauDessin.setIndexFormeSelectionnee(indexFormeSelectionnee);
            this.panneauDessin.repaint();
        }
	}
}