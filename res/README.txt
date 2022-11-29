FEATURES SUPPORTED/COMPLETED

1. Allow a user to create one or more portfolios with shares of one or more stock.
   Once created, shares cannot be added or removed from the portfolio (i.e., a portfolio cannot be
   overwritten once created with new stock information).

2. Persist a portfolio so that it can be saved and loaded (i.e. save to and retrieve from files)
  2.1. The user has an option whether to save the portfolio for future access. If not, they can
  still perform actions on the portfolio they have created.

  2.2. The portfolio is saved as a TEXT (.txt) file in a specific format.
    2.2.1. File format for Portfolio File:
           Portfolio Name
           Stock Symbol;Quantity
           Stock Symbol;Quantity
           ...

3. User can load from existing portfolio file or some other file in expected format.
  3.1. The portfolio to be loaded should be in the provided memory location for access and should be
   in the valid and approved format for it to be considered as a portfolio in the application.

4. Portfolios can be created by the following two methods:
  4.1. Either by entering the stock information manually and saving them inside a portfolio.
    4.1.1. For each stock, the user has to enter valid stock symbol and a non-fractional quantity.
    4.1.2. Additionally, the portfolio name is required.
    4.1.3. Stock names should only be an approved stock symbol by Standard & Poor's 500 (S&P 500)
    list.

  4.2. Or, by loading a portfolio from memory with the stock information in valid format.
    4.2.1. File format for Portfolio File:
           Portfolio Name
           Stock Symbol;Quantity
           Stock Symbol;Quantity
           ...

5. Examine the composition of a portfolio.
  5.1. Composition of a portfolio comprises each stock within the portfolio and their corresponding
  quantity.
  5.2. The user needs to create or load a portfolio in the program to examine its composition.

6. Examine the value of a portfolio at provided date.
  6.1. The price of the stock will be fetched from a web service. If the market is closed at the
    provided date, it returns the market close value. Otherwise, if the market is still open, it
    returns the most updated value of the stock.

  6.2. Future dates are considered as invalid and hence, not supported.
  6.3. Furthermore, if at a certain date, the market is closed, the market closing price at the
  last weekday would be considered.

7. Fractional amount of share is not supported, only whole numbers.
8. Have offered reasonable usability like typo causing, do not crash the program.
9. The user has the ability to terminate the program at any time during the execution.

FLEXIBLE PORTFOLIO

1. Loading from file has different format implemented.
   File format for Flexible Portfolio File:
     Portfolio Name
     Stock Symbol;Buy/Sell Date;Quantity;Commission price
     Stock Symbol;Buy/Sell Date;Quantity;Commission price
     ...
2. Purchase a specific number of shares of a specific stock on a specified date,
   and add them to the portfolio. We Auto-save after each transaction for persistence.
3. Sell a specific number of shares of a specific stock on a specified date from a given portfolio.
4. Determination of the cost basis (i.e. the total amount of money invested in a portfolio)
   by a specific date. This would include all the purchases made in that portfolio till that date.
5. Determination of value of a portfolio on a specific date (to be exact, the end of that day).
   The value for a portfolio before the date of its first purchase would be 0,
   since each stock in the portfolio now was purchased at a specific point in time.
6. Commission fees are usually a fixed fee per transaction. Added support to specify and
   incorporate commissions fees.
7. Composition at date has been supported, which gives composition of portfolio till that date.
8. Graph of performance has been drawn between start date and end date.
9. Web API Integration has been completed, which fetches the real time prices of stocks
   for date with design to support plan (for additional APIs in the future).

USER INTERFACE

The JAR file provides the options to run the program on a graphical user interface or the earlier
created text interface.

The text interface works as expected, showing users a list of operations to work on and taking input
 to select a certain operation. Each operation is associated with a further list of operations and
 input sequences to operate on the portfolios. The text interface provides the following list of
 operations to operate on either inflexible or flexible portoflios.

1. Show value at specific date
2. Create/Load portfolio
3. Show performance over time
4. Buy stocks
5. Sell stocks
6. Show cost basis
7. Show composition at date
8. Exit

The GUI option however, provides the user the below set of operations arranged in a grid layout for
the user to click.

1. Create portfolio
2. Buy/Sell stock
3. Composition at date
4. Check cost basis
5. Check value
6. Save portfolio to file
7. Read portfolio from file
8. Apply strategy
9. Invest amount
10. Performance of portfolio
11. Quit

The user can click on any of the options which opens a new panel for that operation inside the same
window, by replacing the old one. Each window has the associated view components to implement the
functionality correctly and provide a great user experience. Additionally, each panel is equipped
with message field to keep the user updated about the status of their operation and a home button to
 jump back to the home screen for further operations on the same portfolio.