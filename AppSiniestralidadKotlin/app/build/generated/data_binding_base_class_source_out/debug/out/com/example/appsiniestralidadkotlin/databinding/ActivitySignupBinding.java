// Generated by view binder compiler. Do not edit!
package com.example.appsiniestralidadkotlin.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.appsiniestralidadkotlin.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySignupBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnRegistrarse;

  @NonNull
  public final ImageView imageView;

  @NonNull
  public final EditText registerBirthday;

  @NonNull
  public final EditText registrarPassword;

  @NonNull
  public final ScrollView scrollView2;

  @NonNull
  public final TextView textView4;

  @NonNull
  public final TextView textView5;

  private ActivitySignupBinding(@NonNull ConstraintLayout rootView, @NonNull Button btnRegistrarse,
      @NonNull ImageView imageView, @NonNull EditText registerBirthday,
      @NonNull EditText registrarPassword, @NonNull ScrollView scrollView2,
      @NonNull TextView textView4, @NonNull TextView textView5) {
    this.rootView = rootView;
    this.btnRegistrarse = btnRegistrarse;
    this.imageView = imageView;
    this.registerBirthday = registerBirthday;
    this.registrarPassword = registrarPassword;
    this.scrollView2 = scrollView2;
    this.textView4 = textView4;
    this.textView5 = textView5;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySignupBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySignupBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_signup, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySignupBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_registrarse;
      Button btnRegistrarse = ViewBindings.findChildViewById(rootView, id);
      if (btnRegistrarse == null) {
        break missingId;
      }

      id = R.id.imageView;
      ImageView imageView = ViewBindings.findChildViewById(rootView, id);
      if (imageView == null) {
        break missingId;
      }

      id = R.id.register_birthday;
      EditText registerBirthday = ViewBindings.findChildViewById(rootView, id);
      if (registerBirthday == null) {
        break missingId;
      }

      id = R.id.registrar_password;
      EditText registrarPassword = ViewBindings.findChildViewById(rootView, id);
      if (registrarPassword == null) {
        break missingId;
      }

      id = R.id.scrollView2;
      ScrollView scrollView2 = ViewBindings.findChildViewById(rootView, id);
      if (scrollView2 == null) {
        break missingId;
      }

      id = R.id.textView4;
      TextView textView4 = ViewBindings.findChildViewById(rootView, id);
      if (textView4 == null) {
        break missingId;
      }

      id = R.id.textView5;
      TextView textView5 = ViewBindings.findChildViewById(rootView, id);
      if (textView5 == null) {
        break missingId;
      }

      return new ActivitySignupBinding((ConstraintLayout) rootView, btnRegistrarse, imageView,
          registerBirthday, registrarPassword, scrollView2, textView4, textView5);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
