package com.example.escomputer.registrationandlogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {
    private Button registration_button;
    private EditText email,pass,register_name,register_address,register_phone;
    private TextView sign_in;
    private ProgressDialog progressdialogue;
    private FirebaseAuth fba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        fba=FirebaseAuth.getInstance();
        if(fba.getCurrentUser()!=null){

            Intent i=new Intent(RegistrationActivity.this,ProfileActivity.class);
            startActivity(i);
            finish();
        }

        progressdialogue=new ProgressDialog(this);
        registration_button=(Button) findViewById(R.id.register_btn);
        email=(EditText) findViewById(R.id.email);
        pass=(EditText) findViewById(R.id.password);
        register_address=(EditText) findViewById(R.id.address);
        register_name=(EditText) findViewById(R.id.fullname);
        register_phone=(EditText) findViewById(R.id.phonenumber);

        sign_in=(TextView) findViewById(R.id.signin);

        registration_button.setOnClickListener(this);
        sign_in.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if(view==registration_button){
            registerUser();

        }
        if(view==sign_in){
            Intent i=new Intent(RegistrationActivity.this,LoginActivity.class);
            startActivity(i);
            finish();

        }

    }
    public void registerUser(){
        String user_email=email.getText().toString();
        String user_password=pass.getText().toString();
        String user_name=register_name.getText().toString();
        String user_phone=register_phone.getText().toString();
        String user_address=register_address.getText().toString();

        if(TextUtils.isEmpty(user_email)){
            Toast.makeText(this, "Please Complete the required fields", Toast.LENGTH_SHORT).show();
            return;

        }
       else if(TextUtils.isEmpty(user_password)){
            Toast.makeText(this, "Please Complete the required fields", Toast.LENGTH_SHORT).show();
            return;

        }
       else if(TextUtils.isEmpty(user_name)){

        Toast.makeText(this, "Please Complete the required fields", Toast.LENGTH_SHORT).show();
        return;
        }
       else if(TextUtils.isEmpty(user_address)){

            Toast.makeText(this, "Please Complete the required fields", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(user_phone)){

            Toast.makeText(this, "Please Complete the required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        progressdialogue.setMessage("Registereing User...");
        progressdialogue.show();


        fba.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                 if(task.isSuccessful()){

                         Intent i=new Intent(RegistrationActivity.this,ProfileActivity.class);
                         startActivity(i);
                         finish();

                     Toast.makeText(RegistrationActivity.this, "Succesfully Registered", Toast.LENGTH_SHORT).show();
                     progressdialogue.dismiss();

                 }
                 else{

                     FirebaseAuthException e = (FirebaseAuthException)task.getException();
                     Toast.makeText(RegistrationActivity.this, "Failed Registration: "+" "+e.getMessage(), Toast.LENGTH_LONG).show();
                     progressdialogue.dismiss();
                     return;
                 }


                }});

    }
}
