package com.example.escomputer.registrationandlogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{
    private Button logot_btn,btn_next;
    private FirebaseAuth fba;
    private TextView text;
    private DatabaseReference dbr;
    private EditText user_name,user_address;
    private Button save_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
 //database
        dbr= FirebaseDatabase.getInstance().getReference();
        save_button=(Button) findViewById(R.id.save_btn);
        user_address=(EditText) findViewById(R.id.address);
        user_name=(EditText) findViewById(R.id.full_name);
        save_button.setOnClickListener(this);

//firebase
        logot_btn=(Button) findViewById(R.id.logout);
        logot_btn.setOnClickListener(this);
        btn_next=(Button) findViewById(R.id.next);
        btn_next.setOnClickListener(this);
        fba=FirebaseAuth.getInstance();
        text=(TextView) findViewById(R.id.textView);

        if(fba.getCurrentUser()==null){
            Intent i=new Intent(ProfileActivity.this,LoginActivity.class);
            startActivity(i);
            finish();
        }

        FirebaseUser fbu=fba.getCurrentUser();
        text.setText("Welcome"+" " + fbu.getEmail());
    }

    @Override
    public void onClick(View view) {
        if(view==logot_btn){
            fba.signOut();
            Intent i=new Intent(ProfileActivity.this,LoginActivity.class);
            startActivity(i);
            finish();
        }
        if(view==save_button){
            saveInformation();
        }
        if(view==btn_next){
            Intent i=new Intent(ProfileActivity.this,FetchingActivity.class);
            startActivity(i);
            finish();
        }
    }
    public void saveInformation(){
        String full_name=user_name.getText().toString();
        String full_address= user_address.getText().toString();
        UserInformation userinformation=  new UserInformation(full_name,full_address);

        FirebaseUser user=fba.getCurrentUser();
        dbr.child(user.getUid()).setValue(userinformation);
        Toast.makeText(this, "Information Saved", Toast.LENGTH_SHORT).show();


    }
}
