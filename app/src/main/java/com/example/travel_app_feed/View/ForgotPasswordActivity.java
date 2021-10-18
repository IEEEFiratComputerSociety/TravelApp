package com.example.travel_app_feed.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.travel_app_feed.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {
        private EditText sifre_degisim_email;
        private Button sifre_sıfırla;
        private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        sifre_degisim_email = findViewById(R.id.sifre_degisim_email);
        sifre_sıfırla = findViewById(R.id.sifre_sıfırla);
        firebaseAuth = FirebaseAuth.getInstance();

        sifre_sıfırla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail = sifre_degisim_email.getText().toString().trim();
                if (useremail.matches("")){
                    Toast.makeText(ForgotPasswordActivity.this,"Lütfen Email Giriniz",Toast.LENGTH_LONG).show();

                }else {
                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                    Toast.makeText(ForgotPasswordActivity.this,"Şifre Sıfırlama Epostası Gönderildi !",Toast.LENGTH_LONG).show();
                                    finish();
                               /* Intent intent = new Intent(ForgotPasswordActivity.this,LogInActivity.class);
                                startActivity(intent);*/
                            }else {
                                Toast.makeText(ForgotPasswordActivity.this, "Hata Oluştu !!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });

    }
}