package fr.eseo.pdlo.projet.artiste.vue.ihm;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import javax.swing.SpinnerNumberModel;

import fr.eseo.pdlo.projet.artiste.controleur.actions.ActionChoisirAntiAliasing;
import fr.eseo.pdlo.projet.artiste.controleur.actions.ActionChoisirCotesPolygone;
import fr.eseo.pdlo.projet.artiste.controleur.actions.ActionChoisirCouleur;
import fr.eseo.pdlo.projet.artiste.controleur.actions.ActionChoisirEpaisseur;
import fr.eseo.pdlo.projet.artiste.controleur.actions.ActionChoisirForme;
import fr.eseo.pdlo.projet.artiste.controleur.actions.ActionChoisirModeRemplissage;
import fr.eseo.pdlo.projet.artiste.controleur.actions.ActionEffacer;
import fr.eseo.pdlo.projet.artiste.controleur.actions.ActionSelectionForme;
import fr.eseo.pdlo.projet.artiste.controleur.actions.ActionSelectionner;
import fr.eseo.pdlo.projet.artiste.controleur.actions.ActionDeplacer;
import fr.eseo.pdlo.projet.artiste.modele.Remplissage;

public class PanneauBarreOutils extends JPanel{
	
	private static final long serialVersionUID = 1L;
	public static final int NB_COTES_POLYGONE_PAR_DEFAUT = 6;
	private PanneauDessin panneauDessin;
	private int nbCotesPolygone;

	private JLabel labelSelection;
	private JToggleButton boutonDeplacer;
	private JToggleButton boutonRedimension;
	private JToggleButton boutonTourner;
	private JToggleButton boutonChangerCouleur;
	private JToggleButton boutonChangerModeRemplissage;
	
	public PanneauBarreOutils(PanneauDessin panneauDessin) {
		super();
		this.panneauDessin = panneauDessin;
		this.nbCotesPolygone = PanneauBarreOutils.NB_COTES_POLYGONE_PAR_DEFAUT;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.initComponents();
	}
	
