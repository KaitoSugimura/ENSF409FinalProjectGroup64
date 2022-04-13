/**
@author Danny Duong, Kaito Sugimura, Kevin Johnson, Joshua Walters
@version 2.3
@since 1.0
*/
package edu.ucalgary.ensf409;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/**    ****** ******  MAIN CLASS ****** ******  
 * 
 *  Please run this class to run the entire program
 *  Creates the main GUI and an instance of the application
 *  Read setUpGUI() for more detail on GUI functionality
 * 
 */
public class SystemControlGUI extends JFrame {

    private JPanel rightPanel;
    private JPanel leftPanel;
    private JPanel leftCenterHamperPanel;
    private JPanel rightCenterTextFieldPanel;

    private JLabel currentLabel;
    private JButton reviewButton;

    private ArrayList<ConfigurationPanelGUI> hamperConfigPanels = new ArrayList<>();
    private ArrayList<JButton> hamperButtons = new ArrayList<>();
    private int hamperNo = 0;
    private int currentPanelIndex = -1;

    private Application app;

    public static Boolean buttonNotRecentlyPressed = true; // To make review changes button unspammable 

    /**  *******  Main function  *******
     * Run the Entire GUI and Application
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new SystemControlGUI().setVisible(true);        
        });
    }

    /**
     * Contructor, calls setupGUI for further panel setups
     * Create an instance of application 
     */
    public SystemControlGUI() {
        super("Food Bank Order Tool");
        this.setSize(960, 660);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setupGUI();
        this.setVisible(true);
        this.app = new Application();
    }

    /**
     * The GUI will be split up in 2 sections:
     * A Left panel and a Right panel
     * The Left will contain a list of all the different selectable Hampers
     * The Right will contain all the specifications of each selected Hamper 
     */
    public void setupGUI(){
    
        // **** **** LEFT PANEL **** ****
        createLeftPanel(); // Create and set up the Left Panel
        /* Create three panels inside Left panel:
         *  Top Label 
         *  Center List of Hampers
         *  Bottom buttons for adding/removing Hampers
         */
        JLabel leftPanelTopLabel = createLeftPanelTopLabel();         // Top
        this.leftCenterHamperPanel = createLeftPanelCenterPanel();   // Center
        JPanel leftPanelButtons = createLeftPanelHamperConfigButtons(); // Bottom
        
        // add Top, Center, Bottom panels
        leftPanel.add(leftPanelTopLabel, BorderLayout.PAGE_START);
        leftPanel.add(leftCenterHamperPanel, BorderLayout.CENTER);
        leftPanel.add(leftPanelButtons, BorderLayout.PAGE_END);
        this.add(leftPanel);  // Add Panel to JFrame

        // **** **** RIGHT PANEL **** ****

        createRightPanel();     // Create and set up the Right Panel
        createRightCenterLabel();  // Create currentLabel

        JPanel rightPanelTopLabel = createRightPanelTopPanel();
        rightCenterTextFieldPanel = createRightCenterTextFieldPanel();
        rightCenterTextFieldPanel.add(currentLabel);
        setupReviewButton();    // Create submit button

        rightPanel.add(rightPanelTopLabel, BorderLayout.PAGE_START);
        rightPanel.add(rightCenterTextFieldPanel, BorderLayout.CENTER);
        rightPanel.add(reviewButton, BorderLayout.PAGE_END);
        this.add(rightPanel);  // Add Panel to JFrame

    }


    // **** **** CREATE PANELS **** ****

