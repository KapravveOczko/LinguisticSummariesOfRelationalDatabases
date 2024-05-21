module LinguisticSummaries {
    requires com.fasterxml.jackson.databind;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    exports org.ksr.Gui;
    exports org.ksr.FuzzyLib.FuzzySet;

    opens org.ksr.Gui;
    opens org.ksr.DataController to com.fasterxml.jackson.databind;
}
