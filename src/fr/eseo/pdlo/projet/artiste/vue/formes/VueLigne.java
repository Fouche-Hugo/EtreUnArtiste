package fr.eseo.pdlo.projet.artiste.vue.formes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;

import fr.eseo.pdlo.projet.artiste.modele.formes.Ligne;

public class VueLigne extends VueForme {

	public VueLigne(Ligne ligne) {
		super(ligne);
	}
	
	@Override
	public void affiche(Graphics2D g2d) {
		Color couleurPrecedente = g2d.getColor();
		Stroke epaisseurPrecedente = g2d.getStroke();
		AffineTransform oldRotation = g2d.getTransform();
		g2d.setStroke(new BasicStroke(this.getForme().getEpaisseur()));
		g2d.setColor(this.forme.getCouleur());
		g2d.rotate(this.getForme().getAngle(),
			this.getForme().getAbscisse() + this.getForme().getLargeur() / 2,
			this.getForme().getOrdonnee() + this.getForme().getHauteur() / 2);
		
		g2d.drawLine((int)Math.round(this.getForme().getPosition().getAbscisse()), 
				(int)Math.round(this.getForme().getPosition().getOrdonnee()),
				(int)Math.round(this.getForme().getPosition().getAbscisse() + this.getForme().getLargeur()),
				(int)Math.round(this.getForme().getPosition().getOrdonnee() + this.getForme().getHauteur()));
		
		g2d.setTransform(oldRotation);
		g2d.setColor(couleurPrecedente);
		g2d.setStroke(epaisseurPrecedente);
	}
}
