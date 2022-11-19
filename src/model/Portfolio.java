package model;

import java.io.IOException;
import java.util.List;

/**
 * This interface represents the overall functionalities for managing portfolio of stocks.
 */
public interface Portfolio {

  /**
   * This helps to get the current value of portfolio at certain date.
   *
   * @param date represents the date as argument for getting value
   * @return the value of all stocks with current value and quantity
   * @throws IllegalArgumentException if invalid date is provided.
   * @throws RuntimeException         if errors like API timeout, or other error
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
   * @throws IOException      if error occurs while saving file.
   * @throws RuntimeException if improper data.
   */
  void save() throws IOException, RuntimeException;

  /**
   * This helps to distribution of all stocks their quantities in portfolio.
   *
   * @return the composition of the portfolio (list of all stocks)
   */
  List<Stock> getComposition();
}
