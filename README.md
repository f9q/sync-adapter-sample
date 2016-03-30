###SampleSyncAdapter Analysis
1. source
https://github.com/android/platform_development/tree/master/samples/SampleSyncAdapter

2. server 
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

###Sign in Process
![](https://raw.githubusercontent.com/fitzlee/SampleSyncAdapter/master/document/Signin.jpg)

> The process of Sign-in is simple, including verifying the username and passwd, creating account in AccountManager and returning successful or error message to authenticator.


> To write a demo and make my idea clear, I have analysed deeply the open source code of SampleSyncAdapter and other demos about ContactContract apis. You can see more information from this repository, [ContactSyncAdapter](https://github.com/fitzlee/ContactSyncAdapter).


###Synchronize Contacts Process
![](https://raw.githubusercontent.com/fitzlee/SampleSyncAdapter/master/document/Synchronize.jpg)

> There are two key aspects in Synchronization of contacts,
> 1. server, NetworkUtilities(blockingGetAuthToken， SyncContacts)
> 2. client, ContactManager ContactOperations BatchOperation (add, update and delete contacts);  AccountManger ContentResolver (query db).

###Apk Image(you can download in release)

> This modified sample demo has implemented the material design(version >=4.4) in LoginActivity, SignUpActivity and EditContactActivity with the support library v7 and v4. And as a result, this demo also support the lowest android 2.3 version. 

![](https://raw.githubusercontent.com/fitzlee/SampleSyncAdapter/master/document/pic_signin.jpg)
![](https://raw.githubusercontent.com/fitzlee/SampleSyncAdapter/master/document/pic_signup.jpg)
![](https://raw.githubusercontent.com/fitzlee/SampleSyncAdapter/master/document/pic_profile.jpg)
![](https://raw.githubusercontent.com/fitzlee/SampleSyncAdapter/master/document/pic_edit.jpg)