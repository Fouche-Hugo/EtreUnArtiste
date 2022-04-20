package fr.eseo.pdlo.projet.artiste.modele;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CoordonneesTest {

	private static final double DELTA = 10e-6d;
	@Test
	public void testConstructeurVide() {
		Coordonnees coords = new Coordonnees();
		assertEquals(0, coords.getAbscisse(), DELTA);
		assertEquals(0, coords.getOrdonnee(), DELTA);
	}

	@Test
	void testConstructeurNonVide() {
		Coordonnees coords = new Coordonnees(5, 5);
		assertEquals(5, coords.getAbscisse(), DELTA);
		assertEquals(5, coords.getOrdonnee(), DELTA);
	}
	
	@Test
	void testDeplacerVers() {
		Coordonnees coords = new Coordonnees();
		coords.deplacerVers(2, 2);
		assertEquals(2, coords.getAbscisse(), DELTA);
		assertEquals(2, coords.getOrdonnee(), DELTA);
		coords.deplacerVers(new Coordonnees(1, 1));
		assertEquals(1, coords.getAbscisse(), DELTA);
		assertEquals(1, coords.getOrdonnee(), DELTA);
	}
	
	@Test
	void testDeplacerDeOrigine() {
		Coordonnees coords = new Coordonnees();
		coords.deplacerDe(2, 2);
		assertEquals(2, coords.getAbscisse(), DELTA);
		assertEquals(2, coords.getOrdonnee(), DELTA);
	}
	
	@Test
	void testDeplacerDeNonOrigine() {
		Coordonnees coords = new Coordonnees(2, 2);
		coords.deplacerDe(3, 4);
		assertEquals(5, coords.getAbscisse(), DELTA);
		assertEquals(6, coords.getOrdonnee(), DELTA);
	}
	
	@Test
	void testDistanceVers() {
		Coordonnees coords = new Coordonnees(1, 0);
		Coordonnees coords2 = new Coordonnees(3, 0);
		assertEquals(2, coords.distanceVers(coords2), DELTA);
		assertEquals(2, coords2.distanceVers(coords), DELTA);
	}
	
	@Test
	void testAngleVersPositif() {
		Coordonnees origine = new Coordonnees();
		Coordonnees coords = new Coordonnees(0, -1);
		assertEquals(-Math.PI/2, origine.angleVers(coords), DELTA);
	}
	
	@Test
	void testAngleVersNegatif() {
		Coordonnees origine = new Coordonnees();
		Coordonnees coords = new Coordonnees(0, 1);
		assertEquals(+Math.PI/2, origine.angleVers(coords), DELTA);
	}
	
	@Test
	void testToString1() {
		Coordonnees origine = new Coordonnees();
		assertEquals("(0,0 , 0,0)", origine.toString());
	}
	
	@Test
	void testToString2() {
		Coordonnees coords = new Coordonnees(1.23, 2.2569);
		assertEquals("(1,23 , 2,26)", coords.toString());
	}
	
	@Test
	void testEquals() {
		Coordonnees coords1 = new Coordonnees(1, 1);
		Coordonnees coords2 = new Coordonnees(1, 2);
		Coordonnees coords3 = new Coordonnees(1, 1);
		Coordonnees coords4 = new Coordonnees(2, 1);
		assertEquals(true, coords1.equals(coords3), "Identique");
		assertEquals(false, coords1.equals(coords2), "Non identique");
		assertEquals(false, coords2.equals(coords3), "Non identique");
		assertEquals(false, coords1.equals(coords4), "Non identique");
	}
}
