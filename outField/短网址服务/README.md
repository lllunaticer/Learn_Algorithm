#urlshorter

首先我要说，开源托管，必须得 @红薯 家的。

上一次本人写过一篇博客《长URL转短连接的简单设计与实现》，由于写得比较仓促，是缺少设计的，因此方案也是不完整的，看到大家非常有热情，阅读的阅读收藏的收藏，我就深深的为写了这么不够深入的博客而感到不安，于是就有了这一篇博客，以及背后的开源代码。

确实，这次花费时间比较多，大概有大半天的时间设计并写代码。

#需求

首先说明一下这次的主要关注点：

1. 文本可以满足多种场景下的短链接生成需求
2. 文本可以满足多重序列号机制
3. 文本可以满足多种短链接生成方式
4. 自由&可扩展性--秉承一贯的设计原则，觉得框架实现的好就用，觉得不满足就替换之
#主要接口说明

##字符串生成接口

```
package org.tinygroup.shorter;

/**
 * 随机字符串发生器
 * Created by luoguo on 2017/3/24.
 */
public interface StringGenerator {
    String generate(String url);

    void setLength(int length);
}
```
setLength用于指定生成的长度，generate用于生成对应长度的短链接字符串。

上片文章里面被人喷说生成逻辑不够好，这次好了，随便你写你喜欢的。

当然，为了满足懒人，也有一个默认的实现，大致就是把上次的算法挪下来了

```
/**
 * 随机字符串发生器的默认实现类
 * <p>
 * Created by luoguo on 2017/3/24.
 */
public class StringGeneratorRandom implements StringGenerator {
    public static char[] VALID_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
    private static Random random = new Random(System.currentTimeMillis());
    private int length = 4;

    public StringGeneratorRandom() {
    }

    public StringGeneratorRandom(int length) {
        setLength(length);
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    String generate(int seed) {
        char[] sortUrl = new char[length];
        for (int i = 0; i < length; i++) {
            sortUrl[i] = VALID_CHARS[seed % VALID_CHARS.length];
            seed = random.nextInt(Integer.MAX_VALUE) % VALID_CHARS.length;
        }
        return new String(sortUrl);
    }

    /**
     * 这里的实现不考虑url，直接生成随机字符串即可，这个算法如果容量比较大的时候，性能会变低，因此要根据使用情况选择合适的长度
     *
     * @param url
     * @return
     */
    public String generate(String url) {
        String shortUrl;
        shortUrl = generate(random.nextInt(Integer.MAX_VALUE));
        return shortUrl;
    }

}
```
算法非常简单，就不详加说明了。

##短地址发生器接口

```
/**
 *
 * <p>
 * Created by luoguo on 2017/3/24.
 */
public interface UrlShorterGenerator<T extends ShorterGetter> {


    /**
     * 产生一个短链接对象
     *
     * @param url
     * @return
     */
    T generate(String url);

}
```
这里只有一个方法，就是根据长URL来生成一个短地址。有的同学可能要问，短地址不是只有一个字符串么，为什么这里居然有个T，它还是继承了ShorterGetter接口，ShorterGetter接口又是什么鬼？

这就要看需求里面的一句话了『可以满足多种场景下的短链接生成需求』，因为实际应用场景里不仅仅是只生成一个串，还有要生成带密码的，访问次数限制的，可用时长限制的等等方式。

因此这里就设计了一个接口ShorterGetter

```
/**
 * 用来获取短地址
 * Created by luoguo on 2017/3/24.
 */
public interface ShorterGetter<T> {
    String getShorter();
}
```
它只要求返回一个字符串的短链接，至于其他有什么东西，就不管了。

多种短地址使用场景支持

在工程里，我有5种方式

###仅短地址：

```
/**
 * 返回短码和密码
 * Created by luoguo on 2017/3/24.
 */
public class ShorterString implements ShorterGetter<String> {
    private String shorter;

    public ShorterString() {
    }

    public ShorterString(String shorter) {
        setShorter(shorter);
    }

    public String getShorter() {
        return shorter;
    }

    public void setShorter(String shorter) {
        this.shorter = shorter;
    }
}
```
###带密码的短链接

```
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
```
###带可用时间的短链接

```
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
```
带超时时间和使用次数的短链接，时间到了或次数用完了都不可以再被使用

