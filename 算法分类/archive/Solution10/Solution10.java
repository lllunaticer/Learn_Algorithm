package archive.Solution10;

//未实现 .* 条件，其他模式可以匹配
public class Solution10 {
    public boolean isMatch(String s, String p) {
        boolean result;
        if(s.length()==0||p.length()==0){
            if(s.length()==0&&p.length()==0){
                result = true;
            }else result = false;
            return result;
        }else {
            int ip = 0;
            int is = 0;
            while(ip<p.length()&&is<s.length()){
                if(s.charAt(is)==p.charAt(ip)||p.charAt(ip)=='.'){
                    ip++;
                    is++;
                }else{
                    if(p.charAt(ip)=='*'){
                        if(p.charAt(ip-1)=='.'){//*之前是.,也即出现了.*组合

                        }else{//*之前是a-z的普通字符的情况
                            while (s.charAt(is) == s.charAt(is - 1)) {//这里需要保证*不出现在p串的开头，也就是*之前
                                is++;                           // 一定要有其他字符，防止is-1是无效索引的情况
                                if (is >= s.length())
                                    break;
                            }
                            ip++;//如果s串指针移动到aaab中的b了，则p串a*b需要移动到*后面
                        }
                    }else{//如果p串此时是普通字符a-z,则需要检查下一个字符是否为*
                        if(ip+1>=p.length()){//如果没有下一个字符，则判定为错
//                            result = false;
                            break;
                        }
                        if(p.charAt(ip+1)=='*'){//如果*是下一个字符
                            if(ip+2>=p.length()){//如果*是p串的最后一个字符，则s串的当前字符无法被匹配上，应该返回false
//                                result =false;
                                break;
                            }
                            ip =ip+2;//s：aaabbcd, p:aaac*b 当p串移动到c时不相等，p串的下一步是*,则直接将p移动到*之后，而s串不动
                        }else {
//                            result = false;
                            break;
                        }
                    }
                }
            }
            if(ip==p.length()&&is==s.length()){
                result = true;
            }else {
                if(ip+1==p.length()&&p.charAt(ip)=='*'){//此处是为了处理s=aa, b=aa*这样的情况的
                    result = true;
                }else{
                    result =false;
                }
            }
        }
    return result;
    }

    public static void main(String[] args) {
        Solution10 a = new Solution10();
        System.out.println(a.isMatch("aa","a*c"));
    }
}
//还要考虑特殊情况.*