package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import model.Simulator;
import model.Stock;
import utils.Constants;
import utils.Utils;
import view.FlexiblePortfolioViewImpl;
import view.PortfolioView;
import view.PortfolioViewImpl;

/**
 * This is the portfolio controller implementation which handles flow of program and implementation
 * of {@code PortfolioController} interface.
 */
public class PortfolioControllerImpl implements PortfolioController {

  /* The model class instance. */
  private final Simulator model;

  /* The view class instance. */
  private PortfolioView view;

  /* The scanner class instance. */
  private final Scanner scanner;

  /* The Appendable out instance. */
  private final Appendable out;

  /**
   * This method constructs the model of controller.
   *
   * @param model represents the model of program
   * @param view  represents the view of program
   * @param in    represents the Readable Input Stream
   * @param out   represents the Appendable Output Stream
   */
  public PortfolioControllerImpl(Simulator model, PortfolioView view, Readable in, Appendable out) {
    // Create the model
    this.model = model;
    // Create the view
    this.view = view; // Inflexible portfolio
    // Instantiate the scanner
    this.scanner = new Scanner(in);
    // Instantiate the appendable out
    this.out = out;
  }

  @Override
  public void start() throws IOException {
    this.view.showText("Welcome!");

    int portfolioDecision;
    while (true) {
      /* Show user whether they'd like to work on a flexible or an inflexible portfolio. */
      // TODO: Concatenate
      this.view.showText(
          "\nWhich one of the following type of portfolio would you like to work with?"
      );

      this.view.showText("1. Inflexible portfolio, or");
      this.view.showText("2. Flexible portfolio");
      this.view.showText("3. Exit");

      // Get the portfolio selection number
      try {
        portfolioDecision = Integer.parseInt(getInput());

        // In case the user wants to exit the application.
        if (portfolioDecision == 3) {
          return;
        }

        // In case the decision was invalid, restart.
        if (portfolioDecision < 1 || portfolioDecision > 3) {
          this.view.showText("Invalid option!");
          continue;
        }

      } catch (NumberFormatException nfe) {
        this.view.showText(Constants.ERR_ENTER_NUM);
        continue;
      }

      // Assign the view to the correct reference, based on the portfolio decision.
      if (portfolioDecision == 1) {
        // Instantiate view with the inflexible portfolio view.
        this.view = new PortfolioViewImpl(this.out);
        // Reset the portfolio information
        this.model.resetPortfolios();
        // Control the portfolio.
        controlPortfolio();
      } else {
        // Instantiate view with the flexible portfolio view.
        this.view = new FlexiblePortfolioViewImpl(this.out);
        // Reset the portfolio information
        this.model.resetPortfolios();
        // Control the flexible portfolio.
        controlFlexiblePortfolio();
      }
    }
  }

  private void controlPortfolio() throws IOException {
    // Get the action decision number
    int actionDecision;

    while (true) {
      // Show the portfolio actions menu.
      this.view.showPortfolioActions();

      // Get the portfolio selection number
      try {
        actionDecision = Integer.parseInt(getInput());

        // In case the decision was invalid, restart.
        if (actionDecision < 1 || actionDecision > 5) {
          this.view.showText("Invalid option!");
          continue;
        }

        // In case the user wants to exit the application.
        if (actionDecision == 5) {
          return;
        }

      } catch (NumberFormatException nfe) {
        this.view.showText(Constants.ERR_ENTER_NUM);
        continue;
      }

      // Perform action based on the decision.
      performPortfolioAction(actionDecision, PortfolioType.INFLEXIBLE);
    }
  }

  private void controlFlexiblePortfolio() throws IOException {
    // Get the action decision number
    int actionDecision;

    while (true) {
      // Show the flexible portfolio actions menu.
      this.view.showPortfolioActions();

      // Get the portfolio selection number
      try {
        actionDecision = Integer.parseInt(getInput());

        // In case the decision was invalid, restart.
        if (actionDecision < 1 || actionDecision > 9) {
          this.view.showText("Invalid option!");
          continue;
        }

        // In case the user wants to exit the application.
        if (actionDecision == 9) {
          return;
        }

      } catch (NumberFormatException nfe) {
        this.view.showText(Constants.ERR_ENTER_NUM);
        continue;
      }

      // Perform action based on the decision.
      performFlexiblePortfolioAction(actionDecision, PortfolioType.FLEXIBLE);
    }
  }

