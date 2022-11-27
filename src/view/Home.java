package view;

import java.awt.*;

import javax.swing.*;

import controller.Features;

/**
 * This class represents the main view.
 */
public class Home extends JPanel implements PanelView{
  private JButton createPortfolio;
  private JButton checkCost;
  private JButton checkValue;
  private JButton buySellStock;
  private JButton savePortfolioToFile;
  private JButton readPortfolioFromFile;
  private JButton applyStrategy;
  private JButton quit;

  public Home() {
    this.setPreferredSize(new Dimension(450, 500));
    setVisible(true);

    GridLayout layout = new GridLayout(4, 2);
    JPanel panel;
    panel = new JPanel();
    panel.setLayout(layout);

    createPortfolio = new JButton("Create Portfolio");
    buySellStock = new JButton("Buy/Sell Stock");
    quit = new JButton("Quit");
    checkCost = new JButton("Check Cost Basis");
    checkValue = new JButton("Check Value");
    savePortfolioToFile = new JButton("Save Portfolio To File");
    readPortfolioFromFile = new JButton("Read Portfolio From File");
    applyStrategy = new JButton("Apply Strategy");

    createPortfolio.setActionCommand("createPortfolio");
    buySellStock.setActionCommand("buySellStockChooseAWay");
    checkCost.setActionCommand("checkCostBasis");
    checkValue.setActionCommand("checkValue");
    savePortfolioToFile.setActionCommand("savePortfolioToFile");
    readPortfolioFromFile.setActionCommand("readPortfolioFromFile");
    quit.setActionCommand("quit");
    applyStrategy.setActionCommand("apply strategy");

    panel.add(createPortfolio);
    panel.add(buySellStock);
    panel.add(checkCost);
    panel.add(checkValue);
    panel.add(savePortfolioToFile);
    panel.add(readPortfolioFromFile);
    panel.add(applyStrategy);
    panel.add(quit);

    this.add(panel);
  }

  public void addActionListener(Features features) {
//    createPortfolio.addActionListener(listener);
//    showAllPortfolio.addActionListener(listener);
//    buyStock.addActionListener(listener);
//    checkCost.addActionListener(listener);
//    checkValue.addActionListener(listener);
//    getPortfolio.addActionListener(listener);
//    setCommissionFee.addActionListener(listener);
//    savePortfolioToFile.addActionListener(listener);
//    readPortfolioFromFile.addActionListener(listener);
//    setAPI.addActionListener(listener);
//    add.addActionListener(listener);
//    createStrategy.addActionListener(listener);
//    applyStrategy.addActionListener(listener);
//    readStrategy.addActionListener(listener);
//    saveStrategy.addActionListener(listener);
    createPortfolio.addActionListener(evt -> {
      features.showCreatePortfolio();
    });
    buySellStock.addActionListener(evt -> {
      features.showHome();
    });
    quit.addActionListener(evt -> features.exitProgram());
  }

  /**
   * Reset the focus on the appropriate part of the view.
   */
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

}