package view;

import java.io.IOException;

/**
 * This interface represents all the operations as part of the inflexible portfolio view of the
 * application.
 */
public interface PortfolioView {

  /**
   * Provides the options to the user for taking actions on the portfolio, such as determining the
   * composition, value or loading/adding a portfolio.
   *
   * @throws IOException in case of any input-output errors
   */
  void showPortfolioActions() throws IOException;

  /**
   * Provides options to the user related to creation of portfolios, either by loading from filepath
   * or generating the portfolio manually.
   *
   * @throws IOException in case of any input-output errors
   */
  void showPortfolioAddMethods() throws IOException;

  /**
   * Shows a given line of output to the user.
   *
   * @param line the line of output
   * @throws IOException in case of any input-output errors
   */
  void showText(String line) throws IOException;
}
