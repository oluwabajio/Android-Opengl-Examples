package android.opengltutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.opengl.GLSurfaceView;
import android.opengltutorial.databinding.ActivitySimpleGlBinding;
import android.opengltutorial.renderers.SimpleGLRenderer;
import android.os.Bundle;
import android.view.View;

public class SimpleGlActivity extends AppCompatActivity {

    ActivitySimpleGlBinding binding;
    SimpleGLRenderer renderer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySimpleGlBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        initListeners();
        initGlSurfaceView();


    }

    private void initGlSurfaceView() {


        // Create an OpenGL ES 2.0 context
        binding.glSurfaceView.setEGLContextClientVersion(2);

        renderer = new SimpleGLRenderer();

        // Set the Renderer for drawing on the GLSurfaceView
        binding.glSurfaceView.setRenderer(renderer);

        // Render the view only when there is a change in the drawing data
        binding.glSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

    }

    private void initListeners() {

    }
}