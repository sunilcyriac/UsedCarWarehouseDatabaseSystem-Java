import java.util.*;
import java.util.Calendar;
/**
 * This is the CarList class. This issues the car search operations, adding and reading of files for the database system.
 * 
 * @author Sunil Cyriac 
 * @version 4.0 16/10/2017
 */
public class CarList
{
    private Car car;
    private ArrayList<Car> carPort;
    private Validation inputValidator;

    /**
     * Constructor for objects for the class CarList
     */
    public CarList()
    {
        carPort = new ArrayList<Car>();
        inputValidator = new Validation();
        car = new Car();
    }

    /**
     * This method is used for adding all the car information
     */
    public void addCar(String[] carInfo)
    {
        carPort.add(new Car(carInfo[0],Integer.parseInt(carInfo[1]),carInfo[2],carInfo[3],carInfo[4],carInfo[5],carInfo[6],Double.parseDouble(carInfo[7])));
    }

    /**
     * This method is used for deleting a car
     */
    public void carDelete(String carReg)
    {
        boolean correctInput = false;
        while (!correctInput)
        {
            System.out.println("Kindly confirm if you would like to go ahead with the deletion Yes/No");
            String choice = inputValidator.enteredAlphabet();
            if (choice.matches("([Yy])(([eE])?)(([sS])?)"))
            {
                int deleteIndex = getIndexFromCarReg(carReg);
                carPort.remove(deleteIndex);
                System.out.println("Notifying you that the car record with the registration number " + carReg + " is deleted");
                correctInput = true;
            }
            else if(choice.matches("([Nn])(([Oo])?)"))
            {
                System.out.println("Notifying you that the deletion of record with car registration number " + carReg + " is aborted as per your command");	
                correctInput = true;
            }
            else
                System.out.println("Invalid!!! Kindly enter Yes/No");	
        }
    }

    /**
     * This method is used for editng the colour of the car
     */
    public void carEditColour(String carReg)
    {
        for (Car carEntry : carPort)
        {
            if (carEntry.getCarReg().equalsIgnoreCase(carReg))
            {
                System.out.println("When prompted kindly enter the colours of the car to edit");
                carEntry.setColour(inputValidator.enteredColours());
            }
        }
    }

    /**
     * This method is used for editng the price of the car
     */
    public void carEditPrice(String carReg)
    {
        for (Car carEntry : carPort)
        {
            if (carEntry.getCarReg().equalsIgnoreCase(carReg))
            {
                System.out.println("When prompted kindly enter the price of the car to edit");
                carEntry.setPrice(Double.parseDouble(inputValidator.enteredPrice()));
            }
        }
    }

    /**
     * This method is used for searching a car with the age of the car
     */
    public void carSearchAge()
    {
        int carAge = inputValidator.enteredCarAge();
        ArrayList<String> carSearchResult = new ArrayList<String>();
        Calendar cal = Calendar.getInstance();
        int thisYear = cal.get(Calendar.YEAR);
        for (Car carEntry : carPort)
        {
            if ((thisYear - carEntry.getYearMade()) <= carAge)
                carSearchResult.add(carEntry.toString());
        }
        if (carSearchResult.size() == 0)
            System.out.println("There are no records for car with age equal to or less than" + carAge);
        else
        {
            System.out.println("Found " + carSearchResult.size() + " results");
            for (String result : carSearchResult)
            {
                System.out.println("\t\n" + result + "\n");	
            }
        }
    }  

    /**
     * This method is used for searching a car with respect to the colour of the car
     */
    public void carSearchColour()
    {
        System.out.println("Kindly enter the car colour for searching");
        String colour = inputValidator.enteredAlphabet();
        ArrayList<String> carSearchResult = new ArrayList<String>();
        for (Car carEntry : carPort)
        {
            for (int i = 0; i < 3 ; i++)
            {
                if (carEntry.getColour()[i].equalsIgnoreCase(colour))
                    carSearchResult.add(carEntry.toString());
            }
        }
        if (carSearchResult.size() == 0)
            System.out.println("Sorry we couldn't find any cars with the colour " + colour);
        else
        {
            System.out.println("Found " + carSearchResult.size() + " results");
            for (String result : carSearchResult)
            {
                System.out.println("\t\n" + result + "\n");	
            }
        }
    }

