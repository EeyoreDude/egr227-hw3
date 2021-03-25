import java.util.List;

/**
 * Collaborated with: Dalton Adcock
 */

public class AssassinManager {
    AssassinNode root;
    AssassinNode grave;

    /**
     * The constructor. Takes a list of names and initializes the kill ring of nodes.
     * @param names a list of names
     */
    public AssassinManager(List<String> names) {
        if(names.isEmpty())
            throw new IllegalArgumentException("The list of names is either empty or null");
        root = createKillRing(names);
        grave = null;
    }

    /**
     * The helper method for the constructor
     * Takes a list of names and recursively creates the kill ring
     * @param names a list of names
     * @return either itself (recursively iterating to the next node), or a new node that points to null (for the final node)
     */
    private AssassinNode createKillRing(List<String> names) {
            if (names.size() > 1)
                return new AssassinNode(names.get(0), createKillRing(names.subList(1, names.size())));
            else
                return new AssassinNode(names.get(0));
    }

    /**
     * Prints each living participant and who their target is
     */
    public void printKillRing(){
       stalkList(root);
    }

    /**
     * A helper method for printKillRing
     * Takes in a starting node and recursively iterates to the end of the chain, printing each node's name and their target's name
     * @param currNode a starting node
     */
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

    /**
     * Prints each dead participant and who their killer is
     */
    public void printGraveyard(){
        graveList(grave);
    }

    /**
     * The helper method for printGraveyard
     * Takes in a starting node and recursively iterates to the end of the chain, printing each node's name and their killer's name
     * @param currNode a starting node
     */
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

    /**
     * Returns whether or not the kill ring contains a name
     * @param name the name to be searched
     * @return true if the kill ring contains the name, false if otherwise
     */
    public boolean killRingContains(String name){
        return contains(root, name);
    }

    /**
     * Returns whether or not the graveyard contains a name
     * @param name the name to be searched
     * @return true if the graveyard contains the name, false if otherwise
     */
    public boolean graveyardContains(String name){
        return contains(grave, name);
    }

    /**
     * The helper method for killRingContains and graveyardContains
     * Recursively searches a node chain for a given name, starting at the given node
     * @param currNode a starting node
     * @param name the name to be searched
     * @return true if the chain contains the name, false if otherwise
     */
    private boolean contains(AssassinNode currNode, String name){
        if(currNode == null)
            return false;
        else if(currNode.name.equalsIgnoreCase(name))
            return true;
        else
            return contains(currNode.next, name);
    }

    /**
     * Returns whether or not the game is over
     * @return true if the game is over, false if it is not
     */
    public boolean isGameOver(){
        return root.next == null;
    }

    /**
     * Returns the name of the winner, if one is present
     * @return the name of the winner, or null if the game is not over
     */
    public String winner(){
        if(isGameOver())
            return root.name;
        else
            return null;
    }

    /**
     * Sends the name to the graveyard
     * Assigns the name's target to the name's killer
     * Throws an error if the game is over
     * Throws an error if the name is not a part of the kill ring
     * @param name the name to kill
     */
    public void kill(String name){
        if(isGameOver())
            throw new IllegalStateException("The game is over.");
        if(! killRingContains(name))
            throw new IllegalArgumentException(name + " is not part of the kill ring.");

        killName(root, name);
    }

    /**
     * The helper method for kill
     * Recursively searches through a chain until it finds the person who's target is the name to be killed
     * Sets the dead person's killer to the person who killed them
     * Gives the dead person's target to the killer
     * Moves the dead person to the front of the graveyard
     * Handles all special cases (like killing the root node)
     * @param currNode a starting node
     * @param name the name to kill
     */
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
                tempNode.next = grave;
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
