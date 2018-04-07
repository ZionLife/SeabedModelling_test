#version 300 es
attribute vec4 vPosition;
uniform mat4 vMatrix;
attribute vec4 aColor;
void main(){
    gl_Position = vMatrix * vPosition;
    vColor = aColor;
}