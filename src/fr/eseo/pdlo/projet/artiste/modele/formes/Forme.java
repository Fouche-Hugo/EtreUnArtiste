package fr.eseo.pdlo.projet.artiste.modele.formes;
import fr.eseo.pdlo.projet.artiste.modele.Coordonnees;
import fr.eseo.pdlo.projet.artiste.modele.Coloriable;
import java.awt.Color;

public abstract class Forme implements Coloriable{

	public static final double LARGEUR_PAR_DEFAUT = 10;
	public static final double HAUTEUR_PAR_DEFAUT = 10;
	public static final Color COULEUR_PAR_DEFAUT = javax.swing.UIManager.getColor("Panel.foreground");
	public static final float EPAISSEUR_PAR_DEFAUT = 1;
	
	private float epaisseur;
	
	private Coordonnees position;
	private double largeur;
	private double hauteur;
	private Color couleur;
	private Color couleurSecondaire;
	private double angle;
	
	public Forme(Coordonnees position, double largeur, double hauteur) {
		this.position = position;
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.couleur = Forme.COULEUR_PAR_DEFAUT;
		this.couleurSecondaire = Forme.COULEUR_PAR_DEFAUT;
		this.epaisseur = Forme.EPAISSEUR_PAR_DEFAUT;
		this.angle = 0;
	}
	
	public Forme(double x, double y, double largeur, double hauteur) {
		this(new Coordonnees(x, y), largeur, hauteur);
	}
	
	public Forme(double largeur, double hauteur) {
		this(new Coordonnees(), largeur, hauteur);
	}
	
	public Forme(Coordonnees position) {
		this(position, Forme.LARGEUR_PAR_DEFAUT, Forme.HAUTEUR_PAR_DEFAUT);
	}
	
	public Forme() {
		this(Forme.LARGEUR_PAR_DEFAUT, Forme.HAUTEUR_PAR_DEFAUT);
	}

	public Coordonnees getPosition() {
		return position;
	}

	public void setPosition(Coordonnees position) {
		this.position = position;
	}
	
	public void setPosition(double x, double y) {
		this.setPosition(new Coordonnees(x, y));
	}

	public double getLargeur() {
		return largeur;
	}

	public void setLargeur(double largeur) {
		this.largeur = largeur;
	}

	public double getHauteur() {
		return hauteur;
	}

	public void setHauteur(double hauteur) {
		this.hauteur = hauteur;
	}
	
	public double getAbscisse() {
		return this.position.getAbscisse();
	}
	
	public void setAbscisse(double x) {
		this.position.setAbscisse(x);
	}
	
	public double getOrdonnee() {
		return this.position.getOrdonnee();
	}
	
	public void setOrdonnee(double y) {
		this.position.setOrdonnee(y);
	}
	
	public void deplacerVers(double x, double y) {
		this.position.deplacerVers(x, y);
	}
	
	public void deplacerVers(Coordonnees nouvellePosition) {
		this.position.deplacerVers(nouvellePosition);
	}
	
	public void deplacerDe(double x, double y) {
		this.position.deplacerDe(x, y);
	}
	
	public double getCadreMinX() {
		if(this.getLargeur() < 0)
			return this.getAbscisse() + this.getLargeur();
		return this.getAbscisse();
	}
	
	public double getCadreMaxX() {
		if(this.getLargeur() > 0)
			return this.getAbscisse() + this.getLargeur();
		return this.getAbscisse();
	}
	
	public double getCadreMinY() {
		if(this.getHauteur() < 0)
			return this.getOrdonnee() + this.getHauteur();
		return this.getOrdonnee();
	}
	
	public double getCadreMaxY() {
		if(this.getHauteur() > 0)
			return this.getOrdonnee() + this.getHauteur();
		return this.getOrdonnee();
	}
	
	public Color getCouleur() {
		return this.couleur;
	}
	
	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

	public Color getCouleurSecondaire() {
		return this.couleurSecondaire;
	}
	
	public void setCouleurSecondaire(Color couleur) {
		this.couleurSecondaire = couleur;
	}
	
	public float getEpaisseur() {
		return this.epaisseur;
	}
	
	public void setEpaisseur(float epaisseur) {
		this.epaisseur = epaisseur;
	}

	public double getAngle() {
		return this.angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	abstract public double perimetre();
	abstract public double aire();
	abstract public boolean contient(Coordonnees coords);
	
	@Override
	public String toString() {
		String msg = "[" + super.getClass().getSimpleName() + "] pos: " +
				this.position.toString() + " dim : <" + this.getLargeur() +
				"> x <" + this.getHauteur() + ">";
		msg += "\npérimètre: <" + this.perimetre() + "> aire: <" + this.aire() + ">";
		return msg;
	}
	
	public boolean equals(Forme f) {
		if(super.getClass().getSimpleName().equals(f.getClass().getSimpleName()) &&
				this.position.equals(f.getPosition()) &&
				this.getLargeur() == f.getLargeur() && this.getHauteur() == f.getHauteur())
			return true;
		return false;
	}
}
