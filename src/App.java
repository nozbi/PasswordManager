import javax.swing.SwingUtilities;

import controller.Controller;

public final class App 
{
    public static final void main(String[] argsParameter)
    {
        SwingUtilities.invokeLater(() -> {new Controller();});
    }
}
