package model;

import utils.Constants;
import utils.Utils;

/**
 * Represents the stock of company, has name/symbol and quantity.
 */
public class Stock {

  /* The stock name/symbol. */
  private final String name;

  /* The quantity of stock. */
  private double quantity;

  /**
   * This method constructs the stock object from name and quantity.
   *
   * @param name     represents name or symbol of stock
   * @param quantity represents quantity of stock
   */
  public Stock(String name, double quantity) {
    if (Utils.VALID_STOCKS.contains(name)) {
      this.name = name;
      this.quantity = quantity;
    } else {
      throw new IllegalArgumentException(Constants.ERR_INVALID_STOCK_DATA);
    }
  }

  /**
   * This method helps to get the name of stock and quantity.
   *
   * @return returns the symbol of stock
   */
  public String getName() {
    return this.name;
  }

  /**
   * This method helps to get the number of share of stock.
   *
   * @return returns the number of shares
   */
  public double getQuantity() {
    return this.quantity;
  }

  /**
   * This method helps to get the closing price of share price of stock at certain date.
   *
   * @param date the date at which price is required
   * @return the closing price at that date
   */
  public double getPriceAtDate(String date) throws RuntimeException {
    return AlphaVantgeApiUtil.getInstance().getValue(this.name, date);
  }

  @Override
  public String toString() {
    return name + "=" + quantity;
  }

  public void setQuantity(double qty) {
    this.quantity = qty;
  }
}