  /**
   * Controls portfolio operations based on the selected option and the type of portfolio.
   *
   * @param option the selected option
   * @param type   the portfolio type
   * @throws IOException in case of user IO error
   */
  private void performPortfolioAction(int option, PortfolioType type) throws IOException {
    switch (option) {
      case 1: {
        // In case there is no portfolio currently in memory.
        if (!this.model.isPortfolioChosen()) {
          this.view.showText(Constants.ERR_NO_PORTFOLIO_EXISTS);
          return;
        }

        // Format the composition
        String composition = formatStockInformation(
            this.model.getComposition()
        );

        this.view.showText("Portfolio Composition:\n" + composition);
        break;
      }

      case 2: {
        // In case there is no portfolio currently in memory.
        if (!this.model.isPortfolioChosen()) {
          this.view.showText(Constants.ERR_NO_PORTFOLIO_EXISTS);
          return;
        }

        // Extract the date and the value.
        double value = 0.0;
        String date;
        while (true) {
          // Ask for the date to calculate the value at.
          this.view.showText("Enter date (yyyy-mm-dd):");
          date = getInput();

          try {
            value = this.model.getValue(date);

            // Valid value, break.
            break;
          } catch (IllegalArgumentException iae) {
            this.view.showText(iae.getMessage());
          } catch (RuntimeException re) {
            this.view.showText(re.getMessage());
          }
        }

        this.view.showText(String.format("Value on [%s] = $%s%n", date, value));
        break;
      }

      case 3: {
        controlPortfolioCreation(type);
        break;
      }

      case 4: {
        // In case there is no portfolio currently in memory.
        if (!this.model.isPortfolioChosen()) {
          this.view.showText(Constants.ERR_NO_PORTFOLIO_EXISTS);
          return;
        }

        controlPerformanceGraph();
        break;
      }

      default: {
        // Do nothing
      }
    }
  }

  private void performFlexiblePortfolioAction(int option, PortfolioType type) throws IOException {
    if (option <= 4) {
      performPortfolioAction(option, type);
      return;
    }

    switch (option) {
      case 5: {
        // In case there is no portfolio currently in memory.
        if (!this.model.isPortfolioChosen()) {
          this.view.showText(Constants.ERR_NO_PORTFOLIO_EXISTS);
          return;
        }

        controlPortfolioTransaction(TransactionType.BUY);
        break;
      }

      case 6: {
        // In case there is no portfolio currently in memory.
        if (!this.model.isPortfolioChosen()) {
          this.view.showText(Constants.ERR_NO_PORTFOLIO_EXISTS);
          return;
        }

        controlPortfolioTransaction(TransactionType.SELL);
        break;
      }

      case 7: {
        // In case there is no portfolio currently in memory.
        if (!this.model.isPortfolioChosen()) {
          this.view.showText(Constants.ERR_NO_PORTFOLIO_EXISTS);
          return;
        }

        controlCostBasis();
        break;
      }

      case 8: {
        // In case there is no portfolio currently in memory.
        if (!this.model.isPortfolioChosen()) {
          this.view.showText(Constants.ERR_NO_PORTFOLIO_EXISTS);
          return;
        }

        controlDateComposition();
        break;
      }

      default: {
        // Do nothing
      }
    }
  }

