package com.example.estadistica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.estadistica.databinding.ActivityLogInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class LogIn extends AppCompatActivity {


    private ActivityLogInBinding lienzo;
    private FirebaseAuth userAuth;
    private String email;
    private String contraseña;
    private Pattern patternEmail;
    private Pattern patternContraseña;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lienzo = ActivityLogInBinding.inflate(getLayoutInflater());
        final View view = lienzo.getRoot();
        setContentView(view);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        lienzo.button.setOnClickListener(clickLogin());


    }

    public View.OnClickListener clickLogin(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(faltaDato()){
                    Toast.makeText(LogIn.this, "Todos los datos son requeridos", Toast.LENGTH_SHORT).show();
                }else{
                    email = lienzo.editTextTextEmailAddress.getText().toString();
                    contraseña = lienzo.editTextTextPassword.getText().toString();
                    login();
                }
            }
        };
    }

    public View.OnClickListener clickSignIn(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(faltaDato()){
                    Toast.makeText(LogIn.this, "Todos los datos son requeridos", Toast.LENGTH_SHORT).show();
                }else{
                    signIn();
                }
            }
        };
    }

    public boolean faltaDato(){
        if(lienzo.editTextTextEmailAddress.getText().toString().isEmpty()||lienzo.editTextTextPassword.getText().toString().isEmpty()) return true;
        else return false;
    }


    public void goMenuGeneral(){
        Intent intent = new Intent(this,MenuGeneral.class);
        startActivity(intent);
    }

    public void login(){

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    goMenuGeneral();
                    Toast.makeText(LogIn.this, "Bienvenido/Bienvenida!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LogIn.this, "Usuario no registrado", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void signIn(){

        userAuth.signInWithEmailAndPassword(email,contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    
                    AlertDialog.Builder builder = new AlertDialog.Builder(LogIn.this);
                    builder.setMessage("Gracias por registrarte al acceso anticipado de Estadística VPro.\nEspero que el proyecto sea de tu agrado ;)\n\nContacto con el programador: krave4412@gmail.com")
                            .setCancelable(false)
                            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    goMenuGeneral();
                                }
                            });
                    AlertDialog alertDialog1 = builder.create();
                    alertDialog1.show();
                            
                }else{
                    Toast.makeText(LogIn.this, "No se pudo registrar al usuario, por favor reintentar", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}