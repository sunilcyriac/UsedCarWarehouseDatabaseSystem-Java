import java.util.*;
import java.io.*;
/**
 * This is the CarDatabaseManager class. All the user inputs will be taken through this class from console and respective validations will be done in another classes
 * 
 * @author Sunil Cyriac
 * @version 4.0 16/10/2017
 */
public class CarDatabaseManager
{
    private int option;
    private int optionForSearch;
    private CarList carWarehouseList;
    String fileName;
    private Validation inputValidator;

    /**
     * Constructor for objects of CarDatabaseManager class
     */
    public CarDatabaseManager()
    {
        fileName = "usedcars.txt";
        carWarehouseList = new CarList();
        option = 0; //This is used for continuing the display menu
        optionForSearch = 0;
        inputValidator = new Validation();
    }

     /**
     * This method is used for the adding cars to database 
     */
    public void carAddToDatabase()
    {
        String carReg = inputValidator.enteredCarReg();
        if (carWarehouseList.carRegDifferentiator(carReg))
            carWarehouseList.addCar(inputValidator.enteredCarAttributes(carReg));
        else
            System.out.println("Car registration number already exist in the database");
    }
    
    /**
     * This method is used to delete car from the database
     */
    public void carDeleteFromDatabase()
    {
        System.out.println("Kindly enter the following information about the car you want to delete");
        String carReg = inputValidator.enteredCarReg();
        if (carWarehouseList.carRegDifferentiator(carReg))
            System.out.println("Entered car registration number is unavailable!!! Kindly try again with the correct registration number");  
        else
            carWarehouseList.carDelete(carReg);
    }

    /**
     * This method is used for editing the information about the car
     */
    public void carEditInDatabase()
    {
        System.out.println("Kindly enter the following information about the car you would like to edit");
        String carReg = inputValidator.enteredCarReg();
        int optionEdit = 0;
        if (carWarehouseList.carRegDifferentiator(carReg))
            System.out.println("Entered car registration number is unavailable!!! Kindly try again with the correct registration number");  
        else
        {   
            while (optionEdit != 3)
            {
                carEditMenu();
                optionEdit = inputValidator.getInteger();
                switch (optionEdit)
                {
                    case 1:
                           carWarehouseList.carEditColour(carReg);
                           break;
                    case 2:
                           carWarehouseList.carEditPrice(carReg);
                           break;
                    case 3:
                           System.out.println("Go back to the main menu");
                           break;
                    default:
                            System.out.println("Invalid input!!!. Kindly enter the option number correctly again");
                            optionEdit = 0;
                }
            }   
        }
    }

    /**
     * This method is used for displaying the car edit menu
     */
    public void carEditMenu()
    {
        System.out.println("USED CAR WAREHOUSE DATABASE CAR EDIT MENU");
        System.out.println("***********************************************");
        System.out.println("Please select an option from the below list");
        System.out.println("(1) Edit Colours " );
        System.out.println("(2) Edit Price" );
        System.out.println("(3) Go back To Main Menu" );
    }
    
    /**
     * This method is used for displaying the car search menu
     */
    public void carSearchMenu()
    {
        optionForSearch = 0 ;
        while (optionForSearch != 6)
        {
            System.out.println("USED CAR WAREHOUSE DATABASE SYSTEM SEARCH");
            System.out.println("***********************************************");
            System.out.println("(1) By Registration Number " );
            System.out.println("(2) By Car Make and Model" );
            System.out.println("(3) By Age");
            System.out.println("(4) By Price [Min and Max]");
            System.out.println("(5) By Colour");
            System.out.println("(6) Back to main menu");
            System.out.println("\nPlease select an option from the above list");
            operationsForSearch();
        }
    }
    
     /**
     * This method is used for database operations of the car like search, add, edit and delete
     */
    public void dbOperations()
    {
        option = inputValidator.getInteger();
        switch(option)
        {
            case 1:
                 carSearchMenu();
                 break;
            case 2:
                  carAddToDatabase();
                  break;
            case 3:
                   carDeleteFromDatabase();
                   break;
            case 4:
                   carEditInDatabase();
                   break;
            case 5:
                   quitProgram();
                   break;
            default:
                   System.out.println("Invalid input!!!. Kindly enter the option number correctly again");
                   option = 0;
         }
    }

    /**
     * This method is used for displaying the main menu of the program
     */
    public void displayMenu()
    {
        while (option != 5)
        {
            System.out.println("USED CAR WAREHOUSE DATABASE SYSTEM");
            System.out.println("********************************************");
            System.out.println("(1) Search Cars " );
            System.out.println("(2) Add Cars " );
            System.out.println("(3) Delete Car");
            System.out.println("(4) Edit Car");
            System.out.println("(5) Exit System");
            System.out.println("Please choose an option from the above list to continue");
            dbOperations();
        }
    }
    
    /**
     * This method is used for the operations for search options
     */
    public void operationsForSearch()
    {
        optionForSearch = inputValidator.getInteger();
        switch (optionForSearch)
        {
            case 1:
                   carWarehouseList.carSearchReg();
                   break;
            case 2:
                   carWarehouseList.carSearchMakeModel();
                   break;
            case 3:
                   carWarehouseList.carSearchAge();
                   break;
            case 4:
                   carWarehouseList.carSearchPrice();
                   break;
            case 5:
                   carWarehouseList.carSearchColour();
                   break;
            case 6:
                   System.out.println("Jumping to previous main menu\n");
                   break;
            default:
                   System.out.println("Entered option is Invalid!!! Please enter the corrrect option again");
                   option = 0;
        }
    }
    
    /**
     * This method is used for the starting of all the operations of the used car datawarehouse system
     */
    public void startDatabaseManager()
    {
        try
        {
            FileReader inputFile = new FileReader(fileName);
            Scanner parser = new Scanner(inputFile);
            String [] carInfo = new String[8];
            while (parser.hasNext())
            {
                carInfo = parser.nextLine().split(",");
                carWarehouseList.addCar(carInfo);
            }
            inputFile.close();
            displayMenu();
        }
        catch (FileNotFoundException e)
        {
            System.out.println(fileName + " Not found " );
        }
        catch (IOException e)
        {   
            System.out.println("Error reported!! File was not loaded, application launch failed!!");
        }
    }

    /**
     * This method is used for the operations to quit a program by saving all the data to the file ie database
     */
    public void quitProgram()
    {
        try
        {
            PrintWriter outputFile = new PrintWriter(fileName);
            ArrayList<String> allCars = new ArrayList<String>();
            allCars = carWarehouseList.getAllCars();
            for (String carInfo: allCars)
            {
                outputFile.println(carInfo);
            }
            outputFile.close();
        }
        catch (IOException e)
        {
            System.out.println("Error reported while saving!!! Kindly try again");
        }
        catch (Exception e)
        {
            System.out.println("Error!!! Kindly try again");
        }
        System.exit(0);
    }
    
}
