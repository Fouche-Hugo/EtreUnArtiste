package fr.eseo.pdlo.projet.artiste.modele.formes;

import fr.eseo.pdlo.projet.artiste.modele.Coordonnees;
import java.text.DecimalFormat;
import java.util.Locale;

public class Ligne extends Forme{
	
	public static final double EPSILON = 0.2;
	
	public Ligne(Coordonnees position, double largeur, double hauteur) {
		super(position, largeur, hauteur);
	}
	
	public Ligne(double x, double y, double largeur, double hauteur) {
		super(x, y, largeur, hauteur);
	}
	
	public Ligne(Coordonnees position) {
		super(position);
	}
	
	public Ligne(double largeur, double hauteur) {
		super(largeur, hauteur);
	}
	
	public Ligne() {
		super();
	}
	
	public Coordonnees getC1() {
		return this.getPosition();
	}
	
	public Coordonnees getC2() {
		return new Coordonnees(this.getPosition().getAbscisse()+this.getLargeur(), this.getPosition().getOrdonnee()+this.getHauteur());
	}
	
	public void setC1(Coordonnees c1) {
		Coordonnees p2 = this.getC2();
		this.setPosition(c1);
		this.setLargeur(p2.getAbscisse()-this.getAbscisse());
		this.setHauteur(p2.getOrdonnee()-this.getOrdonnee());
	}
	
	public void setC1(double x, double y) {
		this.setC1(new Coordonnees(x, y));
	}
	
	public void setC2(Coordonnees c2) {
		this.setLargeur(c2.getAbscisse()-this.getAbscisse());
		this.setHauteur(c2.getOrdonnee()-this.getOrdonnee());
	}
	
	
	public void setC2(double x, double y) {
		this.setC2(new Coordonnees(x, y));
	}
	
	@Override
	public double perimetre() {
		return Math.sqrt(Math.pow(this.getLargeur(), 2)+Math.pow(this.getHauteur(), 2));
	}
	
	@Override
	public double aire() {
		return 0;
	}
	
	@Override
	public boolean contient(Coordonnees coords) {
		if(this.getC1().distanceVers(coords) + this.getC2().distanceVers(coords) - this.getC1().distanceVers(this.getC2()) <= Ligne.EPSILON) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat();
		df.setMinimumFractionDigits(1);
		df.setMaximumFractionDigits(2);
		String msg = "[" + super.getClass().getSimpleName() + "] c1 : " + this.getC1().toString() + " c2 : " + this.getC2().toString();
		double angle = this.getC1().angleVers(this.getC2()) * 360 / (2*Math.PI);
		if(angle < 0)
			angle += 360;
		msg += " longueur : " + df.format(this.perimetre()) + " angle : " + df.format(angle) + "°";
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
