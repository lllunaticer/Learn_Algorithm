import java.io.BufferedReader;
import java.io.InputStreamReader;

public class test {
    public static void main(String[] args) throws Exception{
//        String s1 = "100";
//        String s2 = "100";
//        System.out.print((s1 == s2) + ",");
//
//        String s3 = new String("100");
//        System.out.print((s2 == s3) + ",");
//
//        String s4 = new String("100");
//        System.out.print((s3 == s4) + ",");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int i = br.read();
        System.out.println(i);
    }
}