  /**
   * Performs the creation or loading of portfolios based on the user's choice and type of
   * portfolio.
   *
   * @param option the user's operation choice
   * @param type   the type of portfolio
   * @throws IOException in case of user IO error
   */
  private void createPortfolio(int option, PortfolioType type) throws IOException {
    if (option == 1) {
      // Show the available portfolio list to the user.
      String[] availablePortfolios = retrievePortfolios(type);

      // If there are available portfolios.
      if (availablePortfolios != null && availablePortfolios.length > 0) {
        StringBuilder portfolioMessage = new StringBuilder();
        portfolioMessage.append("\nPlease load from the following list of portfolios:\n");

        for (String portfolio : availablePortfolios) {
          portfolioMessage.append("  - ").append(portfolio).append("\n");
        }

        // Show the list of portfolio names.
        this.view.showText(portfolioMessage.toString());
      } else {
        this.view.showText("No saved portfolios! Please create a new one and save.\n");
      }

      if (type.equals(PortfolioType.INFLEXIBLE)) {
        this.view.showText("Enter portfolio filepath (portfolios/<portfolio_name>):");
      } else {
        this.view.showText("Enter portfolio filepath (flexibleportfolios/<portfolio_name>):");
      }

      String filepath = "";
      if (this.scanner.hasNextLine()) {
        filepath = getInput();
      }

      // Create the portfolio
      if (type.equals(PortfolioType.INFLEXIBLE)) {
        try {
          this.model.loadPortfolio(filepath);
        } catch (IllegalArgumentException iae) {
          this.view.showText(iae.getMessage());
          return;
        } catch (RuntimeException re) {
          this.view.showText(re.getMessage());
          return;
        }
      } else {
        try {
          this.model.loadFlexiblePortfolio(filepath);
        } catch (IllegalArgumentException iae) {
          this.view.showText(iae.getMessage());
          return;
        } catch (RuntimeException re) {
          this.view.showText(re.getMessage());
          return;
        }
      }
    } else if (option == 2) {
      // Input portfolio name
      String portfolioName;
      while (true) {
        this.view.showText("Enter portfolio name:");
        portfolioName = getInput();

        // Valid portfolio name.
        if (!portfolioName.isEmpty()) {
          break;
        }

        this.view.showText("Error: Invalid portfolio name!");
      }

      // In case of an Inflexible portfolio
      if (type.equals(PortfolioType.INFLEXIBLE)) {
        // Get the portfolio stock input. (throws IAE)
        List<Stock> stocks = getStocksInput();

        // Create the portfolio
        this.model.addPortfolio(portfolioName, stocks);
      } else {
        this.model.addFlexiblePortfolio(portfolioName);
      }

      // Auto-save the portfolio
      try {
        this.model.save();
        this.view.showText("Portfolio saved successfully!");
      } catch (IOException | RuntimeException exception) {
        this.view.showText(exception.getMessage());
        return;
      }
    }
  }

  /**
   * Controls performing purchase and sale of stocks based on the transaction type.
   *
   * @param type the transaction type
   * @throws IOException in case of user IO error
   */
  private void controlPortfolioTransaction(TransactionType type) throws IOException {
    // Buy/Sell stocks
    this.view.showText("\nEnter stock information\n");

    // Stock name
    String stockName;
    while (true) {
      this.view.showText("Enter stock name:");
      stockName = getInput();

      // Valid stock name.
      if (!stockName.isEmpty() && Utils.VALID_STOCKS.contains(stockName)) {
        break;
      }

      this.view.showText("Error: Invalid stock name!");
    }

    // Stock quantity
    int stockQty;
    while (true) {
      this.view.showText("Enter stock quantity:");

      try {
        stockQty = Integer.parseInt(getInput());

        if (stockQty < 0) {
          this.view.showText(Constants.ERR_ENTER_NUM);
          continue;
        }

        // Valid value
        break;
      } catch (NumberFormatException nfe) {
        this.view.showText(Constants.ERR_ENTER_NUM);
      }
    }

    // Stock transaction date
    String stockDate;
    while (true) {
      this.view.showText("Enter transaction date (yyyy-mm-dd):");
      stockDate = getInput();

      if (Utils.isValidDate(stockDate)) {
        break;
      }

      this.view.showText(Constants.ERR_INVALID_DATE);
    }

    // Stock transaction commission
    float stockComm;
    while (true) {
      this.view.showText("Enter transaction commission:");

      try {
        stockComm = Float.parseFloat(getInput());

        if (stockComm < 0) {
          this.view.showText("Error: Enter a correct commission value!");
          continue;
        }

        // Valid value
        break;
      } catch (NumberFormatException nfe) {
        this.view.showText("Error: Enter a correct commission value!");
      }
    }

    // Make stock transaction.
    try {
      if (type.equals(TransactionType.BUY)) {
        this.model.buyStock(stockName, stockQty, stockDate, stockComm);
      } else if (type.equals(TransactionType.SELL)) {
        this.model.sellStock(stockName, stockQty, stockDate, stockComm);
      }
    } catch (RuntimeException re) {
      this.view.showText(re.getMessage());
      return;
    }

    // Auto-save the portfolio
    try {
      this.model.save();
      this.view.showText("Portfolio saved successfully!");
    } catch (IOException | RuntimeException exception) {
      this.view.showText(exception.getMessage());
    }
  }

