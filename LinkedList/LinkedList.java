public class LinkedList {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public Node head;
    public Node tail;

    public static int size = 0;

    //add first
    public void addFirst(int data) {
        Node newNode = new Node(data);
        size++;
        if (head == null) {
            head = tail = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
    }

    //add last
    public void addLast(int data) {
        Node newNode = new Node(data);
        size++;
        if (head == null) {
            head = tail = newNode;
            return;
        }
        tail.next = newNode;
        tail = newNode;
    }

    //print linked-list
    public void printLL() {
        if (head == null) {
            System.out.println("Empty LinkedList");
        }
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + "->");
            temp = temp.next;
        }
        System.out.println("null");
    }

    //add in the middle
    public void addMiddle(int idx, int data) {
        if (idx == 0) {
            addFirst(data);
            return;
        }
        Node newNode = new Node(data);
        size++;
        Node temp = head;
        int i = 0;
        while (i < idx - 1) {
            temp = temp.next;
            i++;
        }
        newNode.next = temp.next;
        temp.next = newNode;
    }
    
    //remove first
    public int removeFirst() {
        if (size == 0) {
            System.out.println("LinkedList is Empty");
            return Integer.MIN_VALUE;
        } else if (size == 1) {
            int val = head.data;
            head = tail = null;
            size = 0;
            return val;
        }
        int val = head.data;
        head = head.next;
        size--;
        return val;
    }

    //remove last
    public int removeLast() {
        if (size == 0) {
            System.out.println("LinkedList is Empty");
            return Integer.MIN_VALUE;
        } else if (size == 1) {
            int val = head.data;
            head = tail = null;
            size = 0;
            return val;
        }
        Node prev = head;
        for (int i = 0; i < size - 2; i++) {
            prev = prev.next;
        }
        int val = prev.next.data; //tail data
        prev.next = null;
        tail = prev;
        size--;
        return val;
    }

    //iterative search
    public int iterativeSearch(int key) {
        if (head == null) {
            System.out.println("Empty LinkedList");
            return -1;
        }
        Node temp = head;
        int i = 0;
        while (temp != null) {
            if (temp.data == key) {
                return i;
            }
            temp = temp.next;
            i++;
        }
        return -1;
    }

    //recursive search  [ TC--> O(n) ]
    public int healper(Node head, int key) {
        if(head == null) {
            return -1;
        }

        if(head.data == key) {
            return 0;
        }

        int idx = healper(head.next, key);
        if(idx == -1) {
            return -1;
        }

        return idx + 1;
    }

    public int recSearch(int key) {
        return healper(head, key);
    }

    //reverse Linked list  [ TC--> O(n) ]
    public void reverseLL() {
        Node prev = null;
        Node curr = tail = head;
        Node next;

        while(curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
    }

    //deleting nth node from the end
    public void deleteNthNodefromEnd(int n) {
        int siz = 0;
        Node temp = head;
        while(temp != null) {
            temp = temp.next;
            siz++;
        }

        if(n == siz) {
            head = head.next;
            return;
        }

        int i = 1;
        int iToFind = siz - n;
        Node prev = head;
        while( i< iToFind) {
            prev = prev.next;
            i++;
        }

        prev.next = prev.next.next;
        return;
    }

    //slow-fast approach to find the mid node
    public Node findMid(Node head) {
        Node slow = head; //+1
        Node fast = head; //+2

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow; //slow is the middle node
    }

    public boolean isPalindrome() {
        if (head == null || head.next == null) {
            return true;
        }

        //find mid
        Node midNode = findMid(head);

        //reverse 2nd half
        Node prev = null;
        Node curr = midNode;
        Node next;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        Node rightHead = prev;
        Node leftHead = head;

        //check left half and right half
        while (rightHead != null) {
            if (leftHead.data != rightHead.data) {
                return false;
            }
            leftHead = leftHead.next;
            rightHead = rightHead.next;
        }
        return true;
    }
    
    // Detect the cycle
    public boolean isCycle() {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        // ll.addFirst(2);
        // ll.addFirst(1);
        // ll.addLast(3);
        // ll.addLast(3);
        // ll.addLast(2);
        // ll.addLast(7);
        // ll.addMiddle(5, 1);
        // ll.removeFirst();
        // ll.removeLast();
        // ll.addFirst(1);
        // ll.printLL();
        // System.out.println(size);
        // System.out.println("Key found at index (Iterative Search): "+ll.iterativeSearch(4));
        // System.out.println("Key found at index (Recursive Search): "+ll.recSearch(5));
        // ll.reverseLL();
        // ll.deleteNthNodefromEnd(4);
        // ll.printLL();
        // System.out.println(ll.isPalindrome());

        ll.head = new Node(1);
        ll.head.next = new Node(2);
        ll.head.next.next = new Node(3);
        ll.head.next.next.next = new Node(4);
        ll.head.next.next.next.next = new Node(5);
        ll.head.next.next.next.next.next = ll.head;

        // ll.printLL();
        System.out.println(ll.isCycle());
    }
}