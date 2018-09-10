package com.hao.myutils.util;

import android.opengl.GLES20;

public class GLUtil {

    private GLUtil() {
    }

    public static int loadVertexShader(String shaderCode) {
        // 创建顶点着色器
        int shader = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
        // 将GLSL代码加入到着色器
        GLES20.glShaderSource(shader, shaderCode);
        // 编译着色器
        GLES20.glCompileShader(shader);
        return shader;
    }

    public static int loadFragmentShader(String shaderCode) {
        // 创建片元着色器
        int shader = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
        // 将GLSL代码加入到着色器中
        GLES20.glShaderSource(shader, shaderCode);
        // 编译着色器
        GLES20.glCompileShader(shader);
        return shader;
    }

    public static int createAndLinkProgram(int vertexShader, int fragmentShader) {
        // 创建一个OpenGLES程序
        int program;
        program = GLES20.glCreateProgram();
        // 将顶点着色器加入到程序
        GLES20.glAttachShader(program, vertexShader);
        // 将片元着色器加入到程序中
        GLES20.glAttachShader(program, fragmentShader);
        // 连接到着色器程序
        GLES20.glLinkProgram(program);
        return program;
    }
}
