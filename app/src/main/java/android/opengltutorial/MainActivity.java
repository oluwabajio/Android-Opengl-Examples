package android.opengltutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.opengltutorial.DecodeMp4ToGLSurface.DecodeToGLSurfaceActivity;
import android.opengltutorial.DecodeMp4ToSurface.DecodeToSurfaceActivity;
import android.opengltutorial.databinding.ActivityMainBinding;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        initListeners();
        setContentView(view);
    }

    private void initListeners() {
        binding.btnSimpleGl.setOnClickListener(v -> {
            startActivity(new Intent(this, SimpleGlActivity.class));
        });

        binding.btnDecodeMp4.setOnClickListener(v -> {
            startActivity(new Intent(this, DecodeToSurfaceActivity.class));
        });

        binding.btnDecodeMp4ToGLSurface.setOnClickListener(v -> {
            startActivity(new Intent(this, DecodeToGLSurfaceActivity.class));
        });
    }
}