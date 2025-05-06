package com.example.telefonbuch;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HelloApplication extends Application {
    private final Path FILENAME = Paths.get("numbers.json");
    private final Path FILENAME2 = Paths.get("numbers2.json");

    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        TelefonBook telefonBook = new TelefonBook();
        telefonBook.loadFromFile(FILENAME);
        TelefonBook telefonBook2 = new TelefonBook();
        telefonBook2.loadFromFile(FILENAME2);

        BorderPane tba1 = createTelefonbookArea(telefonBook);
        BorderPane tba2 = createTelefonbookArea(telefonBook2);

        BorderPane root = new BorderPane();
        root.setLeft(tba1);
        root.setCenter(tba2);

        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e -> {
            telefonBook.safeToFile(FILENAME);
            telefonBook2.safeToFile(FILENAME2);
        });
    }

    private BorderPane createTelefonbookArea(TelefonBook telefonBook) {
        BorderPane root = new BorderPane();

        EntryArea entryArea = new EntryArea(telefonBook.getItems());

        DeleteArea deleteArea = new DeleteArea(
                () -> telefonBook.removeAll( entryArea.getSelectedEntries() ),
                () -> telefonBook.addItem(new TelefonEntry("neuer Eintrag", "neuer Eintrag", "neue Nr."))
        );
        FilterArea filterArea = new FilterArea(telefonBook::filter);

        root.setBottom(deleteArea.getPane());
        root.setCenter(entryArea.getAnchorPane());
        root.setTop(filterArea.getPane());
        return root;
    }

    public static void main(String[] args) {
        launch();
    }
}
