// Generated by view binder compiler. Do not edit!
package com.example.appsiniestralidadkotlin.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.appsiniestralidadkotlin.R;
import com.facebook.login.widget.LoginButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityLoginBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnLogGoogle;

  @NonNull
  public final Button btnLogIni;

  @NonNull
  public final ImageView imageView;

  @NonNull
  public final LoginButton loginButtonFacebook;

  @NonNull
  public final EditText loginEmail;

  @NonNull
  public final EditText loginPassword;

  @NonNull
  public final TextView textViewOlvidar;

  @NonNull
  public final TextView textViewRegistrarse;

  private ActivityLoginBinding(@NonNull ConstraintLayout rootView, @NonNull Button btnLogGoogle,
      @NonNull Button btnLogIni, @NonNull ImageView imageView,
      @NonNull LoginButton loginButtonFacebook, @NonNull EditText loginEmail,
      @NonNull EditText loginPassword, @NonNull TextView textViewOlvidar,
      @NonNull TextView textViewRegistrarse) {
    this.rootView = rootView;
    this.btnLogGoogle = btnLogGoogle;
    this.btnLogIni = btnLogIni;
    this.imageView = imageView;
    this.loginButtonFacebook = loginButtonFacebook;
    this.loginEmail = loginEmail;
    this.loginPassword = loginPassword;
    this.textViewOlvidar = textViewOlvidar;
    this.textViewRegistrarse = textViewRegistrarse;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_login, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityLoginBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_log_google;
      Button btnLogGoogle = ViewBindings.findChildViewById(rootView, id);
      if (btnLogGoogle == null) {
        break missingId;
      }

      id = R.id.btn_log_ini;
      Button btnLogIni = ViewBindings.findChildViewById(rootView, id);
      if (btnLogIni == null) {
        break missingId;
      }

      id = R.id.imageView;
      ImageView imageView = ViewBindings.findChildViewById(rootView, id);
      if (imageView == null) {
        break missingId;
      }

      id = R.id.login_button_facebook;
      LoginButton loginButtonFacebook = ViewBindings.findChildViewById(rootView, id);
      if (loginButtonFacebook == null) {
        break missingId;
      }

      id = R.id.login_email;
      EditText loginEmail = ViewBindings.findChildViewById(rootView, id);
      if (loginEmail == null) {
        break missingId;
      }

      id = R.id.login_password;
      EditText loginPassword = ViewBindings.findChildViewById(rootView, id);
      if (loginPassword == null) {
        break missingId;
      }

      id = R.id.textView_olvidar;
      TextView textViewOlvidar = ViewBindings.findChildViewById(rootView, id);
      if (textViewOlvidar == null) {
        break missingId;
      }

      id = R.id.textView_registrarse;
      TextView textViewRegistrarse = ViewBindings.findChildViewById(rootView, id);
      if (textViewRegistrarse == null) {
        break missingId;
      }

      return new ActivityLoginBinding((ConstraintLayout) rootView, btnLogGoogle, btnLogIni,
          imageView, loginButtonFacebook, loginEmail, loginPassword, textViewOlvidar,
          textViewRegistrarse);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
