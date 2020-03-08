package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {

    TextArea customerIntensity;
    TextArea serverSpeed;
    Button start;

    @Override
    public void start(Stage primaryStage) throws Exception{
        StackPane root = new StackPane();
        root.setAlignment(Pos.CENTER);

        customerIntensity = new TextArea();
        serverSpeed = new TextArea();
        start = new Button("Start");
        start.setMinSize(50,40);
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
               // CustomerQueue customerQueue = new CustomerQueue(customerIntensity, serverSpeed);
               // customerQueue.work();
            }
        });


        StackPane.setMargin(customerIntensity, new Insets(850, 900, 150, 100));
        StackPane.setMargin(serverSpeed, new Insets(850, 800, 150, 200));
        StackPane.setMargin(start, new Insets(850, 700, 150, 300));




        root.getChildren().addAll( start, serverSpeed, customerIntensity);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("First Application");
        primaryStage.setWidth(1000);
        primaryStage.setHeight(1000);
        primaryStage.show();
    }


    public static void main(String[] args) {
        //launch(args);
        QueueModel queueModel = new QueueModel(100,1000);
        queueModel.work();
    }
}
