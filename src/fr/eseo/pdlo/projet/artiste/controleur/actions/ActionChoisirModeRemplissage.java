package fr.eseo.pdlo.projet.artiste.controleur.actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import fr.eseo.pdlo.projet.artiste.modele.Remplissage;
import fr.eseo.pdlo.projet.artiste.vue.ihm.PanneauDessin;

public class ActionChoisirModeRemplissage extends AbstractAction {

	private static final long serialVersionUID = 1L;

	public static final String NOM_ACTION = "Choix mode remplissage";
	
	private PanneauDessin panneauDessin;
	
	public ActionChoisirModeRemplissage(PanneauDessin panneauDessin, Remplissage remplissage) {
		super(remplissage.getMode());
		this.panneauDessin = panneauDessin;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		
		if(command == Remplissage.AUCUNE.getMode()) {
			this.panneauDessin.setModeRemplissageCourant(Remplissage.AUCUNE);
		} else if(command == Remplissage.UNIFORME.getMode()) {
			this.panneauDessin.setModeRemplissageCourant(Remplissage.UNIFORME);
		}
	}
}
