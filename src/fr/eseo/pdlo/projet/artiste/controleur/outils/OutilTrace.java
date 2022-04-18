package fr.eseo.pdlo.projet.artiste.controleur.outils;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import fr.eseo.pdlo.projet.artiste.modele.Coordonnees;
import fr.eseo.pdlo.projet.artiste.modele.formes.Trace;
import fr.eseo.pdlo.projet.artiste.vue.formes.VueForme;
import fr.eseo.pdlo.projet.artiste.vue.formes.VueTrace;

public class OutilTrace extends OutilForme {
	
	private ArrayList<Coordonnees> points;
	private boolean traceEnCours;
	
	public OutilTrace() {
		super();
		this.points = new ArrayList<Coordonnees>();
		this.traceEnCours = false;
	}
	
	@Override
	protected VueForme creerVueForme() {
		Trace trace = new Trace(this.points.get(0), this.points.get(1));
		for(int i=2;i<this.points.size();i++) {
			trace.ajouterPoint(this.points.get(i));
		}
		trace.setCouleur(this.getPanneauDessin().getCouleurCourante());
		trace.setEpaisseur(this.getPanneauDessin().getEpaisseurCourante());
		return new VueTrace(trace);
	}
	
	@Override
	public void mousePressed(MouseEvent event) {
		traceEnCours = true;
		this.points.clear();
		points.add(new Coordonnees((double)event.getX(), (double)event.getY()));
	}
	
	@Override
	public void mouseDragged(MouseEvent event) {
		if(traceEnCours) {
			Coordonnees nouveauPoint = new Coordonnees((double)event.getX(), (double)event.getY());
			if(!this.points.get(this.points.size()-1).equals(nouveauPoint)) {
				this.points.add(nouveauPoint);
				this.getPanneauDessin().setVueFormeTemp(this.creerVueForme());
				this.getPanneauDessin().setVueFormeOn(true);
			}
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent event) {
		if(this.points.size() > 1) {
			this.getPanneauDessin().ajouterVueForme(this.creerVueForme());
			this.getPanneauDessin().setVueFormeOn(false);
		}
		traceEnCours = false;
	}
	
	@Override
	public void mouseClicked(MouseEvent event) {}
}
