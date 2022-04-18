package fr.eseo.pdlo.projet.artiste.vue.formes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import fr.eseo.pdlo.projet.artiste.modele.Remplissage;
import fr.eseo.pdlo.projet.artiste.modele.formes.Rectangle;

public class VueRectangle extends VueForme {
	public VueRectangle(Rectangle rectangle) {
		super(rectangle);
	}
	
	@Override
	public void affiche(Graphics2D g2d) {
		Color couleurPrecedente = g2d.getColor();
		g2d.setColor(this.forme.getCouleur());
		Stroke epaisseurPrecedente = g2d.getStroke();
		g2d.setStroke(new BasicStroke(this.getForme().getEpaisseur()));
		if(((Rectangle)this.getForme()).getRemplissage() == Remplissage.UNIFORME) {
			g2d.fillRect((int)this.getForme().getPosition().getAbscisse(),
				(int)this.getForme().getPosition().getOrdonnee(),
				(int)this.getForme().getLargeur(),
				(int)this.getForme().getHauteur());
		}
		g2d.drawRect((int)this.getForme().getPosition().getAbscisse(),
				(int)this.getForme().getPosition().getOrdonnee(),
				(int)this.getForme().getLargeur(),
				(int)this.getForme().getHauteur());
		g2d.setColor(couleurPrecedente);
		g2d.setStroke(epaisseurPrecedente);
	}
}
