import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import util.UILoader;

import java.io.IOException;

public class AppInitializer extends Application {

    public AnchorPane mainContext;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("views/mainPage.fxml"))));
        primaryStage.setTitle("my test app");
        primaryStage.show();
    }

    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        UILoader.SetUi("myPage", mainContext);
    }

    public void shutDownOnAction(ActionEvent actionEvent) {
        System.exit(0);
    }


    public void animatedAlertOnAction(ActionEvent actionEvent) {
        Notifications notificationBuilder = Notifications.create().title(" Successfully.!")
                .text(" Completed Successfully.")
                .graphic(new ImageView(new Image("/assets/check.png")))
                .hideAfter(Duration.seconds(6))
                .position(Pos.BASELINE_CENTER);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }
}
