package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import utils.Constants;
import utils.Utils;

/**
 * This class represents the semicolon seperated read and write methods.
 */
public class FileIOSCS implements FileIO {

  @Override
  public Portfolio readPortfolio(String filePath)
          throws IllegalArgumentException, RuntimeException {
    String portfolioName;
    List<Stock> stocks;

    // If the filepath is invalid.
    if (filePath == null || filePath.isEmpty() || !Utils.isValidPath(filePath)) {
      throw new IllegalArgumentException(Constants.ERR_INVALID_FILE_PATH);
    }

    // Instantiate the file
    File file = new File(filePath);

    // If the no file exists at the filepath.
    if (!file.exists()) {
      throw new IllegalArgumentException(Constants.ERR_INVALID_FILE_PATH);
    }

    // File exists, read data.
    try {
      StringBuilder builder = new StringBuilder();
      Scanner scanner = new Scanner(file);

      // Extract data from file.
      while (scanner.hasNextLine()) {
        builder.append(scanner.nextLine()).append("\n");
      }

      String fileResponse = builder.toString();
      String[] responses = fileResponse.split("\n");

      // If portfolio data does not start with a name.
      if (responses[0].contains(";")) {   // Stock delimiter
        throw new RuntimeException(Constants.ERR_INVALID_FILE_FORMAT);
      }

      // If the portfolio name is invalid
      if (responses[0].isEmpty()) {
        throw new IllegalArgumentException(Constants.ERR_INVALID_DATA);
      }

      // Set the portfolio name
      portfolioName = responses[0];

      // Instantiate the stock array
      stocks = new ArrayList<Stock>();

      // Iterate through the rest of the list and parse the stock information.
      for (int i = 1; i < responses.length; i++) {
        stocks.add(parseStock(responses[i]));
      }

    } catch (FileNotFoundException e) {
      throw new RuntimeException(Constants.ERR_INVALID_FILE_PATH);
    }

    return new PortfolioImpl(portfolioName, stocks);
  }

  @Override
  public void writePortfolio(String portfolioName, List<Stock> stocks)
          throws IOException, RuntimeException {
    StringBuilder data = new StringBuilder(portfolioName);
    for (Stock stock : stocks) {
      String row = stock.getName() + ";" + (int) stock.getQuantity();
      data.append("\n").append(row);
    }

    writeFile(data, portfolioName, "portfolios", "inflexible");
  }

  @Override
  public FlexiblePortfolio readFlexiblePortfolio(String filePath)
          throws IllegalArgumentException, RuntimeException {
    List<Transaction> transactions = new ArrayList<>();
    Portfolio delegate;
    String portfolioName;

    // If the filepath is invalid.
    if (filePath == null || filePath.isEmpty() || !Utils.isValidPath(filePath)) {
      throw new IllegalArgumentException(Constants.ERR_INVALID_FILE_PATH);
    }

    // Instantiate the file
    File file = new File(filePath);

    // If the no file exists at the filepath.
    if (!file.exists()) {
      throw new IllegalArgumentException(Constants.ERR_INVALID_FILE_PATH);
    }

    // File exists, read data.
    try {
      StringBuilder builder = new StringBuilder();
      Scanner scanner = new Scanner(file);

      // Extract data from file.
      while (scanner.hasNextLine()) {
        builder.append(scanner.nextLine()).append("\n");
      }

      // Parse the portfolio data
      String fileText = builder.toString();
      String[] lines = fileText.split("\n");

      // If portfolio data does not start with a name.
      if (lines[0].contains(";")) {   // Stock delimiter
        throw new RuntimeException(Constants.ERR_INVALID_FILE_FORMAT);
      }

      // If the portfolio name is invalid
      if (lines[0].isEmpty()) {
        throw new IllegalArgumentException(Constants.ERR_INVALID_DATA);
      }

      portfolioName = lines[0];
      for (int i = 1; i < lines.length; i++) {
        String[] transactionParts = lines[i].split(";");
        // Invalid Stock format.
        if (transactionParts.length != 4) {
          throw new RuntimeException(Constants.ERR_INVALID_FILE_FORMAT);
        }

        // If the stock names are not part of the publicly listed stocks, throw exception.
        if (transactionParts[0].isEmpty() || !Utils.VALID_STOCKS.contains(transactionParts[0])) {
          throw new IllegalArgumentException(Constants.ERR_INVALID_DATA);
        }

        // If the stock quantity is invalid (decimal value).
        int quantity;
        try {
          quantity = Integer.parseInt(transactionParts[2]);
        } catch (NumberFormatException nfe) {
          throw new IllegalArgumentException(Constants.ERR_INVALID_DATA);
        }

        transactions.add(
                new Transaction(transactionParts[0], quantity,
                        transactionParts[1], Double.parseDouble(transactionParts[3]))
        );
      }
    } catch (FileNotFoundException e) {
      throw new RuntimeException(Constants.ERR_INVALID_FILE_PATH);
    }

    // Sort transactions by date
    transactions.sort(new Comparator<Transaction>() {
      @Override
      public int compare(Transaction o1, Transaction o2) {
        return o1.getDate().compareTo(o2.getDate());
      }
    });

    // Extract stock from transactions (sorted)
    Map<String, Double> stockMap = new HashMap<>();
    for (Transaction transaction : transactions) {
      // Add up quantity based on date. If < 0, invalid stock sale.
      double stockQty = stockMap.getOrDefault(transaction.getStockName(), 0.0)
              + transaction.getQuantity();

      if (stockQty < 0) {
        throw new IllegalArgumentException(Constants.ERR_INVALID_DATA);
      }

      stockMap.put(transaction.getStockName(), stockQty);
    }

    // Build list from map
    List<Stock> stockList = new ArrayList<>();
    for (Map.Entry<String, Double> entry : stockMap.entrySet()) {
      stockList.add(
              new Stock(entry.getKey(), entry.getValue())
      );
    }

    delegate = new PortfolioImpl(portfolioName, stockList);
    return new FlexiblePortfolioImpl(transactions, delegate);
  }

