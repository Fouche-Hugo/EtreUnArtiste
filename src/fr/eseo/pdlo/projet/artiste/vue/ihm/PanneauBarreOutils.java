package fr.eseo.pdlo.projet.artiste.vue.ihm;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import fr.eseo.pdlo.projet.artiste.controleur.actions.ActionChoisirAntiAliasing;
import fr.eseo.pdlo.projet.artiste.controleur.actions.ActionChoisirCotesPolygone;
import fr.eseo.pdlo.projet.artiste.controleur.actions.ActionChoisirCouleur;
import fr.eseo.pdlo.projet.artiste.controleur.actions.ActionChoisirEpaisseur;
import fr.eseo.pdlo.projet.artiste.controleur.actions.ActionChoisirForme;
import fr.eseo.pdlo.projet.artiste.controleur.actions.ActionChoisirModeRemplissage;
import fr.eseo.pdlo.projet.artiste.controleur.actions.ActionEffacer;
import fr.eseo.pdlo.projet.artiste.controleur.actions.ActionSelectionForme;
import fr.eseo.pdlo.projet.artiste.controleur.actions.ActionSelectionner;
import fr.eseo.pdlo.projet.artiste.controleur.outils.OutilPolygone;
import fr.eseo.pdlo.projet.artiste.modele.Remplissage;

public class PanneauBarreOutils extends JPanel{
	
	private static final long serialVersionUID = 1L;
	public static final int NB_COTES_POLYGONE_PAR_DEFAUT = 6;
	private PanneauDessin panneauDessin;
	private int nbCotesPolygone;
	
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
		
		ButtonGroup boutonsGroupe = new ButtonGroup();
		JToggleButton boutonLigne = new JToggleButton(new ActionChoisirForme(this.panneauDessin, this,
				ActionChoisirForme.NOM_ACTION_LIGNE));
		boutonLigne.setName(ActionChoisirForme.NOM_ACTION_LIGNE);
		JToggleButton boutonEllipse = new JToggleButton(new ActionChoisirForme(this.panneauDessin, this,
				ActionChoisirForme.NOM_ACTION_ELLIPSE));
		boutonEllipse.setName(ActionChoisirForme.NOM_ACTION_ELLIPSE);
		JToggleButton boutonCercle = new JToggleButton(new ActionChoisirForme(this.panneauDessin, this,
				ActionChoisirForme.NOM_ACTION_CERCLE));
		boutonCercle.setName(ActionChoisirForme.NOM_ACTION_CERCLE);
		JToggleButton boutonRectangle = new JToggleButton(new ActionChoisirForme(this.panneauDessin, this,
				ActionChoisirForme.NOM_ACTION_RECTANGLE));
		boutonRectangle.setName(ActionChoisirForme.NOM_ACTION_RECTANGLE);
		JToggleButton boutonCarre = new JToggleButton(new ActionChoisirForme(this.panneauDessin, this,
				ActionChoisirForme.NOM_ACTION_CARRE));
		boutonCarre.setName(ActionChoisirForme.NOM_ACTION_CARRE);
		JToggleButton boutonTrace = new JToggleButton(new ActionChoisirForme(this.panneauDessin, this,
				ActionChoisirForme.NOM_ACTION_TRACE));
		boutonTrace.setName(ActionChoisirForme.NOM_ACTION_TRACE);
		JToggleButton boutonPolygone = new JToggleButton(new ActionChoisirForme(this.panneauDessin, this,
				ActionChoisirForme.NOM_ACTION_POLYGONE));
		boutonPolygone.setName(ActionChoisirForme.NOM_ACTION_POLYGONE);
		JToggleButton boutonSelectionner = new JToggleButton(new ActionSelectionner(this.panneauDessin));
		boutonSelectionner.setName(ActionSelectionner.NOM_ACTION);
		JToggleButton boutonSelectionForme = new JToggleButton(new ActionSelectionForme(this.panneauDessin));
		boutonSelectionForme.setName(ActionSelectionForme.NOM_ACTION);
		ActionChoisirCouleur actionChoisirCouleur = new ActionChoisirCouleur(this.panneauDessin);
		JButton boutonChoixCouleur = new JButton(actionChoisirCouleur);
		boutonChoixCouleur.setName(ActionChoisirCouleur.NOM_ACTION);
		this.add(boutonChoixCouleur);
		
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
	}
	
	public int getNbCotesPolygone() {
		return this.nbCotesPolygone;
	}
	
	public void setNbCotesPolygone(int nbCotes) {
		this.nbCotesPolygone = nbCotes;
	}
}
