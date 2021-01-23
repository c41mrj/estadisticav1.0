package com.example.estadistica;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.estadistica.databinding.ActivityPruebasDeHipotesisBinding;

public interface ProcedimientoPruebasHipotesisMediaDesviacionConocida extends conversiones {
    String ola = "";
    void actualizarValoresEnTablas();


}
