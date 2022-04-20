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

import fr.eseo.pdlo.projet.artiste.controleur.actions.ActionAvancerPlan;
import fr.eseo.pdlo.projet.artiste.controleur.actions.ActionChangerCouleur;
import fr.eseo.pdlo.projet.artiste.controleur.actions.ActionChangerCouleurSecondaire;
import fr.eseo.pdlo.projet.artiste.controleur.actions.ActionChangerModeRemplissage;
import fr.eseo.pdlo.projet.artiste.controleur.actions.ActionChoisirAntiAliasing;
import fr.eseo.pdlo.projet.artiste.controleur.actions.ActionChoisirBranchesEtoile;
import fr.eseo.pdlo.projet.artiste.controleur.actions.ActionChoisirCotesPolygone;
import fr.eseo.pdlo.projet.artiste.controleur.actions.ActionChoisirCouleur;
import fr.eseo.pdlo.projet.artiste.controleur.actions.ActionChoisirCouleurSecondaire;
import fr.eseo.pdlo.projet.artiste.controleur.actions.ActionChoisirEpaisseur;
import fr.eseo.pdlo.projet.artiste.controleur.actions.ActionChoisirForme;
import fr.eseo.pdlo.projet.artiste.controleur.actions.ActionChoisirGrille;
import fr.eseo.pdlo.projet.artiste.controleur.actions.ActionChoisirModeRemplissage;
import fr.eseo.pdlo.projet.artiste.controleur.actions.ActionEffacer;
import fr.eseo.pdlo.projet.artiste.controleur.actions.ActionMettreArrierePlan;
import fr.eseo.pdlo.projet.artiste.controleur.actions.ActionMettreAvantPlan;
import fr.eseo.pdlo.projet.artiste.controleur.actions.ActionReculerPlan;
import fr.eseo.pdlo.projet.artiste.controleur.actions.ActionRedimension;
import fr.eseo.pdlo.projet.artiste.controleur.actions.ActionSelectionForme;
import fr.eseo.pdlo.projet.artiste.controleur.actions.ActionSelectionner;
import fr.eseo.pdlo.projet.artiste.controleur.actions.ActionTourner;
import fr.eseo.pdlo.projet.artiste.controleur.actions.ActionDeplacer;
import fr.eseo.pdlo.projet.artiste.modele.Remplissage;

public class PanneauBarreOutils extends JPanel{
	
	private static final long serialVersionUID = 1L;
	public static final int NB_COTES_POLYGONE_PAR_DEFAUT = 6;
	public static final int NB_BRANCHES_ETOILE_PAR_DEFAUT = 5;

	private PanneauDessin panneauDessin;
	private int nbCotesPolygone;
	private int nbBranchesEtoile;

	private JLabel labelSelection;
	private JToggleButton boutonDeplacer;
	private JToggleButton boutonRedimension;
	private JToggleButton boutonTourner;
	private JButton boutonChangerCouleur;
	private JButton boutonChangerCouleurSecondaire;
	private JButton boutonChangerModeRemplissage;
	private JButton boutonMettreArrierePlan;
	private JButton boutonReculerPlan;
	private JButton boutonAvancerPlan;
	private JButton boutonMettreAvantPlan;
	
