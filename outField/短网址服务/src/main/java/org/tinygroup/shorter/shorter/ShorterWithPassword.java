package 短网址服务.src.main.java.org.tinygroup.shorter.shorter;

import 短网址服务.src.main.java.org.tinygroup.shorter.ShorterGetter;

/**
 * 存放短地址和密码
 * Created by luoguo on 2017/3/24.
 */
public class ShorterWithPassword implements ShorterGetter<ShorterWithPassword> {
    private String shorter;
    private String password;

    public ShorterWithPassword() {
    }

    public ShorterWithPassword(String shorter, String password) {
        setShorter(shorter);
        setPassword(password);
    }

    public String getShorter() {
        return shorter;
    }

    public void setShorter(String shorter) {
        this.shorter = shorter;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
