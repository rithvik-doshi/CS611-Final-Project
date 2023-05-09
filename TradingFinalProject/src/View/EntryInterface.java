package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class EntryInterface extends JFrame {
    private ManagerLoginPage managerLoginRegistrationPage;



    public EntryInterface() {
        JPanel panel = new JPanel(new GridLayout(4, 1));
        JButton customerButton = new JButton("Customer");
        panel.add(customerButton);
        JButton managerButton = new JButton("Manager");
        panel.add(managerButton);

        JButton exitButton = new JButton("Exit");
        panel.add(exitButton);


        // Add action listeners to the buttons
        customerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Customer button clicked
                LoginRegistrationPage loginRegistrationPage = new LoginRegistrationPage();
                loginRegistrationPage.setVisible(true);

            }
        });

        managerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Customer button clicked
                managerLoginRegistrationPage = new ManagerLoginPage();
                managerLoginRegistrationPage.setVisible(true);

            }
        });



        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Exit the application with a status code of 0
            }
        });
        panel.add(exitButton);



        // Add the panel to the frame
        getContentPane().add(panel);

        // Set up the frame
        setTitle("Stocks n stuff ©");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

    public static void main(String[] args) {
        new EntryInterface();
    }
}