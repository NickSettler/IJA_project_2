/*
 * IJA 2022/23: Úloha 1
 * Testovací třída.
 */

import ija.ija2022.homework2.tool.common.*;
import ija.ija2022.homework2.tool.game.MazeConfigure;
import ija.ija2022.homework2.tool.game.PacmanObject;
import ija.ija2022.homework2.tool.game.PathField;
import ija.ija2022.homework2.tool.game.WallField;
import org.junit.Test;
import org.junit.Assert;

/**
 * Testovací třída pro první úkol z předmětu IJA 2022/23.
 * @author koci
 */
public class Homework1Test {
    
    /**
     * Test třídy MazeConfigure. Testuje odolnost vůči chobnému vstupu.
     */
    @Test
    public void test01() {
        MazeConfigure cfg = new MazeConfigure();
        cfg.startReading(4, 3);
        cfg.processLine("...");
        cfg.processLine(".X");
        cfg.processLine(".Z.");
        cfg.stopReading();

        IMaze commonMaze = cfg.createMaze();
        Assert.assertNull("Vytvoreni bludiste se nezdarilo", commonMaze);
    }

    /**
     * Test třídy MazeConfigure. Testuje správné zpracování vstupu a vytvoření bludiště.
     */
    @Test
    public void test02() {
        MazeConfigure cfg = new MazeConfigure();
        cfg.startReading(4, 3);
        cfg.processLine("...");
        cfg.processLine(".X.");
        cfg.processLine(".X.");
        cfg.processLine(".S.");
        cfg.stopReading();

        IMaze commonMaze = cfg.createMaze();
        Assert.assertNotNull("Vytvoreni bludiste se zdarilo", commonMaze);
        Assert.assertEquals("Pocet radku bludiste (vcetne krajnich zdi)", 6, commonMaze.numRows());
        Assert.assertEquals("Pocet sloupcu bludiste (vcetne krajnich zdi)", 5, commonMaze.numCols());
    }
    
    /**
     * Test implementací rozhraní Field. Testuje správné umístění políček, jejich typů 
     * a vzájemných vztahů.
     */
    @Test
    public void test03() {
        IMaze commonMaze = createTestMaze();
        
        // Testy, zda je bludiste ohraniceno zdi.
        // Testuje se pouze prvni radek.
        for (int c = 0; c < 5; c++) {
            IField f1 = new WallField(0,c);
            IField f2 = (IField) commonMaze.getField(0, c);
            Assert.assertEquals("Pole reprezentujici zed", f1, f2);
            Assert.assertTrue("Pole reprezentujici zed nema zadny objekt", f2.isEmpty());
            Assert.assertFalse("Na pole reprezentujici zed se nelze presunout", f2.canMove());
        }

        IField f1 = new WallField(2,2);
        IField f2 = (IField) commonMaze.getField(2, 2);
        Assert.assertEquals("Pole reprezentujici zed", f1, f2);
        Assert.assertTrue("Pole reprezentujici zed nema zadny objekt", f2.isEmpty());
        Assert.assertFalse("Na pole reprezentujici zed se nelze presunout", f2.canMove());

        IField f3 = new PathField(2,1);
        f1 = new WallField(2,1);
        IField f4 = (IField) commonMaze.getField(2, 1);
        Assert.assertEquals("Pole reprezentujici cestu", f3, f4);
        Assert.assertNotEquals("Pole reprezentujici cestu", f1, f4);
        Assert.assertTrue("Pole je zatim prazdne", f4.isEmpty());
        Assert.assertTrue("Na pole reprezentujici cestu se lze presunout", f4.canMove());        

        IField f5 = new PathField(4,2);
        IField f6 = (IField) commonMaze.getField(4, 2);
        Assert.assertEquals("Pole reprezentujici cestu", f5, f6);
        Assert.assertFalse("Pole neni prazdne", f6.isEmpty());
        Assert.assertTrue("Na pole reprezentujici cestu se lze presunout", f6.canMove());        
        
    }

