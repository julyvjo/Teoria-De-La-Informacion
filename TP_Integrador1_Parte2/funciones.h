#define CANT_CODIGOS 10
#define CANT_SIMBOLOS 10
#define LONG_MAX_CODIGO 15

//Funciones de Codificacion
void Codificacion();

void leerProbabilidades(double prob[],int *n);

void generaCodigos(char codigos[][LONG_MAX_CODIGO], int n);

void mostrarCodificacion(double prob[], char codigos[][LONG_MAX_CODIGO], int n);

//Funciones de Calculos
void Calculos();

void leerDatos(double prob[], char codigos[][LONG_MAX_CODIGO], int *n);

double longMedia(double prob[], char codigos[][LONG_MAX_CODIGO], int n);

int cumpleKraft(char codigos[][LONG_MAX_CODIGO], int n);

