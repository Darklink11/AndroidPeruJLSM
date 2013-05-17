package com.jl.androidfinal;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	
	enum Pieza{
		NEGRA,
		BLANCA,
		VACIO,
		MARCADO
	}
	
	Pieza piezaActual = Pieza.NEGRA;
	int tableroWidth = 8;
	int tableroHeight = 8;
	
	int nPiezasBlanco = 2;
	int nPiezasNegro = 2;
	
	Pieza tablero[][] = new Pieza[tableroWidth][tableroHeight];
	ImageAdapter imageAdapter;
	TextView textView;
	TextView textNegras;
	TextView textBlancas;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);

	    GridView gridview = (GridView) findViewById(R.id.gridview);
	    textNegras = (TextView) findViewById(R.id.negras);
	    textBlancas = (TextView) findViewById(R.id.blancas);
	    
	    imageAdapter = new ImageAdapter(this);
	    gridview.setAdapter(imageAdapter);
	    
	    
	    //Iniciamos el tablero
	    setupTablero();

	    gridview.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	            //Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
	            
	            //Si es un movimiento correcto
	          if(checkMovimiento(position))
	          {
	        	  
	        	 
	        	  
		            switch(piezaActual)
		            {
		            case NEGRA:
		            	
		            		voltearPiezas(R.drawable.pieza2);
		            		
		            		piezaActual = Pieza.BLANCA;
		            		textView.setText("Blanco");
		            	break;
		            	
		            case BLANCA:
		            	
		            		voltearPiezas(R.drawable.pieza1);
		            		
		            		piezaActual = Pieza.NEGRA;
		            		textView.setText("Negro");
		            	
		            	break;
		            
		            
		            }
		            
		            if(nPiezasBlanco+nPiezasNegro==tableroWidth*tableroHeight)
		            {
		            	
		            	Toast.makeText(MainActivity.this, "GameOver", Toast.LENGTH_LONG).show();
		            }
		            
		            
	          }
	          else
	          {
	        	  
	        	  //No se puede
	        		Toast.makeText(MainActivity.this, "Jugada Invalida ", Toast.LENGTH_SHORT).show();
	          }
	           
	        }
	    });
	}	
	
	//Checkea si el movimiento es correcto
	//Si es correcto marca las piezas a voltear
	private boolean checkMovimiento(int position)
	{
		
		
		int i_origin = position/tableroWidth;
		int j_origin = position%tableroHeight;
		boolean valido = false;
		
		int nVolteadas = 0;
		
		
		
		//Si no esta vacio retornamos falso
		if(tablero[i_origin][j_origin]!= Pieza.VACIO)
				return false;
		
		
		int i, j;
		i = i_origin-1;
		j = j_origin;
		//Arriba
		
		
		
			
				while(i>=0 && tablero[i][j]!=Pieza.VACIO )
				{
				
					
					if(tablero[i][j]==piezaActual)
					{
						
						if( i+1!=i_origin)
						{
							valido = true;
							while(i!=i_origin)
							{
								tablero[i][j] = Pieza.MARCADO;
								nVolteadas++;
								i++;
								
							}
							
							nVolteadas-=1;
							 
						}
						
						
						break;
					}
						
					//tablero[i][j] = Pieza.MARCADO;
					
					i--;
					
				}
			
		
		
		i = i_origin+1;
		j = j_origin;
		
		//Abajo
		while(i<tableroHeight && tablero[i][j]!=Pieza.VACIO)
		{
			
			if(tablero[i][j]==piezaActual)
			{
				
				if( i-1!=i_origin)
				{
					valido = true;
					while(i!=i_origin)
					{
						tablero[i][j] = Pieza.MARCADO;
						nVolteadas++;
						i--;
					}
					
					nVolteadas-=1;
					 
				}
				
				
				break;
			}
			i++;
		}
		
		i = i_origin;
		j = j_origin-1;
		//Izquierda
		while(j>=0 && tablero[i][j]!=Pieza.VACIO ) 
		{
			
			if(tablero[i][j]==piezaActual)
			{
				
				if( j+1!=j_origin)
				{
					valido = true;
					while(j!=j_origin)
					{
						tablero[i][j] = Pieza.MARCADO;
						nVolteadas++;
						j++;
					}
					
					nVolteadas-=1;
					 
				}
				
				
				break;
			}
			j--;
		}
		
		i = i_origin;
		j = j_origin+1;
		
		//Derecha
		while(j<tableroWidth  && tablero[i][j]!=Pieza.VACIO )
		{
			
			if(tablero[i][j]==piezaActual)
			{
				
				if( j-1!=j_origin)
				{
					valido = true;
					while(j!=j_origin)
					{
						tablero[i][j] = Pieza.MARCADO;
						nVolteadas++;
						j--;
					}
					
					nVolteadas-=1;
					 
				}
				
				
				break;
			}
			j++;
		}
		
		//Arriba -Derecha
		
		i = i_origin-1;
		j = j_origin+1;
		
			
		while(i>=0 && j<tableroWidth && tablero[i][j]!=Pieza.VACIO )
		{
		
			
			if(tablero[i][j]==piezaActual)
			{
				
				if( i+1!=i_origin && j-1!=j_origin)
				{
					valido = true;
					while(i!=i_origin && j!=j_origin)
					{
						tablero[i][j] = Pieza.MARCADO;
						nVolteadas++;
						i++;
						j--;
					}
					
					nVolteadas-=1;
					 
				}
				
				
				break;
			}
				
			//tablero[i][j] = Pieza.MARCADO;
			
			i--;
			j++;
			
		}
		
		//Arriba-Izquierda
		
		i = i_origin-1;
		j = j_origin-1;
		
			
		while(i>=0 && j>=0 &&tablero[i][j]!=Pieza.VACIO )
		{
			
			
			if(tablero[i][j]==piezaActual)
			{
				
				if( i+1!=i_origin && j+1!=j_origin)
				{
					valido = true;
					while(i!=i_origin && j!=j_origin)
					{
						tablero[i][j] = Pieza.MARCADO;
						nVolteadas++;
						i++;
						j++;
					}
					
					nVolteadas-=1;
					 
				}
				
				
				break;
			}
				
			//tablero[i][j] = Pieza.MARCADO;
			
			i--;
			j--;
			
		}
		
		//Abajo - Derecha
		
		i = i_origin+1;
		j = j_origin+1;
		
			
		while(i<tableroHeight && j<tableroWidth && tablero[i][j]!=Pieza.VACIO )
		{
			
			
			if(tablero[i][j]==piezaActual)
			{
				
				if( i-1!=i_origin && j-1!=j_origin)
				{
					valido = true;
					while(i!=i_origin && j!=j_origin)
					{
						tablero[i][j] = Pieza.MARCADO;
						nVolteadas++;
						i--;
						j--;
					}
					nVolteadas-=1;
					 
				}
				
				
				break;
			}
				
			//tablero[i][j] = Pieza.MARCADO;
			
			i++;
			j++;
			
		}
		
		//Abajo  - Izquierda
		
		i = i_origin+1;
		j = j_origin-1;
		
			
		while(i<tableroHeight && j>=0 && tablero[i][j]!=Pieza.VACIO )
		{
			
			
			if(tablero[i][j]==piezaActual)
			{
				
				if( i-1!=i_origin && j+1!=j_origin)
				{
					valido = true;
					while(i!=i_origin && j!=j_origin)
					{
						tablero[i][j] = Pieza.MARCADO;
						nVolteadas++;
						i--;
						j++;
					}
					
					nVolteadas-=1;
					
					 
				}
				
				
				break;
			}
				
			//tablero[i][j] = Pieza.MARCADO;
			
			i++;
			j--;
			
		}
		
		if(valido)
		{
			tablero[i_origin][j_origin] = Pieza.MARCADO;
			
			switch(piezaActual)
			{
			case NEGRA:
						nPiezasNegro+=(nVolteadas+1);
						nPiezasBlanco-= (nVolteadas);
				break;
				
			case BLANCA:
				
						nPiezasBlanco+=(nVolteadas+1);
						nPiezasNegro-= (nVolteadas);
				break;
			
			}
			
		//	Toast.makeText(MainActivity.this, "Volteadas "+nVolteadas, Toast.LENGTH_SHORT).show();
			
			textNegras.setText("	"+nPiezasNegro);
			textBlancas.setText("	"+nPiezasBlanco);
			
			
			return true;
			
		}
		
		return false;
		
	}
	
	public void nuevoButtonPressed(View v)
	{
		setupTablero();
		
	}
	
	private void voltearPiezas(int resourceId)
	{
		
		
		for(int i=0;i<tableroHeight;i++)
		{
			for(int j=0;j<tableroWidth;j++)
			{
				if(tablero[i][j]==Pieza.MARCADO)
				{
				
					int position = i*tableroWidth + j;
					
					//Toast.makeText(MainActivity.this, "Volteando: " + position, Toast.LENGTH_SHORT).show();
					
					
					imageAdapter.mThumbIds[position] = resourceId;
					tablero[i][j]= piezaActual;
					
				}
				
			}
			
		}
		
		imageAdapter.notifyDataSetChanged();
		
		
	}
	private void setupTablero()
	{
		
		//Iniciamos  el tablero en vacio
		for(int i=0;i<tableroHeight;i++)
		{
			for(int j=0;j<tableroWidth;j++)
			{
					tablero[i][j] = Pieza.VACIO;
					int posicion = i*tableroWidth+j;
					
					imageAdapter.mThumbIds[posicion] = R.drawable.tab;
				
			}
			
		}
		
		//Posicion inicial de piezas
			int posInicial = (tableroWidth-1)/2;
			int posicion = posInicial*tableroWidth+posInicial;
			
			 tablero[posInicial][posInicial] = Pieza.BLANCA;
			 imageAdapter.mThumbIds[posicion] = R.drawable.pieza1;
			 
			 
			 tablero[posInicial][posInicial+1] = Pieza.NEGRA;
			 imageAdapter.mThumbIds[posicion+1] = R.drawable.pieza2;
			 
			 tablero[posInicial+1][posInicial] = Pieza.NEGRA;
			 imageAdapter.mThumbIds[posicion+tableroWidth] = R.drawable.pieza2;
			 
			 tablero[posInicial+1][posInicial+1] = Pieza.BLANCA;
			 imageAdapter.mThumbIds[posicion+tableroWidth+1] = R.drawable.pieza1;
			 
			 imageAdapter.notifyDataSetChanged();
			 
			 
			 //Reseteamos los contadores de piezas
			 nPiezasBlanco = 2;
			 nPiezasNegro = 2;
			 
			 textNegras.setText("	"+nPiezasNegro);
			textBlancas.setText("	"+nPiezasBlanco);
			 
			 //Reseteamos el turno
			 
			 piezaActual = Pieza.NEGRA;
			 textView = (TextView)findViewById(R.id.jugador);
			 textView.setText("Negro");
			 

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