	private void initComponents() {
		this.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		ActionEffacer actionEffacer = new ActionEffacer(this.panneauDessin);
		JButton effacer = new JButton(actionEffacer);
		effacer.setName(ActionEffacer.NOM_ACTION);
		this.add(effacer);
		
		//Groupe de boutons pour les outils
		ButtonGroup boutonsGroupe = new ButtonGroup();
		
		//Bouton pour l'outil ligne
		JToggleButton boutonLigne = new JToggleButton(new ActionChoisirForme(this.panneauDessin, this,
				ActionChoisirForme.NOM_ACTION_LIGNE));
		boutonLigne.setName(ActionChoisirForme.NOM_ACTION_LIGNE);
		
		//Bouton pour l'outil ellipse
		JToggleButton boutonEllipse = new JToggleButton(new ActionChoisirForme(this.panneauDessin, this,
				ActionChoisirForme.NOM_ACTION_ELLIPSE));
		boutonEllipse.setName(ActionChoisirForme.NOM_ACTION_ELLIPSE);
		
		//Bouton pour l'outil cercle
		JToggleButton boutonCercle = new JToggleButton(new ActionChoisirForme(this.panneauDessin, this,
				ActionChoisirForme.NOM_ACTION_CERCLE));
		boutonCercle.setName(ActionChoisirForme.NOM_ACTION_CERCLE);
		
		//Bouton pour l'outil rectangle
		JToggleButton boutonRectangle = new JToggleButton(new ActionChoisirForme(this.panneauDessin, this,
				ActionChoisirForme.NOM_ACTION_RECTANGLE));
		boutonRectangle.setName(ActionChoisirForme.NOM_ACTION_RECTANGLE);
		
		//Bouton pour l'outil carre
		JToggleButton boutonCarre = new JToggleButton(new ActionChoisirForme(this.panneauDessin, this,
				ActionChoisirForme.NOM_ACTION_CARRE));
		boutonCarre.setName(ActionChoisirForme.NOM_ACTION_CARRE);
		
		//Bouton pour l'outil trace
		JToggleButton boutonTrace = new JToggleButton(new ActionChoisirForme(this.panneauDessin, this,
				ActionChoisirForme.NOM_ACTION_TRACE));
		boutonTrace.setName(ActionChoisirForme.NOM_ACTION_TRACE);
		
		//Bouton pour l'outil polygone
		JToggleButton boutonPolygone = new JToggleButton(new ActionChoisirForme(this.panneauDessin, this,
				ActionChoisirForme.NOM_ACTION_POLYGONE));
		boutonPolygone.setName(ActionChoisirForme.NOM_ACTION_POLYGONE);
		
		//Bouton pour l'outil selectionner (qui donne l'info d'une forme)
		JToggleButton boutonSelectionner = new JToggleButton(new ActionSelectionner(this.panneauDessin));
		boutonSelectionner.setName(ActionSelectionner.NOM_ACTION);
		
		//Bouton pour la selection d'une forme (pour la modifier ensuite)
		JToggleButton boutonSelectionForme = new JToggleButton(new ActionSelectionForme(this.panneauDessin, this));
		boutonSelectionForme.setName(ActionSelectionForme.NOM_ACTION);
		
		//Boutons qui ne s'affichent que lorsqu'une forme est selectionnée
		ButtonGroup boutonsSelectionGroupe = new ButtonGroup();
		//Label indicateur
		this.labelSelection = new JLabel("Selection en cours");
		this.labelSelection.setEnabled(false);
		//Bouton pour le deplacement
		this.boutonDeplacer = new JToggleButton(new ActionDeplacer(this.panneauDessin));
		this.boutonDeplacer.setName(ActionDeplacer.NOM_ACTION);
		this.boutonDeplacer.setEnabled(false);
		//Bouton pour la redimension
		this.boutonRedimension = new JToggleButton();
		this.boutonRedimension.setName("redimTemp");
		this.boutonRedimension.setEnabled(false);
		//Bouton pour faire tourner une forme
		this.boutonTourner = new JToggleButton();
		this.boutonTourner.setName("tournerTemp");
		this.boutonTourner.setEnabled(false);
		//Bouton pour modifier la couleur de la forme
		this.boutonChangerCouleur = new JToggleButton();
		this.boutonChangerCouleur.setName("changerCouleurTemp");
		this.boutonChangerCouleur.setEnabled(false);
		//Bouton pour changer le mode de remplissage
		this.boutonChangerModeRemplissage = new JToggleButton();
		this.boutonChangerModeRemplissage.setName("changerRemplissageTemp");
		this.boutonChangerModeRemplissage.setEnabled(false);

		boutonsSelectionGroupe.add(this.boutonDeplacer);
		boutonsSelectionGroupe.add(this.boutonRedimension);
		boutonsSelectionGroupe.add(this.boutonTourner);

		//Bouton pour le choix de la couleur actuelle
		ActionChoisirCouleur actionChoisirCouleur = new ActionChoisirCouleur(this.panneauDessin);
		JButton boutonChoixCouleur = new JButton(actionChoisirCouleur);
		boutonChoixCouleur.setName(ActionChoisirCouleur.NOM_ACTION);
		this.add(boutonChoixCouleur);

		//Groupe de boutons pour les types de remplissages
		ButtonGroup boutonsChoixRemplissageGroupe = new ButtonGroup();
		JToggleButton boutonRemplissageAucune = new JToggleButton(new ActionChoisirModeRemplissage(this.panneauDessin,
				Remplissage.AUCUNE));
		boutonRemplissageAucune.setName(Remplissage.AUCUNE.getMode());
		boutonRemplissageAucune.setSelected(true);
		JToggleButton boutonRemplissageUniforme = new JToggleButton(new ActionChoisirModeRemplissage(this.panneauDessin,
				Remplissage.UNIFORME));
		boutonRemplissageUniforme.setName(Remplissage.UNIFORME.getMode());
		
		//Choix du nombre de côtés pour un polygone
		SpinnerNumberModel model = new SpinnerNumberModel(PanneauBarreOutils.NB_COTES_POLYGONE_PAR_DEFAUT, 3, 30, 1);
		JSpinner sp = new JSpinner(model);
		sp.setMaximumSize(new Dimension(50, 28));
		
		sp.addChangeListener(new ActionChoisirCotesPolygone(this.panneauDessin, this, boutonPolygone));
		
		JPanel polygoneContainer = new JPanel();
		polygoneContainer.add(boutonPolygone);
		polygoneContainer.add(sp);
		polygoneContainer.setLayout(new BoxLayout(polygoneContainer, BoxLayout.X_AXIS));
		
		//Epaisseur du trait
		JLabel labelEpaisseur = new JLabel("Epaisseur : ");
		
		SpinnerNumberModel modelEpaisseur = new SpinnerNumberModel(PanneauDessin.EPAISSEUR_PAR_DEFAUT, 0.1f, 5f, 0.1f);
		JSpinner spEpaisseur = new JSpinner(modelEpaisseur);
		spEpaisseur.setMaximumSize(new Dimension(50, 28));
		spEpaisseur.addChangeListener(new ActionChoisirEpaisseur(this.panneauDessin));
		
		JPanel epaisseurContainer = new JPanel();
		epaisseurContainer.add(labelEpaisseur);
		epaisseurContainer.add(spEpaisseur);
		epaisseurContainer.setLayout(new BoxLayout(epaisseurContainer, BoxLayout.X_AXIS));
		
		JToggleButton boutonAntiAliasing = new JToggleButton(ActionChoisirAntiAliasing.NOM_ACTION);
		boutonAntiAliasing.addChangeListener(new ActionChoisirAntiAliasing(this.panneauDessin));
		
		boutonsGroupe.add(boutonLigne);
		boutonsGroupe.add(boutonEllipse);
		boutonsGroupe.add(boutonCercle);
		boutonsGroupe.add(boutonRectangle);
		boutonsGroupe.add(boutonCarre);
		boutonsGroupe.add(boutonTrace);
		boutonsGroupe.add(boutonPolygone);
		boutonsGroupe.add(boutonSelectionner);
		boutonsGroupe.add(boutonSelectionForme);
		
		boutonsChoixRemplissageGroupe.add(boutonRemplissageAucune);
		boutonsChoixRemplissageGroupe.add(boutonRemplissageUniforme);
		
		this.add(boutonLigne);
		this.add(boutonEllipse);
		this.add(boutonCercle);
		this.add(boutonRectangle);
		this.add(boutonCarre);
		this.add(boutonTrace);
		this.add(polygoneContainer);
		this.add(boutonSelectionner);
		this.add(boutonSelectionForme);
		this.add(boutonRemplissageAucune);
		this.add(boutonRemplissageUniforme);
		this.add(epaisseurContainer);
		this.add(boutonAntiAliasing);
		this.add(labelSelection);
		this.add(boutonDeplacer);
		this.add(boutonRedimension);
		this.add(boutonTourner);
		this.add(boutonChangerCouleur);
		this.add(boutonChangerModeRemplissage);
	}
	
	public int getNbCotesPolygone() {
		return this.nbCotesPolygone;
	}
	
	public void setNbCotesPolygone(int nbCotes) {
		this.nbCotesPolygone = nbCotes;
	}

	public void activerBoutonsSelection(boolean selection) {
		if(selection) {
			this.labelSelection.setEnabled(true);
			this.boutonDeplacer.setEnabled(true);
			this.boutonRedimension.setEnabled(true);
			this.boutonTourner.setEnabled(true);
			this.boutonChangerCouleur.setEnabled(true);
			this.boutonChangerModeRemplissage.setEnabled(true);
		} else {
			this.labelSelection.setEnabled(false);
			
			this.boutonDeplacer.setSelected(false);
			this.boutonDeplacer.setEnabled(false);
			
			this.boutonRedimension.setSelected(false);
			this.boutonRedimension.setEnabled(false);

			this.boutonTourner.setSelected(false);
			this.boutonTourner.setEnabled(false);

			this.boutonChangerCouleur.setSelected(false);
			this.boutonChangerCouleur.setEnabled(false);

			this.boutonChangerModeRemplissage.setSelected(false);
			this.boutonChangerModeRemplissage.setEnabled(false);
		}
	}
}
