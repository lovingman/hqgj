import com.huacainfo.ace.plus.mybatis.MapperXmlAdapter;
import org.dom4j.DocumentException;
import org.junit.Test;

import java.io.IOException;

/**
 * @ClassName MyUnitTest
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/4/18 11:28
 */
public class MyUnitTest {


    @Test
    public void test() {
        try {
            MapperXmlAdapter.generate("generatorConfig.xml", "relation_social_volunteer");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

}
