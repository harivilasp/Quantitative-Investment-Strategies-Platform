package model;

/**
 * This interface helps to get value of stock at certain date.
 */
public interface APIUtil {
  /**
   * This method provides the closing day value for a particular date and stock label.
   *
   * @param symbol the stock label.
   * @param date   the date to extract the closing value of stock.
   * @return the closing value of the stock at this date.
   */
  double getValue(String symbol, String date) throws RuntimeException;
}
