package com.easv.myfriends.myfriends;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.easv.myfriends.myfriends.model.Friend;
import java.util.Date;

public class DetailsActivity extends AppCompatActivity {

    //    <------------------- DECLARATIONS ------------------->

    private EditText nameEditText; // displaying/updating friend's name
    private EditText addressEditText; // displaying/updating friend's address
    private EditText phoneEditText; // displaying/updating friend's phoneNumber
    private EditText emailEditText; //  displaying/updating friend's email
    private EditText birthdayEditText; // displaying/updating friend's birthday
    private EditText websiteEditText; //  displaying/updating friend's website

    private Button takePictureButton; // Button for taking a picture
    private Button setHomeAddressLocationButton; // Button for setting friend's home location
    private Button showHomeAddressLocationButton; // Button for showing friend's home location on a map
    private Button callButton; // Button for calling
    private Button sendSmsButton; // Button for sending an sms
    private Button sendEmailButton; // Button for sending an email
    private Button setDateButton; // Button for  setting friend's birthday
    private Button saveProfileButton; // Button for saving/updating profile of a friend

    private String name; //  value of friend's name
    private String address;  // value of friend's address
    private Location homeAddressLocation; // value of friend's homeAddressLocation
    private String phoneNumber; // value of friend's phoneNumber
    private String email; // value of friend's email
    private String website; // value of friend's website
    private Date birthday; // value of friend's birthday
    private String picturePath; // value of friend's picturePath

    private Friend friend; // Instance of a friend

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //    <------------------- ASSIGNING EditTexts BY findViewById  ------------------->

        this.nameEditText = findViewById(R.id.friendDetailsNameEdit);
        this.addressEditText = findViewById(R.id.friendDetailsAddressEdit);
        this.phoneEditText = findViewById(R.id.friendDetailsPhoneEdit);
        this.emailEditText = findViewById(R.id.friendDetailsEmailEdit);
        this.birthdayEditText = findViewById(R.id.friendDetailsBirthdayEdit);
        this.websiteEditText = findViewById(R.id.friendDetailsWebsiteEdit);
    }

    //    <------------------- onClick METHODS FOR BUTTONS  ------------------->

    // taking a new picture/updating previous one
    public void takePicture(View v) {}

    // setting homeAddressLoccation
    public void setHomeAddressLocation(View v){}

    // showing homeAddressLocation on a map
    public void showHomeAddressLocation(View v){}

    // calling a friend's number with built-in application
    public void call(View v){}

    // sending an sms with built-in application
    public void sendSms(View v) {}

    // sending an email with default mailbox application
    public void sendEmail(View v) {}

    // setting date of friend's birthday
    public void setDate(View v) {}

    // saving/updating friend's profile
    public void saveProfile(View v) {}

    //    <------------------- CRUD FUNCTIONALITY FOR Friend's profile ------------------->

    // CREATE
    // no excluded method for Create since it always takes place by updating previously created one

    // READ
    // Getting values of a friend's profile from (array) and displaying them
    public void readProfile(){} // finding friend by id/number ?

    // UPDAtE
    // Assigning new values to friend's profile from EditText fields
    public void updateProfile(Friend frienToUpdate){}

    // DELETE
    // Deleting friend's profile with all it's values
    public void deleteProfile(Friend friendToDelete){}
}
