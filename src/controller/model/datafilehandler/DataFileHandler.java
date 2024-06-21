package controller.model.datafilehandler;

import controller.model.datafilehandler.data.Data;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

public final class DataFileHandler 
{
    private static final String PATH = "data.ser";

    @SuppressWarnings("unchecked")
    public final List<Data> getData()
    {
        try (final ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(DataFileHandler.PATH))) 
        {
            return (List<Data>)objectInputStream.readObject();
        } 
        catch (final Exception exception) 
        {
            return new LinkedList<>();
        }
    }

    public final void setData(final List<Data> dataParameter)
    {
        try (final ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(DataFileHandler.PATH))) 
        {
            objectOutputStream.writeObject(dataParameter);
        } 
        catch (final Exception exception) 
        {
            System.exit(0);
        }
    }
}
