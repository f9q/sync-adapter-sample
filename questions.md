1. contact synchronisation
Situation: 
Now I have implemented the server-to-app synchronisation with the modified SampleSyncAdapter, and this demo can download contacts from server to local sqlite3 database.  Also I can edit and modify the local contact and from logcat I have seen the dirty-contacts being sended to the server. But the synchronisation of server() can't work so that I can't test this funciton.

Prolem:
Moreover, By analysing the XWiki android-authenticator in the repository, I have seen that some function has not been implemented, like getDirtyContacts and getRawContact in ContactManager. And the process of synchronisation in SynAdapter.onPerformSync also confuse me, like ensureXWikiGroupExists->XWikiConnector.getUsers->ContactManager.updateContacts->ContactManager.updateStatusMessages-end. I think it should be this process, like ContactManager.getDirtyContacts->NetworkUtilities.syncContacts->ContactManager.updateContacts. Therefore,I don't know whether there is something wrong with me. Could you give me some advice? 


2. group
I don't know what xwiki group means. so I can't develop this group function. could you tell me more details about the group? 

3. a test xwiki server and test user which may help me a lot to develop this project for testing.
I have analysed the sampleSynAdapter and implemented some function, for example material design(>=4.4), version support(2.3 with v7 library) and the synchronisation from server to the android local sqlite3 database(contacts2.db) using ContactManager. 

But when client sends some dirty contact data to server, the synchronisation of server can't work well so that I can't test my app's synchronisation. And the SampleSyncAdapter server also can't provide me more apis, like signing up and other necessary function. I have tried to upload a python server to google appengine, but failed because of the incompatibility between the source code by python version 2.5 and the cloud platform by 2.7. 

Please if possible, could I have test server apis and user in xwiki server? or maybe i should write a test server by myself? 

4. requirement
sign in(it is easy, just like my analysis of android SampleSyncAdapter, including the server connection with XWikiconnector and the account added by AccountManager)
sign up(may also need some other api, like getting a list of xwiki user, adding friends which can be synchronized in the local phone contacts. Also other activitie may be possibly added, therefor the authenticator app will be very complex)
synchronisation of contacts()
edit the contact()
access by other apps()

Is there more other requirements in this app, like adding friends(general person) and creating new xwiki account(administrator)?  maybe it will cause more demands and be more complex.  


5. support version and ui
ui design 
* material design >=4.4 with the support library support-v7

support version
* The ui design can support the lowest 2.3 version and the android sampleSynAdapter can also support 2.3 version. So I think our authenticator app can support the lowest 2.3 version if needed.





