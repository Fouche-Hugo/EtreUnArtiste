package fr.eseo.pdlo.projet.artiste.modele;

import java.text.DecimalFormat;

public class Coordonnees {
	
	private double x;
	private double y;
	public static final double ABSCISSE_PAR_DEFAUT = 0;
	public static final double ORDONNEE_PAR_DEFAUT = 0;

	public Coordonnees() {
		this(Coordonnees.ABSCISSE_PAR_DEFAUT, Coordonnees.ORDONNEE_PAR_DEFAUT);
	}

	public Coordonnees(double abscisse, double ordonnee) {
		this.x = abscisse;
		this.y = ordonnee;
	}
	
	public void deplacerVers(double nouvelleAbscisse, double nouvelleOrdonnee) {
		this.setAbscisse(nouvelleAbscisse); 	
		this.setOrdonnee(nouvelleOrdonnee);
	}
	
	public void deplacerVers(Coordonnees coords) {
		this.setAbscisse(coords.getAbscisse());
		this.setOrdonnee(coords.getOrdonnee());
	}

	public void deplacerDe(double deltaX, double deltaY) {
		this.deplacerVers(getAbscisse()+deltaX, getOrdonnee()+deltaY);
	}

	public double distanceVers(Coordonnees autreCoordonnees) {
		return Math.sqrt(Math.pow(autreCoordonnees.getAbscisse() - this.getAbscisse(), 2)
				+Math.pow(autreCoordonnees.getOrdonnee() - this.getOrdonnee(), 2));
	}

	public double angleVers(Coordonnees autreCoordonnees) {
		double xFinal = autreCoordonnees.getAbscisse() - this.getAbscisse();
		double yFinal = autreCoordonnees.getOrdonnee() - this.getOrdonnee();
		return Math.atan2(yFinal, xFinal);
	}
	
	public double getAbscisse() {
		return this.x;
	}
	
	public void setAbscisse(double xFinal) {
		this.x = xFinal;
	}

	public double getOrdonnee() {
		return this.y;
	}
	
	public void setOrdonnee(double yFinal) {
		this.y = yFinal;
	}
	
	public String toString() {
		DecimalFormat df = new DecimalFormat();
		df.setMinimumFractionDigits(1);
		df.setMaximumFractionDigits(2);
		return "(" + df.format(this.getAbscisse()) + " , " + df.format(this.getOrdonnee()) + ")";
	}
	
	public boolean equals(Coordonnees coords) {
		boolean retour = false;
		if(this.getAbscisse() == coords.getAbscisse() && this.getOrdonnee() == coords.getOrdonnee()) {
			retour = true;
		}
		return retour;
	}
}