package controller.model;

import controller.model.cryptor.Cryptor;
import controller.model.datafilehandler.DataFileHandler;
import controller.model.datafilehandler.data.Data;
import java.util.List;

public final class Model 
{
    private final DataFileHandler dataFileHandler = new DataFileHandler();
    private final Cryptor cryptor;

    public Model(final String passwordParameter)
    {
        this.cryptor = new Cryptor(passwordParameter);
    }

    public final boolean isPasswordValid()
    {
        final List<Data> encryptedData = this.dataFileHandler.getData();
        if (encryptedData.isEmpty())
        {
            return true;
        }
        else
        {
            if (this.cryptor.decrypt(encryptedData.get(0).name) == null)
            {
                return false;
            }
            else
            {
                return true;
            }
        }
    }

    public final Data[] getData()
    {
        final List<Data> data = this.dataFileHandler.getData();
        final Data[] newData = new Data[data.size()];
        for (int i = 0; i < data.size(); i++)
        {
            newData[i] = this.decryptData(data.get(i));
        }
        return newData;
    }

    public final int addDataAndGetId()
    {
        final List<Data> data = this.dataFileHandler.getData();
        final int id;
        if (data.isEmpty())
        {
            id = 0;
        }
        else
        {
            id = data.get(data.size() - 1).id + 1;
        }
        data.add(this.encryptData(new Data(id, "", "", "")));
        this.dataFileHandler.setData(data);
        return id;
    }

    public final void deleteData(final int idParameter)
    {
        final List<Data> data = this.dataFileHandler.getData();
        for (int i = 0; i < data.size(); i++) 
        {
            if (data.get(i).id == idParameter)
            {
                data.remove(i);
                break;
            }
        }
        this.dataFileHandler.setData(data);
    }

    public final void editData(final Data dataParameter)
    {
        final List<Data> data = this.dataFileHandler.getData();
        for (int i = 0; i < data.size(); i++) 
        {
            if (data.get(i).id == dataParameter.id)
            {
                data.set(i, this.encryptData(dataParameter));
                break;
            }
        }
        this.dataFileHandler.setData(data);
    }
 
    private final Data encryptData(final Data dataParameter)
    {
        return new Data(dataParameter.id, this.cryptor.encrypt(dataParameter.name), this.cryptor.encrypt(dataParameter.login), this.cryptor.encrypt(dataParameter.password));
    }

    private final Data decryptData(final Data dataParameter)
    {
        return new Data(dataParameter.id, this.cryptor.decrypt(dataParameter.name), this.cryptor.decrypt(dataParameter.login), this.cryptor.decrypt(dataParameter.password));
    }
}
