package 短网址服务.src.main.java.org.tinygroup.shorter.shorter;

import 短网址服务.src.main.java.org.tinygroup.shorter.ShorterGetter;

/**
 * 存放短地址和超时时间
 * Created by luoguo on 2017/3/24.
 */
public class ShorterWithPeriod implements ShorterGetter<ShorterWithPeriod> {
    private String shorter;
    private long period;

    public ShorterWithPeriod() {
    }
    public ShorterWithPeriod(String shorter, long period) {
        setShorter(shorter);
        setPeriod(period);
    }

    public String getShorter() {
        return shorter;
    }

    public void setShorter(String shorter) {
        this.shorter = shorter;
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }

}
