import java.awt.*;
import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;


public class ConfigurationPannelGUI {

    /* ******* PRIVATE CLASSES ******* */

    private class CardPanel{

        private JPanel card;
        private JLabel errorMessage = new JLabel("Please enter an integer");

        private final Font CARDFONT = new Font("Comic Sans", Font.ITALIC, 24);
        private final Dimension CARDDIMENSION = new Dimension(500, 60);

        public CardPanel(String label, Color color, JTextField text){
            card = new JPanel();
            JLabel lb1 = new JLabel(label);
            lb1.setFont(CARDFONT);
            card.add(lb1);
            card.add(text);
            card.setBackground(color);
            card.setPreferredSize(CARDDIMENSION);
            card.add(errorMessage);

            this.errorMessage.setForeground(Color.red);
            this.errorMessage.setVisible(false);
        }

        public JPanel getPanel(){ return this.card; }
        public void setError(boolean bool){ this.errorMessage.setVisible(bool); }
    }

    private class TextField {
        private class Listener implements DocumentListener{
            public void insertUpdate(DocumentEvent e) {
                validateFields();
            }
            public void removeUpdate(DocumentEvent e) {
                validateFields();
            }
            public void changedUpdate(DocumentEvent e) {}
        }

        private final Font TEXTFONT = new Font("Arial", Font.PLAIN, 24);
        private final Dimension TEXTDIMENSION = new Dimension(100, 40);
        private JTextField text;
        
        public TextField(){
            text = new JTextField();
            text.setPreferredSize(TEXTDIMENSION);
            text.setFont(TEXTFONT);
            text.getDocument().addDocumentListener(new Listener());
        }
        
        public JTextField getTextField(){ return this.text; }
    }

    /* ******* END OF PRIVATE CLASSES ******* */
    
    private final JPanel PANEL = new JPanel();

    private JTextField adultMaleText;
    private JTextField adultFemaleText;
    private JTextField childOver8Text;
    private JTextField childUnder8Text;

    private CardPanel MaleCard;
    private CardPanel FemaleCard;
    private CardPanel ChildOver8Card;
    private CardPanel ChildUnder8Card;

    public ConfigurationPannelGUI(){
        setMainPanel();
        setTextFields();
        setCards();
        setResetButton();
    }

    // Set the visibility of the panel
    public void setVisibility(boolean bool){
        this.PANEL.setVisible(bool);
    }

    public JPanel getPanel(){ return PANEL;}
    public String getMaleText(){ return adultMaleText.getText(); }
    public String getFemaleText(){ return adultFemaleText.getText(); }
    public String getChildOver8Text(){ return childOver8Text.getText(); }
    public String getChildUnder8Text(){ return childUnder8Text.getText(); }

    public void validateFields(){
        String condition = "[0-9]+|^$";

        MaleCard.setError(!adultMaleText.getText().matches(condition));
        FemaleCard.setError(!adultFemaleText.getText().matches(condition));
        ChildOver8Card.setError(!childOver8Text.getText().matches(condition));
        ChildUnder8Card.setError(!childUnder8Text.getText().matches(condition));
    }

    private void setMainPanel(){
        this.PANEL.setBackground(new Color(0xa8c3f7));
        this.PANEL.setPreferredSize(new Dimension(600, 340));
    }

    private void setTextFields(){
        this.adultMaleText = new TextField().getTextField();
        this.adultFemaleText = new TextField().getTextField();
        this.childOver8Text = new TextField().getTextField();
        this.childUnder8Text = new TextField().getTextField();
    }

    private void setCards(){
        MaleCard = new CardPanel("# of adult males: ", new Color(0xeeeeee), adultMaleText);
        FemaleCard = new CardPanel("# of adult females: ", new Color(0xe1e1e1), adultFemaleText);
        ChildOver8Card = new CardPanel("# of children over 8: ", new Color(0xd4d4d4), childOver8Text);
        ChildUnder8Card = new CardPanel("# of children under 8: ", new Color(0xc7c7c7), childUnder8Text);

        this.PANEL.add(MaleCard.getPanel());
        this.PANEL.add(FemaleCard.getPanel());
        this.PANEL.add(ChildOver8Card.getPanel());
        this.PANEL.add(ChildUnder8Card.getPanel());
    }

    private void setResetButton(){
        JButton resetButton = new JButton();
        resetButton.setText("Reset fields");
        resetButton.setFocusable(false);
        resetButton.setPreferredSize(new Dimension(170, 54));
        resetButton.setFont(new Font("Comic Sans", Font.PLAIN, 24));
        resetButton.addActionListener(event -> {
            this.adultMaleText.setText("");
            this.adultFemaleText.setText("");
            this.childOver8Text.setText("");
            this.childUnder8Text.setText("");
        });
        this.PANEL.add(resetButton);
    }
}
