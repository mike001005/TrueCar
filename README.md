# TrueCar

#Maven
Make sure maven is installed on your machine

http://maven.apache.org/download.cgi

----------------------------------------
#Configuration
-- Url: Url for the Test

-- Make: Make of the Car

-- Model: Model of the Car

-- Year: Year of the Car

-- Style: Style of the Car

-- Zip: ZipCode for the test

-- Driver: firefox

-----------------------------------------
#How to execute a specific test
  open a terminal or command line: 
  
  cd into the dir of the test Example: cd Documents/TrueCar
  
  Hit the enter key.
  
  type or Copy into termainal or window:
      
      mvn clean test -DsiteUrl=https://autoblog.truecar.com/nc/homepage -Dmake=Lincoln -Dmodel=MKS -Dyear=2015 -Dstyle="3.5L EcoBoost AWD" -Dzip=90401 -Ddriver=firefox

-------------------------------------------

#View Results
  cd into the dir of the test Example: cd Documents/TrueCar/target/surefire-reports
  
  open any text file

-------------------------------------------

#Approach
  My approach was to build a framework that would allow for any Car to be tested. the Test selects a random paint and one random option. I also test find car module on the configurator page.
 
--------------------------------------------

#Future Enhancements
  Add more Dirvers
    
    chrome
    
    phantomjs
    
    IE
  
  Add a way to randomly add multiple options


#Compromises
  I had to grab vales from the print certificate page to print out the result of the test. 





