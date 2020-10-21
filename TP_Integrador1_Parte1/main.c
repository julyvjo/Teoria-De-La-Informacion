#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "funciones.h"

int main()
{
    int op;

    printf("recuerde cargar los datos en el archivo txt correspondiente\n\n");
    printf("Seleccione tipo de fuente\n\n");
    printf("1) Memoria Nula\n");
    printf("2) Markov\n\n");

    scanf("%d",&op); //leemos opcion
    system("cls");  //clear screen

    if( op == 1 )
        MemNula();
    else if( op == 2 )
        Markov();

    return 0;
}
