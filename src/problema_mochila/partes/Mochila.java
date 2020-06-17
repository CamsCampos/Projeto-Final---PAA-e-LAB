package problema_mochila.partes;

import java.util.*;

public class Mochila {
	private int capacidade;		
	private List<Parte> partes;	


	public Mochila(int capacidade){
		this.capacidade=capacidade;
		partes=new ArrayList<Parte>();
	}

	public void add(Parte p){
		if(!partes.contains(p))
			partes.add(p);
		else
			throw new IllegalArgumentException("Parte duplicata "+p);
	}
	

	private int valor(Set<Parte> parcial){
		int r=0;
		for(Parte p : parcial)
			r+=p.getValor();
		return r;
	}
	
	private int peso(Set<Parte> parcial){
		int r=0;
		for(Parte p : parcial)
			r+=p.getPeso();
		return r;
	}
	
	private void escolhe(Set<Parte> parcial, int livello, Set<Parte> best){
		if(valor(parcial)>valor(best)){
			best.clear();
			best.addAll(parcial);
			System.out.println(parcial);
		}
		
		for(Parte p : partes){
			if(!parcial.contains(p)){
				if(peso(parcial)+p.getPeso()<=capacidade){
					parcial.add(p);
					escolhe(parcial, livello+1, best);
					parcial.remove(p);
				}
			}
		}
	}
	
	public static double resolveProgramacaoDinamica(int[] w,int[] p,int i,int c,int[][] DP) {
		if (c < 0) {
			double inf = Double.POSITIVE_INFINITY;
			inf = inf*-1;
			return inf;
		}
		else if (i == 0 || c == 0) {
		return 0;
		}
		
		else {
			if (DP[i][c] < 0) {
		       int nottaken = (int) resolveProgramacaoDinamica(w, p, i-1, c, DP);
		       int taken = (int) resolveProgramacaoDinamica(w, p, i-1, c-w[i-1], DP) + p[i-1];
		       DP[i][c] = Math.max(nottaken, taken);
			}
		return DP[i][c];
				
			}
		}
	
	public static int ProgramacaoDinamica(int[]w,int[]p,int n, int C) {
		int[][] DP = new int[n][C];
		for (int i=1; i>=n; i++) {
			for (int c=1; i>=C; c++) {
				DP[i][c]= -1;
						}}
		return (int) resolveProgramacaoDinamica(w,p,n,C,DP);
	}
	
	
	
	public Set<Parte> resolveProgramacaoDinamica(){
		Set<Parte> parcial=new HashSet<Parte>();
		Set<Parte> best=new HashSet<Parte>();
		escolhe(parcial, 0, best);
		return best;
	}
}