  @Override
  public void writeFlexiblePortfolio(String portfolioName, List<Transaction> transactions)
          throws IOException, RuntimeException {
    StringBuilder data = new StringBuilder(portfolioName);
    for (Transaction transaction : transactions) {
      String row = transaction.getStockName() + ";" + transaction.getDate() + ";"
              + (int) transaction.getQuantity() + ";" + transaction.getCommission();
      data.append("\n").append(row);
    }

    writeFile(data, portfolioName, "flexibleportfolios", "flexible");
  }

  /* Helper method to write data to a file. */
  private void writeFile(StringBuilder data, String portfolioName, String dirName, String type)
          throws IOException, RuntimeException {
    // Create the file and write data.
    File rootDir = createDirectory(dirName);
    String filepath = rootDir.getAbsolutePath() + "/" + portfolioName + ".txt";
    File file = new File(filepath);

    // Check if the file already exists.
    if (file.exists()) {
      if (type.compareToIgnoreCase("inflexible") == 0) {
        throw new RuntimeException(Constants.ERR_FILE_EXISTS);
      } else {
        file.delete();
      }
    }
    // Create the file
    boolean response = file.createNewFile();
    if (!response) {
      throw new RuntimeException(Constants.ERR_FILE_NOT_SAVED);
    }

    // Save portfolio info in the file.
    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
    writer.write(data.toString());
    writer.close();
  }

  /* Helper method to parse stock data read from a file. */
  private Stock parseStock(String stockResponse)
          throws IllegalArgumentException, RuntimeException {
    // Split the stock information using ";" delimiter.
    String[] stockInfo = stockResponse.split(";");

    // Invalid Stock format.
    if (stockInfo.length != 2) {
      throw new RuntimeException(Constants.ERR_INVALID_FILE_FORMAT);
    }

    // If the stock names are not part of the publicly listed stocks, throw exception.
    if (stockInfo[0].isEmpty() || !Utils.VALID_STOCKS.contains(stockInfo[0])) {
      throw new IllegalArgumentException(Constants.ERR_INVALID_DATA);
    }

    // If the stock quantity is invalid (decimal value).
    int quantity;
    try {
      quantity = Integer.parseInt(stockInfo[1]);
    } catch (NumberFormatException nfe) {
      throw new IllegalArgumentException(Constants.ERR_INVALID_DATA);
    }

    // If the quantity is negative or 0.
    if (quantity <= 0) {
      throw new IllegalArgumentException(Constants.ERR_INVALID_DATA);
    }

    return new Stock(stockInfo[0], Integer.parseInt(stockInfo[1]));
  }

  /* Helper method to create a local directory to save portfolio data. */
  private File createDirectory(String dirName) throws IOException {
    // Create file directory.
    File dir = new File(dirName);

    if (!dir.exists()) {
      boolean response = dir.mkdir();
      if (response) {
        return dir;
      }
    } else {
      return dir;
    }

    throw new IOException("Error: Unable to create directory " + dir.getAbsolutePath());
  }
}
