import java.io.InputStreamReader;

import controller.SimulatorController;
import model.Simulator;
import model.SimulatorImpl;
import view.GUIMainView;
import view.GUIView;

public class DummyMain {
  public static void main(String args[]) {
    Simulator model = new SimulatorImpl();
//    ButtonOnly mainView = new Home("Main");
//
    GUIView view = new GUIMainView();
    SimulatorController simulatorController =
            new SimulatorController(model, view, new InputStreamReader(System.in), System.out);
  }
}
