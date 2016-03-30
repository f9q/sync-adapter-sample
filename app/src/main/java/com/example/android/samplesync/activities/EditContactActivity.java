package com.example.android.samplesync.activities;

import android.accounts.AccountManager;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import com.example.android.samplesync.R;
import com.example.android.samplesync.client.RawContact;
import com.example.android.samplesync.platform.BatchOperation;
import com.example.android.samplesync.platform.ContactManager;


/**
 * A edit contact screen..
 */
public class EditContactActivity extends AppCompatActivity{

    // UI references.
    private EditText mFullNameView;
    private EditText mEmailView;
    private EditText mCellPhoneView;
    private EditText mWorkPhoneView;

    private TextView mContactInfoTextView;
    private Uri mUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_edit_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        StatusBarCompat.compat(this, Color.parseColor("#0077D9"));

        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.email);
        mFullNameView = (EditText) findViewById(R.id.fullname);
        mCellPhoneView = (EditText) findViewById(R.id.cellphone);
        mWorkPhoneView = (EditText) findViewById(R.id.workphone);

        mUri = getIntent().getData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }else if(item.getItemId()==R.id.action_save){
            boolean flag=save();
            if(flag==true) {
                finish();
            }else{
                Toast.makeText(EditContactActivity.this,"Please Check Firstly",Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean save(){
        updataCotact(mUri);
        return true;
    }


    public void updataCotact(Uri uri) {
        long  rawContactId = 0 ;
        ContentResolver cr = getContentResolver();
        Cursor people = cr.query(uri, new String[]{ContactsContract.Contacts.Data.RAW_CONTACT_ID}, null, null, null);
        if(people.getCount()>0){
            while (people.moveToNext()) {
                rawContactId = people.getLong(people.getColumnIndex(ContactsContract.Data.RAW_CONTACT_ID));
            }
        }

        Log.i("rawContactId", rawContactId+"");
        RawContact rawContact = ContactManager.getRawContact(getApplicationContext(), rawContactId);
        rawContact.mFirstName = mFullNameView.getText().toString();
        rawContact.mFullName = mFullNameView.getText().toString();
        rawContact.mCellPhone = mCellPhoneView.getText().toString();
        rawContact.mEmail = mEmailView.getText().toString();
        Log.i("edit",rawContact.toString());
        ContactManager.updateContactLocal(this, getContentResolver(), rawContact, rawContactId);
    }

    /**
     * Attempts to check input TODO check server permission or save priority in local preference
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no server checking is made.
     */
    private void checkInputAndPermission() {

        // Reset errors.
        mEmailView.setError(null);
        mCellPhoneView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mCellPhoneView.getText().toString();
        String cellphone = mCellPhoneView.getText().toString();
        String workphone = mWorkPhoneView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPhoneValid(password)) {
            mCellPhoneView.setError(getString(R.string.error_invalid_password));
            focusView = mCellPhoneView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.

        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isNameValid(String name){
        return false;
    }

    private boolean isPhoneValid(String phone){
        return false;
    }

}

