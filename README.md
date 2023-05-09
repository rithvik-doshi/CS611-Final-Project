# CS611-Final-Project
## Stock Market Project
Author: Rithivik Doshi, Zhangde Song, Yuxi Ge. 

## Environment 
Java 8

#### How to run the code:
1. Firstly, ensure that all the files are located in the folders, and that you are using the same database files that were included. If you need to, make sure to clear all the files you need to but keep any file headers that are present.
2. Run `java Main.java` in `TradingFinalProject/src/` directory.
3. Enjoy!

#### Files:
##### Account.java
The class provides several constructors, including one for creating a new account with a given balance and owner ID, one for creating a new account with a generated ID, and one for creating a new personal account.
##### Customer.java
The class provides a constructor that initializes the Customer object with the customer's ID, name, email, password, and initial balance. It also has methods for depositing and withdrawing money from the personal account, creating a new personal account history file for the customer, and checking if the customer already has a trading account.
##### CustomerLogin.java
The class provides a constructor that initializes the customers list by reading customer information from a file. It also includes methods for retrieving the list of customers, checking customer login credentials, and registering new customers.
##### CustomerPersonalAccountSystem.java
The class provides a constructor that initializes the CustomerPersonalAccountSystem object with a Customer object. It also includes methods for retrieving the customer, getting the balance of the personal account, depositing and withdrawing money from the personal account, sending a request to the manager to open a trading account, and getting the transaction history of the personal account.
##### CustomerStockTradingSystem.java
The class provides a constructor that initializes the CustomerStockTradingSystem object with a Customer object and a PersonalAccount object. It also includes methods for retrieving the balance of the personal account and the customer's stock holdings and purchase prices, creating a trading account, adding and deducting funds from the personal account, buying and selling stocks, and retrieving the realized and unrealized profits of the trading account.
##### Manager.java
The class provides a constructor that initializes the Manager object with an ID, name, email, and password, and creates a ManagerKey object with the manager's ID. It also includes methods for retrieving the manager's key value, getting the pending requests, approving requests, and rejecting requests.
##### ManagerKey.java
The class provides a constructor that takes a lookup ID as a parameter and retrieves the manager's key from a text file named "Manager.txt". The constructor initializes the key property to the key associated with the manager with the given lookup ID. If no manager with the given ID is found, an InstanceNotFoundException is thrown.
##### ManagerLogin.java
The class provides a constructor that takes a file path as a parameter, which is the path to a text file containing the manager data (ID, username, email, password, and manager key).
##### MarketStock.java
This is a class extends the Stock class and implements the usesMoney interface. It represents a stock that can be traded on a stock market. It has a constructor that takes in a name and a price, which sets the name and price fields of the Stock class.
##### ModelMode.java
This is an enumeration class named ModelMode which has three constant values: CUSTOMER, MANAGER, and NONE. This class is used to keep track of the current mode of the trading application (whether a customer or a manager is logged in, or if no one is logged in).
##### OwnedStock.java
This OwnedStock class represents a stock owned by a customer in their trading account. It extends the Stock class, which provides basic information about the stock such as its name. The OwnedStock class has three instance variables: quantity, which represents the number of shares of the stock that the customer owns; purchasePrice, which represents the price at which the customer purchased the stock; and StockName, which represents the name of the stock. The class has two constructors. The first takes the name of the stock, the quantity of shares, and the purchasePrice. The second constructor takes only the name of the stock and defaults the quantity and purchasePrice to 0.
##### Person.java
This is an abstract class named Person, which serves as the parent class for both Customer and Manager classes. It has private instance variables such as ID, name, email, and password, along with their respective getter and setter methods. The class also has a constructor to initialize the instance variables. The equals method is also overridden to check if two objects are equal based on the ID value.
##### PersonAccount.java
This class represents a personal account for a customer in a trading platform. It allows customers to deposit and withdraw funds, transfer money to their trading account, and get their transaction history. The class extends an Account class, which stores the account balance. The PersonalAccount class also has a PersonalTransactionHistory object to keep track of transactions made in the personal account.
##### PersonalTransaction.java
The PersonalTransaction class extends the Transaction class and represents a transaction that involves a personal account. It has a constructor that takes in a String for the type of behavior (e.g. deposit or withdraw) and a double for the amount of money involved in the transaction. The class overrides the getInfoAsArray method to return an array of String that contains the behavior and money amount. It also overrides the toString method to return a String representation of the transaction in the format "behavior, money".
##### PersonalTransactionHistory.java
This class which extends the abstract class TransactionHistory. It represents the transaction history for a personal account. It overrides some of the methods from the parent class to provide functionality specific to personal accounts.
##### PortfolioManagementSystem.java 
The PortfolioManagementSystem class manages the stocks available for trading, approves new customers, tracks customer profits, and notifies customers who have made more than $10,000 in realized trading gains about the opportunity to create a derivative trading account. It contains the following methods:
##### Request.java
The Request class represents a request for account creation sent by a customer to the manager of the portfolio management system. 
##### RequestFactory.java
This class, RequestFactory, is responsible for managing the creation, storage, and retrieval of Request objects in the system. It contains methods for reading requests from a file, removing approved and rejected requests, checking if a request sender is already in the list of requests, and writing requests to the file. It also contains an ArrayList of Request objects that represent the requests stored in the file.
##### SMProxy.java
The SMProxy class serves as an intermediary between the PortfolioManagementSystem and the StockMarket class. It allows the PortfolioManagementSystem to manage the stocks in the StockMarket class while using the ManagerKey to verify permission before executing operations. It provides methods for getting and setting stock prices, adding and removing stocks, and retrieving information about all stocks.
##### Stock.java
This is the abstract Stock class, which provides a blueprint for creating objects representing stocks. It contains a private name field to store the name of the stock and a public getName() method to retrieve the name. The class also defines an abstract stringify() method, which is implemented in the subclasses to provide a string representation of the stock object.
##### StockFactory.java
The StockFactory class provides two static factory methods for creating stock objects: createMarketStock and createOwnedStock. createMarketStock method creates a new MarketStock object with the given name and price, and createOwnedStock method creates a new OwnedStock object with the given name, quantity, and purchase price. These factory methods allow for easy creation of MarketStock and OwnedStock objects in other parts of the program without the need to know the details of their implementation.
#### StockMarket.java
The StockMarket class represents the stock market system and provides functionalities to manipulate the stocks in the market. It maintains a list of MarketStock objects representing the stocks in the market, and allows adding, removing, setting the price of, and getting the price of a particular stock. The class is implemented as a singleton using the static block initialization method. The stocks in the market are initially loaded from a file named MarketStocks.txt and can be updated by calling the updateStocks() method, which writes the current stocks to the file. The class also overrides the toString() method to provide a string representation of the stocks in the market.
##### StockTransaction.java
The StockTransaction class extends the abstract Transaction class and represents a transaction of a specific stock. It has three instance variables: stockName to store the name of the stock, quantity to store the amount of the stock transacted, and price which is inherited from the Transaction class and represents the price of the transaction. It also has a constructor to initialize these instance variables and getter methods to retrieve their values. Additionally, it overrides the getInfoAsArray() method to return an array of strings containing information about the transaction, and the toString() method to return a string representation of the transaction in the format "behavior, stockName, quantity, price".
##### StockTransactionHistory.java
This class extends the TransactionHistory abstract class. It provides the implementation for a history of transactions for a customer's stock-related transactions.

