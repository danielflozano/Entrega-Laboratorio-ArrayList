import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ProfesoresEstudiantes {
	
	ArrayList<String> listaProfesores;
	ArrayList<String> listaEstudiantes; // Declaración de la lista
	ArrayList<ArrayList<String>> listaGeneralEstudiantes;
	
	public ProfesoresEstudiantes() {
		listaProfesores = new ArrayList<>();
		listaGeneralEstudiantes = new ArrayList<>();
	}
	
	public void iniciar() {
		construirMenuOpciones();
		registrarProfesores();
		registrarEstudiantes();
		consultarListaProfesoresYEstudiantes();
		consultarProfesorPorNombre();
		consultarCantidadEstudiantesPorProfesor();
		consultarEstudiantes();
	}
	
	private void construirMenuOpciones() {
		String nombreUsuario = JOptionPane.showInputDialog("Ingrese su nombre");
		
		String menu = "MENU PRINCIPAL\n\n"
				+ "1. Registrar profesores\n"
				+ "2. Registrar estudiantes asociados al profesor\n"
				+ "3. Consultar lista total de profesores y sus estudiantes asociados\n"
				+ "4. Consultar un profesor e imprimir la lista de estudiantes asociados\n"
				+ "5. Consultar un profesor e indicar la cantidad de estudiantes asociados\n"
				+ "6. Consultar un estudiante e indicar cual es su director de grupo\n"
				+ "7. Salir\n\n"
				+ "Seleccione una opción\n";
		int codigoMenu = Integer.parseInt(JOptionPane.showInputDialog(menu));
		
		System.out.println("Señor/a: " + nombreUsuario + " usted ha seleccionado la opción " + codigoMenu);
	}
		
	private void registrarProfesores() {
		System.out.println("\n**** Registro de Profesores ****");
		int cant = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de profesores"));
		
		for (int i = 0; i < cant; i++) {
			String nombreProfesor = JOptionPane.showInputDialog("Nombre profesor " + (i+1));
			
			if (listaProfesores.contains(nombreProfesor)) {
				JOptionPane.showMessageDialog(null, "El nombre que intenta registrar ya ha sido registrado "
						+ "anteriormente, por favor digite otro nombre");				
				i--;
				
			} else {
				listaProfesores.add(nombreProfesor);
			}
		}
		System.out.println("Registro de Profesores Exitoso!\n");
	}
	
	private void registrarEstudiantes() {
		System.out.println("\n**** Registro de Estudiantes ****");
		for (int i = 0; i < listaProfesores.size(); i++) {
			
			JOptionPane.showMessageDialog(null, "Ingrese los estudiantes para el profesor " + listaProfesores.get(i));			
			listaEstudiantes = new ArrayList<String>();			
			int cant = Integer.parseInt(JOptionPane.showInputDialog("Cantidad de estudiantes"));
			
			for (int j = 0; j < cant; j++) {
				String nombreEst = JOptionPane.showInputDialog("Nombre estudiante " + (j+1));
				
				if (listaEstudiantes.contains(nombreEst)) {
					JOptionPane.showMessageDialog(null, "El nombre que intenta registrar ya ha sido registrado "
							+ "anteriormente, por favor digite otro nombre");
					j--;
					
				} else {
					listaEstudiantes.add(nombreEst);
				}				
			}			
			listaGeneralEstudiantes.add(listaEstudiantes);
		}		
		System.out.println("Registro de Estudiantes Exitoso!\n");
	}
		
	private void consultarListaProfesoresYEstudiantes() {
		System.out.println("\n**** Lista de Profesores y Estudiantes ****");
		ArrayList<String> listaTemporal;
		
		for (int i = 0; i < listaGeneralEstudiantes.size(); i++) {
			
			listaTemporal = listaGeneralEstudiantes.get(i);
			System.out.print("Profesor: " + listaProfesores.get(i) + " = ");
			System.out.print("[");
			for (int j = 0; j < listaTemporal.size(); j++) {
				System.out.print(listaTemporal.get(j));
				if (j < listaTemporal.size()-1) {
					System.out.print(",");
				}
			}
			
			System.out.print("]\n");
		}
	}
	
	private void consultarProfesorPorNombre() {
		System.out.println("\n**** Consulta de Profesor ****\n");
		String nombre = JOptionPane.showInputDialog("Ingrese el nombre del profesor");
		
		if (listaProfesores.contains(nombre)) {
			int posicion = listaProfesores.indexOf(nombre);
			ArrayList<String> listaEstudiantesTemporal = listaGeneralEstudiantes.get(posicion);
			
			System.out.print("Profesor: " + nombre + " ");
			System.out.print("[");
			for (int i = 0; i < listaEstudiantesTemporal.size(); i++) {
				System.out.print(listaEstudiantesTemporal.get(i));
				if (i < listaEstudiantesTemporal.size()-1) {
					System.out.print(",");
				}
			}
			
			System.out.print("]\n");
			
		} else {
			System.out.println("No se encuentra el Profesor " + nombre + "\n");
		}
	}
	
	private void consultarCantidadEstudiantesPorProfesor() {
		System.out.println("\n**** Consulta Cantidad de Estudiantes de Profesores ****\n");
		String nombre = JOptionPane.showInputDialog("Ingrese el nombre del profesor para saber "
				+ "la cantidad de estudiantes asociados");
		
		if (listaProfesores.contains(nombre)) {
			int posicion = listaProfesores.indexOf(nombre);
			ArrayList<String> listaEstudiantesTemporal = listaGeneralEstudiantes.get(posicion);
			System.out.println("La cantidad de estudiantes asociados al Profesor " + nombre +
					" es " + listaEstudiantesTemporal.size());
			
		} else {
			System.out.println("No se Encuentra el Profesor " + nombre + "\n");
		}
	}
	
	private void consultarEstudiantes() {
		System.out.println("\n**** Consulta de Estudiante ****\n");
		ArrayList<String> listaTemporal;
		
		String nombre = JOptionPane.showInputDialog("Ingrese el nombre del estudiante a consultar");
		
		for (int i = 0; i < listaGeneralEstudiantes.size(); i++) {
			listaTemporal = listaGeneralEstudiantes.get(i);
			
			if (listaTemporal.contains(nombre)) {
				for (int j = 0; j < listaTemporal.size(); j++) {
					if (listaTemporal.get(j).equalsIgnoreCase(nombre)) {
						System.out.println("Encuentra al Nombre: " + nombre + " pertenece al grupo de "
								+ listaProfesores.get(i));
					}
				}
			} else {
				System.out.println("No se encuentra el nombre en el Grupo de " + listaProfesores.get(i));
			}
		}
	}
	
}
