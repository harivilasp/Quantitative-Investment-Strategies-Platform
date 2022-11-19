package controller;

import java.io.IOException;

/**
 * This Controller takes input from the user, delegates tasks to model and instructs view to print
 * to user result or exceptions accordingly.
 */
public interface PortfolioController {

  /**
   * This is the main starting point of simulator.
   *
   * @throws IOException if error occurs in input or output during execution
   */
  void start() throws IOException;
}