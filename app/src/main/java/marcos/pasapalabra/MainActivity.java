package marcos.pasapalabra;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

import marcos.pasapalabra.model.Estadisticas;
import marcos.pasapalabra.model.Pregunta;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cargarDatos();

        Button jugar = findViewById(R.id.jugar);
        jugar.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Jugar.class);
            startActivity(intent);
        });
    }

    private void cargarDatos() {
        Estadisticas.getInstance().reset();

        ObjectMapper mapper = new ObjectMapper();
        try {
            InputStream stream = getAssets().open("preguntas.json");
            CollectionType type = mapper.getTypeFactory().constructCollectionType(LinkedList.class, Pregunta.class);
            LinkedList<Pregunta> preguntas = mapper.readValue(stream, type);
            Jugar.preguntas = new LinkedList<>(preguntas);
        } catch (IOException e) {
            throw new RuntimeException();
        }

        if (Jugar.preguntas.isEmpty()) throw new RuntimeException();
    }
}