package Lab6;
//Ronin Sloan And Tuan Le
//3/8/23
//Purpose: Use BST to search for people.
//delete functionality

import java.util.Scanner;

public class RT_Dictionary{
    Node top;
    int size;
    Node root;
    public static void main (String[] args) {
        menuReaction();
    }

    //start of menu method
    public static int menu(){
        System.out.println("Please press...");
        System.out.println("1 to add a new person to list.");
        System.out.println("2 to remove a person from list.");
        System.out.println("3 to search for someone's ID on the list.");
        System.out.println("4 to change an entry on the list.");
        System.out.println("5 to quit");

        Scanner standIn = new Scanner(System.in);
        int input = standIn.nextInt();
        return input;
    }
    //end of menu method

    //start of menu reaction
    //this method creates a while loop and runs based off of what
    //your input was.
    public static void menuReaction(){
        artWelcome();
        System.out.println("Welcome to our employee dictionary.");
        int input = menu();
        RT_Dictionary tree = new RT_Dictionary();
        Scanner scan = new Scanner(System.in);
        while(input != 5){
            if(input == 1){
                menuInput1(tree);
                artCreated();
            }
            else if(input == 2){
                System.out.println("Please enter the ID of the entry you'd like to delete.");
                int delete;
                delete = scan.nextInt();
                tree.delete(tree.root, delete);
                artDeleted();
            }
            else if(input == 3){
                System.out.println("Please enter an ID to search for.");
                int search;
                search = scan.nextInt();
                artFound();
                System.out.println("Found:" + tree.find(search));
            }
            else if(input == 4){
                System.out.println("Which entry would you like to change?");
                int edit;
                edit = scan.nextInt();
                tree.delete(tree.root, edit);
                menuInput1(tree);
                artEdited();

            }
            else if(input == 5){
                artGoodbye();
                return;
            }   
            else if(input != 5){
                System.out.println("Incorrect input, please try again.");
            }
            input = menu();
        }
    }//end of menu reaction method.

    //menu input 1 method
    private static void menuInput1(RT_Dictionary tree){
        Scanner scan = new Scanner(System.in);
        String first;
        String last;
        String address;
        String city;
        String state;
        String zip;
        String email;
        String phone;
        int id;

        System.out.println("Enter first name.");
        first = scan.next();
        System.out.println("Enter last name.");
        last = scan.next();
        System.out.println("Enter street address.");
        address = scan.next();
        System.out.println("Enter city name.");
        city = scan.next();
        System.out.println("Enter state name.");
        state = scan.next();
        System.out.println("Enter zip code.");
        zip = scan.next();
        System.out.println("Enter email.");
        email = scan.next();
        System.out.println("Enter phone name.");
        phone = scan.next();
        System.out.println("Enter ID");
        id = scan.nextInt();
        tree.add(id, first, last, address, city, state, zip, email, phone);
    }

    //add node to BST method
    public void add(int id, String first, String last, String address, String city, String state, String zip, String email, String phone) {
        Node newNode = new Node(id, first, last, address, city, state, zip, email, phone);
        if (root == null) {
            root = newNode;
        } else {
            Node focusNode = root;
            Node parent;
            while (true) {
                parent = focusNode;
                if (id < focusNode.id) {
                    focusNode = focusNode.leftChild;
                    if (focusNode == null) {
                        parent.leftChild = newNode;
                        return;
                    }
                } else { 
                    focusNode = focusNode.rightChild;
                    if (focusNode == null) {
                        parent.rightChild = newNode;
                        return; 
                    }
                }
            }
        }
    }//end of add node method

    //delete node method
    public  Node delete(Node root, int value) {
        if (root == null)
            return null;
        if (root.id > value) {
            root.leftChild = delete(root.leftChild, value);
        } else if (root.id < value) {
            root.rightChild = delete(root.rightChild, value);
        } else {
            // if nodeToBeDeleted have both children
            if (root.leftChild != null && root.rightChild != null) {
                Node temp = root;
                // Finding minimum element from right
            Node minNodeForRight = minimumElement(temp.rightChild);
                // Replacing current node with minimum node from right subtree
                root.id = minNodeForRight.id;
                // Deleting minimum node from right now
                root.rightChild = delete(root.rightChild, minNodeForRight.id);
            }
            // if nodeToBeDeleted has only left child
            else if (root.leftChild != null) {
                root = root.leftChild;
            }
            // if nodeToBeDeleted has only right child
            else if (root.rightChild != null) {
                root = root.rightChild;
            }
            // if nodeToBeDeleted do not have child (Leaf node)
            else
                root = null;
        }
        return root;
    }

