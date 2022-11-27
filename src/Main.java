import javax.swing.JFrame;
import javax.swing.JPanel;
import view.IView;
import view.JGetAtDateView;
import view.JTransactionView;

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

    IView view;
    JPanel tranPanel = new JTransactionView("Buy/Sell");
    JPanel getDatePanel = new JGetAtDateView("Get value at date");
    JFrame frame = new JFrame();
    frame.setVisible(true);

    frame.add(tranPanel);
    frame.add(getDatePanel);
    frame.pack();

    tranPanel.setVisible(true);
    getDatePanel.setVisible(false);
    Thread.sleep(5000);
    tranPanel.setVisible(false);
    getDatePanel.setVisible(true);
    frame.pack();
    // frame.remove(tranPanel);

  }
}