package fr.eseo.pdlo.projet.artiste.modele.formes;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;

import fr.eseo.pdlo.projet.artiste.modele.Coordonnees;
import fr.eseo.pdlo.projet.artiste.modele.Remplissable;
import fr.eseo.pdlo.projet.artiste.modele.Remplissage;

public class Etoile extends Forme implements Remplissable {
    
    public static final int NB_COTES_PAR_DEFAUT = 6;
	private Remplissage modeRemplissage;
	private int nBranches;
	
	public Etoile(Coordonnees coords, double largeur, int n) {
		super(coords, largeur, largeur);
		this.nBranches = n;
		this.modeRemplissage = Remplissage.AUCUNE;
	}
	
	public Etoile(Coordonnees coords) {
		this(coords, Forme.LARGEUR_PAR_DEFAUT, Etoile.NB_COTES_PAR_DEFAUT);
	}
	
	public Etoile(double largeur) {
		this(new Coordonnees(), largeur, Etoile.NB_COTES_PAR_DEFAUT);
	}
	
	public Etoile() {
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
	
	public int getNombreBranches() {
		return this.nBranches;
	}
	
	public void setNombreBranches(int nbBranches) {
		this.nBranches = nbBranches;
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
		double centreX = this.getPosition().getAbscisse() + this.getLargeur() / 2;
		double centreY = this.getPosition().getOrdonnee() + this.getHauteur() / 2;
		Coordonnees centre = new Coordonnees(centreX, centreY);

		double aire = 0;
		ArrayList<Coordonnees> points = this.getPoints();
		for(int i=0;i<points.size();i++) {
			Coordonnees pointB = points.get(i);
			Coordonnees pointC = new Coordonnees();
			if(i+1 < points.size()) {
				pointC = points.get(i+1);
			} else {
				pointC = points.get(0);
			}
			double base = pointB.distanceVers(pointC);
			Coordonnees centreBase = new Coordonnees((pointB.getAbscisse() + pointC.getAbscisse()) / 2,
				(pointB.getOrdonnee() + pointC.getOrdonnee()) / 2);
			aire += base * centreBase.distanceVers(centre) / 2;
		}
		return aire;
	}
	
	@Override
	public boolean contient(Coordonnees coords) {
		//On test si le point est à l'intérieur d'un des triangles du Etoile

		double centreX = this.getPosition().getAbscisse() + this.getLargeur() / 2;
		double centreY = this.getPosition().getOrdonnee() + this.getHauteur() / 2;

		double x = (coords.getAbscisse() - centreX) * Math.cos(-this.getAngle())
			- (coords.getOrdonnee() - centreY) * Math.sin(-this.getAngle());
		double y = (coords.getAbscisse() - centreX) * Math.sin(-this.getAngle())
			+ (coords.getOrdonnee() - centreY) * Math.cos(-this.getAngle());

		Coordonnees coordsRepere = new Coordonnees(x, y);

		Coordonnees a = new Coordonnees(0, 0);
		for(int i = 0;i < this.getPoints().size();i++) {
			//Calcul des produits vectoriels
			Coordonnees b = this.getPoints().get(i);
			b.deplacerDe(-centreX, -centreY);
			Coordonnees c = new Coordonnees();
			if(i+1 < this.getPoints().size()) {
				c = this.getPoints().get(i+1);
			} else {
				c = this.getPoints().get(0);
			}
			c.deplacerDe(-centreX, -centreY);
			double v1 = (a.getAbscisse()-coordsRepere.getAbscisse()) * (b.getOrdonnee()-coordsRepere.getOrdonnee())
				- (a.getOrdonnee()-coordsRepere.getOrdonnee()) * (b.getAbscisse()-coordsRepere.getAbscisse());
			double v2 = (b.getAbscisse()-coordsRepere.getAbscisse()) * (c.getOrdonnee()-coordsRepere.getOrdonnee())
				- (b.getOrdonnee()-coordsRepere.getOrdonnee()) * (c.getAbscisse()-coordsRepere.getAbscisse());
			double v3 = (c.getAbscisse()-coordsRepere.getAbscisse()) * (a.getOrdonnee()-coordsRepere.getOrdonnee())
				- (c.getOrdonnee()-coordsRepere.getOrdonnee()) * (a.getAbscisse()-coordsRepere.getAbscisse());
			if(this.memesSignes(v1, v2, v3)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean memesSignes(double v1, double v2, double v3) {
		if(v1 < 0 && v2 < 0 && v3 < 0) {
			return true;
		} else if(v1 > 0 && v2 > 0 && v3 > 0) {
			return true;
		}
		return false;
	}

	public ArrayList<Coordonnees> getPoints() {
		ArrayList<Coordonnees> points = new ArrayList<Coordonnees>();
		for(int i=0;i<this.getNombreBranches();i++) {
			double angle = i * (2 * Math.PI) / this.getNombreBranches();
			double relativeAbscisse = Math.cos(angle) * this.getLargeur() / 2;
			double relativeOrdonnee = Math.sin(angle) * this.getLargeur() / 2;
			
			points.add(new Coordonnees(this.getAbscisse() + (this.getLargeur() / 2) + relativeAbscisse,
					this.getOrdonnee() + (this.getLargeur() / 2) + relativeOrdonnee));

            double angleEtDemi = (i + 0.5) * (2 * Math.PI) / this.getNombreBranches();
            double relativeAbscisseEtDemi = Math.cos(angleEtDemi) * ((this.getLargeur() / 2) / ((1 + Math.sqrt(5) / 2)));
			double relativeOrdonneeEtDemi = Math.sin(angleEtDemi) * ((this.getLargeur() / 2) / ((1 + Math.sqrt(5) / 2)));

            points.add(new Coordonnees(this.getAbscisse() + (this.getLargeur() / 2) + relativeAbscisseEtDemi,
					this.getOrdonnee() + (this.getLargeur() / 2) + relativeOrdonneeEtDemi));
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
		msg += " nombre de branches : " + this.getNombreBranches();
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
