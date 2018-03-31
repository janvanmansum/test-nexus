Test Nexus
==========
Trying out the Nexus Repository Manager OSS

Goals
-----
* Find out how easy/hard it is to install Nexus on CentOS.
* Find out if the Yum repository functionality works.
* Find out if Nexus can be provisioned using the scripting functionality
  in combination with RESTful API.

Requirements
------------
* Java (1.8.0)
* Groovy (2.4.14)
* Maven (3.5.2)
* Vagrant (2.0.1)
* VirtualBox (5.2.4)
* Ansible (2.4.2.0)

Running
-------
1. Open a terminal in the base directory of this project.
2. Execute `vagrant up`.

If this runs everything without error Nexus is installed and running at
http://192.168.33.32:8081/. (It may take half a minute to start up though.)
Alternatively you may map the IP to a name like `test.dans.knaw.nl` in your `/etc/hosts`
and then open http://test.dans.knaw.nl:8081/.







References
----------
* [Nexus Repository Manager OSS](https://help.sonatype.com/repomanager3)
