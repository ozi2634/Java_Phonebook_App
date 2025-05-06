package com.example.telefonbuch;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.runtime.ObjectMethods;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TelefonBook {
    private ObservableList<TelefonEntry> items = FXCollections.observableArrayList();

    private FilteredList<TelefonEntry> filteredList = new FilteredList<>(items);

    public TelefonBook() {
//        items.add(new TelefonEntry( "Heidegger", "Phillip", "123"));
//        items.add(new TelefonEntry("Heidegger", "Cornelia", "456"));
//        items.add(new TelefonEntry("Heidegger","Paul", "123456"));
    }

    public ObservableList<TelefonEntry> getItems() {
        return this.filteredList;
    }

    public void filter(String str) {
        filteredList.setPredicate(tbe -> {
            return tbe.match(str);
        });

    }

    public void removeAll(List<TelefonEntry> entries) {
        this.items.removeAll(entries);
    }

    public void addItem(TelefonEntry telefonEntry) {
        this.items.add(telefonEntry);
    }

    public void loadFromFile(Path path) {
        try (InputStream is = Files.newInputStream(path)) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(is);
            // Verwenden Sie den ObjectMapper hier, um
            // die Daten aus der Datei auszulesen.
            if (root.isObject() && root.has("entries")) {
                JsonNode entries = root.get("entries");
                if (entries.isArray()) {
                    for (JsonNode e : entries) {
                        String lastName = e.get("lastName").asText();
                        String firstName = e.get("firstName").asText();
                        String number = e.get("number").asText();
                        items.add(new TelefonEntry(lastName, firstName, number));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void safeToFile(Path path) {
        JsonFactory factory = new JsonFactory () ;
        try ( OutputStream os = Files . newOutputStream ( path ) ;
              JsonGenerator jg = factory . createGenerator ( os ) ) {
            // Verwenden Sie jg um fuer jeden Eintrag im Telefonbuch
            // entsprechende Objekte im JSON zu erzeugen
            jg.writeStartObject();
            jg.writeFieldName("entries");
            jg.writeStartArray();
            for (TelefonEntry item : items) {
                jg.writeStartObject();
                jg.writeStringField("lastName", item.getLastName());
                jg.writeStringField("firstName", item.getFirstName());
                jg.writeStringField("number", item.getNumber());
                jg.writeEndObject();
            }
            jg.writeEndArray();
            jg.writeEndObject();
        } catch ( IOException e ) {
            e . printStackTrace () ;
        }
    }

}
