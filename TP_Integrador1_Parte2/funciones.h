#define CANT_CODIGOS 10
#define CANT_SIMBOLOS 10
#define LONG_MAX_CODIGO 15

//Funciones de Codificacion

void leerProbabilidades(double prob[],int *n);

void generaCodigos(char codigos[][LONG_MAX_CODIGO], int n);

//Funciones de Calculos

double longMedia(double prob[], char codigos[][LONG_MAX_CODIGO], int n);

double entropia(double prob[], int n);

int cumpleKraft(char codigos[][LONG_MAX_CODIGO], int n);

void mostrarResultados(double prob[], char codigos[][LONG_MAX_CODIGO],double ACalculado[], int A[], int n);

void generaA(double prob[], double ACalculado[], int A[], int n);

int esCompacto(char codigos[][LONG_MAX_CODIGO],int A[], int n);

