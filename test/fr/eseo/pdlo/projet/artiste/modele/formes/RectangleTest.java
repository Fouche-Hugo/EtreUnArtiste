package fr.eseo.pdlo.projet.artiste.modele.formes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fr.eseo.pdlo.projet.artiste.modele.Coordonnees;

public class RectangleTest {
public static final double DELTA = 1e-7d;
	
	@Test
	void testConstructeur0() {
		Rectangle rectangle = new Rectangle();
		assertEquals(Rectangle.LARGEUR_PAR_DEFAUT, rectangle.getLargeur(), DELTA, "La largeur est correcte");
		assertEquals(Rectangle.HAUTEUR_PAR_DEFAUT, rectangle.getHauteur(), DELTA, "La hauteur est correcte");
		assertEquals(Coordonnees.ABSCISSE_PAR_DEFAUT, rectangle.getPosition().getAbscisse(), DELTA, "L'abscisse est correcte");
		assertEquals(Coordonnees.ORDONNEE_PAR_DEFAUT, rectangle.getPosition().getOrdonnee(), DELTA, "La hauteur est correcte");
	}
	
	@Test
	void testConstructeur1() {
		Rectangle rectangle = new Rectangle(new Coordonnees(1, 1));
		assertEquals(Rectangle.LARGEUR_PAR_DEFAUT, rectangle.getLargeur(), DELTA, "La largeur est correcte");
		assertEquals(Rectangle.HAUTEUR_PAR_DEFAUT, rectangle.getHauteur(), DELTA, "La hauteur est correcte");
		assertEquals(1, rectangle.getPosition().getAbscisse(), DELTA, "L'abscisse est correcte");
		assertEquals(1, rectangle.getPosition().getOrdonnee(), DELTA, "La hauteur est correcte");
	}
	
	@Test
	void testConstructeur2() {
		Rectangle rectangle = new Rectangle(2, 2);
		assertEquals(2, rectangle.getLargeur(), DELTA, "La largeur est correcte");
		assertEquals(2, rectangle.getHauteur(), DELTA, "La hauteur est correcte");
		assertEquals(Coordonnees.ABSCISSE_PAR_DEFAUT, rectangle.getPosition().getAbscisse(), DELTA, "L'abscisse est correcte");
		assertEquals(Coordonnees.ORDONNEE_PAR_DEFAUT, rectangle.getPosition().getOrdonnee(), DELTA, "La hauteur est correcte");
	}
	
	@Test
	void testConstructeur3() {
		Rectangle rectangle = new Rectangle(new Coordonnees(0, 1), 1, 0);
		assertEquals(1, rectangle.getLargeur(), DELTA, "La largeur est correcte");
		assertEquals(0, rectangle.getHauteur(), DELTA, "La hauteur est correcte");
		assertEquals(0, rectangle.getPosition().getAbscisse(), DELTA, "L'abscisse est correcte");
		assertEquals(1, rectangle.getPosition().getOrdonnee(), DELTA, "La hauteur est correcte");
	}
	
	@Test
	void testConstructeur4() {
		Rectangle rectangle = new Rectangle(1, 0, 1, 0);
		assertEquals(1, rectangle.getLargeur(), DELTA, "La largeur est correcte");
		assertEquals(0, rectangle.getHauteur(), DELTA, "La hauteur est correcte");
		assertEquals(1, rectangle.getPosition().getAbscisse(), DELTA, "L'abscisse est correcte");
		assertEquals(0, rectangle.getPosition().getOrdonnee(), DELTA, "La hauteur est correcte");
	}

	
	@Test
	void testCadreMinX() {
		Rectangle rectangle = new Rectangle(new Coordonnees(0, 1), 1, 1);
		assertEquals(0, rectangle.getCadreMinX(), DELTA, "Le minX est correct");
		rectangle.setLargeur(-1);
		assertEquals(-1, rectangle.getCadreMinX(), DELTA, "Le minX est correct");
	}
	
	@Test
	void testCadreMaxX() {
		Rectangle rectangle = new Rectangle(new Coordonnees(0, 1), 1, 1);
		assertEquals(1, rectangle.getCadreMaxX(), DELTA, "Le maxX est correct");
		rectangle.setLargeur(-1);
		assertEquals(0, rectangle.getCadreMaxX(), DELTA, "Le maxX est correct");
	}
	
