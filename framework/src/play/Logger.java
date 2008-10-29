package play;

import org.apache.log4j.Priority;
import play.exceptions.PlayException;

/**
 * Main logger of the application.
 * Free to use from the aplication code.
 */
public class Logger {

    /**
     * The application logger (play).
     */
    public static org.apache.log4j.Logger log4j = org.apache.log4j.Logger.getLogger("play");

    /**
     * Log with TRACE level
     * @param message The message pattern
     * @param args Pattern arguments
     */
    public static void trace(String message, Object... args) {
        log4j.trace(String.format(message, args));
    }

    /**
     * Log with DEBUG level
     * @param message The message pattern
     * @param args Pattern arguments
     */
    public static void debug(String message, Object... args) {
        log4j.debug(String.format(message, args));
    }

    /**
     * Log with DEBUG level
     * @param e the exception to log
     * @param message The message pattern
     * @param args Pattern arguments
     */
    public static void debug(Throwable e, String message, Object... args) {
        if(!niceThrowable(Priority.DEBUG, e, message, args)) log4j.debug(String.format(message, args), e);
    }

    /**
     * Log with INFO level
     * @param message The message pattern
     * @param args Pattern arguments
     */
    public static void info(String message, Object... args) {
        log4j.info(String.format(message, args));
    }

    /**
     * Log with INFO level
     * @param e the exception to log
     * @param message The message pattern
     * @param args Pattern arguments
     */
    public static void info(Throwable e, String message, Object... args) {
        if(!niceThrowable(Priority.INFO, e, message, args)) log4j.info(String.format(message, args), e);
    }

    /**
     * Log with WARN level
     * @param message The message pattern
     * @param args Pattern arguments
     */
    public static void warn(String message, Object... args) {
        log4j.warn(String.format(message, args));
    }

    /**
     * Log with WARN level
     * @param e the exception to log
     * @param message The message pattern
     * @param args Pattern arguments
     */
    public static void warn(Throwable e, String message, Object... args) {
        if(!niceThrowable(Priority.WARN, e, message, args)) log4j.warn(String.format(message, args), e);
    }

    /**
     * Log with ERROR level
     * @param message The message pattern
     * @param args Pattern arguments
     */
    public static void error(String message, Object... args) {
        log4j.error(String.format(message, args));
    }

    /**
     * Log with ERROR level
     * @param e the exception to log
     * @param message The message pattern
     * @param args Pattern arguments
     */
    public static void error(Throwable e, String message, Object... args) {
        if(!niceThrowable(Priority.ERROR, e, message, args)) log4j.error(String.format(message, args), e);
    }

    /**
     * Log with FATAL level
     * @param message The message pattern
     * @param args Pattern arguments
     */
    public static void fatal(String message, Object... args) {
        log4j.fatal(String.format(message, args));
    }

    /**
     * Log with FATAL level
     * @param e the exception to log
     * @param message The message pattern
     * @param args Pattern arguments
     */
    public static void fatal(Throwable e, String message, Object... args) {
        if(!niceThrowable(Priority.FATAL, e, message, args)) log4j.fatal(String.format(message, args), e);
    }
    
    
    // If e is a PlayException -> a very clean report
    static boolean niceThrowable(Priority priority, Throwable e, String message, Object... args) {
        if (e instanceof PlayException) {
            PlayException playException = (PlayException) e;
            log4j.log(priority, "");
            log4j.log(priority, "@" + playException.getId());
            log4j.log(priority, String.format(message, args));
            log4j.log(priority, "");
            if(playException.isSourceAvailable()) {
                log4j.log(priority, playException.getErrorTitle() + " (In " + playException.getSourceFile() + " around line " + playException.getLineNumber() +")");
            
            } else {
                log4j.log(priority, playException.getErrorTitle());            
            }
            log4j.log(priority, playException.getErrorDescription().replace("<strong>", "").replace("</strong>", "").replace("\n", " "));
            log4j.log(priority, null, e);
            log4j.log(priority, "");
            return true;
        }
        return false;
    }
}
