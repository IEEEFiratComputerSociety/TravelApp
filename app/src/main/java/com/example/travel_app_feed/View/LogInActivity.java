package com.example.travel_app_feed.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.travel_app_feed.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogInActivity extends AppCompatActivity {

        TextView kullaniciAdiTextView, sifreTextView;
        EditText kullaniciAdiText, sifreText;

       // private CallbackManager callbackManager;
        private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        kullaniciAdiTextView = findViewById(R.id.kullaniciAdiTextView);
        kullaniciAdiText = findViewById(R.id.kullaniciAdiText);

        sifreTextView = findViewById(R.id.sifreTextView);
        sifreText = findViewById(R.id.sifreText);

         FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser != null) {
            // ana ekrana intent yapılacak.
            Intent intent = new Intent(LogInActivity.this, SearchActivity.class);
            startActivity(intent);

            // intent yaptıktan sonra  'finish();'  ekle.
            //  finish();
        }


    }

        /*private  void  handleFacebookToken (AccessToken token) {
        Log.d(TAG,"handleFacebookToken" + token);

            AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
            mFirebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Log.d(TAG,"sign in with credential: successful");
                        FirebaseUser user = mFirebaseAuth.getCurrentUser();
                    }else {
                        Log.d(TAG,"sign in with credential: failure",task.getException());
                        Toast.makeText(LogInActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        mCallbackManager.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }*/
   /* private void updateUI (FirebaseUser user){
        if (user != null ) {
            textViewUser.setText(user.getDisplayName());
            if (user.getPhotoUrl() != null){
                String photoUrl = user.getPhotoUrl().toString();
                photoUrl = photoUrl + "?type=large";
                Picasso.get().load(photoUrl).into(mLogo);
            }
        }
        else {
            textViewUser.setText("");
            mLogo.setImageResource(R.drawable.wanderlust_logo );
        }
    }*/

    public void Google (View view) {

    }

    public void hesapOlustur (View view) {
       Intent intent = new Intent(LogInActivity.this, SignUpActivity.class);
       startActivity(intent);
    }
    public void Facebook (View view) {
        /*Intent intent = new Intent(LogInActivity.this,AnaEkranActivity.class);
        startActivity(intent);*/
    }
    public void unuttum (View view) {

        Intent intent = new Intent(LogInActivity.this, ForgotPasswordActivity.class);
        startActivity(intent);

    }

   /* @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (authStateListener != null){
            mFirebaseAuth.removeAuthStateListener(authStateListener);
        }

    }*/
   public void Giris (View view) {
       String email = kullaniciAdiText.getText().toString();
       String sifre = sifreText.getText().toString();
       if (email.matches("") || sifre.matches("")) {
           Toast.makeText(getApplicationContext(),"Lütfen email veya şifre giriniz",Toast.LENGTH_LONG).show();
       }else {
           firebaseAuth.signInWithEmailAndPassword(email,sifre).addOnFailureListener(new OnFailureListener() {
               @Override
               public void onFailure(@NonNull Exception e) {
                   Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
               }
           }).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
               @Override
               public void onSuccess(AuthResult authResult) {
                   // ana ekrana intent yapılacak
                   Intent intent = new Intent(LogInActivity.this, SearchActivity.class);
                   startActivity(intent);
                   // intent yaptıktan sonra  'finish();'  ekle.
                   // finish();
               }
           });
       }
   }
}