	@Test
	void testCadreMinY() {
		Rectangle rectangle = new Rectangle(new Coordonnees(0, 1), 1, 1);
		assertEquals(1, rectangle.getCadreMinY(), DELTA, "Le minY est correct");
		rectangle.setHauteur(-1);
		assertEquals(0, rectangle.getCadreMinY(), DELTA, "Le minY est correct");
	}
	
	@Test
	void testCadreMaxY() {
		Rectangle rectangle = new Rectangle(new Coordonnees(0, 1), 1, 1);
		assertEquals(2, rectangle.getCadreMaxY(), DELTA, "Le maxY est correct");
		rectangle.setHauteur(-1);
		assertEquals(1, rectangle.getCadreMaxY(), DELTA, "Le maxY est correct");
	}
	
	@Test
	void testDeplacerVers1() {
		Rectangle rectangle = new Rectangle();
		rectangle.deplacerVers(new Coordonnees(0, 1));
		assertEquals(0, rectangle.getPosition().getAbscisse(), DELTA, "L'abscisse est correcte");
		assertEquals(1, rectangle.getPosition().getOrdonnee(), DELTA, "L'ordonne est correcte");
	}
	
	@Test
	void testDeplacerVers2() {
		Rectangle rectangle = new Rectangle();
		rectangle.deplacerVers(0, 1);
		assertEquals(0, rectangle.getPosition().getAbscisse(), DELTA, "L'abscisse est correcte");
		assertEquals(1, rectangle.getPosition().getOrdonnee(), DELTA, "L'ordonne est correcte");
	}
	
	@Test
	void testDeplacerDe() {
		Rectangle rectangle = new Rectangle(new Coordonnees(1, 1));
		rectangle.deplacerDe(2, 2);
		assertEquals(3, rectangle.getPosition().getAbscisse(), DELTA, "L'abscisse est correcte");
		assertEquals(3, rectangle.getPosition().getOrdonnee(), DELTA, "L'ordonne est correcte");
	}
	
	@Test
	void testAire() {
		Rectangle rectangle = new Rectangle(new Coordonnees(1, 1), 1, 1);
		Rectangle rectangle2 = new Rectangle();
		Rectangle rectangle3 = new Rectangle(4, 4);
		assertEquals(1, rectangle.aire(), DELTA, "L'aire est correcte");
		assertEquals(100, rectangle2.aire(), DELTA, "L'aire est correcte");
		assertEquals(16, rectangle3.aire(), DELTA, "L'aire est correcte");
	}
	
	@Test
	void testPerimetre() {
		Rectangle rectangle = new Rectangle(2, 1);
		Rectangle rectangle2 = new Rectangle(-1, 1);
		Rectangle rectangle3 = new Rectangle(new Coordonnees(0, 1), 0, 0);
		Rectangle rectangle4 = new Rectangle(new Coordonnees(0, 1), 1, 1);
		assertEquals(6, rectangle.perimetre(), DELTA, "Le perimetre est correct");
		assertEquals(4, rectangle2.perimetre(), DELTA, "Le perimetre est correct");
		assertEquals(0, rectangle3.perimetre(), DELTA, "Le perimetre est correct");
		assertEquals(4, rectangle4.perimetre(), DELTA, "Le perimetre est correct");
	}
	
	@Test
	void testContient() {
		Rectangle rectangle = new Rectangle(1, 1);
		assertEquals(true, rectangle.contient(new Coordonnees(0.5, 0.5)), "Le point est bien dans le rectangle");
		assertEquals(false, rectangle.contient(new Coordonnees(2, 2)), "Le point n'est pas dans le rectangle");
		assertEquals(false, rectangle.contient(new Coordonnees(-5.1, 3.2)), "Le point n'est pas dans le rectangle");
	}
	
	@Test
	void testToString() {
		Rectangle rectangle = new Rectangle(1, 1);
		assertEquals("[Rectangle] pos : (0,0 , 0,0) perimetre : 4 aire : 1"
				+ " couleur = R51,V51,B51",
				rectangle.toString(), "Le toString est correct");
	}
}
