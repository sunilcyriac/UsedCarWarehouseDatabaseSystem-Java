import java.util.*;
import java.util.Calendar;
import java.io.*;

/** This is validation class, this is used for all the validations for the user inputs in the program
 * 
 * @author Sunil Cyriac
 * @version 4.0 5-10-2017
 */

public class Validation
{   
    /**
     * This method checks the entered option is only a alphabet
     * 
     * @return returns the option
     */
    public String enteredAlphabet()
    {
        boolean correctInput = false;
        String option = "";
        while (!correctInput)
        {   option = getNonEmptyLine();
            if (option.matches("[a-zA-z ]+"))
            {
                correctInput = true;
                break;
            }
            System.out.println("Input invalid!! Your input should contain only alphabets from a to z or A to Z");
        } 
        return option.trim();
    }

    /**
     * This method is used for verifying whether the entered string have only alphabets and numbers
     * 
     * @param enteredString takes string values
     * @return returns true or false based on the input
     */
    public boolean enteredAlphaNumeric(String enteredString)
    {
        if (enteredString.matches("[a-zA-z0-9]+"))
            return true;
        else
            return false;
    }

    /**
     * This method is used for verifying if the entered age of the car for search is correct
     * 
     * @param    enteredString   is used to see if the input have only alphanumeric values
     * @return   returns the parameter string
     */
    public int enteredCarAge()
    {
        boolean correctInput = false;
        String carAge = "";
        while (!correctInput)
        {
            System.out.println("Kindly enter the carAge of the car for your search");
            carAge = getNonEmptyLine();
            if (carAge.matches("[0-9]{1,}"))
            {
                correctInput = true;
                break;
            }
            System.out.println ("Invalid entry!!! Age value expects only number");
        }
        return Integer.parseInt(carAge);
    }

    /**
     * This method verifies all the car attributes entered
     * 
     * @param car registraion number
     * @return car information
     */
    public String[] enteredCarAttributes(String carReg)
    {
        String[] carInfo = new String[8];
        carInfo[0] = carReg;
        carInfo[1] = enteredYear();
        String[] colours = enteredColours();
        for (int i = 0; i < 3; i++)
        {
            carInfo[(i+2)] = colours[i];
        }
        carInfo[5] = enteredCarMake();
        carInfo[6] = enteredCarModel();
        carInfo[7] = enteredPrice();
        return carInfo;
    }

    /**
     * This method is used for verifying the entered car make is in string format
     * 
     * @return returns the car maker name
     */
    public String enteredCarMake()
    {
        boolean correctInput = false;
        String carMake = "";
        while (!correctInput)
        {
            System.out.println("Please enter the car make");
            carMake = getNonEmptyLine();
            if (enteredAlphaNumeric(carMake))
            {
                correctInput = true;
                break;
            }
            System.out.println("Invalid entry!!! Kindly enter the car make correctly by giving a string value");
        }
        return carMake;
    }

    /**
     * This method is used for verifying the entered car model is in string format
     * 
     * @return returns the car model name
     */
    public String enteredCarModel()
    {
        boolean correctInput = false;
        String carModel = "";
        while (!correctInput)
        {
            System.out.println("Please enter the car model");
            carModel = getNonEmptyLine();
            if (enteredAlphaNumeric(carModel))
            {
                correctInput = true;
                break;
            }
            System.out.println("Invalid entry!!! Kindly enter the car model correctly by giving a string value");
        }
        return carModel;
    }

    /**
     * This method is used for verifying the entered car price range is in decimal number format
     * 
     * @return returns the car price range
     */
    public String[] enteredCarPriceRange()
    {
        boolean correctInput = false;
        String[] carPriceRange = new String[2];
        while (!correctInput)
        {
            System.out.println("Kindly enter the minimum price of the car");
            carPriceRange[0] = getNonEmptyLine();
            if (!carPriceRange[0].matches("([0-9]+)(\\.?)([0-9]*)"))
            {
                System.out.println("Invalid entry!!!. Car price takes only integer values");
                continue;
            }   
            do
            {
                System.out.println("Kindly enter the maximum price of the car");
                carPriceRange[1] = getNonEmptyLine();
                if (!carPriceRange[1].matches("([0-9]+)(\\.?)([0-9]*)"))
                {
                    System.out.println("Invalid entry!!!. Car price takes only integer values");
                }
            } while (!carPriceRange[1].matches("([0-9]+)(\\.?)([0-9]*)"));
            if (Double.parseDouble(carPriceRange[1]) > Double.parseDouble(carPriceRange[0]))
            {
                correctInput = true;
                break;
            }
            System.out.println("Sorry that cannot be processed!!! Kindly enter the minimum price first and it should be lower than the maximum price and then later enter the maximum price of the car");
        }
        return carPriceRange;
    }

    /**
     * This method is used to verify the car registration number is string or not
     * 
     * @return  returns the car registration number
     */
    public String enteredCarReg()
    {
        boolean correctInput = false;
        String carReg = "";
        while (!correctInput)
        {
            System.out.println("Kindly enter a car registration number");
            carReg = getNonEmptyLine();
            if (validateCarReg(carReg))
            {
                correctInput = true;
                break;
            }
            System.out.println("Invalid entry!!! Enter the valid car registration number with length not exceeding 6");
        }
        return carReg;
    }

