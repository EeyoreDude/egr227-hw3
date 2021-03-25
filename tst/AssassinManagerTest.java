import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Put your comments here
 */

//Test constructor
public class AssassinManagerTest {


    @Test
    public void constructorNegativeTest(){
        try{
            List<String> emptyList = new ArrayList<>();
            AssassinManager manager = new AssassinManager(emptyList);
            Assert.fail("AssassinManager should throw IllegalArgumentExeption for empty list");
        }catch (IllegalArgumentException e) {
        }
    }
    @Test
    public void constructorPositiveTest(){
        List<String> list = new ArrayList<>();
        list.add("Grayson");
        list.add("Ocean");
        list.add("Chris");
        list.add("Dr.Han");
        AssassinManager manager = new AssassinManager(list);
        Assert.assertNotNull(manager.root);
    }


    @Test
    public void printKillRingNegativeTest(){
        List<String> list = new ArrayList<>();
        list.add("Grayson");
        list.add("Ocean");
        list.add("Chris");
        list.add("Dr.Han");

        AssassinManager manager = new AssassinManager(list);
        manager.kill("Ocean");
        manager.kill("Chris");
        manager.printKillRing();
    }
    @Test
    public void printKillRingPositiveTest(){
        List<String> list = new ArrayList<>();
        list.add("Grayson");
        list.add("Ocean");
        list.add("Chris");
        list.add("Dr.Han");
        AssassinManager manager = new AssassinManager(list);
        manager.printKillRing();
    }


    @Test
    public void printGraveyardNegativeTest(){
        List<String> list = new ArrayList<>();
        list.add("Grayson");
        list.add("Ocean");
        list.add("Chris");
        list.add("Dr.Han");
        AssassinManager manager = new AssassinManager(list);
        manager.printGraveyard();
    }
    @Test
    public void printGraveyardPositiveTest(){
        List<String> list = new ArrayList<>();
        list.add("Grayson");
        list.add("Ocean");
        list.add("Chris");
        list.add("Dr.Han");
        AssassinManager manager = new AssassinManager(list);
        manager.kill("Ocean");
        manager.kill("Chris");
        manager.printGraveyard();
    }


    @Test
    public void killRingContainsNegativeTest(){
        List<String> list1= new ArrayList<>();
        list1.add("Grayson");
        list1.add("Ocean");
        list1.add("Chris");
        list1.add("Dr.Han");

        AssassinManager manager = new AssassinManager(list1);
        Assert.assertFalse(manager.killRingContains("Tyler"));
        manager.kill("Ocean");
        Assert.assertFalse(manager.killRingContains("Ocean"));
    }
    @Test
    public void killRingContainsPositiveTest(){
        List<String> list1= new ArrayList<>();
        list1.add("Grayson");
        list1.add("Ocean");
        list1.add("Chris");
        list1.add("Dr.Han");

        AssassinManager manager = new AssassinManager(list1);
        Assert.assertTrue(manager.killRingContains("Grayson"));
    }


    @Test
    public void graveyardContainsNegativeTest(){
        List<String> list1= new ArrayList<>();
        list1.add("Grayson");
        list1.add("Ocean");
        list1.add("Chris");
        list1.add("Dr.Han");

        AssassinManager manager = new AssassinManager(list1);
        manager.kill("Grayson");
        Assert.assertFalse(manager.graveyardContains("ocean"));
    }
    @Test
    public void graveyardContainsPositiveTest(){
        List<String> list1= new ArrayList<>();
        list1.add("Grayson");
        list1.add("Ocean");
        list1.add("Chris");
        list1.add("Dr.Han");

        AssassinManager manager = new AssassinManager(list1);
        manager.kill("Grayson");
        Assert.assertTrue(manager.graveyardContains("Grayson"));
    }


    @Test
    public void isGameOverNegativeTest(){
        List<String> list1= new ArrayList<>();
        list1.add("Grayson");
        list1.add("Ocean");
        list1.add("Chris");
        list1.add("Dr.Han");

        AssassinManager manager = new AssassinManager(list1);
        manager.kill("Grayson");
        Assert.assertFalse(manager.isGameOver());
    }
    @Test
    public void isGameOverPositiveTest(){
        List<String> list1= new ArrayList<>();
        list1.add("Grayson");

        AssassinManager manager = new AssassinManager(list1);
        Assert.assertTrue(manager.isGameOver());
    }

    @Test
    public void winnerNegativeTest(){
        List<String> list1= new ArrayList<>();
        list1.add("Grayson");
        list1.add("Ocean");

        AssassinManager manager = new AssassinManager(list1);
        Assert.assertNull(manager.winner());
    }
    @Test
    public void winnerPositiveTest(){
        List<String> list1= new ArrayList<>();
        list1.add("Grayson");

        AssassinManager manager = new AssassinManager(list1);
        Assert.assertEquals("Grayson", manager.winner());
    }

    @Test
    public void killNegativeTest(){
        List<String> list1= new ArrayList<>();
        list1.add("Grayson");

        List<String> list2= new ArrayList<>();
        list2.add("Grayson");
        list2.add("Ocean");
        list2.add("Chris");
        list2.add("Dr.Han");

        AssassinManager gameOver = new AssassinManager(list1);
        AssassinManager manager = new AssassinManager(list2);

        try {
            gameOver.kill("Ocean");
            Assert.fail("Should throw an error because the game is over");
        } catch(IllegalStateException e) {

        }

        try {
            manager.kill("Alexander the Great");
            Assert.fail("Should throw an error because the name is not in the list");
        } catch(IllegalArgumentException e) {

        }

    }
    @Test
    public void killPositiveTest(){
        List<String> list1= new ArrayList<String>();
        list1.add("Grayson");
        list1.add("Ocean");
        list1.add("Chris");
        list1.add("Dr.Han");

        AssassinManager manager = new AssassinManager(list1);
        manager.kill("Grayson");
        Assert.assertTrue(manager.graveyardContains("Grayson"));
        manager.kill("Dr.HAN");
        Assert.assertFalse(manager.killRingContains("Dr.Han"));
    }
}