  /**
   * Controls the performance graph creation control by asking start and end dates. [CASE 4]
   *
   * @throws IOException in case of user IO error
   */
  private void controlPerformanceGraph() throws IOException {
    // Start date
    String startDate;
    while (true) {
      this.view.showText("Enter start date (yyyy-mm-dd):");
      startDate = getInput();

      if (Utils.isValidDate(startDate)) {
        break;
      }

      this.view.showText(Constants.ERR_INVALID_DATE);
    }

    // End date
    String endDate;
    while (true) {
      this.view.showText("Enter end date (yyyy-mm-dd):");
      endDate = getInput();

      if (Utils.isValidDate(endDate)) {
        break;
      }

      this.view.showText(Constants.ERR_INVALID_DATE);
    }

    // Extract the performance map
    Map<String, Integer> performanceMap = new HashMap<>();
    try {
      performanceMap = this.model.getPerformance(startDate, endDate);

    } catch (IllegalArgumentException iae) {
      this.view.showText(iae.getMessage());
      return;
    }

    // Build the result
    StringBuilder result = new StringBuilder();
    result.append("Performance of portfolio ")
        .append("\"").append(this.model.getName()).append("\"")
        .append(" from ")
        .append(startDate).append(" to ").append(endDate)
        .append("\n");

    Map.Entry<String, Integer> absScaleEntry = null;
    Map.Entry<String, Integer> relScaleEntry = null;
    for (Map.Entry<String, Integer> entry : performanceMap.entrySet()) {
      // If the entry is a scale
      if (entry.getKey().contains("Scale (relative)")) {
        relScaleEntry = entry;
        continue;
      }

      if (entry.getKey().contains("Scale (absolute)")) {
        absScaleEntry = entry;
        continue;
      }

      // Else, append to the result
      result.append(entry.getKey()).append(": ").append("*".repeat(entry.getValue()))
          .append("\n");
    }

    if (absScaleEntry != null) {
      result.append("\n").append(absScaleEntry.getKey()).append(": ")
          .append("$").append(absScaleEntry.getValue()).append("\n");
    }

    if (relScaleEntry != null) {
      result.append(relScaleEntry.getKey()).append(": ")
          .append("$").append(relScaleEntry.getValue()).append("\n");
    }

    this.view.showText(result.toString());
  }

  /**
   * Controls portfolio add operation based on the portfolio type. [CASE 3]
   *
   * @param type the portfolio type
   * @throws IOException in case of user IO error
   */
  private void controlPortfolioCreation(PortfolioType type) throws IOException {
    // Get the portfolio adding decision.
    int addDecision;

    while (true) {
      // Show the portfolio creation/loading methods.
      this.view.showPortfolioAddMethods();

      try {
        addDecision = Integer.parseInt(getInput());

        // In case the decision was invalid, restart.
        if (addDecision < 1 || addDecision > 3) {
          this.view.showText("Invalid option!");
          continue;
        }

        // In case the user wants to exit the sub-menu.
        if (addDecision == 3) {
          return;
        }

        // Valid value, break.
        break;
      } catch (NumberFormatException nfe) {
        this.view.showText(Constants.ERR_ENTER_NUM);
      }
    }

    // Create portfolio based on the decision.
    // Assign portfolio to the portfolio memory instance.
    try {
      if (type.equals(PortfolioType.INFLEXIBLE)) {
        createPortfolio(addDecision, PortfolioType.INFLEXIBLE);
      } else {
        createPortfolio(addDecision, PortfolioType.FLEXIBLE);
      }

    } catch (RuntimeException re) {
      this.view.showText(re.getMessage());
    }
  }

