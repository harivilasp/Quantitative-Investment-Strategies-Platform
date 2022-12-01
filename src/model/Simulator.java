package model;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * This interface is starting point of simulator which has features like loading from portfolio file
 * stored in memory, creating a new portfolio from scratch and saving it, and lastly, generating a
 * new stock.
 */
public interface Simulator {

  /**
   * Provides information about whether a portfolio has been selected by the user.
   *
   * @return whether a portfolio has been selected
   */
  boolean isPortfolioChosen();

  /**
   * Helps to reset the selection of portfolios in the model.
   */
  void resetPortfolios();

  /**
   * This helps to get the current value of portfolio at certain date.
   *
   * @param date represents the date as argument for getting value.
   * @return the value of all stocks with current value and quantity.
   * @throws IllegalArgumentException if invalid date is passed.
   * @throws RuntimeException         if errors like API timeout, or other error.
   */
  double getValue(String date) throws IllegalArgumentException, RuntimeException;

  /**
   * This helps to get the name of the portfolio set by the user.
   *
   * @return the portfolio name
   */
  String getName();

  /**
   * This helps to make in memory portfolio persistent by saving it as a text file.
   *
   * @throws IOException      if error occurs while saving file
   * @throws RuntimeException if improper data
   */
  void save() throws IOException, RuntimeException;

  /**
   * This helps to distribution of all stocks their quantities in portfolio.
   *
   * @return the composition of the portfolio (list of all stocks)
   */
  List<Stock> getComposition();

  /**
   * This method helps to create new portfolio with stocks.
   *
   * @param portfolioName the portfolio name
   * @param stocks        the list of stocks composing the portfolio
   */
  void addPortfolio(String portfolioName, List<Stock> stocks);

  /**
   * This method helps to create new empty flexible portfolio.
   *
   * @param portfolioName the portfolio name
   */
  void addFlexiblePortfolio(String portfolioName);

  /**
   * This method helps to load portfolio form existing file.
   *
   * @param filepath the relative file path
   * @throws IllegalArgumentException in case of invalid filepath
   * @throws RuntimeException         if the portfolio does not exist or is of an invalid format
   */
  void loadPortfolio(String filepath) throws IllegalArgumentException, RuntimeException;

  /**
   * This method helps to load flexible portfolio form existing file.
   *
   * @param filepath the relative file path
   * @throws IllegalArgumentException in case of invalid filepath
   * @throws RuntimeException         if the portfolio does not exist or is of an invalid format
   */
  void loadFlexiblePortfolio(String filepath) throws IllegalArgumentException, RuntimeException;

  /**
   * This method creates the stock object with given name and quantity.
   *
   * @param name     name of the stock
   * @param quantity quantity of the stock
   * @return the desired stock instance
   * @throws IllegalArgumentException in case of invalid stock information
   */
  Stock generateStock(String name, int quantity) throws IllegalArgumentException;

  /**
   * This method buys stock at particular date with given commission.
   *
   * @param stock      represents the stock symbol and quantity.
   * @param date       represents date of buying stock.
   * @param commission represents the commission of buying.
   */
  void buyStock(String stockName, int stockQty, String date, double commission)
      throws RuntimeException;

  /**
   * This method sells stock at particular date with given commission.
   *
   * @param stock      represents the stock symbol and quantity.
   * @param date       represents date of selling stock.
   * @param commission represents the commission of selling.
   * @throws RuntimeException when stock invalid combination of selling is provided.
   */
  void sellStock(String stockName, int stockQty, String date, double commission)
      throws RuntimeException;

  /**
   * This method calculated the map to draws the graph of performance in between two dates.
   *
   * @param startDate represents the start date.
   * @param endDate   represents the end date.
   * @return returns the map of date and number of stars to print.
   * @throws IllegalArgumentException when some invalid date is passed.
   */
  Map<String, Integer> getPerformance(String startDate, String endDate)
      throws IllegalArgumentException;

  /**
   * This methods calculates cost basis of portfolio which includes bought stocks price and
   * commission while selling and buying stocks.
   *
   * @param date represents the date at which cost basis is calculated.
   * @return returns the cost spent till given date.
   * @throws RuntimeException when invalid date is passed.
   */
  double getCostBasis(String date) throws Exception;

  /**
   * This method helps to get composition of stock till that date.
   *
   * @param date represents the date at which composition is required.
   * @return returns the list of stocks.
   * @throws RuntimeException when wrong date is passed.
   */
  List<Stock> getCompositionAtDate(String date) throws RuntimeException;

  void addStrategy(double amount, int intervalInDays,
      String startDate, String endDate, double commission,
      Map<String, Double> weights) throws Exception;

  void buyStocksWithWeights(double amount, String Date, double commission,
      Map<String, Double> weights) throws Exception;

}
