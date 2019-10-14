/*
 * Title: Mr
 * Author: Joseph Sigar
 * Date: 25/05/18
 * File name: Tree GUI
 * 
 * Purpose: To enable the program to display the tree as well as the information
 * gathering panel. allow the user to move between the view mode and the edit 
 * mode.
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
import javax.swing.filechooser.*;
import java.awt.*;
import java.awt.event.*;
import FamilyClass.*;
import java.util.*;
import java.io.*;

/**
 * A Class that allows the information panel and the preview panel to be viewed
 * together.
 *
 * @version 2.02 25 May 2018
 * @author Joseph Sigar
 */
public class ModePanel extends JPanel implements ActionListener {

    // To allow the loading and saving of files
    private final TreeIO tree;
    private final JPanel infoPanel;
    private final JPanel insideInfo;
    // for viewing the tree
    private PreviewPanel preview;
    // for getting/displayng the information
    private InfoPanel init;

    private final MainFrame frame;
    // Strings for the commands and naming
    private final String BACK_BEFORE_SAVE = "backbefr";
    private final String BACK_AFTER_SAVE = "backafter";
    private final String EDIT = "edit";
    private final String INITIAL_PANEL = "initial";
    private final String SPOUSE_PANEL = "spouse";
    private final String SAVE_AS = "Save_as";
    private final String SAVE_EDIT = "save_edit";
    private final String SAVE_TO = "Save_To";
    private final String SAVE = "save";
    private final String[] MEMBER = {"parent", "child", "grandChild", " spouse"};
    private String whichMember;
    // where the saving ends up to.
    private File saveFilePath;

    private final ArrayList<Member> members;
    private Member rootView;
    private int counter;

    private final Stack memberStack; //Stores the member object created
    private final Stack panelStack; //Store the panels created
    private final boolean editFlag; // whether we are in view mode or not

    /**
     * A Constructor to the mode panel class. That initializes the Object.
     * Should be used when entering the Edit mode. That is, when the user wants
     * to create a new tree.
     *
     * @param frame - The Mainframe object.
     */
    public ModePanel(MainFrame frame) {
        this.frame = frame;
        this.infoPanel = new JPanel(new BorderLayout(5, 5));
        this.infoPanel.setBorder(BorderFactory.createTitledBorder(""));
        this.insideInfo = new JPanel(new CardLayout());

        this.init = new InfoPanel();
        members = new ArrayList<>();
        memberStack = new Stack();
        panelStack = new Stack();
        counter = 0;

        this.setLayout(new BorderLayout(5, 5));
        createNewMode();
        tree = new TreeIO();
        saveFilePath = null;
        editFlag = false;
    }

    /**
     * An Initializer Constructor. Mainly used when first entering the view mode
     * from a loaded family tree file.
     *
     * @param frame
     * @param members
     * @param loadFile
     */
    public ModePanel(MainFrame frame, ArrayList<Member> members, File loadFile) {
        this.frame = frame;
        this.members = (ArrayList<Member>) members.clone();

        this.infoPanel = new JPanel(new BorderLayout(5, 5));

        this.infoPanel.setBorder(BorderFactory.createTitledBorder(""));

        this.insideInfo = new JPanel(new CardLayout());
        this.setLayout(new BorderLayout(5, 5));

        this.init = new InfoPanel();
        saveFilePath = loadFile; // Tracks the save location.
        tree = new TreeIO();

        editFlag = false;

        // Disables the save button during the view mode
        this.frame.getWindowMenuBar().getSaveAs().setEnabled(false);
        this.frame.getWindowMenuBar().getSaveAs().addActionListener(new SaveButton());
        this.frame.getWindowMenuBar().getSaveAs().setActionCommand(SAVE_AS);
        this.frame.getWindowMenuBar().getSave().addActionListener(new SaveButton());
        this.frame.getWindowMenuBar().getSave().setActionCommand(SAVE_TO);
        this.frame.getWindowMenuBar().getSave().setEnabled(false);
        this.frame.getWindowMenuBar().getEditTree().setEnabled(true);

        // Setting the status label to show whats happening
        frame.getStatusLabel().setText("Displaying a family tree...");

        // For the center of the info side, add a scroll bar and a panel that
        // has the ability to switch between other panels
        JScrollPane jsp = new JScrollPane(insideInfo);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jsp.setPreferredSize(new Dimension(325, 400));
        infoPanel.add(jsp, BorderLayout.CENTER);

        this.add(infoPanel, BorderLayout.WEST);

        // Adding the preview mode with a reference 
        preview = new PreviewPanel(this, this.members);
        this.add(preview, BorderLayout.CENTER);
        panelStack = new Stack();
        memberStack = new Stack();

    }

