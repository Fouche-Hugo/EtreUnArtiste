package fr.eseo.pdlo.projet.artiste.modele.formes;

import java.text.DecimalFormat;
import java.util.Locale;

import fr.eseo.pdlo.projet.artiste.modele.Coordonnees;

public class Carre extends Rectangle {
	public Carre(Coordonnees coords, double largeur) {
		super(coords, largeur, largeur);
	}
	
	public Carre(Coordonnees coords) {
		this(coords, Forme.LARGEUR_PAR_DEFAUT);
	}
	
	public Carre(double x, double y, double largeur) {
		super(x, y, largeur, largeur);
	}
	
	public Carre(double largeur) {
		super(largeur, largeur);
	}
	
	public Carre() {
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
	
	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat();
		df.setMinimumFractionDigits(1);
		df.setMaximumFractionDigits(2);
		df.setGroupingSize(8);
		String msg = "[" + super.getClass().getSimpleName() + " " + this.getRemplissage().getMode() 
				+ "] : pos " + this.getPosition().toString() + " largeur " + df.format(this.getLargeur());
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
