package fr.eseo.pdlo.projet.artiste.modele.formes;

import java.text.DecimalFormat;
import java.util.Locale;

import fr.eseo.pdlo.projet.artiste.modele.Coordonnees;

public class Cercle extends Ellipse{
	public Cercle(Coordonnees coords, double diametre) {
		super(coords, diametre, diametre);
	}
	
	public Cercle(Coordonnees coords) {
		this(coords, Forme.LARGEUR_PAR_DEFAUT);
	}
	
	public Cercle(double x, double y, double diametre) {
		super(x, y, diametre, diametre);
	}
	
	public Cercle(double diametre) {
		super(diametre, diametre);
	}
	
	public Cercle() {
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
	
	public double getDiametre() {
		return this.getHauteur();
	}
	
	public double getRayon() {
		return this.getHauteur() / 2;
	}
	
	public void setRayon(double rayon) {
		this.setLargeur(rayon*2);
	}
	
	public void setDiametre(double diametre) {
		this.setLargeur(diametre);
	}
	
	@Override
	public double perimetre() {
		return 2 * Math.PI * this.getRayon();
	}
	
	
	@Override
	public double aire() {
		return Math.PI * Math.pow(this.getRayon(), 2);
	}
	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat();
		df.setMinimumFractionDigits(1);
		df.setMaximumFractionDigits(2);
		df.setGroupingSize(8);
		String msg = "[" + super.getClass().getSimpleName() + " " + this.getRemplissage().getMode() 
				+ "] : pos " + this.getPosition().toString() + " rayon " + df.format(this.getRayon());
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
