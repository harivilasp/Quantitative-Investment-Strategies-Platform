import javax.swing.*;

import view.JCreatePortfolioView;
import view.JTransactionView;
import view.PanelView;

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
//    // Instantiate the model
//    Simulator model = new SimulatorImpl();
//    // Instantiate the view
//    PortfolioView view = new PortfolioViewImpl(System.out);
//    // Instantiate the controller
//    PortfolioController controller = new PortfolioControllerImpl(model, view,
//            new InputStreamReader(System.in), System.out);
//
//    // Start the application
//    controller.start();

    JPanel panel = new JCreatePortfolioView("Create portfolio");
    JFrame frame = new JFrame();
    frame.setVisible(true);
    frame.add(panel);
    frame.pack();
    // frame.remove(tranPanel);

    PanelView view = new JTransactionView("Sell");
  }
}