/**
* Name         : srishti aggarwal
* Matric No    : A0157006M
* Plab Acct.   : 
*
*/

import java.util.*;

public class Cards {
   private TailedLinkedList<Card> deck = new TailedLinkedList<Card>();
   private ListNode<Card> curr = deck.getHead();

   public void run() {
      Scanner scan = new Scanner(System.in);
      int _numCards = scan.nextInt();
      inputCards(_numCards, scan);
      int _numQ = scan.nextInt();
      doQuery(_numQ, scan);
   }

   public static void main(String[] args) {
      Cards myCards = new Cards();
      myCards.run();
   }

   public void inputCards(int num, Scanner s) {
      int i;

      for(i = 0; i < num; i++) {
         String n = s.next();
         int a = s.nextInt();

         deck.addLast(new Card(n, a));
      }
   }

   public void doQuery(int num, Scanner s) {
      int i;

      for(i = 0; i < num; i++) {
         String query = s.next();

         if(query.equals("swap")) {
            int a = s.nextInt() - 1;
            int b = s.nextInt() - 1;
            int c = s.nextInt() - 1;
            int d = s.nextInt() - 1;

            //to be continued 

         } else if(query.equals("details")) {
            int index = s.nextInt();
            int count = 0;

            curr = deck.getHead();

            while (count < index && curr.getNext() != null) {
               curr = curr.getNext();
               count++;
            }

            Card tempCard  = curr.getElement();
            String tempName = tempCard.getName();
            int tempAge = tempCard.getAge();

            System.out.printf("%s %d\n", tempName, tempAge);

         } else if(query.equals("position")) {
            String tempName = s.next();
            int count = 0;

            curr = deck.getHead();
            
            while (curr.getNext() != null) {
               if(curr.getElement().getName().equals(tempName)) {
                  System.out.printf("%d\n", count+1);
                  break;
               }
               curr = curr.getNext();
            }
         } else if(query.equals("shuffle")) {
            TailedLinkedList<Card> deck1 = new TailedLinkedList<Card>();
            TailedLinkedList<Card> deck2 = new TailedLinkedList<Card>();
            TailedLinkedList<Card> deckFinal = new TailedLinkedList<Card>();

            int half, count = 0;

            if(deck.size() % 2 == 0) {
               half = deck.size()/2;
            } else {
               half = deck.size()/2 + 1;
            }

            curr = deck.getHead();

            while(count < half) {
               deck1.addLast(curr.getElement());
               curr = curr.getNext();
               count++;
            }

            count = 0;

            while(count < (deck.size() - half)) {
               deck2.addLast(curr.getElement());
               curr = curr.getNext();
               count++;
            }

            count = 0;
            ListNode<Card> curr1 = deck1.getHead();
            ListNode<Card> curr2 = deck2.getHead();

            while (count < deck2.size()) {
               deckFinal.addLast(curr1.getElement());
               deckFinal.addLast(curr2.getElement());
               count++;
               curr1 = curr1.getNext();
               curr2 = curr2.getNext();
            }

            if(deck1.size() != deck2.size()) {
               deckFinal.addLast(curr1.getElement());
            }

            deck = deckFinal;

            System.out.println("shuffle has been performed");

         } else if(query.equals("print")) {
            curr = deck.getHead();

            while(curr.getNext() != null) {
               String tempName = curr.getElement().getName();
               System.out.printf("%s ", tempName);
               curr = curr.getNext();
            }

            if(curr.getNext() == null) {
               String tempName = curr.getElement().getName();
               System.out.printf("%s\n", tempName);
            }
         }
      }
   }
}

class Card {
   private String name;
   private int age;

   public Card(String n, int a) {
      name = n;
      age = a;
   }

   public String getName() {
      return this.name;
   }

   public int getAge() {
      return this.age;
   }
}
class TailedLinkedList<E> {

   // Data attributes
   private ListNode<E> head;
   private ListNode<E> tail;
   private int num_nodes;

   public TailedLinkedList() {
      this.head = null;
      this.tail = null;
      this.num_nodes = 0;
   }

   // Return true if list is empty; otherwise return false.
   public boolean isEmpty() {
      return (num_nodes == 0);
   }

   // Return number of nodes in list.
   public int size() {
      return num_nodes;
   }

   // Return value in the first node.
   public E getFirst() throws NoSuchElementException {
      if (head == null)
         throw new NoSuchElementException("can't get from an empty list");
      else
         return head.getElement();
   }

   // Return true if list contains item, otherwise return false.
   public boolean contains(E item) {
      for (ListNode<E> n = head; n != null; n = n.getNext())
         if (n.getElement().equals(item))
            return true;

      return false;
   }



   // Add item to front of list.
   public void addFirst(E item) {
      head = new ListNode<E>(item, head);
      num_nodes++;
      if (num_nodes == 1) tail = head;
   }

   // Return reference to first node.
   public ListNode<E> getHead() {
      return head;
   }

   // Return reference to last node of list.
   public ListNode<E> getTail() {
      return tail;
   }

   // Add item to end of list.
   public void addLast(E item) {
      if (head != null) {
         tail.setNext(new ListNode<E>(item));
         tail = tail.getNext();
      } else {
         tail = new ListNode<E>(item);
         head = tail;
      }
      num_nodes++;
   }

   // Remove node after node referenced by current
   public E removeAfter(ListNode<E> current) throws NoSuchElementException {
      E temp;
      if (current != null) {
         ListNode<E> nextPtr = current.getNext();
         if (nextPtr != null) {
            temp = nextPtr.getElement();
            current.setNext(nextPtr.getNext());
            num_nodes--;
            if (nextPtr.getNext() == null) // last node is removed
               tail = current;
            return temp;
         } else
            throw new NoSuchElementException("No next node to remove");
      } else { // if current is null, we want to remove head
         if (head != null) {
            temp = head.getElement();
            head = head.getNext();
            num_nodes--;
            if (head == null)
               tail = null;
            return temp;
         } else
            throw new NoSuchElementException("No next node to remove");
      }
   }

   // Remove first node of list.
   public E removeFirst() throws NoSuchElementException {
      return removeAfter(null);
   }

   // Remove item from list
   public E remove(E item) throws NoSuchElementException {
      ListNode<E> current = head;
      if (current == null) {
         throw new NoSuchElementException("No node to remove");
      }
      if (current.getElement().equals(item)) {
         return removeAfter(null);
      }
      while (current.getNext().getElement() != null) {
         if (current.getNext().getElement().equals(item)) {
            return removeAfter(current);
         }
         current = current.getNext();
      }
      throw new NoSuchElementException("No node to remove");
   }
}

class ListNode<E> {
   protected E element;
   protected ListNode<E> next;

   /* constructors */
   public ListNode(E item) {
      this.element = item;
      this.next = null;
   }

   public ListNode(E item, ListNode<E> n) {
      element = item;
      next = n;
   }

   /* get the next ListNode */
   public ListNode<E> getNext() {
      return this.next;
   }

   /* get the element of the ListNode */
   public E getElement() {
      return this.element;
   }

   public void setNext(ListNode<E> item) {
      this.next = item;
   }

   public void setElement(E item) {
      this.element = item;
   }
}


