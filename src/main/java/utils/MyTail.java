package utils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author bigduu
 */
public class MyTail {

    private static Long LIFETIME;

    private static File FILE;

    private static ArrayList<String> OUTPUT = new ArrayList<>();

    private static Charset CHARSET;

    private static Long LASTLINENUM;


    private static void outPrint() {
        for (int i = OUTPUT.size() - 1; i >= 0; i--) {
            System.out.println(OUTPUT.get(i));
        }
        OUTPUT.clear();
    }

    /**
     * 从后向前读
     */
    public static void read() {

        RandomAccessFile rf = null;
        try {
            rf = new RandomAccessFile(FILE , "r");
            //获取文件的长度 用于将指正指向文件尾部
            //待解决问题：如果文件过大，将无法指向文件的尾部
            long len = rf.length();
            if (len == 0L) {
                System.out.println();
                return;
            }
            //设置开始的指针位置
            long start = rf.getFilePointer();
            //设置游标指针
            long pos = start + len - 1;
            //用于接收一行的值
            String line;
            //用于读取游标指针所指向的字符
            int c;
            //用于判断结束循环的flag
            int breakPoint = 0;
            rf.seek(pos);
            //从文件末尾开始读
            while (pos > 0 && breakPoint < LASTLINENUM) {
                c = rf.read();
                if (c == '\n' || c == '\r') {
                    line = rf.readLine();
                    if (line != null && line.length() != 0) {
                        breakPoint++;
                        OUTPUT.add(new String(line.getBytes(StandardCharsets.ISO_8859_1) , CHARSET));
                    }
                }
                pos--;
                rf.seek(pos);
                // 当文件指针退至文件开始处，输出第一行
                if (pos == 0) {
                    System.out.println(new String(rf.readLine().getBytes(StandardCharsets.ISO_8859_1) , CHARSET));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            String message = e.getMessage();
            System.out.println(message);

        } finally {
            try {
                if (rf != null) {
                    rf.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //输出剩余的内容
        outPrint();
    }

    /**
     * 对文件进行监控
     */
    public static void init(Map<String, String> option , Charset charset) {
        CHARSET = charset;
        String path = option.get("-f");
        LASTLINENUM = Long.valueOf(option.get("-n"));
        File file = new File(path);
        if (file.exists()) {
            FILE = file;
        } else {
            System.out.println("文件不存在");
        }
    }
}
