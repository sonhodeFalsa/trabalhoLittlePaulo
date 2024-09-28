package com.example.trabalhopdmnathally;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trabalhopdmnathally.R;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNome, editTextEmail, editTextIdade, editTextDisciplina, editTextNota1, editTextNota2;
    private TextView textViewMensagemErro, textViewResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNome = findViewById(R.id.editTextNome);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextIdade = findViewById(R.id.editTextIdade);
        editTextDisciplina = findViewById(R.id.editTextDisciplina);
        editTextNota1 = findViewById(R.id.editTextNota1);
        editTextNota2 = findViewById(R.id.editTextNota2);
        textViewMensagemErro = findViewById(R.id.textViewMensagemErro);
        textViewResultado = findViewById(R.id.textViewResultado);

        Button buttonEnviar = findViewById(R.id.buttonEnviar);
        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarDados();
            }
        });

        Button buttonLimpar = findViewById(R.id.buttonLimpar);
        buttonLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparCampos();
            }
        });
    }

    private void validarDados() {
        String nome = editTextNome.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String idadeStr = editTextIdade.getText().toString().trim();
        String disciplina = editTextDisciplina.getText().toString().trim();
        String nota1Str = editTextNota1.getText().toString().trim();
        String nota2Str = editTextNota2.getText().toString().trim();

        StringBuilder mensagemErro = new StringBuilder();

        // Validação
        if (nome.isEmpty()) {
            mensagemErro.append("O campo de nome está vazio.\n");
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mensagemErro.append("O email é inválido.\n");
        }
        int idade = 0;
        try {
            idade = Integer.parseInt(idadeStr);
            if (idade <= 0) {
                mensagemErro.append("A idade deve ser um número positivo.\n");
            }
        } catch (NumberFormatException e) {
            mensagemErro.append("A idade deve ser um número.\n");
        }
        // Validação das notas
        float nota1 = 0, nota2 = 0;
        try {
            nota1 = Float.parseFloat(nota1Str);
            nota2 = Float.parseFloat(nota2Str);
            if (nota1 < 0 || nota1 > 10 || nota2 < 0 || nota2 > 10) {
                mensagemErro.append("As notas devem estar entre 0 e 10.\n");
            }
        } catch (NumberFormatException e) {
            mensagemErro.append("As notas devem ser números válidos.\n");
        }

        if (mensagemErro.length() > 0) {
            textViewMensagemErro.setText(mensagemErro.toString());
        } else {
            calcularMedia(nome, email, idade, disciplina, nota1, nota2);
        }
    }

    private void calcularMedia(String nome, String email, int idade, String disciplina, float nota1, float nota2) {
        float media = (nota1 + nota2) / 2;
        String resultado = media >= 6 ? "Aprovado" : "Reprovado";

        String resumo = "Nome: " + nome + "\nEmail: " + email + "\nIdade: " + idade +
                "\nDisciplina: " + disciplina + "\nNotas: " + nota1 + " e " + nota2 +
                "\nMédia: " + media + "\nResultado: " + resultado;

        textViewResultado.setText(resumo);
        textViewMensagemErro.setText(""); // Limpa mensagens de erro
    }

    private void limparCampos() {
        editTextNome.setText("");
        editTextEmail.setText("");
        editTextIdade.setText("");
        editTextDisciplina.setText("");
        editTextNota1.setText("");
        editTextNota2.setText("");
        textViewMensagemErro.setText("");
        textViewResultado.setText("");
    }
}
