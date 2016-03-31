###SampleSyncAdapter Analysis
* source
https://github.com/android/platform_development/tree/master/samples/SampleSyncAdapter

* server 
https://samplesyncadapter2.appspot.com/

* UserName: user, Passwd: test
* The Sync of the Server in [url](https://samplesyncadapter2.appspot.com/sync)  can't work when client sends some dirty data. So the synchronization from client to server can't be tested.
* You can reset the database in the server using the [url](https://samplesyncadapter2.appspot.com/reset_database)
* I have tried to make a server on google appengine, but failed because the source code of server is written in Python2.5 and it should not be compatible with Python2.7 on google appengine server. Therefor I can't modify the soure code to find the problems of synchronization.

###App Process
![](https://raw.githubusercontent.com/fitzlee/SampleSyncAdapter/master/document/process.jpg)

#####1. SampleSyncAdapter App
> Sign in
> Sign up
> Edit Contacts
> Other Activities

#####2. Google Synchronize Process
> 1. Get the local dirty data from sqlite3(contact2.db)
> 2. Send the dirty data to server.
> 3. And get the updateContacts from server.
> 4. Update local sqlite3 db using the updateContacts

#####3. Other Apps
> Access of other apps

###Two services
There are two services in the authenticator demo. 
* AuthenticationService authenticator
> for sign_in and adding account
> for the access of other apps

* SyncService syncadapter
> for synchronization of contacts, including client_to_server and server_to_client

* AndroidManifest.xml
```
 <service
	android:name=".authenticator.AuthenticationService"
	android:exported="true">
	<intent-filter>
		<action android:name="android.accounts.AccountAuthenticator" />
	</intent-filter>

	<meta-data
		android:name="android.accounts.AccountAuthenticator"
		android:resource="@xml/authenticator" />
</service>

<service
	android:name=".syncadapter.SyncService"
	android:exported="true">
	<intent-filter>
		<action android:name="android.content.SyncAdapter" />
	</intent-filter>

	<meta-data
		android:name="android.content.SyncAdapter"
		android:resource="@xml/syncadapter" />
	<meta-data
		android:name="android.provider.CONTACTS_STRUCTURE"
		android:resource="@xml/contacts" />
</service>
```

###Activities
* AuthenticatorActivity for login and account adding.
* SignUpActivity for register an account.
* EditContactActivity for edit and modify contact if permissions allowed

###Contact Manager
* ContactManager.java, the main api for updating, adding and delete contacts. 
* ContactOperations.java, for updating phone, email, name and other fields. 
* BatchOperation.java, just for excute together to enhance perfermance. 


###xml/authenticator.xml
* The attributes in this XML file provide configuration information for the Account Manager
* in AndroidManifest.xml, you can set AuthenticationService's meta-data with this authenticator.xml

###xml/contacts.xml
contacts.xml(profile configuration, a mimetype)
* The standard columns representing contact's info from social apps
* SampleSyncAdapterColumns(ContactOperations.addProfileAction) 

###xml/syncadapter.xml
for SyncService and SyncAdapter setting their configuration, it can be found in AndroidManifest.xml


###Sign in Process
![](https://raw.githubusercontent.com/fitzlee/SampleSyncAdapter/master/document/Signin.jpg)
* The process of Sign-in is simple, including verifying the username and passwd, creating account in AccountManager and returning successful or error message to authenticator.

* To write a demo and make my idea clear, I have analysed deeply the open source code of SampleSyncAdapter and other demos about ContactContract apis. You can see more information from this repository, [ContactSyncAdapter](https://github.com/fitzlee/ContactSyncAdapter).



###Synchronize Contacts Process
![](https://raw.githubusercontent.com/fitzlee/SampleSyncAdapter/master/document/Synchronize.jpg)

* There are two key aspects in Synchronization of contacts,

> 1. server, NetworkUtilities(blockingGetAuthToken， SyncContacts)

> 2. client, ContactManager ContactOperations BatchOperation (add, update and delete contacts);  AccountManger ContentResolver (query db).


###Contacts2.db Analysis
* in /data/data/com.android.providers.contacts/databases/contacts2.db 
* tables: mainly include raw_contacts, data, mimetype and accounts
* fields: mainly raw_contact_id,mimetype_id
![](https://raw.githubusercontent.com/fitzlee/SampleSyncAdapter/master/document/contacts2db_raw_contacts.jpg)
![](https://raw.githubusercontent.com/fitzlee/SampleSyncAdapter/master/document/contacts2db_data.jpg)
![](https://raw.githubusercontent.com/fitzlee/SampleSyncAdapter/master/document/contacts2db_mimetype.jpg)
![](https://raw.githubusercontent.com/fitzlee/SampleSyncAdapter/master/document/contacts2db_accounts.jpg)


###Apk Screenshots(you can download in release)

* This modified sample demo has implemented the material design(version >=4.4) in LoginActivity, SignUpActivity and EditContactActivity with the support library v7 and v4. And as a result, this demo also support the lowest android 2.3 version. 

![](https://raw.githubusercontent.com/fitzlee/SampleSyncAdapter/master/document/pic_signin.jpg)
![](https://raw.githubusercontent.com/fitzlee/SampleSyncAdapter/master/document/pic_signup.jpg)
![](https://raw.githubusercontent.com/fitzlee/SampleSyncAdapter/master/document/pic_profile.jpg)
![](https://raw.githubusercontent.com/fitzlee/SampleSyncAdapter/master/document/pic_edit.jpg)