    /**
     * This method is used for searching a car with respect to the make and model of the car
     */
    public void carSearchMakeModel()
    {
        String carMake = inputValidator.enteredCarMake();
        String carModel = inputValidator.enteredCarModel();
        ArrayList<String> carSearchResult = new ArrayList<String>();
        if (carModel.equalsIgnoreCase("any"))
        {
            for (Car carEntry : carPort)
            {
                if (carEntry.getCarMake().equalsIgnoreCase(carMake))
                    carSearchResult.add(carEntry.toString());
            }
        }
        else		
        {
            for (Car carEntry : carPort)
            {
                if (carEntry.getCarMake().equalsIgnoreCase(carMake) && carEntry.getCarModel().equalsIgnoreCase(carModel))
                    carSearchResult.add(carEntry.toString());
            }
        }    
        if (carSearchResult.size() == 0)
            System.out.println("Sorry we couldn't find any cars of car make " + carMake + " and car model " + carModel );
        else
        {
            System.out.println("Found " + carSearchResult.size() + " results");
            for (String result : carSearchResult)
            {
                System.out.println("\t\n" + result + "\n");	
            }
        }
    }

    /**
     * This method is used for searching cars with respect to its price
     */
    public void carSearchPrice()
    {
        String[] priceRange = inputValidator.enteredCarPriceRange();
        ArrayList<String> carSearchResult = new ArrayList<String>();
        for (Car carEntry : carPort)
        {
            if (carEntry.getPrice() >=  Double.parseDouble(priceRange[0]) && carEntry.getPrice() <= Double.parseDouble(priceRange[1]))
            {
                carSearchResult.add(carEntry.toString());
            }
        }       
        if (carSearchResult.size() == 0)
            System.out.println("Sorry we couldn't find any cars between the price ranges " + Double.parseDouble(priceRange[0]) + " and " + Double.parseDouble(priceRange[1]));
        else
        {
            System.out.println("Found " + carSearchResult.size() + " results ");
            for (String result : carSearchResult)
            {
                System.out.println("\n" + result + "\n");	
            }
        }
    }

    /**
     * This method is used for searching cars with respect to its registration
     */
    public void carSearchReg()
    {
        String regCar = inputValidator.enteredCarReg();
        ArrayList<String> carSearchResult = new ArrayList<String>();
        for (Car carEntry : carPort)
        {
            if (carEntry.getCarReg().equalsIgnoreCase(regCar))
                carSearchResult.add(carEntry.toString());
        }       
        if (carSearchResult.size() == 0)
            System.out.println("Sorry we couldn't find any car with the registration number " + regCar);
        else
        {
            System.out.println("Found " + carSearchResult.size() + " result"); 
            for (String result : carSearchResult)
            {
                System.out.println( "\n" + result + " \n" );
            }
        }
    }

    /**
     * This method is used for checking the car registration number is different or not while adding a car
     * @return returns true and false as per the condition
     */
    public boolean carRegDifferentiator(String carReg)
    {
        for (Car carEntry : carPort)
        {
            if (carEntry.getCarReg().equalsIgnoreCase(carReg))
                return false;
        }
        return true;
    }

    /**
     * This method is used for getting all the cars in the list
     */
    public ArrayList<String> getAllCars()
    {
        ArrayList<String> allCars = new ArrayList<String>();
        for (Car carEntry : carPort)
        {
            allCars.add(carEntry.toCommaSeperatedString());
        }
        return allCars;
    }

    /**
     * This method is used for getting index of the cars
     *
     * 
     * @param  the registration of the car
     * @return  returns the index
     */
    public int getIndexFromCarReg(String carReg)
    {
        int index = 0;
        for (Car carEntry : carPort)
        {
            if (carEntry.getCarReg().equalsIgnoreCase(carReg))
                index = carPort.indexOf(carEntry);
        }
        return index;
    }
}
