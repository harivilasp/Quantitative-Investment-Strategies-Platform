package model;

import java.io.IOException;
import java.util.List;

/**
 * This helps to write and read file.
 */
public interface FileIO {

  /**
   * This method helps to read the flexible portfolio for given filepath.
   *
   * @param filePath represents the file path of flexible portfolio.
   * @return returns the flexible portfolio object after reading from file.
   */
  public FlexiblePortfolio readFlexiblePortfolio(String filePath);

  /**
   * This method helps to read the inflexible portfolio for given file path.
   *
   * @param filePath represents the file path of inflexible portfolio.
   * @return returns the inflexible portfolio object.
   */
  public Portfolio readPortfolio(String filePath);

  /**
   * This method helps to write the flexible portfolio in file.
   *
   * @param portfolioName represents the name of portfolio.
   * @param transactions  represents the transactions of portfolio.
   * @throws IOException when error occurs while writing file.
   */
  public void writeFlexiblePortfolio(String portfolioName,
      List<Transaction> transactions, List<Strategy> strategies)
      throws IOException;

  /**
   * This method helps to write the flexible portfolio in file.
   *
   * @param portfolioName represents the name of portfolio.
   * @param stocks        represents the stock of portfolio.
   * @throws IOException when error occurs while writing file.
   */
  public void writePortfolio(String portfolioName, List<Stock> stocks) throws IOException;
}
