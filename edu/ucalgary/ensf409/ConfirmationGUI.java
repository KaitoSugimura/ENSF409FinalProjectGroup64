/**
@author Danny Duong, Kaito Sugimura, Kevin Johnson, Joshua Walters
@version 1.9
@since 1.0
*/
package edu.ucalgary.ensf409;

import java.awt.*;
import javax.swing.*;

/* GUI class that deals with the confirmation screen
 * Includes a text area that shows a List of Hampers and Clients
 * a Button for going back to the main GUI 
 * and a Button for confirming and printing out the order form
 */
public class ConfirmationGUI extends JFrame {
    
    private JFrame oldFrame;
    private Application app;

    // Constructor
    public ConfirmationGUI(JFrame oldFrame, Application app){
        // Setting of main JFrame
        this.setSize(450, 750);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        // Turn oldFrame (Main GUI) to invisible
        this.oldFrame = oldFrame;
        this.oldFrame.setVisible(false);
        this.app = app;

        setUpTitle();           //Setting the Top title banner
        setUpReviewText();      //Setting the List of Hampers in the Center
        setupConfirmButton();   //Setting the Confirmation button

        //Made the Main GUI reappear on exit of current GUI
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                oldFrame.setVisible(true);
                SystemControlGUI.buttonNotRecentlyPressed = true;
            }
        });

        this.setVisible(true);
    }

    // Set up the title
    // Simple top banner 
    private void setUpTitle(){
        JLabel title = new JLabel("Review your Hampers", SwingConstants.CENTER);
        title.setBackground(new Color(0x434e62));
        title.setFont(new Font("Comic Sans", Font.BOLD, 36));
        title.setBounds(0, 0, 450, 75);
        title.setPreferredSize(new Dimension(450, 75));
        this.add(title, BorderLayout.PAGE_START);
    }

    /* Set up the Review text (Unfactoered code for simple one function view)
     * Create a uneditable text area inside a scroller
     * Create a panel that contains the above and a button to go back
     */
    private void setUpReviewText(){
        JPanel panelMiddle = new JPanel();
        panelMiddle.setBackground(new Color(0x434e62));
        panelMiddle.setBounds(0, 0, 450, 500);

        JTextArea orderPreview = new JTextArea();
        orderPreview.setEditable(false);
        // Print list
        orderPreview.append(app.toString());
        orderPreview.append("\n****************************************************************");
        orderPreview.append("\n  **** **** LIST OF HAMPERS AND CLIENTS **** **** ");
        orderPreview.append("\n****************************************************************");
        orderPreview.append("\n This part is not contained in the printed form\n");
        orderPreview.append(app.toStringList());
        orderPreview.setCaretPosition(0);
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
            SystemControlGUI.buttonNotRecentlyPressed = true;
            this.dispose();
        });

        panelMiddle.add(scroll);
        panelMiddle.add(goBackButton);
        this.add(panelMiddle, BorderLayout.CENTER);
    }

    /* Confirm Button
     * Requests an order form upon order and sends the user a message
     */
    private void setupConfirmButton(){
        JButton ConfirmButton = new JButton();
        ConfirmButton.setText("Confirm Order");
        ConfirmButton.setFocusable(false);
        ConfirmButton.setBounds(0, 0, 600, 75);
        ConfirmButton.setPreferredSize(new Dimension(600, 75));
        ConfirmButton.setFont(new Font("Comic Sans", Font.BOLD, 36));
        ConfirmButton.addActionListener(event -> {
            try{
                app.requestOrderForm();
                app.getInventory().removeItemsFromDataBase();
            } catch(HamperHasNoClientsException e){
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Successfully created a Order Form!");
        });
        this.add(ConfirmButton, BorderLayout.PAGE_END);
    }


}
