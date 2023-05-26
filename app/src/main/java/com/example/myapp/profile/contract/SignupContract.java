package com.example.myapp.profile.contract;

public interface SignupContract {
    interface View{
        void onSignupSuccess();
        void onSignupFailure();
        void showLoading(String title,String msg);
    }

    interface Presenter{
        void onSignup(String email, String password);
        void setView(SignupContract.View view);
        void dropView();
    }
}

//MVP -> Model View Presenter

