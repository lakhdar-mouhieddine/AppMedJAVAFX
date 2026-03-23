import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class FirstPageController {

    @FXML
    private Label message;

    @FXML
    private Button sinscrire;

    @FXML
    void inscription(ActionEvent event) {
        message.setText("hello");
    }

}
