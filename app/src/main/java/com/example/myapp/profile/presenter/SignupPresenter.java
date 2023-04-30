package com.example.myapp.profile.presenter;

import androidx.annotation.NonNull;

import com.example.myapp.profile.contract.SignupContract;
import com.example.myapp.profile.view.Signup;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupPresenter implements SignupContract.Presenter {

    private SignupContract.View view;

    private final FirebaseAuth firebaseAuth;

    public SignupPresenter(){
        firebaseAuth = FirebaseAuth.getInstance();

    }


    @Override
    public void onSignup(String email, String password) {
        //api call to firebase
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        view.onSignupSuccess();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        view.onSignupFailure();
                    }
                });
    }

    @Override
    public void setView(SignupContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        this.view = null;
    }


}