  /**
   * Controls the portfolio cost basis operation.
   *
   * @throws IOException in case of user IO error
   */
  private void controlCostBasis() throws IOException {
    // Input date
    String date;
    while (true) {
      this.view.showText("Enter cost-basis date (yyyy-mm-dd):");
      date = getInput();

      if (Utils.isValidDate(date)) {
        break;
      }

      this.view.showText(Constants.ERR_INVALID_DATE);
    }

    try {
      this.view.showText(
          String.format("Cost-basis at [%s]: $%f",
              date, this.model.getCostBasis(date))
      );
    } catch (Exception re) {
      this.view.showText(re.getMessage());
    }
  }

  /**
   * Controls the portfolio date composition operation.
   *
   * @throws IOException in case of user IO error
   */
  private void controlDateComposition() throws IOException {
    // Composition date
    String date;
    while (true) {
      this.view.showText("Enter date (yyyy-mm-dd):");
      date = getInput();

      if (Utils.isValidDate(date)) {
        break;
      }

      this.view.showText(Constants.ERR_INVALID_DATE);
    }

    List<Stock> composition;
    try {
      composition = this.model.getCompositionAtDate(date);
    } catch (RuntimeException re) {
      this.view.showText(re.getMessage());
      return;
    }

    // Format the composition
    String formattedComposition = formatStockInformation(composition);
    this.view.showText("Portfolio Composition:\n" + formattedComposition);
  }

  private List<Stock> getStocksInput() throws IOException {
    this.view.showText("Enter stock information\n");
    Map<String, Integer> stockMap = new HashMap<>();

    while (true) {
      Stock stock = getStockInput();
      stockMap.put(
          stock.getName(),
          stockMap.getOrDefault(stock.getName(), 0) + (int) stock.getQuantity()
      );

      this.view.showText("Would you like to add more stocks? Y/N:");
      String decision = getInput();

      if (decision.equalsIgnoreCase("N")) {
        break;
      }
    }

    // Convert HashMap to array.
    List<Stock> stockArr = new ArrayList<>();
    for (Map.Entry<String, Integer> entry : stockMap.entrySet()) {
      stockArr.add(
          this.model.generateStock(entry.getKey(), entry.getValue())  // #ignored: IAE. No need.
      );
    }

    return stockArr;
  }

  private Stock getStockInput() throws IOException {
    // Stock name
    String stockName;
    while (true) {
      this.view.showText("Enter stock name:");
      stockName = getInput();

      // Valid stock name.
      if (!stockName.isEmpty() && Utils.VALID_STOCKS.contains(stockName)) {
        break;
      }

      this.view.showText("Error: Invalid stock name!");
    }

    // Stock quantity
    int stockQty;
    while (true) {
      this.view.showText("Enter stock quantity:");

      try {
        stockQty = Integer.parseInt(getInput());

        if (stockQty < 0) {
          this.view.showText(Constants.ERR_ENTER_NUM);
          continue;
        }

        break;
      } catch (NumberFormatException nfe) {
        this.view.showText(Constants.ERR_ENTER_NUM);
      }
    }

    return this.model.generateStock(stockName, stockQty);   // #ignored: IAE. No need.
  }

  private String getInput() {
    // Extract the user input.
    return this.scanner.nextLine();
  }

  /* Helper method to format stock information. */
  private String formatStockInformation(List<Stock> composition) {
    // Iterate through the stock composition and append to the formatted string
    StringBuilder builder = new StringBuilder();

    for (Stock stock : composition) {
      builder
          .append(
              String.format(
                  "%s -> %s",
                  stock.getName(), "Quantity = " + stock.getQuantity()
              )
          )
          .append("\n");
    }

    return builder.toString();
  }

  /* Helper method to retrieve the list of all portfolios stored in memory (if any). */
  private String[] retrievePortfolios(PortfolioType type) {
    File rootDir;

    if (type.equals(PortfolioType.INFLEXIBLE)) {
      rootDir = new File("portfolios");
    } else {
      rootDir = new File("flexibleportfolios");
    }

    String[] results;

    if (rootDir.exists()) {
      File[] portfolioList = rootDir.listFiles();
      if (portfolioList != null) {
        results = new String[portfolioList.length];

        for (int i = 0; i < portfolioList.length; i++) {
          results[i] = portfolioList[i].getName();
        }

        return results;
      }
    }

    return null;
  }
}