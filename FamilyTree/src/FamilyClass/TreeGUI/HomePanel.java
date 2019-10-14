/*
 * Title: Mr
 * Author: Joseph Sigar
 * Date: 25/05/18
 * File name: Tree GUI
 * 
 * Purpose: To be able to pair two buttons together. Is then later used to 
 * connect the buttons with a line.
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
 * 23. A class is required to hold two pairs or button that would indicate
 *      a start position and an end position
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
 * source a JButton component
 * destination a JButton Component
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
 * source           -   JButton
 * destination      -   JButton
 */

package FamilyClass.TreeGUI;

import javax.swing.*;
import java.awt.*;

/**
 * A Class That displays the Home screen information back to the user via
 * the Graphic interface. It extends the JPanel class
 *
 * @version 2.02 25 May 2018
 * @author Joseph Sigar
 */
public class HomePanel extends JPanel {
    /**
     * A Variable to denote that a new tree required.
     */
    private final JButton newTree;
    
    /**
     * A Variable to denote that a user would like to load from a file.
     */
    private final JButton fromFile;
    
    private final JPanel holder;
    
    /**
     * A Constructor. Sets up the JPanel with the required welcoming message.
     */
    public HomePanel(){
        
        // Sets the layout for this Panel
        this.setLayout(new BorderLayout(2,5));
        
        // A new panel to hold both intro
        holder = new JPanel();
        holder.setLayout(new BoxLayout(holder, BoxLayout.PAGE_AXIS));
        // To create some space between the intros
        holder.add(Box.createRigidArea(new Dimension(300, 50)));
        JLabel info = new JLabel("Welcome to Family Tree App");
        info.setFont(new Font("SansSerif", Font.BOLD, 20));
        info.setAlignmentX(Component.CENTER_ALIGNMENT);
        holder.add(info);
        JLabel info_2 = new JLabel("Would you like to: ");
        info_2.setFont(new Font("SansSerif", Font.BOLD, 20));
        info_2.setAlignmentX(Component.CENTER_ALIGNMENT);
        holder.add(info_2);
        holder.add(Box.createRigidArea(new Dimension(10,10)));
        
        // A panel to hold the buttons
        JPanel jp2 = new JPanel();
        jp2.setBackground(holder.getBackground());
        jp2.setLayout(new BoxLayout(jp2, BoxLayout.LINE_AXIS));
        newTree = new JButton("Create New Tree");
        fromFile = new JButton("Load from File");
        
        jp2.add(newTree);
        jp2.add(Box.createRigidArea(new Dimension(10, 10)));
        jp2.add(fromFile);
        holder.add(jp2);
        
        // Finally add the buttons onto the home panel
        this.add(holder, BorderLayout.CENTER);
    }
    
    /**
     * Returns a JButton for loading from a file.
     * @return a JButton
     */
    public JButton getFromFileBtn() {
        return this.fromFile;
    }
    
    /**
     * Returns a JButton for creating a new tree
     * @return a JButton
     */
    public JButton getNewTreeBtn() {
        return this.newTree;
    }
    
}
