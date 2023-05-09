# CS611-Final-Project
## Stock Market
Author: Rithivik Doshi, Zhangde Song, Yuxi Ge. 

## Environment 
Hava 8

#### How to run the code:
java javac EntryInterface.java
java EntryInterface.java

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
##### MarketLogin.java
##### MarketStock.java
##### ModelMode.java
##### OwnedStock.java
##### Person.java
##### PersonAccount.java
##### PersonalTransaction.java
##### PersonalTransactionHistory.java
##### PortfolioManagementSystem.java 
##### Request.java
##### RequestFactory.java
##### SMProxy.java
##### Stock.java
##### StockFactory.java
##### StockTransaction.java
##### StockTransactionHistory.java
##### TradingAccount.java
##### TradingSystemModel.java
##### Transaction.java
##### TransactionData.java
##### TransactionHistory.java
##### UserStatus.java
##### CustomerPortfolioView.java
##### EntryInterface.java
##### LoginRegistrationPage.java
##### ManagerLoginPage.java
##### ManagerPortfolioView.java
##### ManageStockView.java
##### PersonalAccountView.java
##### StockMarketView.java
##### TradingAccountRequestsView.java
##### TradingAccountView.java
