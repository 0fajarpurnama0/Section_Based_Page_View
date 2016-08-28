# Section_Based_Page_View
A prototype application that demonstrates page view on more detailed section level of a html page, using Javascript, Java Servlet, JDBC, and MySQL.

! For the concept only you may try just opening the html files on your browser which demonstrates the html and javascript only (no need for API)! or just visit this link "http://plnkr.co/l4p6tt".

The rest is tested on Tomcat7 (deploy this entire source to "/var/lib/tomcat7/webapps/ROOT" for my case) and MySQL with database username = "user" and password = "pass". To change for your own setting open and .java source file on src folder then recompile and put the classes on WEB-INF/classes. Here's how I compile:

export CLASSPATH=$CLASSPATH:"/usr/share/java/mysql-connector-java-5.1.39.jar:/usr/share/tomcat7/lib/servlet-api.jar"
javac section_page_view_prototype_api_store.java
javac section_page_view_prototype_api_show.java
