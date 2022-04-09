import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;


public class SystemControl extends JFrame implements ActionListener, MouseListener, ItemListener {
    private JButton submitButton;

    private JPanel rightPanel;
    private JPanel leftPanel;
    
    private JPanel leftCenterHamperPanel;
    private JPanel rightCenterTextFieldPanel;

    private JLabel currentLabel;
    private ArrayList<ConfigurationPannelGUI> hamperConfigPanels = new ArrayList<>();

    private ArrayList<JButton> hamperButtons = new ArrayList<>();
    private int hamperNo = 0;
    private int currentPannelIndex = -1;

    private Application app;

    public SystemControl() {
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

        JLabel rightPanelTopLabel = createRightPanelTopLabel();
        rightCenterTextFieldPanel = createRightCenterTextFieldPanel();
        rightCenterTextFieldPanel.add(currentLabel);
        setupSubmitButton();    // Create submit button

        rightPanel.add(rightPanelTopLabel, BorderLayout.PAGE_START);
        rightPanel.add(rightCenterTextFieldPanel, BorderLayout.CENTER);
        rightPanel.add(submitButton, BorderLayout.PAGE_END);
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
        rightPanel.setBackground(new Color(0x97afde));
        rightPanel.setBounds(300, 0, 660, 620);
    }

    private JPanel createLeftPanelCenterPannel(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 1));
        panel.setBackground(new Color(0x7588ac));
        //leftPanelHampers.setPreferredSize(new Dimension(300, 420));
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

    private JLabel createRightPanelTopLabel(){
        JLabel label = new JLabel();
        label.setText("Configure Hampers");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setPreferredSize(new Dimension(300, 100));
        label.setFont(new Font("Comic Sans", Font.BOLD, 42));
        label.setBackground(Color.white);
        return label;
    }

    // **** **** BUTTON SET UP **** ****

    private void setupSubmitButton(){
        submitButton = new JButton();
        submitButton.setText("Submit order");
        submitButton.setFocusable(false);
        submitButton.setPreferredSize(new Dimension(300, 110));
        submitButton.setFont(new Font("Comic Sans", Font.BOLD, 42));
        submitButton.addActionListener(event -> {
            for(ConfigurationPannelGUI config : hamperConfigPanels){
                app.addClient(hamperConfigPanels.indexOf(config), ClientType.ADULT_MALE, parseInt(config.getMaleText()));
                app.addClient(hamperConfigPanels.indexOf(config), ClientType.ADULT_FEMALE, parseInt(config.getFemaleText()));
                app.addClient(hamperConfigPanels.indexOf(config), ClientType.CHILD_OVER_8, parseInt(config.getChildOver8Text()));
                app.addClient(hamperConfigPanels.indexOf(config), ClientType.CHILD_UNDER_8, parseInt(config.getChildUnder8Text()));

                System.out.println("-------------------------------------");
                System.out.println("Male: " + config.getMaleText());
                System.out.println("Female: " + config.getFemaleText());
                System.out.println("ChildOver8: " + config.getChildOver8Text());
                System.out.println("ChildUnder8: " + config.getChildUnder8Text());

            }
            app.calculateOrder();
        });
    }

    private int parseInt(String arg){
        try{
            return Integer.parseInt(arg);
        } catch(NumberFormatException e){
            if(arg.equals("")){
                return 0;
            }
            throw new IllegalArgumentException();
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

    public void itemStateChanged(ItemEvent evt) {
    }
    
    public void mouseClicked(MouseEvent event){
    }
    
    public void mouseEntered(MouseEvent event){  
    }

    public void mouseExited(MouseEvent event){
    }

    public void mousePressed(MouseEvent event){
    }

    public void mouseReleased(MouseEvent event){
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new SystemControl().setVisible(true);        
        });
    }
}
