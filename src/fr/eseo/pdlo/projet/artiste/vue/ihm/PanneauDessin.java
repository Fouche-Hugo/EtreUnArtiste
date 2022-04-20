package fr.eseo.pdlo.projet.artiste.vue.ihm;

import fr.eseo.pdlo.projet.artiste.modele.formes.Ligne;
import fr.eseo.pdlo.projet.artiste.controleur.outils.Outil;
import fr.eseo.pdlo.projet.artiste.modele.Coordonnees;
import fr.eseo.pdlo.projet.artiste.modele.Remplissage;
import fr.eseo.pdlo.projet.artiste.modele.formes.Rectangle;
import fr.eseo.pdlo.projet.artiste.modele.formes.Forme;
import fr.eseo.pdlo.projet.artiste.vue.formes.VueRectangle;
import fr.eseo.pdlo.projet.artiste.vue.formes.VueForme;
import fr.eseo.pdlo.projet.artiste.vue.formes.VueLigne;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JPanel;


public class PanneauDessin extends JPanel {

	private static final long serialVersionUID = 1L;
	public static final int LARGEUR_PAR_DEFAUT = 800;
	public static final int HAUTEUR_PAR_DEFAUT = 600;
	public static final float EPAISSEUR_PAR_DEFAUT = 1.0f;
	public static final Color COULEUR_FOND_PAR_DEFAUT = Color.WHITE;
	
	private final List<VueForme> vueFormes;
	private VueForme vueFormeTemp;
	private boolean vueFormeOn;
	private Outil outilCourant;
	private Color couleurCourante;
	private Color couleurSecondaireCourante;
	private float epaisseurCourante;
	private Remplissage modeRemplissage;

	private boolean formeSelectionneeOn;
	private int indexFormeSelectionnee;

	private boolean grilleAffichee;
	
	private RenderingHints antiAliasing;
	
	public PanneauDessin() {
		this(PanneauDessin.LARGEUR_PAR_DEFAUT, PanneauDessin.HAUTEUR_PAR_DEFAUT, PanneauDessin.COULEUR_FOND_PAR_DEFAUT);
	}
	
	public PanneauDessin(int largeur, int hauteur, Color fond) {
		super();
		super.setPreferredSize(new Dimension(largeur, hauteur));
		super.setBackground(fond);
		
		this.vueFormes = new ArrayList<VueForme>();
		this.vueFormeTemp = new VueLigne(new Ligne());
		this.vueFormeOn = false;
		this.outilCourant = null;
		this.epaisseurCourante = PanneauDessin.EPAISSEUR_PAR_DEFAUT;
		this.couleurCourante = Forme.COULEUR_PAR_DEFAUT;
		this.couleurSecondaireCourante = Forme.COULEUR_PAR_DEFAUT;
		this.modeRemplissage = Remplissage.AUCUNE;
		this.indexFormeSelectionnee = 0;
		this.formeSelectionneeOn = false;
		this.grilleAffichee = false;
		
		this.antiAliasing = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
	}
	
	public List<VueForme> getVueFormes() {
		return this.vueFormes;
	}
	
	public void ajouterVueForme(VueForme vueForme) {
		this.vueFormes.add(vueForme);
		this.repaint();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2D = (Graphics2D)g.create();
		
		g2D.setRenderingHints(this.getAntiAliasing());
		
		if(this.getGrilleAffichee()) {
			int largeurPanneau = this.getWidth();
			int hauteurPanneau = this.getHeight();

			int nbLignesVerticales = 1 + this.getWidth() / 50;
			int nbLignesHorizontales = 1 + this.getHeight() / 50;

			for(int i=0;i<nbLignesHorizontales;i++) {
				Ligne ligneHoriz = new Ligne();
				ligneHoriz.setC1(new Coordonnees(0, 50*i));
				ligneHoriz.setC2(new Coordonnees(largeurPanneau, 50*i));
				ligneHoriz.setCouleur(new Color(0, 0, 0, 35));
				new VueLigne(ligneHoriz).affiche(g2D);
			}
			for(int j=0;j<nbLignesVerticales;j++) {
				Ligne ligneVerti = new Ligne();
				ligneVerti.setC1(new Coordonnees(50*j, 0));
				ligneVerti.setC2(new Coordonnees(50*j, hauteurPanneau));
				ligneVerti.setCouleur(new Color(0, 0, 0, 35));
				new VueLigne(ligneVerti).affiche(g2D);
			}
		}

		for(VueForme f : this.vueFormes) {
			f.affiche(g2D);
		}
		
		if(this.vueFormeOn) {
			this.vueFormeTemp.affiche(g2D);
		}
		if(this.getFormeSelectionneeOn()) {
			Forme formeSelectionnee = this.getVueFormes().get(this.getIndexFormeSelectionnee()).getForme();
			Coordonnees coordsInit = new Coordonnees(formeSelectionnee.getCadreMinX(), formeSelectionnee.getCadreMinY());
			double largeur = formeSelectionnee.getCadreMaxX() - formeSelectionnee.getCadreMinX();
			double hauteur = formeSelectionnee.getCadreMaxY() - formeSelectionnee.getCadreMinY();
			Rectangle rect = new Rectangle(coordsInit, largeur, hauteur);
			rect.setCouleur(new Color(0, 0, 0, 35));
			rect.setAngle(formeSelectionnee.getAngle());
			VueRectangle rectSelection = new VueRectangle(rect);
			rectSelection.affiche(g2D);
		}
		
		g2D.dispose();
	}
	
