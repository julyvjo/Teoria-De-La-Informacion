//*****************************************************
//funciones para memoria nula

void MemNula();

void leerMemNula(double prob[], int *n);

void CantInfo(double prob[], double vecCantInfo[], int n);

double entropiaNula(double prob[], double vecCantInfo[], int n);

void extension(int ext[], double prob[], int n, int h, int k);

void mostrarResultados(double prob[], double vecCantInfo[], int n);

//*****************************************************
//funciones para markov

void Markov();

void leerMarkov();

//*****************************************************
//funciones comunes

double info(double p);
