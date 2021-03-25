import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.List;

public class AssassinManager {
    AssassinNode root;
    AssassinNode grave;

    public AssassinManager(List<String> names) {
        if(names.isEmpty())
            throw new IllegalArgumentException("The list of names is either empty or null");
        root = createKillRing(names);
        grave = null;
    }

    private AssassinNode createKillRing(List<String> names) {
            if (names.size() > 1)
                return new AssassinNode(names.get(0), createKillRing(names.subList(1, names.size())));
            else
                return new AssassinNode(names.get(0));
    }

    public void printKillRing(){
       stalkList(root);
    }

    private void stalkList(AssassinNode currNode){
        if(root.next == null)
            System.out.println("    " + currNode.name + " won the game!");
        else if(currNode.next == null)
            System.out.println("    " + currNode.name + " is stalking " + root.name);
        else {
            System.out.println("    " + currNode.name + " is stalking " + currNode.next.name);
            stalkList(currNode.next);
        }
    }

    public void printGraveyard(){
        graveList(grave);
    }

    private void graveList(AssassinNode currNode){
        if(currNode != null) {
            if (currNode.next == null)
                System.out.println("    " + currNode.name + " was killed by " + currNode.killer);
            else {
                System.out.println("    " + currNode.name + " was killed by " + currNode.killer);
                graveList(currNode.next);
            }
        }

    }

    public boolean killRingContains(String name){
        return contains(root, name);
    }

    public boolean graveyardContains(String name){
        return contains(grave, name);
    }

    private boolean contains(AssassinNode currNode, String name){
        if(currNode == null)
            return false;
        else if(currNode.name.equalsIgnoreCase(name))
            return true;
        else
            return contains(currNode.next, name);
    }

    public boolean isGameOver(){
        return root.next == null;
    }

    public String winner(){
        if(isGameOver())
            return root.name;
        else
            return null;
    }

    public void kill(String name){
        if(isGameOver())
            throw new IllegalStateException("The game is over.");
        if(! killRingContains(name))
            throw new IllegalArgumentException(name + " is not part of the kill ring.");

        killName(root, name);
    }

    private void killName(AssassinNode currNode, String name){
        if(currNode.next == null){ // if the killer is the last in the list (the root died)
            root.killer = currNode.name; // make the current person the killer of the root
            AssassinNode tempNode = root; // save the location of the root
            root = root.next; // make the new root the next player
            if(grave == null){ // on the first kill
                tempNode.next = null; // make the dead person point to null
                grave = tempNode; // make the dead person the root of the graveyard
            } else {
                tempNode.next = grave;
                grave = tempNode;
            }
        } else if(currNode.next.name.equalsIgnoreCase(name)){ // if the next person in the list is the name (aka we found the killer)
            currNode.next.killer = currNode.name; // make the current person the killer of the next person
            AssassinNode tempNode = currNode.next; // save the location of the dead person
            currNode.next = currNode.next.next; // give the current person the dead persons target
            if(grave == null){ // on the first kill
                tempNode.next = null; // make the dead person point to null
                grave = tempNode; // make the dead person the root of the graveyard
            } else {
                tempNode.next = grave; //
                grave = tempNode;
            }
        } else
            killName(currNode.next, name);
    }

    //////// DO NOT MODIFY AssassinNode.  You will lose points if you do. ////////
    /**
     * Each AssassinNode object represents a single node in a linked list
     * for a game of Assassin.
     */
    private static class AssassinNode {
        public final String name;  // this person's name
        public String killer;      // name of who killed this person (null if alive)
        public AssassinNode next;  // next node in the list (null if none)

        /**
         * Constructs a new node to store the given name and no next node.
         */
        public AssassinNode(String name) {
            this(name, null);
        }

        /**
         * Constructs a new node to store the given name and a reference
         * to the given next node.
         */
        public AssassinNode(String name, AssassinNode next) {
            this.name = name;
            this.killer = null;
            this.next = next;
        }
    }
}
