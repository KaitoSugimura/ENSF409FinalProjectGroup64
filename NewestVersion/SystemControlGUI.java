import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;


public class SystemControlGUI extends JFrame implements ActionListener {

    private JPanel rightPanel;
    private JPanel leftPanel;
    private JPanel leftCenterHamperPanel;
    private JPanel rightCenterTextFieldPanel;

    private JLabel currentLabel;
    private JButton reviewButton;

    private ArrayList<ConfigurationPannelGUI> hamperConfigPanels = new ArrayList<>();
    private ArrayList<JButton> hamperButtons = new ArrayList<>();
    private int hamperNo = 0;
    private int currentPannelIndex = -1;

    private Application app;

    public static Boolean buttonNotRecentlyPressed = true; // To make review changes button unspammable 

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new SystemControlGUI().setVisible(true);        
        });
    }

    public SystemControlGUI() {
        super("Food Bank Order Tool");
        setupGUI();
        this.setSize(960, 660);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.getContentPane().setBackground(new Color(0, 0, 0));
        this.setVisible(true);
        this.app = new Application();
    }

    public void setupGUI(){
        /* The GUI will be split up in 2 sections:
         * A Left panel and a Right panel
         * The Left will contain a list of all the different selectable Hampers
         * The Right will contain all the specifications of each selected Hamper
        */
    
        // **** **** LEFT PANEL **** ****

        createLeftPanel(); // Create and set up the Left Pannel
        /* Create three panels inside Left pannel:
         *  Top Label 
         *  Center List of Hampers
         *  Bottom buttons for adding/removing Hampers*/
        JLabel leftPanelTopLabel = createLeftPanelTopLabel();         // Top
        this.leftCenterHamperPanel = createLeftPanelCenterPannel();   // Center
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

    private void createLeftPanel(){
        leftPanel = new JPanel();
        leftPanel.setBackground(new Color(0x434e62));
        leftPanel.setBounds(0, 0, 300, 620);
        leftPanel.setLayout(new BorderLayout());
    }

    private void createRightPanel(){
        rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBounds(300, 0, 660, 620);
    }

    private JPanel createLeftPanelCenterPannel(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 1));
        panel.setBackground(new Color(0x7588ac));
        return panel;
    }

    private JLabel createLeftPanelTopLabel(){
        JLabel label = new JLabel();
        label.setText("Hampers");
        label.setForeground(Color.white);
        label.setPreferredSize(new Dimension(300, 100));
        label.setFont(new Font("Comic Sans", Font.BOLD, 42));
        label.setHorizontalAlignment(JLabel.CENTER);
        return label;
    }

    private void createRightCenterLabel(){
        currentLabel = new JLabel();
        currentLabel.setText("Please select a Hamper");
        currentLabel.setFont(new Font("Comic Sans", Font.BOLD, 30));
        currentLabel.setPreferredSize(new Dimension(400, 50));
    }

    private JPanel createRightCenterTextFieldPanel(){
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0xa8c3f7));
        return panel;
    }

    private JPanel createRightPanelTopPanel(){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(660, 100));
        panel.setBackground(new Color(0x97afde));

        JLabel label = new JLabel();
        label.setText("Configure Hampers");
        //label.setVerticalAlignment(JLabel.CENTER);
        label.setFont(new Font("Comic Sans", Font.BOLD, 42));
        label.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 40));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        buttonPanel.setBackground(new Color(0x97afde));
        JButton resetButton = new JButton();
        resetButton.setText("Reset All");
        resetButton.setFocusable(false);
        resetButton.setPreferredSize(new Dimension(100, 50));
        resetButton.setFont(new Font("Arial", Font.PLAIN, 16));
        resetButton.addActionListener(event -> {
            removeArrayElements();
            app.resetApplication();
            hamperNo = 0;
            currentPannelIndex = -1;
            revalidate();
            repaint();
        });
        buttonPanel.add(resetButton);

        panel.add(label);
        panel.add(buttonPanel);

        return panel;
    }

    private void removeArrayElements(){
        for(ConfigurationPannelGUI panel : hamperConfigPanels){
            rightCenterTextFieldPanel.remove(panel.getPanel());
        }
        for(JButton button : hamperButtons){
            leftCenterHamperPanel.remove(button);
        }
        hamperConfigPanels = new ArrayList<>();
        hamperButtons = new ArrayList<>();
    }

    // **** **** BUTTON SET UP **** ****

    private void setupReviewButton(){
        reviewButton = new JButton();
        reviewButton.setText("Review Order");
        reviewButton.setFocusable(false);
        reviewButton.setPreferredSize(new Dimension(300, 110));
        reviewButton.setFont(new Font("Comic Sans", Font.BOLD, 42));
        reviewButton.addActionListener(event -> {
            if(buttonNotRecentlyPressed){
                buttonNotRecentlyPressed = false;
                app.removeAllClients(); // Remove any existing clients
                int male;
                int female;
                int childOver8;
                int childUnder8;

                for(ConfigurationPannelGUI config : hamperConfigPanels){
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
                }
            }
        });
    }

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

    private JButton createRemoveHampersButton(){
        JButton button = new JButton();
        button.setText("Remove Hamper");
        button.setFocusable(false);
        button.setPreferredSize(new Dimension(300, 50));
        button.setFont(new Font("Comic Sans", Font.BOLD, 25));
        button.addActionListener(event -> {
            removeHamper();
            revalidate();
            repaint();
        });
        return button;
    }

    private JButton createAddNewHampersButton(){
        JButton button = new JButton();
        button.setText("Add a new Hamper");
        button.setFocusable(false);
        button.setPreferredSize(new Dimension(300, 50));
        button.setFont(new Font("Comic Sans", Font.BOLD, 25));
        button.addActionListener(event -> {
            createNewHamper();
            revalidate();
            repaint();
        });
        return button;
    }

    private JPanel createLeftPanelHamperConfigButtons(){
        JButton addNewHampersButton = createAddNewHampersButton();
        JButton addRemoveHampersButton = createRemoveHampersButton();
        
        JPanel leftPannelButtons = new JPanel();
        leftPannelButtons.setPreferredSize(new Dimension(300, 110));
        leftPannelButtons.add(addNewHampersButton, BorderLayout.PAGE_START);
        leftPannelButtons.add(addRemoveHampersButton, BorderLayout.PAGE_END);
        return leftPannelButtons;
    }

    // **** **** HAMPER FUNCTIONALITY **** ****

    private void createNewHamper(){
        var button = new JButton();
        hamperButtons.add(button);
        button.setText("Hamper #" + ++hamperNo);
        button.setFocusable(false);
        button.setBounds(200, 100, 100, 100);
        button.setFont(new Font("Comic Sans", Font.PLAIN, 25));
        button.addActionListener(event -> {
            JButton thisButton = (JButton) event.getSource();

            if(currentPannelIndex != -1){
                hamperConfigPanels.get(currentPannelIndex).setVisibility(false);;
            }
            currentPannelIndex = hamperButtons.indexOf(thisButton);
            hamperConfigPanels.get(currentPannelIndex).setVisibility(true);
            currentLabel.setText("Configure: " + thisButton.getText());
        });
        
        ConfigurationPannelGUI hamperConfig = new ConfigurationPannelGUI();
        hamperConfigPanels.add(hamperConfig);
        rightCenterTextFieldPanel.add(hamperConfig.getPanel());
        hamperConfig.setVisibility(false);;
        leftCenterHamperPanel.add(button);

        app.addHamper();
    }

    private void removeHamper(){
        if(currentPannelIndex != -1){
            rightCenterTextFieldPanel.remove(hamperConfigPanels.get(currentPannelIndex).getPanel());
            hamperConfigPanels.remove(currentPannelIndex);

            leftCenterHamperPanel.remove(hamperButtons.get(currentPannelIndex));
            hamperButtons.remove(currentPannelIndex);

            app.removeHamper(currentPannelIndex);

            currentPannelIndex = -1;
            currentLabel.setText("Please select a Hamper");
        }

    }

    public void actionPerformed(ActionEvent event){
    }
    
}
