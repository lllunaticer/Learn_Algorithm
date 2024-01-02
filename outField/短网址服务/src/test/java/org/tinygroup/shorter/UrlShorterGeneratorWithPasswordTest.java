package 短网址服务.src.test.java.org.tinygroup.shorter;

import org.junit.Test;
import org.tinygroup.shorter.storage.ShorterStorageMemory;
import org.tinygroup.shorter.shorter.ShorterWithPassword;
import org.tinygroup.shorter.generator.StringGeneratorRandom;
import org.tinygroup.shorter.generator.UrlShorterGeneratorWithPassword;

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