    private void createNewMode() {
        // Enabling the save as button from the menu from the main frame
        frame.getWindowMenuBar().getSaveAs().setEnabled(true);
        frame.getWindowMenuBar().getSaveAs().addActionListener(new SaveButton());
        frame.getWindowMenuBar().getSaveAs().setActionCommand(SAVE_AS);
        frame.getWindowMenuBar().getSave().addActionListener(new SaveButton());

        // If a save file exists overrite it
        if (saveFilePath == null) {
            frame.getWindowMenuBar().getSave().setActionCommand(SAVE_AS);
        } else if (saveFilePath != null) {
            frame.getWindowMenuBar().getSave().setActionCommand(SAVE_TO);
        }

        frame.getWindowMenuBar().getSave().setEnabled(true);

        // Setting the status label to show whats happening
        frame.getStatusLabel().setText("Creating a new Tree ...");

        // For the center of the info side, add a scroll bar and a panel that
        // has the ability to switch between other panels
        JScrollPane jsp = new JScrollPane(insideInfo);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jsp.setPreferredSize(new Dimension(325, 400));
        infoPanel.add(jsp, BorderLayout.CENTER);

        // Setting the Header for the initial class
        init.getHeaderLabel().setText("Your Information: ");

        // Setting the initial Panel for the inside the info panel
        init.setName(this.INITIAL_PANEL);
        insideInfo.add(init, init.getName());

        // This button can only be enabled after saving
        init.getSpouseButton().setEnabled(false);
        init.getChildButton().setEnabled(false);
        init.getParentButton().setEnabled(false);

        // Adding Action listeners for the init panel buttons
        init.getSpouseButton().addActionListener(this);
        init.getParentButton().addActionListener(this);
        init.getChildButton().addActionListener(this);
        init.getSaveButton().addActionListener(new SaveButton());

        //init.getSpouseButton().setBorderPainted(false);
        // Setting the action command for the init save button
        init.getSaveButton().setActionCommand(this.SAVE);

        // Setting the action listener for the done and saveAS button
        init.getDoneButton().setActionCommand(SAVE_AS);
        init.getDoneButton().addActionListener(new SaveButton());
        init.getEditButton().addActionListener(new EditTree());

        // finally add the info side onto the mode
        this.add(infoPanel, BorderLayout.WEST);
        // adding a preview mode on the mode panel
        preview = new PreviewPanel();
        this.add(preview, BorderLayout.CENTER);
    }

    /**
     * Allows the setting of the Member Object. Called mainly in the preview
     * panel to allow the selected button to change the information displayed in
     * the information panel.
     *
     * @param root - A member object.
     */
    public void setRoot(Member root) {
        panelStack.clear();
        memberStack.clear();
        insideInfo.removeAll();
        this.rootView = root;

        viewUI(this.rootView);
    }

