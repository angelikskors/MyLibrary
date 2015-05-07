package sample;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main extends Application {
    private int widthStage = 600;
    private int hightStage = 400;
    private Label label;
    private static Label labelDeleteChoice;
    private static Label authorLabel;
    private static Label nameLabel;
    private Button buttonCreate;
    private static Button buttonSave;
    private static TextField textFieldName;
    private static TextField textFieldInfo;
    private Label outInutLabel;
    private static Label yearLabel;
    private static TextField yearTextField;
    private String pathFile = "D:\\libraryBase.txt";
    private static Button buttonClose;
    private Button buttonEdit;
    private Button buttonDelete;
    private static ChoiceBox cb;
    private Image image;

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("My Library");


        Group root = new Group();

        File f = new File("style.css");
        root.getStylesheets().add("file:///" + f.getAbsolutePath());

        Label label = new Label("Welcome to Smart Library");
        label.getStyleClass().add("welcome-label");
        label.setLayoutX(130);
        label.setLayoutY(0);


        root.getChildren().add(label);


        DropShadow shadow = new DropShadow();
        buttonCreate = new Button();
        buttonCreate.getStyleClass().add("my-button");
        buttonCreate.setLayoutX(0);
        buttonCreate.setLayoutY(100);
        buttonCreate.setText("Create");
        buttonCreate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                closeDelete(root);
                authorLabel = new Label();
                authorLabel.setText("Author's name");
                authorLabel.setLayoutX(100);
                authorLabel.setLayoutY(100);
                root.getChildren().add(authorLabel);
                textFieldName = new TextField();
                textFieldName.setMaxSize(100, 100);
                textFieldName.setLayoutX(100);
                textFieldName.setLayoutY(120);
                root.getChildren().add(textFieldName);
                nameLabel = new Label();
                nameLabel.setText("Name of the book");
                nameLabel.setLayoutX(220);
                nameLabel.setLayoutY(100);
                root.getChildren().add(nameLabel);
                textFieldInfo = new TextField();
                textFieldInfo.setMaxSize(100, 100);
                textFieldInfo.setLayoutX(220);
                textFieldInfo.setLayoutY(120);
                root.getChildren().add(textFieldInfo);
                yearLabel = new Label();
                yearLabel.setText("Year");
                yearLabel.setLayoutX(340);
                yearLabel.setLayoutY(100);
                root.getChildren().add(yearLabel);
                yearTextField = new TextField();
                yearTextField.setMaxSize(100, 100);
                yearTextField.setLayoutX(340);
                yearTextField.setLayoutY(120);
                root.getChildren().add(yearTextField);
                buttonSave = new Button();
                buttonSave.setMinSize(50, 20);
                buttonSave.setText("Save");
                buttonSave.setLayoutX(500);
                buttonSave.setLayoutY(100);
                root.getChildren().add(buttonSave);
                buttonSave.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String author = textFieldName.getText();
                        String name = textFieldInfo.getText();
                        String year = yearTextField.getText();
                        if (year != null & name != null & author != null) {
                            if (Integer.parseInt(year) != 0) {
                                FileHelper.writeIntoFile(pathFile, author, name, year);
                            } else {

                            }
                            textFieldName.clear();
                            textFieldInfo.clear();
                            yearTextField.clear();

                        }
                    }
                });


                closeButton(root, "creation");

            }
        });
        root.getChildren().add(buttonCreate);
        buttonEdit = new Button();
        buttonEdit.getStyleClass().add("my-button");
        buttonEdit.setLayoutX(0);
        buttonEdit.setLayoutY(160);
        buttonEdit.setText("Edit");
        buttonEdit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
        root.getChildren().add(buttonEdit);

        buttonDelete = new Button();
        buttonDelete.getStyleClass().add("my-button");
        buttonDelete.setLayoutX(0);
        buttonDelete.setLayoutY(220);
        buttonDelete.setText("Delete");
        buttonDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                closeCreation(root);

                cb = new ChoiceBox();
                labelDeleteChoice = new Label();
                labelDeleteChoice.setText("Choose of the given Books to delete");
                labelDeleteChoice.setFont(new Font("Arial", 15));
                labelDeleteChoice.setLayoutX(150);
                labelDeleteChoice.setLayoutY(100);
                root.getChildren().add(labelDeleteChoice);
                try {
                    cb.setItems(FXCollections.observableArrayList(
                                    FileHelper.getNames(pathFile))
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                }
                cb.setMinSize(130, 50);
                cb.setLayoutX(180);
                cb.setLayoutY(120);
                cb.setTooltip(new Tooltip("Select the book"));

                root.getChildren().add(cb);
                buttonSave = new Button();
                buttonSave.setMinSize(50, 20);
                buttonSave.setText("Delete");
                buttonSave.setLayoutX(500);
                buttonSave.setLayoutY(100);
                root.getChildren().add(buttonSave);
                closeButton(root, "delete");
                buttonSave.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        try {
                            String book = String.valueOf(cb.getSelectionModel().getSelectedItem());
                            System.out.println(book);
                            FileHelper.writeIntoFile(pathFile, FileHelper.deleteBook(book, pathFile));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }


                });
            }
        });
        root.getChildren().add(buttonDelete);


        Scene scene = new Scene(root, widthStage, hightStage);
        Paint paint = new Color(0.5, 0, 0, 0.5);
        scene.setFill(paint);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private static void closeButton(Group root, String function) {
        buttonClose = new Button();
        buttonClose.setMinSize(50, 20);
        buttonClose.setText("Close");
        buttonClose.setLayoutX(500);
        buttonClose.setLayoutY(130);
        root.getChildren().add(buttonClose);
        buttonClose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (function.equals("creation")) {
                    closeDelete(root);
                    closeCreation(root);
                } else if (function.equals(("delete"))) {
                    closeCreation(root);
                    closeDelete(root);
                }


            }
        });
    }


    public static void main(String[] args) {
        Application.launch(args);

    }

    private static void closeCreation(Group root) {
        root.getChildren().remove(textFieldName);
        root.getChildren().remove(textFieldInfo);
        root.getChildren().remove(yearTextField);
        root.getChildren().remove(yearLabel);
        root.getChildren().remove(authorLabel);
        root.getChildren().remove(nameLabel);
        root.getChildren().remove(buttonSave);
        root.getChildren().remove(buttonClose);
    }

    private static void closeDelete(Group root) {
        root.getChildren().remove(labelDeleteChoice);
        root.getChildren().remove(cb);


    }


}
