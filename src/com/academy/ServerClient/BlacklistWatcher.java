package com.academy.ServerClient;

import com.academy.util.Logger;

import java.io.IOException;
import java.nio.file.*;

public class BlacklistWatcher implements Runnable{
    private static final Logger LOGGER = new Logger(BlacklistWatcher.class.getName());
    public void watchBlacklist() throws InterruptedException, IOException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path path = Paths.get("src/com/academy/ServerClient/");
        path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

        WatchKey key;
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                if ((event.context()).toString().equals("Blacklist.txt")) {
                    Server.setBlacklist(Service.readBlacklist());
                }
            }
            key.reset();
        }
        watchService.close();
    }

    @Override
    public void run() {
        LOGGER.debug("Running blacklist watcher.");
        try {
            watchBlacklist();
        } catch (IOException | InterruptedException e) {
            LOGGER.warning("Incorrect termination of the method is possible. " +
                    "Exiting blacklist watcher due to interrupted exception.", e);
            //do nothing, just log
        } LOGGER.debug("Exiting blacklist watcher.");
    }
}
