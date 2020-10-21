#define N_MAX 10
//*****************************************************
//funciones para memoria nula

void MemNula();

void leerMemNula(double prob[], int *n);

void CantInfo(double prob[], double vecCantInfo[], int n);

double entropiaNula(double prob[], double vecCantInfo[], int n);

void extension(int ext[], double prob[], int n, int h, int k);

void mostrarResultadosNula(double prob[], double vecCantInfo[], int n);

//*****************************************************
//funciones para markov

void Markov();

void leerMarkov(double mat[][N_MAX], int *n);

void calcula_V(double mat[][N_MAX], double v[], int n);

void mat_cuadrada(double mat[][N_MAX],int n);

void copia_matriz(double mat2[][N_MAX], double mat[][N_MAX], int n);

double entropiaMarkov(double mat[][N_MAX], double v[N_MAX], int n);

void mostrarResultadosMarkov(double mat[][N_MAX], double v[N_MAX], int n);

//*****************************************************
//funciones comunes

double info(double p);
