package controller.view;

import controller.view.datapanel.DataPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

public final class View 
{
    private final JPanel dataPanelsContainer = new JPanel();

    public final void createWindow(final ActionListener onAddDataButtonClickedActionListenerParameter)
    {
        final JFrame frame = new JFrame("Password manager");
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

            final JMenuBar menuBar = new JMenuBar();
            menuBar.setLayout(new GridLayout());
            frame.setJMenuBar(menuBar);

                menuBar.add(new JLabel("Service"));

                menuBar.add(new JLabel("Login"));

                menuBar.add(new JLabel("Password"));

                final JButton addDataButton = new JButton("Add");
                addDataButton.addActionListener(onAddDataButtonClickedActionListenerParameter);
                menuBar.add(addDataButton);

            final JScrollPane scrollPane = new JScrollPane();
            frame.add(scrollPane);

                this.dataPanelsContainer.setLayout(new BoxLayout(this.dataPanelsContainer, BoxLayout.Y_AXIS));
                this.dataPanelsContainer.setBackground(Color.WHITE);
                scrollPane.setViewportView(this.dataPanelsContainer);
    }

    public final String askUserForPassword()
    {
        JPasswordField passwordField = new JPasswordField(64);
        int option = JOptionPane.showConfirmDialog(null, passwordField, "Enter Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.OK_OPTION) 
        {
            return String.valueOf(passwordField.getPassword());
        }
        else
        {
            return null;
        }
    }

    public final void showInvalidPasswordError()
    {
        JOptionPane.showMessageDialog(null, "Access denied", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public final void addDataPanel(final DataPanel dataPanelParameter)
    {
        this.dataPanelsContainer.add(dataPanelParameter);
        this.dataPanelsContainer.revalidate();
        this.dataPanelsContainer.repaint();
    }
}
