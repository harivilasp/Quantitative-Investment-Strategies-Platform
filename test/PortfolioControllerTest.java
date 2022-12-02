import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import controller.PortfolioController;
import controller.PortfolioControllerImpl;
import model.Portfolio;
import model.Simulator;
import model.SimulatorImpl;
import model.Stock;
import view.PortfolioView;
import view.PortfolioViewImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test class for the {@code PortfolioControllerImpl} class.
 */
public class PortfolioControllerTest {

  private PortfolioView view;
  private PortfolioController controller;
  private final Simulator model;

  private Appendable out;
  private Reader in;

  /**
   * Instantiates the model, view, controller and the input-output streams.
   */
  public PortfolioControllerTest() {
    this.in = new StringReader("");
    this.out = new StringBuffer();
    this.model = new SimulatorImpl();
    this.view = new PortfolioViewImpl(out);
    this.controller = new PortfolioControllerImpl(model, view, in, out);
  }

  static class MockSimulator implements Simulator {

    StringBuilder log;

    public MockSimulator(StringBuilder log) {
      this.log = log;
    }

    @Override
    public boolean isPortfolioChosen() {
      return false;
    }

    @Override
    public void resetPortfolios() {
      // does nothing
    }

    @Override
    public double getValue(String date) throws IllegalArgumentException, RuntimeException {
      return 0;
    }


    @Override
    public String getName() {
      return null;
    }

    @Override
    public void save() throws IOException, RuntimeException {
      // does nothing
    }

    @Override
    public List<Stock> getComposition() {
      return null;
    }

    @Override
    public void addPortfolio(String portfolioName, List<Stock> stocks) {
      log.append(portfolioName).append(" ").append(stocks.toString());
    }

    @Override
    public void addFlexiblePortfolio(String portfolioName) {
      // Do nothing
    }

    @Override
    public void loadPortfolio(String filepath) throws IllegalArgumentException, RuntimeException {
      log.append("Received: ").append(filepath).append("\n");
    }

    @Override
    public void loadFlexiblePortfolio(String filepath)
            throws IllegalArgumentException, RuntimeException {
      // Do nothing
    }


    @Override
    public Stock generateStock(String name, int quantity) throws IllegalArgumentException {
      return new Stock(name, quantity);
    }

    @Override
    public void buyStock(String stockName, int stockQty, String date, double commission) {
      // Do nothing
    }

    @Override
    public void sellStock(String stockName, int stockQty, String date,
                          double commission) throws RuntimeException {
      // Do nothing
    }

    @Override
    public Map<String, Integer> getPerformance(String startDate, String endDate)
            throws IllegalArgumentException {
      return null;
    }

    @Override
    public double getCostBasis(String date) throws RuntimeException {
      return 0;
    }

    @Override
    public List<Stock> getCompositionAtDate(String date) throws RuntimeException {
      return null;
    }

    @Override
    public void addStrategy(double amount,
                            int intervalInDays, String startDate, String endDate,
                            double commission, Map<String, Double> weights) throws Exception {
      // do nothing
    }

    @Override
    public void buyStocksWithWeights(double amount,
                                     String date, double commission,
                                     Map<String, Double> weights) throws Exception {
      // Do nothing
    }
  }

  static class MockSimulatorForStock implements Simulator {

    StringBuilder log;

    public MockSimulatorForStock(StringBuilder log) {
      this.log = log;
    }

    @Override
    public boolean isPortfolioChosen() {
      return false;
    }

    @Override
    public void resetPortfolios() {
      // Do nothing
    }

    @Override
    public double getValue(String date) throws IllegalArgumentException, RuntimeException {
      return 0;
    }

    @Override
    public String getName() {
      return null;
    }

    @Override
    public void save() throws IOException, RuntimeException {
      // Do nothing
    }

    @Override
    public List<Stock> getComposition() {
      return null;
    }

    @Override
    public void addPortfolio(String portfolioName, List<Stock> stocks) {
      // Do nothing
    }

    @Override
    public void addFlexiblePortfolio(String portfolioName) {
      // Do nothing
    }

    @Override
    public void loadPortfolio(String filepath)
            throws IllegalArgumentException, RuntimeException {
      // Do nothing
    }

