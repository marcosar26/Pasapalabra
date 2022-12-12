package marcos.pasapalabra;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

import marcos.pasapalabra.model.Estadisticas;

public class Final extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        TextView acertadas = findViewById(R.id.acertadas);
        TextView falladas = findViewById(R.id.falladas);
        Button salir = findViewById(R.id.salir);

        Estadisticas estadisticas = Estadisticas.getInstance();

        acertadas.setText(String.format(new Locale("es", "ES"), "Preguntas acertadas: %d", estadisticas.getAcertadas()));
        falladas.setText(String.format(new Locale("es", "ES"), "Preguntas falladas: %d", estadisticas.getFalladas()));

        salir.setOnClickListener(v -> Final.this.finish());
    }
}