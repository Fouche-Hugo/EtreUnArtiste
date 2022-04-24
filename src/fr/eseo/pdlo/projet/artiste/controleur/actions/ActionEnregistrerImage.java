package fr.eseo.pdlo.projet.artiste.controleur.actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import fr.eseo.pdlo.projet.artiste.vue.ihm.PanneauDessin;

public class ActionEnregistrerImage extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	public static final String NOM_ACTION = "Sauvegarder l'image";
	private PanneauDessin panneauDessin;
	
	public ActionEnregistrerImage(PanneauDessin panneauDessin) {
		super(ActionEnregistrerImage.NOM_ACTION);
		this.panneauDessin = panneauDessin;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
			"Choisissez un nom d'image", "jpg", "gif", "pnj");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(this.panneauDessin);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			this.panneauDessin.enregistrerImage(chooser.getSelectedFile().getAbsolutePath());
    	}
	}
}