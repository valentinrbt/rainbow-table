package test;

import junit.framework.*;
import src.*;
import src.view.*;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Test extends TestCase {

    public Test(String name) {
        super(name);
    }

    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTest(new Test("testHashCompare"));
        suite.addTest(new Test("testMenu"));
        suite.addTest(new Test("testRainbowGUI"));
        suite.addTest(new Test("testSearch"));
        suite.addTest(new Test("testRainbow"));
        return suite;
    }

    /**
     * Test de la classe HashCompare.
     */
    public void testHashCompare() {
        Hash hash = new Hash("mdp");
        String hash_res = hash.creeHash();
        HashCompare hashCompare = new HashCompare(hash_res);
        assertEquals(hash_res, hashCompare.getHash());
        assertNull(hashCompare.recupHashFichier("hash_inexistant"));
        try (PrintWriter writer = new PrintWriter(new FileWriter("mdp.txt", true))) {
            writer.println("5 [Red,Blue,Green,Yellow,Purple]");
            writer.println("mdp " + hash_res);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals("mdp", hashCompare.recupHashFichier(hash_res));
        assertEquals("mdp", hashCompare.rechercheMdp());
    }

    /**
     * Test de la classe Menu.
     */
    public void testMenu() {
        Menu menu = new Menu();
        assertNotNull(menu.getBoutonRecherche());
    }

    /**
     * Test de la classe RainbowGUI.
     */
    public void testRainbowGUI() {
        RainbowGUI rainbowGUI = new RainbowGUI(10, new ArrayList<String>(), 5);
        assertTrue(rainbowGUI.isVisible());
    }

    /**
     * Test de la classe Search.
     */
    public void testSearch() {
        Search search1 = new Search("mot_de_passe");
        assertTrue(search1.isVisible());
        Search search2 = new Search(null);
        assertTrue(search2.isVisible());
    }

    /**
     * Test de la classe Rainbow.
     */
    public void testRainbow() {
        Rainbow rainbow = new Rainbow();
        assertEquals("ab51c57108b263496121aff8db2a4a99", rainbow.ApplicationColor("Purple", "ab51c57108b263496121aff8db2a4a9963121d3e30b0adb6de0eb7e5f561c144"));
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}