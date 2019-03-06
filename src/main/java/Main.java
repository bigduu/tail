import utils.MyFileWatcher;
import utils.MyTail;
import utils.OptionDeal;

import java.io.IOException;
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
            testArgs[1] = "10";
            testArgs[2] = "-f";
            testArgs[3] = "test.log";
            OptionDeal.setArgs(testArgs);
        }else{
            OptionDeal.setArgs(args);
        }
        Map<String, String> option = OptionDeal.getOption();
        MyTail.init(option, StandardCharsets.UTF_8);
        //第一读取 以后都用做watcher监听文件
        MyTail.read();
        try {
            MyFileWatcher myFileWatcher = new MyFileWatcher("src/main/resources/","test.log");
            myFileWatcher.start();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
