package com.example.travel_app_feed.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.travel_app_feed.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
        EditText emailText;
        EditText yeniSifreText;
        private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        emailText = findViewById(R.id.emailText);
        yeniSifreText = findViewById(R.id.yeniSifreText);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void kayitOl (View view) {

        String yeniemail = emailText.getText().toString();
        String yenisifre = yeniSifreText.getText().toString();

        if (yeniemail.matches("") || yenisifre.matches("")) {

            Toast.makeText(getApplicationContext(),"Lütfen email veya şifre giriniz",Toast.LENGTH_LONG).show();
        }else {

            firebaseAuth.createUserWithEmailAndPassword(yeniemail,yenisifre).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Toast.makeText(getApplicationContext(),"Kullanıcı oluşturuldu",Toast.LENGTH_LONG).show();
                    // Ya Giriş kısmına intent ya da ana ekrana intent
                    Intent intent = new Intent(SignUpActivity.this, SearchActivity.class);
                    startActivity(intent);
                    // intent yaptıktan sonra  'finish();'  ekle.
                    // finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }
            });
        }

    }
}