import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AssassinManagerTest {

    /**
     * The constructor should throw an error when passed an empty list
     */
    @Test
    public void constructorNegativeTest(){
        try{
            List<String> emptyList = new ArrayList<>();
            AssassinManager manager = new AssassinManager(emptyList);
            Assert.fail("AssassinManager should throw IllegalArgumentExeption for empty list");
        }catch (IllegalArgumentException e) {
        }
    }

    /**
     * The constructor should created the kill ring when passed a valid list
     */
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

    /**
     * printKillRing should print an updated list of names after kills
     */
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

    /**
     * printKillRing should print the list of names when the game starts
     */
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

    /**
     * printGraveyard should print nothing when the game starts
     */
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

    /**
     * printGraveyard should print an updated graveyard list after kills
     */
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

    /**
     * killRingContains should be false if the name is not in the list or if the name has already been killed
     */
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

    /**
     * killRingContains should be true if the name is in the list
     */
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

    /**
     * graveyardContains should be false if the name is not in the graveyard
     */
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

    /**
     * graveyardContains should be true if the name is in the graveyard
     */
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

    /**
     * isGameOver should be false if there is more than one person alive
     */
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

    /**
     * isGameOver should be true if only one person is alive
     */
    @Test
    public void isGameOverPositiveTest(){
        List<String> list1= new ArrayList<>();
        list1.add("Grayson");

        AssassinManager manager = new AssassinManager(list1);
        Assert.assertTrue(manager.isGameOver());
    }

    /**
     * winner should be null if the game is not over
     */
    @Test
    public void winnerNegativeTest(){
        List<String> list1= new ArrayList<>();
        list1.add("Grayson");
        list1.add("Ocean");

        AssassinManager manager = new AssassinManager(list1);
        Assert.assertNull(manager.winner());
    }

    /**
     * winner should be the name of the only living person if the game is over
     */
    @Test
    public void winnerPositiveTest(){
        List<String> list1= new ArrayList<>();
        list1.add("Grayson");

        AssassinManager manager = new AssassinManager(list1);
        Assert.assertEquals("Grayson", manager.winner());
    }

    /**
     * kill should throw an exception if the game is over
     * kill should throw an exception if the name is not in the kill ring
     */
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

    /**
     * kill should send the input name to the graveyard
     * kill should ignore case
     */
    @Test
    public void killPositiveTest(){
        List<String> list1= new ArrayList<>();
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