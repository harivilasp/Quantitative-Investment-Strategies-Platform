PROGRAM SETUP

Instructions for Setup:
1. Download the project zip and uncompress.
2. JAR file is located in res/. RUN JAR file inside res/.
3. Program doesn't not require any external library, standard JDK 11 will suffice.

Your present working directory should be like:
panjwaniha@Haris-MacBook-Air res % pwd
/Users/panjwaniha/IdeaProjects/assign-6-stocks-part-3/res

Your list directory should be like:
panjwaniha@Haris-MacBook-Air res % ls
DESIGN-README.txt		model_class_diagram.png
README.txt			portfolios
SETUP-README.txt		summary_class_diagram.png
assign-6-stocks-part-3.jar	supported_stocks.csv
controller_class_diagram.png	view_class_diagram.png
docs.zip

Terminal command (example): To run Jar file
panjwaniha@Haris-MacBook-Air res % java -jar assign-5-stocks-part-2.jar

NOTE: Currently, we are supporting high value stocks only list can be found in res/ folder.


/* INFLEXIBLE PORTFOLIO */
"""
Welcome!

Which one of the following type of portfolio would you like to work with?
1. Inflexible portfolio, or
2. Flexible portfolio
3. Exit

PORTFOLIO ACTIONS:
1. Show composition
2. Show value at specific date
3. Create/Load portfolio
4. Show performance over time
5. Exit

PORTFOLIO CREATION METHODS:
1. Load from existing filepath
2. Create portfolio manually
3. Exit
"""

Example 1: For portfolio with 3 stocks creation (creation from scratch)
[GOTO 1]
1. Initially there will be no portfolio so, we need to create portfolio first.
2. Type "3" to go to "Create/Load portfolio" and then type "2" for "Create portfolio manually".
3. Enter portfolio name: test3stockscreation
4. Enter stock name: AAPL after that Enter stock quantity: 1
5. Would you like to add more stocks? Y/N: Y
6. Again follow step 3, and similarly add 3 stocks
e.g.  Enter stock name: GOOG after that Enter stock quantity: 2
      Enter stock name: NFLX after that Enter stock quantity: 3
      Would you like to add more stocks? Y/N: N

7. Portfolio is created and saved as a text file. Refresh needed to view the file.
8. Since portfolio is created, we can
  8.1 Find composition of these stocks by option 1
  8.2 Value at specific date by option 2
    8.2.1. Enter date (yyyy-mm-dd): 2022-02-22
    8.2.2. Returns the Value on [2022-02-20] = $6472.5599999999995

Example 2: For portfolio with 3 stocks creation (loading from memory)
[GOTO 1]
1. Initially there will be no portfolio so, we need to load portfolio first.
2. Type "3" to go to "Create/Load portfolio" and then type "1" for "Load from existing filepath".
3. Choose the portfolio name from the existing list of saved portfolios.
4. Input filepath name on message "Enter portfolio filepath (portfolios/<portfolio_name>):"
5. Input: portfolios/test3stockscreation.txt
6. Portfolio is loaded. Now, we can
  6.1 Find composition of these stocks by option 1
  6.2 Value at specific date by option 2
    6.2.1. Enter date (yyyy-mm-dd): 2022-02-22
    6.2.2. Returns the Value on [2022-02-20] = $6472.5599999999995

Example 3: For portfolio with 2 stocks creation (creation from scratch)
[GOTO 1]
1. Type "3" to go to "Create/Load portfolio" and then type "2" for "Create portfolio manually"
2. Enter portfolio name: test2stockscreation
3. Enter stock name: META after that Enter stock quantity: 2
4. Would you like to add more stocks? Y/N: Y
5. Enter stock name: MSFT after that Enter stock quantity: 3
6. Would you like to add more stocks? Y/N: N
7. Now can perform portfolio actions like get composition, value on this portfolio.

Example 4: For portfolio with 2 stocks creation (loading from memory)
[GOTO 1]
1. Initially there will be no portfolio so, we need to load portfolio first.
2. Type "3" to go to "Create/Load portfolio" and then type "1" for "Load from existing filepath".
3. Choose the portfolio name from the existing list of saved portfolios.
4. Input filepath name on message "Enter portfolio filepath (portfolios/<portfolio_name>):"
5. Input: portfolios/test2stockscreation.txt
6. Now can perform portfolio actions like get composition, value on this portfolio.

Portfolio Actions:
1. Show composition
2. Show value at specific date
3. Create/Load portfolio
4. Exit