    /**
     * Test implementací rozhraní Field. Testuje správné chování při práci s objekty (MazeObject),
     * která lze na políčka umístit.
     */
    @Test
    public void test04() {
        IMaze commonMaze = createTestMaze();

        IField f1 = (IField) commonMaze.getField(4, 2);
        IField f2 = (IField) f1.nextField(IField.Direction.R);
        IField f3 = (IField) f1.nextField(IField.Direction.D);
        
        Assert.assertTrue("Pacman se muze posunout na pole f2", f2.canMove());
        Assert.assertFalse("Pacman se nemuze posunout na pole f3", f3.canMove());
        
        IMazeObject obj = (IMazeObject) f1.get();
        Assert.assertTrue("Objekt je instanci tridy PacmanObject", obj instanceof PacmanObject);
        
        Assert.assertFalse("Pole f1 neni prazdne", f1.isEmpty());
        Assert.assertTrue("Odebrani pacmana z pole f1", f1.remove(obj));
        Assert.assertTrue("Pole f1 je prazdne", f1.isEmpty());
        Assert.assertFalse("Pokus od odebrani pacmana z pole f2 (neni tam)", f2.remove(obj));
        
        Assert.assertTrue("Pole f2 je prazdne", f1.isEmpty());
        Assert.assertTrue("Vlozeni pacmana na pole f2", f2.put(obj));
        Assert.assertFalse("Pole f2 neni prazdne", f2.isEmpty());
        Assert.assertEquals("Na novem miste je stejny pacman", obj, f2.get());        
        
        Assert.assertThrows(
                "Na pole typu zed nelze vlozit - generuje vyjimku UnsupportedOperationException",
                UnsupportedOperationException.class, 
                () -> { f3.put(obj); }
        );
    }

    /**
     * Test implementací rozhraní MazeObject. Testuje správné chování při přesunu objektů.
     */
    @Test
    public void test05() {
        IMaze commonMaze = createTestMaze();
        
        IField f = (IField) commonMaze.getField(4, 2);
        IMazeObject obj = (IMazeObject) f.get();
        
        Assert.assertTrue("Pacman se muze posunout doprava", obj.canMove(IField.Direction.R));
        Assert.assertTrue("Pacman se muze posunout doleva", obj.canMove(IField.Direction.L));
        Assert.assertFalse("Pacman se nemuze posunout nahoru", obj.canMove(IField.Direction.U));
        Assert.assertFalse("Pacman se nemuze posunout dolu", obj.canMove(IField.Direction.D));
        
        Assert.assertFalse("Posun nahoru se nezdari", obj.move(IField.Direction.U));
        Assert.assertFalse("Puvodni misto pacmana je obsazeno", f.isEmpty());
        Assert.assertTrue("Vedlejsi misto je volne", f.nextField(IField.Direction.U).isEmpty());
        Assert.assertFalse("Posun dolu se nezdari", obj.move(IField.Direction.D));
        Assert.assertFalse("Puvodni misto pacmana je obsazeno", f.isEmpty());
        Assert.assertTrue("Vedlejsi misto je volne", f.nextField(IField.Direction.D).isEmpty());
        
        Assert.assertTrue("Posun doleva se zdari", obj.move(IField.Direction.L));
        Assert.assertTrue("Puvodni misto pacmana je volne", f.isEmpty());
        Assert.assertFalse("Vedlejsi misto je obsazene", f.nextField(IField.Direction.L).isEmpty());
        
        Assert.assertEquals("Na novem miste je stejny pacman", obj, f.nextField(IField.Direction.L).get());
    }    
    
    /**
     * Pomocná metoda pro vytvoření testovacího bludiště. Využito v testech 03, 04 a 05.
     * @return Vytvoné bludiště.
     */
    private IMaze createTestMaze() {
        MazeConfigure cfg = new MazeConfigure();
        cfg.startReading(4, 3);
        cfg.processLine("...");
        cfg.processLine(".X.");
        cfg.processLine(".X.");
        cfg.processLine(".S.");
        cfg.stopReading();
        
        return cfg.createMaze();        
    }
    
}
