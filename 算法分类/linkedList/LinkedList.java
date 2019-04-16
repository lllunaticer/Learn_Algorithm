/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 **/

/*Design your implementation of the linked list. You can choose to use the singly linked list or the doubly linked list. A node in a singly linked list should have two attributes: val and next. val is the value of the current node, and next is a pointer/reference to the next node. If you want to use the doubly linked list, you will need one more attribute prev to indicate the previous node in the linked list. Assume all nodes in the linked list are 0-indexed.

Implement these functions in your linked list class:

get(index) : Get the value of the index-th node in the linked list. If the index is invalid, return -1.
addAtHead(val) : Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
addAtTail(val) : Append a node of value val to the last element of the linked list.
addAtIndex(index, val) : Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
deleteAtIndex(index) : Delete the index-th node in the linked list, if the index is valid.
Example:

MyLinkedList linkedList = new MyLinkedList();
linkedList.addAtHead(1);
linkedList.addAtTail(3);
linkedList.addAtIndex(1, 2);  // linked list becomes 1->2->3
linkedList.get(1);            // returns 2
linkedList.deleteAtIndex(1);  // now the linked list is 1->3
linkedList.get(1);            // returns 3
Note:

All values will be in the range of [1, 1000].
The number of operations will be in the range of [1, 1000].
Please do not use the built-in LinkedList library.*/

package linkedList;


public class LinkedList {
    ListNode head;

    /**
     * Initialize your data structure here.
     */
    public LinkedList() {
    }

    public static void main(String[] args) {
        LinkedList a = new LinkedList();
//        a.addAtHead(1);
//        a.addAtTail(3);

        a.get(0);
        a.addAtIndex(1,2);
        a.get(0);
        a.get(1);
        a.addAtIndex(0,1);
        a.get(0);
        a.get(1);
//        a.deleteAtIndex(1);

//        a.addAtTail(36);
        a.addAtIndex(3,27);
//        a.addAtTail(76);
//        a.addAtHead(7);
//        a.addAtHead(36);
//        a.addAtHead(34);
//        a.addAtTail(91);
//        a.addAtTail(69);
//        a.addAtHead(37);
//        a.addAtTail(38);
//        a.addAtTail(4);
//        a.addAtHead(66);
//        a.addAtTail(38);
//        a.deleteAtIndex(14);
//        a.addAtTail(12);
//        a.addAtTail(32);
//        a.get(5);
//        a.addAtIndex(7,5);
//        a.addAtHead(74);
//        a.get(7);
//        a.get(13);
//        a.addAtHead(11);
//        a.get(8);
//        a.addAtIndex(10,9);
//        a.addAtTail(19);
//        a.addAtIndex(3,76);
//        a.addAtHead(7);
//        a.addAtHead(37);
//        a.addAtHead(99);
//        a.get(10);
//        a.addAtHead(12);
//        a.addAtIndex(1,20);
//        a.addAtTail(29);
//        a.addAtHead(42);
//        a.addAtIndex(21,52);
//        a.get(11);
//        a.addAtTail(44);
//        a.addAtTail(47);
//        a.addAtIndex(6,27);
//        a.addAtIndex(31,85);
//        a.addAtHead(59);
//        a.addAtHead(57);
//        a.get(4);
//        a.addAtTail(99);
//        a.addAtIndex(13,83);
//        a.addAtIndex(10,34);
//        a.addAtHead(48);
//        a.deleteAtIndex(9);
//        a.addAtIndex(22,64);
//        a.addAtHead(69);
//        a.deleteAtIndex(33);
//        a.addAtTail(5);
//        a.deleteAtIndex(18);
//        a.addAtTail(87);
//        a.addAtHead(42);
//        a.addAtTail(1);
//        a.addAtTail(35);
//        a.addAtHead(31);
//        a.addAtTail(67);
//        a.addAtIndex(36,46);
//        a.deleteAtIndex(23);
//        a.addAtHead(64);
//        a.addAtHead(81);
//        a.addAtHead(29);
//        a.addAtTail(50);
//        a.get(23);
//        a.addAtIndex(36,63);
//        a.addAtTail(8);
//        a.addAtTail(19);
//        a.addAtTail(98);
//        a.deleteAtIndex(22);
//        a.get(28);
//        a.addAtHead(42);
//        a.get(24);
//        a.get(34);
//        a.addAtTail(32);
//        a.deleteAtIndex(25);
//        a.addAtTail(53);
//        a.addAtIndex(55,76);
//        a.addAtHead(38);
//        a.addAtIndex(23,98);
//        a.addAtTail(27);
//        a.get(18);
//        a.addAtIndex(44,27);
//        a.addAtIndex(16,8);
//        a.addAtHead(70);
//        a.addAtHead(15);
//        a.get(9);
//        a.get(27);
//        a.get(59);
//        a.addAtIndex(40,50);
//        a.addAtHead(15);
//        a.addAtIndex(11,57);
//        a.addAtHead(80);
//        a.addAtTail(50);
//        a.addAtIndex(23,37);
//        a.get(12);
    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (index < 0) {
            return -1;
        }else {
            ListNode temp = head;
            int i =0;
            while(i!=index&&temp!=null){
                temp = temp.next;
                i++;
            }
            if(i==index&&temp!=null){
                return temp.val;
            }else {
                return -1;
            }
        }

    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        ListNode newhead = new ListNode(val);
        newhead.next = head;
        head = newhead;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        ListNode newtail = new ListNode(val);
        ListNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newtail;
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        if (index < 0) {
        }
        else {
            if (index == 0) {
                addAtHead(val);
            } else if(head!=null){
                ListNode addnode = new ListNode(val);
                ListNode temp = head;
                int i = 0;
                while (temp.next != null) {
                    if (i + 1 == index)
                        break;
                    temp = temp.next;
                    i++;
                }
                if (i + 1 == index) {
                    addnode.next = temp.next;
                    temp.next = addnode;
                }

            }

        }
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        if (index < 0 || head ==null) {
        } else {
            if (index == 0) {
                head = head.next;
            } else {
                ListNode temp = head;
                int i = 0;
                while (temp.next != null) {
                    if (i+1 == index)
                        break;
                    temp = temp.next;
                    i++;
                }
                if (i+1 == index && temp.next!=null) {
                    temp.next = temp.next.next;
                }

            }
        }
    }

//    @Override
//    public String toString() {
//        Node temp = head;
//        while(temp!=null){
//            temp = temp.next;
//            System.out.println(temp.val);
//        }
//        return " ";
//    }

}

