package fr.eseo.pdlo.projet.artiste.controleur.outils;

import java.awt.event.MouseEvent;

import fr.eseo.pdlo.projet.artiste.modele.Coordonnees;
import fr.eseo.pdlo.projet.artiste.modele.formes.Ligne;
import fr.eseo.pdlo.projet.artiste.vue.formes.VueForme;
import fr.eseo.pdlo.projet.artiste.vue.formes.VueLigne;

public class OutilLigne extends OutilForme{
	
	@Override
	protected VueForme creerVueForme() {
		if(this.getDebut().equals(this.getFin())) {
			Ligne ligne = new Ligne(this.getDebut());
			ligne.setCouleur(this.getPanneauDessin().getCouleurCourante());
			ligne.setEpaisseur(this.getPanneauDessin().getEpaisseurCourante());
			return new VueLigne(ligne);
		}
		Ligne ligne = new Ligne(this.getDebut(),
				this.getFin().getAbscisse() - this.getDebut().getAbscisse(),
				this.getFin().getOrdonnee() - this.getDebut().getOrdonnee());
		ligne.setCouleur(this.getPanneauDessin().getCouleurCourante());
		ligne.setEpaisseur(this.getPanneauDessin().getEpaisseurCourante());
		return new VueLigne(ligne);
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
