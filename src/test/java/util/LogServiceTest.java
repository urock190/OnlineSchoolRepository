package util;

import com.academy.util.Level;
import com.academy.util.Log;
import com.academy.util.LogService;
import org.junit.jupiter.api.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static com.academy.util.LogService.readLevelSetting;
import static com.academy.util.LogService.readLog;
import static org.junit.jupiter.api.Assertions.*;

class LogServiceTest {
    static ArrayList<String> logs;
    static String set;
    static final Path setting = Path.of("src/main/java/com/academy/util/setting.txt");
    static final Path log = Path.of("src/main/java/com/academy/util/Log.txt");

    @BeforeAll
    static void readAll(){
        if(Files.exists(setting)){
            try {
                set = Files.readString(setting);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (Files.exists(log)) {
            try {
                logs = (ArrayList<String>) Files.readAllLines(log);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @BeforeEach
    void init() {
        LogService.writeLevelSetting(Level.DEBUG);
        try {
            Files.deleteIfExists(log);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LogService.writeLog(new Log("Log Name", Level.DEBUG, "Test message"));
    }

    @Test
    void ContainsSpecifiedStringsIfLogIsWrittenToTheFile(){
        LogService.writeLog(new Log("New log", Level.INFO, "Message #2"));
        try {
            String myLogString = Files.readString(log);
            assertAll(() -> {assertNotNull(myLogString);
                assertAll("Contains strings: ",
                        () -> assertTrue(myLogString.contains("New log")),
                        () -> assertTrue(myLogString.contains("INFO")),
                        () -> assertTrue(myLogString.contains("Message #2"))
                     );
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void ReturnsSpecifiedMessageIfLogFileNotFound(){
        try {
            Files.delete(log);
        } catch (IOException e) {
            fail(e);
        }
        assertEquals("There are no logs in the file. No log file found.", readLog());
    }

    @Test
    void ContainsSpecifiedStringsOfLogFromInitMethodIfLogIsReadFromTheFile(){
        String initLog = readLog();
        assertAll(() -> {assertNotNull(initLog);
            assertAll("Contains strings: ",
                    () -> assertTrue(initLog.contains("Log Name")),
                    () -> assertTrue(initLog.contains("DEBUG")),
                    () -> assertTrue(initLog.contains("Test message"))
            );
        });
    }

    @Test
    void EqualsSpecifiedStringIfLevelSettingIsWrittenToTheFile(){
        LogService.writeLevelSetting(Level.WARNING);
        try {
            String myLevelSet = Files.readString(setting);
            assertAll(() -> {assertNotNull(myLevelSet);
                assertEquals("level = WARNING", myLevelSet);
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void LevelOFFIfSettingFileDoesntExist(){
        try {
            Files.delete(setting);
        } catch (IOException e) {
            fail(e);
        }
        assertEquals(Level.OFF, readLevelSetting());
    }

    @Test
    void LevelIsEqualToTheLevelFromInitMethodIfSettingIsReadFromTheFile(){
        assertEquals(Level.DEBUG, readLevelSetting());
    }

    @AfterEach
    void tearDown(){
        try {
            Files.deleteIfExists(log);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterAll
    static void writeAll(){
        try {
            if (!Files.exists(setting)) Files.createFile(setting);
            if (!Files.exists(log)) Files.createFile(log);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try(BufferedWriter settingBufWriter = Files.newBufferedWriter(setting);
            BufferedWriter logBufWriter = Files.newBufferedWriter(log)) {
            for (String logLine : logs) logBufWriter.write(logLine + "\n");
            settingBufWriter.write(set);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
