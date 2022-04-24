package fr.eseo.pdlo.projet.artiste.controleur.actions;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import fr.eseo.pdlo.projet.artiste.modele.Remplissable;
import fr.eseo.pdlo.projet.artiste.modele.Remplissage;
import fr.eseo.pdlo.projet.artiste.vue.formes.VueForme;
import fr.eseo.pdlo.projet.artiste.vue.ihm.PanneauDessin;

public class ActionChangerModeRemplissage extends AbstractAction {
    private static final long serialVersionUID = 1L;
	
	public static final String NOM_ACTION = "Choix mode remplissage";
	private PanneauDessin panneauDessin;
	
	public ActionChangerModeRemplissage(PanneauDessin panneauDessin) {
		super(ActionChangerModeRemplissage.NOM_ACTION);
		this.panneauDessin = panneauDessin;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
        //Test si au moins une forme est Remplissable
        ArrayList<VueForme> vuesFormesSelectionneesRemplissables = new ArrayList<VueForme>();
        for(VueForme f : this.panneauDessin.getVuesFormesSelectionnees()) {
            if(f.getForme() instanceof Remplissable) {
                vuesFormesSelectionneesRemplissables.add(f);
            }
        }
        if(vuesFormesSelectionneesRemplissables.size() > 0) {
            String possibilites[] = new String[Remplissage.values().length];
            int i = 0;
            for(Remplissage r : Remplissage.values()) {
                possibilites[i] = r.getMode();
                i += 1;
            }

            String s = (String)JOptionPane.showInputDialog(
                null,
                "Choisir le mode : ", ActionChangerModeRemplissage.NOM_ACTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                possibilites, 
                0);
            if(s != null) {
                for(VueForme f : vuesFormesSelectionneesRemplissables) {
                    ((Remplissable)f.getForme()).setRemplissage(Remplissage.fromString(s));
                }
                this.panneauDessin.repaint();
            }
        }
	}
}
