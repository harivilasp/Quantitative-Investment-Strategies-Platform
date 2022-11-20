import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import model.FlexiblePortfolio;
import model.FlexiblePortfolioImpl;
import model.Stock;
import utils.Utils;

/**
 * This class tests methods of flexible portfolio.
 */
public class FlexiblePortfolioTest {

  private final Set<String> validStocks;
  private final String FILEPATH;

  /**
   * Instantiates and loads the valid stock information.
   */
  public FlexiblePortfolioTest() {
    Utils.loadValidStocks();
    validStocks = Utils.VALID_STOCKS;
    FILEPATH = "flexibleportfolios/testflexible.txt";
  }

  @Before
  public void setUp() {
    List<Stock> stocks = new ArrayList<>();
    Stock stock1 = new Stock("GOOG", 145);
    Stock stock2 = new Stock("META", 1);

    stocks.add(stock1);
    stocks.add(stock2);
  }

  @Test
  public void testPortfolioCreationWith() {
    FlexiblePortfolio flexiblePortfolio = new FlexiblePortfolioImpl(FILEPATH, validStocks);
    List<Stock> expected = new ArrayList<Stock>();
    expected.add(new Stock("NFLX", 24));
    expected.add(new Stock("MSFT", 23));
    expected.add(new Stock("AAPL", 21));
    // Test the name
    Assert.assertEquals("TestFlexible", flexiblePortfolio.getName());
    // Test the stocks
    Assert.assertEquals(expected.toString(), flexiblePortfolio.getComposition().toString());
  }

  @Test
  public void testPortfolioBuyStock() {
    FlexiblePortfolio flexiblePortfolio =
            new FlexiblePortfolioImpl("test");
    flexiblePortfolio.buyStock(new Stock("GOOG", 2), "2022-10-01", 2);
    List<Stock> expected = new ArrayList<Stock>();
    expected.add(new Stock("GOOG", 2));
    Assert.assertEquals(expected.toString(), flexiblePortfolio.getComposition().toString());
  }

  @Test(expected = RuntimeException.class)
  public void testPortfolioSellStockWhenNoStock() throws RuntimeException {
    FlexiblePortfolio flexiblePortfolio =
            new FlexiblePortfolioImpl("test");
    flexiblePortfolio.sellStock(new Stock("GOOG", 2), "2022-10-01", 2);
    List<Stock> expected = new ArrayList<Stock>();
    expected.add(new Stock("GOOG", 143));
    expected.add(new Stock("META", 1));
    Assert.assertEquals(expected.toString(), flexiblePortfolio.getComposition().toString());
  }

  @Test
  public void testPortfolioGetCostBasis() throws Exception {
    FlexiblePortfolio flexiblePortfolio =
            new FlexiblePortfolioImpl("test");
    flexiblePortfolio.buyStock(new Stock("NFLX", 3),
            "2022-09-12", 0.9);
    Assert.assertEquals(710.49,
            flexiblePortfolio.getCostBasis("2022-10-01"), 0.01);
  }
}
