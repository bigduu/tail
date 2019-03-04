import utils.MyTail;
import utils.OptionDeal;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author bigduu
 */
public class Main {
    public static void main(String[] args) {
        //这里注意，如果运行环境方便修改传入参数则设置为false 如果不方便则保持默认
        Boolean canNotChangeTheArgs = true;
        if (canNotChangeTheArgs) {
            String[] testArgs = new String[4];
            testArgs[0] = "-n";
            testArgs[1] = "50";
            testArgs[2] = "-f";
            testArgs[3] = "test.log";
            OptionDeal.setArgs(testArgs);
        }else{
            OptionDeal.setArgs(args);
        }
        Map<String, String> option = OptionDeal.getOption();
        MyTail.monitoring(option, StandardCharsets.UTF_8);
    }
}
