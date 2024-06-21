package controller;

import controller.model.Model;
import controller.model.datafilehandler.data.Data;
import controller.view.View;
import controller.view.datapanel.DataPanel;
import java.awt.event.ActionEvent;

public final class Controller 
{
    private final Model model;
    private final View view = new View();

    public Controller()
    {
        final String password = this.view.askUserForPassword();
        if (password == null)
        {
            this.model = null;
            System.exit(0);
        }
        else
        {
            this.model = new Model(password);
            if (this.model.isPasswordValid())
            {
                this.view.createWindow(event -> this.onAddDataButtonClicked());
                for (Data data : this.model.getData()) 
                {
                    this.view.addDataPanel(new DataPanel(data.id, data.name, data.login, data.password, event -> this.onDeleteDataButtonClicked(event), event -> this.onEditDataButtonClicked(event)));
                }
            }
            else
            {
                this.view.showInvalidPasswordError();
                System.exit(0);
            }
        }
    }

    private final void onAddDataButtonClicked()
    {
        this.view.addDataPanel(new DataPanel(this.model.addDataAndGetId(), event -> this.onDeleteDataButtonClicked(event), event -> this.onEditDataButtonClicked(event)));
    }

    private final void onDeleteDataButtonClicked(final ActionEvent actionEventParameter)
    {
        this.model.deleteData(((DataPanel)actionEventParameter.getSource()).getId());
        
    }

    private final void onEditDataButtonClicked(final ActionEvent actionEventParameter)
    {
        final DataPanel dataPanel = (DataPanel)actionEventParameter.getSource();
        this.model.editData(new Data(dataPanel.getId(), dataPanel.getName(), dataPanel.getLogin(), dataPanel.getPassword()));
    }
}
