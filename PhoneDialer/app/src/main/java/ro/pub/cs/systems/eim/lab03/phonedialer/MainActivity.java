package ro.pub.cs.systems.eim.lab03.phonedialer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.telephony.AccessNetworkConstants;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.security.Permission;
import java.security.Permissions;

public class MainActivity extends AppCompatActivity {
    private final int PERMISSION_REQUEST_CALL_PHONE = 5;
    class DigitButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            EditText text = findViewById(R.id.editPhoneNo);
            Button button = (Button) v;
            text.getText().append(button.getText());

            //if (ContextCompat.checkSelfPermission(PhoneDialerActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            //  ActivityCompat.requestPermissions(
            //    PhoneDialerActivity.this,
            //    new String[]{Manifest.permission.CALL_PHONE},
            //    Constants.PERMISSION_REQUEST_CALL_PHONE);
            //} else {
            //  Intent intent = new Intent(Intent.ACTION_CALL);
            //  intent.setData(Uri.parse("tel:" + phoneNumberEditText.getText().toString()));
            //  startActivity(intent);
            //}
        }
    }

    class CallButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            EditText phoneNumberEditText = findViewById(R.id.editPhoneNo);

            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
              ActivityCompat.requestPermissions(
                MainActivity.this,
                new String[]{Manifest.permission.CALL_PHONE},
                      PERMISSION_REQUEST_CALL_PHONE);
            } else {
              Intent intent = new Intent(Intent.ACTION_CALL);
              intent.setData(Uri.parse("tel:" + phoneNumberEditText.getText().toString()));
              startActivity(intent);
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_dialer);

        Button button = findViewById(R.id.butdigit);
        button.setOnClickListener(new DigitButtonListener());

        ImageButton callButton = findViewById(R.id.call);
        callButton.setOnClickListener(new CallButtonListener());
    }
}