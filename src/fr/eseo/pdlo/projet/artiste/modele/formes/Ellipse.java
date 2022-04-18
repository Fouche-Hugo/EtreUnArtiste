package fr.eseo.pdlo.projet.artiste.modele.formes;

import fr.eseo.pdlo.projet.artiste.modele.Coordonnees;
import fr.eseo.pdlo.projet.artiste.modele.Remplissable;
import fr.eseo.pdlo.projet.artiste.modele.Remplissage;

import java.text.DecimalFormat;
import java.util.Locale;

public class Ellipse extends Forme implements Remplissable{
	
	private Remplissage modeRemplissage;
	
	public Ellipse(Coordonnees position, double largeur, double hauteur) {
		super(position, largeur, hauteur);
		this.setLargeur(largeur);
		this.setHauteur(hauteur);
		this.setPosition(position);
		this.modeRemplissage = Remplissage.AUCUNE;
	}

	public Ellipse(double largeur, double hauteur) {
		this(new Coordonnees(), largeur, hauteur);
	}

	public Ellipse(Coordonnees position) {
		this(position, Forme.LARGEUR_PAR_DEFAUT, Forme.HAUTEUR_PAR_DEFAUT);
	}

	public Ellipse(double x, double y, double largeur, double hauteur) {
		this(new Coordonnees(x, y), largeur, hauteur);
	}
	
	public Ellipse() {
		this(new Coordonnees());
	}
	
	@Override
	public void setLargeur(double largeur) {
		if(largeur < 0)
			throw new IllegalArgumentException("Largeur négative impossible pour une ellipse");
		super.setLargeur(largeur);
	}
	
	@Override
	public void setHauteur(double hauteur) {
		if(hauteur < 0)
			throw new IllegalArgumentException("Hauteur négative impossible pour une ellipse");
		super.setHauteur(hauteur);
	}
	
	public Remplissage getRemplissage() {
		return this.modeRemplissage;
	}
	
	public void setRemplissage(Remplissage nouveauModeRemplissage) {
		this.modeRemplissage = nouveauModeRemplissage;
	}
	
	@Override
	public double perimetre() {
		double a = this.getLargeur() / 2;
		double b = this.getHauteur() / 2;
		
		double h = Math.pow(a - b, 2) / Math.pow(a + b, 2);
		
		return Math.PI * (a + b) * (1 + 3 * h / (10 + Math.sqrt(4 - 3 * h)));
	}
	
	@Override
	public double aire() {
		return Math.PI * (this.getLargeur() / 2) * (this.getHauteur() / 2);
	}
	
	@Override
	public boolean contient(Coordonnees coords) {
		//équation de l'ellipse ((x-a)/X²)² + ((y-b)/Y)² = 1
		double a = this.getPosition().getAbscisse() + this.getLargeur() / 2;
		double b = this.getPosition().getOrdonnee() + this.getHauteur() / 2;
		double delX =  this.getLargeur() / 2;
		double delY = this.getHauteur() / 2;
		
		double equation = Math.pow((coords.getAbscisse()-a) / delX, 2)
				+ Math.pow((coords.getOrdonnee()-b) / delY, 2);
		if(equation <= 1)
			return true;
		return false;
	}
	
	@Override
	public String toString() {
		double a = Math.min(this.getLargeur() / 2, this.getHauteur() / 2);
		double b = Math.max(this.getLargeur() / 2, this.getHauteur() / 2);
		DecimalFormat df = new DecimalFormat();
		df.setMinimumFractionDigits(1);
		df.setMaximumFractionDigits(2);
		df.setGroupingSize(0);
		String msg = "[" + super.getClass().getSimpleName() + " " + this.getRemplissage().getMode()
				+ "] : pos " + this.getPosition().toString() + " petit rayon " + df.format(a);
		msg += " grand rayon " + df.format(b) + " périmètre : " + df.format(this.perimetre()) + " aire : " + df.format(this.aire());
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
