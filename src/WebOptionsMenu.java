import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.*;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.web.WebView;

import static javafx.scene.text.FontSmoothingType.GRAY;
import static javafx.scene.text.FontSmoothingType.LCD;

public class WebOptionsMenu extends MenuButton {

    public WebOptionsMenu(WebView webView){
        this.setText("Options");

        CheckMenuItem ctxMenu = new CheckMenuItem("Enable Context Menu");
        ctxMenu.setSelected(true);
        webView.contextMenuEnabledProperty().bind(ctxMenu.selectedProperty());

        Menu scalingMenu = new Menu("Font Scale");
        scalingMenu.textProperty().bind(
                new SimpleStringProperty("Font Scale ")
                .concat(webView.fontScaleProperty().multiply(100.0))
                .concat("%"));
        MenuItem normalFontMenu = new MenuItem("Normal");
        MenuItem biggerFontMenu = new MenuItem("10% Bigger");
        MenuItem smallerFontMenu = new MenuItem("10% Smaller");
        normalFontMenu.setOnAction(e -> webView.setFontScale(1.0));
        biggerFontMenu.setOnAction(
                e -> webView.setFontScale(webView.getFontScale() + 0.10));
        smallerFontMenu.setOnAction(
                e -> webView.setFontScale(webView.getFontScale() - 0.10));
        scalingMenu.getItems().addAll(normalFontMenu, biggerFontMenu, smallerFontMenu);

        Menu smoothingMenu = new Menu("Font Smoothing");
        RadioMenuItem grayMenu = new RadioMenuItem("GRAY");
        grayMenu.setSelected(true);
        RadioMenuItem lcdMenu = new RadioMenuItem("LCD");
        grayMenu.setOnAction(e -> webView.setFontSmoothingType(GRAY));
        lcdMenu.setOnAction(e -> webView.setFontSmoothingType(LCD));
        new ToggleGroup().getToggles().addAll(lcdMenu, grayMenu);
        smoothingMenu.getItems().addAll(grayMenu, lcdMenu);

        Menu zoomMenu = new Menu("Zoom");
        zoomMenu.textProperty().bind(
                new SimpleStringProperty("Zoom ")
                .concat(webView.zoomProperty().multiply(100.0))
                .concat("%"));

        MenuItem normalZoomMenu = new MenuItem("Normal");
        MenuItem biggerZoomMenu = new MenuItem("10% Bigger");
        MenuItem smallerZoomMenu = new MenuItem("10% Smaller");
        normalZoomMenu.setOnAction(e -> webView.setZoom(1.0));
        biggerZoomMenu.setOnAction(e -> webView.setZoom(webView.getZoom() + 0.10));
        smallerZoomMenu.setOnAction(e -> webView.setZoom(webView.getZoom() - 0.10));
        zoomMenu.getItems().addAll(normalZoomMenu, biggerZoomMenu, smallerZoomMenu);

        CheckMenuItem scriptMenu = new CheckMenuItem("Enable JavaScript");
        scriptMenu.setSelected(true);
        webView.getEngine().javaScriptEnabledProperty()
                .bind(scriptMenu.selectedProperty());

        this.getItems().addAll(ctxMenu, scalingMenu,
                smoothingMenu, zoomMenu, new SeparatorMenuItem(), scriptMenu);

    }
}
