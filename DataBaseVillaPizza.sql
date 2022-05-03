create database villa_pizza;
use villa_pizza;
drop database villa_pizza;
SET FOREIGN_KEY_CHECKS=0;



create table MENU(
MenuItemID int,
itemName varchar(50),
itemPrice int,
foodType varchar(50),
primary key(MenuItemID));

create table CustomerOrderLine(
OrderID int,
ItemQuantity int,
itemID int,
priceLine float,
primary key(OrderID,itemID),
foreign key(itemID) references MENU(MenuItemID) on delete cascade,
foreign key(OrderID) references CustomerOrder(OrderID) on delete cascade
);

create table CustomerOrder(
OrderID int,
OredrDate varchar(50),
price float,
DeliveryMethod varchar(50),
primary key(OrderID));

create table Customer(
CustomerID int,
CustomerName varchar(50),
CustomerPhone int,
CustomerAddress varchar(50),
primary key(CustomerID));

create table MakeCustomerOrder(
EmployeeResponseID int,
OrderID int,
CustomerID int,
primary key(OrderID,EmployeeResponseID,CustomerID),
foreign key(EmployeeResponseID) references Employees(EmployeeId) on update cascade,
foreign key(OrderID) references CustomerOrder(OrderID) on update cascade,
foreign key(CustomerID) references Customer(CustomerID) on update cascade
);
drop table MakeCustomerOrder;

create table Employees(
EmployeeId int primary key,
EmployeeName varchar(30),
EmployeePhone varchar(30),
EmployeeSalary  float,
EmployeeAddress varchar(30),
EmployeeGender varchar(30),
EmployeeDateOfBirth varchar(30),
EmployeeDateOfEmployment varchar(30),
EmployeeDateOfResignation varchar(30),
EmployeeDepartmentName varchar(30),
BranchWorkId int,
foreign key (BranchWorkId) references Branch(BranchId) on update cascade


);
ALTER TABLE Employees MODIFY EmployeeId int auto_increment;
ALTER TABLE Employees
MODIFY COLUMN EmployeeSalary varchar(30);


insert into Employees values(1,"ahmad","05997777",2500.0,"ramallah","M","29/4/2000","12/12/2021","10/10/2022","casher",1);
insert into Employees values(2,"hassan","05996666",2500.0,"ramallah","M","2/6/2000","12/2/2021","10/11/2022","casher",1);
update  Employees set EmployeeName = "ali" where EmployeeId = 2;
update Employees set BranchWorkId=2 where EmployeeId = 2;


select * from Employees;

create table Branch
(BranchId int primary key,
location varchar(30),
nameByLocation varchar(30),
phoneNumber varchar(30),
Email varchar(30),
numberOfEmployees int

);
ALTER TABLE Branch MODIFY BranchId int auto_increment;







alter table Employees add Foreign Key (BranchWorkId)References Branch(BranchId) on update cascade;

insert into Branch values(1,"ramallah","ramallahBramch","02222","ram@ram",20);
insert into Branch values(2,"nblus","nablusBramch","02221","nab@nab",23);
update Branch set numberOfEmployees=1 where BranchId =2 ;
select * from Branch;




create table Item(
ItemId int primary key,
ItemName varchar(30),
ItemPrice float,
ItemType varchar(30)
);
ALTER TABLE Item MODIFY ItemId int auto_increment;

drop table item;

create table purchaseOrder
(purchaseOrderId int primary key,
purchaseOrderprice float,
purchaseOrderDate varchar(30)
);
ALTER TABLE purchaseOrder MODIFY purchaseOrderId int auto_increment;


create table purchaseOrderLine(
purchaseOrderId int, 
ItemId int,
Foreign Key (purchaseOrderId)References purchaseOrder(purchaseOrderId) on delete cascade,
Foreign Key (ItemId)References Item(ItemId) on delete cascade,
primary key(purchaseOrderId,ItemId),
ItemQuantity int,
priceLine float
);
drop table purchaseOrderLine;


create table Suppliers(
SupplierID int primary key,
SupplierName varchar(30),
SupplierPhone varchar(30),
SupplierEmail varchar(30),
SupplierLocation varchar(30)
);

ALTER TABLE Suppliers MODIFY SupplierID int auto_increment;

create table makePurchaseOrder(
BranchId int,
purchaseOrderId int,
EmployeeResponseId int,
SupplierID int,
Foreign Key (SupplierID)References Suppliers(SupplierID) on delete cascade,
Foreign Key (BranchId)References Branch(BranchId) on delete cascade,
Foreign Key (purchaseOrderId)References purchaseOrder(purchaseOrderId) on delete cascade,
Foreign Key (EmployeeResponseId)References Employees(EmployeeId) on delete cascade,
primary key(BranchId,purchaseOrderId,EmployeeResponseId,SupplierID)
);
drop table makePurchaseOrder;



