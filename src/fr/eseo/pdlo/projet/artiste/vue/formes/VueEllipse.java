package fr.eseo.pdlo.projet.artiste.vue.formes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.GradientPaint;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import fr.eseo.pdlo.projet.artiste.modele.Remplissage;
import fr.eseo.pdlo.projet.artiste.modele.formes.Ellipse;

public class VueEllipse extends VueForme {
	public VueEllipse(Ellipse ellipse) {
		super(ellipse);
	}
	
	@Override
	public void affiche(Graphics2D g2d) {
		Ellipse2D ellipse2D = new Ellipse2D.Double(this.forme.getPosition().getAbscisse(),
				this.forme.getPosition().getOrdonnee(),
				this.forme.getLargeur(),
				this.forme.getHauteur());
		
		Color couleurPrecedente = g2d.getColor();
		Stroke epaisseurPrecedente = g2d.getStroke();
		AffineTransform oldRotation = g2d.getTransform();
		Paint oldPaint = g2d.getPaint();
		
		g2d.rotate(this.getForme().getAngle(),
			this.getForme().getAbscisse() + this.getForme().getLargeur() / 2,
			this.getForme().getOrdonnee() + this.getForme().getHauteur() / 2);
		g2d.setStroke(new BasicStroke(this.getForme().getEpaisseur()));
		g2d.setColor(this.forme.getCouleur());
		if(((Ellipse)this.getForme()).getRemplissage() == Remplissage.UNIFORME) {
			g2d.fill(ellipse2D);
			g2d.draw(ellipse2D);
		} else if(((Ellipse)this.getForme()).getRemplissage() == Remplissage.DEGRADE) {
			GradientPaint gp = new GradientPaint((int)this.getForme().getPosition().getAbscisse(),
				(int)this.getForme().getPosition().getOrdonnee(),
				this.getForme().getCouleur(),
				(int)(this.getForme().getPosition().getAbscisse() + this.getForme().getLargeur()),
				(int)(this.getForme().getPosition().getOrdonnee() + this.getForme().getHauteur()),
				this.getForme().getCouleurSecondaire());
			g2d.setPaint(gp);
			g2d.fill(ellipse2D);
			g2d.draw(ellipse2D);
		} else if(((Ellipse)this.getForme()).getRemplissage() == Remplissage.PARTIEL) {
			g2d.fill(ellipse2D);
			g2d.setColor(this.getForme().getCouleurSecondaire());
			g2d.draw(ellipse2D);
		} else {
			g2d.draw(ellipse2D);
		}

		g2d.setPaint(oldPaint);
		g2d.setColor(couleurPrecedente);
		g2d.setStroke(epaisseurPrecedente);
		g2d.setTransform(oldRotation);
	}
}
