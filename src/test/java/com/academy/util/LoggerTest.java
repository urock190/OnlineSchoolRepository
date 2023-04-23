package java.com.academy.util;

import com.academy.util.Level;
import com.academy.util.Logger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoggerTest {
    private final Logger logger = new Logger("Test");

    @Test
    void EveryLevelIsLoggableIfLevelSettingIsDebug(){
        Logger.setLevelValue(Level.DEBUG.getValue());
        assertTrue(logger.isLoggable(Level.DEBUG));
        assertTrue(logger.isLoggable(Level.INFO));
        assertTrue(logger.isLoggable(Level.WARNING));
        assertTrue(logger.isLoggable(Level.ERROR));
    }

    @Test
    void NothingIsLoggableIfLevelSettingIsOff(){
        Logger.setLevelValue(Level.OFF.getValue());
        assertFalse(logger.isLoggable(Level.DEBUG));
        assertFalse(logger.isLoggable(Level.INFO));
        assertFalse(logger.isLoggable(Level.WARNING));
        assertFalse(logger.isLoggable(Level.ERROR));
    }

    @Test
    void OnlyErrorIsLoggableIfLevelSettingIsError(){
        Logger.setLevelValue(Level.ERROR.getValue());
        assertFalse(logger.isLoggable(Level.DEBUG));
        assertFalse(logger.isLoggable(Level.INFO));
        assertFalse(logger.isLoggable(Level.WARNING));
        assertTrue(logger.isLoggable(Level.ERROR));
    }

    @Test
    void ErrorAndWarningAreLoggableIfLevelSettingIsWarning(){
        Logger.setLevelValue(Level.WARNING.getValue());
        assertFalse(logger.isLoggable(Level.DEBUG));
        assertFalse(logger.isLoggable(Level.INFO));
        assertTrue(logger.isLoggable(Level.WARNING));
        assertTrue(logger.isLoggable(Level.ERROR));
    }

    @Test
    void OnlyDebugIsNotLoggableIfLevelSettingIsInfo(){
        Logger.setLevelValue(Level.INFO.getValue());
        assertFalse(logger.isLoggable(Level.DEBUG));
        assertTrue(logger.isLoggable(Level.INFO));
        assertTrue(logger.isLoggable(Level.WARNING));
        assertTrue(logger.isLoggable(Level.ERROR));
    }
}
