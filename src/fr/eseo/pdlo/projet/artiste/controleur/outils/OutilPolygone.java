package fr.eseo.pdlo.projet.artiste.controleur.outils;

import java.awt.event.MouseEvent;

import fr.eseo.pdlo.projet.artiste.modele.Coordonnees;
import fr.eseo.pdlo.projet.artiste.modele.formes.Ligne;
import fr.eseo.pdlo.projet.artiste.modele.formes.Polygone;
import fr.eseo.pdlo.projet.artiste.vue.formes.VueForme;
import fr.eseo.pdlo.projet.artiste.vue.formes.VuePolygone;

public class OutilPolygone extends OutilForme {
	
	private int nCotes;
	private Coordonnees coordsPremierClic;
	
	public OutilPolygone(int nbCotes) {
		this.nCotes = nbCotes;
	}
	
	@Override
	protected VueForme creerVueForme() {
		Polygone poly = new Polygone(this.getDebut(),
				Math.max(Math.abs(this.getFin().getAbscisse() - this.getDebut().getAbscisse()),
						Math.abs(this.getFin().getOrdonnee() - this.getDebut().getOrdonnee())),
						this.nCotes);
		poly.setCouleur(this.getPanneauDessin().getCouleurCourante());
		poly.setRemplissage(this.getPanneauDessin().getModeRemplissageCourant());
		poly.setEpaisseur(this.getPanneauDessin().getEpaisseurCourante());
		return new VuePolygone(poly);
	}
	
	@Override
	public void mousePressed(MouseEvent event) {
		this.coordsPremierClic = new Coordonnees(event.getX(), event.getY());
		super.mousePressed(event);
	}
	
	@Override
	public void mouseClicked(MouseEvent event) {
		super.mouseClicked(event);
	}

	@Override
	public void mouseDragged(MouseEvent event) {
		this.majCoords(event);
		this.getPanneauDessin().setVueFormeTemp(this.creerVueForme());
		this.getPanneauDessin().setVueFormeOn(true);
	}
	
	@Override
	public void mouseReleased(MouseEvent event) {
		if (event.getX() != this.getDebut().getAbscisse() || event.getY() != this.getDebut().getOrdonnee()) {
			this.majCoords(event);
			this.getPanneauDessin().ajouterVueForme(this.creerVueForme());
			this.getPanneauDessin().setVueFormeOn(false);
		}
	}
	
	private double getSigne1(double nombre) {
		if(nombre < 0) {
			return -1;
		} else {
			return 1;
		}
	}
	
	private void majCoords(MouseEvent event) {
		this.setDebut(this.coordsPremierClic);
		Ligne ligneTemp = new Ligne(this.getDebut(),
				(double)event.getX() - this.getDebut().getAbscisse(),
				(double)event.getY() - this.getDebut().getOrdonnee());
		double largeurAbs = Math.max(Math.abs(ligneTemp.getLargeur()), Math.abs(ligneTemp.getHauteur()));
		if(largeurAbs == Math.abs(ligneTemp.getLargeur())) {
			ligneTemp.setHauteur(largeurAbs * this.getSigne1(ligneTemp.getHauteur()));
		} else {
			ligneTemp.setLargeur(largeurAbs * this.getSigne1(ligneTemp.getLargeur()));
		}
		this.setDebut(new Coordonnees(ligneTemp.getCadreMinX(), ligneTemp.getCadreMinY()));
		this.setFin(new Coordonnees(ligneTemp.getCadreMaxX(), ligneTemp.getCadreMaxY()));
	}
}
