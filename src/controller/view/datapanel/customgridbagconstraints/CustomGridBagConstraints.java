package controller.view.datapanel.customgridbagconstraints;

import java.awt.GridBagConstraints;

public final class CustomGridBagConstraints extends GridBagConstraints
{
    public CustomGridBagConstraints(final int positionParameter)
    {
        super();
        this.gridx = positionParameter;
        this.weightx = 1;
        this.fill = GridBagConstraints.HORIZONTAL;
    }
}
