package fr.eseo.pdlo.projet.artiste.modele.formes;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;

import fr.eseo.pdlo.projet.artiste.modele.Coordonnees;
import fr.eseo.pdlo.projet.artiste.modele.Remplissable;
import fr.eseo.pdlo.projet.artiste.modele.Remplissage;

public class Polygone extends Forme implements Remplissable {
	
	public static final int NB_COTES_PAR_DEFAUT = 6;
	private Remplissage modeRemplissage;
	private int nCotes;
	
	public Polygone(Coordonnees coords, double largeur, int n) {
		super(coords, largeur, largeur);
		this.nCotes = n;
		this.modeRemplissage = Remplissage.AUCUNE;
	}
	
	public Polygone(Coordonnees coords) {
		this(coords, Forme.LARGEUR_PAR_DEFAUT, Polygone.NB_COTES_PAR_DEFAUT);
	}
	
	public Polygone(double largeur) {
		this(new Coordonnees(), largeur, Polygone.NB_COTES_PAR_DEFAUT);
	}
	
	public Polygone() {
		this(Forme.LARGEUR_PAR_DEFAUT);
	}
	
	@Override
	public void setLargeur(double diametre) {
		super.setLargeur(diametre);
		super.setHauteur(diametre);
	}
	
	@Override
	public void setHauteur(double diametre) {
		this.setLargeur(diametre);
	}
	
	public int getNombreCotes() {
		return this.nCotes;
	}
	
	public void setNombreCotes(int nbCotes) {
		this.nCotes = nbCotes;
	}
	
	public Remplissage getRemplissage() {
		return this.modeRemplissage;
	}
	
	public void setRemplissage(Remplissage nouveauModeRemplissage) {
		this.modeRemplissage = nouveauModeRemplissage;
	}
	
	@Override
	public double perimetre() {
		double p = 0;
		ArrayList<Coordonnees> points = this.getPoints();
		for(int i=0;i<points.size()-1;i++) {
			Ligne ligneTemp = new Ligne(points.get(i),
					points.get(i+1).getAbscisse() - points.get(i).getAbscisse(),
					points.get(i+1).getOrdonnee() - points.get(i).getOrdonnee());
			p += ligneTemp.perimetre();
		}
		return p;
	}
	
	@Override
	public double aire() {
		return 0;
	}
	
	@Override
	public boolean contient(Coordonnees coords) {
		return false;
	}
	
	public ArrayList<Coordonnees> getPoints() {
		ArrayList<Coordonnees> points = new ArrayList<Coordonnees>();
		for(int i=0;i<this.nCotes;i++) {
			double angle = i * (2 * Math.PI) / this.getNombreCotes();
			double relativeAbscisse = Math.cos(angle) * this.getLargeur() / 2;
			double relativeOrdonnee = Math.sin(angle) * this.getLargeur() / 2;
			
			points.add(new Coordonnees(this.getAbscisse() + (this.getLargeur() / 2) + relativeAbscisse,
					this.getOrdonnee() + (this.getLargeur() / 2) + relativeOrdonnee));
		}
		return points;
	}
	
	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat();
		df.setMinimumFractionDigits(1);
		df.setMaximumFractionDigits(2);
		df.setGroupingSize(8);
		String msg = "[" + super.getClass().getSimpleName() + " " + this.getRemplissage().getMode() 
				+ "] : pos " + this.getPosition().toString() + " largeur " + df.format(this.getLargeur());
		msg += " nombre de côtés : " + this.getNombreCotes();
		msg += " périmètre : " + df.format(this.perimetre()) + " aire : " + df.format(this.aire());
		if(Locale.getDefault().getLanguage().equals("fr")) {
			msg += " couleur = R" + this.getCouleur().getRed() + ",V" + this.getCouleur().getGreen() + ",B"
					+ this.getCouleur().getBlue();
		} else {
			msg += " couleur = R" + this.getCouleur().getRed() + ",G" + this.getCouleur().getGreen() + ",B"
					+ this.getCouleur().getBlue();
		}
		return msg;
	}
}
