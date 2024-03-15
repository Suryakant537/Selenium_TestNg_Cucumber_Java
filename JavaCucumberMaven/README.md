#Selenium Appium Rest Assured TestNG Maven Cucumber Project
This project is a comprehensive automation framework built using Selenium, TestNG,
Maven, BDD (Behavior-Driven Development) with Cucumber, Appium, Rest Assured, and
Allure Report. It aims to provide a robust and scalable solution for automating web and
mobile application testing.

#Overview
Rest assured using The Selenium TestNG Maven BDD Cucumber Appium To speed up the
testing process, the AllureReport Project combines the strength of numerous well-known
automation tools and frameworks. It makes use of Selenium WebDriver for testing web
applications, Appium for testing mobile applications, and Rest Assured for testing APIs. The
management, execution, of tests are done using the TestNG framework and reporting with
allure. Cucumber is also used to execute BDD concepts for developing scenarios that are
readable by humans and enhancing communication between technical and non-technical
parties. For the purpose of producing thorough and aesthetically pleasing test reports, the
Allure Report plugin is integrated.

#Key Features
● Selenium WebDriver: The Selenium WebDriver library is used for automating web
application testing. It provides a rich set of APIs for interacting with web elements,
performing actions, and validating the application's behaviour.
● TestNG: TestNG is a powerful testing framework that allows organising and
managing test cases efficiently. It supports parallel execution, data-driven testing,
and test configuration through annotations.
● Maven: Maven is a popular build automation tool used for managing dependencies,
building the project, and executing tests. It simplifies the project setup and provides a
standardised structure for managing dependencies and plugins.
● BDD with Cucumber: Behaviour-Driven Development (BDD) is a collaborative
approach to software development that emphasises communication between
stakeholders. Cucumber is a BDD tool that allows writing test scenarios in plain
English using the Gherkin syntax. It bridges the gap between technical and
non-technical team members.
● Appium: Appium is an open-source automation framework for mobile applications. It
supports both Android and iOS platforms, allowing you to write tests that can run on
multiple devices and simulators/emulators.
● Rest Assured: Rest Assured is a Java library specifically designed for RESTful API
testing. It provides a simple and intuitive DSL (Domain-Specific Language) for
making HTTP requests, validating responses, and performing assertions.
● Allure Report: Allure Report is a flexible reporting tool that generates
comprehensive and visually appealing test reports. It provides detailed information
about test execution, including test status, steps, screenshots, and logs.

#Getting Started
To set up and run this project on your local machine, follow these steps:
● Install Java Development Kit (JDK):
● Visit the Oracle website
(https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) and
download the JDK appropriate for your operating system.
● Follow the installation instructions provided for your specific operating system.
● Install Maven:
● Visit the Apache Maven website (https://maven.apache.org/download.cgi) and
download the latest binary distribution.
● Unzip the downloaded file to a directory of your choice.
● Add the Maven bin directory to your system's PATH environment variable.
● Install Selenium WebDriver:
● Selenium WebDriver is a Java library that requires the use of a build
management tool like Maven.
● Create a new Maven project or open an existing one.
● Add the Selenium WebDriver dependency to your project's pom.xml file:
<dependency>
<groupId>org.seleniumhq.selenium</groupId>
<artifactId>selenium-java</artifactId>
<version>3.141.59</version>
</dependency>
● Install TestNG:
● TestNG is a testing framework that works well with Selenium.
● Add the TestNG dependency to your project's pom.xml file:
<dependency>
<groupId>org.testng</groupId>
<artifactId>testng</artifactId>
<version>7.4.0</version>
</dependency>
● Install Appium:
● Appium is an open-source mobile automation framework.
● Install Node.js from the official website (https://nodejs.org/en/download/).
● Open a terminal or command prompt and run the following command to install
Appium:
npm install -g appium
● Install Rest Assured:
● Rest Assured is a Java library used for testing RESTful APIs.
● Add the Rest Assured dependency to your project's pom.xml file:
<dependency>
<groupId>io.rest-assured</groupId>
<artifactId>rest-assured</artifactId>
<version>4.4.0</version>
</dependency>
● Install Allure Report:
● Allure is a reporting framework for test automation.
● Add the Allure TestNG dependency to your project's pom.xml file:
<dependency>
<groupId>io.qameta.allure</groupId>
<artifactId>allure-testng</artifactId>
<version>2.15.0</version>
<scope>test</scope>
</dependency>
● Additionally, you need to configure Allure to generate the report. You can do this by
adding the following plugins to your project's pom.xml file:
<build>
<plugins>
<plugin>
<groupId>io.qameta.allure</groupId>
<artifactId>allure-maven</artifactId>
<version>2.10.0</version>
</plugin>
</plugins>
</build>
Once you've followed these steps and added the dependencies to your Maven project, you
should have Selenium, Appium, Rest Assured, TestNG, Maven, and Allure report installed
and ready to use in your automation project. Remember to run mvn clean test to execute
your tests and generate Allure reports.

#Project Structure
The project follows a standard Page Object Model with Maven project structure. Here is an
overview of the main directories:
● src/main/java: Contains the Java source code for implementing test logic, page
objects, and utility classes.
● src/main/resources: Includes configuration files, property files, and other resources
required for the project.
● src/test/java/common: Contains the source code for defining config
files,DriverInstance classes with other webUtilities.
● src/test/java/StepDefs: Contains the source code for step definitions.
● src/test/java/Pages: Contains the Java source code for Pages that contains
methods and locators.
● src/test/java/TestRunner: Contains the Java source code for defining test runners.
● src/test/java/Schema:Contains the JSON source data for Different Endpoints.
● target: The output directory for compiled classes, generated reports, and other build
artifacts.

#Commands To execute Tests and Fetch Reports
● Web Automations Test Cases are Executed By Maven Command
mvn clean test -D"cucumber.filter.tags"=@Web -D"tags1"=@Web
● API Automations Test Cases are Executed By Maven Command
mvn clean test -D"cucumber.filter.tags"=@API -D"tags1"=@API
● Android Automations Test Cases are Executed By Maven Command
mvn clean test -D"cucumber.filter.tags"=@Android -D"tags1"=@Android
● Allure report: To Fetch Allure report we run this command
allure serve .\allure-results\
● Jenkins : To Execute Jobs(Web/API/Mobile) on Jenkins we have fixed this in Command
Line : mvn clean test -D"cucumber.filter.tags"=%tags% -D"tags1"=%tags%