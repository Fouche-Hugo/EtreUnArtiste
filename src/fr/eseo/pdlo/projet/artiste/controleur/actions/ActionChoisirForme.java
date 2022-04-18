package fr.eseo.pdlo.projet.artiste.controleur.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import fr.eseo.pdlo.projet.artiste.controleur.outils.OutilCarre;
import fr.eseo.pdlo.projet.artiste.controleur.outils.OutilCercle;
import fr.eseo.pdlo.projet.artiste.controleur.outils.OutilEllipse;
import fr.eseo.pdlo.projet.artiste.controleur.outils.OutilLigne;
import fr.eseo.pdlo.projet.artiste.controleur.outils.OutilPolygone;
import fr.eseo.pdlo.projet.artiste.controleur.outils.OutilRectangle;
import fr.eseo.pdlo.projet.artiste.controleur.outils.OutilTrace;
import fr.eseo.pdlo.projet.artiste.vue.ihm.PanneauBarreOutils;
import fr.eseo.pdlo.projet.artiste.vue.ihm.PanneauDessin;

public class ActionChoisirForme extends AbstractAction{

	private static final long serialVersionUID = 1L;

	public static final String NOM_ACTION_LIGNE = "Ligne";
	public static final String NOM_ACTION_ELLIPSE = "Ellipse";
	public static final String NOM_ACTION_CERCLE = "Cercle";
	public static final String NOM_ACTION_RECTANGLE = "Rectangle";
	public static final String NOM_ACTION_CARRE = "Carre";
	public static final String NOM_ACTION_TRACE = "Trace";
	public static final String NOM_ACTION_POLYGONE = "Polygone";
	
	private PanneauDessin panneauDessin;
	private PanneauBarreOutils panneauBarreOutils;
	
	public ActionChoisirForme(PanneauDessin panneauDessin, PanneauBarreOutils panneauBarreOutils, String action) {
		super(action);
		this.panneauDessin = panneauDessin;
		this.panneauBarreOutils = panneauBarreOutils;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
			case NOM_ACTION_LIGNE:
				this.panneauDessin.associerOutil(new OutilLigne());
				break;
			case NOM_ACTION_ELLIPSE:
				this.panneauDessin.associerOutil(new OutilEllipse());
				break;
			case NOM_ACTION_CERCLE:
				this.panneauDessin.associerOutil(new OutilCercle());
				break;
			case NOM_ACTION_RECTANGLE:
				this.panneauDessin.associerOutil(new OutilRectangle());
				break;
			case NOM_ACTION_CARRE:
				this.panneauDessin.associerOutil(new OutilCarre());
				break;
			case NOM_ACTION_TRACE:
				this.panneauDessin.associerOutil(new OutilTrace());
				break;
			case NOM_ACTION_POLYGONE:
				this.panneauDessin.associerOutil(new OutilPolygone(this.panneauBarreOutils.getNbCotesPolygone()));
				break;
			default:
				//En cas de défaut : outil ligne
				this.panneauDessin.associerOutil(new OutilLigne());
				break;
		}
	}
}
