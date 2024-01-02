
/*
*题目描述
定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
* */

/*
*题解：
* 算法
(单调栈) O(1)
我们除了维护基本的栈结构之外，还需要维护一个单调栈，来实现返回最小值的操作。
下面介绍如何维护单调栈：

当我们向栈中压入一个数时，如果该数 ≤ 单调栈的栈顶元素，则将该数同时压入单调栈中；否则，不压入，这是由于栈具有先进后出性质，所以在该数被弹出之前，栈中一直存在一个数比该数小，所以该数一定不会被当做最小数输出。
当我们从栈中弹出一个数时，如果该数等于单调栈的栈顶元素，则同时将单调栈的栈顶元素弹出。
单调栈由于其具有单调性，所以它的栈顶元素，就是当前栈中的最小数。
时间复杂度
四种操作都只有常数次入栈出栈操作，所以时间复杂度都是 O(1).

https://www.acwing.com/solution/acwing/content/749/
* */

import java.util.Stack;

public class 栈和队列_包含min函数的栈 {
    Stack<Integer> stk = new Stack<>();
    Stack<Integer> min_stk = new Stack<>();

    public void push(int node) {
        if(min_stk.isEmpty()||min_stk.peek()>=node)
            min_stk.push(node);
        stk.push(node);
    }

    public void pop() {
        if(min_stk.peek() == stk.pop())
            min_stk.pop();
    }

    public int top() {
        return stk.peek();
    }

    public int min() {
        return min_stk.peek();
    }
}
