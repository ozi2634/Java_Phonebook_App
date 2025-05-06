package com.example.telefonbuch;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class FilterArea {
    private final AnchorPane anchorPane = new AnchorPane();
    private final Button filterButton = new Button("Filter");
    private final TextField filterText = new TextField();


    public FilterArea(OnFilter onFilter) {
        AnchorPane.setTopAnchor(filterButton, 10.0);
        AnchorPane.setBottomAnchor(filterButton, 10.0);
        AnchorPane.setRightAnchor(filterButton, 10.0);

        AnchorPane.setTopAnchor(filterText, 10.0);
        AnchorPane.setBottomAnchor(filterText, 10.0);
        AnchorPane.setRightAnchor(filterText, 70.0);
        AnchorPane.setLeftAnchor(filterText, 10.0);

        anchorPane.getChildren().addAll(filterButton, filterText);
        filterButton.onActionProperty().setValue( e -> {
            onFilter.filter(filterText.getText());
        });
    }

    public Node getPane() {
        return anchorPane;
    }

}