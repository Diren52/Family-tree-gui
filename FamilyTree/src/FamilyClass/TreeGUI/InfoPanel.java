/*
 * Title: Mr
 * Author: Joseph Sigar
 * Date: 25/05/18
 * File name: Tree GUI
 * 
 * Purpose: To be able to display a grapics interface that collects information 
 * from the user. 
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
 * A Class that allows the collection of information from the user..
 *
 * @version 2.02 25 May 2018
 * @author Joseph Sigar
 */
public class InfoPanel extends JPanel {

    private JTextField firstName;
    private JTextField surnameBirth;
    private JTextField surnameMarriage;
    private JComboBox gender;
    private JTextArea description;
    private JTextField streetNumber;
    private JTextField streetName;
    private JTextField suburb;
    private JTextField pCode;

    private JPanel buttonPanel;
    private JPanel centerPanel;
    private JLabel addLabel;
    private JButton saveTree;
    private JButton addSpouse;
    private JButton addParent;
    private JButton addChild;
    private JButton edit;

    /**
     * A string to indicate a spouse of the current
     */
    public final String SPOUSE;

    /**
     * A string to indicate a parent of the current
     */
    public final String CHILD;

    /**
     * A string to indicate a child of the current
     */
    public final String PARENT;

    /**
     * A string to signify a save
     */
    public final String SAVE;

    /**
     * A string to signify edit
     */
    public final String EDIT;

    /**
     * A string to signify going back
     */
    public final String BACK;

    /**
     * A string to signify done
     */
    public final String DONE;

    private JPanel headerPanel;
    private JLabel headerLabel;

    private JPanel bottomPanel;
    private JLabel bottomLabel;
    private JButton doneButton;
    private JButton backButton;

    /**
     * A constructor used to initialize and create the Graphics interface of
     * of the information panel
     */
    public InfoPanel() {

        // Setting up the required strings
        SAVE = "Save";
        SPOUSE = "Spouse";
        CHILD = "Child";
        PARENT = "Parent";
        EDIT = "Edit";
        DONE = "Done";
        BACK = "back";

        createUI();
    }

