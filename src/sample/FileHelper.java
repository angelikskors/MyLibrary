package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class FileHelper {
    static String divider = ";";


    public static void writeIntoFile(String pathFile, String author, String name, String year) {

        File file = new File(pathFile);
        checkExistenceFile(file);

        try {


            FileWriter writer = new FileWriter(file, false);
            BufferedWriter df = new BufferedWriter(writer);
            df.newLine();
            df.write(author + divider);


            df.write(name + divider);

            df.write(year + divider);

            df.flush();
            df.close();

        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    public static ArrayList<Book> getNames(String pathFile) throws IOException {
        File file = new File(pathFile);
        if (file.exists()) {
            FileReader reader = new FileReader(file);
            BufferedReader bf = new BufferedReader(reader);
            ArrayList<Book> books = new ArrayList<Book>();

            String str = null;
            while ((str = bf.readLine()) != null) {
                Book newBook = Book.format(str);
                if (newBook != null) {
                    books.add(newBook);
                }
            }

            bf.close();


            return books;

        }

        return null;
    }


    public static ArrayList<Book> deleteBook(String name, String pathFile) throws IOException {

        ArrayList<Book> books = getNames(pathFile);
        for (Book list : books) {
            for (int i = 0; i < books.size(); i++) {
                if (name.equals(list.toString())) {
                    books.remove(list);
                    System.out.println("Deleted");
                    return books;
                } else System.out.println("No matches found");
            }
        }

        return null;
    }

    public static void writeIntoFile(String pathFile, ArrayList<Book> books) throws IOException {
        File file = new File(pathFile);

        FileWriter writer = new FileWriter(file, false);
        BufferedWriter df = new BufferedWriter(writer);

        for (Book list : books) {

            df.write(list.toString());
            df.newLine();
        }

        df.flush();

        df.close();

    }

    public static void writeIntoFile(String pathFile, Book book) throws IOException {
        File file = new File(pathFile);

        FileWriter writer = new FileWriter(file, false);
        BufferedWriter df = new BufferedWriter(writer);


        df.write(book.toString());
        df.newLine();


        df.flush();

        df.close();

    }


    public static void checkExistenceFile(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
