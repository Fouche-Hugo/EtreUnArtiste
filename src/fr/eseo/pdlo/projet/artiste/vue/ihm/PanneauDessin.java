package fr.eseo.pdlo.projet.artiste.vue.ihm;

import fr.eseo.pdlo.projet.artiste.modele.formes.Ligne;
import fr.eseo.pdlo.projet.artiste.controleur.outils.Outil;
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
	private float epaisseurCourante;
	private int indexFormeSelectionnee;
	private boolean selectionEnCours;
	private Remplissage modeRemplissage;
	
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
		this.modeRemplissage = Remplissage.AUCUNE;
		this.indexFormeSelectionnee = 0;
		this.selectionEnCours = false;
		
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
		
		for(VueForme f : this.vueFormes) {
			f.affiche(g2D);
		}
		
		if(this.vueFormeOn) {
			this.vueFormeTemp.affiche(g2D);
		}
		if(this.selectionEnCours) {
			Forme formeSelectionnee = this.getVueFormes().get(this.getIndexFormeSelectionnee()).getForme();
			Rectangle carre = new Rectangle(formeSelectionnee.getPosition(),
					formeSelectionnee.getLargeur(),
					formeSelectionnee.getHauteur());
			carre.setCouleur(new Color(0, 0, 0, 35));
			VueRectangle carreSelection = new VueRectangle(carre);
			carreSelection.affiche(g2D);
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
	
	public int getIndexFormeSelectionnee() {
		return this.indexFormeSelectionnee;
	}
	
	public void setIndexFormeSelectionnee(int index) {
		this.indexFormeSelectionnee = index;
	}
}