    @Override
    public void loadFlexiblePortfolio(String filepath)
            throws IllegalArgumentException, RuntimeException {
      // Do nothing
    }

    @Override
    public Stock generateStock(String name, int quantity) throws IllegalArgumentException {
      log.append(name).append(";").append(quantity);
      return new Stock(name, quantity);
    }

    @Override
    public void buyStock(String stockName, int stockQty, String date, double commission) {
      // Do nothing
    }

    @Override
    public void sellStock(String stockName,
                          int stockQty, String date, double commission) throws RuntimeException {
      // Do nothing
    }

    @Override
    public Map<String, Integer> getPerformance(String startDate, String endDate)
            throws IllegalArgumentException {
      return null;
    }

    @Override
    public double getCostBasis(String date) throws RuntimeException {
      return 0;
    }

    @Override
    public List<Stock> getCompositionAtDate(String date) throws RuntimeException {
      return null;
    }

    @Override
    public void addStrategy(double amount,
                            int intervalInDays, String startDate, String endDate,
                            double commission, Map<String, Double> weights) throws Exception {
      // Do nothing
    }

    @Override
    public void buyStocksWithWeights(double amount,
                                     String date, double commission,
                                     Map<String, Double> weights) throws Exception {
      // do nothing
    }
  }

  @Test
  public void testStartCall() {
    this.in = new StringReader("3\n");
    this.view = new PortfolioViewImpl(out);
    this.controller = new PortfolioControllerImpl(model, view, in, out);

    String expected = "";
    try {
      expected =
              new String(Files.readAllBytes(Paths.get(
                      "test/testfiles/controller_test_files/test_start_call.txt")));
    } catch (IOException ioe) {
      System.out.println(ioe.getMessage());
    }

    try {
      this.controller.start();
      assertEquals(expected, this.out.toString());
    } catch (IOException ioe) {
      fail("Test failed: IO error\n" + ioe.getMessage());
    }
  }

  @Test
  public void testInflexiblePortfolioCall() {
    this.in = new StringReader("1\n5\n3\n");
    this.view = new PortfolioViewImpl(out);
    this.controller = new PortfolioControllerImpl(model, view, in, out);

    String expected = "";
    try {
      expected =
              new String(Files.readAllBytes(Paths.get(
                      "test/testfiles/controller_test_files/"
                              + "test_inflexible_portfolio_call.txt")));
    } catch (IOException ioe) {
      System.out.println(ioe.getMessage());
    }

    try {
      this.controller.start();
      assertEquals(expected, this.out.toString());
    } catch (IOException ioe) {
      fail("Test failed: IO error\n" + ioe.getMessage());
    }
  }

  @Test
  public void testFlexiblePortfolioCall() {
    this.in = new StringReader("2\n9\n3\n");
    this.view = new PortfolioViewImpl(out);
    this.controller = new PortfolioControllerImpl(model, view, in, out);

    String expected = "";
    try {
      expected =
              new String(Files.readAllBytes(Paths.get(
                      "test/testfiles/controller_test_files/test_flexible_portfolio_call.txt")));
    } catch (IOException ioe) {
      System.out.println(ioe.getMessage());
    }

    try {
      this.controller.start();
      assertEquals(expected, this.out.toString());
    } catch (IOException ioe) {
      fail("Test failed: IO error\n" + ioe.getMessage());
    }
  }

  @Test
  public void testFlexibleCreatePortfolio() {
    this.in = new StringReader("2\n3\n2\ntestCreatePortfolio\n9\n3\n");
    this.view = new PortfolioViewImpl(out);
    this.controller = new PortfolioControllerImpl(model, view, in, out);

    String expected = "";
    try {
      expected =
              new String(Files.readAllBytes(Paths.get(
                      "test/testfiles/controller_test_files/"
                              + "test_flexible_create_portfolio.txt")));
    } catch (IOException ioe) {
      System.out.println(ioe.getMessage());
    }

    try {
      this.controller.start();
      assertEquals(expected, this.out.toString());
    } catch (IOException ioe) {
      fail("Test failed: IO error\n" + ioe.getMessage());
    }
  }

