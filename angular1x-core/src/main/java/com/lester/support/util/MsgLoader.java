package tw.com.platform.util;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class MsgLoader {
    public static String getMsgByNumber(String _sNumber) {

        Resource _oResource = new ClassPathResource("message.properties");
        Properties _oProperties = null;
        String _sMsg = "";
        try {
            _oProperties = PropertiesLoaderUtils.loadProperties(_oResource);
            _sMsg = _oProperties.getProperty("Msg" + _sNumber);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (_sMsg == null) _sMsg = "訊息代號錯誤。";
        return _sMsg;
    }
}
