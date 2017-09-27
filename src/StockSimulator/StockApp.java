package StockSimulator;

import StockSimulator.Controllers.TaskBarController;
import StockSimulator.Dictionary.StockNames;
import StockSimulator.Observers.AaplObserver;
import StockSimulator.Observers.GoogObserver;
import StockSimulator.Observers.IBMObserver;
import StockSimulator.Views.AAPLGraphView;
import StockSimulator.Views.AAPLTableView;
import StockSimulator.Views.GOOGTableView;
import StockSimulator.Views.IBMTableView;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.util.HashMap;
import java.util.Map;

import static javafx.fxml.FXMLLoader.load;

public class StockApp extends Application {
    private final VBox vBox = new VBox();
    @Override
    public void start(Stage primaryStage) throws Exception{
        IBMTableView ibmTableView = new IBMTableView();
        AAPLTableView aaplTableView = new AAPLTableView();
        GOOGTableView googTableView = new GOOGTableView();
        AAPLGraphView aaplGraphView = new AAPLGraphView();
        Scene scene = new Scene(vBox);
        primaryStage.setTitle("National Stock exchange");
        primaryStage.setWidth(1500);
        primaryStage.setHeight(1000);
        Map<String, Node> views = new HashMap<>();
        views.put(StockNames.IBM_NAME.getName(), ibmTableView.getParent());
        views.put(StockNames.AAPL_NAME.getName(), aaplTableView.getParent());
        views.put(StockNames.GOOG_NAME.getName(), googTableView.getParent());
        views.put("test", aaplGraphView.getLineChart());
        TaskBarController taskBarController = new TaskBarController(vBox, views);
        vBox.getChildren().addAll(taskBarController.getView(), ibmTableView.getParent());

        primaryStage.setScene(scene);
        StockGrabber stockGrabber = new StockGrabber();
        IBMObserver observer1 = new IBMObserver(stockGrabber, ibmTableView);
        AaplObserver observer2 = new AaplObserver(stockGrabber, aaplTableView, aaplGraphView);
        GoogObserver observer3 = new GoogObserver(stockGrabber, googTableView);

        GetTheStock ibmSimulater = new GetTheStock(20,stockGrabber, StockNames.IBM_NAME.getName());
        GetTheStock aaplSimulater = new GetTheStock(20,stockGrabber, StockNames.AAPL_NAME.getName());
        GetTheStock googSimulator = new GetTheStock(20, stockGrabber, StockNames.GOOG_NAME.getName());
        primaryStage.show();





    }


    public static void main(String[] args) {
        launch(args);
    }
}
