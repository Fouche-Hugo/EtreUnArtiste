package fr.eseo.pdlo.projet.artiste.controleur.actions;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

import fr.eseo.pdlo.projet.artiste.modele.formes.FormeComposee;
import fr.eseo.pdlo.projet.artiste.vue.formes.VueForme;
import fr.eseo.pdlo.projet.artiste.vue.formes.VueFormeComposee;
import fr.eseo.pdlo.projet.artiste.vue.ihm.PanneauDessin;

public class ActionFusionner extends AbstractAction {
    
    private static final long serialVersionUID = 1L;
	
	public static final String NOM_ACTION = "Fusionner formes";
	private PanneauDessin panneauDessin;
	
	public ActionFusionner(PanneauDessin panneauDessin) {
		super(ActionFusionner.NOM_ACTION);
		this.panneauDessin = panneauDessin;
	}

    @Override
	public void actionPerformed(ActionEvent event) {
        FormeComposee formeComposee = new FormeComposee();
        for(VueForme vueF : this.panneauDessin.getVuesFormesSelectionnees()) {
            formeComposee.ajouterForme(vueF.getForme());
            this.panneauDessin.getVueFormes().remove(vueF);
        }
        this.panneauDessin.getVuesFormesSelectionnees().clear();
        VueFormeComposee vueFormeComposee = new VueFormeComposee(formeComposee);
        this.panneauDessin.getVueFormes().add(vueFormeComposee);
        this.panneauDessin.getVuesFormesSelectionnees().add(vueFormeComposee);
        this.panneauDessin.repaint();
    }
}
