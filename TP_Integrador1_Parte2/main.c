#include <stdio.h>
#include <stdlib.h>
#include "funciones.h"
int main()
{
    int op;

    printf("CARGAR LOS ARCHIVOS ANTES DE CONTINUAR!\n\n");
    printf("Seleccione una Opcion:\n\n");
    printf("1) Codificacion\n");
    printf("2) Calculos\n");

    scanf("%d",&op); //Lee opcion
    system("cls");  //Clear screen

    if( op == 1 )
        Codificacion();
    else if( op == 2 )
        Calculos();

    return 0;
}
