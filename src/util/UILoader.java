package util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class UILoader {
    public static void SetUi(String location, AnchorPane allLoaderContext) throws IOException {

        /**----------------------------------FULL CODE

         URL resource = getClass().getResource("../View/LoginForm.fxml");
         Parent load = FXMLLoader.load(resource);
         Stage window = (Stage) logoutPainContext.getScene().getWindow();
         window.setScene(new Scene(load));

         */
        allLoaderContext.getChildren().clear();
        allLoaderContext.getChildren().add(FXMLLoader.load(UILoader.class.getResource("../views/" + location + ".fxml")));
    }


}
