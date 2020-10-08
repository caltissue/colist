package com.colist;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReadWriter {
    public static void writeSave(Context context, String filename, String contents) throws IOException {
        writeSave(context, filename, contents, false);
    }

    public static void writeSave(Context context, String filename, String contents, boolean append) throws IOException {
        File saveFile = new File(context.getFilesDir(), filename);
        saveFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(saveFile, append);
        try{
            fos.write(contents.getBytes());
        }
        catch (Exception e) {
            // TODO: handle
        }
        finally {
            fos.close();
        }
    }

    public static String readLoad(Context context, String filename) throws IOException {
        File readFile = new File(context.getFilesDir(), filename);
        FileInputStream fis = new FileInputStream(readFile);
        byte[] fileBytes = new byte[(int)readFile.length()];
        try {
            fis.read(fileBytes);
        }
        catch (Exception e) {
            // TODO: uhh
        }
        finally {
            fis.close();
        }

        return new String(fileBytes);
    }

    public static File[] fileList(Context context) {
        File dir = new File(context.getFilesDir(), "");
        return dir.listFiles();
    }

    public static void deleteFile(String filename, Context context) {
        File deleteFile = new File(context.getFilesDir(), filename);
        deleteFile.delete();
    }
}
