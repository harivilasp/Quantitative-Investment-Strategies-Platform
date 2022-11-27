import java.io.InputStreamReader;

import controller.SimulatorController;
import model.Simulator;
import model.SimulatorImpl;
import view.ButtonOnly;
import view.MainView;

public class DummyMain {
  public static void main(String args[]){
    Simulator model = new SimulatorImpl();
    ButtonOnly mainView = new MainView("Main");
    SimulatorController simulatorController =
            new SimulatorController(model,mainView,new InputStreamReader(System.in), System.out);
  }
}