create table BranchManegers(
BranchId int ,
managerEmployeeId int,
 primary key( BranchId,managerEmployeeId),
 Foreign Key (BranchId)References Branch(BranchId) on delete cascade,
 Foreign Key (managerEmployeeId)References Employees(EmployeeId) on delete cascade
);

insert into BranchManegers values(1,1),(2,2);

drop table BranchManegers;


drop table makePurchaseOrder;
show tables
show databases;
use villa_pizza;
show tables;
delete from  Employees where EmployeeId =3;
select  * from Employees;
Insert into Employees  values(3,"Mohammad","05996444","2500","ramallah","M","2/6/2000","12/2/2021",null,"Baker","1");
select * from Employees where EmployeeDepartmentName = 'Baker';
select * from Employees where EmployeeDateOfResignation IS  NOT NULL;


insert into Suppliers values(1,"Mahmoud","059999999","mah@mah","ramallah");
insert into Suppliers values(2,"Mohammad","05998888","moh@moh","nablus");

insert into Item values(1,"box",3,"nonfood");
insert into Item values(2,"bread",5,"food");

insert into purchaseOrder values(1,45,"1/1/2022");
insert into purchaseOrder values(2,33,"1/10/2022");

insert into purchaseOrderLine values(1,1,15,45);
insert into purchaseOrderLine values(1,2,15,75);

insert into purchaseOrderLine values(2,1,20,60);
insert into purchaseOrderLine values(2,2,20,100);


insert into makePurchaseOrder values(1,1,1,1);
insert into makePurchaseOrder values(2,2,2,2);
show tables;

select b.nameByLocation ,m.purchaseOrderId,o.purchaseOrderprice,o.purchaseOrderDate,m.EmployeeResponseId,e.EmployeeName,s.SupplierName,s.SupplierPhone from Branch b,Employees e,Suppliers s ,PurchaseOrder o ,makePurchaseOrder m where m.BranchId=b.BranchId and m.SupplierID=s.SupplierID and e.EmployeeId=m.EmployeeResponseId and m.purchaseOrderId=o.purchaseOrderId and  b.nameByLocation=BranchName ;


select e.ItemId,e.ItemName,e.ItemPrice,pl.ItemQuantity,e.ItemType from purchaseOrderLine pl, Item e where pl.purchaseOrderId=2 and pl.ItemId=e.ItemId;

select b.nameByLocation ,m.purchaseOrderId,o.purchaseOrderprice,o.purchaseOrderDate,m.EmployeeResponseId,e.EmployeeName,s.SupplierName,s.SupplierPhone 
from Branch b,Employees e,Suppliers s ,PurchaseOrder o ,makePurchaseOrder m 
where m.BranchId=b.BranchId and m.SupplierID=s.SupplierID and e.EmployeeId=m.EmployeeResponseId and m.purchaseOrderId=o.purchaseOrderId and  e.EmployeeName="ahmad";


select b.nameByLocation ,m.purchaseOrderId,o.purchaseOrderprice,o.purchaseOrderDate,m.EmployeeResponseId,e.EmployeeName,s.SupplierName,s.SupplierPhone from Branch b,Employees e,Suppliers s ,PurchaseOrder o ,makePurchaseOrder m where m.BranchId=b.BranchId and m.SupplierID=s.SupplierID and e.EmployeeId=m.EmployeeResponseId and m.purchaseOrderId=o.purchaseOrderId and  e.EmployeeName="ahmad";

insert into BranchManegers values(1,1),(2,2);

String mystring = "select b.nameByLocation ,m.purchaseOrderId,o.purchaseOrderprice,o.purchaseOrderDate,m.EmployeeResponseId,e.EmployeeName,s.SupplierName,s.SupplierPhone from Branch b,Employees e,Suppliers s ,PurchaseOrder o ,makePurchaseOrder m where m.BranchId=b.BranchId and m.SupplierID=s.SupplierID and e.EmployeeId=m.EmployeeResponseId and m.purchaseOrderId=o.purchaseOrderId and s.SupplierName=" + '"' + BranchNamee + '"' + " and b.nameByLocation=" + '"' + BranchNamee + '"' + ";";

    	String mystring = "select b.nameByLocation ,m.purchaseOrderId,o.purchaseOrderprice,o.purchaseOrderDate,m.EmployeeResponseId,e.EmployeeName,s.SupplierName,s.SupplierPhone from Branch b,Employees e,Suppliers s ,PurchaseOrder o ,makePurchaseOrder m where m.BranchId=b.BranchId and m.SupplierID=s.SupplierID and e.EmployeeId=m.EmployeeResponseId and m.purchaseOrderId=o.purchaseOrderId and  b.nameByLocation=" + '"' + BranchNamee + '"' + ";";

