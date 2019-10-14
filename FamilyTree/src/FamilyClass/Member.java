/*
 * Title: Mr
 * Author: Joseph Sigar
 * Date: 25/05/18
 * File name: Family Tree
 * 
 * Purpose: To be able to store Members details in an object that will then 
 * be used by the client program to access the details of the family member.
 * 
 * Assumption:
 * 1. A class is required to store/handle the address details of a member
 * 2. StreetNumber can only contain integers
 * 3. StreetName can be any form of string literal 
 * 4. Post code can only contain integers
 * 5. suburb can be any form of string literal 
 * 6. The Street number can be any integer from 0 to virtually infinity
 * 7. The post code cane be any integer from 0 to virtually infinity
 * 8. An Id is required to identify a member
 * 9. A Member can may have a spouse or not
 * 10. A member may have 1, 2 or no parents.
 * 11. A member may have 0 or more children.
 * 12. A member may have 0 or more grandchildren.
 * 13. A member may have a surname from birth or marriage or both.
 * 14. The ID can be any integer from 0 to virtually infinity
 * 15. The first name can be any form of string literal 
 * 16. The surname birth can be any form of string literal 
 * 17. The surname marriage can be any form of string literal 
 * 18. The Street number can be any form of string literal 
 * 19. The Description can be any form of string literal 
 * 20. Gender can only be either male or female
 * 21. An arrayList is required to hold the parent objects
 * 22. An arrayList is required to hold the children objects
 * 
 * Condition of Input:
 * Street number and postcode can only be an integer
 * street name and suburb can be any combination of a string literal
 * ID can only be an integer
 * firstName can be a string literal
 * surnameBirth can be any form of string literal 
 * surnameMarriage can be any form of string literal 
 * description can be any form of string literal 
 * gender can only be two of either female or male string
 * 
 * 
 * Expected Output:
 * StreetNumber     -   integer
 * postcode         -   integer
 * street name      -   string
 * suburb           -   string
 * ID               -   integer
 * firstName        -   string 
 * surnameBirth     -   string 
 * surnameMarriage  -   string 
 * description      -   string 
 * gender           -   string
 * spouse           -   Member
 * parents          -   ArrayList<Member>
 * children         -   ArrayList<Member>
 * address          -   Address
 */
package FamilyClass;

import java.util.*;
import java.io.Serializable;

/**
 * A Class that handles and store the details of a member
 *
 * @version 2.02 25 May 2018
 * @author Joseph Sigar
 */
public class Member implements Serializable, Cloneable {

    /**
     * A parameter used to define the members identification
     */
    private int id;

    /**
     * A parameter used to store the first name of the member
     */
    private String firstName;

    /**
     * A parameter used to store the surname at birth of the member
     */
    private String surnameBirth;

    /**
     * A parameter used to store the surname at Marriage of the member
     */
    private String surnameMarriage;

    /**
     * A parameter used to store the gender of the member
     */
    private String gender;

    /**
     * A parameter used to store the first name of the member
     */
    private Address address;

    /**
     * A parameter used to store the description of the member
     */
    private String description;

    /**
     * A parameter used to store an ArrayList of the parents of the member
     */
    private ArrayList<Member> parent;

    /**
     * A parameter used to store the spouse member object of the member
     */
    private Member spouse;

    /**
     * A parameter used to store an ArrayList of the children of the member
     */
    private ArrayList<Member> children;

