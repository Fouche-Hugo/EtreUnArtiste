package fr.eseo.pdlo.projet.artiste.vue.ihm;

import java.awt.event.MouseMotionListener;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.eseo.pdlo.projet.artiste.modele.Coordonnees;

public class PanneauBarreEtat extends JPanel implements MouseMotionListener{

	private static final long serialVersionUID = 1L;
	private JLabel valeurX;
	private JLabel valeurY;
	private JLabel infoForme;
	
	public PanneauBarreEtat(PanneauDessin panneauDessin) {
		this.valeurX = new JLabel("x: 0");
		this.valeurY = new JLabel("y: 0");
		this.infoForme = new JLabel("");
		
		panneauDessin.addMouseMotionListener(this);
		this.add(this.valeurX);
		this.add(this.valeurY);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
	}
	
	public void mouseDragged(MouseEvent event) {
		this.mettreAJourAffichage(new Coordonnees(event.getX(), event.getY()));
	}
	
	public void mouseMoved(MouseEvent event) {
		this.mettreAJourAffichage(new Coordonnees(event.getX(), event.getY()));
	}
	
	private void mettreAJourAffichage(Coordonnees coords) {
		String strX = Double.toString(coords.getAbscisse());
		String strY = Double.toString(coords.getOrdonnee());
		
		this.valeurX.setText("x: " + strX);
		this.valeurY.setText("y: " + strY);
	}
	
	public void mettreAJourInfos(String infos) {
		this.infoForme.setText(infos);
	}
}
