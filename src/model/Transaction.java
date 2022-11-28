package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * This class represents the transaction which is buy or sell.
 */
public class Transaction {

  private String stockName;
  private double quantity;

  private String date;
  private double commission;

  /**
   * This method constructs the transaction object from its components.
   *
   * @param stockName  represents name of stock.
   * @param quantity   represents quantity of stock.
   * @param date       represents date of transaction.
   * @param commission represents commission charged for that transaction.
   */
  public Transaction(String stockName, double quantity, String date, double commission)
      throws IllegalArgumentException {
    if (commission < 0) {
      throw new IllegalArgumentException("Negative commission not allowed");
    }

    SimpleDateFormat sdfrmt = new SimpleDateFormat("yyyy-MM-dd");
    sdfrmt.setLenient(false);
    try {
      sdfrmt.parse(date);
    } catch (ParseException e) {
      throw new IllegalArgumentException("Date format invalid");
    }

    this.stockName = stockName;
    this.quantity = quantity;
    this.date = date;
    this.commission = commission;
  }

  public double getCommission() {
    return commission;
  }

  public void setCommission(double commission) {
    this.commission = commission;
  }

  public double getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getStockName() {
    return stockName;
  }

  public void setStockName(String stockName) {
    this.stockName = stockName;
  }
}