  @Test
  public void testFlexibleLoadPortfolio() {
    this.in = new StringReader("2\n3\n1\nflexibleportfolios/testLoadPortfolio.txt\n9\n3\n");
    this.view = new PortfolioViewImpl(out);
    this.controller = new PortfolioControllerImpl(model, view, in, out);

    String expected = "";
    try {
      expected =
              new String(Files.readAllBytes(Paths.get(
                      "test/testfiles/controller_test_files/"
                              + "test_flexible_load_portfolio.txt")));
    } catch (IOException ioe) {
      System.out.println(ioe.getMessage());
    }

    try {
      this.controller.start();
      assertEquals(expected, this.out.toString());
    } catch (IOException ioe) {
      fail("Test failed: IO error\n" + ioe.getMessage());
    }
  }

  @Test
  public void testFlexibleLoadNonExistentPortfolio() {
    this.in = new StringReader("2\n3\n1\nflexibleportfolios/non_existent.txt\n9\n3\n");
    this.view = new PortfolioViewImpl(out);
    this.controller = new PortfolioControllerImpl(model, view, in, out);

    String expected = "";
    try {
      expected =
              new String(Files.readAllBytes(Paths.get(
                      "test/testfiles/controller_test_files/"
                              + "test_flexible_load_non_existent_portfolio"
                              + ".txt")));
    } catch (IOException ioe) {
      System.out.println(ioe.getMessage());
    }

    try {
      this.controller.start();
      assertEquals(expected, this.out.toString());
    } catch (IOException ioe) {
      fail("Test failed: IO error\n" + ioe.getMessage());
    }
  }

  @Test
  public void testFlexibleLoadInvalidPortfolio() {
    this.in = new StringReader("2\n3\n1\nflexibleportfolios/invalid_portfolio.txt\n9\n3\n");
    this.view = new PortfolioViewImpl(out);
    this.controller = new PortfolioControllerImpl(model, view, in, out);

    String expected = "";
    try {
      expected =
              new String(Files.readAllBytes(Paths.get(
                      "test/testfiles/controller_test_files/"
                              + "test_flexible_load_invalid_portfolio.txt")));
    } catch (IOException ioe) {
      System.out.println(ioe.getMessage());
    }

    try {
      this.controller.start();
      assertEquals(expected, this.out.toString());
    } catch (IOException ioe) {
      fail("Test failed: IO error\n" + ioe.getMessage());
    }
  }

  @Test
  public void testFlexibleBuyStock() {
    this.in = new StringReader("2\n3\n1\nflexibleportfolios/testCreatePortfolio"
            + ".txt\n5\nNFLX\n450\n2022-06-23\n13.23\n9\n3\n");
    this.view = new PortfolioViewImpl(out);
    this.controller = new PortfolioControllerImpl(model, view, in, out);

    String expected = "";
    try {
      expected =
              new String(Files.readAllBytes(Paths.get(
                      "test/testfiles/controller_test_files/test_flexible_buy_stock.txt")));
    } catch (IOException ioe) {
      System.out.println(ioe.getMessage());
    }

    try {
      this.controller.start();
      assertEquals(expected, this.out.toString());
    } catch (IOException ioe) {
      fail("Test failed: IO error\n" + ioe.getMessage());
    }
  }

  @Test
  public void testFlexibleSellStock() {
    this.in = new StringReader("2\n3\n1\nflexibleportfolios/testLoadPortfolio"
            + ".txt\n6\nMSFT\n7\n2022-11-13\n3.45\n9\n3\n");
    this.view = new PortfolioViewImpl(out);
    this.controller = new PortfolioControllerImpl(model, view, in, out);

    String expected = "";
    try {
      new String(Files.readAllBytes(Paths.get(
              "test/testfiles/controller_test_files/test_flexible_sell_stock.txt")));
    } catch (IOException ioe) {
      System.out.println(ioe.getMessage());
    }

    try {
      this.controller.start();
      assertEquals(expected, this.out.toString());
    } catch (IOException ioe) {
      fail("Test failed: IO error\n" + ioe.getMessage());
    }
  }

