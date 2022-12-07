module kompo {
    requires javafx.controls;
    requires javafx.fxml;
    requires model;
    exports pl.lodz.p.it.kompo.view to javafx.graphics;
    opens pl.lodz.p.it.kompo.view to javafx.fxml;
}