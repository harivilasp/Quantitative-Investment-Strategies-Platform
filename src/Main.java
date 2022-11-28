import java.io.InputStreamReader;
import java.util.Scanner;

import controller.PortfolioController;
import controller.PortfolioControllerImpl;
import controller.SimulatorController;
import model.SimulatorImpl;
import utils.Constants;
import view.GUIMainView;
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
  public static void main(String args[]) {

    System.out.println("Welcome!");
    int choice;
    Scanner s = new Scanner(System.in);
    while (true) {
      System.out.println("\nWhich one of the following type of GUI would you like to work with?");
      System.out.println("1. Commandline or");
      System.out.println("2. GUI");
      System.out.println("3. Exit");

      try {
        choice = Integer.parseInt(s.nextLine());

        // In case the user wants to exit the application.
        if (choice == 3) {
          return;
        }

        // In case the decision was invalid, restart.
        if (choice < 1 || choice > 3) {
          System.out.println("Invalid option!");
          continue;
        }

      } catch (NumberFormatException nfe) {
        System.out.println(Constants.ERR_ENTER_NUM);
        continue;
      }

      if (choice == 2) {
        SimulatorController sc = new SimulatorController(new SimulatorImpl(),
                new GUIMainView(), new InputStreamReader(System.in), System.out);
      } else {
        PortfolioController pc = new PortfolioControllerImpl(new SimulatorImpl(),
                new PortfolioViewImpl(System.out), new InputStreamReader(System.in), System.out);
        try {
          pc.start();
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }
      }
    }
  }
}