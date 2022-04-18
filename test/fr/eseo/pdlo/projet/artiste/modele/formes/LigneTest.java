package fr.eseo.pdlo.projet.artiste.modele.formes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import fr.eseo.pdlo.projet.artiste.modele.Coordonnees;

import org.junit.jupiter.api.Test;

public class LigneTest {

	public static final double DELTA = 1e-7d;
	
	@Test
	void testConstructeur0() {
		Ligne ligne = new Ligne();
		assertEquals(Ligne.LARGEUR_PAR_DEFAUT, ligne.getLargeur(), DELTA, "La largeur est correcte");
		assertEquals(Ligne.HAUTEUR_PAR_DEFAUT, ligne.getHauteur(), DELTA, "La hauteur est correcte");
		assertEquals(Coordonnees.ABSCISSE_PAR_DEFAUT, ligne.getPosition().getAbscisse(), DELTA, "L'abscisse est correcte");
		assertEquals(Coordonnees.ORDONNEE_PAR_DEFAUT, ligne.getPosition().getOrdonnee(), DELTA, "La hauteur est correcte");
	}
	
	@Test
	void testConstructeur1() {
		Ligne ligne = new Ligne(new Coordonnees(1, 1));
		assertEquals(Ligne.LARGEUR_PAR_DEFAUT, ligne.getLargeur(), DELTA, "La largeur est correcte");
		assertEquals(Ligne.HAUTEUR_PAR_DEFAUT, ligne.getHauteur(), DELTA, "La hauteur est correcte");
		assertEquals(1, ligne.getPosition().getAbscisse(), DELTA, "L'abscisse est correcte");
		assertEquals(1, ligne.getPosition().getOrdonnee(), DELTA, "La hauteur est correcte");
	}
	
	@Test
	void testConstructeur2() {
		Ligne ligne = new Ligne(2, 2);
		assertEquals(2, ligne.getLargeur(), DELTA, "La largeur est correcte");
		assertEquals(2, ligne.getHauteur(), DELTA, "La hauteur est correcte");
		assertEquals(Coordonnees.ABSCISSE_PAR_DEFAUT, ligne.getPosition().getAbscisse(), DELTA, "L'abscisse est correcte");
		assertEquals(Coordonnees.ORDONNEE_PAR_DEFAUT, ligne.getPosition().getOrdonnee(), DELTA, "La hauteur est correcte");
	}
	
	@Test
	void testConstructeur3() {
		Ligne ligne = new Ligne(new Coordonnees(0, 1), 1, 0);
		assertEquals(1, ligne.getLargeur(), DELTA, "La largeur est correcte");
		assertEquals(0, ligne.getHauteur(), DELTA, "La hauteur est correcte");
		assertEquals(0, ligne.getPosition().getAbscisse(), DELTA, "L'abscisse est correcte");
		assertEquals(1, ligne.getPosition().getOrdonnee(), DELTA, "La hauteur est correcte");
	}
	
	@Test
	void testConstructeur4() {
		Ligne ligne = new Ligne(1, 0, 1, 0);
		assertEquals(1, ligne.getLargeur(), DELTA, "La largeur est correcte");
		assertEquals(0, ligne.getHauteur(), DELTA, "La hauteur est correcte");
		assertEquals(1, ligne.getPosition().getAbscisse(), DELTA, "L'abscisse est correcte");
		assertEquals(0, ligne.getPosition().getOrdonnee(), DELTA, "La hauteur est correcte");
	}

	@Test
	void testGetC2() {
		Ligne ligne = new Ligne();
		assertEquals(Coordonnees.ABSCISSE_PAR_DEFAUT+Ligne.LARGEUR_PAR_DEFAUT,
				ligne.getC2().getAbscisse(), DELTA, "L'abscisse est correcte");
		assertEquals(Coordonnees.ORDONNEE_PAR_DEFAUT+Ligne.HAUTEUR_PAR_DEFAUT,
				ligne.getC2().getOrdonnee(), DELTA, "L'ordonnee est correcte");
	}
	
	@Test
	void testSetC2() {
		Ligne ligne = new Ligne();
		ligne.setC2(new Coordonnees(0, 1));
		assertEquals(0, ligne.getC2().getAbscisse(), DELTA, "L'abscisse est correcte");
		assertEquals(1, ligne.getC2().getOrdonnee(), DELTA, "L'ordonnee est correcte");
		ligne.setC2(2, 3);
		assertEquals(2, ligne.getC2().getAbscisse(), DELTA, "L'abscisse est correcte");
		assertEquals(3, ligne.getC2().getOrdonnee(), DELTA, "L'ordonnée est correcte");
	}
	
	@Test
	void testGetC1() {
		Ligne ligne = new Ligne(new Coordonnees(1, 1));
		assertEquals(1, ligne.getC1().getAbscisse(), DELTA, "L'abscisse est correcte");
		assertEquals(1, ligne.getC1().getOrdonnee(), DELTA, "L'ordonnee est correcte");
	}
	
