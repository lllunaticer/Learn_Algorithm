/*
* 题目描述
输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，
另一个特殊指针指向任意一个节点），返回结果为复制后复杂链表的head。
（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
*/

/*
 * 题解
 * 见 链表_复杂链表的复制.png
 * */
public class 链表_复杂链表的复制 {
    public RandomListNode Clone(RandomListNode pHead) {
//        第一步，复制链表中的每个节点，并将其插入到该节点和其后面节点之间
        for(RandomListNode p = pHead; p!=null;){
            RandomListNode np = new RandomListNode(p.label);
            RandomListNode next = p.next;
            np.next = next;
            p.next = np;
            p = next;
        }

//        第二步，依据主链表在副链表上连接相应的random指针
        for(RandomListNode p = pHead;p!=null;p = p.next.next){
            if(p.random!=null)
                p.next.random = p.random.next;
        }

//        第三步,将副链表从主链表上拆下来，串成新链表，并将主链表还原
//        为副链表做一个虚拟头节点
        RandomListNode dummy = new RandomListNode(-1);
        RandomListNode cur = dummy;
        for(RandomListNode p = pHead;p!=null;){
            cur.next=p.next;
            cur = cur.next;
            p.next = cur.next;//将主链表重新连接
            p = p.next;
        }
        return dummy.next;
    }
}

class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}
