package com.example.telefonbuch;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class DeleteArea {
    private final AnchorPane anchorPane = new AnchorPane();
    private final Button deleteButton = new Button("Löschen");
    private final Button createButton = new Button("Erstellen");

    public DeleteArea(OnDelete onDelete, OnCreate onCreate) {
        AnchorPane.setTopAnchor(deleteButton, 10.0);
        AnchorPane.setBottomAnchor(deleteButton, 10.0);
        AnchorPane.setRightAnchor(deleteButton, 10.0);

        AnchorPane.setTopAnchor(createButton, 10.0);
        AnchorPane.setBottomAnchor(createButton, 10.0);
        AnchorPane.setRightAnchor(createButton, 90.0);

        anchorPane.getChildren().addAll(deleteButton, createButton);

        deleteButton.onActionProperty().setValue(e -> onDelete.onDelete());
        createButton.onActionProperty().setValue(e -> onCreate.onCreate());

    }

    public Pane getPane() {
        return anchorPane;
    }
}
