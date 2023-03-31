/*
 * IJA 2022/23: Úloha 1
 * Testovací třída.
 */

import ija.ija2022.homework2.tool.common.CommonField;
import ija.ija2022.homework2.tool.common.CommonMaze;
import ija.ija2022.homework2.tool.common.CommonMazeObject;
import ija.ija2022.homework2.tool.game.MazeConfigure;
import ija.ija2022.homework2.tool.game.PacmanObjectCommon;
import ija.ija2022.homework2.tool.game.PathCommonField;
import ija.ija2022.homework2.tool.game.WallCommonField;
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

        CommonMaze commonMaze = cfg.createMaze();
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

        CommonMaze commonMaze = cfg.createMaze();
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
        CommonMaze commonMaze = createTestMaze();
        
        // Testy, zda je bludiste ohraniceno zdi.
        // Testuje se pouze prvni radek.
        for (int c = 0; c < 5; c++) {
            CommonField f1 = new WallCommonField(0,c);
            CommonField f2 = commonMaze.getField(0, c);
            Assert.assertEquals("Pole reprezentujici zed", f1, f2);
            Assert.assertTrue("Pole reprezentujici zed nema zadny objekt", f2.isEmpty());
            Assert.assertFalse("Na pole reprezentujici zed se nelze presunout", f2.canMove());
        }

        CommonField f1 = new WallCommonField(2,2);
        CommonField f2 = commonMaze.getField(2, 2);
        Assert.assertEquals("Pole reprezentujici zed", f1, f2);
        Assert.assertTrue("Pole reprezentujici zed nema zadny objekt", f2.isEmpty());
        Assert.assertFalse("Na pole reprezentujici zed se nelze presunout", f2.canMove());

        CommonField f3 = new PathCommonField(2,1);
        f1 = new WallCommonField(2,1);
        CommonField f4 = commonMaze.getField(2, 1);
        Assert.assertEquals("Pole reprezentujici cestu", f3, f4);
        Assert.assertNotEquals("Pole reprezentujici cestu", f1, f4);
        Assert.assertTrue("Pole je zatim prazdne", f4.isEmpty());
        Assert.assertTrue("Na pole reprezentujici cestu se lze presunout", f4.canMove());        

        CommonField f5 = new PathCommonField(4,2);
        CommonField f6 = commonMaze.getField(4, 2);
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
        CommonMaze commonMaze = createTestMaze();
        
        CommonField f1 = commonMaze.getField(4, 2);
        CommonField f2 = f1.nextField(CommonField.Direction.R);
        CommonField f3 = f1.nextField(CommonField.Direction.D);
        
        Assert.assertTrue("Pacman se muze posunout na pole f2", f2.canMove());
        Assert.assertFalse("Pacman se nemuze posunout na pole f3", f3.canMove());
        
        CommonMazeObject obj = f1.get();
        Assert.assertTrue("Objekt je instanci tridy PacmanObject", obj instanceof PacmanObjectCommon);
        
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
        CommonMaze commonMaze = createTestMaze();
        
        CommonField f = commonMaze.getField(4, 2);
        CommonMazeObject obj = f.get();
        
        Assert.assertTrue("Pacman se muze posunout doprava", obj.canMove(CommonField.Direction.R));
        Assert.assertTrue("Pacman se muze posunout doleva", obj.canMove(CommonField.Direction.L));
        Assert.assertFalse("Pacman se nemuze posunout nahoru", obj.canMove(CommonField.Direction.U));
        Assert.assertFalse("Pacman se nemuze posunout dolu", obj.canMove(CommonField.Direction.D));
        
        Assert.assertFalse("Posun nahoru se nezdari", obj.move(CommonField.Direction.U));
        Assert.assertFalse("Puvodni misto pacmana je obsazeno", f.isEmpty());
        Assert.assertTrue("Vedlejsi misto je volne", f.nextField(CommonField.Direction.U).isEmpty());
        Assert.assertFalse("Posun dolu se nezdari", obj.move(CommonField.Direction.D));
        Assert.assertFalse("Puvodni misto pacmana je obsazeno", f.isEmpty());
        Assert.assertTrue("Vedlejsi misto je volne", f.nextField(CommonField.Direction.D).isEmpty());
        
        Assert.assertTrue("Posun doleva se zdari", obj.move(CommonField.Direction.L));
        Assert.assertTrue("Puvodni misto pacmana je volne", f.isEmpty());
        Assert.assertFalse("Vedlejsi misto je obsazene", f.nextField(CommonField.Direction.L).isEmpty());
        
        Assert.assertEquals("Na novem miste je stejny pacman", obj, f.nextField(CommonField.Direction.L).get());
    }    
    
    /**
     * Pomocná metoda pro vytvoření testovacího bludiště. Využito v testech 03, 04 a 05.
     * @return Vytvoné bludiště.
     */
    private CommonMaze createTestMaze() {
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
