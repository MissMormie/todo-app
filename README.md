#TODO application

## Get it working
To run this application on your own infrastructure you need to set up MySQL 
and configure this to have a database available and accessible.  

In src/main/resources/application.properties update:
* spring.datasource.url=jdbc:mysql://**localhost/databasename**?useLegacyDatetimeCode=false&useTimezone=true&serverTimezone=WET&useSSL=false
* spring.datasource.username=**username**
* spring.datasource.password=**password**

After that it's a regular spring boot application. 
