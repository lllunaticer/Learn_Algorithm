package 短网址服务.src.test.java.org.tinygroup.shorter;

import 短网址服务.src.test.java.org.tinygroup.shorter.generator.StringGeneratorRandom;

import static 短网址服务.src.test.java.org.junit.Assert.assertEquals;

/**
 * Created by luoguo on 2017/3/24.
 */
public class RandomStringGeneratorTest {
    @org.junit.Test
    public void generate() throws Exception {
        StringGenerator generator4 = new StringGeneratorRandom(4);
        for (int i = 0; i < 100; i++) {
            assertEquals(4, generator4.generate(null).length());
        }
        StringGenerator generator6 = new StringGeneratorRandom(6);
        for (int i = 0; i < 100; i++) {
            assertEquals(6, generator6.generate(null).length());
        }
    }

}