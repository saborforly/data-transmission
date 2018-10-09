[spade-war](https://bitbucket.org/berkeleylab/nest-spade-war) is vanilla front end to the [SPADE](https://bitbucket.org/berkeleylab/nest-spade) J2EE application. This project bindles up the standard SPADE jar files into a WAR file that can then be deployed into a JBoss7 application server.

This project can also act as a template from which a customized implementation of SPADE can be created. Any class that is needed to customize SPADE can be included in a cloned copy of this project and any of the default timers can be customized (or even replaced or removed) by editing the PeriodicExecution class.

Similarly the defualt HTML pages can be customized by editing or replacing the pades that appear in the webapp directory.
 