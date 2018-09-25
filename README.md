# Traffic-Simulator
Simulador de tráfico con carreteras, cruces de varios tipos y vehículo variados. Cuenta con interfaz gráfica (GUI). Práctica de la asignatura Tecnología de la Programación (TP) de la Universidad Complutense de Madrid

# Modo de ejecutarlo
El simulador recibe parámetros a través de los parámetros de lanzamientos. Entre ellos están:
- -i + [path]: indica un path de entrada de eventos. Dichos eventos se encontrarán en la carpeta "resources/examples/.../"
- -m + [Mode]: Indica el modo de ejecución del programa. Existe la versión de console ("batch") e interfaz gráfica ("gui"). Este parámetro es
obligatorio.
- -o + [path]: Indica el path del archivo de salida donde, por pasos de simulación, se indica cómo ha resultado la emulación y cual es estado de
cada vehículo, carretera y cruce en cada instante. 
### Este parámetro tampoco es obligatorio.

Un ejemplo de comando de parámetro es: -m gui -i resources/examples/cruces1.ini -o resources/examples/cruces1.ini.eout

# Capturas del simulador:
A continuación se muestrán algunas capturas del simulador: 

![captura_simulador](https://user-images.githubusercontent.com/43186051/46008758-f1a0cf80-c0bd-11e8-8bf4-89c9dc1759a2.PNG)

