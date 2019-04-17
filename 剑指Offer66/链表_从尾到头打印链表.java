/*
 * 输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。*/
import java.util.ArrayList;
import java.util.Stack;

public class 链表_从尾到头打印链表 {
//    非递归做法
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        Stack<Integer> tmp = new Stack<>();
        while(listNode!=null){
            tmp.push(listNode.val);
            listNode = listNode.next;
        }
        ArrayList<Integer> res = new ArrayList<>();
        while (!tmp.empty())
            res.add(tmp.pop());
        return res;
    }

//    递归做法
public ArrayList<Integer> printListFromTailToHead1(ListNode listNode) {
    ArrayList<Integer> re = new ArrayList<>();
    if(listNode==null)
        return re;
    else{
        printListRev(listNode, re);
        return re;
    }
}

    public void printListRev(ListNode l, ArrayList re){
        if(l==null)
            return;
        else{
            printListRev(l.next, re);
            re.add(l.val);
        }
    }
}