  @Test
  public void testFlexibleSellMoreStock() {
    this.in = new StringReader("2\n3\n1\nflexibleportfolios/testLoadPortfolio"
            + ".txt\n6\nMSFT\n100\n2022-11-14\n3.45\n9\n3\n");
    this.view = new PortfolioViewImpl(out);
    this.controller = new PortfolioControllerImpl(model, view, in, out);

    String expected = "";
    try {
      expected =
              new String(Files.readAllBytes(Paths.get(
                      "test/testfiles/controller_test_files/"
                              + "test_flexible_sell_more_stock.txt")));
    } catch (IOException ioe) {
      System.out.println(ioe.getMessage());
    }

    try {
      this.controller.start();
      assertEquals(expected, this.out.toString());
    } catch (IOException ioe) {
      fail("Test failed: IO error\n" + ioe.getMessage());
    }
  }

  @Test
  public void testFlexibleCostBasis() {
    this.in = new StringReader("2\n3\n1\nflexibleportfolios/testLoadPortfolio"
            + ".txt\n7\n2022-10-19\n9\n3\n");
    this.view = new PortfolioViewImpl(out);
    this.controller = new PortfolioControllerImpl(model, view, in, out);

    String expected = "";
    try {
      expected =
              new String(Files.readAllBytes(Paths.get(
                      "test/testfiles/controller_test_files/test_flexible_cost_basis.txt")));
    } catch (IOException ioe) {
      System.out.println(ioe.getMessage());
    }

    try {
      this.controller.start();
      assertEquals(expected, this.out.toString());
    } catch (IOException ioe) {
      fail("Test failed: IO error\n" + ioe.getMessage());
    }
  }

  @Test
  public void testFlexibleCompositionAtDate() {
    this.in = new StringReader("2\n3\n1\nflexibleportfolios/testLoadPortfolio"
            + ".txt\n8\n2022-10-19\n9\n3\n");
    this.view = new PortfolioViewImpl(out);
    this.controller = new PortfolioControllerImpl(model, view, in, out);

    String expected = "";
    try {
      expected =
              new String(Files.readAllBytes(Paths.get(
                      "test/testfiles/controller_test_files/test_flexible_comp_at_date.txt")));
    } catch (IOException ioe) {
      System.out.println(ioe.getMessage());
    }

    try {
      this.controller.start();
      assertEquals(expected, this.out.toString());
    } catch (IOException ioe) {
      fail("Test failed: IO error\n" + ioe.getMessage());
    }
  }

  @Test
  public void testFlexibleCompositionAtInvalidDate() {
    this.in = new StringReader("2\n3\n1\nflexibleportfolios/testLoadPortfolio"
            + ".txt\n8\n2000-345-51\n2022-10-19\n9\n3\n");
    this.view = new PortfolioViewImpl(out);
    this.controller = new PortfolioControllerImpl(model, view, in, out);

    String expected = "";
    try {
      expected =
              new String(Files.readAllBytes(Paths.get(
                      "test/testfiles/controller_test_files/"
                              + "test_flexible_comp_at_invalid_date.txt")));
    } catch (IOException ioe) {
      System.out.println(ioe.getMessage());
    }

    try {
      this.controller.start();
      assertEquals(expected, this.out.toString());
    } catch (IOException ioe) {
      fail("Test failed: IO error\n" + ioe.getMessage());
    }
  }

  @Test
  public void testFlexiblePerformanceGraph() {
    this.in = new StringReader("2\n3\n1\nflexibleportfolios/testLoadPortfolio"
            + ".txt\n4\n2022-05-01\n2022-11-01\n9\n3\n");
    this.view = new PortfolioViewImpl(out);
    this.controller = new PortfolioControllerImpl(model, view, in, out);

    String expected = "";
    try {
      expected =
              new String(Files.readAllBytes(Paths.get(
                      "test/testfiles/controller_test_files/"
                              + "test_flexible_performance_graph.txt")));
    } catch (IOException ioe) {
      System.out.println(ioe.getMessage());
    }

    try {
      this.controller.start();
      assertEquals(expected, this.out.toString());
    } catch (IOException ioe) {
      fail("Test failed: IO error\n" + ioe.getMessage());
    }
  }

  @Test
  public void testFlexiblePerformanceGraphInvalidDate() {
    this.in = new StringReader("2\n3\n1\nflexibleportfolios/testLoadPortfolio"
            + ".txt\n4\n2022-05-01\n2012-34-91\n2022-11-01\n9\n3\n");
    this.view = new PortfolioViewImpl(out);
    this.controller = new PortfolioControllerImpl(model, view, in, out);

    String expected = "";
    try {
      expected =
              new String(Files.readAllBytes(Paths.get(
                      "test/testfiles/controller_test_files/"
                              + "test_flexible_performance_graph_invalid_date"
                              + ".txt")));
    } catch (IOException ioe) {
      System.out.println(ioe.getMessage());
    }

    try {
      this.controller.start();
      assertEquals(expected, this.out.toString());
    } catch (IOException ioe) {
      fail("Test failed: IO error\n" + ioe.getMessage());
    }
  }

