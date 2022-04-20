package fr.eseo.pdlo.projet.artiste.modele.formes;

import java.text.DecimalFormat;
import java.util.Locale;

import fr.eseo.pdlo.projet.artiste.modele.Coordonnees;
import fr.eseo.pdlo.projet.artiste.modele.Remplissable;
import fr.eseo.pdlo.projet.artiste.modele.Remplissage;

public class Rectangle extends Forme implements Remplissable {
private Remplissage modeRemplissage;
	
	public Rectangle(Coordonnees position, double largeur, double hauteur) {
		super(position, largeur, hauteur);
		this.setLargeur(largeur);
		this.setHauteur(hauteur);
		this.setPosition(position);
		this.modeRemplissage = Remplissage.AUCUNE;
	}

	public Rectangle(double largeur, double hauteur) {
		this(new Coordonnees(), largeur, hauteur);
	}

	public Rectangle(Coordonnees position) {
		this(position, Forme.LARGEUR_PAR_DEFAUT, Forme.HAUTEUR_PAR_DEFAUT);
	}

	public Rectangle(double x, double y, double largeur, double hauteur) {
		this(new Coordonnees(x, y), largeur, hauteur);
	}
	
	public Rectangle() {
		this(new Coordonnees());
	}
	
	@Override
	public void setLargeur(double largeur) {
		if(largeur < 0)
			throw new IllegalArgumentException("Largeur négative impossible pour un rectangle");
		super.setLargeur(largeur);
	}
	
	@Override
	public void setHauteur(double hauteur) {
		if(hauteur < 0)
			throw new IllegalArgumentException("Hauteur négative impossible pour un rectangle");
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
		return 2 * (this.getLargeur() + this.getHauteur());
	}
	
	@Override
	public double aire() {
		return this.getLargeur() * this.getHauteur();
	}
	
	@Override
	public boolean contient(Coordonnees coords) {
		double a = this.getPosition().getAbscisse() + this.getLargeur() / 2;
		double b = this.getPosition().getOrdonnee() + this.getHauteur() / 2;

		double x = (coords.getAbscisse() - a) * Math.cos(-this.getAngle())
			- (coords.getOrdonnee() - b) * Math.sin(-this.getAngle());
		double y = (coords.getAbscisse() - a) * Math.sin(-this.getAngle())
			+ (coords.getOrdonnee() - b) * Math.cos(-this.getAngle());

		Coordonnees coordsRepere = new Coordonnees(x, y);

		double xMin = - this.getLargeur() / 2;
		double yMin = - this.getHauteur() / 2;

		double xMax = this.getLargeur() / 2;
		double yMax = this.getHauteur() / 2;
		
		if(coordsRepere.getAbscisse() >= xMin && coordsRepere.getAbscisse() <= xMax
			&& coordsRepere.getOrdonnee() >= yMin && coordsRepere.getOrdonnee() <= yMax)
			return true;
		return false;
		
		/*
		if(coords.getAbscisse() >= this.getCadreMinX() && coords.getAbscisse() <= this.getCadreMaxX()
				&& coords.getOrdonnee() >= this.getCadreMinY() && coords.getOrdonnee() <= this.getCadreMaxY())
			return true;
		return false;
		*/
	}
	
	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat();
		df.setMinimumFractionDigits(1);
		df.setMaximumFractionDigits(2);
		df.setGroupingSize(0);
		String msg = "[" + super.getClass().getSimpleName() + " " + this.getRemplissage().getMode()
				+ "] : pos " + this.getPosition().toString() + " largeur " + df.format(this.getLargeur());
		msg += " hauteur " + df.format(this.getHauteur()) + " périmètre : " + df.format(this.perimetre()) + " aire : "
				+ df.format(this.aire());
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
