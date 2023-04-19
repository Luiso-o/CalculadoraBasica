package ClaseCalculadora;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Calculadora {

	public static void main(String[] args) {
	
		//instancio un obj MarcoCalculadora
		MarcoCalculadora mimarco=new MarcoCalculadora();
		
		//le digi al programa que termine el programa al presionar el boton close
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//de esta manera el marco será visible
		mimarco.setVisible(true);
		

	}

}

	//creo la clase donde contruire el marco
	class MarcoCalculadora extends JFrame{
		
		//constructor
		public MarcoCalculadora() {
			
			setTitle("Calculadora");//nombre del marco
			
			setBounds(400,100,450,250);//dimensiones y posicion en la pantalla
			
			LaminaCalculadora milamina=new LaminaCalculadora();//instancio un obj calculadora
			
			add(milamina);//agrego la lamina al marco
			
			//pack();//el contenedor se adaptará al tamaño de lo que contiene(tamaño por defecto)
			
		}
		
	}
	
	//clase lamina, donde se verá el codigo y con lo que el usuario interactuará
	class LaminaCalculadora extends JPanel{
		//constructor
		public LaminaCalculadora() {
			
			//aqui la variable empezará en true cada que abramos el programa
			principio=true;
			
			//instancio un bordeLayout a una zona de la lamina
			setLayout(new BorderLayout());
			
			//creo un boton y le denomino un nombre, en este caso 0
			pantalla=new JButton("o");
			
			pantalla.setEnabled(false);//desabilito el JButton pantalla, para usarlo como pantalla
			
			//agrego el boton(ahora desabilitado) a la lamina, el boton estará ubicado arriba,(Norte del marco)
			add(pantalla,BorderLayout.NORTH);
			
			//instancio un segundo objeto de tipo JPanel(aqui agregaremos los botones con los numeros)
			milamina2=new JPanel();
			
			//divido el resto del marco en 4 filas y 4 columnsas
			milamina2.setLayout(new GridLayout(4,4));
			
			//instanciamos la clase que insertará el numero que el usuario teclee en pantalla
			ActionListener insertar=new InsertaNumero();
			//tambien con las operaciones básicas
			ActionListener orden=new AccionOrden();
						
			//creamos botones
			ponerBoton("7",insertar);
			ponerBoton("8",insertar);
			ponerBoton("9",insertar);
			ponerBoton("/",orden);
			ponerBoton("4",insertar);
			ponerBoton("5",insertar);
			ponerBoton("6",insertar);
			ponerBoton("*",orden);
			ponerBoton("1",insertar);
			ponerBoton("2",insertar);
			ponerBoton("3",insertar);
			ponerBoton("-",orden);
			ponerBoton("0",insertar);
			ponerBoton(".",insertar);
			ponerBoton("=",orden);
			ponerBoton("+",orden);
			
			
			//agrego la segunda lamina al centro del marco
			add(milamina2,BorderLayout.CENTER);
			
			//doy un valor al String ultimaOperacion
			ultimaOperacion="=";
		}
		
		//agrego botones y los pongo a la escucha
		private void ponerBoton(String rotulo,ActionListener oyente) {
			
			//creamos botones y le damos valor
			JButton boton=new JButton(rotulo);
			
			//cuando se produzca el evento el boton esté a la escucha
			boton.addActionListener(oyente);
			
			//saco el obj del constructor para poder trabajar el.
			milamina2.add(boton);
		}
		
		//creo una clase interna que se encargara de producir los eventos
		private class InsertaNumero implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//devuelve el String entrada
				String entrada=e.getActionCommand();
				
				//condicion para eliminar el mensaje en pantalla cuando introduciomos el primer numero
				if(principio) {
					pantalla.setText("");
					principio=false;
				}
				
				//podré cambiar el texto de la pantalla cuando lo desee
				pantalla.setText(pantalla.getText() + entrada);//Boton que desabilitamos anteriormente(0)
				
			}
			
		}
		
		//creamos una clase que se encargaran de gestionar las operaciones matematicas
		private class AccionOrden implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//alamaceno un string +-*/
				String operacion=e.getActionCommand();
				
				//convierte el texto de la pantalla en un numero de tipo double
				calcular(Double.parseDouble(pantalla.getText()));
				
				//se almacenara la ultima operacion
				ultimaOperacion=operacion;
				
				//esto reseteara la pantalla cada que usemos un sigo de operacion
				principio=true;
				
			}
			
			public void calcular(double x) {
				
				if(ultimaOperacion.equals("+")) {
					resultado+=x;
				
				}else if(ultimaOperacion.equals("-")) {
					resultado-=x;
					
				}else if(ultimaOperacion.equals("*")) {
					resultado*=x;
					
				}else if(ultimaOperacion.equals("/")) {
					resultado/=x;
					
				}else if(ultimaOperacion.equals("=")) {
					resultado=x;
					
				}
				
				pantalla.setText("" + resultado);
				
			}
			
		}
		
		//declaro variables encapsuladas fuera del contructor para que pueda usarla con cualquier metodo o clase
		private JPanel milamina2;
		private JButton pantalla;
		private boolean principio;
		private double resultado;
		private String ultimaOperacion;
		
	}
