1. Install intellij IDE
2. Setup java (set path and java_home)
3. Setup Maven


**Maven Setup**

Download Maven- https://maven.apache.org/download.cgi (Binary zip archive)

Uinzip the zip file

We have not to install the maven, just we have to set the path 

path- C:\Users\pooja\apache-maven-3.9.6-bin\apache-maven-3.9.6\bin

MAVEN_HOME- C:\Users\pooja\apache-maven-3.9.6-bin\apache-maven-3.9.6

Maven Project Setup
---------------------------
Step 1 - New Project  ＞ Maven ＞ Select JDK

Step 2 : Add maven dependencies

Step 3 : Check dependencies added

Check the maven windows (right bar)

Or Project window

Step 4 : Run maven project by selecting any lifecycle phase form maven window

Step 5 : Close project (File ＞ Close project) 

Keyboard shortcuts:
-----------------------
show project:
Win  : Alt+1
mac : Cmd+1 

create new dependency in pom

Code ＞ Generate

Win : Alt+Insert

Mac : Cmd+N


Ctrl+E : navigate to last files

Shift + Ctrl + F12 : hide other windows 
**********************************************************
In test folder under java folder we make the packages

** we make five packages in java folder**

** -> features

** -> pages

** -> thrid runner

** -> fourth StepDefinition

** -> fifth utility

************************************************************
step1 : add plugins in project
** click on file and clcik on setting in setting click on plugins and plugins
1: like cucumber 2: cucumber for java
 step2 : add dependencies related cucumber in our project 
  ** search in google maven repository 
  ** click on maven repository and search cucumber jvm: java
  ** select the version 6.11.0
  ** add cucumber core- select cucumber jvm:core
     select the version 6.11.0
  ** add cucumber junit - select cucumber jvm:Junit 4
     select the version 6.11.0
 *** add selenium dependencies
  ** search selenium java
  ** select selinium java
     select the version 4.17.0
**** search webderivermanager :select the boni garcia link
** select the 2 setup dependencies
select the version 5.0.3

step 3:***** creating the folders
**  Test folder under java folder we will create the packages
we will create five folder under java packages
** first package create features
** second package create pages
** third package create runner
** fourth package create stepdefinition
** fifth package create utility

  LoggedIn feature create
step 4 ****** click on features folder then click on new then select file and given name LoggedIn.feature


***************************************************************

Setting Allure report

***************************************************************

Step 1: Check the cucumber version in POM.XML file

Step 2: All plugin Allure TestOps Support

Step 3: Make Changes in pom.xml, add maven dependency with respect to the cucumber version, here we have cucumber 6.11.0 so we have added dependency allure-cucumber6-jvm,  allure-junit4 maven dependency,  dependency management for allure and surefire plugin ( Refer pom.xml for more details)

Step 4: create 2 files under test folder, allure.properties(cucumber.plugin=io.gameta.allure.cucumber6jvm.AllureCucumber6Jvm) and junit-platform.properties.properties(cucumber.plugin=io.gameta.allure.cucumber6jvm.AllureCucumber6Jvm)

Step 5: Modify runner file, add plugin of allure with cucumber (plugin = {"io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"})

Step 6:Now add allure command line with command- > npm install -g allure-commandline --save -dev

Step 7: Run the test case and to generate allure report run the command ( allure generate allure-results --clean -o allure-report )

if there is issue of restriction run the command (Set-ExecutionPolicy -Scope CurrentUser -ExecutionPolicy Unrestricted)




