# OpenGL ES Training (Android)

### Shaders

The steps in working with a shader object:

1. Create shader object: 

    > ```glCreateShader(int type)```
    >>  _type:_ The type of shader to create, either GL_VERTEX_SHADER or GL_FRAGMENT_SHADER. 
   
2. Provide shader source code:

    > ```glShaderSource(int shader, String shaderSrc)```
    >> _shader:_ The shader created with ```glCreateShader()```.
    >>
    >> _shaderSrc:_ Shader source code.
    
3. Compile the shader:
    
    > ```glCompileShader(int shader)```
    >> _shader:_ The shader object which already has a source code.

4. Get information about compiling after compile:
    
    > ```glGetShaderiv(int shader, int pname, int[] params)```
    >> _shader:_ The shader object to get information about.
    >> 
    >> _pname:_ Information parameter;
    >>
    *   GL_COMPILE_STATUS
    *   GL_DELETE_STATUS
    *   GL_INFO_LOG_LENGTH
    *   GL_SHADER_SOURCE_LENGTH
    *   GL_SHADER_TYPE
    >
    >> _params:_ Integer storage location for the result of the query.

5. Get shader info log to see if error occurs and find out why:

    > ```glGetShaderInfoLog(int shader)```
    >> _shader:_ The shader object which you want to get log info about.

### Programs

1. Create program object:

    > ```glCreateProgram()```

2. Attach shader object to our program object created before:

    > ```glAttachShader(int program, int shader)```
    >> _program:_ The program object which shader will be attached to it.
    >>
    >> _shader:_ The shader object which will be attached to the program object created before with ```glCreateProgram()``` method.

3. After shaders have been attached, it is ready to link shader objects together.

    > ```glLinkProgram(int program)```
    >> _program:_ The program object which shader objects attached to it with ```glAttachShader()```.

4. Check the link succeeded or not:

    > ```glGetProgramiv (int program, int pname, int[] params, int offset)```
    >> _program:_ The program object to get information about.
    >>
    >> _pname:_ Information parameter;
    >>
    *   GL_ACTIVE_ATTRIBUTES
    *   GL_ACTIVE_ATTRIBUTE_MAX_LENGTH
    *   GL_ACTIVE_UNIFORM_BLOCK
    *   GL_ACTIVE_UNIFORM_BLOCK_MAX_LENGTH
    *   GL_ACTIVE_UNIFORMS
    *   GL_ACTIVE_UNIFORM_MAX_LENGTH
    *   GL_ATTACHED_SHADERS
    *   GL_DELETE_STATUS
    *   GL_INFO_LOG_LENGTH
    *   GL_LINK_STATUS
    *   GL_PROGRAM_BINARY_RETRIEVABLE_HINT
    *   GL_TRANSFORM_FEEDBACK_BUFFER_MODE
    *   GL_TRANSFORM_FEEDBACK_VARYINGS
    *   GL_TRANSFORM_FEEDBACK_VARYING_MAX_LENGTH
    *   GL_VALIDATE_STATUS
    >
    >> _params:_ Integer storage location for the result of the query.
    
5. Get program info log to see if error occurs and find out why:

    > ```glGetProgramInfoLog(int program)```
    >> _program:_ The program object which you want to get log info about.
    
6. Activate the program object:

    > ```glUseProgram(int program)```
    >> _program:_ The program object which will be activated for render process.