package rybina.Helpers.Serialization;

import java.io.*;
import java.lang.reflect.Array;

public class Serializator {
    public static <T>  T loadObject(String fileName, Class<T> tClass) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        Object object = null;
        try {
            fileInputStream = new FileInputStream(fileName);
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

    public static <T> T[] loadObjectArray(String fileName, Class<T> tClass) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        Object[] objects = null;
        try {
            fileInputStream = new FileInputStream(fileName);
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

    public static void saveObject(Object object, String fileName) {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(fileName);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveObjectsArray(Object[] objects, String fileName) {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(fileName);
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


    private static <T> T[] convertArray(Object[] objects, Class<T> targetType) {
        if (objects == null) return null;
        T[] convertedArray = (T[]) Array.newInstance(targetType, objects.length);
        for (int i = 0; i < objects.length; i++) {
            convertedArray[i] = targetType.cast(objects[i]);
        }
        return convertedArray;
    }
}
