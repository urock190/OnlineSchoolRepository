package com.academy.util;

import java.io.IOException;
import java.nio.file.*;

public class LevelWatcher implements Runnable {
    private static final Logger LOGGER = new Logger(LevelWatcher.class.getName());
    public void watcher() throws InterruptedException, IOException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path path = Paths.get("src/main/java/com/academy/util/");
        path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

        WatchKey key;
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                if ((event.context()).toString().equals("setting.txt")) {
                    Logger.setLevelValue(LogService.readLevelSetting().getValue());
                }
            }
            key.reset();
        }
        watchService.close();
    }

    @Override
    public void run() {
        LOGGER.debug("Running level watcher.");
        try {
            watcher();
        } catch (IOException | InterruptedException e) {
            LOGGER.warning("Incorrect termination of the method is possible. " +
                    "Exiting level watcher due to interrupted exception.", e);
            //do nothing, just log
        } LOGGER.debug("Exiting level watcher.");
    }
}
