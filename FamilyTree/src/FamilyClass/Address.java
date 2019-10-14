/*
 * Title: Mr
 * Author: Joseph Sigar
 * Date: 25/05/18
 * File name: Family Tree
 * 
 * Purpose: To be able to store Members address in an object that will then 
 * be used by the client program to access the address of the family member.
 * 
 * Assumption:
 * 1. A class is required to store/handle the address details of a member
 * 2. StreetNumber can only contain integers
 * 3. StreetName can be any form of string literal 
 * 4. Post code can only contain integers
 * 5. suburb can be any form of string literal 
 * 6. The Street number can be anything from 0 to virtually infinity
 * 7. The post code cane be anything from 0 to virtually infinity
 * 
 * Condition of Input:
 * Street number and postcode can only be an integer
 * street name and suburb can be any combination of a string literal
 * 
 * Expected Output:
 * StreetNumber     -   integer
 * postcode         -   integer
 * street name      -   string
 * suburb           -   string
 */

package FamilyClass;

import java.io.Serializable;

/**
 * A Class that handles and store the address details of a member
 * 
 * @version 2.02 25 May 2018
 * @author Joseph Sigar
 */

public class Address implements Serializable, Cloneable{
    /**
     * A parameter to store the street number of the member.
     */
    private int streetNumber;
    
    /**
     * A parameter to store the street name of the member.
     */
    private String streetName;
    
    /**
     * A parameter to store the suburb of the member.
     */
    private String suburb;
    
    /**
     * A parameter to store the postcode of the member.
     */
    private int postCode;
    
    /**
     * An Address constructor. The constructor is called upon when the Address 
     * class is created. It initializes the parameters to the default values.
     */
    public Address (){
        // initializing the Address parameters to the default values
        this.streetNumber = 0;
        this.streetName = "";
        this.suburb = "";
        this.postCode = 0;
    }
    
    /**
     * An Address Initializer. The initializer is called upon when the Address 
     * class is created and the user specifies the given values.
     * @param streetNumber user-defined 
     * @param streetName user-defined
     * @param suburb user-defined
     * @param postCode user-defined
     * @throws UnknownOpException when a wrong input is entered by the user.
     */
    public Address (int streetNumber, String streetName, String suburb, 
            int postCode) throws UnknownOpException{
        // Constructor that initializes using user-defined parameters
        if (streetNumber < 0)
            throw new UnknownOpException("Invalid Street Number");
        if (postCode <= 0)
            throw new UnknownOpException("Invalid Postcode");
        
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.suburb = suburb;
        this.postCode = postCode;
    }
    
    /**
     * A method that returns whether an address object is empty or not.
     * @return true if the Address object is empty
     */
    public boolean isEmpty() {
        return this.streetNumber == 0 && this.streetName.isEmpty() &&
                this.suburb.isEmpty() && this.postCode == 0;
    }
    
    /**
     * A method that clears the values and sets them to program.
     * 
     */
    public void clear() {
        // restore the values to the default values
        this.streetNumber = 0;
        this.streetName = "";
        this.suburb = "";
        this.postCode = 0;
    }
    
    /**
     * A method that returns the street number of the address.
     * @return an integer indicating the street number
     */
    public int getStreetNumber () {
        return this.streetNumber;
    }
    
    /**
     * A method that allows the user to the street number of the address.
     * @param streetNumber user defined value that is used to set the address
     *                      value.
     * @throws UnknownOpException if the given number is less than 0
     */
    public void setStreetNumber (int streetNumber) throws UnknownOpException{
        if (streetNumber < 0)
            throw new UnknownOpException("Invalid Street Number");
        
        this.streetNumber = streetNumber;
    }
    
    /**
     * A method of returning the street name of the address.
     * 
     * @return a string literal indicating the street name.
     */
    public String getStreetName () {
        return this.streetName;
    }
    
    /**
     * A method that allows the user to set the street name of the address
     * @param streetName a value set by the user representing the street name
     */
    public void setStreetName (String streetName) {
        this.streetName = streetName;
    }
    
    /**
     *  A method that returns the suburb of an address.
     * @return a string literal representing the suburb of the address.
     */
    public String getSuburb () {
        return this.suburb;
    }
    
    /**
     * A method that allows the user to set the suburb of the address.
     * @param suburb a string literal denoting the suburb to be set.
     */
    public void setSuburb (String suburb) {
        this.suburb = suburb;
    }
    
    /**
     * A method of returning the post code to the user.
     * @return an integer value denoting the post code of the address.
     */
    public int getPostCode () {
        return this.postCode;
    }
    
    /**
     * A method that used to the set the post code of the address. 
     * @param postCode a user-defined value that is set by the user
     * @throws UnknownOpException if the value given is less than or equal to 0.
     */
    public void setPostCode (int postCode) throws UnknownOpException {
        if (postCode <= 0)
            throw new UnknownOpException("Invalid Postcode");
        this.postCode = postCode;
    }
    
    /**
     * A method that used when the user wants to make a copy of the Address.
     * @return a new address object with the same details copied from the 
     *          current address object
     * @throws CloneNotSupportedException
     */
    @Override
    protected Address clone () throws CloneNotSupportedException {
        Address address = (Address) super.clone();
        address.streetNumber = this.streetNumber;
        address.streetName = this.streetName;
        address.suburb = this.suburb;
        address.postCode = this.postCode;
        
        return address;
    }
    
}