```
/**
 * 用来存储短地址，超时时间和访问次数
 * Created by luoguo on 2017/3/24.
 */
public class ShorterWithPeriodAndTimes implements ShorterGetter<ShorterWithPeriodAndTimes> {
    private String shorter;
    private long period;
    private long times;
    public ShorterWithPeriodAndTimes() {
    }
    public ShorterWithPeriodAndTimes(String shorter, long period, long times) {
        setShorter(shorter);
        setTimes(times);
        setPeriod(period);
    }

    public long getTimes() {
        return times;
    }

    public void setTimes(long times) {
        this.times = times;
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
```
当然，只要你有需要，也可以增加其他场景的实现类

##短地址存储接口

当然，生成了短地址总要落地的，否则后面没有办法使用。

```
/**
 * 用来存储字符串短地址
 * Created by luoguo on 2017/3/24.
 */
public interface ShorterStorage<T extends ShorterGetter> {

    String get(String shorter);

    void clean(String url);

    void cleanShorter(String shorter);

    void save(String url, T shorter);

    void clean();

}
```
上面定义了获取、保存、清理等相关接口方法。

同样的，我也提供了一个用于测设和验证的内存存储方法

```
/**
 * Created by luoguo on 2017/3/24.
 */
public class ShorterStorageMemory<T extends ShorterGetter> implements ShorterStorage<T> {
    /**
     * 存储shorter,url
     */
    Map<ShorterGetter, String> shorterMap = new ConcurrentHashMap<ShorterGetter, String>();
    /**
     * 存储url,shorter
     */
    Map<String, ShorterGetter> urlMap = new ConcurrentHashMap<String, ShorterGetter>();
    /**
     * 存储shorter.shorter,shorter
     */
    Map<String, ShorterGetter> shorterUrlMap = new ConcurrentHashMap<String, ShorterGetter>();

    public String get(String shorterKey) {
        ShorterGetter shorter = shorterUrlMap.get(shorterKey);
        if (shorter != null) {
            return shorterMap.get(shorter);
        }
        return null;
    }

    public void clean(String url) {
        ShorterGetter shorter = urlMap.get(url);
        if (shorter != null) {
            urlMap.remove(url);
            shorterMap.remove(shorter);
            shorterUrlMap.remove(shorter.getShorter());
        }
    }

    public void cleanShorter(String shorterKey) {
        ShorterGetter shorter = shorterUrlMap.get(shorterKey);
        if (shorter != null) {
            urlMap.remove(shorterMap.get(shorter));
            shorterMap.remove(shorter);
            shorterUrlMap.remove(shorter.getShorter());
        }

    }

    public void save(String url, T shorter) {
        urlMap.put(url, shorter);
        shorterMap.put(shorter, url);
        shorterUrlMap.put(shorter.getShorter(), shorter);
    }

    public void clean() {
        shorterMap.clear();
        shorterUrlMap.clear();
        urlMap.clear();
    }
}
```
需要的，你也可以实现自己的数据库类型的、REDIS，或者你想要的其他方式。

至此，已经把主要的问题都已经解决，然后再看看发生器的实现类

注意：这里没有对限制条件进行控制，实际实现要进行控制，比如get的时候检查是否有可用性，如果没有可用性，就返回null。

发生器的实现类

##简单短链接

```
/**
 * 用于生成指定长度的串
 * Created by luoguo on 2017/3/24.
 */
public class UrlShorterGeneratorSimple implements UrlShorterGenerator<ShorterString> {

    private StringGenerator generator;
    private ShorterStorage<ShorterString> shorterStorage;

    public ShorterStorage<ShorterString> getShorterStorage() {
        return shorterStorage;
    }

    public void setShorterStorage(ShorterStorage<ShorterString> shorterStorage) {
        this.shorterStorage = shorterStorage;
    }

    public StringGenerator getGenerator() {
        return generator;
    }

    public void setGenerator(StringGenerator generator) {
        this.generator = generator;
    }

    public ShorterString generate(String url) {
        String shorter = generator.generate(url);
        while (shorterStorage.get(shorter) != null) {
            shorter = generator.generate(url);
        }
        ShorterString newShorter = new ShorterString(shorter);
        shorterStorage.save(url, newShorter);
        return newShorter;
    }
}
```
##带密码的