	public PanneauBarreOutils(PanneauDessin panneauDessin) {
		super();
		this.panneauDessin = panneauDessin;
		this.nbCotesPolygone = PanneauBarreOutils.NB_COTES_POLYGONE_PAR_DEFAUT;
		this.nbBranchesEtoile = PanneauBarreOutils.NB_BRANCHES_ETOILE_PAR_DEFAUT;
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
		
		//Bouton pour l'outil etoile
		JToggleButton boutonEtoile = new JToggleButton(new ActionChoisirForme(this.panneauDessin, this,
				ActionChoisirForme.NOM_ACTION_ETOILE));
		boutonPolygone.setName(ActionChoisirForme.NOM_ACTION_ETOILE);

		//Bouton pour l'outil selectionner (qui donne l'info d'une forme)
		JToggleButton boutonSelectionner = new JToggleButton(new ActionSelectionner(this.panneauDessin));
		boutonSelectionner.setName(ActionSelectionner.NOM_ACTION);
		
		//Bouton pour la selection d'une forme (pour la modifier ensuite)
		JToggleButton boutonSelectionForme = new JToggleButton(new ActionSelectionForme(this.panneauDessin, this));
		boutonSelectionForme.setName(ActionSelectionForme.NOM_ACTION);
		
		//Label indicateur
		this.labelSelection = new JLabel("Selection en cours");
		this.labelSelection.setEnabled(false);
		//Bouton pour le deplacement
		this.boutonDeplacer = new JToggleButton(new ActionDeplacer(this.panneauDessin));
		this.boutonDeplacer.setName(ActionDeplacer.NOM_ACTION);
		this.boutonDeplacer.setEnabled(false);
		//Bouton pour la redimension
		this.boutonRedimension = new JToggleButton(new ActionRedimension(this.panneauDessin));
		this.boutonRedimension.setName(ActionRedimension.NOM_ACTION);
		this.boutonRedimension.setEnabled(false);
		//Bouton pour faire tourner une forme
		this.boutonTourner = new JToggleButton(new ActionTourner(this.panneauDessin));
		this.boutonTourner.setName(ActionTourner.NOM_ACTION);
		this.boutonTourner.setEnabled(false);
		//Bouton pour modifier la couleur de la forme
		this.boutonChangerCouleur = new JButton(new ActionChangerCouleur(this.panneauDessin));
		this.boutonChangerCouleur.setName(ActionChangerCouleur.NOM_ACTION);
		this.boutonChangerCouleur.setEnabled(false);
		//Bouton pour changer la couleur secondaire de la forme
		this.boutonChangerCouleurSecondaire = new JButton(new ActionChangerCouleurSecondaire(this.panneauDessin));
		this.boutonChangerCouleurSecondaire.setName(ActionChangerCouleurSecondaire.NOM_ACTION);
		this.boutonChangerCouleurSecondaire.setEnabled(false);
		//Bouton pour changer le mode de remplissage de la forme
		this.boutonChangerModeRemplissage = new JButton(new ActionChangerModeRemplissage(this.panneauDessin));
		this.boutonChangerModeRemplissage.setName(ActionChangerModeRemplissage.NOM_ACTION);
		this.boutonChangerModeRemplissage.setEnabled(false);
		//JPanel qui contient les boutons pour changer l'ordre des formes
		JPanel ordreContainer = new JPanel();
		ordreContainer.setLayout(new BoxLayout(ordreContainer, BoxLayout.X_AXIS));
		//Bouton pour mettre en arrière plan
		this.boutonMettreArrierePlan = new JButton(new ActionMettreArrierePlan(this.panneauDessin));
		boutonMettreArrierePlan.setName(ActionMettreArrierePlan.NOM_ACTION);
		//Bouton pour reculer d'un cran la forme
		this.boutonReculerPlan = new JButton(new ActionReculerPlan(this.panneauDessin));
		boutonReculerPlan.setName(ActionReculerPlan.NOM_ACTION);
		//Bouton pour avancer d'un cran la forme
		this.boutonAvancerPlan = new JButton(new ActionAvancerPlan(this.panneauDessin));
		boutonAvancerPlan.setName(ActionAvancerPlan.NOM_ACTION);
		//Bouton pour mettre à l'avant plan
		this.boutonMettreAvantPlan = new JButton(new ActionMettreAvantPlan(this.panneauDessin));
		boutonMettreAvantPlan.setName(ActionMettreAvantPlan.NOM_ACTION);

		ordreContainer.add(this.boutonMettreArrierePlan);
		ordreContainer.add(this.boutonReculerPlan);
		ordreContainer.add(this.boutonAvancerPlan);
		ordreContainer.add(this.boutonMettreAvantPlan);

		//Bouton pour le choix de la couleur actuelle
		ActionChoisirCouleur actionChoisirCouleur = new ActionChoisirCouleur(this.panneauDessin);
		JButton boutonChoixCouleur = new JButton(actionChoisirCouleur);
		boutonChoixCouleur.setName(ActionChoisirCouleur.NOM_ACTION);
		this.add(boutonChoixCouleur);

		//Bouton pour le choix de la couleur secondaire actuelle
		ActionChoisirCouleurSecondaire actionChoisirCouleurSecondaire = new ActionChoisirCouleurSecondaire(this.panneauDessin);
		JButton boutonChoixCouleurSecondaire = new JButton(actionChoisirCouleurSecondaire);
		boutonChoixCouleurSecondaire.setName(ActionChoisirCouleurSecondaire.NOM_ACTION);
		this.add(boutonChoixCouleurSecondaire);

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

		//Choix du nombre de côtés pour une etoile
		SpinnerNumberModel modelEtoile = new SpinnerNumberModel(PanneauBarreOutils.NB_BRANCHES_ETOILE_PAR_DEFAUT, 3, 18, 1);
		JSpinner spEtoile = new JSpinner(modelEtoile);
		spEtoile.setMaximumSize(new Dimension(50, 28));
		
		spEtoile.addChangeListener(new ActionChoisirBranchesEtoile(this.panneauDessin, this, boutonEtoile));
		
		JPanel etoileContainer = new JPanel();
		etoileContainer.add(boutonEtoile);
		etoileContainer.add(spEtoile);
		etoileContainer.setLayout(new BoxLayout(etoileContainer, BoxLayout.X_AXIS));
		
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

		JToggleButton boutonGrille = new JToggleButton(ActionChoisirGrille.NOM_ACTION);
		boutonGrille.addChangeListener(new ActionChoisirGrille(panneauDessin));
		
		boutonsGroupe.add(boutonLigne);
		boutonsGroupe.add(boutonEllipse);
		boutonsGroupe.add(boutonCercle);
		boutonsGroupe.add(boutonRectangle);
		boutonsGroupe.add(boutonCarre);
		boutonsGroupe.add(boutonTrace);
		boutonsGroupe.add(boutonPolygone);
		boutonsGroupe.add(boutonEtoile);
		boutonsGroupe.add(boutonSelectionner);
		boutonsGroupe.add(boutonSelectionForme);
		boutonsGroupe.add(boutonDeplacer);
		boutonsGroupe.add(boutonRedimension);
		boutonsGroupe.add(boutonTourner);
		
		boutonsChoixRemplissageGroupe.add(boutonRemplissageAucune);
		boutonsChoixRemplissageGroupe.add(boutonRemplissageUniforme);
		
		this.add(boutonLigne);
		this.add(boutonEllipse);
		this.add(boutonCercle);
		this.add(boutonRectangle);
		this.add(boutonCarre);
		this.add(boutonTrace);
		this.add(polygoneContainer);
		this.add(etoileContainer);
		this.add(boutonSelectionner);
		this.add(boutonSelectionForme);
		this.add(boutonRemplissageAucune);
		this.add(boutonRemplissageUniforme);
		this.add(epaisseurContainer);
		this.add(boutonAntiAliasing);
		this.add(boutonGrille);
		this.add(labelSelection);
		this.add(boutonDeplacer);
		this.add(boutonRedimension);
		this.add(boutonTourner);
		this.add(boutonChangerCouleur);
		this.add(boutonChangerCouleurSecondaire);
		this.add(boutonChangerModeRemplissage);
		this.add(ordreContainer);
	}
	
