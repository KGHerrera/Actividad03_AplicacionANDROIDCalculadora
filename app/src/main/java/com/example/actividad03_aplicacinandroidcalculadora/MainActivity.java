package com.example.actividad03_aplicacinandroidcalculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;


public class MainActivity extends AppCompatActivity {

    private String resultado = "none";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText e1 = findViewById(R.id.caja_resultado);
        e1.setEnabled(false);
    }

    public void btnCero(View v){
        verificarNumero("0");
    }
    public void btnUno(View v){
        verificarNumero("1");
    }
    public void btnDos(View v){
        verificarNumero("2");
    }
    public void btnTres(View v){
        verificarNumero("3");
    }
    public void btnCuatro(View v){
        verificarNumero("4");
    }
    public void btnCinco(View v){
        verificarNumero("5");
    }
    public void btnSeis(View v){
        verificarNumero("6");
    }
    public void btnSiete(View v){
        verificarNumero("7");
    }
    public void btnOcho(View v){
        verificarNumero("8");
    }
    public void btnNueve(View v){
        verificarNumero("9");
    }

    public void verificarNumero(String numeroAgregar){
        EditText e1 = findViewById(R.id.caja_resultado);
        String numero = e1.getText().toString();

        if(numero.equals("0") || numero.contains("a") || numero.contains("e") || numero.contains("i")) e1.setText(numeroAgregar);
        else if (numero.equals("-0")) e1.setText(numero.replace("0", numeroAgregar));
        else e1.setText(numero + numeroAgregar);
    }

    public void btnC(View v){
        EditText e1 = findViewById(R.id.caja_resultado);
        e1.setText("0");
        resultado = "none";
    }

    public void btnPunto(View v){
        EditText e1 = findViewById(R.id.caja_resultado);
        String numero = e1.getText().toString();

        if(!numero.contains(".")) e1.setText(numero + ".");
    }

    public void btnDel(View v){
        EditText e1 = findViewById(R.id.caja_resultado);
        String numero = e1.getText().toString();

        if (numero.length()>1){
            e1.setText(numero.substring(0, numero.length()-1));
            if (e1.getText().toString().equals("-")){
                e1.setText("0");
            }
        } else{
            e1.setText("0");
        }
    }

    public void btnSumar(View v){
        encadenarOperaciones(" + ");
    }
    public void btnRestar(View v){
        encadenarOperaciones(" - ");
    }
    public void btnMultiplicar(View v){
        encadenarOperaciones(" * ");
    }
    public void btnDividir(View v){
        encadenarOperaciones(" / ");
    }

    public void btnMasMenos(View v){
        EditText e1 = findViewById(R.id.caja_resultado);
        String numero = e1.getText().toString();

        if (numero.contains("-")){
            numero = numero.replace("-","");
        } else{
            numero = "-" + numero;
        }
        if (numero.equals("-0")){
            numero = numero.replace("-", "");
        }
        e1.setText(numero);
    }

    public void btnSqrt(View v){
        EditText e1 = findViewById(R.id.caja_resultado);
        String numero = e1.getText().toString();

        try{
            numero = String.valueOf(Math.sqrt(Double.parseDouble(numero)));
            e1.setText(numero);
        } catch (Exception e){
            e1.setText("Syntax error");
        }
    }

    public void btnElevado(View v){
        EditText e1 = findViewById(R.id.caja_resultado);
        String numero = e1.getText().toString();
        numero = String.valueOf(Math.pow(Double.parseDouble(numero), 2));
        e1.setText(numero);

    }

    public void encadenarOperaciones(String operador){
        EditText e1 = findViewById(R.id.caja_resultado);
        String numero = e1.getText().toString();
        if(resultado.equals("none")){
            resultado = numero + operador;
        } else{
            resultado = resultado + numero + operador;
        }
        e1.setText("0");

    }

    public void btnIgual(View v){
        EditText e1 = findViewById(R.id.caja_resultado);
        String numero = e1.getText().toString();

        if (!resultado.equals("none")){
            resultado = resultado + numero;
            String resultado2;

            try{
                ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
                resultado2 = engine.eval(resultado.replace(" ", "")).toString();
                resultado = "none";
            }catch (Exception e){
                resultado2 = "Syntax error";
                resultado = "none";
            }
            e1.setText(resultado2);
        }
    }




}