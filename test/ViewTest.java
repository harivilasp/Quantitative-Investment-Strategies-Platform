import org.junit.Test;

import java.io.IOException;

import view.PortfolioView;
import view.PortfolioViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the view of program.
 */
public class ViewTest {

  private Appendable out;
  private PortfolioView view;

  /**
   * This method constructs and initializes the view tests.
   */
  public ViewTest() {
    out = new StringBuffer();
  }

  @Test
  public void testShowPortfolioActions() throws IOException {
    view = new PortfolioViewImpl(out);
    view.showPortfolioActions();
    assertEquals("\n"
            + "PORTFOLIO ACTIONS:\n"
            + "1. Show composition\n"
            + "2. Show value at specific date\n"
            + "3. Create/Load portfolio\n"
            + "4. Show performance over time\n"
            + "5. Exit\n", out.toString());
  }

  @Test
  public void testShowPortfolioAddMethods() throws IOException {
    view = new PortfolioViewImpl(out);
    view.showPortfolioAddMethods();
    assertEquals("\n"
            + "PORTFOLIO CREATION METHODS:\n"
            + "1. Load from existing filepath\n"
            + "2. Create portfolio manually\n"
            + "3. Exit\n", out.toString());
  }

  @Test
  public void testShowText() throws IOException {
    view = new PortfolioViewImpl(out);
    view.showText("Testing printing");
    assertEquals("Testing printing\n", out.toString());
  }
}
