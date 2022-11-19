import java.io.InputStreamReader;

import controller.PortfolioController;
import controller.PortfolioControllerImpl;
import model.Simulator;
import model.SimulatorImpl;
import view.PortfolioView;
import view.PortfolioViewImpl;

/**
 * This method is main class and staring point of execution.
 */
public class Main {

  /**
   * Instantiates the model, view and controller. Also, runs the app for the client.
   *
   * @param args any input parameter
   * @throws Exception in case of any errors
   */
  public static void main(String[] args) throws Exception {
    // Instantiate the model
    Simulator model = new SimulatorImpl();
    // Instantiate the view
    PortfolioView view = new PortfolioViewImpl(System.out);
    // Instantiate the controller
    PortfolioController controller = new PortfolioControllerImpl(model, view,
            new InputStreamReader(System.in), System.out);

    // Start the application
    controller.start();
  }
}