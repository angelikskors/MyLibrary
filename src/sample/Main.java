package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Main extends Application {
    private int widthStage = 800;
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
    private static ChoiceBox<Book> cb;
    private Image image;
    private static ChoiceBox choicebox;
    private static Button choiceEdit;
    private static TextField textInfo;


    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("My Library");


        Group root = new Group();

        File f = new File("style.css");
        root.getStylesheets().add(f.toURI().toString());
        System.out.println(f.exists());


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
                closeEdit(root);
                authorLabel = new Label();
                authorLabel.setText("Author's name");
                authorLabel.setLayoutX(200);
                authorLabel.setLayoutY(150);
                root.getChildren().add(authorLabel);
                textFieldName = new TextField();
                textFieldName.setMaxSize(100, 100);
                textFieldName.setLayoutX(200);
                textFieldName.setLayoutY(170);
                root.getChildren().add(textFieldName);
                nameLabel = new Label();
                nameLabel.setText("Name of the book");
                nameLabel.setLayoutX(320);
                nameLabel.setLayoutY(150);
                root.getChildren().add(nameLabel);
                textFieldInfo = new TextField();
                textFieldInfo.setMaxSize(100, 100);
                textFieldInfo.setLayoutX(320);
                textFieldInfo.setLayoutY(170);
                root.getChildren().add(textFieldInfo);
                yearLabel = new Label();
                yearLabel.setText("Year");
                yearLabel.setLayoutX(440);
                yearLabel.setLayoutY(150);
                root.getChildren().add(yearLabel);
                yearTextField = new TextField();
                yearTextField.setMaxSize(100, 100);
                yearTextField.setLayoutX(440);
                yearTextField.setLayoutY(170);
                root.getChildren().add(yearTextField);
                buttonSave = new Button();

                buttonSave.setMinSize(50, 20);
                buttonSave.setText("Save");
                buttonSave.setLayoutX(600);
                buttonSave.setLayoutY(150);
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
                closeCreation(root);
                closeDelete(root);
                cb = new ChoiceBox<Book>();
                labelDeleteChoice = new Label();
                labelDeleteChoice.setText("Choose of the given Books to edit");
                labelDeleteChoice.setFont(new Font("Arial", 15));
                labelDeleteChoice.setLayoutX(250);
                labelDeleteChoice.setLayoutY(150);
                root.getChildren().add(labelDeleteChoice);
                try {
                    cb.setItems(FXCollections.observableArrayList(
                                    FileHelper.getNames(pathFile))
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                }
                cb.setMinSize(130, 50);
                cb.setLayoutX(280);
                cb.setLayoutY(170);
                cb.setTooltip(new Tooltip("Select the book"));
                root.getChildren().add(cb);
                closeButton(root, "edit");
                choiceEdit = new Button();
                choiceEdit.setMinSize(50, 20);
                choiceEdit.setText("make Edit");
                choiceEdit.setLayoutX(600);
                choiceEdit.setLayoutY(150);
                root.getChildren().add(choiceEdit);


                choiceEdit.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        textInfo = new TextField();

                        textInfo.setText(String.valueOf(cb.getSelectionModel().getSelectedItem()));
                        textInfo.setEditable(true);
                        textInfo.setLayoutX(280);
                        textInfo.setLayoutY(240);
                        root.getChildren().add(textInfo);
                        buttonSave = new Button();
                        buttonSave.setText(" save edit");
                        buttonSave.setLayoutX(280);
                        buttonSave.setLayoutY(280);
                        buttonSave.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                //    try {
                                System.out.println(textInfo.getText());


                                // FileHelper.writeIntoFile(pathFile,Book.format(textInfo.getText()));
                                //   } catch (IOException e) {
                                //   e.printStackTrace();
                            }
                            // }
                        });
                        root.getChildren().add(buttonSave);

                    }
                });


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
                closeEdit(root);

                closeCreation(root);

                cb = new ChoiceBox<Book>();
                labelDeleteChoice = new Label();
                labelDeleteChoice.setText("Choose of the given Books to delete");
                labelDeleteChoice.setFont(new Font("Arial", 15));
                labelDeleteChoice.setLayoutX(250);
                labelDeleteChoice.setLayoutY(150);
                root.getChildren().add(labelDeleteChoice);
                try {
                    cb.setItems(FXCollections.observableArrayList(
                                    FileHelper.getNames(pathFile))
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                }
                cb.setMinSize(130, 50);
                cb.setLayoutX(280);
                cb.setLayoutY(170);
                cb.setTooltip(new Tooltip("Select the book"));

                root.getChildren().add(cb);

                buttonSave.setMinSize(50, 20);
                buttonSave.setText("Delete");
                buttonSave.setLayoutX(600);
                buttonSave.setLayoutY(150);
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

                        label.setFont(new Font("Arial", 17));
                        buttonSave.setTooltip(new Tooltip(" The file is deleted "));
                    }


                });
            }
        });
        root.getChildren().add(buttonDelete);


        Scene scene = new Scene(root, widthStage, hightStage);
        Paint paint = new Color(0, 0, 0, 0.3);
        scene.setFill(paint);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private static void closeButton(Group root, String function) {
        buttonClose = new Button();
        buttonClose.setMinSize(50, 20);
        buttonClose.setText("Close");
        buttonClose.setLayoutX(600);
        buttonClose.setLayoutY(200);
        root.getChildren().add(buttonClose);
        buttonClose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (function.equals("creation")) {
                    closeDelete(root);
                    closeCreation(root);
                    closeEdit(root);
                } else if (function.equals(("delete"))) {
                    closeCreation(root);
                    closeDelete(root);
                    closeEdit(root);
                }
                if (function.equals("edit")) {
                    closeEdit(root);
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
        root.getChildren().remove(textInfo);
    }

    private static void closeDelete(Group root) {
        root.getChildren().remove(labelDeleteChoice);
        root.getChildren().remove(cb);
        root.getChildren().remove(buttonSave);
        root.getChildren().remove(buttonClose);
        root.getChildren().remove(textInfo);


    }

    private static void closeEdit(Group root) {
        root.getChildren().remove(labelDeleteChoice);
        root.getChildren().remove(cb);
        root.getChildren().remove(choiceEdit);
        root.getChildren().remove(buttonClose);
        root.getChildren().remove(textInfo);


    }


}
