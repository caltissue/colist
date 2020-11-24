package com.colist;

import java.io.*;
import java.util.HashMap;

public class ReadWriter {

    private static String filepath = "/Users/cal/Desktop/CoList/app/Entries";

    public static void setFilepath(String path) {
        filepath = path;
    }

    public static void makeDirectory() {
        File file = new File(filepath);
        file.mkdir();
    }

    public static void saveEntry(String filename, HashMap<String, Object> contents) throws IOException
    {
        File file = new File(filepath, filename);
        file.createNewFile();

        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(contents);

        oos.close();
        fos.close();
    }

    public static HashMap<String, Object> getEntry(String filename) throws IOException, ClassNotFoundException {
        File file = new File(filepath, filename);
        file.createNewFile();

        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);

        HashMap<String, Object> contents = (HashMap<String, Object>) ois.readObject();

        ois.close();
        fis.close();
        return contents;
    }

    public static void updateFile(String filename, String update) throws IOException
    {
        File file = new File(filepath, filename);
        file.createNewFile();

        FileOutputStream fos = new FileOutputStream(file, true);
        fos.write(update.getBytes());
        fos.close();
    }

    public static String readFile(String filename) throws IOException {
        File readFile = new File(filepath, filename);
        FileInputStream fis = new FileInputStream(readFile);
        byte[] fileBytes = new byte[(int)readFile.length()];
        try {
            fis.read(fileBytes);
        }
        catch (Exception e) {
            // TODO
        }
        finally {
            fis.close();
        }

        return new String(fileBytes);
    }

    public static File[] fileList() throws IOException {
        File dir = new File(filepath);
        dir.createNewFile();

        File[] files = dir.listFiles();
        if (files == null) files = new File[] {};
        return files;
    }

    public static void deleteFile(String filename) {
        File d = new File(filepath, filename);
        d.delete();
    }
}
