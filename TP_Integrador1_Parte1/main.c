#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "funciones.h"

int main()
{
    int op;
    srand(time(NULL));

    printf("CARGAR LOS ARCHIVOS ANTES DE CONTINUAR!(Leer README)\n\n");
    printf("Seleccione un tipo de Fuente:\n\n");
    printf("1) Memoria Nula\n");
    printf("2) Markov\n");

    scanf("%d",&op); //Lee opcion
    system("cls");  //Clear screen

    if( op == 1 )
        MemNula();
    else if( op == 2 )
        Markov();

    return 0;
}
