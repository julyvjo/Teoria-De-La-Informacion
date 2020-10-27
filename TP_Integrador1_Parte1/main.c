#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "funciones.h"

int main()
{
    int op;
    srand(time(NULL));

    printf("Recuerde cargar los datos en el Archivo .txt correspondiente!\n\n");
    printf("Seleccione un tipo de Fuente:\n\n");
    printf("1) Memoria Nula\n");
    printf("2) Markov\n");

    scanf("%d",&op); //leemos opcion
    system("cls");  //clear screen

    if( op == 1 )
        MemNula();
    else if( op == 2 )
        Markov();

    return 0;
}
