/*
 * Title: Mr
 * Author: Joseph Sigar
 * Date: 25/05/18
 * File name: Family Tree
 * 
 * Purpose: To be able to load file given a file object that denotes
 * the path and file the loading would take place. As well as a file to denote 
 * the saving of an member object on to a file given the save file and path
 * are provided
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

import java.io.*;
import java.util.*;

/**
 * A Class that handles the Input and output of a family tree. 
 * allows the loading and saving to a serialized object
 *
 * @version 2.02 25 May 2018
 * @author Joseph Sigar
 */
public class TreeIO {

    /**
     * An ArrayList that holds the member values that are loaded from a file.
     */
    private ArrayList<Member> familyMembers;
    
    /**
     * A Constructor that would initialize the Member ArrayList.
     */
    public TreeIO() {
        familyMembers = new ArrayList<>();
    }

    /**
     * Returns the loaded family Members objects. This is returned in an 
     * ArrayList
     * @return an ArrayList containing the family member objects.
     */
    public ArrayList<Member> getFamilyMembers() {
        return this.familyMembers;
    }

    /**
     * A method used to save a given member array list to a given file object 
     * @param members array list to be saved
     * @param file file object where the above list would be saved in
     * @throws IOException
     */
    public void saveToFile(ArrayList<Member> members, File file)
            throws IOException {

        String fileName = file.getName();
        String filePath = null;
        if (fileName.endsWith(".ser"))
            filePath = file.getAbsolutePath();
        else
            filePath = file.getAbsolutePath()+".ser";
        
        FileOutputStream fileOut = new FileOutputStream(
                filePath);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(members);
        out.close();
        fileOut.close();

    }

    /**
     * Allows the user to load a family tree from a given file object.
     * @param file -  file object that is used to load the family free.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void loadFromFile(File file)
            throws IOException, ClassNotFoundException {

        FileInputStream filein = new FileInputStream(file.getAbsoluteFile());
        ObjectInputStream in = new ObjectInputStream(filein);

        // Assume the file is .ser format
        familyMembers = (ArrayList<Member>) in.readObject();

        in.close();
        filein.close();
    }

}
