// Generated by view binder compiler. Do not edit!
package com.example.meuscontatos.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.example.meuscontatos.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityRecuperarSenhaBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final EditText emailRecuperacaoSenhaEt;

  @NonNull
  public final Button enviarEmailBt;

  private ActivityRecuperarSenhaBinding(@NonNull ConstraintLayout rootView,
      @NonNull EditText emailRecuperacaoSenhaEt, @NonNull Button enviarEmailBt) {
    this.rootView = rootView;
    this.emailRecuperacaoSenhaEt = emailRecuperacaoSenhaEt;
    this.enviarEmailBt = enviarEmailBt;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityRecuperarSenhaBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityRecuperarSenhaBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_recuperar_senha, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityRecuperarSenhaBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.emailRecuperacaoSenhaEt;
      EditText emailRecuperacaoSenhaEt = rootView.findViewById(id);
      if (emailRecuperacaoSenhaEt == null) {
        break missingId;
      }

      id = R.id.enviarEmailBt;
      Button enviarEmailBt = rootView.findViewById(id);
      if (enviarEmailBt == null) {
        break missingId;
      }

      return new ActivityRecuperarSenhaBinding((ConstraintLayout) rootView, emailRecuperacaoSenhaEt,
          enviarEmailBt);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
