# Project Name: Playwright Framework

## Description
This is a Java-based Playwright testing framework using Maven and TestNG. It is designed download US Filings.

## Prerequisites
1. **Java JDK 17 or above** must be installed.
2. **Apache Maven** must be installed.
3. Configure the following environment variables:
   - `JAVA_HOME`
   - `MAVEN_HOME`
   - Add both to the system `PATH`.

## How to Run
1. Unzip the project folder.
2. Open a terminal or command prompt.
3. Navigate to the project folder where pom.xml is present.
4. Run the command:
5. command: mvn clean test -Dpath="Your Path Where Pdfs should be downloaded" -Drange=all
	Range can be given as:
							1. all -> To download all the PDFs
							2. 10 -> To download last 10 year docs
							3. 5 -> To download last 5 years docs
							4. last -> To download last year docs