select b.nameByLocation ,m.purchaseOrderId,o.purchaseOrderprice,o.purchaseOrderDate,m.EmployeeResponseId,e.EmployeeName,s.SupplierName,s.SupplierPhone from Branch b,Employees e,Suppliers s ,PurchaseOrder o ,makePurchaseOrder m where m.BranchId=b.BranchId and m.SupplierID=s.SupplierID and e.EmployeeId=m.EmployeeResponseId and m.purchaseOrderId=o.purchaseOrderId and s.SupplierName="Mahmoud" and b.nameByLocation="ramallahBramch";

select * FROM Employees;

update Employees set EmployeeDateOfResignation=null where EmployeeId =4 or  EmployeeId =5;
Insert into Employees(EmployeeName,EmployeePhone,EmployeeSalary,EmployeeAddress,EmployeeGender,EmployeeDateOfBirth,EmployeeDateOfEmployment,EmployeeDateOfResignation,EmployeeDepartmentName,BranchWorkId)  values("Ali","05997777","3500","nabuls","M","2/7/2001","1/1/2022","null","delivery driver","2");

select SupplierID from suppliers where SupplierLocation="ramallah   " and  SupplierName="Mahmoud" and SupplierPhone="059999999"and SupplierEmail="mah@mah";


select * from item;
select * from purchaseOrder;
select * from purchaseOrderLine;
select * from MakePurchaseOrder;
select * from Suppliers;
select * from Branch;
select * from Employees;




delete from purchaseOrder where purchaseOrderId=9;
select SupplierID,SupplierName,SupplierPhone,SupplierEmail,SupplierLocation from suppliers where SupplierLocation='ramallah'and  SupplierName='Mahmoud' and SupplierPhone='059999999'and SupplierEmail='mah@mah';

			insert into Suppliers values(0,'"+supName+"','"+supPhone+"','"+supEmail+"','"+supLocation+"');
insert into makePurchaseOrder value("+BranchhhId+","+poId+","+empResNumb+","+supId+");
show tables;

select sum(o.purchaseOrderprice) from makePurchaseOrder m,purchaseOrder o where m.BranchId=1 and m.purchaseOrderId=o.purchaseOrderId;


##Hamedo

create table OrderLine(
OrderID int,
ItemQuantity int,
itemID int,
priceLine float,
primary key(OrderID,itemID),
foreign key(itemID) references MENU(itemID) on delete cascade,
foreign key(OrderID) references CustomerOrder(OrderID) on delete cascade
);


create table CustomerOrder(
OrderID int auto_increment,
OrderDate varchar(50),
OrderPrice float,
DeliveryMethod varchar(50),
primary key(OrderID));

create table Customer(
CustomerID int auto_increment,
CustomerName varchar(50),
CustomerPhone int,
CustomerAddress varchar(50) null,
primary key(CustomerID)
);

create table MakeCustomerOrder(
EmployeeResponseID int,
OrderID int,
CustomerID int,
primary key(OrderID,EmployeeResponseID,CustomerID),
foreign key(EmployeeResponseID) references Employees(EmployeeId) on delete cascade,
foreign key(OrderID) references CustomerOrder(OrderID) on delete cascade,
foreign key(CustomerID) references Customer(CustomerID) on delete cascade
);

create table MENU(
itemID int auto_increment,
itemName varchar(50),
itemPrice int,
foodType varchar(50),
primary key(itemID)
);

insert into MENU values(1,"burgar",20,"meat");
insert into MENU values(2,"kola",5,"drink");

insert into OrderLine values(1,1,1,20);
insert into OrderLine values(2,2,2,10);
insert into OrderLine values(2,2,1,40);

insert into CustomerOrder values(1,"12/12",20,"in");
insert into CustomerOrder values(2,"12/4",50,"out");

insert into Customer values(1,"tawfeeq",0599878,null);
insert into Customer values(2,"rdai",0566989,"ramallah");

insert into MakeCustomerOrder values(1,1,1);
insert into MakeCustomerOrder values(2,1,2);

select m.ItemName,l.ItemQuantity,l.priceLine from OrderLine l,Menu m where l.OrderID=1 and l.itemID=m.ItemId;