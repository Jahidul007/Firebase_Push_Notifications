package com.jahid.firebasepushnotifications;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity {

    private static final int PICK_IMAGE = 1 ;
    private CircleImageView mImageView;
    private EditText mEmailField;
    private EditText mNameField;
    private EditText mPasswordField;
    private Button mRegBtn;
    private Button mLoginPageBtn;

    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        imageUri = null;
        mImageView = (CircleImageView) findViewById(R.id.register_image_btn);
        mEmailField = (EditText) findViewById(R.id.register_email);
        mPasswordField = (EditText) findViewById(R.id.register_password);
        mNameField = (EditText) findViewById(R.id.register_name);
        mRegBtn = (Button) findViewById(R.id.register_btn);
        mLoginPageBtn = (Button) findViewById(R.id.register_login_btn);

        mRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(imageUri != null){

                    String name = mNameField.getText().toString();
                    String email = mEmailField.getText().toString();
                    String password = mPasswordField.getText().toString();

                    if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){

                    }
                }

            }
        });

        mLoginPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 Intent intent = new Intent();
                 intent.setType("image/*");
                 intent.setAction(Intent.ACTION_GET_CONTENT);

                 startActivityForResult(Intent.createChooser(intent, "Select Picture"),PICK_IMAGE);

            }
        });
    }

    private void startLogin() {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE){

            imageUri = data.getData();
            mImageView.setImageURI(imageUri);
        }
    }
}
