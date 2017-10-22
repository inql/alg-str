//UWAGA: TABLICA INDEKSOWANA OD 1 !!!!

#include <time.h>
#include <stdio.h>
#include <math.h>
#include<stdlib.h>
#define MAX 60000l
#define MLD 1000000000.0 //10**9

void swap(int *a, int *b){
  int temp =*a;
  *a=*b;
  *b=temp;
}

void heapify(int *A, int i, int heap_size){
  int largest = 0;
  int l = 2*i; //lewy syn
  int r = 2*i+1; //prawy syn
  if(l<=heap_size && A[l-1]>A[i-1]){
    largest = l;
  }
  else{
    largest = i;
  }
  if(r<=heap_size && A[r-1]>A[largest-1]){
    largest = r;
  }
  if(largest != i){
    swap(&A[i-1],&A[largest-1]);
    heapify(A,largest,heap_size);
  }
}

void buildHeap(int *A, int length){
  int i;
  for(i=length/2; i>0; i--) heapify(A,i,length);
}

void HeapSort(int *A, int length){
  int i;
  buildHeap(A,length);
  for(i=length; i>1; i--){
    swap(&A[i-1],&A[0]);
    length--;
    heapify(A,1,length);
  }
}

void gen_inp(int n)
{
    FILE *inp = fopen("2_3in.txt","w");
    int i;
    srand(time(NULL)); //ziarno generatora
    for(i=0;i<n;i++)
        fprintf(inp,"%i\n",rand()%100000);
}

//--------------------------------------------------

int main() {
  int i=1, n=0, buf=0, *A, x; //A to tablica, n to wielkosc tablicy

  struct timespec tp0, tp1;
  double Tn,Fn;
  for(x=2;x<330000000;x=2*x){ //n<33000
  gen_inp(x);

  FILE *heapin=fopen("2_3in.txt", "r");
  while(fscanf(heapin, "%d", &buf)==1) {
    n++;
  } //tutaj plik sie zamknal i trzeba go jeszcze raz otworzyc

  A = (int*)malloc((n+1)*sizeof(int));
  fclose(heapin);
  heapin=fopen("2_3in.txt", "r");
  while(fscanf(heapin, "%d", &buf)==1) {
    A[i]=buf;
    i++;
  }

  clock_gettime(CLOCK_PROCESS_CPUTIME_ID,&tp0);
  //wywolanie sortowania
  HeapSort(A, n);

  Fn=x*log2(x);

  clock_gettime(CLOCK_PROCESS_CPUTIME_ID,&tp1);

    Tn=(tp1.tv_sec+tp1.tv_nsec/MLD)-(tp0.tv_sec+tp0.tv_nsec/MLD);
    printf("x: %5d \t czas: %3.5lf \t wspolczynnik: %3.5lf\n",x,Tn, (double)Fn/Tn);
    fclose(heapin);
    free(A);
    }

  return 0;
}
