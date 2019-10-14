/*
 * Title: Mr
 * Author: Joseph Sigar
 * Date: 25/05/18
 * File name: Tree GUI
 * 
 * Purpose: To be able to display to the user the Menu option and allow them
 * to pick a suitabel option.
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

import java.awt.event.*;
import javax.swing.*;

/**
 * A Class that creates and allows the frame to display the menu bar. 
 *
 * @version 2.02 25 May 2018
 * @author Joseph Sigar
 */
public class MenuBar extends JMenuBar{
    
    private JMenu file;
    private JMenu edit;
    private JMenuItem newTree;
    private JMenuItem load;
    private JMenuItem save;
    private JMenuItem saveAs;
    private JMenuItem editTree;
    private JMenuItem exit;
    
    /**
     * A Constructor for the Menu bar that initializes and creates the menu bar
     * and the menu items contained in the menu bar.
     */
    public MenuBar(){
        file = new JMenu("File");
        edit = new JMenu("Edit");
        
        newTree = new JMenuItem("New...");
        newTree.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        file.add(newTree);
        load = new JMenuItem("Load...");
        load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK));
        file.add(load);
        file.addSeparator();
        save = new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        file.add(save);
        
        saveAs = new JMenuItem("Save As...");
        file.add(saveAs);
        file.addSeparator();
        
        exit = new JMenuItem("Exit");
        file.add(exit);
        this.add(file);
        
        editTree = new JMenuItem("Edit Tree...");
        editTree.setEnabled(false);
        editTree.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK));
        edit.add(editTree);
        
        this.add(edit);
    }
    
    /**
     * Returns a MenuItem components for the New tree selections
     * @return a JMenuIteme
     */
    public JMenuItem getNewTree(){
        return this.newTree;
    }
    
    /**
     * Returns a MenuItem components for the load tree selections
     * @return a JMenuIteme
     */
    public JMenuItem getLoad() {
        return this.load;
    }
    
    /**
     * Returns a MenuItem components for the save tree selections
     * @return a JMenuIteme
     */
    public JMenuItem getSave() {
        return this.save;
    }
    
    /**
     * Returns a MenuItem components for the save as tree selections
     * @return a JMenuIteme
     */
    public JMenuItem getSaveAs(){
        return this.saveAs;
    }
    
    /**
     * Returns a MenuItem components for the edit tree selections
     * @return a JMenuIteme
     */
    public JMenuItem getEditTree(){
        return this.editTree;
    }
    
    /**
     * Returns a MenuItem components for the exit program selections
     * @return a JMenuIteme
     */
    public JMenuItem getExit() {
        return this.exit;
    }
    
}
