package utils;

/**
 * This class represents the set of constants associated with the model and controller packages.
 */
public class Constants {

  public static final String ERR_INVALID_FILE_PATH = "Error: Invalid file path!";
  public static final String ERR_OPEN_FILE = "Error: Problem encountered in opening file!";
  public static final String ERR_INVALID_FILE_FORMAT = "Error: Invalid file format!";
  public static final String ERR_INVALID_DATA = "Error: Invalid portfolio data!";
  public static final String ERR_INVALID_DATE = "Error: Invalid date (future) provided!";
  public static final String ERR_FILE_EXISTS = "Error: Portfolio already exists!";
  public static final String ERR_FILE_NOT_SAVED = "Error: Problem encountered saving portfolio!";
  public static final String ERR_INVALID_STOCK_DATA = "Error: Invalid stock data!";
  public static final String ERR_INS_STOCK_DATA = "Error: Insufficient stocks!";
  public static final String ERR_STOCK_NF = "Error: Stock not found!";
  public static final String ERR_API_TIMEOUT = "Error: Server timeout!";

  /* Controller */
  public static final String ERR_NO_PORTFOLIO_EXISTS = "No portfolio currently in memory! Please "
          + "create or load a portfolio by navigating to option 3 in the main menu";
  public static final String ERR_ENTER_NUM = "Error: Please enter a whole number.";
}
