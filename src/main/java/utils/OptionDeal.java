package utils;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * 对args进行处理
 * @author bigduu
 */
public class OptionDeal {
    private static String[] ARGS;
    private static Map<String,String> ARG = new HashMap<>();


    public static void setArgs(String[] args) {
        ARGS = args;
    }

    /**
     * 获取args的值并且封装在一个map中方便调用
     * @return
     */
    public static Map<String,String> getOption(){
        //此处注意需要将地址设置为resource里面，如果使用包路径的话，文件会在target里面单元测试将无效
        String path = "src/main/resources/";
        for (int i = 0; i < ARGS.length;i++){
            switch (ARGS[i]){
                case "-n":ARG.put("-n", ARGS[i+1]); i++; break;
                case "-f":ARG.put("-f", path+ARGS[i+1]); i++; break;
                default:
            }
        }
        if (ARG.get("-f") == null){
            ARG.put("-f", path+"test.log");
        }
        if (ARG.get("-n") == null){
            ARG.put("-n", "50");
        }
        return ARG;
    }
}
