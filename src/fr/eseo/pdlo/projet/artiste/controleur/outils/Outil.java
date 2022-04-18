package fr.eseo.pdlo.projet.artiste.controleur.outils;

import javax.swing.event.MouseInputListener;
import fr.eseo.pdlo.projet.artiste.modele.Coordonnees;
import fr.eseo.pdlo.projet.artiste.vue.ihm.PanneauDessin;
import java.awt.event.MouseEvent;

abstract public class Outil implements MouseInputListener{
	private PanneauDessin panneauDessin;
	private Coordonnees debut;
	private Coordonnees fin;
	
	public Coordonnees getDebut() {
		return debut;
	}
	
	public void setDebut(Coordonnees debut) {
		this.debut = debut;
	}
	
	public Coordonnees getFin() {
		return fin;
	}
	
	public void setFin(Coordonnees fin) {
		this.fin = fin;
	}
	
	public PanneauDessin getPanneauDessin() {
		return this.panneauDessin;
	}
	
	public void setPanneauDessin(PanneauDessin nouvelOutil) {
		this.panneauDessin = nouvelOutil;
	}
	
	public void mousePressed(MouseEvent event) {
		this.debut = new Coordonnees((double)event.getX(), (double)event.getY());
	}
	
	public void mouseReleased(MouseEvent event) {
		this.fin = new Coordonnees((double)event.getX(), (double)event.getY());
	}
	
	public void mouseDragged(MouseEvent event) {}
	public void mouseEntered(MouseEvent event ) {}
	public void mouseExited(MouseEvent event) {}
	public void mouseMoved(MouseEvent event) {}
	public void mouseClicked(MouseEvent event) {}
}
