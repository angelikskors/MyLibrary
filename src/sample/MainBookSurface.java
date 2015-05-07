package sample;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


public class MainBookSurface extends Group {

    private final Image image;

    public MainBookSurface() {
        image = new Image(getInputStreamForFile("book.jpg"));

        Label label = new Label();
        label.setText("Welcome to Smart Library");
        label.setGraphic(new ImageView(image));
        label.setLayoutX(130);
        label.setLayoutY(0);
        label.setFont(new Font("Arial", 30));
        label.setAlignment(Pos.CENTER);

    }


    private InputStream getInputStreamForFile(String path) {
        try {
            return new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