    /** Create the left panel; this panel will contain 3 more sub sections */
    private void createLeftPanel(){
        leftPanel = new JPanel();
        leftPanel.setBackground(new Color(0x434e62));
        leftPanel.setBounds(0, 0, 300, 620);
        leftPanel.setLayout(new BorderLayout());
    }
    /** Create the right panel; this panel will contain 3 more sub sections */
    private void createRightPanel(){
        rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBounds(300, 0, 660, 620);
    }
    /** 
     * Create left top section panel; contains a simple title "Hampers" 
     * @return panel 
     */
    private JLabel createLeftPanelTopLabel(){
        JLabel label = new JLabel();
        label.setText("Hampers");
        label.setForeground(Color.white);
        label.setPreferredSize(new Dimension(300, 100));
        label.setFont(new Font("Comic Sans", Font.BOLD, 42));
        label.setHorizontalAlignment(JLabel.CENTER);
        return label;
    }
    /**
     * Create left center section panel, contains the list of hampers
     * The list of hampers will be buttons to select a working hamper 
     * @return panel 
     */
    private JPanel createLeftPanelCenterPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 1));
        panel.setBackground(new Color(0x7588ac));
        return panel;
    }

    /** 
     * Create two buttons, one to add a hamper, one to remove a selected hamper
     * @return created panel of buttons
     */
    private JPanel createLeftPanelHamperConfigButtons(){
        JButton addNewHampersButton = createAddNewHampersButton();
        JButton addRemoveHampersButton = createRemoveHampersButton();
        
        JPanel leftPanelButtons = new JPanel();
        leftPanelButtons.setPreferredSize(new Dimension(300, 110));
        leftPanelButtons.add(addNewHampersButton, BorderLayout.PAGE_START);
        leftPanelButtons.add(addRemoveHampersButton, BorderLayout.PAGE_END);
        return leftPanelButtons;
    }

    /**
     * Create right center section panel, contains the configure panel for the selected hamper
     * A ConfigurationPanelGUI will be added upon user hamper addition  
     * @return created panel 
     */
    private JPanel createRightCenterTextFieldPanel(){
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0xa8c3f7));
        return panel;
    }

    /** Create label for right center panel */
    private void createRightCenterLabel(){
        currentLabel = new JLabel();
        currentLabel.setText("Please select a Hamper");
        currentLabel.setFont(new Font("Comic Sans", Font.BOLD, 30));
        currentLabel.setPreferredSize(new Dimension(400, 50));
    }
    /**
     * Create right top section panel, contains the title and ResetAll button
     * Contains multiple panels just to adjust style
     * @return created panel
     */
    private JPanel createRightPanelTopPanel(){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(660, 100));
        panel.setBackground(new Color(0x97afde));

        JLabel label = new JLabel();
        label.setText("Configure Hampers");
        label.setFont(new Font("Comic Sans", Font.BOLD, 42));
        label.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 40));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        buttonPanel.setBackground(new Color(0x97afde));
        JButton resetButton = createResetAllButton();
        buttonPanel.add(resetButton);

        panel.add(label);
        panel.add(buttonPanel);

        return panel;
    }

    // **** **** BUTTON SET UP **** ****

    /** 
     * Review order button setup
     * If button is pressed:
     * 1. Remove any existing clients
     * 2. Obtain values of each hamper text field and append into the application
     * 3. Calculate the order through the application
     * 4. Open up the confirmation screen GUI
     * The function catches any exceptions and gives the User a Message
     */
    private void setupReviewButton(){
        reviewButton = new JButton();
        reviewButton.setText("Review/Calculate Order");
        reviewButton.setFocusable(false);
        reviewButton.setPreferredSize(new Dimension(300, 110));
        reviewButton.setFont(new Font("Comic Sans", Font.BOLD, 42));
        // ** ACTION LISTENER ** 
        reviewButton.addActionListener(event -> {
            if(buttonNotRecentlyPressed){
                buttonNotRecentlyPressed = false;
                app.removeAllClients(); // Remove any existing clients
                int male;
                int female;
                int childOver8;
                int childUnder8;

                for(ConfigurationPanelGUI config : hamperConfigPanels){
                    try{
                        male = parseInt(config.getMaleText());
                        female = parseInt(config.getFemaleText());
                        childOver8 = parseInt(config.getChildOver8Text());
                        childUnder8 = parseInt(config.getChildUnder8Text());
                    } catch (NumberFormatException e){
                        return;
                    }
                    app.addClient(hamperConfigPanels.indexOf(config), ClientType.ADULT_MALE, male);
                    app.addClient(hamperConfigPanels.indexOf(config), ClientType.ADULT_FEMALE, female);
                    app.addClient(hamperConfigPanels.indexOf(config), ClientType.CHILD_OVER_8, childOver8);
                    app.addClient(hamperConfigPanels.indexOf(config), ClientType.CHILD_UNDER_8, childUnder8);
                }
                try{
                    app.calculateOrder();
                    new ConfirmationGUI(this, app);
                } catch (HamperHasNoClientsException e){
                    JOptionPane.showMessageDialog(this, "Please add a Hamper or add at least one Client to each Hamper");
                    buttonNotRecentlyPressed = true;
                } catch (InsufficientInventoryException e){
                    JOptionPane.showMessageDialog(this, app.getInventory().getShortagesAsString());
                    buttonNotRecentlyPressed = true;
                }
            }
        });
    }

    /** 
     * Button that removes a selected hamper
     * The remove button can not be spammed
     * That is, the user must first select the hamper they wish to delete
     * @return the created button
     */
    private JButton createRemoveHampersButton(){
        JButton button = new JButton();
        button.setText("Remove Hamper");
        button.setFocusable(false);
        button.setPreferredSize(new Dimension(300, 50));
        button.setFont(new Font("Comic Sans", Font.BOLD, 25));
        // ** ACTION LISTENER ** 
        button.addActionListener(event -> {
            removeHamper();
            revalidate();
            repaint();
        });
        return button;
    }

    /** 
     * Button that adds a new hamper
     * The user will see a new added hamper
     * Currently selected hamper should still remain the same
     * @return the created button
     */
    private JButton createAddNewHampersButton(){
        JButton button = new JButton();
        button.setText("Add a new Hamper");
        button.setFocusable(false);
        button.setPreferredSize(new Dimension(300, 50));
        button.setFont(new Font("Comic Sans", Font.BOLD, 25));
        // ** ACTION LISTENER ** 
        button.addActionListener(event -> {
            createNewHamper();
            revalidate();
            repaint();
        });
        return button;
    }

    /** 
     * Reset everything button
     * All Hampers, Clients, etc are removed (reset)
     * @return the created button
     */
    private JButton createResetAllButton(){
        JButton resetButton = new JButton();
        resetButton.setText("Reset All");
        resetButton.setFocusable(false);
        resetButton.setPreferredSize(new Dimension(100, 50));
        resetButton.setFont(new Font("Arial", Font.PLAIN, 16));
        // ** ACTION LISTENER ** 
        resetButton.addActionListener(event -> {
            removeArrayElements();
            app.resetApplication();
            hamperNo = 0;
            currentPanelIndex = -1;
            revalidate();
            repaint();
        });
        return resetButton;
    }

    /** 
     * Create a Hamper button
     * This button will be created upon user hitting the "Add new Hamper" button
     * Will contain multiple instances inside the list of hampers
     * Button acts as a selection for the user to select their working hamper
     * The naming indexes should only increase, even after removal of previous Hampers
     * @return created button
     */
    private JButton createHamperListButton(){
        JButton button = new JButton();
        hamperButtons.add(button);
        button.setText("Hamper #" + ++hamperNo);
        button.setFocusable(false);
        button.setBounds(200, 100, 100, 100);
        button.setFont(new Font("Comic Sans", Font.PLAIN, 25));
        button.addActionListener(event -> {
            JButton thisButton = (JButton) event.getSource();

            if(currentPanelIndex != -1){
                hamperConfigPanels.get(currentPanelIndex).setVisibility(false);;
            }
            currentPanelIndex = hamperButtons.indexOf(thisButton);
            hamperConfigPanels.get(currentPanelIndex).setVisibility(true);
            currentLabel.setText("Configure: " + thisButton.getText());
        });
        return button;
    }

    // **** **** FUNCTIONALITY **** ****

    /** 
     * Functionality to create a new hamper
     * Creates a ConfigurationPanelGUI 
     * Adds the new config panel to the right Center panel
     * Adds the newly created button to the left center list
     * Adds a physical hamper in the application
     */
    private void createNewHamper(){
        var button = createHamperListButton();
        
        ConfigurationPanelGUI hamperConfig = new ConfigurationPanelGUI();
        hamperConfigPanels.add(hamperConfig);
        rightCenterTextFieldPanel.add(hamperConfig.getPanel());
        hamperConfig.setVisibility(false);;
        leftCenterHamperPanel.add(button);

        app.addHamper();
    }

    /** 
     * Remove a selected hamper
     * Should not remove if nothing is selected
     */
    private void removeHamper(){
        if(currentPanelIndex != -1){
            rightCenterTextFieldPanel.remove(hamperConfigPanels.get(currentPanelIndex).getPanel());
            hamperConfigPanels.remove(currentPanelIndex);

            leftCenterHamperPanel.remove(hamperButtons.get(currentPanelIndex));
            hamperButtons.remove(currentPanelIndex);

            app.removeHamper(currentPanelIndex);

            currentPanelIndex = -1;
            currentLabel.setText("Please select a Hamper");
        }

    }

    /** 
     * Remove the elements in the ArrayList
     * Aswell as reset ArrayList
     */
    private void removeArrayElements(){
        for(ConfigurationPanelGUI panel : hamperConfigPanels){
            rightCenterTextFieldPanel.remove(panel.getPanel());
        }
        for(JButton button : hamperButtons){
            leftCenterHamperPanel.remove(button);
        }
        hamperConfigPanels = new ArrayList<>();
        hamperButtons = new ArrayList<>();
    }

    /**
     * Parses the argument String
     * If the argument can simply be parsed, return the value
     * If the argument contains no characters ("") then return 0
     * Else rethrow the exception
     * @param arg Argument String
     * @return Parsed integer of string
     * @throws NumberFormatException
     */
    private int parseInt(String arg) throws NumberFormatException{
        try{
            return Integer.parseInt(arg);
        } catch(NumberFormatException e){
            if(arg.equals("")){
                return 0;
            }
            JOptionPane.showMessageDialog(this, "Please make sure all of your inputs are Integer values");
            buttonNotRecentlyPressed = true;
            throw new NumberFormatException();
        }
    }

}
