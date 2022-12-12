package marcos.pasapalabra;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.LinkedList;

import marcos.pasapalabra.model.Estadisticas;
import marcos.pasapalabra.model.Pregunta;

public class Jugar extends AppCompatActivity {
    public static LinkedList<Pregunta> preguntas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugar);

        Pregunta pregunta = preguntas.pollFirst();
        assert pregunta != null;

        TextView preguntaView = findViewById(R.id.pregunta);
        EditText respuestaView = findViewById(R.id.respuestaUsuario);
        Button validar = findViewById(R.id.validar);
        Button pasapalabra = findViewById(R.id.pasapalabra);
        Button rendirse = findViewById(R.id.rendirse);

        preguntaView.setText(pregunta.getPregunta());

        validar.setOnClickListener(view -> {
            String respuestaUsuario = respuestaView.getText().toString();

            if (respuestaUsuario.equalsIgnoreCase(pregunta.getRespuesta())) {
                Toast.makeText(Jugar.this, "Pregunta acertada", Toast.LENGTH_SHORT).show();
                Estadisticas.getInstance().preguntaAcertada();
            } else {
                Toast.makeText(Jugar.this, "Pregunta fallada", Toast.LENGTH_SHORT).show();
                Estadisticas.getInstance().preguntaFallada();
            }

            siguientePregunta();
        });

        pasapalabra.setOnClickListener(view -> {
            preguntas.addLast(pregunta);
            siguientePregunta();
        });

        rendirse.setOnClickListener(view -> rendirse());
    }

    private void siguientePregunta() {
        Intent intent;
        if (preguntas.isEmpty()) {
            intent = new Intent(this, Final.class);
        } else {
            intent = new Intent(this, Jugar.class);
        }
        startActivity(intent);
        Jugar.this.finish();
    }

    private void rendirse() {
        preguntas.clear();
        Intent intent = new Intent(this, Final.class);
        startActivity(intent);
        Jugar.this.finish();
    }
}