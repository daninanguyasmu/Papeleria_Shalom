# Papelería Shalom - Point of Sale (POS) System 📦

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

## 📌 Overview
A Point of Sale (POS) system developed entirely in Java (Core SE), designed to demonstrate mastery of Object-Oriented Programming (OOP) principles. The system manages inventory, processes sales transactions, and generates financial reports using a custom data persistence engine based on local text files.

## 🚀 Architecture & Technologies
*   **Language:** Java
*   **Graphical User Interface (GUI):** Java Swing (`JOptionPane`) for menu navigation and data capture.
*   **Data Storage:** Custom persistence system utilizing `.txt` file manipulation (`java.io.*` and `java.nio.file.*`).
*   **Software Design:** Strict implementation of OOP principles (Abstraction, Inheritance, Polymorphism, Encapsulation).

## ✨ Key Features

*   **Role-Based Access Control (RBAC):** The system implements an abstract `Usuario` class inherited by distinct roles with delimited permissions:
    *   **Administrator:** Full access to modify prices, add stock, and generate financial analytics.
    *   **Cashier:** Restricted access tailored for inventory queries and sales processing.
*   **Secure Persistence Engine:** Stock updates and price modifications use a secure algorithm that creates temporary files (`temp_inventario.txt`) to rewrite data strings (CSV-style) and safely replace the original file, preventing data corruption or loss.
*   **Transaction Processing:** Sales are handled via an in-memory "shopping cart" using Java Collections (`ArrayList<String>`). Upon completion, the system automatically deducts stock and logs the transaction.
*   **Financial Intelligence:** The system goes beyond simple totals by cross-referencing purchase prices (`precioCompra`) against sale prices (`precioVenta`) in real-time, generating analytical reports for **real net profit**, highest/lowest sales, and average ticket size.

## 📂 Data Structure (TXT)
The project emulates a relational database via three main files located in `src/Archivos/`:
*   `inventario.txt`: Stores `[Code, Name, PurchasePrice, SalePrice, Stock, Type]`.
*   `ventas.txt`: Logs `[InvoiceID, Date, Cashier, Products, Total, NetProfit]`.
*   `contador_ventas.txt`: Manages the auto-incremental invoice ID.

## ⚙️ Local Development Setup

### 1. Clone the repository
```bash
git clone https://github.com/daninanguyasmu/Papeleria_Shalom.git
```

### 2. Open in an IDE
This project can be run in any IDE with Java support (IntelliJ IDEA, Eclipse, Apache NetBeans, or VS Code with the Java Extension Pack).

### 3. Execution
*   Locate the main executable file at: `src/Clases/Main.java`.
*   Compile and run the `main` method.
*   *Default test credentials:*
    *   **Admin:** User: `admin` | Password: `admin123`
    *   **Cashier:** User: `cajero` | Password: `cajera29`

## 👨‍💻 Author
* **Daniel Horacio Nanguyasmú Mejía**  - *Core Developer* - [GitHub](https://github.com/daninanguyasmu)
* **Vanessa Stephania Roblero Rodríguez** - *Core Developer* - [GitHub](https://github.com/roblerovane29-a11y)
* **Emanuel Vázquez Roblero** - *Core Developer* - [GitHub](https://github.com/Ing-Vazquez20)
* **Jorge López Silva** - *Core Developer* - [GitHub](https://github.com/jorlopsil1404-debug)
* **Bryan Alexander Guillén Guillén** - *Core Developer* - [GitHub](https://github.com/alexguillen1512-beep)
Computer Systems Engineering Students @ TecNM Campus Tuxtla Gutiérrez
