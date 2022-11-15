package com.company;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static Object saveGame(String nameAddress) {
// 1.Создать три экземпляра класса GameProgress
        GameProgress Player1 = new GameProgress(100, 10, 390, 230430);
        GameProgress Player2 = new GameProgress(80, 7, 150, 100340);
        GameProgress Player3 = new GameProgress(50, 4, 60, 50267);
        return null;
    }

    public static Object zipFiles(String nameAddress) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("C:/Games/savegames/zip.zip"));
             FileInputStream fis = new FileInputStream("C:/Games/savegames/save3.dat")) {
            // Создаем архивный объект и кладём его в архив
            ZipEntry entre = new ZipEntry("save3.dat");
            zout.putNextEntry(entre);
            // Считываем содержимове файла в массив byte
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            // Добавляем содержимое к архиву
            zout.write(buffer);
            // Закрываем текущую запись для новой записи
            zout.closeEntry();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        try (ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream("C:/Games/savegames/save3.dat"))) {
            saveGame("C:/Games/savegames/save3.dat");
// 2.Сохранил сериализованные объекты GameProgress в папку savegames из предыдущей задачи
            oss.writeObject(saveGame("C:/Games/savegames/save3.dat"));
        } catch (IOException e) {
            e.printStackTrace();
        }

// 3.Созданные файлы сохранений из папки savegames запаковать в архив zip
        zipFiles("C:/Games/savegames/zip.zip");

// 4.Использовал метод класса File, удалил файлы сохранений, не лежащие в архиве zip.
        File dir = new File("C:/Games/savegames/save3.dat");
        if (dir.delete())
            System.out.println("Каталог удален");
    }
}
