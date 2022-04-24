package fr.eseo.pdlo.projet.artiste.controleur.actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import fr.eseo.pdlo.projet.artiste.vue.ihm.PanneauDessin;

public class ActionOuvrirProjet extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	public static final String NOM_ACTION = "Ouvrir un projet";
	private PanneauDessin panneauDessin;
	
	public ActionOuvrirProjet(PanneauDessin panneauDessin) {
		super(ActionOuvrirProjet.NOM_ACTION);
		this.panneauDessin = panneauDessin;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
			"Projet", "proj");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(this.panneauDessin);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
            String path = chooser.getSelectedFile().getAbsolutePath();
            if(path.indexOf(".proj") == -1) {
                path += ".proj";
            }
			this.panneauDessin.ouvrirProjet(path);
    	}
	}
}