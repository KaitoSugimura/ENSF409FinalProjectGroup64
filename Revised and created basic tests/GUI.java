import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;


public class GUI extends JFrame implements ActionListener, MouseListener, ItemListener {
    private JButton submitButton;

    private JPanel rightPanel;
    private JPanel leftPanel;
    
    private JPanel leftCenterHamperPanel;
    private JPanel rightCenterTextFieldPanel;

    private JLabel currentLabel;
    private ArrayList<JPanel> hamperConfigPanels = new ArrayList<>();

    private ArrayList<JButton> hamperButtons = new ArrayList<>();
    private int hamperNo = 0;
    private int currentPannelIndex = -1;

    public GUI() {
        super("Food Bank Order Tool");
        setupGUI();
        this.setSize(960, 660);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.getContentPane().setBackground(new Color(0, 0, 0));
        this.setVisible(true);
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

    private JPanel createHamperConfigField(){
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0xa8c3f7));
        panel.setPreferredSize(new Dimension(600, 340));

        JTextField adultMaleText = new JTextField();
        adultMaleText.setPreferredSize(new Dimension(100, 40));
        Font bigFont = adultMaleText.getFont().deriveFont(Font.PLAIN, 24f);
        adultMaleText.setFont(bigFont);
        JTextField adultFemaleText = new JTextField();
        adultFemaleText.setPreferredSize(new Dimension(100, 40));
        adultFemaleText.setFont(bigFont);
        JTextField childOver8Text = new JTextField();
        childOver8Text.setPreferredSize(new Dimension(100, 40));
        childOver8Text.setFont(bigFont);
        JTextField childUnder8Text = new JTextField();
        childUnder8Text.setPreferredSize(new Dimension(100, 40));
        childUnder8Text.setFont(bigFont);

        JPanel card1 = new JPanel();
        JLabel lb1 = new JLabel("# of adult males: ");
        lb1.setFont(new Font("Comic Sans", Font.ITALIC, 24));
        card1.add(lb1);
        card1.add(adultMaleText);
        card1.setBackground(new Color(0xeeeeee));
        card1.setPreferredSize(new Dimension(500, 60));
        JPanel card2 = new JPanel();
        JLabel lb2 = new JLabel("# of adult females: ");
        lb2.setFont(new Font("Comic Sans", Font.ITALIC, 24));
        card2.add(lb2);
        card2.add(adultFemaleText);
        card2.setBackground(new Color(0xe1e1e1));
        card2.setPreferredSize(new Dimension(500, 60));
        JPanel card3 = new JPanel();
        JLabel lb3 = new JLabel("# of children over 8: ");
        lb3.setFont(new Font("Comic Sans", Font.ITALIC, 24));
        card3.add(lb3);
        card3.add(childOver8Text);
        card3.setBackground(new Color(0xd4d4d4));
        card3.setPreferredSize(new Dimension(500, 60));
        JPanel card4 = new JPanel();
        JLabel lb4 = new JLabel("# of children under 8: ");
        lb4.setFont(new Font("Comic Sans", Font.ITALIC, 24));
        card4.add(lb4);
        card4.add(childUnder8Text);
        card4.setBackground(new Color(0xc7c7c7));
        card4.setPreferredSize(new Dimension(500, 60));

        JButton resetButton = new JButton();
        resetButton.setText("Reset fields");
        resetButton.setFocusable(false);
        resetButton.setPreferredSize(new Dimension(170, 54));
        resetButton.setFont(new Font("Comic Sans", Font.PLAIN, 24));
        resetButton.addActionListener(event -> {
            adultMaleText.setText("");
            adultFemaleText.setText("");
            childOver8Text.setText("");
            childUnder8Text.setText("");
            System.out.println("Reset");
        });

        panel.add(card1);
        panel.add(card2);
        panel.add(card3);
        panel.add(card4);
        panel.add(resetButton);
        return panel;
    }

    // **** **** BUTTON SET UP **** ****

    private void setupSubmitButton(){
        submitButton = new JButton();
        submitButton.setText("Submit order");
        submitButton.setFocusable(false);
        submitButton.setPreferredSize(new Dimension(300, 110));
        submitButton.setFont(new Font("Comic Sans", Font.BOLD, 42));
        submitButton.addActionListener(event -> {
            System.out.println("poo");
        });
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
                hamperConfigPanels.get(currentPannelIndex).setVisible(false);
            }
            currentPannelIndex = hamperButtons.indexOf(thisButton);
            hamperConfigPanels.get(currentPannelIndex).setVisible(true);
            currentLabel.setText("Configure: " + thisButton.getText());
        });
        JPanel hamperConfig = createHamperConfigField();
        hamperConfigPanels.add(hamperConfig);
        rightCenterTextFieldPanel.add(hamperConfig);
        hamperConfig.setVisible(false);
        leftCenterHamperPanel.add(button);
    }

    private void removeHamper(){
        if(currentPannelIndex != -1){
            rightCenterTextFieldPanel.remove(hamperConfigPanels.get(currentPannelIndex));
            hamperConfigPanels.remove(currentPannelIndex);

            leftCenterHamperPanel.remove(hamperButtons.get(currentPannelIndex));
            hamperButtons.remove(currentPannelIndex);

            currentPannelIndex = -1;
            currentLabel.setText("Please select a Hamper");
        }
    }

    @Override
    public void actionPerformed(ActionEvent event){

    }

    public void itemStateChanged(ItemEvent evt) {
        //CardLayout cl = (CardLayout)(cards.getLayout());
        //cl.show(cards, (String)evt.getItem());
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
            new GUI().setVisible(true);        
        });
    }
}
