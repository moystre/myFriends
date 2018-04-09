package com.easv.myfriends.myfriends;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.easv.myfriends.myfriends.model.Friend;
import com.easv.myfriends.myfriends.service.FriendsService;
import com.easv.myfriends.myfriends.service.MessageService;

import java.util.Date;

public class DetailsActivity extends AppCompatActivity {

    //    <------------------- DECLARATIONS ------------------->

    private static final String TAG = "MapActivity";    //Activity's TAG used for Log

    FriendsService friendsService;
    MessageService messageService;
    Friend selectedFriend;
    Integer selectedFriendId;
    Boolean isNewFriend;
    Location currentLocation;
    double currentLocationLongitude;
    double currentLocationLatitude;

    private ImageView profilePicture;
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
        friendsService = new FriendsService(this);

        //    <------------------- RETREIVING EXTRAS  ------------------->

        selectedFriendId = getIntent().getIntExtra("friendId", 0);
        isNewFriend = getIntent().getBooleanExtra("isNewFriend", false);
        //currentLocationLongitude = getIntent().getDoubleExtra("currentLocationLatitude", 0);
        //currentLocationLatitude = getIntent().getDoubleExtra("currentLocationLongitude", 0);


        //    <------------------- CREATE AND POPULATE FRIEND OBJECT  ------------------->
        if(!isNewFriend) {
             selectedFriend = friendsService.get(selectedFriendId);
        }
        else {
            selectedFriend = new Friend();
        }

        //    <------------------- FINDING EditTexts BY findViewById  ------------------->

        this.profilePicture = findViewById(R.id.friendDetailsPictureEdit);
        this.nameEditText = findViewById(R.id.friendDetailsNameEdit);
        this.addressEditText = findViewById(R.id.friendDetailsAddressEdit);
        this.phoneEditText = findViewById(R.id.friendDetailsPhoneEdit);
        this.emailEditText = findViewById(R.id.friendDetailsEmailEdit);
        this.birthdayEditText = findViewById(R.id.friendDetailsBirthdayEdit);
        this.websiteEditText = findViewById(R.id.friendDetailsWebsiteEdit);

        //    <------------------- ASSIGNING TextViews values  ------------------->

        if(selectedFriend.getmPictureString()!=null && selectedFriend.getmPictureString()!="") {

            this.profilePicture.setImageBitmap(BitmapFactory.decodeFile(selectedFriend.getmPictureString()));
        }
        Log.d("XXXXXXXXXXXXXXXXXXXX", selectedFriend.getmPictureString());
        this.nameEditText.setText(selectedFriend.getmName());
        this.addressEditText.setText(selectedFriend.getmAddress());
        this.phoneEditText.setText(selectedFriend.getmPhoneNumber());
        this.emailEditText.setText(selectedFriend.getmEmail());
        this.birthdayEditText.setText(selectedFriend.getmBirthdayStringDAYMONTH());
        this.websiteEditText.setText(Html.fromHtml(selectedFriend.getmWebsite()));
        this.websiteEditText.setMovementMethod(LinkMovementMethod.getInstance());

        //    <------------------- ASSIGNING ImageView picture  ------------------->

        this.nameEditText.setText(selectedFriend.getmName());
    }
    //    <------------------- onClick METHODS FOR BUTTONS  ------------------->

    // taking a new picture/updating previous one
    public void takePicture(View v) {
        Intent i = new Intent(getApplicationContext(), CameraActivity.class);
        startActivityForResult(i, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                String resultPath = data.getStringExtra("imagePath");
                selectedFriend.setmPicturePath(resultPath);
                this.profilePicture.setImageBitmap(BitmapFactory.decodeFile(selectedFriend.getmPictureString()));
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result todo
            }
        }
    }
    // setting homeAddressLoccation
    public void setHomeAddressLocation(View v){
       /* Log.d(TAG, "Home location set to: " + currentLocation);
        currentLocation.setLatitude(currentLocationLatitude);
        currentLocation.setLongitude(currentLocationLongitude);
        selectedFriend.setmLocation(currentLocation);
        messageService.message(getApplicationContext(), "New address set");*/
    }

    // showing homeAddressLocation on a map
    public void showHomeAddressLocation(View v){}

    // calling a friend's number with built-in application
    public void call(View v){
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:"+selectedFriend.getmPhoneNumber()));
        startActivity(callIntent);
    }

    // sending an sms with built-in application
    public void sendSms(View v) {
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address", selectedFriend.getmPhoneNumber());
        smsIntent.putExtra("sms_body","");
        startActivity(smsIntent);
    }

    // sending an email with default mailbox application
    public void sendEmail(View v) {
        Uri uri = Uri.parse("mailto:"+selectedFriend.getmEmail());
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, uri);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,"");
        startActivity(emailIntent);
    }

    // setting date of friend's birthday
    public void setDate(View v) {}

    // saving/updating friend's profile
    public void saveProfile(View v) {
        if(!isNewFriend) {
            selectedFriend.setmName(nameEditText.getText().toString().trim());
            selectedFriend.setmAddress(addressEditText.getText().toString().trim());
            //todo set location
            selectedFriend.setmPhoneNumber(phoneEditText.getText().toString().trim());
            selectedFriend.setmEmail(emailEditText.getText().toString().trim());
            //todo set birthday
            selectedFriend.setmWebsite(websiteEditText.getText().toString().trim());
            friendsService.update(selectedFriend);
        }
        else {
            selectedFriend.setmName(nameEditText.getText().toString().trim());
            selectedFriend.setmAddress(addressEditText.getText().toString().trim());
            //todo set location
            selectedFriend.setmPhoneNumber(phoneEditText.getText().toString().trim());
            selectedFriend.setmEmail(emailEditText.getText().toString().trim());
            //todo set birthday
            selectedFriend.setmWebsite(websiteEditText.getText().toString().trim());
            // todo defaultpic
            friendsService.addFriend(selectedFriend);
        }
    }

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
