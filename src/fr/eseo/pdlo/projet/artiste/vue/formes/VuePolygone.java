package fr.eseo.pdlo.projet.artiste.vue.formes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Stroke;
import java.util.ArrayList;

import fr.eseo.pdlo.projet.artiste.modele.Coordonnees;
import fr.eseo.pdlo.projet.artiste.modele.Remplissage;
import fr.eseo.pdlo.projet.artiste.modele.formes.Polygone;

public class VuePolygone extends VueForme {
	public VuePolygone(Polygone polygone) {
		super(polygone);
	}
	
	@Override
	public void affiche(Graphics2D g2d) {
		Color couleurPrecedente = g2d.getColor();
		g2d.setColor(this.getForme().getCouleur());
		Stroke epaisseurPrecedente = g2d.getStroke();
		g2d.setStroke(new BasicStroke(this.getForme().getEpaisseur()));
		//On faire un tableau des x et un tableau des y
		ArrayList<Coordonnees> points = ((Polygone)this.getForme()).getPoints();
		int nbCotes = ((Polygone)this.getForme()).getNombreCotes();
		int xValues[] = new int[nbCotes];
		int yValues[] = new int[nbCotes];
		
		for(int i=0;i < nbCotes;i++) {
			xValues[i] = (int)points.get(i).getAbscisse();
			yValues[i] = (int)points.get(i).getOrdonnee();
		}
		
		Polygon poly = new Polygon(xValues, yValues, nbCotes);
		if(((Polygone)this.getForme()).getRemplissage() == Remplissage.UNIFORME) {
			g2d.fill(poly);
		}
		g2d.draw(poly);
		g2d.setColor(couleurPrecedente);
		g2d.setStroke(epaisseurPrecedente);
	}
}