  @Test
  public void testFlexibleComposition() {
    this.in = new StringReader("2\n3\n1\nflexibleportfolios/testLoadPortfolio"
            + ".txt\n1\n9\n3\n");
    this.view = new PortfolioViewImpl(out);
    this.controller = new PortfolioControllerImpl(model, view, in, out);

    String expected = "";
    try {
      expected =
              new String(Files.readAllBytes(Paths.get(
                      "test/testfiles/controller_test_files/test_flexible_comp.txt")));
    } catch (IOException ioe) {
      System.out.println(ioe.getMessage());
    }

    try {
      this.controller.start();
      assertEquals(expected, this.out.toString());
    } catch (IOException ioe) {
      fail("Test failed: IO error\n" + ioe.getMessage());
    }
  }

  @Test
  public void testFlexibleGetValueAtDate() {
    this.in = new StringReader("2\n3\n1\nflexibleportfolios/testLoadPortfolio"
            + ".txt\n2\n2022-10-19\n9\n3\n");
    this.view = new PortfolioViewImpl(out);
    this.controller = new PortfolioControllerImpl(model, view, in, out);

    String expected = "";
    try {
      expected =
              new String(Files.readAllBytes(Paths.get(
                      "test/testfiles/controller_test_files/"
                              + "test_flexible_get_value_at_date.txt")));
    } catch (IOException ioe) {
      System.out.println(ioe.getMessage());
    }

    try {
      this.controller.start();
      assertEquals(expected, this.out.toString());
    } catch (IOException ioe) {
      fail("Test failed: IO error\n" + ioe.getMessage());
    }
  }

  @Test
  public void testFlexibleGetValueAtInvalidDate() {
    this.in = new StringReader("2\n3\n1\nflexibleportfolios/testLoadPortfolio"
            + ".txt\n2\n1005-564-99\n9\n3\n");
    this.view = new PortfolioViewImpl(out);
    this.controller = new PortfolioControllerImpl(model, view, in, out);

    String expected = "";
    try {
      expected =
              new String(Files.readAllBytes(Paths.get(
                      "test/testfiles/controller_test_files/"
                              + "test_flexible_get_value_at_invalid_date.txt")));
    } catch (IOException ioe) {
      System.out.println(ioe.getMessage());
    }

    try {
      this.controller.start();
      assertEquals(expected, this.out.toString());
    } catch (IOException ioe) {
      fail("Test failed: IO error\n" + ioe.getMessage());
    }
  }

  @Before
  public void setup() {
    this.in = new StringReader("");
    this.out = new StringBuilder();
  }

  @Test
  public void testControllerCreation() {
    this.in = new StringReader("");
    this.controller = new PortfolioControllerImpl(model, view, in, out);
    this.view = new PortfolioViewImpl(out);
    assertEquals("", this.out.toString());
  }

  @Test
  public void testCreatePortfolioInvalidOption() throws IOException {
    this.in = new StringReader("9\n4\n3");
    this.view = new PortfolioViewImpl(out);
    this.controller = new PortfolioControllerImpl(model, view, in, out);
    controller.start();
    String expected = "";
    try {
      expected =
              new String(Files.readAllBytes(Paths.get(
                      "test/testfiles/testCreatePortfolioInvalidOption.txt")));
    } catch (Exception e) {
      fail("not able to read file");
    }
    assertEquals(expected + "\n", out.toString());
  }

  @Test
  public void testCreateStocksCompositionPrice() {
    this.in = new StringReader(
            "1\n3\n2\nDuplicates\nGOOG\n12\nY\nAAPL\n26\nY\nGOOG\n27\nN\n1\n5\n3\n");
    this.view = new PortfolioViewImpl(out);
    this.controller = new PortfolioControllerImpl(model, view, in, out);

    String expected = "";
    try {
      expected =
              new String(Files.readAllBytes(Paths.get(
                      "test/testfiles/createStocksCompositionPrice.txt")));
    } catch (Exception e) {
      fail("not able to read file");
    }
    Portfolio portfolio;
    try {
      controller.start();
      assertEquals(expected, this.out.toString());
    } catch (IOException e) {
      fail("Test failed: IO Error");
    }
  }

