###xml/authenticator.xml
* The attributes in this XML file provide configuration information for the Account Manager
* in AndroidManifest.xml, you can set AuthenticationService's meta-data with this authenticator.xml

###xml/contacts.xml
contacts.xml(profile configuration, a mimetype)
* The standard columns representing contact's info from social apps
* SampleSyncAdapterColumns(ContactOperations.addProfileAction) 

###xml/syncadapter.xml
for SyncService and SyncAdapter setting their configuration, it can be found in AndroidManifest.xml

###two service(AndroidManifest.xml)
There are two services in the authenticator demo. 
* AuthenticationService authenticator
> for sign_in and adding account
> for the access of other apps

* SyncService syncadapter
> for synchronization of contacts, including client_to_server and server_to_client

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

###Contacts2.db Analysis