	public int getNbCotesPolygone() {
		return this.nbCotesPolygone;
	}
	
	public void setNbCotesPolygone(int nbCotes) {
		this.nbCotesPolygone = nbCotes;
	}

	public int getNbBranchesEtoile() {
		return this.nbBranchesEtoile;
	}

	public void setNbBranchesEtoile(int nbBranches) {
		this.nbBranchesEtoile = nbBranches;
	}

	public void activerBoutonsSelection(boolean selection) {
		if(selection) {
			this.labelSelection.setEnabled(true);
			this.boutonDeplacer.setEnabled(true);
			this.boutonRedimension.setEnabled(true);
			this.boutonTourner.setEnabled(true);
			this.boutonChangerCouleur.setEnabled(true);
			this.boutonChangerCouleurSecondaire.setEnabled(true);
			this.boutonChangerModeRemplissage.setEnabled(true);
			this.boutonMettreArrierePlan.setEnabled(true);
			this.boutonReculerPlan.setEnabled(true);
			this.boutonAvancerPlan.setEnabled(true);
			this.boutonMettreAvantPlan.setEnabled(true);
		} else {
			this.labelSelection.setEnabled(false);
			this.boutonDeplacer.setEnabled(false);
			this.boutonRedimension.setEnabled(false);
			this.boutonTourner.setEnabled(false);
			this.boutonChangerCouleur.setEnabled(false);
			this.boutonChangerCouleurSecondaire.setEnabled(false);
			this.boutonChangerModeRemplissage.setEnabled(false);
			this.boutonMettreArrierePlan.setEnabled(false);
			this.boutonReculerPlan.setEnabled(false);
			this.boutonAvancerPlan.setEnabled(false);
			this.boutonMettreAvantPlan.setEnabled(false);
		}
	}
}
