package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

/**
 * This class has method which are utility methods.
 */
public class Utils {

  /* The set of valid stock symbols. */
  public static Set<String> VALID_STOCKS;

  /**
   * Helper method that verifies whether the provided date is of a valid format or not.
   *
   * @param date the date to be verified
   * @return whether the date is of a valid format
   */
  public static boolean isValidDate(String date) {
    // Format the date parameter.
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    Date d1;
    try {
      d1 = format.parse(date);
    } catch (ParseException exception) {
      return false;
    }

    // Current date
    Date d2 = new Date();

    // If provided date is in the future.
    return d1.compareTo(d2) <= 0;
  }

  /**
   * Helper method to load the valid stocks from a local file.
   *
   * @throws IllegalArgumentException in case of an invalid valid stocks path
   */
  public static void loadValidStocks() throws IllegalArgumentException {
    // Instantiate the validStocks set.
    Utils.VALID_STOCKS = new HashSet<>();

    File file = new File("supported_stocks.csv");

    // If the no file exists at the filepath.
    if (!file.exists()) {
      throw new IllegalArgumentException(Constants.ERR_INVALID_FILE_PATH);
    }

    // File exists, read data.
    try {
      Scanner scanner = new Scanner(file);
      String line;
      scanner.nextLine();

      // Extract data from file.
      while (scanner.hasNextLine()) {
        line = scanner.nextLine();
        Utils.VALID_STOCKS.add(line.split(",")[0]);
      }
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException(Constants.ERR_INVALID_FILE_PATH);
    }
  }


  /**
   * Helper method to check whether the filepath is valid or not.
   *
   * @param filepath the filepath to be checked
   * @return whether the provided filepath is valid
   */
  public static boolean isValidPath(String filepath) {
    try {
      Paths.get(filepath);
    } catch (InvalidPathException ipe) {
      return false;
    }

    return true;
  }

}
