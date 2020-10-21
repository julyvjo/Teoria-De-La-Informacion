#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include "funciones.h"

#define N_MAX 10
#define ITERACIONES 7


//*****************************************************
//funciones para memoria nula

void MemNula(){

    double prob[N_MAX];
    double vecCantInfo[N_MAX];
    int ext[10] = {0,1,2,3,4,5,6,7,8,9};
    int n=0,k=0;

    leerMemNula(prob,&n);

    //cant info de cada simbolo
    CantInfo(prob,vecCantInfo,n);


    //mostramos resultados sin extension
    mostrarResultadosNula(prob,vecCantInfo,n);


    //extension ?
    printf("Desea calcular extension? ingrese K o 0 para salir\n\n");

    scanf("%d",&k);

    if( 0 < k && k < 11){
        extension(ext,prob,n,0,k);
        printf("\nH(S^%d) = %lf\n",k,entropiaNula(prob,vecCantInfo,n)*k);
    }

}

void leerMemNula(double prob[], int *n){
    FILE *arch;
    int j=0;

    arch = fopen("nula.txt","rt");

    if(arch == NULL){
        printf("Archivo nula.txt no encontrado");
        exit(1);
    }else{
        while(!feof(arch)){
            fscanf(arch,"%lf",prob+j);
            j++;
        }
        *n = j;
    }


    fclose(arch);
}

void CantInfo(double prob[], double vecCantInfo[], int n){

    for(int i=0; i<n; i++){
        vecCantInfo[i] = info(prob[i]);
    }
}

double entropiaNula(double prob[], double vecCantInfo[], int n){
    double e = 0;

    for(int i=0; i<n; i++){
        e += prob[i] * vecCantInfo[i];
    }

    return e;
}

/*
F[] almacena los simbolos por ej 1234 / F[0] == 1 / F[1] == 2 / etc...
prob[] almacena las probabilidades de cada simbolo
n es la cantidad de simbolos de la fuente
h siempre se invoca desde main con valor 0, nos sirve para incrementar el "digito" al que va a afectar el algoritmo para generar las combinaciones
k es de a cuanto tomamos los digitos, (nsimbolos tomados de a k)


el algoritmo llama recursivamente en un bucle de forma que va generando todas las posibilidades de simbolos tomados de a K
en el primer llamado F esta vacio y si por ejemplo nuestros simbolos son 1,2,3 y los tomamos de a 3 quedaria llamando a la recursividad para formar:

1XX 2XX 3XX

en el llamado siguiente, si partimos del 1XX quedaria aumentandose h para pasar al siguiente digito de la siguiente forma:

11X 12X 13X y asi hasta que h == k entonces se completan todos los digitos y se realizan los calculos de informacion en cada uno
esto se hace en la etapa de fin de recursividad (condicion de salida)
*/
void extension(int ext[], double prob[], int n, int h, int k){

    if(h==k){ //caso llegado al final de la recursividad
        double cantInfo = 1, probabilidad = 1;
        for(int i=0; i<k; i++) //calcula la cantidad de informacion
            cantInfo *= prob[ ext[i]-1 ]; //es -1 porque como en el else sumamos 1 esta desfasado
        probabilidad = cantInfo; //en este punto cantinfo tiene el valor de la probabilidad
        cantInfo = ( log10(1/cantInfo) / log10(2) );

        //printf("simbolo ");
        for(int i=0; i<k; i++){
            printf("S%d",ext[i]);
        }
        printf("\t%lf",probabilidad);
        printf("\t%lf \n",cantInfo);

    }else{
        for(int i=0; i<n; i++){
            ext[h] = i+1; //es +1 para que los simbolos no empiecen en cero, nada mas
            extension(ext,prob,n,h+1,k);
        }
    }
}

void mostrarResultadosNula(double prob[], double vecCantInfo[], int n){

    printf("**************** RESULTADOS ****************\n\n");

    printf("Si     P(Si)      I(Si)\n\n");
    for(int i=0; i<n; i++){

        printf("S%d: %10.5f %10.5f\n",i+1,prob[i],vecCantInfo[i]);
    }
    printf("\nH(s) = %10.5f\n\n",entropiaNula(prob,vecCantInfo,n));

    printf("********************************************\n\n");
}

//********************************************************************************************************************************************************************************************************************
//funciones para markov

void Markov(){

    double mat[N_MAX][N_MAX];
    double v[N_MAX];
    int n;

    //leemos la matriz
    leerMarkov(mat,&n);

    //calculamos V*
    calcula_V(mat,v,n);

    //mostrar resultados
    mostrarResultadosMarkov(mat,v,n);


}

/*
FORMATO DE LECTURA

n
matriz

*/
void leerMarkov(double mat[][N_MAX], int *n){

    FILE *arch;

    arch = fopen("markov.txt","rt");

    if(arch == NULL){
        printf("Archivo markov.txt no encontrado");
        exit(1);
    }else{
        fscanf(arch,"%d",n);

        for(int i=0; i<*n; i++){
            for(int j=0; j<*n; j++){
                fscanf(arch,"%lf",&(mat[i][j]));
            }
        }
    }

    fclose(arch);

}

void calcula_V(double mat[][N_MAX], double v[], int n){

    double mAux[N_MAX][N_MAX];

    //copiamos la matriz original para no perderla
    copia_matriz(mAux,mat,n);

    //itera hasta llegar al estado estacionario de la matriz
    for(int i=0; i<ITERACIONES; i++ ){
        mat_cuadrada(mAux,n);
    }

    for(int i=0; i<n; i++)
        v[i] = mAux[i][1];

}

void mat_cuadrada(double mat[][N_MAX],int n){

        double acum;
        double mAux[N_MAX][N_MAX];

        copia_matriz(mAux,mat,n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                acum = 0;
                for (int k = 0; k < n; k++) {
                    acum += mAux[i][k] * mAux[k][j];
                }
                mat[i][j] = acum;
            }
        }

}

void copia_matriz(double mat2[][N_MAX], double mat[][N_MAX], int n){

    for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                mat2[i][j] = mat[i][j];
            }
    }

}

double entropiaMarkov(double mat[][N_MAX], double v[N_MAX], int n){

    double entropia = 0, auxColumna = 0; //auxColumna es la sumatoria de la columna

    for(int j=0; j<n; j++){
            auxColumna = 0;
            for(int i=0; i<n; i++){
                auxColumna += mat[i][j] * info(mat[i][j]);
            }
        entropia += v[j] * auxColumna;
    }

    return entropia;
}

void mostrarResultadosMarkov(double mat[][N_MAX], double v[N_MAX], int n){

    printf("**************** RESULTADOS ****************\n\n");

    printf("Matriz de transicion:\n\n");

    //mostrar matriz
    for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                printf("%6.3lf ",mat[i][j]);
            }
            printf("\n");
    }

    //mostrar V*
    printf("\nV*: {");
    for(int i=0; i<n; i++){
        printf("%4.3lf",v[i]);
        if( i != n-1 )
            printf(", ");
    }
    printf("}\n");


    printf("\nH(s) = %7.4f\n\n",entropiaMarkov(mat,v,n));

    printf("********************************************\n\n");
}



//*****************************************************
//funciones comunes

double info(double p){

    if( p != 0 )
        return log10(1/p) / log10(2);
    else
        return 0;
}
















