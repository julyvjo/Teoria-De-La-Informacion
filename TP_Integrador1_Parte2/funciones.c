#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include "funciones.h"

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
//Genera codificaciones de la forma: 0, 10, 110, 1110, 11110, etc
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

//Caclula la longitud media de un codigo dado
double longMedia(double prob[], char codigos[][LONG_MAX_CODIGO], int n){
    double longMed = 0;
    for(int i=0; i<n; i++){
        longMed += prob[i]*strlen(codigos[i]); //Sumatoria de Pi*Li
    }
    return longMed;
}

//Calcula la entropía
double entropia(double prob[], int n){
    double e = 0;

    for(int i=0; i<n; i++){
        e += prob[i] * (log10(1/prob[i])/log10(2)); //Sumatoria de Pi*Ii
    }

    return e;
}

//Verifica si una codificacion cumple con la inecuacion de craft (0 = NO, 1 = SI)
int cumpleKraft(char codigos[][LONG_MAX_CODIGO], int n){
    double sum = 0;
    int rta = 0;
    for(int i=0; i<n; i++){
        sum+= pow((double)2,(double)-1*strlen(codigos[i])); //Sumatoria de r**(-li)
    }
    if(sum <= 1)
        rta = 1;
    return rta;
}

//Genera un vector con los alfa sub i de cada palabra
void generaA(double prob[], double ACalculado[], int A[], int n){

    for (int i=0; i<n; i++ ){
        ACalculado[i] = ( -log10(prob[i]) / log10(2) );
        A[i] = (int) ACalculado[i] + 1;
    }

}

//Verifica si un codigo instantaneo es compacto (0 = NO, 1 = SI)
int esCompacto(char codigos[][LONG_MAX_CODIGO],int A[], int n){
    //El Codigo a verificar siempre es instantaneo en nuestro caso,
    //por lo tanto solo se comprueba que todos los Li <= Ai para ser compacto

    int respuesta = 1, i=0;

    while( i<n && strlen(codigos[i]) <= A[i] ){ //Si encuentra un codigo que no cumple la condicion corta el ciclo
        i++;
    }

    if( i < n )
        respuesta = 0;

    return respuesta;
}

void mostrarResultados(double prob[], char codigos[][LONG_MAX_CODIGO],double ACalculado[], int A[], int n){

    printf("\n***************** RESULTADOS *****************\n");

    printf("______________________________________________\n");
    printf("Si  P(Si)    Li   AiCalculado    Ai   Codigo\n");
    printf("______________________________________________\n");
    for(int i=0;i<n;i++){
        printf("S%d  %4.3f    %d       %5.3f       %d    %s\n",i+1,prob[i],strlen(codigos[i]),ACalculado[i],A[i],codigos[i]);
    }
    printf("______________________________________________\n");

    printf("\n- H(S) = %5.3f\n",entropia(prob,n));
    printf("- L = %5.3f\n",longMedia(prob,codigos,n));

    if(cumpleKraft(codigos,n))
        printf("- El codigo cumple la inecuacion de Kraft\n");
    else
        printf("- El codigo NO cumple la inecuacion de Kraft\n");

    if ( esCompacto(codigos,A,n) )
        printf("- El codigo es compacto\n");
    else
        printf("- El codigo NO es compacto\n");

    printf("\n**********************************************\n\n\n");

}