    private void viewUI(Member root) {
        init = new InfoPanel();

        // sets the disabled field colour to black for visibility.
        UIManager.put("JTextField.disabledForeground", Color.BLACK);
        UIManager.put("JComboBox.disabled", Color.BLACK);

        init.getHeaderLabel().setText("Showing " + root.getFirstName()
                + " Family Tree: ");

        // Setting the initial panel to the roots details
        init.getFirstName().setText(root.getFirstName());
        init.getSurnameBirth().setText(root.getSurnameBirth());
        init.getSurnameMarriage().setText(root.getSurnameMarriage());
        if (root.getGender().equalsIgnoreCase("male")) {
            init.getGender().setSelectedIndex(0);
        } else {
            init.getGender().setSelectedIndex(1);
        }
        init.getDescription().setText(root.getDescription());
        init.getStreetName().setText(root.getAddress().getStreetName());
        init.getStreetNumber().setText("" + root.getAddress().getStreetNumber());
        init.getPostCode().setText("" + root.getAddress().getPostCode());
        init.getSuburb().setText(root.getAddress().getSuburb());

        // This button can only be enabled during edit mode
        init.getSpouseButton().setEnabled(false);
        init.getChildButton().setEnabled(false);
        init.getParentButton().setEnabled(false);
        init.getSaveButton().setVisible(false);
        init.getEditButton().setVisible(true);
        init.getEditButton().setEnabled(true);
        init.getEditButton().setActionCommand(EDIT);
        init.getEditButton().addActionListener(new EditTree());
        init.getChildButton().addActionListener(ModePanel.this);

        init.getSpouseButton().addActionListener(ModePanel.this);
        init.getParentButton().addActionListener(ModePanel.this);
        init.getDoneButton().addActionListener(new SaveButton());
        init.getSaveButton().addActionListener(new SaveButton());
        init.setName(this.INITIAL_PANEL);

        // setting the back button into the bottom panel
        init.getBottomLabel().setText("Viewing Family Tree");
        init.getDoneButton().setVisible(false);
        init.getBackButton().setVisible(false);

        changeTextFields(init, false);

        insideInfo.add(init, init.getName());

        panelStack.add(init);
        memberStack.add(root);

        preview.setRoot(root, true);
    }