The StockTransactionHistory class overrides the historyType method to provide a unique file path for the customer's stock transaction history file. It also overrides the getTransaction method to create StockTransaction objects from the information in a single line of the transaction history file.
##### TradingAccount.java
This class extends the Account class. It represents a trading account for a customer, with attributes including the customer ID, net profit, realized profit, stock holdings, and purchase prices.
##### Transaction.java
This class erves as a blueprint for creating different types of transactions. It has two attributes - behaviour and money - which represent the type of transaction (buy, sell, deposit, withdraw, etc.) and the amount of money involved.
##### TransactionData.java
This class reads a transaction history file and returns an ArrayList of transaction records. It has an attribute called transactionHistory which is an ArrayList of strings containing the transaction records.
##### TransactionHistory.java
This abstract class defines a framework for different types of transaction histories. It includes a constructor that initializes the transaction history and reads it from a file, and abstract methods that must be implemented by subclasses to define specific types of transaction histories.
##### UserStatus.java
This is a Java enum called UserStatus that defines three constants: LOGGED_IN, ACCOUNT_PENDING, and LOGGED_OUT. This enum can be used to represent the different statuses of a user in a trading or investment application.
##### CustomerPortfolioView.java
This class is a graphical user interface (GUI) for displaying a profit report of all customers. It extends the JFrame class, which is a top-level container for creating a window in a Java Swing application. It has a Manager object and a PortfolioManagementSystem object as instance variables. The displayReport method takes an ArrayList of Customer objects as a parameter and creates a JPanel for each customer, displaying their name, realized profit, and unrealized profit. The GUI also includes a back button for returning to the previous window.
##### EntryInterface.java
The EntryInterface class is a GUI class that represents the entry interface of the Market App. It extends the JFrame class, which is a top-level container for components in a GUI, and displays a window with three buttons: "Customer", "Manager", and "Exit".
##### LoginRegistrationPage.java
This is a GUI (Graphical User Interface) class for the login and registration page for the customers. It contains a tabbed pane with two tabs: "Login" and "Registration". In the login tab, the user can enter their username and password to log in. In the registration tab, the user can register a new account by providing their name, email, and password. The class also contains buttons for login, registration, and a back button to return to the previous screen. When the user clicks the login or register button, the input data is sent to the CustomerLogin object to check if the login is valid or to register a new customer, respectively. If the login is successful, a new PersonalAccountView is created and displayed to the user.
##### ManagerLoginPage.java
This GUI class that represents the Manager Login page of a trading application. It contains a login panel that includes text fields for username, password, and a key, as well as login and back buttons. The class includes an event listener for the login button that checks the validity of the manager's credentials (username, password, and key) using the ManagerLogin class, and opens the ManagerPortfolioView if the login is successful. The back button allows the user to go back to the EntryInterface.
##### ManagerPortfolioView.java
The class implemente different views for customers and managers. The EntryInterface is the starting point of the application, which has buttons for logging in as a customer or manager. LoginRegistrationPage is used for logging in or registering a new customer, while ManagerLoginPage is used for logging in as a manager. The successful login for a customer will lead to PersonalAccountView and for a manager to ManagerPortfolioView, where they can manage stocks, approve new customers, and track customer profits.
##### ManageStockView.java
ManagerLoginPage is a class that represents the login page for the application's manager users. It includes a form for the manager to enter their username, password, and key, and buttons for logging in or going back to the previous screen. When the user logs in successfully, the application displays the manager's portfolio.It displays the manager's portfolio, which includes buttons for managing stocks, approving new customers, and tracking customer profits.It also represents the view for managing stocks. It displays the stocks in the market and allows the manager to add, edit, or delete stocks. It also includes a back button that takes the user back to the previous screen.
##### PersonalAccountView.java
This is a GUI for a personal account view for a customer. It includes buttons for depositing, withdrawing money and accessing the trading account view. There is also a button for viewing the personal account history.
##### StockMarketView.java
This class is a Java Swing-based graphical user interface (GUI) that represents a stock market view for customers. It displays a list of stocks available for trading and allows customers to buy stocks. The class is part of a larger system that handles stock trading and personal accounts.
##### TradingAccountRequestsView.java
The TradingAccountRequestsView class is a Java Swing-based graphical user interface (GUI) that represents a view for managers to handle trading account requests. This class is part of a larger system that handles portfolio management and requests.
##### TradingAccountView.java
The TradingAccountView class is a Java Swing-based graphical user interface (GUI) that represents a view for customers to manage their trading accounts. This class is part of a larger system that handles portfolio management and stock transactions.
