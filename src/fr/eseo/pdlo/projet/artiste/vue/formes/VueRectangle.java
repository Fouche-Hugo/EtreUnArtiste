package fr.eseo.pdlo.projet.artiste.vue.formes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.GradientPaint;
import java.awt.geom.AffineTransform;

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
		AffineTransform oldRotation = g2d.getTransform();
		Paint oldPaint = g2d.getPaint();

		g2d.setStroke(new BasicStroke(this.getForme().getEpaisseur()));
		g2d.rotate(this.getForme().getAngle(),
			this.getForme().getAbscisse() + this.getForme().getLargeur() / 2,
			this.getForme().getOrdonnee() + this.getForme().getHauteur() / 2);
		
		if(((Rectangle)this.getForme()).getRemplissage() == Remplissage.UNIFORME) {
			g2d.fillRect((int)this.getForme().getPosition().getAbscisse(),
				(int)this.getForme().getPosition().getOrdonnee(),
				(int)this.getForme().getLargeur(),
				(int)this.getForme().getHauteur());
			g2d.drawRect((int)this.getForme().getPosition().getAbscisse(),
				(int)this.getForme().getPosition().getOrdonnee(),
				(int)this.getForme().getLargeur(),
				(int)this.getForme().getHauteur());
		} else if(((Rectangle)this.getForme()).getRemplissage() == Remplissage.DEGRADE) {
			GradientPaint gp = new GradientPaint((int)this.getForme().getPosition().getAbscisse(),
				(int)this.getForme().getPosition().getOrdonnee(),
				this.getForme().getCouleur(),
				(int)(this.getForme().getPosition().getAbscisse() + this.getForme().getLargeur()),
				(int)(this.getForme().getPosition().getOrdonnee() + this.getForme().getHauteur()),
				this.getForme().getCouleurSecondaire());
			g2d.setPaint(gp);
			g2d.fillRect((int)this.getForme().getPosition().getAbscisse(),
				(int)this.getForme().getPosition().getOrdonnee(),
				(int)this.getForme().getLargeur(),
				(int)this.getForme().getHauteur());
			g2d.drawRect((int)this.getForme().getPosition().getAbscisse(),
				(int)this.getForme().getPosition().getOrdonnee(),
				(int)this.getForme().getLargeur(),
				(int)this.getForme().getHauteur());
		} else if(((Rectangle)this.getForme()).getRemplissage() == Remplissage.PARTIEL) {
			g2d.fillRect((int)this.getForme().getPosition().getAbscisse(),
				(int)this.getForme().getPosition().getOrdonnee(),
				(int)this.getForme().getLargeur(),
				(int)this.getForme().getHauteur());
			g2d.setColor(this.getForme().getCouleurSecondaire());
			g2d.drawRect((int)this.getForme().getPosition().getAbscisse(),
					(int)this.getForme().getPosition().getOrdonnee(),
					(int)this.getForme().getLargeur(),
					(int)this.getForme().getHauteur());
		} else {
			g2d.drawRect((int)this.getForme().getPosition().getAbscisse(),
					(int)this.getForme().getPosition().getOrdonnee(),
					(int)this.getForme().getLargeur(),
					(int)this.getForme().getHauteur());
		}
		
		g2d.setPaint(oldPaint);
		g2d.setTransform(oldRotation);
		g2d.setColor(couleurPrecedente);
		g2d.setStroke(epaisseurPrecedente);
	}
}
