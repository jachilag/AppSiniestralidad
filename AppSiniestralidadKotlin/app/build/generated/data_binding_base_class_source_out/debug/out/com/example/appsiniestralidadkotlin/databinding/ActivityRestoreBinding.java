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
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityRestoreBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnRecuperar;

  @NonNull
  public final ImageView imageView;

  @NonNull
  public final EditText textCorreoRecuperacion;

  @NonNull
  public final TextView textView4;

  private ActivityRestoreBinding(@NonNull ConstraintLayout rootView, @NonNull Button btnRecuperar,
      @NonNull ImageView imageView, @NonNull EditText textCorreoRecuperacion,
      @NonNull TextView textView4) {
    this.rootView = rootView;
    this.btnRecuperar = btnRecuperar;
    this.imageView = imageView;
    this.textCorreoRecuperacion = textCorreoRecuperacion;
    this.textView4 = textView4;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityRestoreBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityRestoreBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_restore, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityRestoreBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_recuperar;
      Button btnRecuperar = ViewBindings.findChildViewById(rootView, id);
      if (btnRecuperar == null) {
        break missingId;
      }

      id = R.id.imageView;
      ImageView imageView = ViewBindings.findChildViewById(rootView, id);
      if (imageView == null) {
        break missingId;
      }

      id = R.id.text_correo_recuperacion;
      EditText textCorreoRecuperacion = ViewBindings.findChildViewById(rootView, id);
      if (textCorreoRecuperacion == null) {
        break missingId;
      }

      id = R.id.textView4;
      TextView textView4 = ViewBindings.findChildViewById(rootView, id);
      if (textView4 == null) {
        break missingId;
      }

      return new ActivityRestoreBinding((ConstraintLayout) rootView, btnRecuperar, imageView,
          textCorreoRecuperacion, textView4);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
