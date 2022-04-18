package fr.eseo.pdlo.projet.artiste.controleur.outils;

import java.awt.event.MouseEvent;

import fr.eseo.pdlo.projet.artiste.modele.Coordonnees;
import fr.eseo.pdlo.projet.artiste.modele.formes.Ellipse;
import fr.eseo.pdlo.projet.artiste.vue.formes.VueForme;
import fr.eseo.pdlo.projet.artiste.vue.formes.VueEllipse;

public class OutilEllipse extends OutilForme{
	@Override
	protected VueForme creerVueForme() {
		if(this.getDebut().equals(this.getFin())) {
			Ellipse ellipse = new Ellipse(this.getDebut());
			ellipse.setCouleur(this.getPanneauDessin().getCouleurCourante());
			ellipse.setRemplissage(this.getPanneauDessin().getModeRemplissageCourant());
			ellipse.setEpaisseur(this.getPanneauDessin().getEpaisseurCourante());
			return new VueEllipse(ellipse);
		}
		Ellipse ellipse = new Ellipse(new Coordonnees(Math.min(this.getDebut().getAbscisse(),
				this.getFin().getAbscisse()), Math.min(this.getDebut().getOrdonnee(), this.getFin().getOrdonnee())),
				Math.abs(this.getFin().getAbscisse() - this.getDebut().getAbscisse()),
				Math.abs(this.getFin().getOrdonnee() - this.getDebut().getOrdonnee()));
		ellipse.setCouleur(this.getPanneauDessin().getCouleurCourante());
		ellipse.setRemplissage(this.getPanneauDessin().getModeRemplissageCourant());
		ellipse.setEpaisseur(this.getPanneauDessin().getEpaisseurCourante());
		return new VueEllipse(ellipse);
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
