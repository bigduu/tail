package utils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Map;

import static java.nio.file.StandardWatchEventKinds.*;

public class MyFileWatcher {

    private WatchService watcher;
    private String fileName;

    public MyFileWatcher(String directory,String fileName) throws IOException {
        this.fileName = fileName;
        this.watcher = FileSystems.getDefault().newWatchService();
        Path path = Paths.get(directory);
        path.register(watcher, ENTRY_CREATE, ENTRY_MODIFY);
    }

    public void start() throws InterruptedException {

        while (true) {
            WatchKey key = watcher.take();
            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();
                //事件可能lost or discarded
                if (kind == OVERFLOW) {
                    continue;
                }

                Path fileNameFromEvent = (Path) event.context();

                if (kind.name().equals("ENTRY_MODIFY") && this.fileName.equals(fileNameFromEvent.toString())){
                    System.out.printf("Event %s has happened,which fileName is %s%n", kind.name(), fileName);
                    MyTail.read();
                }

            }
            //这行必须有，不然不能连续地监控目录
            if (!key.reset()) {
                break;
            }
        }
    }

}
