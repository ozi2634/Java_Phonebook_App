package com.example.telefonbuch;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class TelefonBookArea {

    private final BorderPane root = new BorderPane();
    private final EntryArea entryArea;

    public TelefonBookArea(TelefonBook telefonBook) {
        this.entryArea = new EntryArea(telefonBook.getItems());
        DeleteArea deleteArea = new DeleteArea(
                () -> telefonBook.removeAll(entryArea.getSelectedEntries()),
                () -> telefonBook.addItem(new TelefonEntry("neuer Eintrag","neuer Eintrag", "neuer Eintrag"))
        );
        FilterArea filterArea = new FilterArea(telefonBook::filter);
        root.setBottom(deleteArea.getPane());
        root.setCenter(entryArea.getAnchorPane());
        root.setTop(filterArea.getPane());
    }

    public Node getPane()
    {
        return root;
    }

    public ObservableList<TelefonEntry> getSelectedEntries() {
        return this.entryArea.getSelectedEntries();
    }
}
