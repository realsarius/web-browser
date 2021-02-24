import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.time.temporal.ValueRange;

public class BasicWebBrowser extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        webEngine.titleProperty().addListener((ObservableValue<? extends String> p,
                                               String oldTitle, String newTitle) -> {
            stage.setTitle(newTitle);
        });
        String homePageUrl = "https://google.com";

        MenuButton options = new WebOptionsMenu(webView);
        BrowserHistory historyComponent = new BrowserHistory(webView);
        NavigationBar navBar = new NavigationBar(webView, homePageUrl, true);
        navBar.getChildren().addAll(options, historyComponent);

        VBox root = new VBox(navBar, webView);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
