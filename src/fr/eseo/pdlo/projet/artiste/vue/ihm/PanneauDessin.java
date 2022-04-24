package fr.eseo.pdlo.projet.artiste.vue.ihm;

import fr.eseo.pdlo.projet.artiste.modele.formes.Ligne;
import fr.eseo.pdlo.projet.artiste.modele.formes.Polygone;
import fr.eseo.pdlo.projet.artiste.controleur.outils.Outil;
import fr.eseo.pdlo.projet.artiste.modele.Coordonnees;
import fr.eseo.pdlo.projet.artiste.modele.Remplissable;
import fr.eseo.pdlo.projet.artiste.modele.Remplissage;
import fr.eseo.pdlo.projet.artiste.modele.formes.Rectangle;
import fr.eseo.pdlo.projet.artiste.modele.formes.Trace;
import fr.eseo.pdlo.projet.artiste.modele.formes.Carre;
import fr.eseo.pdlo.projet.artiste.modele.formes.Cercle;
import fr.eseo.pdlo.projet.artiste.modele.formes.Ellipse;
import fr.eseo.pdlo.projet.artiste.modele.formes.Etoile;
import fr.eseo.pdlo.projet.artiste.modele.formes.Forme;
import fr.eseo.pdlo.projet.artiste.modele.formes.FormeComposee;
import fr.eseo.pdlo.projet.artiste.vue.formes.VueRectangle;
import fr.eseo.pdlo.projet.artiste.vue.formes.VueTrace;
import fr.eseo.pdlo.projet.artiste.vue.formes.VueCarre;
import fr.eseo.pdlo.projet.artiste.vue.formes.VueCercle;
import fr.eseo.pdlo.projet.artiste.vue.formes.VueEllipse;
import fr.eseo.pdlo.projet.artiste.vue.formes.VueEtoile;
import fr.eseo.pdlo.projet.artiste.vue.formes.VueForme;
import fr.eseo.pdlo.projet.artiste.vue.formes.VueFormeComposee;
import fr.eseo.pdlo.projet.artiste.vue.formes.VueLigne;
import fr.eseo.pdlo.projet.artiste.vue.formes.VuePolygone;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.util.List;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sql.rowset.spi.SyncResolver;
import javax.swing.JPanel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

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

	//private boolean formeSelectionneeOn;
	private ArrayList<VueForme> vuesFormesSelectionnees;

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
		//this.indexFormeSelectionnee = 0;
		this.vuesFormesSelectionnees = new ArrayList<VueForme>();
		//this.formeSelectionneeOn = false;
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
		/*
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
		}*/
		//On teste si au moins une forme est selectionnée
		for(VueForme f : this.getVuesFormesSelectionnees()) {
			Forme formeSelectionnee = f.getForme();
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
	
	public void ouvrirProjet(String path) {
		//On ouvre un ficher
		Charset charset = Charset.forName("UTF-8");
		Path chemin = FileSystems.getDefault().getPath(path);
		try(BufferedReader reader = Files.newBufferedReader(chemin, charset)) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				Forme forme = this.getFormeFromString(line);
				VueForme vueForme = null;
				if(forme instanceof Carre) {
					vueForme = new VueCarre((Carre)forme);
				} else if(forme instanceof Cercle) {
					vueForme = new VueCercle((Cercle)forme);
				} else if(forme instanceof Ellipse) {
					vueForme = new VueEllipse((Ellipse)forme);
				} else if(forme instanceof Etoile) {
					vueForme = new VueEtoile((Etoile)forme);
				} else if(forme instanceof FormeComposee) {
					vueForme = new VueFormeComposee((FormeComposee)forme);
				} else if(forme instanceof Ligne) {
					vueForme = new VueLigne((Ligne)forme);
				} else if(forme instanceof Polygone) {
					vueForme = new VuePolygone((Polygone)forme);
				} else if(forme instanceof Rectangle) {
					vueForme = new VueRectangle((Rectangle)forme);
				} else if(forme instanceof Trace) {
					vueForme = new VueTrace((Trace)forme);
				}
				this.ajouterVueForme(vueForme);
			}
		} catch(IOException e) {
			System.out.println("Impossible d'ouvrir le fichier");
		}
	}

	private Forme getFormeFromString(String stringForme) {
		String[] lineSeparee = stringForme.split(":");
		Forme forme = null;
		String type = lineSeparee[0];
		//Initialisation de la forme
		switch(type) {
			case "Carre":
				forme = new Carre();
				break;
			case "Cercle":
				forme = new Cercle();
				break;
			case "Ellipse":
				forme = new Ellipse();
				break;
			case "Etoile":
				forme = new Etoile();
				break;
			case "FormeComposee":
				forme = new FormeComposee();
				break;
			case "Ligne":
				forme = new Ligne();
				break;
			case "Polygone":
				forme = new Polygone();
				break;
			case "Rectangle":
				forme = new Rectangle();
				break;
			case "Trace":
				forme = new Trace();
				break;
		}
		forme.getPosition().setAbscisse(Double.parseDouble(lineSeparee[1]));
		forme.getPosition().setOrdonnee(Double.parseDouble(lineSeparee[2]));
		forme.setLargeur(Double.parseDouble(lineSeparee[3]));
		forme.setHauteur(Double.parseDouble(lineSeparee[4]));
		forme.setAngle(Double.parseDouble(lineSeparee[5]));
		forme.setEpaisseur(Float.parseFloat(lineSeparee[6]));
		int rouge = Integer.parseInt(lineSeparee[7]);
		int vert = Integer.parseInt(lineSeparee[8]);
		int bleu = Integer.parseInt(lineSeparee[9]);
		forme.setCouleur(new Color(rouge, vert, bleu));
		rouge = Integer.parseInt(lineSeparee[10]);
		vert = Integer.parseInt(lineSeparee[11]);
		bleu = Integer.parseInt(lineSeparee[12]);
		forme.setCouleurSecondaire(new Color(rouge, vert, bleu));
		if(forme instanceof Remplissable) {
			((Remplissable)forme).setRemplissage(Remplissage.fromString(lineSeparee[13]));
		}
		if(forme instanceof Polygone) {
			((Polygone)forme).setNombreCotes(Integer.parseInt(lineSeparee[14]));
		} else if(forme instanceof Etoile) {
			((Etoile)forme).setNombreBranches(Integer.parseInt(lineSeparee[14]));
		} else if(forme instanceof FormeComposee) {
			String resteString = "";
			for(int i = 14;i < lineSeparee.length;i++) {
				resteString += lineSeparee[i] + ":";
			}
			resteString = resteString.substring(0, resteString.length()-1);
			String[] stringFormes = resteString.split("/");
			for(String s : stringFormes) {
				((FormeComposee)forme).ajouterForme(this.getFormeFromString(s));
			}
		}
		return forme;
	}

	public void enregistrerProjet(String path) {
		//On ouvre un fichier
		Charset charset = Charset.forName("UTF-8");
		Path chemin = FileSystems.getDefault().getPath(path);
		try(BufferedWriter writer = Files.newBufferedWriter(chemin, charset)) {
			for(VueForme f : this.getVueFormes()) {
				writer.append(this.getStringEnregistrement(f.getForme()));
				writer.append("\n");
			}
		} catch(IOException e) {
			System.out.println("Problème lors de l'écriture");
		}
	}

	private String getStringEnregistrement(Forme forme) {
		String stringForme = "";
		stringForme += forme.getClass().getSimpleName() + ":";
		stringForme += forme.getPosition().getAbscisse() + ":";
		stringForme += forme.getPosition().getOrdonnee() + ":";
		stringForme += forme.getLargeur() + ":";
		stringForme += forme.getHauteur() + ":";
		stringForme += forme.getAngle() + ":";
		stringForme += forme.getEpaisseur() + ":";
		stringForme += forme.getCouleur().getRed() + ":";
		stringForme += forme.getCouleur().getGreen() + ":";
		stringForme += forme.getCouleur().getBlue() + ":";
		stringForme += forme.getCouleurSecondaire().getRed() + ":";
		stringForme += forme.getCouleurSecondaire().getGreen() + ":";
		stringForme += forme.getCouleurSecondaire().getBlue() + ":";

		if(forme instanceof Remplissable) {
			stringForme += ((Remplissable)forme).getRemplissage().getMode() + ":";
		}

		if(forme instanceof Polygone) {
			stringForme += ((Polygone)forme).getNombreCotes() + ":";
		} else if(forme instanceof Etoile) {
			stringForme += ((Etoile)forme).getNombreBranches() + ":";
		} else if(forme instanceof Trace) {
			for(Coordonnees point : ((Trace)forme).getPoints()) {
				stringForme += point.getAbscisse() + ":";
				stringForme += point.getOrdonnee() + ":";
			}
		} else if(forme instanceof FormeComposee) {
			for(Forme fC : ((FormeComposee)forme).getFormes()) {
				stringForme += this.getStringEnregistrement(fC) + "/";
			}
			stringForme = stringForme.substring(0, stringForme.length()-1);
		}
		return stringForme;
	}

	public void enregistrerImage(String path) {

		BufferedImage bufferedImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2D = bufferedImage.createGraphics();
		
		g2D.setRenderingHints(this.getAntiAliasing());
		for(VueForme f : this.vueFormes) {
			f.affiche(g2D);
		}

		RenderedImage rendImage = bufferedImage;

		File file = new File(path + ".png");
		try {
			ImageIO.write(rendImage, "png", file);
		} catch(IOException e) {
			System.out.println("Impossible d'enregistrer le fichier");
		}
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
	
	public boolean estDevant(VueForme devant, VueForme derriere) {
		if(this.getVueFormes().indexOf(devant) > this.getVueFormes().indexOf(derriere)) {
			return true;
		}
		return false;
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

	public ArrayList<VueForme> getVuesFormesSelectionnees() {
		return this.vuesFormesSelectionnees;
	}

	/*
	public boolean getFormeSelectionneeOn() {
		return this.formeSelectionneeOn;
	}

	public void setFormeSelectionneeOn(boolean selection) {
		this.formeSelectionneeOn = selection;
		this.repaint();
	}
	*/

	public boolean getGrilleAffichee() {
		return this.grilleAffichee;
	}

	public void setGrilleAffichee(boolean grilleAffichee) {
		this.grilleAffichee = grilleAffichee;
	}
}
