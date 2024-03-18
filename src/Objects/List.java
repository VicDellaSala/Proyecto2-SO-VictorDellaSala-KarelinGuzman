/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;

/**
 *
 * @author Estudiante
 */
public class List {
    
    private Node head;
    private int length;

    public List() {
        this.head = null;
        this.length = 0;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }
    
    public boolean isEmpty() {
        return getHead() == null;
    }
    
    public void insertBegin(int element) {
        Node node = new Node(element);
        if (isEmpty()) {
            setHead(node);
        } else {
            node.setNext(getHead());
            setHead(node);
        }
        length++;
    }
    
    public void insertEnd(int element) {
        Node node = new Node(element);
        if (isEmpty()) {
            setHead(node);
        } else {
            Node pointer = getHead();
            while (pointer.getNext() != null) {
                pointer = pointer.getNext();
            }
            pointer.setNext(node);
        }
        length++;
    }
    
    public void insertInIndex(int element) {
        Node node = new Node(element);
        if (isEmpty()) {
            setHead(node);
        } else {
            Node pointer = getHead();
            while (pointer.getNext() != null) {
                pointer = pointer.getNext();
            }
            pointer.setNext(node);
        }
        length++;
    }
    
    public void printList() {
        Node pointer = getHead();
        while (pointer != null) {
            System.out.print("[ "+pointer.getElement()+" ]");
            pointer = pointer.getNext();
        }
    }
    
}
