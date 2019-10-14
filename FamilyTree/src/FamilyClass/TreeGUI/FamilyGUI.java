/*
 * Title: Mr
 * Author: Joseph Sigar
 * Date: 25/05/18
 * File name: Tree GUI
 * 
 * Purpose: To be able to Display the Family Tree onto the Graphics interface.
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

package FamilyClass.TreeGUI;

import javax.swing.*;


/**
 * The Main Java Program.
 * A class that is required to run the program.
 *
 * @version 2.02 25 May 2018
 * @author Joseph Sigar
 */
public class FamilyGUI {

    /**
     * The Main java program. allow the project to start.
     * @param args
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(
                    "com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | 
                IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}
