import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Confirmation extends JFrame implements ActionListener {
    
    private JFrame oldFrame;
    private JButton ConfirmButton;
    private Application app;

    public Confirmation(JFrame oldFrame, Application app){
        this.setSize(450, 750);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.oldFrame = oldFrame;
        this.oldFrame.setVisible(false);
        this.app = app;

        setUpTitle();
        setUpReviewText();
        setupConfirmButton();

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                oldFrame.setVisible(true);
            }
        });

        this.setVisible(true);
         
    }

    private void setUpTitle(){
        JLabel title = new JLabel("Review your Hampers", SwingConstants.CENTER);
        title.setBackground(new Color(0x434e62));
        title.setFont(new Font("Comic Sans", Font.BOLD, 36));
        title.setBounds(0, 0, 450, 75);
        title.setPreferredSize(new Dimension(450, 75));
        this.add(title, BorderLayout.PAGE_START);
    }

    private void setUpReviewText(){
        JPanel panelMiddle = new JPanel();
        panelMiddle.setBackground(new Color(0x434e62));
        panelMiddle.setBounds(0, 0, 450, 500);

        JTextArea orderPreview = new JTextArea();
        orderPreview.setEditable(false);
        orderPreview.append(app.toString());
        JScrollPane scroll = new JScrollPane(orderPreview);
        scroll.setPreferredSize(new Dimension(400, 475));
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JButton goBackButton = new JButton();
        goBackButton.setText("Go Back");
        goBackButton.setFocusable(false);
        goBackButton.setPreferredSize(new Dimension(175, 50));
        goBackButton.setFont(new Font("Comic Sans", Font.BOLD, 32));
        goBackButton.addActionListener(event -> {
            oldFrame.setVisible(true);
            this.dispose();
        });

        panelMiddle.add(scroll);
        panelMiddle.add(goBackButton);
        this.add(panelMiddle, BorderLayout.CENTER);
    }

    private void setupConfirmButton(){
        ConfirmButton = new JButton();
        ConfirmButton.setText("Confirm Order");
        ConfirmButton.setFocusable(false);
        ConfirmButton.setBounds(0, 0, 600, 75);
        ConfirmButton.setPreferredSize(new Dimension(600, 75));
        ConfirmButton.setFont(new Font("Comic Sans", Font.BOLD, 36));
        ConfirmButton.addActionListener(event -> {
            try{
                app.requestOrderForm();
            } catch(HamperHasNoClientsException e){
                e.printStackTrace();
            }
        });
        this.add(ConfirmButton, BorderLayout.PAGE_END);
    }

        
    public void actionPerformed(ActionEvent event){
        
    }

}