	@Test
	void testSetC1() {
		Ligne ligne = new Ligne();
		ligne.setC1(new Coordonnees(1, 1));
		assertEquals(1, ligne.getC1().getAbscisse(), DELTA, "L'abscisse est correcte");
		assertEquals(1, ligne.getC1().getOrdonnee(), DELTA, "L'ordonnee est correcte");
		assertEquals(Ligne.LARGEUR_PAR_DEFAUT, ligne.getC2().getAbscisse(), DELTA, "L'abscisse de C2 est inchangée");
		assertEquals(Ligne.HAUTEUR_PAR_DEFAUT, ligne.getC2().getOrdonnee(), DELTA, "L'ordonnee de C2 est inchangée");
		
		ligne.setC1(2, 3);
		assertEquals(2, ligne.getC1().getAbscisse(), DELTA, "L'abscisse est correcte");
		assertEquals(3, ligne.getC1().getOrdonnee(), DELTA, "L'ordonnee est correcte");
		assertEquals(Ligne.LARGEUR_PAR_DEFAUT, ligne.getC2().getAbscisse(), DELTA, "L'abscisse de C2 est inchangée");
		assertEquals(Ligne.HAUTEUR_PAR_DEFAUT, ligne.getC2().getOrdonnee(), DELTA, "L'ordonnee de C2 est inchangée");
	}
	
	@Test
	void testCadreMinX() {
		Ligne ligne = new Ligne(new Coordonnees(0, 1), 1, 1);
		assertEquals(0, ligne.getCadreMinX(), DELTA, "Le minX est correct");
		ligne.setLargeur(-1);
		assertEquals(-1, ligne.getCadreMinX(), DELTA, "Le minX est correct");
	}
	
	@Test
	void testCadreMaxX() {
		Ligne ligne = new Ligne(new Coordonnees(0, 1), 1, 1);
		assertEquals(1, ligne.getCadreMaxX(), DELTA, "Le maxX est correct");
		ligne.setLargeur(-1);
		assertEquals(0, ligne.getCadreMaxX(), DELTA, "Le maxX est correct");
	}
	
	@Test
	void testCadreMinY() {
		Ligne ligne = new Ligne(new Coordonnees(0, 1), 1, 1);
		assertEquals(1, ligne.getCadreMinY(), DELTA, "Le minY est correct");
		ligne.setHauteur(-1);
		assertEquals(0, ligne.getCadreMinY(), DELTA, "Le minY est correct");
	}
	
	@Test
	void testCadreMaxY() {
		Ligne ligne = new Ligne(new Coordonnees(0, 1), 1, 1);
		assertEquals(2, ligne.getCadreMaxY(), DELTA, "Le maxY est correct");
		ligne.setHauteur(-1);
		assertEquals(1, ligne.getCadreMaxY(), DELTA, "Le maxY est correct");
	}
	
	@Test
	void testDeplacerVers1() {
		Ligne ligne = new Ligne();
		ligne.deplacerVers(new Coordonnees(0, 1));
		assertEquals(0, ligne.getPosition().getAbscisse(), DELTA, "L'abscisse est correcte");
		assertEquals(1, ligne.getPosition().getOrdonnee(), DELTA, "L'ordonne est correcte");
	}
	
	@Test
	void testDeplacerVers2() {
		Ligne ligne = new Ligne();
		ligne.deplacerVers(0, 1);
		assertEquals(0, ligne.getPosition().getAbscisse(), DELTA, "L'abscisse est correcte");
		assertEquals(1, ligne.getPosition().getOrdonnee(), DELTA, "L'ordonne est correcte");
	}
	
	@Test
	void testDeplacerDe() {
		Ligne ligne = new Ligne(new Coordonnees(1, 1));
		ligne.deplacerDe(2, 2);
		assertEquals(3, ligne.getPosition().getAbscisse(), DELTA, "L'abscisse est correcte");
		assertEquals(3, ligne.getPosition().getOrdonnee(), DELTA, "L'ordonne est correcte");
	}
	
	@Test
	void testAire() {
		Ligne ligne = new Ligne(new Coordonnees(1, 1), 0, 0);
		Ligne ligne2 = new Ligne();
		Ligne ligne3 = new Ligne(4, 4);
		assertEquals(0, ligne.aire(), DELTA, "L'aire est correcte");
		assertEquals(0, ligne2.aire(), DELTA, "L'aire est correcte");
		assertEquals(0, ligne3.aire(), DELTA, "L'aire est correcte");
	}
	
	@Test
	void testPerimetre() {
		Ligne ligne = new Ligne(0, 1);
		Ligne ligne2 = new Ligne(-1, 0);
		Ligne ligne3 = new Ligne(new Coordonnees(0, 1), 0, 0);
		Ligne ligne4 = new Ligne(new Coordonnees(0, 1), 1, 1);
		assertEquals(1, ligne.perimetre(), DELTA, "Le perimetre est correct");
		assertEquals(1, ligne2.perimetre(), DELTA, "Le perimetre est correct");
		assertEquals(0, ligne3.perimetre(), DELTA, "Le perimetre est correct");
		assertEquals(Math.sqrt(2), ligne4.perimetre(), DELTA, "Le perimetre est correct");
	}
	
	@Test
	void testContient() {
		Ligne ligne = new Ligne(1, 1);
		assertEquals(true, ligne.contient(new Coordonnees(0.5, 0.5)), "Le point est bien dans la ligne");
		assertEquals(false, ligne.contient(new Coordonnees(2, 2)), "Le point n'est pas dans la ligne");
		assertEquals(false, ligne.contient(new Coordonnees(-5.1, 3.2)), "Le point n'est pas dans la ligne");
	}
	
	@Test
	void testToString() {
		Ligne ligne = new Ligne(1, 1);
		assertEquals("[Ligne] c1 : (0,0 , 0,0) c2 : (1,0 , 1,0) longueur : 1,41 angle : 45,0°"
				+ " couleur = R51,V51,B51",
				ligne.toString(), "Le toString est correct");
	}
}