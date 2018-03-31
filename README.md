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

If this runs to the end without error, Nexus is installed and running at
http://192.168.33.32:8081/. (It may take half a minute to start up though.)
Alternatively you may map the IP to a name like `test.dans.knaw.nl` in your `/etc/hosts`
and then open http://test.dans.knaw.nl:8081/. The default username/password
(admin/admin123) has not been changed.

A blob store called 'yum' and a hosted Yum repo 'test' should also be there.

You can now deploy RPMs through the REST API:

```
curl -v -u admin:admin123 --upload-file path/to/my.rpm http://192.168.33.32:8081/repository/test/
```

Conclusions
-----------

### Installation
It is not really hard to install Nexus, although it does takes some manual steps.

### Yum repo
The Yum-repo works. It seems to have a custom layout. After uploading one RPM I got this layout in
the blob-store:

```
[root@test yum]# tree
.
├── 56E859E5-627B890A-94D89EA1-D5475209-D71F84C8-deletions.index
├── 56E859E5-627B890A-94D89EA1-D5475209-D71F84C8-metrics.properties
├── content
│   ├── tmp
│   │   ├── tmp$65b921a3-3134-4df0-9270-b2c3ffd234ef.bytes
│   │   ├── tmp$65b921a3-3134-4df0-9270-b2c3ffd234ef.properties
│   │   ├── tmp$6afb95ac-b419-4cdf-8003-f87e5a4232c5.bytes
│   │   ├── tmp$6afb95ac-b419-4cdf-8003-f87e5a4232c5.properties
│   │   ├── tmp$e56462e3-10d1-429e-9f63-609241db59e5.bytes
│   │   ├── tmp$e56462e3-10d1-429e-9f63-609241db59e5.properties
│   │   ├── tmp$f32679f6-c3bb-4f94-97ac-9d4fbc1995d1.bytes
│   │   └── tmp$f32679f6-c3bb-4f94-97ac-9d4fbc1995d1.properties
│   ├── vol-05
│   │   └── chap-09
│   │       ├── dce0b212-d50d-406e-b1ab-ee45dbc502a9.bytes
│   │       └── dce0b212-d50d-406e-b1ab-ee45dbc502a9.properties
│   ├── vol-11
│   │   └── chap-20
│   │       ├── 9c4b771f-9f9d-42ac-a677-d3edb686beea.bytes
│   │       └── 9c4b771f-9f9d-42ac-a677-d3edb686beea.properties
│   ├── vol-24
│   │   └── chap-13
│   │       ├── b81660bc-99cb-46ab-a273-5036b2d0e010.bytes
│   │       └── b81660bc-99cb-46ab-a273-5036b2d0e010.properties
│   └── vol-36
│       ├── chap-03
│       │   ├── b4ef13de-a193-49a6-bddc-5b5f24352fb4.bytes
│       │   └── b4ef13de-a193-49a6-bddc-5b5f24352fb4.properties
│       └── chap-36
│           ├── 98e5b331-f471-487d-b900-1127978f35b4.bytes
│           └── 98e5b331-f471-487d-b900-1127978f35b4.properties
└── metadata.properties
```

This is very different from the layout that you get when using `createrepo`, where you can still find the
RPM file in the repo. Through the HTML browsing interface at http://192.168.33.32:8081/service/rest/repository/browse/test/ you
can see that the RPM is still at least virtually present. If you try to `locate` it on the server, you will not find
it, however.

### Provisioning
Provisioning is a bit involved, but can be managed. You have to create a Groovy script to do the actual work and then
first wrap it in JSON and upload it, before you can execute it. It is probably easier to make the Groovy script itself idempotent
instead of trying detect everything from the Ansible (although you could also do a lot of the "local facts").

One bug: Nexus does not handle well the scenario in which you delete a script and then add a new script under the same name. This
leads to a unique key violation in its index. You would expect it to delete any entries in its index when deleting an indexed item.
This is not a blocker, though. You can overwrite existing scripts.

References
----------
* [Nexus Repository Manager OSS](https://help.sonatype.com/repomanager3)
