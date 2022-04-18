package fr.eseo.pdlo.projet.artiste.controleur.outils;

import java.awt.event.MouseEvent;

import fr.eseo.pdlo.projet.artiste.modele.Coordonnees;
import fr.eseo.pdlo.projet.artiste.modele.formes.Rectangle;
import fr.eseo.pdlo.projet.artiste.vue.formes.VueRectangle;
import fr.eseo.pdlo.projet.artiste.vue.formes.VueForme;

public class OutilRectangle extends OutilForme{
	@Override
	protected VueForme creerVueForme() {
		if(this.getDebut().equals(this.getFin())) {
			Rectangle rectangle = new Rectangle(this.getDebut());
			rectangle.setCouleur(this.getPanneauDessin().getCouleurCourante());
			rectangle.setRemplissage(this.getPanneauDessin().getModeRemplissageCourant());
			rectangle.setEpaisseur(this.getPanneauDessin().getEpaisseurCourante());
			return new VueRectangle(rectangle);
		}
		Rectangle rectangle = new Rectangle(new Coordonnees(Math.min(this.getDebut().getAbscisse(),
				this.getFin().getAbscisse()), Math.min(this.getDebut().getOrdonnee(), this.getFin().getOrdonnee())),
				Math.abs(this.getFin().getAbscisse() - this.getDebut().getAbscisse()),
				Math.abs(this.getFin().getOrdonnee() - this.getDebut().getOrdonnee()));
		rectangle.setCouleur(this.getPanneauDessin().getCouleurCourante());
		rectangle.setRemplissage(this.getPanneauDessin().getModeRemplissageCourant());
		rectangle.setEpaisseur(this.getPanneauDessin().getEpaisseurCourante());
		return new VueRectangle(rectangle);
	}
	
	@Override
	public void mouseDragged(MouseEvent event) {
		this.setFin(new Coordonnees((double)event.getX(), (double)event.getY()));
		this.getPanneauDessin().setVueFormeTemp(this.creerVueForme());
		this.getPanneauDessin().setVueFormeOn(true);
	}
	
	@Override
	public void mouseReleased(MouseEvent event) {
		this.getPanneauDessin().setVueFormeOn(false);
		this.getPanneauDessin().ajouterVueForme(this.creerVueForme());
	}
}