    private void createUI() {
        // Set up the initial class layout to border layout
        this.setLayout(new BorderLayout(5, 5));
        this.setBackground(Color.cyan);

        // Add the header layer onto the header of the layout
        headerPanel = new JPanel();
        headerLabel = new JLabel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.LINE_AXIS));
        headerPanel.add(headerLabel);
        this.add(headerPanel, BorderLayout.NORTH);

        edit = new JButton(EDIT);

        this.bottomPanel = new JPanel();
        this.backButton = new JButton(BACK);
        this.doneButton = new JButton(DONE);
        this.bottomLabel = new JLabel();

        // setting the back button into the bottom panel
        bottomLabel.setText("Click to Save to new Tree; ");
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.LINE_AXIS));
        bottomPanel.add(bottomLabel);
        bottomPanel.add(Box.createHorizontalGlue());
        bottomPanel.add(backButton);
        bottomPanel.add(doneButton);

        // Adding the bottom panel onto the info 
        this.add(bottomPanel, BorderLayout.SOUTH);

        // Disabling some of the buttoms on the bottom panel for the time being
        backButton.setVisible(false);

        // Disabling the done button. can only be enabled when the customer has saved
        doneButton.setEnabled(false);

        // Set the done button as the initial visible button
        edit.setEnabled(false);
        edit.setVisible(false);

        // Setting up the Button panel
        buttonPanel = new JPanel();
        addLabel = new JLabel("add: ");
        saveTree = new JButton(SAVE);
        addChild = new JButton(CHILD);
        addParent = new JButton(PARENT);
        addSpouse = new JButton(SPOUSE);

        // Set up the center of the layout and add it to the Class layout
        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));

        // Setting up the first name textfield
        firstName = new JTextField();
        firstName.setPreferredSize(new Dimension(100, 20));
        JPanel fName = makePanel("First Name: ", firstName);

        // Setting up the surname Birth textfield
        surnameBirth = new JTextField();
        surnameBirth.setPreferredSize(new Dimension(100, 20));
        JPanel sBirth = makePanel("Surname Birth: ", surnameBirth);

        // Setting up the surname Marriage textfield
        surnameMarriage = new JTextField();
        surnameMarriage.setPreferredSize(new Dimension(100, 20));
        JPanel sMarriage = makePanel("Surname Marriage: ", surnameMarriage);

        // Setting up the gender combo box
        String[] genders = {"Male", "Female"};
        gender = new JComboBox(genders);
        gender.setSelectedIndex(0);
        JPanel gndr = makePanel("Gender: ", gender);

        // Setting up the description text area using a scroll pane
        description = new JTextArea();
        JScrollPane jsp = new JScrollPane(description);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jsp.setPreferredSize(new Dimension(100, 50));
        description.setWrapStyleWord(true);
        description.setLineWrap(true);
        JPanel desc = makePanel("Description: ", jsp);
        desc.setPreferredSize(new Dimension(250, 75));

        // Setting up the street Number textfield
        streetNumber = new JTextField();
        JPanel sNumber = makePanel("Street Number: ", streetNumber);

        // Setting up the street name textfield
        streetName = new JTextField();
        JPanel sName = makePanel("Street Name: ", streetName);

        // Setting up the suburb textfield
        suburb = new JTextField();
        JPanel sub = makePanel("Suburb : ", suburb);

        // seting up the post code textfield
        pCode = new JTextField();
        JPanel postC = makePanel("Post Code: ", pCode);

        // Adding the above text field and area onto the center panel
        centerPanel.add(fName);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        centerPanel.add(sBirth);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        centerPanel.add(sMarriage);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        centerPanel.add(gndr);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        centerPanel.add(desc);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        centerPanel.add(new JSeparator(JSeparator.HORIZONTAL));
        centerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        centerPanel.add(sNumber);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        centerPanel.add(sName);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        centerPanel.add(sub);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        centerPanel.add(postC);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        centerPanel.add(new JSeparator(JSeparator.HORIZONTAL));
        centerPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        // adding a button panel onto the bottom of the info side of the mode
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        buttonPanel.add(saveTree);
        buttonPanel.add(edit);
        buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        buttonPanel.add(addLabel);
        buttonPanel.add(Box.createRigidArea(new Dimension(2, 0)));
        buttonPanel.add(addSpouse);
        buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        buttonPanel.add(addParent);
        buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        buttonPanel.add(addChild);
        centerPanel.add(buttonPanel);

        // Addin the center panel to the center of the current layout
        this.add(centerPanel, BorderLayout.CENTER);
    }

    /**
     * Returns a JButton for editing purposes the information.
     * @return a JButton
     */
    public JButton getEditButton() {
        return this.edit;
    }

    /**
     * Returns a JLabel for the header of the panel.
     * @return a JLabel
     */
    public JLabel getHeaderLabel() {
        return this.headerLabel;
    }

    /**
     * Returns a JButton for the save button
     * @return a JButton
     */
    public JButton getSaveButton() {
        return this.saveTree;
    }

    /**
     * Returns a JButton for the spouse button
     * @return a JButton
     */
    public JButton getSpouseButton() {
        return this.addSpouse;
    }

    /**
     * Returns a JButton for the child button
     * @return a JButton
     */
    public JButton getChildButton() {
        return this.addChild;
    }

    /**
     * Returns a JButton for the parent button
     * @return a JButton
     */
    public JButton getParentButton() {
        return this.addParent;
    }

    private JPanel makePanel(String labelName, Component obj) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(250, 20));
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        JLabel label = new JLabel(labelName);
        label.setPreferredSize(new Dimension(120, 20));

        label.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(50, 20)));
        obj.setSize(new Dimension(150, obj.getHeight()));
        panel.add(obj);

        return panel;
    }

    /**
     * Returns a JTextfield for first name input
     * @return a JTextField
     */
    public JTextField getFirstName() {
        return this.firstName;
    }

    /**
     * Returns a JTextfield for surname birth input
     * @return a JTextField
     */
    public JTextField getSurnameBirth() {
        return this.surnameBirth;
    }

   /**
     * Returns a JTextfield for surname marriage input
     * @return a JTextField
     */
    public JTextField getSurnameMarriage() {
        return this.surnameMarriage;
    }

    /**
     * Returns a JComboBox for gender Selection input
     * @return a JComboBox
     */
    public JComboBox getGender() {
        return this.gender;
    }

    /**
     * Returns a JTextArea for description input
     * @return a JTextArea
     */
    public JTextArea getDescription() {
        return this.description;
    }

    /**
     * Returns a JTextfield for street number input
     * @return a JTextField
     */
    public JTextField getStreetNumber() {
        return this.streetNumber;
    }

    /**
     * Returns a JTextfield for street name input
     * @return a JTextField
     */
    public JTextField getStreetName() {
        return this.streetName;
    }

    /**
     * Returns a JTextfield for suburb input
     * @return a JTextField
     */
    public JTextField getSuburb() {
        return this.suburb;
    }

    /**
     * Returns a JTextfield for post code input
     * @return a JTextField
     */
    public JTextField getPostCode() {
        return this.pCode;
    }

    /**
     * Returns a JButton for done button
     * @return a JButton
     */
    public JButton getDoneButton() {
        return this.doneButton;
    }

    /**
     * Returns a JButton for back button
     * @return a JButton
     */
    public JButton getBackButton() {
        return this.backButton;
    }

    /**
     * Returns a JLabel for the bottom of the panel
     * @return a JLabel
     */
    public JLabel getBottomLabel() {
        return this.bottomLabel;
    }

}
