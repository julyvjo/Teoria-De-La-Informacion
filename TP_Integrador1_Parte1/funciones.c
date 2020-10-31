#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <time.h>
#include "funciones.h"

//******************************************* MEMORIA NULA *******************************************
void MemNula(){
    double prob[N_MAX];
    double vecCantInfo[N_MAX];
    double probSim[N_MAX] = {0};
    int n=0;

    leerMemNula(prob,&n);
    CantInfo(prob,vecCantInfo,n);
    SimulacionNula(prob,probSim,n);
    mostrarResultadosNula(prob,probSim,vecCantInfo,n);
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

//Carga el vector con la cantidad de informacion de casa simbolo
void CantInfo(double prob[], double vecCantInfo[], int n){
    for(int i=0; i<n; i++){
        vecCantInfo[i] = info(prob[i]);
    }
}

//Calcula la entropia en fuente de memoria NULA
double entropiaNula(double prob[], double vecCantInfo[], int n){
    double e = 0;

    for(int i=0; i<n; i++){
        e += prob[i] * vecCantInfo[i];
    }

    return e;
}

//Realiza una simulacion segun la distribucion de probabilidades ingresada
void SimulacionNula(double prob[], double probSim[], int n){
    int tope, pos;

    printf("Ingrese numero de simulaciones: ");
    scanf("%d",&tope);
    system("cls");
    printf("Simulacion:\n\n");
    for(int i=0; i<tope; i++){
        pos= simulaSimbolo(prob,n);
        printf("S%d ",pos+1);
        probSim[pos] += (double) 1/tope;
    }
    printf("\n\n");
}

//Crea un simbolo random y retorna su posicion segun la dist de probabilidades
int simulaSimbolo(double prob[], int n){
    double p = (double)(rand() % 1000 + 1)/1000; //Numero random entre 0 y 1
    double acum = prob[0];
    int i = 0;

    while(p > acum && i < n){ //Sale del ciclo cuando el numero random es mayor que la probabilidad acumulada
        i++;
        acum += prob[i];
    }
    return i;
}

void mostrarResultadosNula(double prob[], double probSim[], double vecCantInfo[], int n){

    printf("**************** RESULTADOS ****************\n");

    printf("Si       P(Si)        P(Sim)       I(Si)\n");
    for(int i=0; i<n; i++){

        printf("S%d  %12.5f %12.5f %12.5f\n",i+1,prob[i],probSim[i],vecCantInfo[i]);
    }
    printf("\nH(s)=%6.3f\n",entropiaNula(prob,vecCantInfo,n));

    printf("********************************************\n\n");
}

//*************************************************** MARKOV ****************************************************
void Markov(){

    double mat[N_MAX][N_MAX];
    double v[N_MAX];
    int n;

    leerMarkov(mat,&n);
    calcula_V(mat,v,n);

    mostrarResultadosMarkov(mat,v,n);
}

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

//Calcula el vector estacionario de una matriz de transicion por medio de ITERACIONES
void calcula_V(double mat[][N_MAX], double v[], int n){
    double mAux[N_MAX][N_MAX];

    copia_matriz(mAux,mat,n); //Copiamos la matriz original para no perderla

    for(int i=0; i<ITERACIONES; i++ ){ //Itera hasta llegar al estado estacionario
        mat_cuadrada(mAux,n);
    }

    for(int i=0; i<n; i++) //Asigna a cada pos de V* su valor correspondiente tomando la primera columna de la matriz
        v[i] = mAux[i][1];

}

//Calcula la matriz al cuadrado usando una matriz auxiliar
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

//Metodo para copiar una matriz ya que en C no se pueden asignar
void copia_matriz(double mat2[][N_MAX], double mat[][N_MAX], int n){
    for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                mat2[i][j] = mat[i][j];
            }
    }

}

//Calcula la entropia en una fuente de MARKOV
double entropiaMarkov(double mat[][N_MAX], double v[N_MAX], int n){
    double entropia = 0, auxColumna = 0; //auxColumna es la sumatoria de la columna

    for(int j=0; j<n; j++){
            auxColumna = 0;
            for(int i=0; i<n; i++){
                auxColumna += mat[i][j] * info(mat[i][j]); //Sumatoria por COLUMNA
            }
        entropia += v[j] * auxColumna; //Sumatoria de V* multiplicado por la entropía por columna
    }

    return entropia;
}

void mostrarResultadosMarkov(double mat[][N_MAX], double v[N_MAX], int n){

    printf("**************** RESULTADOS ****************\n");

    printf("Matriz de transicion:\n");

    //Mostrar matriz
    for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                printf("%6.3f ",mat[i][j]);
            }
            printf("\n");
    }

    //Mostrar V*
    printf("\nV*: {");
    for(int i=0; i<n; i++){
        printf("%4.3lf",v[i]);
        if( i != n-1 )
            printf(", ");
    }
    printf("}\n");


    printf("\nH(s) =%6.3f\n",entropiaMarkov(mat,v,n));

    printf("********************************************\n\n");
}

//Calcula la cantidad de informacion dada una probabilidad
double info(double p){
    if( p != 0 ) //Verifica que p != 0 para no tener conflictos con la matriz de transicion
        return log10(1/p) / log10(2);
    else
        return 0;
}
















