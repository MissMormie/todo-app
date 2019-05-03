#TODO application

## Get it working
To run this application on your own infrastructure you need to set up a MySQL 
database and configure this.  

In src/main/resources/application.properties update:
⋅⋅⋅ spring.datasource.url=jdbc:mysql://**localhost/todo_app**?useLegacyDatetimeCode=false&useTimezone=true&serverTimezone=WET&useSSL=false
⋅⋅⋅ spring.datasource.username=**username**
⋅⋅⋅ spring.datasource.password=**password**

After that it's a regular spring boot application. 
