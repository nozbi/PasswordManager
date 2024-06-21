package controller.model.datafilehandler.data;

import java.io.Serializable;

public final class Data implements Serializable
{
    public final int id;
    public final String name;
    public final String login;
    public final String password;

    public Data(final int idParameter, final String nameParameter, final String loginParameter, final String passwordParameter)
    {
        this.id = idParameter;
        this.name = nameParameter;
        this.login = loginParameter;
        this.password = passwordParameter;
    }
}
