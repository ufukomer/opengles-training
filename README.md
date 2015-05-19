# OpenGL ES Training (Android)

### Shaders

The steps in working with a shader object:

1. Create shader object: 

    > glCreateShader(int type)
    >>  _type:_ The type of shader to create, either GL_VERTEX_SHADER or GL_FRAGMENT_SHADER. 
   
2. Provide shader source code:

    > glShaderSource(int shader, String shaderSrc)
    >> _shader:_ The shader created with _glCreateShader_.
    >>
    >> _shaderSrc:_ Shader source code.
    
3. Compile the shader:
    
    > glCompileShader(int shader)
    >> _shader:_ The shader object which already has a source code.

4. Get information about compiling after compile:

    > glGetShaderiv(int shader, int pname, int[] params)
    >> _shader:_ The shader object to get information about.
    >> 
    >> _pname:_ Information parameter;
    >>
    *   GL_COMPILE_STATUS
    *   GL_DELETE_STATUS
    *   GL_INFO_LOG_LENGTH
    *   GL_SHADER_SOURCE_LENGTH
    *   GL_SHADER_TYPE
    >>
    >> _params:_ Integer storage location for the result of the query.

5. Get shader info log to see if error occurs and find out why:

    > glGetShaderInfoLog(int shader)
    >> _shader:_ The shader object which you want to get log info about.
    
