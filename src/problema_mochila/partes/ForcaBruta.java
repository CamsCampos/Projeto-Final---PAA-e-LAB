package problema_mochila.partes;

import java.io.IOException;
import java.util.*;

public class ForcaBruta {
	int[] p;	
    double[] v; 
    int n;	
    int P;	

    double[][] V; 
    boolean[][] K;
    public ForcaBruta( int p[], double v[], int P )
    {
        this.p = (int[])(p.clone());
	this.P = P;
        this.v = (double[])(v.clone());
        this.n = p.length;
        V = new double[n][1+P];
        K = new boolean[n][1+P];
    }

    public void imprimaSolucao( )
    {
        int i=n-1;
        int j=P;
        System.out.println("Objetos na mochila");
        while( i>=0 ) {
            if ( K[i][j] ) {
                System.out.println("Objetos "+i+" peso "+p[i]+" valor "+v[i]);
                j-=p[i];
            }
            i=i-1;
        }
        System.out.println("Valor total "+V[n-1][P]);
        System.out.println("Peso total "+(P-j)+" su "+P);
    }

    public void imprimaV( )
    {
	for (int i=0; i<n; ++i) {
	    for (int j=0; j<=P; ++j) {
		System.out.print(V[i][j]+" ");
	    }
	    System.out.println();
	}		    
    }
    public void imprimaK( ) 
    {
	for (int i=0; i<n; ++i) {
	    for (int j=0; j<=P; ++j) {
		System.out.print(K[i][j]+" ");
	    }
	    System.out.println();
	}		    
    }
    public double resolve( )
    {
        int i,j;
        
        for (i=0; i<n; ++i ) {
	    V[i][0] = 0.0;
	    K[i][0] = false;
        }
	
	for (j=0; j<=P; ++j ) {
	    if ( j < p[0] ) {
		V[0][j] = 0.0;
		K[0][j] = false;
	    } else {
		V[0][j] = v[0];
		K[0][j] = true;
	    }
	}
	
        for (i=1; i<n; ++i) {
            for (j=1; j<=P; ++j) {
		if ( (j >= p[i]) && (V[i-1][j-p[i]]+v[i] > V[i-1][j]) ) {
		    V[i][j] = V[i-1][j-p[i]]+v[i];
		    K[i][j] = true;
		} else {
		    V[i][j] = V[i-1][j];
		    K[i][j] = false;
		}
            }
        }

        
        return V[n-1][P];
    }      

	}


