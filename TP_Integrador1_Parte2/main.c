#include <stdio.h>
#include <stdlib.h>
#include "funciones.h"
int main()
{
    printf("CARGAR EL ARCHIVO ANTES DE CONTINUAR! (Leer README)\n\n");

    char codigos[CANT_CODIGOS][LONG_MAX_CODIGO];
    double prob[CANT_SIMBOLOS];
    double ACalculado[CANT_SIMBOLOS];
    int A[CANT_SIMBOLOS];
    int n; //Cantidad de simbolos

    leerProbabilidades(prob,&n);
    generaCodigos(codigos,n);
    generaA(prob,ACalculado,A,n);
    mostrarResultados(prob,codigos,ACalculado,A,n);

    getchar();

    return 0;
}
