package controller.view.datapanel;

import controller.view.datapanel.customgridbagconstraints.CustomGridBagConstraints;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public final class DataPanel extends JPanel
{
    private final JTextField nameTextField = new JTextField();
    private final JTextField loginTextField = new JTextField();
    private final JTextField passwordTextField = new JTextField();
    private final JButton showDataButton = new JButton("Show");
    private final JButton hideDataButton = new JButton("Hide");
    private final JButton deleteDataButton = new JButton("Delete");
    private final JButton editDataButton = new JButton("Edit");
    private final JButton saveEditDataButton = new JButton("Save");
    private final JButton cancelEditDataButton = new JButton("Cancel");
    private final JButton copyLoginButton = new JButton("Copy login");
    private final JButton copyPasswordButton = new JButton("Copy password");
    private final ActionListener onDeleteDataButtonClickedActionListener;
    private final ActionListener onEditDataButtonClickedActionListener;
    private int id;
    private String name;
    private String login;
    private String password;

    public DataPanel(final int idParameter, final ActionListener onDeleteDataButtonClickedActionListenerParameter, final ActionListener onSaveEditDataButtonClickedActionListenerParameter)
    {
        this(idParameter, "", "", "", onDeleteDataButtonClickedActionListenerParameter, onSaveEditDataButtonClickedActionListenerParameter);
        this.onEditDataButtonClicked();
    }
    
    public DataPanel(final int idParameter, final String nameParameter, final String loginParameter, final String passwordParameter, final ActionListener onDeleteDataButtonClickedActionListenerParameter, final ActionListener onSaveEditDataButtonClickedActionListenerParameter)
    {
        super();
        this.onDeleteDataButtonClickedActionListener = onDeleteDataButtonClickedActionListenerParameter;
        this.onEditDataButtonClickedActionListener = onSaveEditDataButtonClickedActionListenerParameter;
        this.id = idParameter;
        this.name = nameParameter;
        this.login = loginParameter;
        this.password = passwordParameter;
        this.setLayout(new GridBagLayout());
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20)); 

        this.nameTextField.setDisabledTextColor(Color.BLACK);
        this.add(this.nameTextField, new CustomGridBagConstraints(0));

        this.loginTextField.setDisabledTextColor(Color.BLACK);
        this.add(this.loginTextField, new CustomGridBagConstraints(1));

        this.passwordTextField.setDisabledTextColor(Color.BLACK);
        this.add(this.passwordTextField, new CustomGridBagConstraints(2));

        final JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridBagLayout());
        this.add(buttonsPanel, new CustomGridBagConstraints(3));

            this.showDataButton.setMinimumSize(new Dimension(1, this.getMaximumSize().height));
            this.showDataButton.addActionListener(event -> this.onShowDataButtonClicked());
            buttonsPanel.add(this.showDataButton, new CustomGridBagConstraints(0));

            this.hideDataButton.setMinimumSize(new Dimension(1, this.getMaximumSize().height));
            this.hideDataButton.addActionListener(event -> this.onHideDataButtonClicked());
            buttonsPanel.add(this.hideDataButton, new CustomGridBagConstraints(1));

            this.editDataButton.setMinimumSize(new Dimension(1, this.getMaximumSize().height));
            this.editDataButton.addActionListener(event -> this.onEditDataButtonClicked());
            buttonsPanel.add(this.editDataButton, new CustomGridBagConstraints(2));

            this.deleteDataButton.setMinimumSize(new Dimension(1, this.getMaximumSize().height));
            this.deleteDataButton.addActionListener(event -> this.onDeleteDataButtonClicked());
            buttonsPanel.add(this.deleteDataButton, new CustomGridBagConstraints(3));

            this.cancelEditDataButton.setMinimumSize(new Dimension(1, this.getMaximumSize().height));
            this.cancelEditDataButton.addActionListener(event -> this.onCancelEditDataButtonClicked());
            buttonsPanel.add(this.cancelEditDataButton, new CustomGridBagConstraints(4));
            
            this.saveEditDataButton.setMinimumSize(new Dimension(1, this.getMaximumSize().height));
            this.saveEditDataButton.addActionListener(event -> this.onSaveEditDataButtonClicked());
            buttonsPanel.add(this.saveEditDataButton, new CustomGridBagConstraints(5));

            this.copyLoginButton.setMinimumSize(new Dimension(1, this.getMaximumSize().height));
            this.copyLoginButton.addActionListener(event -> this.onCopyLoginButtonClicked());
            buttonsPanel.add(this.copyLoginButton, new CustomGridBagConstraints(6));

            this.copyPasswordButton.setMinimumSize(new Dimension(1, this.getMaximumSize().height));
            this.copyPasswordButton.addActionListener(event -> this.onCopyPasswordButtonClicked());
            buttonsPanel.add(this.copyPasswordButton, new CustomGridBagConstraints(7));
    
        this.onCancelEditDataButtonClicked();
        this.onHideDataButtonClicked();
    }

    public final int getId()
    {
        return this.id;
    }

    public final String getName()
    {
        return this.name;
    }

    public final String getLogin()
    {
        return this.login;
    }

    public final String getPassword()
    {
        return this.password;
    }

    private final void hideAllButtons()
    {
        this.showDataButton.setVisible(false);
        this.hideDataButton.setVisible(false);
        this.deleteDataButton.setVisible(false);
        this.editDataButton.setVisible(false);
        this.saveEditDataButton.setVisible(false);
        this.cancelEditDataButton.setVisible(false);
        this.copyLoginButton.setVisible(false);
        this.copyPasswordButton.setVisible(false);
    }

    private final void onHideDataButtonClicked()
    {
        this.hideAllButtons();
        this.showDataButton.setVisible(true);
        this.copyLoginButton.setVisible(true);
        this.copyPasswordButton.setVisible(true);
        this.loginTextField.setText(null);
        this.passwordTextField.setText(null);
    }

    private final void onShowDataButtonClicked()
    {
        this.hideAllButtons();
        this.hideDataButton.setVisible(true);
        this.deleteDataButton.setVisible(true);
        this.editDataButton.setVisible(true);
        this.nameTextField.setText(this.name);
        this.loginTextField.setText(this.login);
        this.passwordTextField.setText(this.password);
    }

    private final void onDeleteDataButtonClicked()
    {
        this.onDeleteDataButtonClickedActionListener.actionPerformed(new ActionEvent(this, 0, null));
        final Container parent = this.getParent();
        parent.remove(this);
        parent.revalidate();
        parent.repaint();
    }

    private final void onEditDataButtonClicked()
    {
        this.hideAllButtons();
        this.saveEditDataButton.setVisible(true);
        this.cancelEditDataButton.setVisible(true);
        this.nameTextField.setEnabled(true);
        this.loginTextField.setEnabled(true);
        this.passwordTextField.setEnabled(true);
        this.nameTextField.setBackground(Color.WHITE);
        this.loginTextField.setBackground(Color.WHITE);
        this.passwordTextField.setBackground(Color.WHITE);
    }

    private final void onSaveEditDataButtonClicked()
    {
        this.name = this.nameTextField.getText();
        this.login = this.loginTextField.getText();
        this.password = this.passwordTextField.getText();
        this.onCancelEditDataButtonClicked();
        this.onEditDataButtonClickedActionListener.actionPerformed(new ActionEvent(this, 0, null));
    }

    private final void onCancelEditDataButtonClicked()
    {
        this.onShowDataButtonClicked();
        this.nameTextField.setEnabled(false);
        this.loginTextField.setEnabled(false);
        this.passwordTextField.setEnabled(false);
        this.nameTextField.setBackground(new Color(240, 240, 240));
        this.loginTextField.setBackground(new Color(240, 240, 240));
        this.passwordTextField.setBackground(new Color(240, 240, 240));
    }

    private final void onCopyLoginButtonClicked()
    {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(this.login), null);
    }

    private final void onCopyPasswordButtonClicked()
    {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(this.password), null);
    }
}
