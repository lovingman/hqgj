import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.wxms.wechat.api.WeChatApi;
import com.huacainfo.ace.wxms.wechat.entity.pojo.base.Button;
import com.huacainfo.ace.wxms.wechat.entity.pojo.base.Menu;
import com.huacainfo.ace.wxms.wechat.util.HttpKit;
import org.junit.Test;

/**
 * @ClassName UnitTest
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/8/29 14:16
 */
public class UnitTest {
    /**
     * unit test method
     */
    @Test
    public void test() throws Exception {

        String accessToken = HttpKit.get("http://wx.cdwmsq1314.com/weChat/weChatAccount/findAccessToken.action").replace("\"", "");
        String menu = HttpKit.get("https://api.weixin.qq.com/cgi-bin/menu/get?access_token=" + accessToken);

        String menuBody = String.valueOf(JsonUtil.toMap(menu).get("menu"));
        System.out.println(menuBody);
        Menu m = JsonUtil.toObject(menuBody, Menu.class);
        Button[] bArray = m.getButton();
        for (int i = 0; i < bArray.length; i++) {
            if (i == 1) {
                Button bb = bArray[i];
                bb.setName("众创平安");
                bArray[i] = bb;
                System.out.println(JsonUtil.toJson(bArray[i]));
            }
        }

        System.out.println(m);

        System.out.println(WeChatApi.createMenu(accessToken, m));

    }
}
