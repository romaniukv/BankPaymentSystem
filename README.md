# FinalProject
## 13.  
Bank Payments System. User can have one or more bank accounts (deposit, credit). Access to account can be obtained after entering login and password. User can make bank transfers, pay bills, display general information about account (account balance, last transactions, expiration date). For Credit Accounts there are information about the credit limit, current indebtedness, the amount of accrued interest and credit rate. For Deposit Accounts - amount of the deposit, rate and history of the replenishment. User can apply for opening a credit account, if there is none. Administrator confirms account opening.

## Installation guide
### Step 1:
 Clone repository:  
```console
git clone https://github.com/romaniukv/FinalProject.git
```
### Step 2:
 Tomcat:  
 Add user in  
 ```console
 $TOMCAT_HOME/conf/tomcat-users.xml
 ```
 ```xml 
  <role rolename="manager-gui"/>
  <role rolename="manager-script"/>
  <user username="tomcat" password="alonsy10" roles="manager-script, manager-gui"/>
```
### Step 3:
Сreate new user in MySql:  
```sql
CREATE USER 'db_user'@'localhost' IDENTIFIED BY 'alonsy10';
GRANT ALL PRIVILEGES ON *.* TO 'db_user'@'localhost' WITH GRANT OPTION;
```
### Step 4:
Create database:
 - Run SetupDataBase.sql.
### Step 5:
Run 
```console
mvn tomcat7:deploy
```
in project root folder.