    /**
     * The action listener method for this class. Allows various events to be
     * performed depending on their action commands.
     *
     * @param e - event that invoked the action listener
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Get the action command to check which button pressed
        String event = e.getActionCommand();

        // Get the layout to enable switching between layouts
        CardLayout cl = (CardLayout) insideInfo.getLayout();

        if (event.equals(init.SPOUSE)) {
            frame.getStatusLabel().setBackground(Color.black);
            
            counter++;
            
            InfoPanel peek = (InfoPanel) panelStack.peek();
            init = new InfoPanel();

            // Changes the header to a suitable title
            init.getHeaderLabel().setText(peek.getFirstName().getText()
                    + "'s " + init.SPOUSE + " information: ");

            // Setting the name of the spouse panel
            init.setName(this.SPOUSE_PANEL + " " + counter);

            // adds a spouse panel to the iniside info panel
            insideInfo.add(this.init, init.getName());

            // allows the switching to the spouse information gathering
            cl.show(insideInfo, init.getName());

            // Disables the add child button. At the moment can only be added 
            // from the main person
            init.getChildButton().setEnabled(false);

            // Disabling the parent button until the spouse has been saved.
            init.getParentButton().setEnabled(false);

            // since we adding a spouse no need to have the button enabled
            init.getSpouseButton().setEnabled(false);

            // changing the status label to a more fitting text
            frame.getStatusLabel().setText("Entering Spouse Information: ");

            // enabling the bottom panel to allow the user to go back
            init.getBackButton().setVisible(true);
            init.getBackButton().setActionCommand(BACK_BEFORE_SAVE);
            init.getBackButton().addActionListener(new BackButton());
            init.getDoneButton().setVisible(false);

            init.getParentButton().addActionListener(this);
            init.getBottomLabel().setText("Unsaved Info will be LOST: ");
            whichMember = MEMBER[3];

            // Setting the ActionCommand for the spouse save button
            init.getSaveButton().setActionCommand(this.SAVE);
            init.getSaveButton().addActionListener(new SaveButton());

            init.getEditButton().addActionListener(new EditTree());

        } else if (event.equals(init.PARENT)) {

            frame.getStatusLabel().setBackground(Color.black);
            counter++;
            
            InfoPanel peek = (InfoPanel) panelStack.peek();

            // Setting up the parent initial class
            init = new InfoPanel();

            // Changes the header to a suitable title
            init.getHeaderLabel().setText(peek.getFirstName().getText() + "'s "
                    + init.PARENT + " information: ");

            init.setName(init.PARENT + " " + counter);

            // adds a parent panel to the iniside info panel
            insideInfo.add(this.init, init.getName());

            // allows the switching to the parent information gathering
            cl.show(insideInfo, init.getName());

            // Disables the add child button. At the moment can only be added 
            // from the main person
            init.getChildButton().setEnabled(false);

            // since we adding a parent no need to have the button enabled
            init.getSpouseButton().setEnabled(false);
            init.getParentButton().setEnabled(false);

            // changing the status label to a more fitting text
            frame.getStatusLabel().setText("Entering Parent Information: ");

            // enabling the bottom panel to allow the user to go back
            init.getBackButton().setVisible(true);
            init.getBackButton().setActionCommand(this.BACK_BEFORE_SAVE);
            init.getBackButton().addActionListener(new BackButton());

            init.getDoneButton().setVisible(false);
            init.getEditButton().addActionListener(new EditTree());

            // adding the action listener for the back button and save button
            init.getSaveButton().setActionCommand(this.SAVE);
            init.getSaveButton().addActionListener(new SaveButton());

            whichMember = MEMBER[0];

        } else if (event.equals(init.CHILD)) {
            frame.getStatusLabel().setBackground(Color.black);
            counter++;
            // Peek onto the last panel in the panels stack
            InfoPanel peek = (InfoPanel) panelStack.peek();

            // Create a new Child iniatilclass object
            init = new InfoPanel();

            // Set the header to fitting header
            init.getHeaderLabel().setText(peek.getFirstName().getText() + "'s "
                    + init.CHILD + " information: ");

            // Set a name for the child class
            // if the previous panel was a child then disable child button
            // Changing the status label
            if (peek.getName().contains(init.CHILD)) {
                init.setName("Grand" + init.CHILD + " " + counter);
                frame.getStatusLabel().setText("Entering GrandChild Information: ");
                whichMember = MEMBER[2];
            } else {
                init.setName(init.CHILD + " " + counter);
                frame.getStatusLabel().setText("Entering Child Information: ");
                whichMember = MEMBER[1];
            }

            // add the child onto the inside info panel
            insideInfo.add(init, init.getName());

            // Display the UI onto the user
            cl.show(insideInfo, init.getName());

            // Disable the parent button
            init.getParentButton().setEnabled(false);

            // disabling the spouse button
            init.getSpouseButton().setEnabled(false);
            init.getSpouseButton().addActionListener(this);

            // disabling the child button
            init.getChildButton().setEnabled(false);
            init.getChildButton().addActionListener(this);

            init.getEditButton().addActionListener(new EditTree());
            init.getDoneButton().setVisible(false);
            init.getBackButton().setVisible(true);
            init.getBackButton().setEnabled(true);

            // setting the actionlistener to this fro the back button
            init.getSaveButton().setActionCommand(this.SAVE);
            init.getSaveButton().addActionListener(new SaveButton());
            init.getBackButton().setActionCommand(BACK_BEFORE_SAVE);
            init.getBackButton().addActionListener(new BackButton());
            init.getBottomLabel().setText("Unsaved Info will be LOST: ");

        }
    }

    private class SaveButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // Get the action command to check which button pressed
            String event = e.getActionCommand();

            // Get the layout to enable switching between layouts
            CardLayout cl = (CardLayout) insideInfo.getLayout();

            if (event.equals(SAVE_EDIT)) {
                frame.getStatusLabel().setBackground(Color.black);
                try {
                    int index = members.indexOf((Member) memberStack.peek());
                    Member trial = members.remove(index);
                    getFromJFields((InfoPanel)panelStack.peek(), trial, trial.getId());
                    members.add(index, trial);

                    init.getEditButton().setEnabled(true);
                    init.getEditButton().setVisible(true);
                    init.getEditButton().setActionCommand(EDIT);
                    init.getSaveButton().setEnabled(false);
                    init.getSaveButton().setVisible(false);

                    changeTextFields(init, false);
                    preview.setRoot((Member) memberStack.peek(), true);
                } catch (CloneNotSupportedException | UnknownOpException |
                        NumberFormatException op) {
                    frame.getStatusLabel().setText("Error: " + op.getMessage());
                    frame.getStatusLabel().setForeground(Color.red);
                }
            }

            if (event.equals(SAVE_TO)) {
                frame.getStatusLabel().setBackground(Color.black);
                try {
                    tree.saveToFile(members, saveFilePath);
                    changeTextFields(init, false);
                } catch (IOException file) {
                }
            }

            if (event.equals(SAVE_AS)) {
                frame.getStatusLabel().setBackground(Color.black);
                JFileChooser save = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());
                save.setDialogTitle("Save As...");
                save.setFileSelectionMode(JFileChooser.FILES_ONLY);

                int rV = save.showSaveDialog(frame);
                if (rV == JFileChooser.APPROVE_OPTION) {
                    if (save.getSelectedFile() != null) {
                        saveFilePath = save.getSelectedFile();
                        try {
                            tree.saveToFile(members, saveFilePath);
                            changeTextFields(init, false);
                            preview.setRoot(members.get(0), true);
                            init.getSpouseButton().setEnabled(false);
                            init.getChildButton().setEnabled(false);
                            init.getParentButton().setEnabled(false);
                        } catch (IOException file) {
                        }
                    }
                }
            }

            if (event.equals(SAVE)) {
                frame.getStatusLabel().setBackground(Color.black);
                rootView = new Member();

                int id = 0;
                for (Member p : members) {
                    if (p.getId() > id) {
                        id = p.getId();
                    }
                }

                // Since never called upon unless the first person is saved onto 
                // stack
                Member previousMember = null;

                if (!memberStack.isEmpty()) {
                    previousMember = (Member) memberStack.peek();
                }

                if (members.isEmpty()) {
                    try {

                        getFromJFields(init, rootView, id);

                        init.getSpouseButton().setEnabled(true);
                        init.getChildButton().setEnabled(true);
                        init.getParentButton().setEnabled(true);
                        init.getDoneButton().setEnabled(true);
                        init.getEditButton().setVisible(true);
                        init.getEditButton().setEnabled(true);
                        init.getEditButton().setActionCommand(EDIT);
                        init.getSaveButton().setEnabled(false);
                        init.getSaveButton().setVisible(false);
                        frame.getStatusLabel().setText(
                                "Information saved on system.");

                        changeTextFields(init, false);

                        members.add(rootView);

                        memberStack.push(rootView);
                        panelStack.push(init);

                    } catch (UnknownOpException | CloneNotSupportedException |
                            NumberFormatException op) {
                        frame.getStatusLabel().setText("Error: " + op.getMessage());
                        frame.getStatusLabel().setForeground(Color.red);

                    }
                } else if (members.contains(previousMember)) {

                    if (whichMember.equals(MEMBER[0])) {
                        try {

                            getFromJFields(init, rootView, id);
                            rootView.addChild(previousMember);

                            // Assuming that both parents are married
                            if (!previousMember.getParent().isEmpty()) {
                                members.get(members.indexOf(previousMember))
                                        .getParent().get(0)
                                        .setSpouse(rootView);
                                rootView.setSpouse(members.get(
                                        members.indexOf(previousMember))
                                        .getParent().get(0));
                            }

                            members.get(members.indexOf(previousMember))
                                    .addParent(rootView);

                            members.add(rootView);
                            init.getBackButton().setActionCommand(BACK_AFTER_SAVE);
                            init.getEditButton().setVisible(true);
                            init.getEditButton().setEnabled(true);
                            init.getEditButton().setActionCommand(EDIT);
                            init.getSaveButton().setEnabled(false);
                            init.getSaveButton().setVisible(false);
                            frame.getStatusLabel().setText("Information saved on system.");

                            changeTextFields(init, false);
                            memberStack.push(rootView);
                            panelStack.push(init);

                        } catch (UnknownOpException | CloneNotSupportedException |
                                NumberFormatException op) {
                            frame.getStatusLabel().setText("Error: "
                                    + op.getMessage());
                            frame.getStatusLabel().setForeground(Color.red);

                        }
                    } else if (whichMember.equals(MEMBER[1])) {
                        try {

                            getFromJFields(init, rootView, id);

                            rootView.addParent(previousMember);

                            if (previousMember.hasSpouse()) {
                                previousMember.getSpouse().addChild(rootView);
                            }

                            members.get(members.indexOf(previousMember))
                                    .addChild(rootView);
                            members.add(rootView);
                            memberStack.push(rootView);

                            init.getSpouseButton().setEnabled(true);
                            init.getChildButton().setEnabled(true);
                            init.getBackButton().setActionCommand(BACK_AFTER_SAVE);
                            init.getEditButton().setVisible(true);
                            init.getEditButton().setEnabled(true);
                            init.getEditButton().setActionCommand(EDIT);
                            init.getSaveButton().setEnabled(false);
                            init.getSaveButton().setVisible(false);
                            frame.getStatusLabel().setText("Information saved on system.");

                            changeTextFields(init, false);
                            panelStack.push(init);

                        } catch (UnknownOpException | CloneNotSupportedException |
                                NumberFormatException op) {
                            frame.getStatusLabel().setText("Error: "
                                    + op.getMessage());
                            frame.getStatusLabel().setForeground(Color.red);

                        }
                    } else if (whichMember.equals(MEMBER[2])) {
                        try {

                            getFromJFields(init, rootView, id);

                            rootView.addParent(previousMember);

                            if (previousMember.hasSpouse()) {
                                previousMember.getSpouse()
                                        .addChild(rootView);
                            }

                            members.get(members.indexOf(previousMember))
                                    .addChild(rootView);
                            members.add(rootView);
                            memberStack.push(rootView);

                            init.getSpouseButton().setEnabled(true);
                            init.getBackButton().setActionCommand(BACK_AFTER_SAVE);
                            init.getEditButton().setVisible(true);
                            init.getEditButton().setEnabled(true);
                            init.getEditButton().setActionCommand(EDIT);
                            init.getSaveButton().setEnabled(false);
                            init.getSaveButton().setVisible(false);
                            frame.getStatusLabel().setText("Information saved on system.");

                            changeTextFields(init, false);

                            panelStack.push(init);

                        } catch (UnknownOpException | CloneNotSupportedException |
                                NumberFormatException op) {
                            frame.getStatusLabel().setText("Error: " + op.getMessage());
                            frame.getStatusLabel().setForeground(Color.red);

                        }
                    } else if (whichMember.equals(MEMBER[3])) {

                        try {

                            getFromJFields(init, rootView, id);
                            rootView.setSpouse(previousMember);
                            members.get(members.indexOf(previousMember))
                                    .setSpouse(rootView);

                            if (!previousMember.getChildren().isEmpty()) {
                                for (Member child : previousMember.getChildren()) {
                                    rootView.addChild(child);
                                }
                            }

                            memberStack.push(rootView);
                            members.add(rootView);

                            init.getParentButton().setEnabled(true);
                            init.getEditButton().setVisible(true);
                            init.getEditButton().setEnabled(true);
                            init.getEditButton().setActionCommand(EDIT);
                            init.getSaveButton().setEnabled(false);
                            init.getSaveButton().setVisible(false);
                            frame.getStatusLabel().setText("Information saved on system.");

                            changeTextFields(init, false);

                            init.getBackButton().setActionCommand(BACK_AFTER_SAVE);
                            // Adding Action listeners for the init panel buttons

                            panelStack.push(init);

                        } catch (UnknownOpException | CloneNotSupportedException |
                                NumberFormatException op) {
                            frame.getStatusLabel().setText("Error: " + op.getMessage());
                            frame.getStatusLabel().setForeground(Color.red);

                        }

                    }
                }
                if (!editFlag) {
                    preview.setRoot(members.get(0), false);
                } else {
                    preview.setRoot(members.get(members.indexOf(previousMember)), true);
                }

            }
        }
    }

    private class BackButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String event = e.getActionCommand();

            CardLayout cl = (CardLayout) insideInfo.getLayout();

            if (event.equals(BACK_BEFORE_SAVE)) {
                System.out.println("p " + panelStack.size());
                InfoPanel trial = (InfoPanel) panelStack.peek();

                cl.show(insideInfo, trial.getName());
            } else if (event.equals(BACK_AFTER_SAVE)) {
                // switching back to the initial panel

                InfoPanel trial;

                if (panelStack.size() == 1) {
                    trial = (InfoPanel) panelStack.peek();
                } else {
                    panelStack.pop();
                    memberStack.pop();
                    trial = (InfoPanel) panelStack.peek();
                }

                Component[] components = (Component[]) insideInfo
                        .getComponents();

                for (Component component : components) {
                    if (component == trial) {
                        checkParents(members, (Member) memberStack.peek(),
                                (InfoPanel) component);
                        checkSpouse(members, (Member) memberStack.peek(),
                                (InfoPanel) component);
                        insideInfo.remove(component);
                        insideInfo.add(component, component.getName());
                    }
                }

                cl.show(insideInfo, trial.getName());

            }
        }
    }

    private class EditTree implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String event = e.getActionCommand();

            if (event.equals(EDIT)) {
                changeTextFields(init, true);
                frame.getWindowMenuBar().getSaveAs().setEnabled(true);
                frame.getWindowMenuBar().getSave().setEnabled(true);

                if (((Member) memberStack.peek()).getParent().isEmpty()
                        || ((Member) memberStack.peek()).getParent().size() == 1) {
                    init.getParentButton().setEnabled(true);
                }
                if (!((Member) memberStack.peek()).hasSpouse()) {
                    init.getSpouseButton().setEnabled(true);
                }

                init.getChildButton().setEnabled(true);

                init.getSaveButton().setVisible(true);
                init.getSaveButton().setActionCommand(SAVE_EDIT);
                init.getEditButton().setVisible(false);
                init.getSaveButton().setEnabled(true);
                preview.setRoot((Member) memberStack.peek(), false);

                init.getDoneButton().setVisible(true);
                init.getDoneButton().setEnabled(true);
                init.getDoneButton().setActionCommand(SAVE_AS);
            }
        }
    }

    private void getFromJFields(InfoPanel p, Member em, int id) throws
            CloneNotSupportedException, UnknownOpException {
        if (p.getFirstName().getText() == null
                || (p.getSurnameBirth().getText().equals("")
                && p.getSurnameMarriage().getText().equals(""))
                || p.getStreetNumber().getText().equals("")
                || p.getStreetName().getText().equals("")
                || p.getSuburb().getText().equals("")
                || p.getPostCode().getText().equals("")) {
            throw new UnknownOpException("Empty Fields");
        }

        em.setId(id + 1);
        em.setFirstName(p.getFirstName().getText());
        em.setSurnameBirth(p.getSurnameBirth().getText());
        em.setSurnameMarriage(p.getSurnameMarriage().getText());
        em.setGender((String) p.getGender().getSelectedItem());
        em.setDescription(p.getDescription().getText());
        em.setAddress(new Address(
                Integer.parseInt(p.getStreetNumber().getText()),
                p.getStreetName().getText(), p.getSuburb().getText(),
                Integer.parseInt(p.getPostCode().getText())));
    }

    private void checkParents(ArrayList<Member> mems, Member pm, InfoPanel in) {
        if (mems.get(mems.indexOf(pm)).getParent().size() == 2) {
            in.getParentButton().setEnabled(false);
        }
    }

    private void checkSpouse(ArrayList<Member> mems, Member pm, InfoPanel in) {
        if (mems.get(mems.indexOf(pm)).hasSpouse()) {
            in.getSpouseButton().setEnabled(false);
        }
    }

    private void changeTextFields(InfoPanel init, boolean flag) {
        init.getFirstName().setEditable(flag);
        init.getSurnameBirth().setEditable(flag);
        init.getSurnameMarriage().setEditable(flag);
        init.getGender().setEditable(flag);
        init.getGender().setEnabled(flag);
        init.getDescription().setEditable(flag);
        init.getStreetName().setEditable(flag);
        init.getStreetNumber().setEditable(flag);
        init.getPostCode().setEditable(flag);
        init.getSuburb().setEditable(flag);
    }
}
