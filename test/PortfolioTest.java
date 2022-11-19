import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import model.Portfolio;
import model.PortfolioImpl;
import model.Simulator;
import model.SimulatorImpl;
import model.Stock;
import utils.Utils;

/**
 * Test class for {@code PortfolioImpl} class.
 */
public class PortfolioTest {

  private List<Stock> stocks;
  private final String FILEPATH;

  /**
   * Instantiates and loads the valid stock information.
   */
  public PortfolioTest() {
    Utils.loadValidStocks();
    FILEPATH = "portfolios/College Fund.txt";
  }

  @Before
  public void setUp() {
    stocks = new ArrayList<>();
    Stock stock1 = new Stock("GOOG", 145);
    Stock stock2 = new Stock("META", 1);

    stocks.add(stock1);
    stocks.add(stock2);
  }

  /**
   * Test Portfolio creation from file.
   */
  @Test
  public void testConstructorWithFile() {
    Portfolio portfolio = new PortfolioImpl(FILEPATH);
    List<Stock> stockList = new ArrayList<>();
    stockList.add(new Stock("AAPL", 1));
    stockList.add(new Stock("NFLX", 1));

    // Test the name
    Assert.assertEquals("College Fund", portfolio.getName());
    // Test the stocks
    Assert.assertEquals(stockList.toString(), portfolio.getComposition().toString());
  }

  /**
   * Test portfolio creation with invalid filepath.
   */
  @Test
  public void testConstructorWithInvalidFile() {
    try {
      Portfolio portfolio = new PortfolioImpl("portfolios/Medical Savings.txt");

      Assert.fail("Test failed: Portfolio created with not existent file!");
    } catch (RuntimeException re) {
      // Test passed.
    }

    try {
      Portfolio portfolio = new PortfolioImpl("stocks/College Fund.txt");

      Assert.fail("Test failed: Portfolio created with invalid filepath!");
    } catch (RuntimeException re) {
      // Test passed.
    }
  }

  /**
   * Test portfolio creation from stock list and portfolio name.
   */
  @Test
  public void testConstructorFromStocks() {
    Portfolio portfolio = new PortfolioImpl("Tech Portfolio", stocks);

    // Test the name
    Assert.assertEquals("Tech Portfolio", portfolio.getName());
    // Test the stocks
    Assert.assertEquals(stocks, portfolio.getComposition());
  }

  /**
   * Test portfolio save method.
   */
  @Test
  public void testSavePortfolio() {
    Portfolio portfolio = new PortfolioImpl("Tech Portfolio", stocks);

    try {
      try {
        File myObj = new File("portfolios/Tech Portfolio.txt");
        if (myObj.delete()) {
          System.out.println("Deleted the file: " + myObj.getName());
        }
      } catch (Exception e) {
        //file didn't exist
      }
      portfolio.save();
    } catch (IOException e) {
      Assert.fail("Test failed: Portfolio save unsuccessful!");
    }

    // Filepath: portfolios/Tech Portfolio.txt
    // Load the saved data
    Portfolio portfolioSaved = new PortfolioImpl("portfolios/Tech Portfolio.txt");

    // Test the saved name
    Assert.assertEquals("Tech Portfolio", portfolioSaved.getName());
    // Test the saved stocks
    Assert.assertEquals(stocks.toString(), portfolioSaved.getComposition().toString());
  }

  /**
   * Test portfolio composition (from filepath).
   */
  @Test
  public void testCompositionFromFilepath() {
    List<Stock> stockList = new ArrayList<>();
    stockList.add(new Stock("AAPL", 1));
    stockList.add(new Stock("NFLX", 1));

    Portfolio portfolio = new PortfolioImpl(FILEPATH);
    // Test the saved stocks
    Assert.assertEquals(stockList.toString(), portfolio.getComposition().toString());
  }

  /**
   * Test portfolio composition (from stocks).
   */
  @Test
  public void testCompositionFromStocks() {
    Portfolio portfolio = new PortfolioImpl("Tech Portfolio", stocks);

    // Test the saved stocks
    Assert.assertEquals(stocks, portfolio.getComposition());
  }

  /**
   * Test get value of portfolio (from filepath, valid date).
   */
  @Test
  public void testGetValueFromFilepath() throws ParseException {
    double expected = 1 * 155.74 + 1 * 295.72;

    Portfolio portfolio = new PortfolioImpl(FILEPATH);
    // Test the saved stocks
    Assert.assertEquals(expected, portfolio.getValue("2022-10-28"), 0.00);
  }

  /**
   * Test get value of portfolio (from filepath, valid date).
   */
  @Test
  public void testGetValueFromStocks() throws ParseException {
    double expected = 14102.04;
    Portfolio portfolio = new PortfolioImpl("Tech Portfolio", stocks);
    // Test the saved stocks
    Assert.assertEquals(expected, portfolio.getValue("2022-10-28"), 0.02);
  }

  /**
   * Test get value for portfolio (invalid future date).
   */
  @Test(expected = RuntimeException.class)
  public void testGetValueDateInFuture() throws ParseException {
    Simulator sm = new SimulatorImpl();
    Portfolio portfolio = new PortfolioImpl(FILEPATH);
    // @throws: RuntimeException for future date.
    portfolio.getValue("2023-12-29");
    Assert.assertEquals("Error", "Error");
  }
}