    public Node minimumElement(Node root) {
        if (root.leftChild == null)
            return root;
        else {
            return minimumElement(root.leftChild);
        }
    }

    //find node method
    public Node find(int search) {
        //start at root
        Node focusNode = root;
        while (focusNode.id != search) {
            if (search < focusNode.id) {
                focusNode = focusNode.leftChild;
            } else {
                focusNode = focusNode.rightChild;
            }
            if (focusNode == null)
                return null;
        }
        return focusNode;
    }//end of find node method

    //ASCII art methods
    public static void artWelcome(){
        System.out.println(" _      _       _                          _");
        System.out.println("/ \\    / \\ ___ | |  ___  ___    ___   ___ | |");
        System.out.println("\\  \\/\\/  // _ \\| | / __>/ _ \\  /   \\ / _ \\| |");
        System.out.println(" \\      / | __/| |_\\ \\_( (_) )| | | || __/|_|");
        System.out.println("  \\_/\\_/  \\___>|___/\\__>\\___/ |_|_|_|\\___><_>");
        System.out.println();
    }
    public static void artGoodbye(){
        System.out.println(" ___               _  _               _ ");
        System.out.println("/  _>  ___  ___  _| || |_  _ _  ___  | |");
        System.out.println("| |_/\\/ . \\/ . \\/ . || . \\| | |/ ._> |_|");
        System.out.println("\\____/\\___/\\___/\\___||___/\\__ |\\___| <_>");
        System.out.println("                          <___|         ");
        System.out.println();
    }
    public static void artCreated(){
        System.out.println("   ___              _          _   _ ");
        System.out.println("  / __|_ _ ___ __ _| |_ ___ __| | | |");
        System.out.println(" | (__| '_/ -_/ _` |  _/ -_/ _` | |_|");
        System.out.println("  \\___|_| \\___\\__,_|\\__\\___\\__,_| <_>");
    }
    public static void artFound(){
        System.out.println("  ___                 _   _ ");
        System.out.println(" | __|__ _  _ _ _  __| | | | ");
        System.out.println(" | _/ _ \\ || | ' \\/ _` | |_|  ");
        System.out.println(" |_|\\___/\\_,_|_||_\\__,_| <_> ");
        System.out.println();
    }
    public static void artEdited(){
        System.out.println("  ___    _ _ _          _   _ ");
        System.out.println(" | __|__| (_) |_ ___ __| | | |");
        System.out.println(" | _|/ _` | |  _/ -_) _` | |_|");
        System.out.println(" |___\\__,_|_|\\__\\___\\__,_| <_>");
        System.out.println();
    }
    public static void artDeleted(){
        System.out.println("  ___      _     _          _   _ ");
        System.out.println(" |   \\ ___| |___| |_ ___ __| | | |");
        System.out.println(" | |) / -_) / -_)  _/ -_) _` | |_|");
        System.out.println(" |___/\\___|_\\___|\\__\\___\\__,_| <_>");
        System.out.println();
    }
}//end of class


class Node {

    int id;
    String first;
    String last;
    String address;
    String city;
    String state;
    String zip;
    String email;
    String phone;
    Node leftChild;
    Node rightChild;   
    Node prev = this;   
    Node next=this;
    int data;

    Node(int id, String first, String last, String address, String city, String state, String zip, String email, String phone) {
    
        this.id = id;
        this.first = first;
        this.last = last;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.email = email;
        this.phone = phone;
    } //for queue

    Node(int data, String first, String last, String address, String city, String state, 
    String zip, String email, String phone, Node next, Node prev) {
        this.data = data;
        this.first = first;
        this.last = last;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.email = email;
        this.phone = phone;
        this.next = next;
        this.prev = prev;
    }

    public String toString() {
        return " " + first + " " + last + "\nAddress: " + address + " " + city + " " + state + "\nZip: " + zip + 
        "\nEmail: " + email + "\nPhone #: " + phone + "\nID: " + id +"\n";
   
    }
    
}

