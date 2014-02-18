#Codebrag Installation Guide


##Installation Overview

In short:

1. Connect your repository
2. Edit `codebrag.conf` for advanced settings
2. Run `run.sh`

Enjoy!

## Prerequisites

* Java 1.7 installed. [Download here](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html) or install package for your server's OS.


##Questions? Help?
Should you encounter any issues with installation:

* Ping us at [ask@codebrag.com](mailto:ask@codebrag.com) 
* [FAQ](http://codebrag.com/devoxx/faq.html)
* [Codebrag Users group](https://groups.google.com/forum/#!forum/codebrag-users)


<div style="page-break-after: always;"></div>


## 1. Connect your repository

We suport Git and SVN repositories, both standalone and in the cloud (e.g. Github, Bitbucket).

*** NOTE: *** At this moment you can connect one repository per Codebrag instance. Support for multiple repositories is coming very soon.


<br>

### 1.1. Git repository

Checkout your repository to `codebrag/repos`:
	
	cd codebrag/repos
	git clone git://yourcompany.com/path/to/project-abc.git

And, that's it (usually). Codebrag will automatically use this repository.

##### 1.1.1. Git - manual authentication (optional)

If, for some reason, your repository requires authentication **every time** you execute a command, you need to provide username/password or passphrase in `codebrag.conf`:

	repositories {
		
		git-https {
			name = "project-abc"	# directory name in codebrag/repos
			username = "emanresu"
			password = "drowssap"
		}
		
		# or
		
		git-ssh {
			name = "project-abc"
			passphrase = "esarhpssap"
		}
	}


#### 1.2. SVN repository

Checkout your repository to `codebrag/repos`:
	
	cd codebrag/repos
	git svn clone git://yourcompany.com/path/to/project-abc.git

*** NOTE: *** Use `git svn clone` command as in the example below. **Do not** perform `svn checkout` instead, cause Codebrag **will not** be able to connect to such a repository.

*** NOTE: *** You need **Git version 1.7 or higher** to do this. [Download here](http://git-scm.com/) or install package for your server's OS.

##### 1.2.1. SVN - manual authentication (optional)

If, for some reason, your repository requires authentication **every time** you execute a command, you need to provide username/password or passphrase in `codebrag.conf`:

	TODO


	repository {
	    type = "svn"
	    svn {
	        name = "..." 
	        uri = "..."				// e.g. http://codebrag.com/svn
	        username = "..."
	        password = "..."
	    }
	}

<br>

**### 1.3. "email" section

Setup your email server so that Codebrag can send emails with invitations and notifications:

    email {
        smtp-host = "your.smtp.server.com"
        smtp-port = "465"
        smtp-username = "user@mydomain.com"
        smtp-password = "myPassword"
        from = "user@mydomain.com"
        encoding = "UTF-8"
        ssl-connection = true
        verify-ssl-certificate = true
    }

You can use internal SMTP server or external one (e.g. create [GMail](http://gmail.com)  or [Mandrill](http://mandrill.com/) account).

*** NOTE: *** Make sure Java has the required security certificates when using the SSL/TLS connection. You can skip certificate verification by setting `verify-ssl-certificate` to `false`.

### 1.4. "codebrag" section

##### application-url

The `application-url` property indicates the URL under which Codebrag will be available:

	application-url = "http://codebrag.mydomain.com:8080"
	
##### send-anon-usage-data

By default, Codebrag sends anonymous data about its usage (e.g. number of users, number of comments). This information **never includes any contents of your repository**.

Gathering such anonymous statistics helps us improve Codebrag. Hovewer, if you wish to disable this, set `send-anon-usage-data` property to `false`.

### 1.5. Other settings

All other settings are optional and usually you do not need to change them. They are listed at the end of this installation guide (Appendix A).
	
<div style="page-break-after: always;"></div>


## 2. Running Codebrag

*** NOTE: *** If you already have a previous Codebrag installation you need to run database migration scripts before running the new version. Go to the `mongo_migration` directory in the distribution package and follow the instructions from the `README.txt` file there.

To start Codebrag execute:

	./run.sh 			# Unix/OS X
	run.bat				# Windows
	
Logs will be written to `codebrag.log`.

To stop Codebrag execute:

	./stop.sh
	

## 3. Create user account

The first user who accesses Codebrag after installation will be asked to register an account.

Either the email address or the name provided have to match corresponding fields in `git log` in order to match commits with a given user.

*** NOTE: *** If your repository is large, you may need to wait until it is downloaded.

Other users can be invited to join Codebrag by choosing the "Invite team members" link from the menu in the upper right corner.

## 4. Questions? Help? Contact us

Do drop us a line at
[**ask@codebrag.com**](mailto:ask@codebrag.com) if you find an issue, or just want to share a thought or ask a question.

You may also want to check out the Codebrag [FAQ](http://codebrag.com/devoxx/faq.html) or join the [Codebrag Users group](https://groups.google.com/forum/#!forum/codebrag-users) to be among the very first to learn about bug fixes and new releases.

We hope you and your team will enjoy doing code reviews with Codebrag!


<div style="page-break-after: always;"></div>


### Appendix A - Optional Configuration Properties

#### 'codebrag' section

##### local-git-storage-path

Repository data will be stored under this location (Codebrag will create `repos` directory). Remember to set access rights accordingly so that Codebrag can read and write to it.

##### invitation-expiry-time

This property indicates how long invitation links are valid.

    //this setting uses Scala Duration syntax, i.e. 15 minutes, 24 hours, 2 days
	invitation-expiry-time= "24 hours"

##### user-email-notifications

    user-email-notifications {
        enabled = true
        check-interval = "15 minutes"
        user-offline-after = "5 minutes"
        daily-digest-hour = 9
    }

###### enabled

Indicates whether email notifications should be enabled. If notifications are enabled each user can switch them off in his profile in the application.

###### check-interval

Defines how often the system will check for notifications to send.

###### user-offline-after

Defines how long after closing the Codebrag tab the user will be considered offline.

###### daily-digest-hour

Defines when (full hour) daily digest emails will be sent.

#### "web-server" section

By default Codebrag starts on port 8080. Edit `port` property to change it.