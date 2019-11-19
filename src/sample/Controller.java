package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class Controller {


    private PropArrayInt pai;
    private ChartModel cm;
    private IntegerProperty[] listener;

    private MergeSort task;
    private QuickSort task2;

    @FXML
    public AnchorPane apChart;
    @FXML
    public Label lblDelayOut;
    @FXML
    public Label lblBarsOut;
    @FXML
    public Button btnPrint;
    @FXML
    public Button btnRegenerate;
    @FXML
    public Button btnMergeSort;
    @FXML
    public Button btnQuicksort;
    @FXML
    public ProgressBar pbProgess;
    @FXML
    public Slider sldDelay;
    @FXML
    public Slider sldBars;

    @FXML
    public void initialize(){

        pai = Main.getPaiModel();
        cm = Main.getCmModel();
        listener = Main.getListener();

        task = Main.getMergeSort();
        task2 = Main.getQuickSort();

        sldBars.setMax(Main.getBarMax());
        sldBars.setMin(Main.getBarMin());
        sldBars.setMajorTickUnit(Main.getBarTic());

        sldDelay.setMax(Main.getDelayMax());
        sldDelay.setMin(Main.getDelayMin());
        sldDelay.setMajorTickUnit(Main.getDelayTic());


        sldBars.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldVal, Number newVal) {
                int iNewValue = (int) Math.floor(newVal.doubleValue());
                int iOldValue = (int)Math.floor(oldVal.doubleValue());
                lblBarsOut.setText(String.format("%d",iNewValue));
                pai.setArray(iNewValue);
            }
        });

        sldDelay.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldVal, Number newVal) {
                int iNewValue = (int) Math.floor(newVal.doubleValue());
               MergeSort.setDelay(iNewValue);
               QuickSort.setDelay(iNewValue);
               lblDelayOut.setText(String.format("%d",iNewValue));
            }
        });


       pai.getSizeValidValuesProperty().addListener((obs,old,nevv)->{

           apChart.getChildren().clear();
           cm = new ChartModel((int)nevv,pai.getValidArrayAsDouble());
           apChart.getChildren().addAll(cm.getVB());

       });

       btnMergeSort.setOnAction(event->{

            task.setParams(pai.arrayProperty(),pai.getIndexProperty(),pai.getValidSize()-1);
            Thread thread = new Thread(task);
            thread.setDaemon(true);
            thread.start();
       });

        btnPrint.setOnAction(event->{
            pai.printArray();
        });

        btnRegenerate.setOnAction(event ->{
            pai.setArray(pai.getValidSize()-1);
            pai.setArray(pai.getValidSize()+1);
        });

        btnQuicksort.setOnAction(event->{
            task2.setParams(pai.arrayProperty(),pai.getIndexProperty(),pai.getValidSize()-1);
            Thread thread = new Thread(task2);
            thread.setDaemon(true);
            thread.start();

        });


        for(int i=0;i<listener.length;i++){
            listener[i].addListener((obs,old, nevv)->{
                int iNewValue = (int) Math.floor(nevv.doubleValue());
                if(pai.getIndex()<pai.getValidSize()) {
                    cm.setVBoxIndivHeight(pai.getIndex(), (int) iNewValue);
                }
            });
        }

       pai.getIndexProperty().addListener((obs,old,nevv)->{
           int iNewValue = (int) Math.floor(nevv.doubleValue());
           int iOldValue = (int)Math.floor(old.doubleValue());
               cm.setStyle(iNewValue);
           if(iOldValue<pai.getValidSize()) {
               cm.setStyleOld(iOldValue);
           }

        });



    }



}

