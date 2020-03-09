package com.example.escomputer.registrationandlogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class FetchingActivity extends AppCompatActivity {
    private Firebase fb;
    private TextView user,address;
    private Button btn_fetch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetching);
        Firebase.setAndroidContext(this);

        btn_fetch=(Button) findViewById(R.id.fetch_btn);
        user=(TextView) findViewById(R.id.name);
        address=(TextView) findViewById(R.id.address);
      fb=new Firebase("https://registrationandlogin-8c688.firebaseio.com/name");
          btn_fetch.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {


 fb.addValueEventListener(new ValueEventListener() {
     @Override
     public void onDataChange(DataSnapshot dataSnapshot) {
         String user_name=dataSnapshot.getValue(String.class);
         user.setText(user_name);
     }

     @Override
     public void onCancelled(FirebaseError firebaseError) {
         Toast.makeText(FetchingActivity.this, "eorr"+firebaseError.getMessage(), Toast.LENGTH_SHORT).show();
     }
 });
        Toast.makeText(FetchingActivity.this, "msla", Toast.LENGTH_SHORT).show();
    }
});
    }
}
