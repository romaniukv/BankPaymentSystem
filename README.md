# FinalProject
## Вариант 13.  
Система Банковские платежи. Пользователь может иметь один или
несколько банковских Счетов (депозитные, кредитные). Доступ к своему
Счету можно получить после введения логина и пароля. Пользователь
может делать банковские переводы, оплачивать счета, выводить на экран
общую информацию (баланса счета, последних операциях, срок действия).
Для Кредитных Счетов также доступна информация по кредитном лимите,
текущей задолженности, сумме начисленных процентов, кредитной ставкой.
Для Депозитных счетов - сумма депозита, ставка, история пополнения.
Пользователь может подать запрос на открытие кредитного счета, если
таковой отсутствует. Администратор подтверждает открытие счета с учетом
размера депозита и срока действия.

## Installation guide
### Step 1:
 Clone repository:  
```console
git clone https://github.com/romaniukv/FinalProject.git
```
### Step 2:
 Tomcat 8:  
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
```sql
CREATE 
```
Run SetupDataBase.sql.
### Step 5:
Run 
```console git clone https://github.com/romaniukv/FinalProject.git
mvn packge
```
in project root folder.
