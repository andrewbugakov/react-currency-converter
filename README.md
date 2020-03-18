# react-currency-converter
Проект реализует логику работы конвертера валют.
Используемые технологии: Java, Spring Boot, Reactjs, Postgresql, Maven
информацию о курсах валют получается с помощью api https://cbr.ru/development/sxml/
Для запуска приложения необходимо установить:
1) Java 8 и выше https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html
2) Postgresql 12 https://www.postgresql.org/download/
3) Maven https://maven.apache.org/download.cgi
Шаги для запуска приложения:
1) установить в src/main/resources/application.yml свои параметры подключения к бд(url, username, password,hibernate.ddl-auto),
2) собрать проект с помощью команды: mvn clean install
3) запустить src/main/java/com/example/converter/Application.java
