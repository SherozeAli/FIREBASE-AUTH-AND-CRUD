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

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button login_button;
    private EditText email,pass;
    private TextView sign_up;
    private ProgressDialog progressdialogue;
    private FirebaseAuth fba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fba=FirebaseAuth.getInstance();
        if(fba.getCurrentUser()!=null){

            Intent i=new Intent(LoginActivity.this,ProfileActivity.class);
            startActivity(i);
            finish();
        }

        progressdialogue=new ProgressDialog(this);
        login_button=(Button) findViewById(R.id.login_btn);
        email=(EditText) findViewById(R.id.login_email);
        pass=(EditText) findViewById(R.id.login_password);
        sign_up=(TextView) findViewById(R.id.signup);

        login_button.setOnClickListener(this);
        sign_up.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if(view==login_button){
            loginUser();

        }
        if(view==sign_up){
            Intent i=new Intent(LoginActivity.this,RegistrationActivity.class);
            startActivity(i);
            finish();

        }

    }
private void loginUser(){

        String email_user=email.getText().toString();
        String pass_user=pass.getText().toString();

        if(TextUtils.isEmpty(email_user)){
            Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show();
        }
    if(TextUtils.isEmpty(pass_user)){
        Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show();
    }
    progressdialogue.setMessage("LogIn User...");
    progressdialogue.show();

    fba.signInWithEmailAndPassword(email_user,pass_user).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
           if(task.isSuccessful()){
               Intent i=new Intent(LoginActivity.this,ProfileActivity.class);
               startActivity(i);
               finish();

           }



        }});



}
}