```
/**
 * 用于生成指定长度的串
 * Created by luoguo on 2017/3/24.
 */
public class UrlShorterGeneratorWithPassword implements UrlShorterGenerator<ShorterWithPassword> {

    private StringGenerator shorterGenerator;
    private StringGenerator passwordGenerator;
    private ShorterStorage<ShorterWithPassword> shorterStorage;

    public StringGenerator getShorterGenerator() {
        return shorterGenerator;
    }

    public void setShorterGenerator(StringGenerator shorterGenerator) {
        this.shorterGenerator = shorterGenerator;
    }

    public StringGenerator getPasswordGenerator() {
        return passwordGenerator;
    }

    public void setPasswordGenerator(StringGenerator passwordGenerator) {
        this.passwordGenerator = passwordGenerator;
    }

    public ShorterStorage<ShorterWithPassword> getShorterStorage() {
        return shorterStorage;
    }

    public void setShorterStorage(ShorterStorage<ShorterWithPassword> shorterStorage) {
        this.shorterStorage = shorterStorage;
    }


    public ShorterWithPassword generate(String url) {
        String shorter = shorterGenerator.generate(url);
        while (shorterStorage.get(shorter) != null) {
            shorter = shorterGenerator.generate(url);
        }
        ShorterWithPassword shorterWithPassword = new ShorterWithPassword(shorter, passwordGenerator.generate(url));
        shorterStorage.save(url, shorterWithPassword);
        return shorterWithPassword;
    }


}
```
##带使用时长的
```
/**
 * 用于生成指定长度的串,限制访问次数
 * Created by luoguo on 2017/3/24.
 */
public class UrlShorterGeneratorLimitPeriod implements UrlShorterGenerator<ShorterWithPeriod> {

    private StringGenerator generator;
    private ShorterStorage<ShorterWithPeriod> shorterStorage;
    /**
     * 有效时长，单位秒
     */
    private long period;

    public StringGenerator getGenerator() {
        return generator;
    }

    public void setGenerator(StringGenerator generator) {
        this.generator = generator;
    }

    public ShorterStorage<ShorterWithPeriod> getShorterStorage() {
        return shorterStorage;
    }

    public void setShorterStorage(ShorterStorage<ShorterWithPeriod> shorterStorage) {
        this.shorterStorage = shorterStorage;
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }


    public ShorterWithPeriod generate(String url) {
        String shorter = generator.generate(url);
        while (shorterStorage.get(shorter) != null) {
            shorter = generator.generate(url);
        }
        ShorterWithPeriod shorterWithPeriod = new ShorterWithPeriod(shorter, period);
        shorterStorage.save(url, shorterWithPeriod);
        return shorterWithPeriod;
    }

}
```
##带使用次数的
```
/**
 * 用于生成指定长度的串,限制访问次数
 * Created by luoguo on 2017/3/24.
 */
public class UrlShorterGeneratorLimitTimes implements UrlShorterGenerator<ShorterWithTimes> {

    private StringGenerator generator;
    private ShorterStorage<ShorterWithTimes> shorterStorage;
    /**
     * 有效时长，单位秒
     */
    private long times;

    public StringGenerator getGenerator() {
        return generator;
    }

    public void setGenerator(StringGenerator generator) {
        this.generator = generator;
    }

    public ShorterStorage<ShorterWithTimes> getShorterStorage() {
        return shorterStorage;
    }

    public void setShorterStorage(ShorterStorage<ShorterWithTimes> shorterStorage) {
        this.shorterStorage = shorterStorage;
    }

    public long getTimes() {
        return times;
    }

    public void setTimes(long times) {
        this.times = times;
    }


    public ShorterWithTimes generate(String url) {
        String shorter = generator.generate(url);
        while (shorterStorage.get(shorter) != null) {
            shorter = generator.generate(url);
        }
        ShorterWithTimes shorterWithPeriod = new ShorterWithTimes(shorter, times);
        shorterStorage.save(url, shorterWithPeriod);
        return shorterWithPeriod;
    }

}
```
##带使用次数和时间限制的
```
/**
 * 用于生成指定长度的串,限制访问次数
 * Created by luoguo on 2017/3/24.
 */
public class UrlShorterGeneratorLimitPeriodAndTimes implements UrlShorterGenerator<ShorterWithPeriodAndTimes> {
    private StringGenerator generator;
    private ShorterStorage<ShorterWithPeriodAndTimes> shorterStorage;
    /**
     * 有效时长，单位秒
     */
    private long period;
    /**
     * 最多使用次数
     */
    private long times;

    public StringGenerator getGenerator() {
        return generator;
    }

    public void setGenerator(StringGenerator generator) {
        this.generator = generator;
    }

    public ShorterStorage<ShorterWithPeriodAndTimes> getShorterStorage() {
        return shorterStorage;
    }

    public void setShorterStorage(ShorterStorage<ShorterWithPeriodAndTimes> shorterStorage) {
        this.shorterStorage = shorterStorage;
    }

    public long getTimes() {
        return times;
    }

    public void setTimes(long times) {
        this.times = times;
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }

    public ShorterWithPeriodAndTimes generate(String url) {
        String shorter = generator.generate(url);
        while (shorterStorage.get(shorter) != null) {
            shorter = generator.generate(url);
        }
        ShorterWithPeriodAndTimes shorterWithPeriodAndTimes = new ShorterWithPeriodAndTimes(shorter, period, times);
        shorterStorage.save(url, shorterWithPeriodAndTimes);
        return shorterWithPeriodAndTimes;
    }

}
```
至此，主体都已经完成，看看示例吧

