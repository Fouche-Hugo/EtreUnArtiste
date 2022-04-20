package fr.eseo.pdlo.projet.artiste.vue.formes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;

import fr.eseo.pdlo.projet.artiste.modele.formes.Trace;

public class VueTrace extends VueForme{
	public VueTrace(Trace trace) {
		super(trace);
	}
	
	@Override
	public void affiche(Graphics2D g2d) {
		Color couleurPrecedente = g2d.getColor();
		g2d.setColor(this.forme.getCouleur());
		Stroke epaisseurPrecedente = g2d.getStroke();
		AffineTransform oldRotation = g2d.getTransform();
		
		g2d.setStroke(new BasicStroke(this.getForme().getEpaisseur()));
		g2d.rotate(this.getForme().getAngle(),
			this.getForme().getAbscisse() + this.getForme().getLargeur() / 2,
			this.getForme().getOrdonnee() + this.getForme().getHauteur() / 2);
		
		for(int i=0;i<((Trace)forme).getPoints().size()-1;i++) {
			g2d.drawLine((int)Math.round(((Trace)this.getForme()).getPoints().get(i).getAbscisse()),
					(int)Math.round(((Trace)this.getForme()).getPoints().get(i).getOrdonnee()),
					(int)Math.round(((Trace)this.getForme()).getPoints().get(i+1).getAbscisse()),
					(int)Math.round(((Trace)this.getForme()).getPoints().get(i+1).getOrdonnee()));
		}
		g2d.setTransform(oldRotation);
		g2d.setColor(couleurPrecedente);
		g2d.setStroke(epaisseurPrecedente);
	}
}
