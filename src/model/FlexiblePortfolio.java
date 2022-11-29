package model;

import java.util.List;
import java.util.Map;

/**
 * This represents the inflexible portfolio which has all functionalities of inflexible portfolios
 * and also supports buying, selling, getting composition at certain date, getting cost basis.
 */
public interface FlexiblePortfolio extends Portfolio {

  /**
   * This method buys stock at particular date with given commission.
   *
   * @param stock      represents the stock symbol and quantity.
   * @param date       represents date of buying stock.
   * @param commission represents the commission of buying.
   */
  void buyStock(Stock stock, String date, double commission) throws RuntimeException;

  /**
   * This method sells stock at particular date with given commission.
   *
   * @param stock      represents the stock symbol and quantity.
   * @param date       represents date of selling stock.
   * @param commission represents the commission of selling.
   * @throws RuntimeException when stock invalid combination of selling is provided.
   */
  void sellStock(Stock stock, String date, double commission) throws RuntimeException;

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

  /**
   * This method helps add strategy to the flexible portfolio for buying and selling of stocks.
   *
   * @param amount         the provided balance to use for purchase and sale of stocks
   * @param intervalInDays the interval in days to complete these operations
   * @param startDate      the start date of these operations
   * @param endDate        the end date of these operations (optional)
   * @param commission     the commission associated with these operations
   * @param weights        the stocks weights associated with these operations
   */
  void addStrategy(double amount, int intervalInDays, String startDate, String endDate,
      double commission, Map<String, Double> weights) throws Exception;

  /**
   * This method helps buy stocks associated with weights for each stocks for a defined amount.
   *
   * @param amount     the provided amount
   * @param date       the date of the purchase
   * @param commission the commission associated with the purchase
   * @param weights    the defined weights for the stocks
   */
  void buyStocksWithWeights(double amount, String date, double commission,
      Map<String, Double> weights) throws Exception;
}
