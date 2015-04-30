package com.example.opengles_training.app.triangle;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import static com.example.opengles_training.app.constants.Constants.TAG;

/**
 * Created by Ufuk on 30-04-15.
 */
public class TriangleRenderer implements GLSurfaceView.Renderer {

    private FloatBuffer mVertices;
    private int mProgramObject;
    private int mWidth;
    private int mHeight;

    // (x,y,z) coordinates of primitive
    private final float[] mVerticesData =
            {
                    0.0f, 0.5f, 0.0f,
                    -0.5f, -0.5f, 0.0f,
                    0.5f, -0.5f, 0.0f
            };

    public TriangleRenderer(Context context) {
        mVertices = ByteBuffer.allocateDirect(mVerticesData.length * 4)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        mVertices.put(mVerticesData).position(0);
    }

    private int LoadShader(int type, String shaderSrc) {

        int shader;
        int[] compiled = new int[1];

        // Create the shader object of the type specified
        shader = GLES20.glCreateShader(type);

        if (shader == 0) {
            return 0;
        }

        // Load the shader source
        GLES20.glShaderSource(shader, shaderSrc);

        // Compile the shader
        GLES20.glCompileShader(shader);

        // Check the compile status
        GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compiled, 0);

        if (compiled[0] == 0) {
            Log.e(TAG(this), GLES20.glGetShaderInfoLog(shader));
            GLES20.glDeleteShader(shader);
            return 0;
        }

        return shader;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // #1 onDrawFrame function will send in positions for each vertex that will be placed in this variable.
        String vertexShaderSrc =
                "attribute vec4 vPosition;      \n" +
                        "attribute vec4 a_color;    \n" +
                        "varying vec4 v_color;      \n" +
                        "void main()                \n" +
                        "{                          \n" +
                        "   v_color = a_color;      \n" +
                        "   gl_Position = vPosition;\n" +
                        "}                          \n";

        // #1 The default precision for float variables in the shader.
        // #2 The value written into the v_color variable is what will be written out into the color buffer.
        String fragmentShaderSrc =
                "precision mediump float;       \n" +
                        "varying vec4 v_color;      \n" +
                        "void main()                \n" +
                        "{                          \n" +
                        "   gl_FragColor = v_color; \n" +
                        "}                          \n";

        int vertexShader;
        int fragmentShader;
        int programObject;
        int[] linked = new int[1];

        vertexShader = LoadShader(GLES20.GL_VERTEX_SHADER, vertexShaderSrc);
        fragmentShader = LoadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderSrc);

        // Create the program object, later on shaders will be attached to program object
        programObject = GLES20.glCreateProgram();

        if (programObject == 0) {
            return;
        }

        GLES20.glAttachShader(programObject, vertexShader);
        GLES20.glAttachShader(programObject, fragmentShader);

        // Bind vPosition to attribute 0
        GLES20.glBindAttribLocation(programObject, 0, "vPosition");

        // Link the program
        GLES20.glLinkProgram(programObject);

        // Check the link status
        GLES20.glGetProgramiv(programObject, GLES20.GL_LINK_STATUS, linked, 0);

        if (linked[0] == 0) {
            Log.e(TAG(this), "Error linking program:");
            Log.e(TAG(this), GLES20.glGetProgramInfoLog(programObject));
            GLES20.glDeleteProgram(programObject);
            return;
        }

        mProgramObject = programObject;

        // The clear color should be set prior to calling glClear
        GLES20.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        mWidth = width;
        mHeight = height;
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        // Viewport represents 2D rectangle in which all rendering operations will be displayed.
        GLES20.glViewport(0, 0, mWidth, mHeight);

        // Clear the color buffer according to defined color in glClearColor
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        // After this method all subsequent rendering will occur using the
        // vertex and fragment shaders attached to the program object.
        GLES20.glUseProgram(mProgramObject);

        // Load the vertex data
        GLES20.glVertexAttribPointer(0, 3, GLES20.GL_FLOAT, false, 0, mVertices);
        GLES20.glEnableVertexAttribArray(0);

        // This function draws a primitive such as a triangle, line, or strip.
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3);
    }
}
