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
    private EditText cajaResultado;
    private String numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cajaResultado = findViewById(R.id.caja_resultado);
        cajaResultado.setEnabled(false);
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

        numero = cajaResultado.getText().toString();
        if(numero.equals("0") || numero.contains("a") || numero.contains("e") || 
                numero.contains("i")) cajaResultado.setText(numeroAgregar);
        else if (numero.equals("-0")) cajaResultado.setText(numero.replace("0", numeroAgregar));
        else cajaResultado.setText(numero + numeroAgregar);
    }

    public void btnC(View v) {
        cajaResultado.setText("0");
        resultado = "none";
    }

    public void btnPunto(View v){
        numero = cajaResultado.getText().toString();

        if(!numero.contains(".")) cajaResultado.setText(numero + ".");
    }

    public void btnDel(View v){
        numero = cajaResultado.getText().toString();
        if (numero.length()>1){
            cajaResultado.setText(numero.substring(0, numero.length()-1));
            if (cajaResultado.getText().toString().equals("-")){
                cajaResultado.setText("0");
            }
        } else{
            cajaResultado.setText("0");
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

        numero = cajaResultado.getText().toString();

        if (numero.contains("-")){
            numero = numero.replace("-","");
        } else{
            numero = "-" + numero;
        }
        if (numero.equals("-0")){
            numero = numero.replace("-", "");
        }
        cajaResultado.setText(numero);
    }

    public void btnSqrt(View v){
        numero = cajaResultado.getText().toString();

        try{
            numero = String.valueOf(Math.sqrt(Double.parseDouble(numero)));
            cajaResultado.setText(numero);
        } catch (Exception e){
            cajaResultado.setText("Syntax error");
        }
    }

    public void btnElevado(View v){
        numero = cajaResultado.getText().toString();
        numero = String.valueOf(Math.pow(Double.parseDouble(numero), 2));
        cajaResultado.setText(numero);
    }

    public void encadenarOperaciones(String operador){
        numero = cajaResultado.getText().toString();
        if(resultado.equals("none")){
            resultado = numero + operador;
        } else{
            resultado = resultado + numero + operador;
        }
        cajaResultado.setText("0");

    }

    public void btnIgual(View v){
        numero = cajaResultado.getText().toString();

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
            cajaResultado.setText(resultado2);
        }
    }
}