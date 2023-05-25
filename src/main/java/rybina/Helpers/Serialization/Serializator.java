package rybina.Helpers.Serialization;

import java.io.*;
import java.lang.reflect.Array;

/**
 * The Serializator class provides methods for loading and saving serialized objects.
 */
public class Serializator {
    /**
     * Loads a serialized object from the specified file.
     *
     * @param fileName the name of the file containing the serialized object
     * @param tClass   the class of the object to be loaded
     * @param <T>      the type of the object to be loaded
     * @return the loaded object
     */
    public <T>  T loadObject(String fileName, Class<T> tClass) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        String filePath = getClass().getResource(fileName).getPath();

        Object object = null;
        try {
            fileInputStream = new FileInputStream(filePath);;
            if (fileInputStream.getChannel().size() != 0) {
                objectInputStream = new ObjectInputStream(fileInputStream);
                object = objectInputStream.readObject();
                objectInputStream.close();
                fileInputStream.close();
            }
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
        return (T) object;
    }

    /**
     * Loads an array of serialized objects from the specified file.
     *
     * @param fileName the name of the file containing the serialized objects
     * @param tClass   the class of the objects to be loaded
     * @param <T>      the type of the objects to be loaded
     * @return the loaded array of objects
     */
    public <T> T[] loadObjectArray(String fileName, Class<T> tClass) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        String filePath = getClass().getResource(fileName).getPath();

        Object[] objects = null;
        try {
            fileInputStream = new FileInputStream(filePath);
            if (fileInputStream.getChannel().size() != 0) {
                objectInputStream = new ObjectInputStream(fileInputStream);
                int objectCount = objectInputStream.readInt();
                objects = new Object[objectCount];
                for (int i = 0; i < objectCount; i++) {
                    objects[i] = objectInputStream.readObject();
                }
                objectInputStream.close();
                fileInputStream.close();
            }

        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
        return convertArray(objects, tClass);
    }

    /**
     * Saves an object as a serialized file.
     *
     * @param object   the object to be saved
     * @param fileName the name of the file to save the object
     */
    public void saveObject(Object object, String fileName) {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        String filePath = getClass().getResource(fileName).getPath();

        try {
            fileOutputStream = new FileOutputStream(filePath);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Saves an array of objects as a serialized file.
     *
     * @param objects  the array of objects to be saved
     * @param fileName the name of the file to save the objects
     */
    public void saveObjectsArray(Object[] objects, String fileName) {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        String filePath = getClass().getResource(fileName).getPath();

        try {
            fileOutputStream = new FileOutputStream(filePath);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeInt(objects.length);
            for (Object object : objects) {
                objectOutputStream.writeObject(object);
            }
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Saves an array of objects as a serialized file.
     *
     * @param objects  the array of objects which should be converted
     * @param targetType the name of class which we need to convert to
     */
    private static <T> T[] convertArray(Object[] objects, Class<T> targetType) {
        if (objects == null) return null;
        T[] convertedArray = (T[]) Array.newInstance(targetType, objects.length);
        for (int i = 0; i < objects.length; i++) {
            convertedArray[i] = targetType.cast(objects[i]);
        }
        return convertedArray;
    }
}
