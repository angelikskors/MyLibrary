package sample;

import java.util.ArrayList;

/**
 * Created by PC Kors on 06.05.2015.
 */
public class Book extends ArrayList<Book> {
    private static String divider = ";";
    private String author;
    private String name;
    private int year;

    public Book(String author, String name, int year) {

        setAuthor(author);
        setName(name);
        setYear(year);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public static Book format(String string) {
        String[] fields = string.split(divider);


        if (fields.length < 3) {
            return null;
        }

        String author = fields[0].trim();
        String name = fields[1].trim();
        int year = Integer.parseInt(fields[2].trim());

        return new Book(author, name, year);
    }

    @Override
    public String toString() {
        return getAuthor() + "; " + getName() + "; " + +getYear() + "; ";
    }
}
