package com.nyxwolves.wannabuy.Helpers;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.facebook.AccessToken;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.nyxwolves.wannabuy.R;
import com.nyxwolves.wannabuy.activities.HomeActivity;
import com.nyxwolves.wannabuy.activities.SplashScreen;

public class FirebaseHelper {

    private FirebaseAuth mauth;
    private Context ctx;
    private ProgressDialog dialog;
    private final String TAG = "FIREBASE_HELPER";

    public FirebaseHelper(Context ctx) {
        this.ctx = ctx;
        mauth = FirebaseAuth.getInstance();
    }

    private boolean checkInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network = connectivityManager.getActiveNetworkInfo();
        return (network != null && network.isConnectedOrConnecting());
    }

    public Intent signInGoogleUser() {

        GoogleSignInClient signInClient;
        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(ctx.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        signInClient = GoogleSignIn.getClient(ctx, options);
        signInClient.signOut();
        return signInClient.getSignInIntent();
    }

    public void checkAuthentication(Intent data, View v) {
        Log.d("LOGIN", "ON ACTIVITY");
        if (checkInternet()) {
            showDialog("Logging in...");
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                userAuth(account, v);
            } catch (ApiException e) {
                closeDialog();
            }
        } else {
            showSnackBar("Device offline", v);
        }


    }

    private void userAuth(GoogleSignInAccount account, final View v) {

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        Log.d("HELPER", "REACHED_USERAUTH");
        mauth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    closeDialog();
                    showSnackBar("Welcome", v);
                    Intent i = new Intent(ctx, HomeActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    ctx.startActivity(i);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                closeDialog();
                showSnackBar("Error occured", v);
            }
        });
    }

    public void facebookLogin(AccessToken token, final View v) {
        showDialog("Logging in...");
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());

        mauth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    closeDialog();
                    showSnackBar("Welcome", v);
                    ctx.startActivity(new Intent(ctx, HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e.getLocalizedMessage().equals("The email address is already in use by another account.")) {
                    showSnackBar("User already exists", v);
                }
                closeDialog();
            }
        });
    }

    public void registerUser(String name, String email, String password, final ConstraintLayout view) {

        showDialog("Registering user...");
        mauth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                closeDialog();
                ctx.startActivity(new Intent(ctx, HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                closeDialog();
                Log.d(TAG,e.getLocalizedMessage());
                if (e.getLocalizedMessage().equals(ctx.getString(R.string.user_already_registered))) {
                    showSnackBar("User already registered", view);
                }
            }
        });
    }

    public void loginUser(String email, String password, final View view) {
        showDialog("Logging in...");
        mauth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                closeDialog();
                if (FirebaseAuth.getInstance().getCurrentUser() != null){
                    ctx.startActivity(new Intent(ctx, HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                }else{
                    showSnackBar("Invalid Credentials", view);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                closeDialog();
                showSnackBar("Error Occurred", view);
            }
        });
    }


    public void changePassword(String oldPass, final String newPass, final View view) {
        showDialog("Authenticating");
        final FirebaseUser user;
        user = FirebaseAuth.getInstance().getCurrentUser();
        AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), oldPass);

        user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    user.updatePassword(newPass).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            closeDialog();
                            if (!task.isSuccessful()) {
                                showSnackBar("Something went wrong. Please try again later", view);
                            } else {
                                showSnackBar("Password Successfully Modified", view);
                                ctx.startActivity(new Intent(ctx, HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                            }
                        }
                    });
                } else {
                    closeDialog();
                    showSnackBar("Authentication Failed", view);
                }
            }
        });
    }

    public void deleteUser(final View view) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    showSnackBar("User Account Deleted", view);
                    ctx.startActivity(new Intent(ctx, SplashScreen.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                } else
                    showSnackBar("Please logout and login once and try again", view);
            }
        });
    }

    private void showDialog(String msg) {
        dialog = new ProgressDialog(ctx);
        dialog.setCancelable(false);
        dialog.setMessage(msg);
        dialog.setIndeterminate(true);
        dialog.create();
        dialog.show();
    }

    private void closeDialog() {
        dialog.dismiss();
    }

    private void showSnackBar(String msg, View v) {
        Snackbar.make(v, msg, Snackbar.LENGTH_SHORT).show();
    }


}
