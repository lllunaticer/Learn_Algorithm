package 短网址服务.src.test.java.org.tinygroup.shorter;

import org.junit.Test;
import 短网址服务.src.test.java.org.tinygroup.shorter.storage.ShorterStorageMemory;
import 短网址服务.src.test.java.org.tinygroup.shorter.shorter.ShorterString;
import 短网址服务.src.test.java.org.tinygroup.shorter.generator.StringGeneratorRandom;
import 短网址服务.src.test.java.org.tinygroup.shorter.generator.UrlShorterGeneratorSimple;

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