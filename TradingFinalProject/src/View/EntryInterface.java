package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EntryInterface extends JFrame {
    private JButton customerButton;
    private JButton managerButton;
    private JButton viewStocksButton;
    private JButton exitButton;
    private LoginRegistrationPage customerLoginRegistrationPage;
    private ManagerLoginPage managerLoginRegistrationPage;
    private StockMarketView stockMarketView;

    public EntryInterface() {
        // Create the panel and add the buttons to it
        JPanel panel = new JPanel(new GridLayout(4, 1));
        customerButton = new JButton("Customer");
        panel.add(customerButton);
        managerButton = new JButton("Manager");
        panel.add(managerButton);
        viewStocksButton = new JButton("View Market Stocks");
        panel.add(viewStocksButton);
        exitButton = new JButton("Exit");
        panel.add(exitButton);


        // Add action listeners to the buttons
        customerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Customer button clicked
                customerLoginRegistrationPage = new LoginRegistrationPage();
                EntryInterface.this.setVisible(false); // Set the current frame to be invisible


            }
        });

        managerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Customer button clicked
                managerLoginRegistrationPage = new ManagerLoginPage();
                managerLoginRegistrationPage.setVisible(true);
                EntryInterface.this.setVisible(false); // Set the current frame to be invisible

            }
        });

        viewStocksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stockMarketView = new StockMarketView();
                stockMarketView.setVisible(true);
                EntryInterface.this.setVisible(false);
            }
        });

        // Add the panel to the frame
        getContentPane().add(panel);

        // Set up the frame
        setTitle("Market App");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

    public static void main(String[] args) {
        new EntryInterface();
    }
}