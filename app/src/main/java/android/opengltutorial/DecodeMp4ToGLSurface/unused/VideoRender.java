package android.opengltutorial.DecodeMp4ToGLSurface.unused;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.opengl.GLSurfaceView;
import android.opengltutorial.DecodeMp4ToGLSurface.unused.TextureRender;
import android.util.Log;
import android.view.Surface;

import java.io.IOException;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * A GLSurfaceView implementation that wraps TextureRender.  Used to render frames from a
 * video decoder to a View.
 */
public class VideoRender implements GLSurfaceView.Renderer, SurfaceTexture.OnFrameAvailableListener {
    private static String TAG = "VideoRender";
    private TextureRender mTextureRender;
    private SurfaceTexture mSurfaceTexture;
    private boolean updateSurface = false;
    private MediaPlayer mMediaPlayer;
    public VideoRender(Context context) {
        mTextureRender = new TextureRender();
    }
    public void setMediaPlayer(MediaPlayer player) {
        mMediaPlayer = player;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        mTextureRender.surfaceCreated();
        /*
         * Create the SurfaceTexture that will feed this textureID,
         * and pass it to the MediaPlayer
         */
        mSurfaceTexture = new SurfaceTexture(mTextureRender.getTextureId());
        mSurfaceTexture.setOnFrameAvailableListener(this);
        Surface surface = new Surface(mSurfaceTexture);
        mMediaPlayer.setSurface(surface);
        surface.release();
        try {
            mMediaPlayer.prepare();
        } catch (IOException t) {
            Log.e(TAG, "media player prepare failed");
        }
        synchronized(this) {
            updateSurface = false;
        }
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }

    public void onDrawFrame(GL10 glUnused) {
        synchronized(this) {
            if (updateSurface) {
                mSurfaceTexture.updateTexImage();
                updateSurface = false;
            }
        }
        mTextureRender.drawFrame(mSurfaceTexture);
    }


    synchronized public void onFrameAvailable(SurfaceTexture surface) {
        updateSurface = true;
    }
}  // End of class VideoRender.

