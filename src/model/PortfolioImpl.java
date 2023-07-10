package model;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import utils.Constants;

/**
 * Portfolio model implementation represents the functionalities of {@code Portfolio} interface.
 */
public class PortfolioImpl implements Portfolio {

  /* The portfolio name. */
  private final String portfolioName;

  /* The list of stocks, part of the portfolio. */
  private final List<Stock> stocks;

  /* The FileIO reference for reading, parsing and writing data. */
  private FileIO fileIO;

  /**
   * This method constructs portfolio object from portfolio name and stocks.
   *
   * @param portfolioName represents name of the portfolio.
   * @param stocks        represents array of stocks.
   */
  public PortfolioImpl(String portfolioName, List<Stock> stocks) {
    this.portfolioName = portfolioName;
    this.stocks = stocks;
    this.fileIO = new FileIOSCS();
  }

  /**
   * This method constructs portfolio object from filepath.
   *
   * @param filepath represents path of loaded file.
   * @throws IllegalArgumentException if file path is invalid.
   * @throws RuntimeException         if file has stocks that are not valid.
   */
  public PortfolioImpl(String filepath) throws IllegalArgumentException, RuntimeException {
    this.fileIO = new FileIOSCS();
    // Read and parse portfolio from the filepath and assign values.
    PortfolioImpl portfolio = (PortfolioImpl) fileIO.readPortfolio(filepath);
    this.portfolioName = portfolio.portfolioName;
    this.stocks = portfolio.stocks;
  }

  @Override
  public double getValue(String date) throws IllegalArgumentException, RuntimeException {
    // Format the date parameter.
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    // Parse the date
    Date d1;
    try {
      d1 = format.parse(date);
    } catch (ParseException exception) {
      throw new IllegalArgumentException(Constants.ERR_INVALID_DATE);
    }

    // Current date (local time)
    Date d2 = new Date();

    // If provided date is in the future (invalid).
    if (d1.compareTo(d2) >= 0) {
      throw new IllegalArgumentException(Constants.ERR_INVALID_DATE);
    }

    // Compute the value
    double value = 0;
    for (Stock stock : stocks) {
      try {
        value += stock.getQuantity() * stock.getPriceAtDate(date);
      } catch (RuntimeException re) {
        throw new RuntimeException(Constants.ERR_API_TIMEOUT);
      }
    }

    return value;
  }

  @Override
  public String getName() {
    return this.portfolioName;
  }

  @Override
  public void save() throws IOException, RuntimeException {
    this.fileIO.writePortfolio(this.portfolioName, this.stocks);
  }

  @Override
  public List<Stock> getComposition() {
    return this.stocks;
  }

  @Override
  public String toString() {
    return "portfolioName=" + portfolioName + ", stocks=" + stocks.toString();
  }

}
