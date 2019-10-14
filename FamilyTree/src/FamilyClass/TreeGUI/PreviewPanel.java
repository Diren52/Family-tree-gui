/*
 * Title: Mr
 * Author: Joseph Sigar
 * Date: 25/05/18
 * File name: Tree GUI
 * 
 * Purpose: To enable the GUI to lay out the family tree and connect the 
 * necessary individuals wih a line. Show casing a tree.
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

import FamilyClass.Member;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

/**
 * A Class that allow GUI to lay out the family tree and connect them using
 * lines.
 *
 * @version 2.02 25 May 2018
 * @author Joseph Sigar
 */
public class PreviewPanel extends JPanel implements ActionListener {

    private final ArrayList<Connect> connecting;
    private final ArrayList<JButton> buttonList;
    private final ArrayList<Member> members;

    private Member root;
    private final Trial trial;

    private boolean flag;

    private ModePanel view;

    /**
     * A Constructor of the Preview panel class. Initializers the required 
     * variables for this 
     */
    public PreviewPanel() {
        connecting = new ArrayList<>();
        buttonList = new ArrayList<>();
        members = new ArrayList<>();
        trial = new Trial();
        flag = false;
    }

    /**
     * An Initializer Constructor. The constructor initializes the ModePanel 
     * class to this class.
     * @param view - a ModePanel class object to indicate that the program is in
     * view mode
     * @param m - an arraylist members of the family tree
     */
    public PreviewPanel(ModePanel view, ArrayList<Member> m) {
        this.view = view;
        connecting = new ArrayList<>();
        buttonList = new ArrayList<>();
        members = m;
        trial = new Trial();
        flag = true;
    }

    /**
     * Sets the PreviewPanel to the given Member object. This allows the tree
     * to be constructed for that particular object. The Boolean flag is to 
     * indicate whether the class is used for viewing or editing.
     * @param root -  a member object for the tree to be constructed on.
     * @param flag -  to switch the tree between view mode and edit mode
     */
    public void setRoot(Member root, boolean flag) {
        trial.removeAll();
        trial.validate();
        this.root = root;
        this.flag = flag;
        this.connecting.clear();
        this.buttonList.clear();
        createUI(this.root);
        allowChangeRoot(this.flag);
    }

    private void createUI(Member root) {
        // Creates the display for the given Member object
        
        trial.setOpaque(true); // To be able to draw onto this panels it 
                               // needs to be Opaque.
        this.setBorder(BorderFactory.createTitledBorder("Tree Preview: "));
        
        trial.setLayout(new GridBagLayout()); // Easier to arrange the buttons
                                              // accordingly with this layout
        GridBagConstraints c = new GridBagConstraints();

        c.gridy = 0;
        c.gridx = 2;
        JLabel me = new JLabel("Viewing");
        me.setForeground(Color.gray);
        trial.add(me, c);

        // Needs to be initialized here to allow better rearrangement below
        JButton crnt = makeButton(root.getFirstName(), "" + root.getId());

        int pos1 = 1;
        int pos2 = 2;
        int t = 1;
        
        if (!root.getChildren().isEmpty()) {
            c.gridy = 0;
            c.gridx = 6;
            JLabel label = new JLabel("Childlren");
            label.setForeground(Color.gray);
            trial.add(label, c);
            for (int i = 0; i < root.getChildren().size(); i++) {

                JButton child = makeButton(root.getChildren()
                        .get(i).getFirstName(), "" + root.getChildren()
                        .get(i).getId());
                if (!root.getChildren().get(i).getChildren().isEmpty()) {
                    c.gridy = 0;
                    c.gridx = 9;
                    JLabel parent = new JLabel("GrandChild");
                    parent.setForeground(Color.gray);
                    trial.add(parent, c);
                    
                    for (int j = 0; j < root.getChildren().get(i)
                            .getChildren().size(); j++) {
                        c.gridx = 9;
                        c.gridy = pos2;
                        c.insets = new Insets(10, 10, 10, 10);
                        JButton gChild = makeButton(root.getChildren()
                                .get(i).getChildren().get(j).getFirstName(), ""
                                + root.getChildren()
                                .get(i).getChildren().get(j).getId());
                        // add a connecton between the child and the parent
                        connecting.add(new Connect(child, gChild));
                        trial.add(gChild, c);

                        buttonList.add(gChild);
                        pos2++;
                    }
                }
                // A little math magic here to enable rearrangement 
                // The gist of it. 
                // Have 2 positions and take the difference between them.
                // Divide by 2 and the round up. lastly add the first position.
                // and the set the first position to be where the 2nd position 
                // was.
                t = pos1 + (int) Math.floor((pos2 - pos1) / 2);
                pos1 = pos2;
                c.gridx = 6;
                c.gridy = t;
                c.insets = new Insets(10, 10, 10, 10);

                connecting.add(new Connect(crnt, child));

                buttonList.add(child);
                trial.add(child, c);
            }
        }

        // Get the number of childrena and divide by 2. thats where the root
        // button stays.
        int x1;
        if (t % 2 == 0) {
            x1 = t / 2;
        } else {
            x1 = (int) Math.floor(t / 2);
        }

        
        c.insets = new Insets(10, 10, 10, 10);
        c.gridx = 2;
        c.gridy = x1+1;
        members.add(root);

        buttonList.add(crnt);
        trial.add(crnt, c);

        if (root.hasSpouse()) {
            c.gridy = x1 + 2;
            c.gridx = 2;
            JButton spse = makeButton(root.getSpouse().getFirstName(),
                    "" + root.getSpouse().getId());
            buttonList.add(spse);
            trial.add(spse, c);
            connecting.add(new Connect(crnt, spse));
        }

        if (!root.getParent().isEmpty()) {
            int count = root.getParent().size();

            c.gridy = 0;
            c.gridx = 0;
            JLabel parent = new JLabel("Parent");
            parent.setForeground(Color.gray);
            trial.add(parent, c);

            if (count == 2) {
                c.gridy = x1+1;
                c.gridx = 0;
                members.add(root.getParent().get(0));
                JButton par1 = makeButton(root.getParent()
                        .get(0).getFirstName(), "" + root.getParent()
                        .get(0).getId());
                trial.add(par1, c);
                c.gridy = x1 + 2;
                JButton par2 = makeButton(root.getParent()
                        .get(1).getFirstName(), "" + root.getParent()
                        .get(1).getId());
                trial.add(par2, c);
                buttonList.add(par1);
                buttonList.add(par2);
                connecting.add(new Connect(par1, par2));
                connecting.add(new Connect(par1, crnt));
            } else {
                c.gridx = 0;
                c.gridy = x1+1;
                JButton par1 = makeButton(root.getParent()
                        .get(0).getFirstName(), "" + root.getParent()
                        .get(0).getId());
                trial.add(par1, c);
                buttonList.add(par1);
                connecting.add(new Connect(par1, crnt));
            }
        }
        
        this.add(trial, BorderLayout.CENTER);
        trial.revalidate();
        UIManager.put("JButton.disabledForeground", Color.BLACK);
        UIManager.put("JButton.disabledBackground", trial.getBackground());
    }