  @Test
  public void testCreateStocksCompositionPriceAtWrongDate() {
    this.in = new StringReader(
            "1\n3\n2\nNew_Duplicates\nGOOG\n12\nY\nAAPL\n26\nY\nGOOG\n27\nN\n2\n"
                    + "2022-10-\n2022-10-12"
                    + "\n5\n3\n");
    this.view = new PortfolioViewImpl(out);
    this.controller = new PortfolioControllerImpl(model, view, in, out);

    String expected = "";
    try {
      expected =
              new String(Files.readAllBytes(Paths.get(
                      "test/testfiles/testCreateStocksCompositionPriceAtWrongDate.txt")));
    } catch (Exception e) {
      fail("not able to read file");
    }
    Portfolio portfolio;
    try {
      controller.start();
      assertEquals(expected, this.out.toString());
    } catch (IOException e) {
      fail("Test failed: IO Error");
    }
  }

  @Test
  public void testCreateStocksCompositionPriceAtFutureDate() {
    this.in = new StringReader(
            "1\n3\n2\nNew_Duplicates\nGOOG\n12\nY\nAAPL\n26\n"
                    + "Y\nGOOG\n27\nN\n2\n2024-10-10\n2022-10-12"
                    + "\n5\n3\n");
    this.view = new PortfolioViewImpl(out);
    this.controller = new PortfolioControllerImpl(model, view, in, out);

    String expected = "";
    try {
      expected =
              new String(Files.readAllBytes(Paths.get(
                      "test/testfiles/testCreateStocksCompositionPriceAtWrongDate.txt")));
    } catch (Exception e) {
      fail("not able to read file");
    }
    Portfolio portfolio;
    try {
      controller.start();
      assertEquals(expected, this.out.toString());
    } catch (IOException e) {
      fail("Test failed: IO Error");
    }
  }

  @Test
  public void testCreateStocksMultiplePortfolio() {
    this.in = new StringReader(
            "3\n2\nGOOG\n12\nY\nAAPL\n26\nN\nD\nN\n"
                    + "3\n2\nGOOG\n12\nY\nAAPL\n26\nN\nF\nN\n1\n2\n2014-10-10\n4\n");
    this.view = new PortfolioViewImpl(out);
    this.controller = new PortfolioControllerImpl(model, view, in, out);

    String expected = "";
    try {
      expected =
              new String(Files.readAllBytes(Paths.get(
                      "test/testfiles/testCreateStocksMultiplePortfolio.txt")));
    } catch (Exception e) {
      fail("not able to read file");
    }
    Portfolio portfolio;
    try {
      controller.start();
      assertEquals(expected, this.out.toString());
    } catch (IOException e) {
      fail("Test failed: IO Error");
    }
  }

  @Test
  public void testLoadMultiplePortfolio() {
    this.in = new StringReader(
            "1\n3\n1\nportfolios/test2stockscreation.txt\n3\n1\nportfolios/testportfolio"
                    + ".txt\n1\n5\n3\n");
    //"1\n3\n1\nportfolios/PortfolioWithIncorrectFormat.txt\n5\n20\n3\n"
    this.view = new PortfolioViewImpl(out);
    this.controller = new PortfolioControllerImpl(model, view, in, out);

    String expected = "";
    try {
      expected =
              new String(Files.readAllBytes(Paths.get(
                      "test/testfiles/testLoadMultiplePortfolio.txt")));
    } catch (Exception e) {
      fail("not able to read file");
    }
    Portfolio portfolio;
    try {
      controller.start();
      assertEquals(expected, this.out.toString());
    } catch (IOException e) {
      fail("Test failed: IO Error");
    }
  }

