#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include "funciones.h"

#define N_MAX 10


//*****************************************************
//funciones para memoria nula

void MemNula(){

    double prob[N_MAX];
    double vecCantInfo[N_MAX];
    int n=0,k=1;

    leerMemNula(prob,&n);

    //cant info de cada simbolo


    //entropia


    //extension ?

    //mostrar resultados



}

void leerMemNula(double prob[], int *n){
    FILE *arch;

    arch = fopen("nula.txt","rt");

    if(arch == NULL){
        printf("Archivo nula.txt no encontrado");
        exit(1);
    }else{
        while(!feof(arch)){
            fscanf(arch,"%f",prob[*n]);
            *n = *n + 1;
        }
    }

    fclose(arch);
}

void vecCantInfo(double prob[], int n, double cantInfo[] ){

    for(int i=0; i<n; i++){
        cantInfo[i] = info(prob[i]);
    }
}

//*****************************************************
//funciones para markov

void Markov(){

}

void leerMarkov(){

}

//*****************************************************
//funciones comunes

double info(double p){

    if( p != 0 )
        return p * log10(1/p) / log10(2);
    else
        return 0;
}
















