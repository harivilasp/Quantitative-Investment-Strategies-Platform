package view;

import java.io.IOException;

/**
 * This class represents the view of program for interacting with user and implements the
 * {@code FlexiblePortfolioView} interface.
 */
public class FlexiblePortfolioViewImpl implements PortfolioView {

  /* The out writer instance. */
  private final Appendable out;

  /**
   * This constructor initializes the object of view, with appendable object.
   *
   * @param out represents the appendable type object
   */
  public FlexiblePortfolioViewImpl(Appendable out) {
    this.out = out;
  }

  @Override
  public void showPortfolioActions() throws IOException {
    this.out.append("\n");
    this.out.append("FLEXIBLE PORTFOLIO ACTIONS:\n");
    this.out.append("1. Show composition\n");
    this.out.append("2. Show value at specific date\n");
    this.out.append("3. Create/Load portfolio\n");
    this.out.append("4. Show performance over time\n");
    // Flexible portfolio operations
    this.out.append("5. Buy stocks\n");
    this.out.append("6. Sell stocks\n");
    this.out.append("7. Show cost basis\n");
    this.out.append("8. Show composition at date\n");
    this.out.append("9. Exit\n");
  }

  @Override
  public void showPortfolioAddMethods() throws IOException {
    this.out.append("\n");
    this.out.append("FLEXIBLE PORTFOLIO CREATION METHODS:\n");
    this.out.append("1. Load from existing filepath\n");
    this.out.append("2. Create portfolio manually\n");
    this.out.append("3. Exit\n");
  }

  @Override
  public void showText(String line) throws IOException {
    this.out.append(line).append("\n");
  }
}
