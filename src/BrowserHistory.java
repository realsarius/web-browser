import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

public class BrowserHistory extends HBox {
    public BrowserHistory(WebView webView) {
        this.setSpacing(4);
        WebHistory history = webView.getEngine().getHistory();
        Button backBtn = new Button("Back");
        Button forwardBtn = new Button("Forward");
        backBtn.setDisable(true);
        forwardBtn.setDisable(true);

        backBtn.setOnAction(e -> history.go(-1));
        forwardBtn.setOnAction(e -> history.go(1));

        history.currentIndexProperty().addListener((p, oldValue, newValue) -> {
            int currentIndex = newValue.intValue();
            if (currentIndex <= 0) {
                backBtn.setDisable(true);
            } else {
                backBtn.setDisable(false);
            }

            if (currentIndex >= history.getEntries().size()) {
                forwardBtn.setDisable(true);
            } else {
                forwardBtn.setDisable(false);
            }
        });

        ComboBox<WebHistory.Entry> historyList = new ComboBox<>();
        historyList.setPrefWidth(150);
        historyList.setItems(history.getEntries());

        historyList.setCellFactory(entry -> {
            ListCell<WebHistory.Entry> cell = new ListCell<WebHistory.Entry>() {
                @Override
                public void updateItem(WebHistory.Entry item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        this.setText(null);
                        this.setGraphic(null);
                    } else {
                        String pageTitle = item.getTitle();
                        this.setText(pageTitle);
                    }
                }
            };
            return cell;
        });

        historyList.setOnAction(e -> {
            int currentIndex = history.getCurrentIndex();
            WebHistory.Entry selectedEntry = historyList.getValue();
            int selectedIndex = historyList.getItems().indexOf(selectedEntry);
            int offset = selectedIndex - currentIndex;
            history.go(offset);
        });

        this.getChildren().addAll(backBtn, forwardBtn, new Label("History:"),
        historyList);
    }
}