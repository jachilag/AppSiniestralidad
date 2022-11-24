// Generated by view binder compiler. Do not edit!
package com.example.appsiniestralidadkotlin.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.appsiniestralidadkotlin.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentPerfilBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button btnActualizar;

  @NonNull
  public final Button btnTomarFoto;

  @NonNull
  public final ImageView imgPerfil1;

  @NonNull
  public final EditText registerBirthday;

  @NonNull
  public final EditText registrarCelular;

  @NonNull
  public final EditText registroApellido;

  @NonNull
  public final EditText registroCiudad;

  @NonNull
  public final EditText registroCorreo;

  @NonNull
  public final EditText registroNombre;

  @NonNull
  public final Spinner spinnerCiudad;

  @NonNull
  public final TextView textView;

  @NonNull
  public final TextView textView0;

  @NonNull
  public final TextView textView2;

  @NonNull
  public final TextView textView3;

  @NonNull
  public final TextView textView4;

  @NonNull
  public final TextView textView5;

  private FragmentPerfilBinding(@NonNull LinearLayout rootView, @NonNull Button btnActualizar,
      @NonNull Button btnTomarFoto, @NonNull ImageView imgPerfil1,
      @NonNull EditText registerBirthday, @NonNull EditText registrarCelular,
      @NonNull EditText registroApellido, @NonNull EditText registroCiudad,
      @NonNull EditText registroCorreo, @NonNull EditText registroNombre,
      @NonNull Spinner spinnerCiudad, @NonNull TextView textView, @NonNull TextView textView0,
      @NonNull TextView textView2, @NonNull TextView textView3, @NonNull TextView textView4,
      @NonNull TextView textView5) {
    this.rootView = rootView;
    this.btnActualizar = btnActualizar;
    this.btnTomarFoto = btnTomarFoto;
    this.imgPerfil1 = imgPerfil1;
    this.registerBirthday = registerBirthday;
    this.registrarCelular = registrarCelular;
    this.registroApellido = registroApellido;
    this.registroCiudad = registroCiudad;
    this.registroCorreo = registroCorreo;
    this.registroNombre = registroNombre;
    this.spinnerCiudad = spinnerCiudad;
    this.textView = textView;
    this.textView0 = textView0;
    this.textView2 = textView2;
    this.textView3 = textView3;
    this.textView4 = textView4;
    this.textView5 = textView5;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentPerfilBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentPerfilBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_perfil, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentPerfilBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_actualizar;
      Button btnActualizar = ViewBindings.findChildViewById(rootView, id);
      if (btnActualizar == null) {
        break missingId;
      }

      id = R.id.btn_tomarFoto;
      Button btnTomarFoto = ViewBindings.findChildViewById(rootView, id);
      if (btnTomarFoto == null) {
        break missingId;
      }

      id = R.id.img_perfil_1;
      ImageView imgPerfil1 = ViewBindings.findChildViewById(rootView, id);
      if (imgPerfil1 == null) {
        break missingId;
      }

      id = R.id.register_birthday;
      EditText registerBirthday = ViewBindings.findChildViewById(rootView, id);
      if (registerBirthday == null) {
        break missingId;
      }

      id = R.id.registrar_celular;
      EditText registrarCelular = ViewBindings.findChildViewById(rootView, id);
      if (registrarCelular == null) {
        break missingId;
      }

      id = R.id.registro_apellido;
      EditText registroApellido = ViewBindings.findChildViewById(rootView, id);
      if (registroApellido == null) {
        break missingId;
      }

      id = R.id.registro_ciudad;
      EditText registroCiudad = ViewBindings.findChildViewById(rootView, id);
      if (registroCiudad == null) {
        break missingId;
      }

      id = R.id.registro_correo;
      EditText registroCorreo = ViewBindings.findChildViewById(rootView, id);
      if (registroCorreo == null) {
        break missingId;
      }

      id = R.id.registro_nombre;
      EditText registroNombre = ViewBindings.findChildViewById(rootView, id);
      if (registroNombre == null) {
        break missingId;
      }

      id = R.id.spinner_ciudad;
      Spinner spinnerCiudad = ViewBindings.findChildViewById(rootView, id);
      if (spinnerCiudad == null) {
        break missingId;
      }

      id = R.id.textView;
      TextView textView = ViewBindings.findChildViewById(rootView, id);
      if (textView == null) {
        break missingId;
      }

      id = R.id.textView0;
      TextView textView0 = ViewBindings.findChildViewById(rootView, id);
      if (textView0 == null) {
        break missingId;
      }

      id = R.id.textView2;
      TextView textView2 = ViewBindings.findChildViewById(rootView, id);
      if (textView2 == null) {
        break missingId;
      }

      id = R.id.textView3;
      TextView textView3 = ViewBindings.findChildViewById(rootView, id);
      if (textView3 == null) {
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

      return new FragmentPerfilBinding((LinearLayout) rootView, btnActualizar, btnTomarFoto,
          imgPerfil1, registerBirthday, registrarCelular, registroApellido, registroCiudad,
          registroCorreo, registroNombre, spinnerCiudad, textView, textView0, textView2, textView3,
          textView4, textView5);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
