#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <time.h>
#include "funciones.h"

//Memoria Nula
void MemNula(){
    double prob[N_MAX];
    double vecCantInfo[N_MAX];
    double probSim[N_MAX] = {0};
    int ext[10] = {0,1,2,3,4,5,6,7,8,9};
    int n=0,k=0;

    leerMemNula(prob,&n);
    CantInfo(prob,vecCantInfo,n);
    SimulacionNula(prob,probSim,n);
    mostrarResultadosNula(prob,probSim,vecCantInfo,n);

    printf("Desea calcular extension? ingrese K o 0 para salir\n\n");
    scanf("%d",&k);

    if( 0 < k && k < 11){
        Extension(ext,prob,n,0,k);
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

void Extension(int ext[], double prob[], int n, int h, int k){
    if(h==k){ //caso llegado al final de la recursividad
        double cantInfo = 1, probabilidad = 1;
        for(int i=0; i<k; i++) //calcula la cantidad de informacion
            cantInfo *= prob[ ext[i]-1 ]; //es -1 porque como en el else sumamos 1 esta desfasado
        probabilidad = cantInfo; //en este punto cantinfo tiene el valor de la probabilidad
        cantInfo = ( log10(1/cantInfo) / log10(2) );

        for(int i=0; i<k; i++){
            printf("S%d",ext[i]);
        }
        printf("\t%lf",probabilidad);
        printf("\t%lf \n",cantInfo);

    }else{
        for(int i=0; i<n; i++){
            ext[h] = i+1; //es +1 para que los simbolos no empiecen en cero, nada mas
            Extension(ext,prob,n,h+1,k);
        }
    }
}

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
    double p = (double)(rand() % 1000 + 1)/1000;
    double acum = prob[0];
    int i = 0;

    while(p > acum && i < n){
        i++;
        acum += prob[i];
    }
    return i;
}

void mostrarResultadosNula(double prob[], double probSim[], double vecCantInfo[], int n){

    printf("**************** RESULTADOS ****************\n");

    printf("Si       P(Si)        P(Sim)       I(Si)\n");
    for(int i=0; i<n; i++){

        printf("S%d: %12.5f %12.5f %12.5f\n",i+1,prob[i],probSim[i],vecCantInfo[i]);
    }
    printf("\nH(s)=%6.3f\n",entropiaNula(prob,vecCantInfo,n));

    printf("********************************************\n\n");
}

//Markov
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

void calcula_V(double mat[][N_MAX], double v[], int n){
    double mAux[N_MAX][N_MAX];

    copia_matriz(mAux,mat,n); //Copiamos la matriz original para no perderla

    for(int i=0; i<ITERACIONES; i++ ){ //Itera hasta llegar al estado estacionario
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

    printf("**************** RESULTADOS ****************\n");

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


    printf("\nH(s) =%6.3f\n",entropiaMarkov(mat,v,n));

    printf("********************************************\n\n");
}

double info(double p){
    if( p != 0 )
        return log10(1/p) / log10(2);
    else
        return 0;
}
















