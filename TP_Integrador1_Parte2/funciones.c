#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include "funciones.h"

//*****************************************************************CODIFICACION*****************************************************************
void Codificacion(){
    char codigos[CANT_CODIGOS][LONG_MAX_CODIGO];
    double prob[CANT_SIMBOLOS];
    int n; //cantidad de simbolos

    leerProbabilidades(prob,&n);
    generaCodigos(codigos,n);
    mostrarCodificacion(prob,codigos,n);
}

void leerProbabilidades(double prob[],int *n){
    FILE *arch;
    int j=0;

    arch = fopen("probabilidades.txt","rt");

    if(arch == NULL){
        printf("Archivo probabilidades.txt no encontrado");
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

void generaCodigos(char codigos[][LONG_MAX_CODIGO], int n){
    char codigo[LONG_MAX_CODIGO] = {"0"};
    char aux[LONG_MAX_CODIGO];
    char uno[2] = {"1"};

    for(int i=0; i<n; i++){
        strcpy(codigos[i],codigo);
        strcpy(aux,"");
        strcat(aux,uno);
        strcat(aux,codigo);
        strcpy(codigo,aux);
    }
}

void mostrarCodificacion(double prob[], char codigos[][LONG_MAX_CODIGO], int n){
    system("cls");
    printf("*********************************\n");
    printf("Si  P(Si)  Codigo\n");
    for(int i=0;i<n;i++){
        printf("S%d  %4.3f  %s\n",i+1,prob[i],codigos[i]);
    }
    printf("*********************************\n");
}

//*****************************************************************CALCULOS************************************************************************************************************

void Calculos(){
    char codigos[CANT_CODIGOS][LONG_MAX_CODIGO];
    double prob[CANT_SIMBOLOS];
    int n; //cantidad de simbolos

    leerDatos(prob,codigos,&n);

    //mostrar resultados
    mostrarResultados(prob,codigos,n);

}

void leerDatos(double prob[], char codigos[][LONG_MAX_CODIGO], int *n){
    FILE *arch;
    int j=0;

    arch = fopen("codigo.txt","rt");

    if(arch == NULL){
        printf("Archivo codigo.txt no encontrado");
        exit(1);
    }else{
        while(!feof(arch)){
            fscanf(arch,"%lf %s",prob+j,codigos[j]);
            j++;
        }
        *n = j;
    }
    fclose(arch);
}

double longMedia(double prob[], char codigos[][LONG_MAX_CODIGO], int n){
    double longMed = 0;
    for(int i=0; i<n; i++){
        longMed += prob[i]*strlen(codigos[i]);
    }
    return longMed;
}

double entropia(double prob[], int n){
    double e = 0;

    for(int i=0; i<n; i++){
        e += prob[i] * (log10(1/prob[i])/log10(2));
    }

    return e;
}

int cumpleKraft(char codigos[][LONG_MAX_CODIGO], int n){
    double sum = 0;
    int rta = 0;
    for(int i=0; i<n; i++){
        sum+= pow((double)2,(double)-1*strlen(codigos[i]));
    }
    if(sum <= 1)
        rta = 1;
    return rta;
}

void mostrarResultados(double prob[], char codigos[][LONG_MAX_CODIGO], int n){

    printf("\n************ RESULTADOS ************\n");

    printf("Si  P(Si)    Li    Codigo\n");
    for(int i=0;i<n;i++){
        printf("S%d  %4.3f    %d     %s\n",i+1,prob[i],strlen(codigos[i]),codigos[i]);
    }

    printf("\nH(S) = %5.3f\n",entropia(prob,n));
    printf("L = %5.3f\n",longMedia(prob,codigos,n));

    if(cumpleKraft(codigos,n))
        printf("Cumple la inecuacion de Kraft\n");
    else
        printf("No cumple la inecuacion de Kraft\n");

    printf("es compacto?\n");

    printf("************************************\n\n\n");

}

