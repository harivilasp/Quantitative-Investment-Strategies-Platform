MVC DESIGN PATTERN
Program is designed with the following Model-View-Controller Design.

SUMMARY
The current design considers only one user. However, is open to extension for multiple users. The
only change will be adding user class and separate folder will be maintained with all portfolios.

INFLEXIBLE PORTFOLIO

1. Stock consists as company symbol, number of shares separated by ';'.
2. Portfolio is essentially array of Stocks.
3. Portfolio are only saved if user wants, and can be created only once.
4. Portfolio can be created from file or can create new Portfolio by
   entering stocks.
5. Stock is as of now takes symbol, and quantity. Quantity can
   only be integer.
6. To avoid any invalid input, try and catch have been used to maintain continuity of program.
7. Currently supporting S&P 500 stocks only for simplification.

FLEXIBLE PORTFOLIO

1. Have used composition for code reuse from inflexible portfolio which maintains current
   composition of all stocks and their quantity.
2. Flexible portfolio also maintains list of transactions.
3. Each transaction has stock symbol, quantity, date of transaction, fixed transaction commission.
4. Negative quantity in transaction represents sell.
5. Positive quantity in transaction represents buy.
6. Since we are able to add or remove stocks, flexible portfolio is created just by portfolio name.
7. Loading from file is also provided but file format for flexible is different.
8. Flexible Portfolio are saved in different directory for distinction.
9. Saving of Flexible Portfolio overrides existing portfolio with same name.
10. Portfolio name is considered as unique identifier for portfolio.
11. Vertical Scaling of Graph:
    To determine optimal difference between 2 intervals,
    Case 1: Number of days difference between min first and last date < 7 and greater than 0
    		Representation -> Daily Frequency
    Case 2: Number of days difference between min first and last date < 70 (Approx 10 weeks) and greater than 7
    		Representation -> Weekly Frequency
    Case 3: Number of days difference between min first and last date < 366 (Around year) and greater than 70
    		Representation -> Monthly Frequency
    Case 4: Number of days difference between min first and last date > 366 (More than year)
    		It is better to calculate year difference between 2 dates in this case
    		jump from one date to next date = number of years difference/10
    		Representation -> Year added jump years

12. Horizontal scaling of graph:
    12.1. Calculated min and max value from all values
       [Relative Scale]
       12.1.1. Subtract min from all values and represent those values using
       12.1.2. For the relative scale, determine whether the maximum value is within twice the
       minimum
       value of the portfolio.

       12.1.3. If yes, then calculate the “jump factor” by dividing the difference between the max
       and min by 10 (max. allowed number of stars per row)
            diff = (max - min), jump factor = diff / 10.

       12.1.4. Compute the “relative scale” value by subtracting the jump factor from the min value.
            relative scale = (min - jump factor).

       12.1.5. Now, we compute the number of stars for each row by subtracting relative scale from
       the value at each row and dividing that by jump factor.

       111, 116, 128, 146

       Min = 111
       Max = 146
       Diff = 35
       Diff < min (35 < 111) need to go for relative
       Jump factor = 35/10
       Relative scale = min (111) - jump factor (3.5) = 107.5
       111 -> 1 star
       116 -> 2 star
       128 -> 5 star
       146 -> 10 star

    12.2. Hence, value will be converted into number of stars and view will print same.


DESIGN IMPROVEMENTS
1. Implemented Facade design pattern for model, which helps to reduce coupling between
   model and controller, as now only single model will be connecting with controller.
2. File Writer class is seperated from core model, which helps in changing file format easily.
3. Made API Interface and singleton class of Alphavantage API, which helps in supporting other
   API's in future if required.
4. Singleton class for API will have single instance for session and maintains cache to
   decrease calling API repeatedly.
5. Changed quantity from int to double, to inbuilt support for fractional share, although
   user is not allowed to buy or share fractional share.

DETAILED DESCRIPTION
1. MODEL
  1.1. The model will have the core functionalities.
  1.2. The Simulator interface is the starting point of simulation. Provides the following features:
    1.2.1. Load from existing portfolio file or some other file in expected format from memory.
    1.2.2. Create one or more portfolios with shares of one or more stock from scratch.
    Once created, shares cannot be added or removed from the portfolio (i.e., a portfolio cannot be
    overwritten once created with new stock information).

    1.2.3. Generating a new stock with a valid stock symbol (as per the S&P 500 list) and a
    non-fractional quantity.

  1.3. The Portfolio interface provides the overall functionalities for managing portfolio of
  stocks. Provides the following features:
    1.3.1. Provide the value of a portfolio at provided date. The price of the stock will be
    fetched from a web service and, it returns the market close value. If the market is
    still open, it returns the most updated value of the stock.

    1.3.2. Extract the portfolio name, provided by the user either by manually creating the stock or
     loading the stock from memory.

    1.3.3. Save a portfolio in memory, as a TEXT (.txt) file in the below format.
      File format for Portfolio File:
        Portfolio Name
        Stock Symbol;Quantity
        Stock Symbol;Quantity
        ...

    1.3.4. Extract the composition of a portfolio, which comprises each stock within the portfolio
    and their corresponding quantity in a specific format.

  1.4. The Stock model class represents stock of a company. It has a name/symbol and a
  corresponding quantity.
    1.4.1. This only accepts valid values of a stock as it's field values.
    1.4.2. Returns the value of the stock, fetched from a web service.

  1.5. The ApiUtil class helps to fetch data from web and provides results of the stock value for a
   particular date.
   1.5.1. Establishes a stream with the url to fetch data from the web service.
   1.5.2. Parses the fetched data for the most updated and valid value.

2. CONTROLLER
  2.1. The controller instructs view to display options.
  2.2. Processes input from the user and based on their choice, executes one of the methods
  defined inside the PortfolioController interface.
  2.3. In case of any invalid choices or inputs from the user, an appropriate message is displayed
  using the view.
  2.4. Interacts with the model to complete the actions displayed to the user.
  2.5. The controller comprises a "Features" interface to represent all the features supported by
  the variety of views. This improves the design by decoupling the controller and the views.

3. VIEW
  3.1. The View will have methods to display options to user in interactive way.
  3.2. Additionally, the view is responsible for showing the input messages to the user.
  3.3. The GUI however, has a home screen JFrame that presents a grid of ten operations available to
   the user to act on portfolios. The JFrame remains the same, however we replace the JPanels
   displayed to present different views. The GUIMainView controls the different portfolio operation
   JPanels by swapping out using: getContentPane().removeAll(), repaint(),
   getContentPane().add(<JPanel Instance>) and, pack().

   The initial state of the JFrame consists of the home screen JPanel. In case the user selects a
   panel from the provided grid, all current panels are removed from the frame, and then the panel
   to be displayed is added to the content pane and packed.

   3.4. The JHome view panel controls the action listener for all the subsequent JPanel's using the
   Features interface implemented by the Controller. Each action event is associated with the
   opening of that specific JPanel.

   3.5. The operation JPanels have the associated view components to implement the functionality
   correctly and provide a great user experience. Additionally, each panel is equipped with message
   field to keep the user updated about the status of their operation and a home button to jump back
    to the home screen for further operations on the same portfolio.