    /**
     * This method returns the colours
     * 
     * @return returns colours 
     */
    public String[] enteredColours()
    {
        String[] colours = new String[3];
        Scanner input = new Scanner(System.in);
        int i = 0;
        while (i < 3)
        {
            System.out.println("Kindly input the colour "+ (i+1));
            colours[i] = input.nextLine().trim();
            if (validateColours(colours))
                i++;
        }
        return colours;
    }

    /**
     * This method is used to verify the car price is within the condition and datatype
     * 
     * @return  returns the car price
     */
    public String enteredPrice()
    {
        boolean correctInput = false;
        String price = "";
        while (!correctInput)
        {
            System.out.println("Kindly input the car price");
            price = getNonEmptyLine();
            if (validatePrice(price))
            {
                correctInput = true;
                break;
            }
            System.out.println("Invalid entry!!!!. Kindly enter the car price as number and should not exceed 10 million");
        }
        return price;
    }

    /**
     * This method is used for validating the entered car manufacturing year
     * 
     * @return returns the car manufacturing year
     */
    public String enteredYear()
    {
        boolean correctInput = false;
        String year = "";
        while (!correctInput)
        {
            System.out.println("Kindly enter the car manufacturing year");
            year = getNonEmptyLine();
            if (validateYear(year))
            {
                correctInput = true;
                break;
            }
        }
        return year;
    }

    /**
     * This method is used for verifying whether the entered value is an integer
     * 
     * @return returns 0 if any exception is catched and returns the character to numeric value if there is no exception thrown.
     */
    public int getInteger()
    {
        try
        {
            Scanner input = new Scanner(System.in);
            String option = "";
            option = getNonEmptyLine();
            return (Character.getNumericValue(option.charAt(0))); //takes only the first digit from the option number entered and will verify in the other class if its within the option list
        }
        catch (Exception e)
        {
            return 0;
        }
    }

    /**
     * This method is used for checking if the user have given an empty space as input
     * 
     * @return returns a trimed line
     */
    public String getNonEmptyLine()
    {
        Scanner input = new Scanner(System.in);
        String line = "";
        line = input.nextLine();
        while (line.trim().isEmpty())
        {
            System.out.println("Kindly input a non empty line as your input");
            line = input.nextLine();
        }
        return line.trim();
    }

    /**
     * This method is used for checking the car registration number is contains only alphanumeric values of maximum length 6
     * 
     * @param  car registraion number
     * @return returns true or false based on the validationns
     */
    public boolean validateCarReg(String carReg)
    {
        if (carReg.matches("[a-zA-z0-9]{1,6}"))
            return true;
        else
            return false;
    }

    /**
     * This method is used for validating colours
     * 
     * @param  colours in string array format
     * @return returns true or false based on the conditons
     */
    public boolean validateColours(String[] colours)
    {
        for (int i =0 ; i < colours.length ; i++)
        {
            try
            {
                if(i == 0)
                {
                    if ((colours[i].trim().isEmpty()) || !(colours[i].matches("[A-Za-z ]+")))
                    {
                        System.out.println("Invalid entry for colour " + (i+1));
                        System.out.println("Kindly note that entry for colour " + (i+1) + "should not contian space or special characters or integers");
                        return false;
                    }
                }
                else 
                {
                    if (!(colours[i].matches("([A-Za-z ]+)?")))
                    {
                        System.out.println("Invalid entry for colour " + ++i);
                        System.out.println("Kindly note that entry for colour " + i + " should not contain any special characters");
                        return false;
                    }
                    else 
                    {
                        if (colours[i].equals(colours[(i-1)])  && !(colours[i].isEmpty()))
                        {
                            System.out.println("colour" + (i+1) + " can't be the same as colour" + i);
                            return false;
                        }
                        else if (i==2)
                        {
                            if(colours[i].equals(colours[(i-2)]))
                            {
                                System.out.println("colour" + (i+1) + " can't be same as colour " + (i-1));
                                return false;
                            }
                        }
                    }
                }
            }
            catch (NullPointerException e)
            {
                //used to catch the null pointer exception
            }
        }
        return true;
    }

    /**
     * This method is used for verifying whether the entered option have is only integer and it is greater than 0 and less than 7
     * 
     * @param menu option
     * @return returns true or false based on the input
     */
    public boolean validateInputMenuOption(int menuOption)
    {
        if (menuOption > 0 && menuOption < 7)
            return true;
        else
            return false;
    }

    /**
     * This method is used to validate the price of the car
     * 
     * @param  price
     * @return true or false based on the condtions satisfied
     */
    public boolean validatePrice(String price)
    {
        if (price.matches("([0-9]+)(\\.?)([0-9]*)"))
            return true;
        else
            return false;
    }

    /**
     * This method is used for validating the car manufacturing year
     * 
     * @param  year
     * @return true or false based on the condtions satisfied
     */
    public boolean validateYear(String year)
    {
        Calendar cal = Calendar.getInstance();
        int thisYear = cal.get(Calendar.YEAR);
        if (!year.matches("[0-9]{4}"))
        {
            System.out.println("Invalid year entered!!! Enter the year in the format of YYYY");
            return false;
        }
        else
        {
            try
            {
                if (Integer.parseInt(year) > thisYear || Integer.parseInt(year) < 1950)
                {
                    System.out.println("Invalid year entered!!!. Enter the year only between 1950 and the current year");
                    return false;
                }
            }
            catch (NumberFormatException e)     
            {
                System.out.println("Invalid year entered!!! Enter the year in the format of YYYY");
                return false;
            }
        }
        return true;
    }
}