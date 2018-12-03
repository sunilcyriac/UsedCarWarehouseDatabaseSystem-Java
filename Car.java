import java.util.*;
import java.util.Calendar;
/**
 * This is a Car class, this class saves the information about the car including car registration, year made, colours, make, model and price
 * 
 * @author Sunil Cyriac
 * @version 4.0 15-10-2017
 */
public class Car
{
    private String carReg;
    private int yearMade;
    private String[] colour;
    private String carMake;
    private String carModel;
    private double price;
    private Validation inputValidator;

    /**
     * Constructor for creating a car with no  attributes defined
     */
    public Car()
    {
        this.colour = new String[3];
        this.colour[0] = "";
        this.colour[1] = "";
        this.colour[2] = "";
        this.carMake = "";
        this.carModel = "";
        this.carReg = "";
        inputValidator = new Validation();
    }

    /**
     * Constructor for creating a car by defining all the attributes
     */
    public Car(String carReg,int yearMade,String  colour1, String colour2, String colour3 ,String carMake,String carModel,double price)
    {
        this.carReg = carReg;
        this.yearMade = yearMade;
        this.colour = new String[3];
        this.colour[0] = colour1;
        this.colour[1] = colour2;
        this.colour[2] = colour3;
        this.carMake = carMake;
        this.carModel = carModel;
        this.price = price;
        inputValidator = new Validation();
    }

    /**
     * This method returns the car maker name
     * 
     * @return car make 
     */
    public String getCarMake()
    {
        return carMake;
    }

    /**
     * This method returns the car model
     * 
     * @return car model
     */
    public String getCarModel()
    {
        return carModel;
    }

    /**
     * This method returns the registration of the car
     * 
     * @return registration of the car
     */
    public String getCarReg()
    {
        return carReg;
    }

    /**
     * This method returns the colours of the car
     * 
     * @return all the colours of the car
     */
    public String[] getColour()
    {
        return colour;
    }

    /**
     * This method returns the price of the car
     * 
     * @return price of the car
     */
    public double getPrice()
    {
        return price;
    }

    /**
     * This method returns the manufacturing year of the car
     * 
     * @return car's manufacture year 
     */
    public int getYearMade()
    {
        return yearMade;
    }

    /**
     * This method sets the car maker name
     * 
     * @param carMake
     */
    public void setCarMake(String carMake)
    {
        if (inputValidator.enteredAlphaNumeric(carMake))
            this.carMake = carMake;
        else
            System.out.println("Invalid Entry!!! Kindly input only alphanumeric characters");
    }

    /**
     * This method sets the car model name
     * 
     * @param carModel
     */
    public void setCarModel(String carModel)
    {
        if (inputValidator.enteredAlphaNumeric(carModel))
            this.carModel = carModel;
        else
            System.out.println("Invalid Entry!!! Kindly input only alphanumeric characters");
    }

    /**
     * This method set the car registration
     * 
     * @param carReg
     */
    public void setCarReg(String carReg)
    {
        if (inputValidator.validateCarReg(carReg))  
            this.carReg = carReg;
        else
        {
            System.out.println("Car Registration number entered is invalid!!");
            System.out.println("Kindly make sure the entered car registration number length doesn't exceeds 6");
        } 
    }

    /**
     * This method sets the car colours
     * 
     * @param car colours in string format
     */
    public void setColour(String[] colour)
    {
        if (inputValidator.validateColours(colour))
        {
            for (int i = 0; i < colour.length ; i++)
                this.colour[i] = colour[i];
        }
    }

    /**
     * This method sets the car price
     * 
     * @param price of the car
     */
    public void setPrice(double price)
    {  
        try
        {
            if(price <= 100 || 
            price > 1000000)
                System.out.println("Sorry we manage cars only with minimum price of 100 and maximum 1000000");
            else
                this.price = price;
        }
        catch (Exception e)
        {
            System.out.println("You have entered a number that is invalid. Please re enter a valid number");
        }
    }

    /**
     * This method sets the car manufacture year
     * 
     * @return yearMade
     */
    public void setYearMade(int yearMade)
    {
        Calendar cal = Calendar.getInstance();//using calendar class for getting the system year everytime hence can avoid the manual entry
        int thisYear = cal.get(Calendar.YEAR);
        if(yearMade <= thisYear || yearMade >= 1950)
            this.yearMade = yearMade;
        else         
            System.out.println("Enter a valid year between 1950 and the current year");
    }

    /**
     * This method returns the price of the car
     * 
     * @return  returns the entire attribue values of the car with comma separation
     */
    public String toCommaSeperatedString()
    {
        return (this.getCarReg() + "," + String.valueOf(this.getYearMade()) + "," + this.getColour()[0] + "," + this.getColour()[1] + "," + this.getColour()[2] + "," + this.getCarMake() 
            + "," + this.getCarModel() + "," + String.valueOf(this.getPrice()));
    }

    /**
     * This method is used for creating a method of toString
     * 
     * @return returns the entire attribute values of car in a string format
     */
    public String toString()
    {
        Calendar cal = Calendar.getInstance();
        int thisYear = cal.get(Calendar.YEAR);
        return (" Car Registration number : " + this.getCarReg()   + " \n Car Make : " + this.getCarMake()  + " \n Car Model : " + this.getCarModel()  + "\n Car Colour1 : " + 
                this.getColour()[0]  + "\n Car Colour2 : " +  this.getColour()[1]  + "\n Car Colour3 : " +  this.getColour()[2] + "\n Year made : " + (this.getYearMade())  + "\n Car Age : " + 
                (thisYear-this.getYearMade())  + "\n Price : " + this.getPrice());
    }
}