    /**
     * A Constructor of the Member class. Used to create all required objects
     *
     */
    public Member() {
        // A constructor
        this.id = 0;
        this.firstName = "";
        this.surnameBirth = "";
        this.surnameMarriage = "";
        this.gender = "";
        this.address = new Address();
        this.description = "";

        this.parent = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    /**
     * An Initializer constructor of the Member class. Used to initialize the
     * Member class using the given values.
     *
     * @param id an integer to define the identify the member
     * @param firstname a string literal that defines the member's first name
     * @param surnameBirth a string literal that defines the surname at birth
     * @param gender used to define the gender
     * @param address used to set the address of member
     * @param description used the members description
     * @throws UnknownOpException when an unexpected input is entered
     * @throws CloneNotSupportedException when a clone object is not supported
     */
    public Member(int id, String firstname, String surnameBirth, String gender,
            Address address, String description)
            throws UnknownOpException, CloneNotSupportedException {

        if (id <= 0) {
            throw new UnknownOpException("Wrong Identification Entered.");
        }
        this.id = id;
        this.firstName = firstname;
        this.surnameBirth = surnameBirth;
        this.surnameMarriage = "";

        if (gender.length() == 1) {
            if (gender.equalsIgnoreCase("f")) {
                this.gender = "female";
            } else if (gender.equalsIgnoreCase("m")) {
                this.gender = "male";
            } else {
                throw new UnknownOpException("Wrong Gender entered.");
            }
        } else if (gender.equalsIgnoreCase("female")
                || gender.equalsIgnoreCase("male")) {
            this.gender = gender;
        } else {
            throw new UnknownOpException("Wrong Gender entered.");
        }

        this.address = address.clone();
        this.description = description;
        this.parent = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    /**
     * An Initializer constructor of the Member class. Used to initialize the
     * Member class using the given values.
     *
     * @param id an integer to define the identify the member
     * @param firstname a string literal that defines the member's first name
     * @param surnameBirth a string literal that defines the surname at birth
     * @param surnameMarriage used to define the surname at marriage
     * @param gender used to define the gender
     * @param address used to set the address of member
     * @param description used the members description
     * @throws UnknownOpException when an unexpected input is entered
     * @throws CloneNotSupportedException when a clone object is not supported
     */
    public Member(int id, String firstname, String surnameBirth,
            String surnameMarriage, String gender,
            Address address, String description)
            throws UnknownOpException, CloneNotSupportedException {

        if (id <= 0) {
            throw new UnknownOpException("Wrong Identification Entered.");
        }

        this.id = id;
        this.firstName = firstname;
        this.surnameBirth = surnameBirth;
        this.surnameMarriage = surnameMarriage;

        if (gender.length() == 1) {
            if (gender.equalsIgnoreCase("f")) {
                this.gender = "Female";
            } else if (gender.equalsIgnoreCase("m")) {
                this.gender = "Male";
            } else {
                throw new UnknownOpException("Wrong Gender entered.");
            }
        } else if (gender.equalsIgnoreCase("female")
                || gender.equalsIgnoreCase("male")) {
            this.gender = gender;
        } else {
            throw new UnknownOpException("Wrong Gender entered.");
        }

        this.address = address.clone();
        this.description = description;
        this.parent = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    /**
     * A method that clears the values and sets them to program.
     *
     */
    public void clear() {
        this.id = 0;
        this.firstName = "";
        this.surnameBirth = "";
        this.surnameMarriage = "";
        this.gender = "";
        this.address.clear();
        this.description = "";
    }

    /**
     * A method that returns whether an address object is empty or not.
     *
     * @return true if the Address object is empty
     */
    public boolean isEmpty() {
        return this.id == 0 && this.firstName.isEmpty()
                && this.surnameBirth.isEmpty()
                && this.surnameMarriage.isEmpty() && this.gender.isEmpty()
                && this.address.isEmpty() && this.description.isEmpty();
    }

    /**
     * A method to determine whether a member has a spouse or not
     *
     * @return true if the member has a spouse <br> otherwise returns false
     */
    public boolean hasSpouse() {
        return this.spouse != null;
    }

    /**
     * A method used to return the spouse member object of the current member.
     *
     * @return A member objects that holds the details of the spouse
     */
    public Member getSpouse() {
        return this.spouse;
    }

    /**
     * A Method that allows the user to the an ArrayList that contains the
     * members of the children.
     *
     * @param children an ArrayList contains members that indicate the children
     * of the current member
     */
    public void setChildren(ArrayList<Member> children) {
        this.children = children;
    }

    /**
     * A method that is used to add a Child member on to the current Member.
     *
     * @param child a Member object that contains the details of the child.
     */
    public void addChild(Member child) {
        if (!this.children.contains(child)) {
            this.children.add(child);
        }
    }

    /**
     * A method that is used to return the children of the current member.
     *
     * @return an ArrayList that contains the child members of the current
     * member <br> May also return an empty list if the member has no children
     */
    public ArrayList<Member> getChildren() {
        return this.children;
    }

    /**
     * A method used to set the spouse object of the current Member.
     * 
     * @param spouse a member object that has the spouse details on it.
     */
    public void setSpouse(Member spouse) {
        this.spouse = spouse;
    }

    /**
     * Returns an ArrayList contains the Parent member objects.
     * May also return an empty ArrayList
     * @return an ArrayList denoting the parents of the current member
     */
    public ArrayList<Member> getParent() {
        return this.parent;
    }

    /**
     * Adds a Parent Object to the Current member.
     * This methods will only allow the entry of two parents. The rest will 
     * be discarded
     * @param parent - an Object denoting the parent details of the current 
     * member
     */
    public void addParent(Member parent) {
        if (this.parent.size() < 2) {
            this.parent.add(parent); 
        }
    }

    /**
     * Use to set an ArrayList of the parent member objects of the current
     * member. Only the first two elements of the given list are considered 
     * for this program.
     * @param parents an ArrayList provided that contains the parent member
     * objects
     */
    public void setParent(ArrayList<Member> parents) {
        
        this.parent.add(parents.get(0));
        this.parent.add(parents.get(1));
    }

    /**
     * Returns the Member ID.
     * 
     * @return returns an integer indicating the member identification number
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets the Member's Identification number.
     * Sets an integer value to denote the members identification.
     * @param id an integer value to indicate the members identification
     * @throws UnknownOpException
     */
    public void setId(int id) throws UnknownOpException {
        if (id <= 0) {
            throw new UnknownOpException("Wrong Identification Entered.");
        }

        this.id = id;
    }

    /**
     * Returns the first name of the Member.
     * @return a string literal indicating the first name of the member
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Sets the first name of the Member.
     * Sets the first name of the Member to a user defined string literal
     * @param firstName - staring value that indicates the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the surname given at birth.
     * @return a string literal denoting the surname of the member
     */
    public String getSurnameBirth() {
        return this.surnameBirth;
    }

    /**
     * Sets the surname at birth.
     * Allows the user to set the surname at birth for the member.
     * @param surnameAtBirth a string literal denoting the surname at birth
     */
    public void setSurnameBirth(String surnameAtBirth) {
        this.surnameBirth = surnameAtBirth;
    }

    /**
     * Returns the surname a member may have gotten after marriage.
     * @return a string literal indicating the surname after marriage
     */
    public String getSurnameMarriage() {
        return this.surnameMarriage;
    }

    /**
     * Sets the surname of the member after marriage.
     * @param surnameAtMarriage a string literal denoting the surname a member
     * may have inherited from marriage
     */
    public void setSurnameMarriage(String surnameAtMarriage) {
        this.surnameMarriage = surnameAtMarriage;
    }

    /**
     * Returns a String thats shows the gender of the Member.
     * @return a string literal denoting either male or female
     */
    public String getGender() {
        return this.gender;
    }

    /**
     * Sets the gender of the Member.
     * Sets the gender of the member to either 'male' or 'female'. Otherwise
     * throws an exception that must be caught.
     * @param gender a string that is to be set. can only be 'male' or 'female'. 
     * Or alternatively 'f' or 'm'
     * @throws UnknownOpException
     */
    public void setGender(String gender) throws UnknownOpException {
        if (gender.length() == 1) {
            if (gender.equalsIgnoreCase("f")) {
                this.gender = "female";
            } else if (gender.equalsIgnoreCase("m")) {
                this.gender = "male";
            } else {
                throw new UnknownOpException("Wrong Gender entered.");
            }
        } else if (gender.equalsIgnoreCase("female")
                || gender.equalsIgnoreCase("male")) {
            this.gender = gender;
        } else {
            throw new UnknownOpException("Wrong Gender entered.");
        }
    }

    /**
     * Returns an Address object.
     * This methods returns an Address object for the current Member.
     * @return an Address Object for the current Member
     */
    public Address getAddress() {
        return this.address;
    }

    /**
     * Sets the Address of the current member.
     * Provides a copy of the given address object.
     * @param address object to denote the address of the 
     */
    public void setAddress(Address address)  {
        this.address = address;
    }

    /**
     * Returns a string literal description of the current member.
     * @return a string literal of the current member
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * A method used to set the description of a member.
     * A description would indicate something the member would describe them
     * selves as.
     * @param description a string to define the members description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns a hash code value for the object. 
     * This method is supported for the benefit of hash tables such as those 
     * provided by HashMap.
     * 
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() { 
        int hash = 3;
        hash = 61 * hash + this.id;
        hash = 61 * hash + Objects.hashCode(this.firstName);
        hash = 61 * hash + Objects.hashCode(this.surnameBirth);
        hash = 61 * hash + Objects.hashCode(this.surnameMarriage);
        hash = 61 * hash + Objects.hashCode(this.gender);
        hash = 61 * hash + Objects.hashCode(this.address);
        hash = 61 * hash + Objects.hashCode(this.description);
        hash = 61 * hash + Objects.hashCode(this.parent);
        hash = 61 * hash + Objects.hashCode(this.spouse);
        hash = 61 * hash + Objects.hashCode(this.children);
        return hash;
    }

    /**
     * Indicates whether some other object is "equal to" this one. 
     * @param obj - the reference object with which to compare.
     * @return true if this object is the same as the obj argument; false
     * otherwise. 
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) { 
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Member other = (Member) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.surnameBirth, other.surnameBirth)) {
            return false;
        }
        if (!Objects.equals(this.surnameMarriage, other.surnameMarriage)) {
            return false;
        }
        if (!Objects.equals(this.gender, other.gender)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.parent, other.parent)) {
            return false;
        }
        if (!Objects.equals(this.spouse, other.spouse)) {
            return false;
        }
        return Objects.equals(this.children, other.children);
    }

    /**
     * Creates and returns a copy of this object. First, if the class of this
     * object does not implement the interface Cloneable, then a
     * CloneNotSupportedException is thrown.
     *
     * @return: A clone of this instance.
     * @throws CloneNotSupportedException
     */
    @Override
    public Member clone() throws CloneNotSupportedException {
        
        Member mem = (Member) super.clone();;

        mem.firstName = this.firstName;
        mem.surnameBirth = this.surnameBirth;
        mem.surnameMarriage = this.surnameMarriage;
        mem.gender = this.gender;
        mem.address = this.address.clone();
        mem.description = this.description;
        mem.children = this.children;
        mem.parent = this.parent;
        return mem;
    }
}