/* FLEXIBLE PORTFOLIO */
"""
Welcome!

Which one of the following type of portfolio would you like to work with?
1. Inflexible portfolio, or
2. Flexible portfolio
3. Exit

FLEXIBLE PORTFOLIO ACTIONS:
1. Show composition
2. Show value at specific date
3. Create/Load portfolio
4. Show performance over time
5. Buy stocks
6. Sell stocks
7. Show cost basis
8. Show composition at date
9. Exit

FLEXIBLE PORTFOLIO CREATION METHODS:
1. Load from existing filepath
2. Create portfolio manually
3. Exit
"""

Example 1: For portfolio with 3 stocks creation (creation from scratch)
[GOTO 2]
1. Initially there will be no portfolio so, we need to create portfolio first.
2. Type "3" to go to "Create/Load portfolio" and then type "2" for "Create portfolio manually".
3. Enter portfolio name: test3differentstocks.
4. Creates an empty portfolio in the "flexibleportfolios" folder.
5. BUY STOCK: Type "5" to go to "Buy stocks", repeat 3 times to buy different companies stocks.
  5.1. Enter stock name: NFLX, purchase quantity: 9, Purchase date: 2022-06-23, commission: 1.23
      Enter stock name:
      NFLX
      Enter stock quantity:
      9
      Enter transaction date (yyyy-mm-dd):
      2022-06-23
      Enter transaction commission:
      1.23

  5.2. Enter stock name: MSFT, purchase quantity: 4, Purchase date: 2021-09-23, commission: 3.33
  5.3. Enter stock name: AAPL, purchase quantity: 5, Purchase date: 2022-01-23, commission: 0.93

6. PERFORMANCE OVER TIME: type "4" to go to "Show performance over time"
  9.1. Enter the start and end date to view the performance of that portfolio.
    Enter start date (yyyy-mm-dd):
    2022-01-01
    Enter end date (yyyy-mm-dd):
    2022-09-09
    Performance of portfolio "test3differentstocks" from 2022-01-01 to 2022-09-09...

7. COST BASIS: Type "7" to go to "Show cost basis", repeat 2 times for different dates
   Enter date to show the amount invested till that point in that specific portfolio.
  7.1. Enter cost-basis date (yyyy-mm-dd):
       2021-12-31
       Cost-basis at [2021-12-31]: $1201.570000

  7.2. Enter cost-basis date (yyyy-mm-dd):
       2022-03-06
       Cost-basis at [2022-03-06]: $2014.550000

8. COMPOSITION AT DATE: Type "8" to go to "Show composition at date"
  8.1. Enter the date to show the composition of the portfolio at that specific date.
    Enter date (yyyy-mm-dd):
    2022-01-30
    Portfolio Composition:
    AAPL -> Quantity = 5.0
    MSFT -> Quantity = 4.0


Example 2: For portfolio with 3 stocks creation (loading from memory)
[GOTO 2]
1. Initially there will be no portfolio so, we need to create portfolio first.
2. Type "3" to go to "Create/Load portfolio" and then type "1" for "Load from existing filepath".
3. Choose the portfolio name from the existing list of saved portfolios.
4. Input filepath name on message "Enter portfolio filepath (flexibleportfolios/<portfolio_name>):"
5. Input: flexibleportfolios/testLoadPortfolio.txt. Portfolio is now loaded.
6. BUY STOCK:
  6.1. Enter stock name: NFLX, Purchase date: 2022-06-23, purchase quantity: 450, commission: 13.23
7. SELL STOCK:
  7.1. Enter stock name: NFLX, Sale date: 2022-07-14, Sale quantity: 23, commission: 2.45
8. COST BASIS:
  8.1. Enter date to show the amount invested till that point in that specific portfolio.
9. COMP. AT DATE:
  9.1. Enter the date to show the composition of the portfolio at that specific date.
10. PERF. OVER TIME:
  10.1. Enter the start and end date to view the performance of that portfolio which would be show
  either daily, weekly, monthly or yearly, based on the end date.

/* If we want to load from a file. */
File format for Portfolio File:
  Portfolio Name
  Stock Symbol;Quantity
  Stock Symbol;Quantity
  ...

File format for Flexible Portfolio File:
  Portfolio Name
  Stock Symbol;Buy/Sell Date;Quantity;Commission price
  Stock Symbol;Buy/Sell Date;Quantity;Commission price
  ...

