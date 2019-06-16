//Java default API controls
import java.util.InputMismatchException;
import javafx.stage.Stage;


public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    private static final Stage s = null;
    private static AlertList alert;
    
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        alert = new AlertList(s);

        if(e instanceof InputMismatchException)
            alert.IllegalMovement(e.getMessage());
    }
}