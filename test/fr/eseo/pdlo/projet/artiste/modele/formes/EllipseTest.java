package fr.eseo.pdlo.projet.artiste.modele.formes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import fr.eseo.pdlo.projet.artiste.modele.Coordonnees;

public class EllipseTest {
	private static final double DELTA = 10e-6;
	
	@Test
	void testConstructeur0() {
		Ellipse ellipse = new Ellipse();
		assertEquals(Ellipse.LARGEUR_PAR_DEFAUT, ellipse.getLargeur(), DELTA, "La largeur est correcte");
		assertEquals(Ellipse.HAUTEUR_PAR_DEFAUT, ellipse.getHauteur(), DELTA, "La hauteur est correcte");
		assertEquals(Coordonnees.ABSCISSE_PAR_DEFAUT, ellipse.getPosition().getAbscisse(), DELTA, "L'abscisse est correcte");
		assertEquals(Coordonnees.ORDONNEE_PAR_DEFAUT, ellipse.getPosition().getOrdonnee(), DELTA, "La hauteur est correcte");
	}
	
	@Test
	void testConstructeur1() {
		Ellipse ellipse = new Ellipse(new Coordonnees(1, 1));
		assertEquals(Ellipse.LARGEUR_PAR_DEFAUT, ellipse.getLargeur(), DELTA, "La largeur est correcte");
		assertEquals(Ellipse.HAUTEUR_PAR_DEFAUT, ellipse.getHauteur(), DELTA, "La hauteur est correcte");
		assertEquals(1, ellipse.getPosition().getAbscisse(), DELTA, "L'abscisse est correcte");
		assertEquals(1, ellipse.getPosition().getOrdonnee(), DELTA, "La hauteur est correcte");
	}
	
	@Test
	void testConstructeur2() {
		Ellipse ellipse = new Ellipse(2, 2);
		assertEquals(2, ellipse.getLargeur(), DELTA, "La largeur est correcte");
		assertEquals(2, ellipse.getHauteur(), DELTA, "La hauteur est correcte");
		assertEquals(Coordonnees.ABSCISSE_PAR_DEFAUT, ellipse.getPosition().getAbscisse(), DELTA, "L'abscisse est correcte");
		assertEquals(Coordonnees.ORDONNEE_PAR_DEFAUT, ellipse.getPosition().getOrdonnee(), DELTA, "La hauteur est correcte");
	}
	
	@Test
	void testConstructeur3() {
		Ellipse ellipse = new Ellipse(new Coordonnees(0, 1), 1, 0);
		assertEquals(1, ellipse.getLargeur(), DELTA, "La largeur est correcte");
		assertEquals(0, ellipse.getHauteur(), DELTA, "La hauteur est correcte");
		assertEquals(0, ellipse.getPosition().getAbscisse(), DELTA, "L'abscisse est correcte");
		assertEquals(1, ellipse.getPosition().getOrdonnee(), DELTA, "La hauteur est correcte");
	}
	
	@Test
	void testConstructeur4() {
		Ellipse ellipse = new Ellipse(0, 1, 1, 0);
		assertEquals(1, ellipse.getLargeur(), DELTA, "La largeur est correcte");
		assertEquals(0, ellipse.getHauteur(), DELTA, "La hauteur est correcte");
		assertEquals(0, ellipse.getPosition().getAbscisse(), DELTA, "L'abscisse est correcte");
		assertEquals(1, ellipse.getPosition().getOrdonnee(), DELTA, "La hauteur est correcte");
	}
	
	@Test
	void testSetLargeurPositive() {
		Ellipse ellipse = new Ellipse();
		ellipse.setLargeur(29);
		assertEquals(29, ellipse.getLargeur(), DELTA, "La largeur est correcte");
	}
	
	@Test
	void testSetLargeurNegative() {
		Ellipse ellipse = new Ellipse();
		
		assertThrows(IllegalArgumentException.class, ()->{ellipse.setLargeur(-29);});
	}
	
	@Test
	void testSetHauteurPositive() {
		Ellipse ellipse = new Ellipse();
		ellipse.setHauteur(24);
		assertEquals(24, ellipse.getHauteur(), DELTA, "La largeur est correcte");
	}
	
	@Test
	void testSetHauteurNegative() {
		Ellipse ellipse = new Ellipse();
		
		assertThrows(IllegalArgumentException.class, ()->{ellipse.setHauteur(-24);});
	}
	
	@Test
	void testPerimetre() {
		Ellipse ellipse = new Ellipse(new Coordonnees(), 12, 6);
		assertEquals(29, ellipse.perimetre(), 0.5, "Le perimetre est correct");
	}
	
	@Test
	void testAire() {
		Ellipse ellipse = new Ellipse(new Coordonnees(), 20, 16);
		assertEquals(251.3, ellipse.aire(), 0.5, "L'aire est correcte");
	}
	
	@Test
	void testContient() {
		Ellipse ellipse = new Ellipse(2, 3);
		assertEquals(true, ellipse.contient(new Coordonnees(0.5, 0.5)));
		assertEquals(true, ellipse.contient(new Coordonnees(0.8, 0.5)));
		assertEquals(false, ellipse.contient(new Coordonnees(2, 3)));
		assertEquals(false, ellipse.contient(new Coordonnees(0, 0)));
		assertEquals(false, ellipse.contient(new Coordonnees(-0.5, -0.5)));
		ellipse.setPosition(new Coordonnees());
		ellipse.setLargeur(1);
		ellipse.setHauteur(1);
		assertEquals(false, ellipse.contient(new Coordonnees(-1.5, -1.5)));
		assertEquals(false, ellipse.contient(new Coordonnees(-0.8, -0.8)));
	}
	
	@Test
	void testToString() {
		Ellipse ellipse = new Ellipse(new Coordonnees(52.27, 12.48), 20, 36);
		assertEquals("[Ellipse aucune] : pos (52,27 , 12,48) petit rayon 10,0 grand rayon 18,0 "
				+ "périmètre : 89,77 aire : 565,49 couleur = R51,V51,B51",
				ellipse.toString(), "Le toString est correct");
	}
}
