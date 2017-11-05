# KondiBazis-Web
Building the project:
`mvn clean install`

Running With Wildfly 10:

Build the project with:
`mvn clean install -Pwith-wildfly`

Running from the parent:
`mvn -pl fitbase-web wildfly:run`

Running from the fitbase-web module:
`mvn wildfly:run`

With the run goal the server starts and deploys the .war.
With start goal you can start the server.
With deploy goal you can deploy the app and etc...