	public void associerOutil(Outil outil) {
		if(outil != null) {
			if(this.outilCourant != null) {
				this.dissocierOutil();
			}
			this.setOutilCourant(outil);
			this.getOutilCourant().setPanneauDessin(this);
			this.addMouseListener(outil);
			this.addMouseMotionListener(outil);
		}
	}
	
	public void dissocierOutil() {
		this.getOutilCourant().setPanneauDessin(null);
		this.removeMouseListener(this.getOutilCourant());
		this.removeMouseMotionListener(this.getOutilCourant());
		this.setOutilCourant(null);
	}
	
	private void setOutilCourant(Outil outil) {
		this.outilCourant = outil;
	}
	
	public Outil getOutilCourant() {
		return this.outilCourant;
	}
	
	public Color getCouleurCourante() {
		return this.couleurCourante;
	}
	
	public void setCouleurCourante(Color couleur) {
		this.couleurCourante = couleur;
	}

	public Color getCouleurSecondaireCourante() {
		return this.couleurSecondaireCourante;
	}
	
	public void setCouleurSecondaireCourante(Color couleur) {
		this.couleurSecondaireCourante = couleur;
	}
	
	public Remplissage getModeRemplissageCourant() {
		return this.modeRemplissage;
	}
	
	public void setModeRemplissageCourant(Remplissage nouveauModeRemplissage) {
		this.modeRemplissage = nouveauModeRemplissage;
	}
	
	public void setEtatAntiAliasing(boolean etat) {
		if(etat) {
			this.antiAliasing.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		} else {
			this.antiAliasing.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		}
	}
	
	public RenderingHints getAntiAliasing() {
		return this.antiAliasing;
	}
	
	public void setVueFormeOn(boolean on) {
		this.vueFormeOn = on;
	}
	
	public boolean getVueFormeOn() {
		return this.vueFormeOn;
	}
	
	public void setVueFormeTemp(VueForme vueForme) {
		this.vueFormeTemp = vueForme;
		this.repaint();
	}
	
	public VueForme getVueFormeTemp() {
		return vueFormeTemp;
	}
	
	public float getEpaisseurCourante() {
		return this.epaisseurCourante;
	}
	
	public void setEpaisseurCourante(float nouvelleEpaisseur) {
		this.epaisseurCourante = nouvelleEpaisseur;
	}
	
	public VueForme getVueFormeSelectionnee() {
		return this.getVueFormes().get(this.getIndexFormeSelectionnee());
	}

	public int getIndexFormeSelectionnee() {
		return this.indexFormeSelectionnee;
	}
	
	public void setIndexFormeSelectionnee(int index) {
		this.indexFormeSelectionnee = index;
	}

	public boolean getFormeSelectionneeOn() {
		return this.formeSelectionneeOn;
	}

	public void setFormeSelectionneeOn(boolean selection) {
		this.formeSelectionneeOn = selection;
		this.repaint();
	}

	public boolean getGrilleAffichee() {
		return this.grilleAffichee;
	}

	public void setGrilleAffichee(boolean grilleAffichee) {
		this.grilleAffichee = grilleAffichee;
	}
}
