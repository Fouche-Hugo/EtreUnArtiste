package fr.eseo.pdlo.projet.artiste.modele.formes;

import java.util.ArrayList;

import fr.eseo.pdlo.projet.artiste.modele.Coordonnees;

public class Trace extends Forme{
	
	private ArrayList<Coordonnees> points;
	
	public Trace(Coordonnees pos1, Coordonnees pos2) {
		super(pos1.getAbscisse(), pos1.getOrdonnee(), 1, 1);
		this.points = new ArrayList<Coordonnees>();
		this.ajouterPoint(pos1);
		this.ajouterPoint(pos2);
	}
	
	public Trace() {
		this(new Coordonnees(), new Coordonnees());
	}
	
	public ArrayList<Coordonnees> getPoints() {
		return this.points;
	}
	
	public void ajouterPoint(Coordonnees c) {
		//Test si le point ajouté est identique au dernier
		//On teste d'abord si il y a un point
		boolean doitEtreAjoute = false;
		if(this.getPoints().size() == 0) {
			doitEtreAjoute = true;
		}
		else if(!this.getPoints().get(this.getPoints().size()-1).equals(c)) {
			doitEtreAjoute = true;
		}
		if(doitEtreAjoute) {
			this.points.add(c);
			this.updateInfos(c);
		}
	}
	
	private void updateInfos(Coordonnees c) {
		if(this.getAbscisse() > c.getAbscisse()) {
			super.setLargeur(this.getLargeur()+Math.abs(this.getAbscisse()-c.getAbscisse()));
			super.setAbscisse(c.getAbscisse());
		}
		else if(this.getAbscisse()+this.getLargeur() < c.getAbscisse()) {
			super.setLargeur(c.getAbscisse()-this.getAbscisse());
		}
		if(this.getOrdonnee() > c.getOrdonnee()) {
			super.setHauteur(this.getHauteur()+Math.abs(this.getOrdonnee()-c.getOrdonnee()));
			super.setOrdonnee(c.getOrdonnee());
		}
		else if(this.getOrdonnee()+this.getHauteur() < c.getOrdonnee()) {
			super.setHauteur(c.getOrdonnee()-this.getOrdonnee());
		}
	}
	
	@Override
	public void deplacerDe(double x, double y) {
		this.getPosition().deplacerDe(x, y);
		//On déplace aussi tous les points
		for(Coordonnees c : this.points) {
			c.deplacerDe(x, y);
		}
	}
	
	@Override
	public void deplacerVers(double x, double y) {
		this.deplacerDe(x-this.getAbscisse(), y-this.getOrdonnee());
	}
	
	@Override
	public void deplacerVers(Coordonnees c) {
		this.deplacerVers(c.getAbscisse(), c.getOrdonnee());
	}
	
	@Override
	public void setPosition(Coordonnees c) {
		this.deplacerVers(c);
	}
	
	@Override
	public void setPosition(double x, double y) {
		this.deplacerVers(new Coordonnees(x, y));
	}
	
	@Override
	public void setAbscisse(double x) {
		this.deplacerVers(x, this.getOrdonnee());
	}
	
	@Override
	public void setOrdonnee(double y) {
		this.deplacerVers(this.getAbscisse(), y);
	}
	
	@Override
	public void setLargeur(double nouvelleLargeur) {
		double ratio = nouvelleLargeur / this.getLargeur();
		for(Coordonnees c : this.getPoints()) {
			c.setAbscisse(this.getAbscisse() + (c.getAbscisse()-this.getAbscisse())*ratio);
		}
		super.setLargeur(nouvelleLargeur);
	}
	
	@Override
	public void setHauteur(double nouvelleHauteur) {
		double ratio = nouvelleHauteur / this.getHauteur();
		for(Coordonnees c : this.getPoints()) {
			c.setOrdonnee(this.getOrdonnee() + (c.getOrdonnee()-this.getOrdonnee())*ratio);
		}
		super.setHauteur(nouvelleHauteur);
	}
	
	@Override
	public double perimetre() {
		double p = 0;
		for(int i=0;i<this.getPoints().size()-1;i++) {
			p += this.getPoints().get(i).distanceVers(this.getPoints().get(i+1));
		}
		return p;
	}
	
	@Override
	public double aire() {
		return 0;
	}
	
	@Override
	public String toString() {
		String msg = "[" + super.getClass().getSimpleName() + "] pos: (<" + this.getAbscisse() + ">, <"
	+ this.getOrdonnee() + ">) dim : (<" + this.getLargeur() + ">, <" + this.getHauteur()+ ">)";
		msg += " - longueur: " + this.perimetre() + " - nbLignes: " + this.points.size();
		return msg;
	}
	
	public boolean equals(Trace t) {
		if(this.getPoints().size() == t.getPoints().size()) {
			return false;
		}
		boolean pointsTousEgaux = true;
		for(int i = 0;i < this.getPoints().size();i++) {
			if(!this.getPoints().get(i).equals(t.getPoints().get(i))) {
				pointsTousEgaux = false;
			}
		}
		return pointsTousEgaux;
	}
	
	@Override
	public boolean contient(Coordonnees coords) {
		for(int i=0;i<this.getPoints().size()-1;i++) {
			Ligne ligneTemp = new Ligne(this.getPoints().get(i),
					this.getPoints().get(i+1).getAbscisse() - this.getPoints().get(i).getAbscisse(),
					this.getPoints().get(i+1).getOrdonnee() - this.getPoints().get(i).getOrdonnee());
			if(ligneTemp.contient(coords)) {
				return true;
			}
		}
		return false;
	}
}
