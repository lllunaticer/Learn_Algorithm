/*
* 题目描述
用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
* */
import java.util.Stack;
public class 栈和队列_两个栈实现队列 {
    Stack<Integer> stackIn = new Stack<Integer>();
    Stack<Integer> stackOut = new Stack<Integer>();

    public void push(int node) {
        stackIn.push(node);
    }

    public int pop() {
        /*如果第二个栈是空的，要把第一个栈里面所有的数倒进第二个栈里面*/
        /*但第二个栈一定要是空的才能从第一个栈里倒数进来，并且要将第一个栈的数全部倒进来*/
        if(stackOut.empty()){
            while (!stackIn.empty())
                stackOut.push(stackIn.pop());
        }
        return stackOut.pop();
    }
}
