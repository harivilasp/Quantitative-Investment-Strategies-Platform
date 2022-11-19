package model;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import utils.Constants;

/**
 * This class represents flexible portfolio which is mutable
 * i.e. it is possible to add and delete stocks after creation.
 */
public class FlexiblePortfolioImpl implements FlexiblePortfolio {

  private final Portfolio delegate;
  private final List<Transaction> transactions;
  private FileIO fileIO;

  /**
   * This constructs object of portfolio with portfolio name.
   *
   * @param portfolioName represents the name of the portfolio.
   */
  public FlexiblePortfolioImpl(String portfolioName) {
    fileIO = new FileIOSCS();
    List<Stock> stocks = new ArrayList<>();
    this.delegate = new PortfolioImpl(portfolioName, stocks);
    transactions = new ArrayList<>();
  }

  /**
   * This constructs the portfolio from filepath and valid stocks.
   *
   * @param filePath    represents path of file.
   * @param validStocks represents the supported stocks.
   * @throws IllegalArgumentException when invalid data in file.
   * @throws RuntimeException         when error occurs while reading.
   */
  public FlexiblePortfolioImpl(String filePath, Set<String> validStocks)
          throws IllegalArgumentException, RuntimeException {
    fileIO = new FileIOSCS();
    FlexiblePortfolioImpl flexiblePortfolio =
            (FlexiblePortfolioImpl) fileIO.readFlexiblePortfolio(filePath);
    this.transactions = flexiblePortfolio.transactions;
    this.delegate = flexiblePortfolio.delegate;
  }

  /**
   * This constructs the portfolio with transactions and portfolio.
   *
   * @param transactions represents the list of transactions.
   * @param delegate     represents the portfolio type object.
   */
  FlexiblePortfolioImpl(List<Transaction> transactions, Portfolio delegate) {
    this.delegate = delegate;
    this.transactions = transactions;
  }

  @Override
  public void buyStock(Stock stock, String date, double commission) throws RuntimeException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    try {
      sdf.parse(date);
    } catch (ParseException pe) {
      throw new IllegalArgumentException(Constants.ERR_INVALID_DATE);
    }
    List<Stock> stocks = delegate.getComposition();
    boolean isAdded = false;

    for (Stock presentStock : stocks) {
      if (stock.getName().equals(presentStock.getName())) {
        // Change composition (quantity) of existing stock.
        presentStock.setQuantity(presentStock.getQuantity() + stock.getQuantity());
        isAdded = true;
        break;
      }
    }

    if (!isAdded) {
      stocks.add(stock);
    }

    transactions.add(new Transaction(stock.getName(), stock.getQuantity(), date, commission));
  }

  @Override
  public void save() throws IOException, RuntimeException {
    fileIO.writeFlexiblePortfolio(this.getName(), transactions);
  }

  @Override
  public void sellStock(Stock stock, String date, double commission) throws RuntimeException {
    List<Stock> stocks = delegate.getComposition();
    boolean isSold = false;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    try {
      sdf.parse(date);
    } catch (ParseException pe) {
      throw new IllegalArgumentException(Constants.ERR_INVALID_DATE);
    }

    List<Stock> compositionAtDate = this.getCompositionAtDate(date);
    // List to Map
    Map<String, Double> compDateMap = new HashMap<>();
    for (Stock stock1 : compositionAtDate) {
      compDateMap.put(stock1.getName(), stock1.getQuantity());
    }

    for (Stock presentStock : stocks) {
      if (stock.getName().equals(presentStock.getName())) {
        if (compDateMap.get(stock.getName()) >= stock.getQuantity()) {
          // Change composition (quantity) of existing stock.
          presentStock.setQuantity(presentStock.getQuantity() - stock.getQuantity());
          isSold = true;
          break;
        } else {
          throw new RuntimeException(Constants.ERR_INS_STOCK_DATA);
        }
      }
    }

    if (!isSold) {
      throw new RuntimeException(Constants.ERR_STOCK_NF);
    }

    transactions.add(new Transaction(stock.getName(), -(stock.getQuantity()), date, commission));
  }

  @Override
  public List<Stock> getComposition() {
    return delegate.getComposition();
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

    double value = 0;
    List<Stock> composition = getCompositionAtDate(date);
    for (Stock stock : composition) {
      try {
        value += (stock.getQuantity() * AlphaVantgeApiUtil.getInstance()
                .getValue(stock.getName(), date));
      } catch (RuntimeException re) {
        throw new RuntimeException(Constants.ERR_API_TIMEOUT);
      }
    }
    return value;
  }

  @Override
  public String getName() {
    return delegate.getName();
  }

  @Override
  public double getCostBasis(String date) throws RuntimeException {
    // Format the date parameter.
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    format.setLenient(false);

    // Parse the date
    Date formattedDate;
    try {
      formattedDate = format.parse(date);
    } catch (ParseException exception) {
      throw new RuntimeException(Constants.ERR_INVALID_DATE);
    }

    double cost = 0;
    // Assuming transactions are sorted.
    for (Transaction transaction : transactions) {
      if (date.compareTo(transaction.getDate()) >= 0) {
        cost += transaction.getCommission();

        double qty = transaction.getQuantity();
        if (qty > 0) {
          try {
            cost += qty * AlphaVantgeApiUtil.getInstance()
                    .getValue(transaction.getStockName(), transaction.getDate());
          } catch (RuntimeException re) {
            throw new RuntimeException(Constants.ERR_API_TIMEOUT);
          }
        }
      }
    }

    return cost;
  }

  @Override
  public List<Stock> getCompositionAtDate(String date) {
    Map<String, Double> stocks = new TreeMap<>();
    for (Transaction transaction : transactions) {
      if (date.compareTo(transaction.getDate()) >= 0) {
        stocks.put(
                transaction.getStockName(),
                transaction.getQuantity()
                        + stocks.getOrDefault(transaction.getStockName(), 0.0)
        );
      }
    }

    // Convert map to list
    List<Stock> stockList = new ArrayList<>();
    for (Map.Entry<String, Double> entry : stocks.entrySet()) {
      stockList.add(
              new Stock(entry.getKey(), entry.getValue())
      );
    }

    return stockList;
  }
}
