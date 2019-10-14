/*
 * Title: Mr
 * Author: Joseph Sigar
 * Date: 25/05/18
 * File name: Tree GUI
 * 
 * Purpose: To enable all the contents to be contained and viewed within a frame.
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
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.*;
import FamilyClass.*;
import java.io.*;
import java.util.*;

/**
 * A Class that Holds all the contents of the graphic interface on a frame..
 *
 * @version 2.02 25 May 2018
 * @author Joseph Sigar
 */
public class MainFrame extends JFrame {

    private final MenuBar menu;
    private final HomePanel home;
    private CardLayout panels;
    private static final int HEIGHT = 500;
    private static final int WIDTH = 900;
    private final JPanel status;
    private final JLabel statusLabel;
    private ArrayList<Member> members;

    /**
     * A constructor that creates and initializers the frame. Creates the frame
     * by specifying the dimensions and the contents that would be preset on the
     * frame.
     */
    public MainFrame() {
        super("Family Tree Desktop App.");

        this.getContentPane().setLayout(new BorderLayout(5, 5));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.menu = new MenuBar();
        this.setJMenuBar(menu);
        
        home = new HomePanel();

        // Introducing a Status label to inform the user of any problems in the
        // programs.. or the process.
        status = new JPanel();
        statusLabel = new JLabel("Welcome...");

        status.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.black), "Program Status",
                TitledBorder.RIGHT, TitledBorder.TOP));
        status.setPreferredSize(new Dimension(this.getWidth(), 35));
        status.setLayout(new BoxLayout(status, BoxLayout.LINE_AXIS));
        status.add(statusLabel);

        // Disallow the resizing of the window.
        this.setResizable(false);
        this.add(status, BorderLayout.SOUTH);
        this.setSize(WIDTH, HEIGHT);

        //Everything in the middle
        MainPanel main = new MainPanel();
        this.add(main, BorderLayout.CENTER);

    }

    /**
     * Returns a JMenuBar specifically for this Frame and Program.
     * @return a JMenuBar
     */
    public MenuBar getWindowMenuBar() {
        return this.menu;
    }

    /**
     * Returns a JLabel that shows the status of the program.
     * @return a JLabel
     */
    public JLabel getStatusLabel() {
        return this.statusLabel;
    }

    /**
     * A inner class that holds the contents that would go onto the frame.
     * It also allows switching between the frames using a cradLayout feature.
     */
    public class MainPanel extends JPanel {

        /**
         * A constructor for the MainPanel class. Creates the contents and
         * positions them to the required place.
         */
        public MainPanel() {
            // A card layout manager to allow the screen to switch from the
            // current screen to the one the user specifies.
            panels = new CardLayout();
            this.setLayout(panels);
            this.add(home, "home");
            
            // Allows the user to exit the system using the exit menu item
            menu.getExit().addActionListener((ActionEvent el) -> {
                System.exit(0);
            });

            menu.getNewTree().addActionListener(new NewTree());
            menu.getLoad().addActionListener(new LoadFromFile());
            
            // Disables the save buttons as they are not needed in the beginning.
            menu.getSave().setEnabled(false);
            menu.getSaveAs().setEnabled(false);
            home.getNewTreeBtn().addActionListener(new NewTree());
            home.getFromFileBtn().addActionListener(new LoadFromFile());
        }

        private class NewTree implements ActionListener {
            // Redirects the user to the edit mode of creating a new tree
            @Override
            public void actionPerformed(ActionEvent el) {
                ModePanel info = new ModePanel(MainFrame.this);
                MainPanel.this.add(info, "editMode");
                CardLayout cL = (CardLayout) MainPanel.this.getLayout();
                cL.show(MainPanel.this, "editMode");

            }
        }

        private class LoadFromFile implements ActionListener {
            // Redirects the user to the view mode. allows the user to view a
            // selected tree.
            @Override
            public void actionPerformed(ActionEvent el) {
                JFileChooser load = new JFileChooser(FileSystemView
                        .getFileSystemView().getDefaultDirectory());
                load.setDialogTitle("Select a family tree");
                load.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter fil
                        = new FileNameExtensionFilter(" .ser files", "ser");
                load.addChoosableFileFilter(fil);
                int rV = load.showOpenDialog(null);
                if (rV == JFileChooser.APPROVE_OPTION) {

                    try {
                        TreeIO tree = new TreeIO();
                        tree.loadFromFile(load.getSelectedFile());
                        statusLabel.setText(load.getSelectedFile().getName());
                        members = new ArrayList<>();
                        members = (ArrayList<Member>) tree.getFamilyMembers().clone();

                        ModePanel viewMode = new ModePanel(MainFrame.this, members, load.getSelectedFile());
                        viewMode.setRoot(members.get(0));
                        MainPanel.this.add(viewMode, "ViewMode");
                        CardLayout cL = (CardLayout) MainPanel.this.getLayout();
                        cL.show(MainPanel.this, "ViewMode");
                    } catch (IOException | ClassNotFoundException file) {
                        MainFrame.this.getStatusLabel()
                                .setText(file.getMessage());
                        Color cor1 = Color.red;
                        MainFrame.this.getStatusLabel().setForeground(cor1);
                    }
                }
            }
        }
    }
}
