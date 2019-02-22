package archive;

class Solution2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result =  new ListNode(0);
        ListNode tail = result;
        ListNode s = new ListNode(0);
        while(l1!=null||l2!=null){
            if(l1==null) l1 = s;
            if(l2==null) l2 = s;
            int r_temp= l1.val+l2.val+tail.val;
            if(r_temp>=10){
                tail.val = r_temp%10;
                tail.next = new ListNode(1);
            }else{
                tail.val = r_temp;
                if(l1.next!=null||l2.next!=null)
                    tail.next = new ListNode(0);
            }
            tail = tail.next;
            l1=l1.next;
            l2=l2.next;
        }
        return result;
    }

    public static void main(String[] args){
        ListNode l1 = new ListNode(9);
        l1.next = new ListNode(2);
        ListNode l2 = new ListNode(9);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(1);
        Solution2 exe = new Solution2();
        ListNode l3 = exe.addTwoNumbers(l1, l2);
    }
}

class ListNode{
    int val;
    ListNode next;
    ListNode(int x){val=x;}
}