package fr.eseo.pdlo.projet.artiste.modele.formes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import fr.eseo.pdlo.projet.artiste.modele.Coordonnees;

public class CercleTest {
	private static final double DELTA = 10e-6;
	
	@Test
	void testConstructeur0() {
		Cercle cercle = new Cercle();
		assertEquals(Cercle.LARGEUR_PAR_DEFAUT, cercle.getLargeur(), DELTA, "La largeur est correcte");
		assertEquals(Cercle.HAUTEUR_PAR_DEFAUT, cercle.getHauteur(), DELTA, "La hauteur est correcte");
		assertEquals(cercle.getLargeur(), cercle.getHauteur(), DELTA, "Longueur = hauteur");
		assertEquals(Coordonnees.ABSCISSE_PAR_DEFAUT, cercle.getPosition().getAbscisse(), DELTA, "L'abscisse est correcte");
		assertEquals(Coordonnees.ORDONNEE_PAR_DEFAUT, cercle.getPosition().getOrdonnee(), DELTA, "La hauteur est correcte");
	}
	
	@Test
	void testConstructeur1() {
		Cercle cercle = new Cercle(new Coordonnees(1, 1));
		assertEquals(Cercle.LARGEUR_PAR_DEFAUT, cercle.getLargeur(), DELTA, "La largeur est correcte");
		assertEquals(Cercle.HAUTEUR_PAR_DEFAUT, cercle.getHauteur(), DELTA, "La hauteur est correcte");
		assertEquals(cercle.getLargeur(), cercle.getHauteur(), DELTA, "Longueur = hauteur");
		assertEquals(1, cercle.getPosition().getAbscisse(), DELTA, "L'abscisse est correcte");
		assertEquals(1, cercle.getPosition().getOrdonnee(), DELTA, "La hauteur est correcte");
	}
	
	@Test
	void testConstructeur2() {
		Cercle cercle = new Cercle(2);
		assertEquals(2, cercle.getLargeur(), DELTA, "La largeur est correcte");
		assertEquals(2, cercle.getHauteur(), DELTA, "La hauteur est correcte");
		assertEquals(cercle.getLargeur(), cercle.getHauteur(), DELTA, "Longueur = hauteur");
		assertEquals(Coordonnees.ABSCISSE_PAR_DEFAUT, cercle.getPosition().getAbscisse(), DELTA, "L'abscisse est correcte");
		assertEquals(Coordonnees.ORDONNEE_PAR_DEFAUT, cercle.getPosition().getOrdonnee(), DELTA, "La hauteur est correcte");
	}
	
	@Test
	void testConstructeur3() {
		Cercle cercle = new Cercle(new Coordonnees(0, 1), 1);
		assertEquals(1, cercle.getLargeur(), DELTA, "La largeur est correcte");
		assertEquals(1, cercle.getHauteur(), DELTA, "La hauteur est correcte");
		assertEquals(cercle.getLargeur(), cercle.getHauteur(), DELTA, "Longueur = hauteur");
		assertEquals(0, cercle.getPosition().getAbscisse(), DELTA, "L'abscisse est correcte");
		assertEquals(1, cercle.getPosition().getOrdonnee(), DELTA, "La hauteur est correcte");
	}
	
	@Test
	void testConstructeur4() {
		Cercle cercle = new Cercle(0, 1, 1);
		assertEquals(1, cercle.getLargeur(), DELTA, "La largeur est correcte");
		assertEquals(1, cercle.getHauteur(), DELTA, "La hauteur est correcte");
		assertEquals(cercle.getLargeur(), cercle.getHauteur(), DELTA, "Longueur = hauteur");
		assertEquals(0, cercle.getPosition().getAbscisse(), DELTA, "L'abscisse est correcte");
		assertEquals(1, cercle.getPosition().getOrdonnee(), DELTA, "La hauteur est correcte");
	}
	
	@Test
	void testSetLargeurPositive() {
		Cercle cercle = new Cercle();
		cercle.setLargeur(29);
		assertEquals(29, cercle.getLargeur(), DELTA, "La largeur est correcte");
	}
	
	@Test
	void testSetLargeurNegative() {
		Cercle cercle = new Cercle();
		
		assertThrows(IllegalArgumentException.class, ()->{cercle.setLargeur(-29);});
	}
	
	@Test
	void testSetHauteurPositive() {
		Cercle cercle = new Cercle();
		cercle.setHauteur(24);
		assertEquals(24, cercle.getHauteur(), DELTA, "La largeur est correcte");
	}
	
	@Test
	void testSetHauteurNegative() {
		Cercle cercle = new Cercle();
		
		assertThrows(IllegalArgumentException.class, ()->{cercle.setHauteur(-24);});
	}
	
	@Test
	void testPerimetre() {
		Cercle cercle = new Cercle(new Coordonnees(), 12);
		assertEquals(12*Math.PI, cercle.perimetre(), 0.5, "Le perimetre est correct");
	}
	
	@Test
	void testAire() {
		Cercle cercle = new Cercle(new Coordonnees(), 20);
		assertEquals(Math.PI * Math.pow((20/2), 2), cercle.aire(), 0.5, "L'aire est correcte");
	}
	
	@Test
	void testSetLargeur() {
		Cercle cercle = new Cercle(2);
		cercle.setLargeur(5);
		assertEquals(5, cercle.getLargeur(), DELTA, "La largeur est correcte");
		assertEquals(5, cercle.getHauteur(), DELTA, "La hauteur est correcte");
		assertEquals(cercle.getLargeur(), cercle.getHauteur(), DELTA, "Longueur = hauteur");
	}
	
	@Test
	void testSetHauteur() {
		Cercle cercle = new Cercle(2);
		cercle.setHauteur(5);
		assertEquals(5, cercle.getLargeur(), DELTA, "La largeur est correcte");
		assertEquals(5, cercle.getHauteur(), DELTA, "La hauteur est correcte");
		assertEquals(cercle.getLargeur(), cercle.getHauteur(), DELTA, "Longueur = hauteur");
	}
	
	@Test
	void testGetDiametre() {
		Cercle cercle = new Cercle(1);
		assertEquals(1, cercle.getDiametre(), DELTA, "Le diametre est correct");
	}
	
	@Test
	void testSetDiametre() {
		Cercle cercle = new Cercle(2);
		cercle.setDiametre(3);
		assertEquals(3, cercle.getDiametre(), DELTA, "Le diametre est correct");
	}
	
	@Test
	void testGetRayon() {
		Cercle cercle = new Cercle(2);
		assertEquals(1, cercle.getRayon(), DELTA, "Le rayon est correct");
	}
	
	@Test
	void testSetRayon() {
		Cercle cercle = new Cercle();
		cercle.setRayon(5);
		assertEquals(5, cercle.getRayon(), DELTA, "Le rayon est correct");
	}
	
	@Test
	void testContient() {
		Cercle cercle = new Cercle(new Coordonnees(1, 1), 2);
		assertEquals(true, cercle.contient(new Coordonnees(1.5, 1.5)));
		assertEquals(true, cercle.contient(new Coordonnees(1.5, 2)));
		assertEquals(false, cercle.contient(new Coordonnees(-1, 2)));
		assertEquals(false, cercle.contient(new Coordonnees(-1, -1)));
	}
	
	@Test
	void testToString() {
		Cercle cercle = new Cercle(new Coordonnees(1, 1), 6);
		assertEquals("[Cercle aucune] : pos (1,0 , 1,0) rayon 3,0 périmètre : 18,85 aire : 28,27"
				+ " couleur = R51,V51,B51", cercle.toString(), "Le toString est correct");
	}
}