/*请实现一个函数，将一个字符串中的每个空格替换成“%20”。
例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。*/
/*
* 先遍历一次字符串，统计字符串中空格的总数，由此计算出替换之后的字符串的总长度。
* 每替换一个空格，长度增加2，因此替换以后字符串的长度等于原来的长度加上2乘以空格数目。
* 从字符串的后面开始复制和替换。首先准备两个指针：P1和P2。P1指向原始字符串的末尾，
* 而P2指向替换之后的字符串的末尾，接下来我们向前移动指针P1，逐个把它指向的字符复制
* 到P2指向的位置，碰到空格就替换。*/
public class 字符串_替换空格 {
    public static String replaceSpace(StringBuffer str) {
        int size = str.length();
        for (int i = 0; i < size; i++) {
            if (str.charAt(i) == 32) {
                str.replace(i, i + 1, "%20");
                i = i + 2;
                size = size + 2;
            }
        }
        return str.toString();
    }

    public static void main(String[] args) {
        System.out.println(字符串_替换空格.replaceSpace(new StringBuffer("   I love you ")));
    }
}