    private JButton makeButton(String buttonName, String buttonCommand) {
        // Enables te creation of buttons.
        JButton demo = new JButton();
        demo.setText(buttonName);
        demo.setActionCommand(buttonCommand);
        demo.setEnabled(false);
        demo.setPreferredSize(new Dimension(100, 20));
        demo.addActionListener(this);

        return demo;
    }

    private void allowChangeRoot(Boolean flag) {
        // Enables the switching from view(true) to edit(false).
        this.flag = flag;

        if (flag == true) {
            buttonList.stream().forEach((JButton jb) -> {
                jb.setEnabled(true);
                jb.addActionListener(PreviewPanel.this);
            });
        } else {
            buttonList.stream().forEach((JButton jb) -> {
                jb.setEnabled(false);
            });
        }
    }

    /**
     * Required since The class implements the Action Listener interface.
     * Performs the given actions when a button is pressed.
     * @param ed -  the action that is performed.
     */
    @Override
    public void actionPerformed(ActionEvent ed) {
        String event = ed.getActionCommand();
        int id;
        boolean miniFlag = false; // Allows the break from the loop when the 
                                  // required action has been fulfilled
        for (JButton jb : buttonList) {
            if (event.equals(jb.getActionCommand())) {
                for (Member m : members) {
                    id = Integer.parseInt(jb.getActionCommand());
                    if (m.getId() == id) {
                        trial.removeAll();
                        trial.validate();
                        this.connecting.clear();
                        this.buttonList.clear();
                        createUI(members.get(members.indexOf(m)));
                        allowChangeRoot(true);
                        this.view.setRoot(m);
                        this.view.validate();
                        miniFlag = true;
                        break;
                    }
                }

                if (miniFlag == true) {
                    break;
                }
            }
        }
    }

    private class Trial extends JPanel {
        // Allows the drawing of the Lines between two connect buttons.
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D tri = (Graphics2D) g.create();
            PreviewPanel.this.setBackground(Color.white);

            Trial.this.setBackground(PreviewPanel.this.getBackground());
            tri.setColor(Color.BLACK);

            connecting.stream().forEach((connect) -> {
                JComponent src = connect.getSource();
                
                JComponent dst = connect.getDest();

                if (src.getX() == dst.getX()) { 
                    // if they are in the same column
                    tri.drawLine(src.getX() + src.getWidth() / 2, src.getY(),
                            dst.getX() + dst.getWidth() / 2, dst.getY());
                } else if (src.getY() == dst.getY()) {
                    // if they are in the same row
                    tri.drawLine(src.getX(), src.getY() + src.getHeight() / 2,
                            dst.getX(), dst.getY() + dst.getHeight() / 2);
                } else if (src.getX() > dst.getX()) {
                    // otherwise.
                    tri.drawLine(src.getX(), src.getY() + src.getHeight() / 2,
                            dst.getX() + dst.getWidth(), dst.getY()
                                    + dst.getHeight() / 2);
                } else if (src.getX() < dst.getX()) {
                    tri.drawLine(dst.getX(), dst.getY() + dst.getHeight() / 2,
                            src.getX() + src.getWidth(), src.getY()
                                    + src.getHeight() / 2);
                } else if (src.getY() > dst.getY()) {
                    tri.drawLine(src.getX() + src.getWidth() / 2, src.getY(),
                            dst.getX() + dst.getWidth() / 2, dst.getY()
                                    + dst.getHeight());
                } else if (src.getY() < dst.getY()) {
                    tri.drawLine(dst.getX() + dst.getWidth() / 2, dst.getY(),
                            src.getX() + src.getWidth() / 2, src.getY()
                                    + src.getHeight());
                }
            });

            tri.dispose();

        }
    }

}
