import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Test {

    private static String path ="src/main/resources/test.log";
    @org.junit.Test
    public void inputChar() {
        File file = new File(path);
        FileWriter writer = null;
        try {
            writer = new FileWriter(file,true);
            for (int i = 0;i<10;i++) {
                String out = "fasfjhskjfhskafhsruyweiqryiuqwrndsf"+System.currentTimeMillis()+"\n\r";
                writer.write(out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @org.junit.Test
    public void inputPunctuation(){
        File file = new File(path);
        FileWriter writer = null;
        try {
            writer = new FileWriter(file,true);
            for (int i = 0;i<10;i++) {
                String out = "*&^((^*^#%&%^!%@~#$~$#$^$^"+System.currentTimeMillis()+"\n\r";
                writer.write(out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }



}
