import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class GUI extends JFrame implements ActionListener, MouseListener, ItemListener {
    private JLabel header;
    JPanel cards; //a panel that uses CardLayout


    public GUI() {
        super("Food Bank Order Tool");
        setupGUI();
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setupGUI(){
        JPanel dropdownPane = new JPanel();
        String dropdownItems[] = { "Hamper 1", "Hamper 2" };
        JComboBox<String> dropdown = new JComboBox<>(dropdownItems);
        dropdown.setEditable(false);
        dropdown.addItemListener(this);
        dropdownPane.add(dropdown);
        
        // Create the cards
        JPanel card1 = new JPanel();
        card1.add(new JLabel("# of adult males"));
        card1.add(new JTextField(3));
        card1.add(new JButton("Button 1"));
        card1.add(new JButton("Button 2"));
        card1.add(new JButton("Button 3"));
        
        JPanel card2 = new JPanel();
        card2.add(new JTextField("TextField", 20));
        
        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(card1, "Hamper 1");
        cards.add(card2, "Hamper 2");
        
        this.getContentPane().add(dropdownPane, BorderLayout.PAGE_START);
        this.getContentPane().add(cards, BorderLayout.CENTER);
    }

    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String)evt.getItem());
    }

    public void actionPerformed(ActionEvent event){
        System.out.println(event.getActionCommand());
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
