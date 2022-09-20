CREATE TABLE Employees (
  EmployeeID int,
  Name Varchar(50),
  Email Varchar(50),
  Sex Varchar(8),
  PhoneNumber Int,
  Password Varchar(24),
  PRIMARY KEY (EmployeeID)
);

CREATE TABLE Payment (
  CardNumber int,
  CustomerID char(10),
  SecurityNum int,
  PaymentType varchar(20),
  PRIMARY KEY (CustomerID),
  FOREIGN KEY (CustomerID) references CUSTOMERS
);

CREATE TABLE ProductType (
  CategoryID int,
  CategoryName Varchar(16),
  PRIMARY KEY (CategoryID)
);

CREATE TABLE StoreInventory (
  StoreID int,
  StoreName Varchar(16),
  ProductID int,
  Quantity int,
  Price float(10),
  CategoryID int,
  AddressID int,
  PRIMARY KEY (StoreID),
  Foreign key (CategoryID) references ProductType
);

CREATE TABLE InStore (
  ProductID int,
  TrackingNumber int,
  PRIMARY KEY (TrackingNumber)
);

CREATE TABLE WarehouseInventory (
  WarehouseID int,
  WarehouseName Varchar(16),
  ProductID int,
  Quantity int,
  Price float(10),
  BrandID int,
  AddressID int,
  PRIMARY KEY (WarehouseID)
);

CREATE TABLE Customers (
  CustomerID char(10),
  Name varchar(50),
  Email varchar(100),
  Age int,
  Sex char(6),
  PhoneNumber int,
  Password varchar(50),
  PRIMARY KEY (CustomerID)
);

CREATE SEQUENCE costumers_sequence
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;
    
CREATE SEQUENCE employee_sequence
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;
    
CREATE SEQUENCE cart_sequence
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;
    
CREATE SEQUENCE products_sequence
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;
    
CREATE SEQUENCE orders_sequence
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;    
    
CREATE SEQUENCE ship_sequence
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;   
/*
CREATE OR REPLACE TRIGGER Customers_on_insert
    BEFORE INSERT ON Customers
    FOR EACH ROW
BEGIN
    :new.CustomerID := customers_sequence.nextval;
END;
*/


CREATE TABLE Products (
  ProductID int,
  Price float(10),
  Stock int,
  ProductName Varchar(16),
  CategoryID int,
  PRIMARY KEY (ProductID),
  Foreign key (CategoryID) references ProductType
);

CREATE TABLE ShippingOrder (
  CustomerID char(10),
  TrackingNumber int,
  CartID int,
  PRIMARY KEY (TrackingNumber),
  FOREIGN KEY (CustomerID) references CUSTOMERS
);

CREATE TABLE Address (
  CustomerID char(10),
  Street Varchar(16),
  City Varchar(16),
  State Varchar(16),
  Zipcode int,
  Country Varchar(16),
  PRIMARY KEY (CustomerID),
  FOREIGN KEY (CustomerID) references CUSTOMERS
);

CREATE TABLE Cart (
  CartID int,
  CustomerID CHAR(10),
  ProductID int,
  FOREIGN KEY (CustomerID) references CUSTOMERS,
  FOREIGN KEY (ProductID) references PRODUCTS
);

CREATE TABLE Orders (
  OrderID int,
  CustomerID CHAR(10),
  CartID int,
  DateInfo Date,
  Status varchar(20),
  PRIMARY KEY (OrderID),
  FOREIGN KEY (CustomerID) references CUSTOMERS
);


INSERT INTO CUSTOMERS (CUSTOMERID, NAME, EMAIL, PAYMENTMETHOD, AGE, SEX, PHONENUMBER, PASSWORD, ADDRESSID) VALUES (costumers_sequence.nextval, 'Carlos', 'carlos@hawk.iit.edu', '3', '3', 'M', '3', 'lol', '2');
INSERT INTO "HR"."EMPLOYEES" (EMPLOYEEID, NAME, EMAIL, SEX, PHONENUMBER, PASSWORD) VALUES ('2', 'Jacob', 'ja@hawk.iit.edu', 'F', '1234556', 'lolo');


SELECT * FROM PRODUCTS WHERE 'CATEOGRYID' = '1';

SELECT CATEGORYNAME FROM PRODUCTTYPE WHERE CATEGORYID = 1; 

SELECT * FROM ADDRESS NATURAL JOIN CUSTOMERS WHERE CUSTOMERID = 82;

select * from PRODUCTS natural join Cart where CARTID=52;

select * from PRODUCTS natural join Cart where CARTID=53;
select * from CUSTOMERS natural join SHIPPINGORDER natural join ADDRESS where CUSTOMERID=82;
select * from CUSTOMERS natural join SHIPPINGORDER natural join ORDERS natural join PRODUCTS where CUSTOMERID = 82;

select * from CUSTOMERS natural join ADDRESS natural join PAYMENT natural join SHIPPINGORDER;