#示例

##固定长度的短地址生成
```
/**
 * Created by luoguo on 2017/3/24.
 */
public class UrlShorterGeneratorSimpleTest {
    @Test
    public void generate() throws Exception {
        for (int i = 4; i <= 8; i++) {
            UrlShorterGeneratorSimple simple = new UrlShorterGeneratorSimple();
            simple.setGenerator(new StringGeneratorRandom(i));
            simple.setShorterStorage(new ShorterStorageMemory<ShorterString>());
            for (int j = 0; j < 5; j++) {
                String shorter = simple.generate("").getShorter();
                assert shorter.length()==i;
                System.out.println(shorter);
            }
        }
    }
}
```
生成的结果
```
zoHU
PcYv
0Lde
rsK2
zyTo
3sivy
jhZa5
02ir2
Pueqo
L4TlI
wXAYQB
2caquM
rZ8pCn
FhocFi
QZHroK
bxTPWCW
38gUCX3
2Ma4fQr
KCgvofA
NZNMK3Y
Jj7xkUjY
FP3XObRf
YBrrI8C8
I91HvRNs
VITEfp0T
```
##带密码的
```
/**
 * Created by luoguo on 2017/3/25.
 */
public class UrlShorterGeneratorWithPasswordTest {
    @Test
    public void generate() throws Exception {
        for (int i = 4; i <= 8; i++) {
            UrlShorterGeneratorWithPassword withPassword = new UrlShorterGeneratorWithPassword();
            withPassword.setShorterGenerator(new StringGeneratorRandom(i));
            withPassword.setPasswordGenerator(new StringGeneratorRandom(4));
            withPassword.setShorterStorage(new ShorterStorageMemory<ShorterWithPassword>());
            for (int j = 0; j < 5; j++) {
                ShorterWithPassword shorter = withPassword.generate("");
                assert shorter.getShorter().length()==i;
                System.out.printf("%s %s\n",shorter.getShorter(),shorter.getPassword());
            }
        }
    }
}
```
运行结果
```
0yET AYOf
37w1 MBjA
SDMg B72n
BdTv KAwd
KQ1w iwiP
mZAVV u8Zx
rdUlH 5a7T
uZQ5i j38x
PUfY0 kfH3
MG3iW bkHO
Ea4TJr Nt8v
2fycK1 6eF3
Q6arED rEID
wc9yf1 kcGr
uGs5uu vKhA
upsmJXt 1IIl
6feAOFV Afqm
j0qPXCG R9VN
2We0RqM 9722
SdgG0Yy tS6e
ZDUyOeeg kiTh
3RGlJuSp OQfl
EswJLPlk Jqjx
IgeQMtU7 91GP
9LWNni4z xPw8
```
呵呵，效果和我想象的一样。

总结

这一版本相对于上一版有了长足进步，设计更合理，功能更强大，自由度也更高。相信会给这方面有需求的小伙伴有一定触发。

由于编写时间太短，里面BUG和不足或设计缺陷一定难以避免，欢迎小伙伴门提交PR，共同完善。

git地址https://git.oschina.net/tinyframework/urlshorter.git

更多精彩博客，请移步前往悠然博客空间，对悠然动态感兴趣的同学们请关注。

认为有用或有价值，也欢迎打赏哦~~~