  @Test
  public void testLoadTypoInvalidOption() {
    this.in = new StringReader(
            "3\n1\nportfolios/mistake.txt\n3\n1\n"
                    + "portfolios/testportfolio.txt\n"
                    + "1\n2\n2014-10-10\n4\n");
    this.view = new PortfolioViewImpl(out);
    this.controller = new PortfolioControllerImpl(model, view, in, out);

    String expected = "";
    try {
      expected =
              new String(Files.readAllBytes(Paths.get(
                      "test/testfiles/testLoadTypoInvalidOption.txt")));
    } catch (Exception e) {
      fail("not able to read file");
    }
    Portfolio portfolio;
    try {
      controller.start();
      assertEquals(expected, this.out.toString());
    } catch (IOException e) {
      fail("Test failed: IO Error");
    }
  }

  @Test
  public void testTypoInvalidOption() {
    this.in = new StringReader(
            "1\n3\n1\nportfolios/PortfolioWithIncorrectFormat.txt\n5\n20\n3\n");
    this.view = new PortfolioViewImpl(out);
    this.controller = new PortfolioControllerImpl(model, view, in, out);

    String expected = "";
    try {
      expected =
              new String(Files.readAllBytes(Paths.get(
                      "test/testfiles/testTypoInvalidOption.txt")));
    } catch (Exception e) {
      fail("not able to read file");
    }
    Portfolio portfolio;
    try {
      controller.start();
      assertEquals(expected, this.out.toString());
    } catch (IOException e) {
      fail("Test failed: IO Error");
    }
  }

  @Test
  public void testPortfolioAlreadyExist() {
    this.in = new StringReader(
            "1\n3\n2\nCollege Fund\nGOOG\n12\nY\nAAPL\n26\nN\n5\n3\n");
    this.view = new PortfolioViewImpl(out);
    this.controller = new PortfolioControllerImpl(model, view, in, out);

    String expected = "";
    try {
      expected =
              new String(Files.readAllBytes(Paths.get(
                      "test/testfiles/testPortfolioAlreadyExist.txt")));
    } catch (Exception e) {
      fail("not able to read file");
    }
    Portfolio portfolio;
    try {
      controller.start();
      assertEquals(expected, this.out.toString());
    } catch (IOException e) {
      fail("Test failed: IO Error");
    }
  }

  @Test
  public void testPortfolioInvalidTicker() {
    this.in = new StringReader(
            "3\n2\nGOOG\n12\nY\nAAPLE\n26\n4\n");
    this.view = new PortfolioViewImpl(out);
    this.controller = new PortfolioControllerImpl(model, view, in, out);

    String expected = "";
    try {
      expected =
              new String(Files.readAllBytes(Paths.get(
                      "test/testfiles/testPortfolioInvalidTicker.txt")));
    } catch (Exception e) {
      fail("not able to read file");
    }
    Portfolio portfolio;
    try {
      controller.start();
      assertEquals(expected, this.out.toString());
    } catch (IOException e) {
      fail("Test failed: IO Error");
    }
  }

  @Test
  public void testPortfolioFractionalQuantity() {
    this.in = new StringReader(
            "1\n3\n2\nFractional_Portfolio\nGOOG\n1\nY\nAAPL\n26.3\n26\nN\n5\n3\n");
    this.view = new PortfolioViewImpl(out);
    this.controller = new PortfolioControllerImpl(model, view, in, out);

    String expected = "";
    try {
      expected =
              new String(Files.readAllBytes(Paths.get(
                      "test/testfiles/testPortfolioInvalidTicker.txt")));
    } catch (Exception e) {
      fail("not able to read file");
    }
    Portfolio portfolio;
    try {
      controller.start();
      assertEquals(expected, this.out.toString());
    } catch (IOException e) {
      fail("Test failed: IO Error");
    }
  }

  @Test
  public void testLoadInvalidFormatFile() {
    this.in = new StringReader(
            "1\n3\n1\nportfolios/PortfolioWithIncorrectFormat.txt\n5\n3\n");
    this.view = new PortfolioViewImpl(out);
    this.controller = new PortfolioControllerImpl(model, view, in, out);

    String expected = "";
    try {
      expected =
              new String(Files.readAllBytes(Paths.get(
                      "test/testfiles/testLoadInvalidFormatFile.txt")));
    } catch (Exception e) {
      fail("not able to read file");
    }
    Portfolio portfolio;
    try {
      controller.start();
      assertEquals(expected, this.out.toString());
    } catch (IOException e) {
      fail("Test failed: IO Error");
    }
  }

}
