
package Radix;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import dato.*;
import java.io.RandomAccessFile;

/**
 * Clase en la que se encuentran métodos necesarios para implementar RadixSort.
 * @version 14/10/2020/2.0
 * @author Sofía Elizabeth Cabello Díaz
 */
public class RadixSort {
        /**
         * Su atributo es la ruta de la carpeta en la que se da el ordenamiento. 
         */
        String folderpath;    
        
        /**
         * Método para obtener el valor máximo de números a ordenar.
         * @param alumno Alumno que se quiere ordenar. 
         * @param max Máximo número encontrado.
         * @return Valor máximo de los números encontrados. 
         */
        public int getMax(Alumno alumno, int max){
            int n = alumno.getNoCuenta();
            if(alumno.getNoCuenta() > max){
                max = alumno.getNoCuenta();
            }
            return max;
        }
        
        /**
         * Método para obtener la longitud máxima de cadenas. 
         * @param alumno Alumno del cuál se quiere comparar longitud de su nombre o apellido. 
         * @param max Máxima longitud encontrada.
         * @param met Número para indicar si se busca longitud de nombres o apellidos.
         * @return Longitud máxima.
         */
        public int getMaxLength(Alumno alumno, int max, int met){
            String s;
            if(met == 1){
                s = alumno.getApellido();
            }
            else{
                s = alumno.getNombre();
            }
            if(s.length() > max){
                max = s.length();
            }
            return max;
        }
        
        /**
         * Método de ordenamiento de números por RadixSort. 
         * @param archivo Archivo de texto a ordenar.
         * @throws IOException 
         */
        public void RadixSortC(String archivo) throws IOException{
        
        // Se crea la carpeta en la que estarán las iteraciones y archivo ordenado. 
        RadixFiles rF = new RadixFiles(archivo);
        folderpath = rF.getPath();
        rF.directory();      
            
        
            int i, max = 0, n= 0;
            
            // Se crean los 10 archivos auxiliares que servirán como colas, y sus buffer para leerlos. 
            BufferedReader br_f0 = rF.files("f0",archivo);
            BufferedReader br_f1 = rF.files("f1",archivo);
            BufferedReader br_f2 = rF.files("f2",archivo);
            BufferedReader br_f3 = rF.files("f3",archivo);
            BufferedReader br_f4 = rF.files("f4",archivo);
            BufferedReader br_f5 = rF.files("f5",archivo);
            BufferedReader br_f6 = rF.files("f6",archivo);
            BufferedReader br_f7 = rF.files("f7",archivo);
            BufferedReader br_f8 = rF.files("f8",archivo);
            BufferedReader br_f9 = rF.files("f9",archivo);
            // Archivo en el que se van colocando todos los elementos al final de cada iteración. 
            BufferedReader br_list = rF.files("list",archivo);
            
            
            Dato dato = new Dato();
            Alumno alumno;
            String string;
            
            // Se abre el archivo a ordenar. 
            File file = new File(archivo);
            FileReader reader_file = new FileReader(file);
            BufferedReader br = new BufferedReader(reader_file);
            // Se lee el archivo hasta encontrar un elemento nulo y todos los elementos se copian en list.txt. 
            string = br.readLine();
            while(null != string){
                alumno = dato.obtenerDato(string);
                // Se busca si el número de cuenta es el mayor.
                max = getMax(alumno, max);
                dato.escribirDato(alumno, folderpath+"/list.txt");  
                string = br.readLine();
                // Cuenta el número de líneas en el archivo.
                n++;
            }
            br.close();
            
            // Iteraciones desde el dígito menos significativo hasta alcanzar al más significativo. 
            for (int powerOf10 = 1; powerOf10 <= max; powerOf10 = powerOf10 * 10) {
                
                // Se imprime el número de iteración en los archivos auxiliares.
                printIte(folderpath+"/list.txt",powerOf10);
                for(i=0; i<10;i++){
                    String a = String.valueOf("f"+i+".txt");
                    printIte(folderpath+"/f"+i+".txt",powerOf10);
                }
                
                // Se lee la lista de alumnos, se busca el número de cuenta,se calcula el dígito significativo y se escribe en el archivo auxiliar apropiado. 
                for (i = 0; i < n; i++) {
                    string = br_list.readLine();
                    if(null != string){
                        alumno = dato.obtenerDato(string);
                        int digit = (alumno.getNoCuenta()/powerOf10)%10;
                        if(digit > 0 ){
                            dato.escribirDato(alumno,folderpath+"/f"+digit+".txt");
                        }
                        // Si el número es más corto, se pone al inicio de la lista.
                        else{
                            dato.escribirDato(alumno,folderpath+"/list.txt");
                        }
                    }
                }  
                
                // Se lee cada archivo auxiliar y su contenido se escribe en la lista. 
                readWrite(n,br_f0);
                readWrite(n,br_f1);
                readWrite(n,br_f2);
                readWrite(n,br_f3);
                readWrite(n,br_f4);
                readWrite(n,br_f5);
                readWrite(n,br_f6);
                readWrite(n,br_f7);
                readWrite(n,br_f8);
                readWrite(n,br_f9);
                
                br_list.readLine();
                br_list.readLine();
            } 
            
            // Se crea un archivo para colocar los datos ordenados, se leen de la lista y se escriben.
            File file2 = rF.newFile(archivo);
            for(i = 0;i<n;i++){
                string = br_list.readLine();
                if(null != string && !string.isBlank()){
                    alumno = dato.obtenerDato(string);
                    dato.escribirDato(alumno, folderpath+"/"+archivo+".txt");
                }
            }
            
            System.out.println("Información de iteraciones y archivo ordenado: "+folderpath);
            
            // Cierre de todos los lectores. 
            br_list.close();
            br_f1.close();
            br_f2.close();
            br_f3.close();
            br_f4.close();
            br_f5.close();
            br_f6.close();
            br_f7.close();
            br_f8.close();
            br_f9.close();
            br_f0.close();
        }
        
        
        
        
        /**
         * Método de ordenamiento de apellidos por RadixSort. 
         * @param archivo Archivo de texto a ordenar.
         * @throws IOException 
         */
        public void RadixSortA(String archivo) throws IOException {
            // Se crea la carpeta en la que se colocarán archivos creador durante el ordenamiento.
            RadixFiles rF = new RadixFiles(archivo);
            folderpath = rF.getPath();
            rF.directory();
            
            int i, maxLength = 0, n= 0;
            // Se crean los 27 archivos auxiliares que servirán como colas, y lista, así como sus buffer para leerlos. 
            BufferedReader br_fA = rF.files("fA",archivo);
            BufferedReader br_fB = rF.files("fB",archivo);
            BufferedReader br_fC = rF.files("fC",archivo);
            BufferedReader br_fD = rF.files("fD",archivo);
            BufferedReader br_fE = rF.files("fE",archivo);
            BufferedReader br_fF = rF.files("fF",archivo);
            BufferedReader br_fG = rF.files("fG",archivo);
            BufferedReader br_fH = rF.files("fH",archivo);
            BufferedReader br_fI = rF.files("fI",archivo);
            BufferedReader br_fJ = rF.files("fJ",archivo);
            BufferedReader br_fK = rF.files("fK",archivo);
            BufferedReader br_fL = rF.files("fL",archivo);
            BufferedReader br_fM = rF.files("fM",archivo);
            BufferedReader br_fN = rF.files("fN",archivo);
            BufferedReader br_fO = rF.files("fO",archivo);
            BufferedReader br_fP = rF.files("fP",archivo);
            BufferedReader br_fQ = rF.files("fQ",archivo);
            BufferedReader br_fR = rF.files("fR",archivo);
            BufferedReader br_fS = rF.files("fS",archivo);
            BufferedReader br_fT = rF.files("fT",archivo);
            BufferedReader br_fU = rF.files("fU",archivo);
            BufferedReader br_fV = rF.files("fV",archivo);
            BufferedReader br_fW = rF.files("fW",archivo);
            BufferedReader br_fX = rF.files("fX",archivo);
            BufferedReader br_fY = rF.files("fY",archivo);
            BufferedReader br_fZ = rF.files("fZ",archivo);
            BufferedReader br_list = rF.files("list",archivo);
            
            Dato dato = new Dato();
            Alumno alumno;
            String string;
            
            // Se abre el archivo a ordenar para leerlo.
            File file = new File(archivo);
            FileReader reader_file = new FileReader(file);
            BufferedReader br = new BufferedReader(reader_file);
            // Se leen todos sus elementos y se copian en list.txt.
            string = br.readLine();
            while(null != string && !string.isBlank() ){
                  alumno = dato.obtenerDato(string);
                  // Se encuentra longitud máxima de cadena.
                  maxLength = getMaxLength(alumno, maxLength,1);
                  dato.escribirDato(alumno, folderpath+"/list.txt");  
                  string = br.readLine();
                  // Número de elementos a ordenar. 
                  n++;
            }
            br.close();

            // Desde la longitud máxima hasta que se llegue a la mínima.
            for(int pos = maxLength-1 ; pos >= 0; pos--){
                
                // Se imprimen iteraciones en los archivos.
                for(char b = 'A'; b< 'Z';b++){
                    String a = String.valueOf("f"+b+".txt");
                    printIte(folderpath+"/f"+b+".txt",pos);
                }
                printIte(folderpath+"/list.txt",pos);
                
                // Se leen todos los elementos en la lista.
                    for(i = 0; i < n; i++){
                        string = br_list.readLine();
                        alumno = dato.obtenerDato(string);
                        // Si la cadena de apellido del alumno es lo suficientemente larga.
                        if(alumno.getApellido().length() > pos){
                            char charValue = (alumno.getApellido().toUpperCase().charAt(pos));
                            // Si no es una letra, se salta el carácter.
                            if(!Character.isLetter(charValue)){
                                charValue = (alumno.getApellido().toUpperCase().charAt(pos-1));
                            }
                            dato.escribirDato(alumno,folderpath+"/f"+ charValue +".txt");
                        }
                        // Si la cadena no es tan larga como la posición, se escribe al principio de la lista. 
                        else{
                            dato.escribirDato(alumno,folderpath+"/list.txt");
                        }
                        
                    }
                
                // Lee todos los archivos auxiliares y escribe su contenido en la lista. 
                readWrite(n,br_fA);
                readWrite(n,br_fB);
                readWrite(n,br_fC);
                readWrite(n,br_fD);
                readWrite(n,br_fE);
                readWrite(n,br_fF);
                readWrite(n,br_fG);
                readWrite(n,br_fH);
                readWrite(n,br_fI);
                readWrite(n,br_fJ);
                readWrite(n,br_fK);
                readWrite(n,br_fL);
                readWrite(n,br_fM);
                readWrite(n,br_fN);
                readWrite(n,br_fO);
                readWrite(n,br_fP);
                readWrite(n,br_fQ);
                readWrite(n,br_fR);
                readWrite(n,br_fS);
                readWrite(n,br_fT);
                readWrite(n,br_fU);
                readWrite(n,br_fV);
                readWrite(n,br_fW);
                readWrite(n,br_fX);
                readWrite(n,br_fY);
                readWrite(n,br_fZ);
                
                br_list.readLine();
                br_list.readLine();
            }
            
            // Se crea un archivo y se escriben todos los datos ordenados en él.  
            File file2 = rF.newFile(archivo);
            for(i = 0;i<n;i++){
                string = br_list.readLine();
                if(null != string && !string.isBlank()){
                    alumno = dato.obtenerDato(string);
                    dato.escribirDato(alumno, folderpath+"/"+archivo+".txt");
                }
            }
            
            System.out.println("Información de iteraciones y archivo ordenado: "+folderpath);
            
            // Se cierran todos los buffers de los archivos. 
            br_list.close();
            br_fA.close();
            br_fB.close();
            br_fC.close();
            br_fD.close();
            br_fE.close();
            br_fF.close();
            br_fG.close();
            br_fH.close();
            br_fI.close();
            br_fJ.close();
            br_fK.close();
            br_fL.close();
            br_fM.close();
            br_fN.close();
            br_fO.close();
            br_fP.close();
            br_fQ.close();
            br_fR.close();
            br_fS.close();
            br_fT.close();
            br_fU.close();
            br_fV.close();            
            br_fW.close();
            br_fX.close();
            br_fY.close();
            br_fZ.close();
 
        }
        
        /**
         * Método de ordenamiento de nombres por RadixSort. 
         * @param archivo Archivo de texto a ordenar.
         * @throws IOException 
         */
        public void RadixSortN(String archivo) throws IOException {
            // Se crea la carpeta en la que se colocarán archivos creador durante el ordenamiento.
            RadixFiles rF = new RadixFiles(archivo);
            folderpath = rF.getPath();
            rF.directory();
            
            int i, maxLength = 0, n= 0;
            // Se crean los 27 archivos auxiliares que servirán como colas, y lista, así como sus buffer para leerlos. 
            BufferedReader br_fA = rF.files("fA",archivo);
            BufferedReader br_fB = rF.files("fB",archivo);
            BufferedReader br_fC = rF.files("fC",archivo);
            BufferedReader br_fD = rF.files("fD",archivo);
            BufferedReader br_fE = rF.files("fE",archivo);
            BufferedReader br_fF = rF.files("fF",archivo);
            BufferedReader br_fG = rF.files("fG",archivo);
            BufferedReader br_fH = rF.files("fH",archivo);
            BufferedReader br_fI = rF.files("fI",archivo);
            BufferedReader br_fJ = rF.files("fJ",archivo);
            BufferedReader br_fK = rF.files("fK",archivo);
            BufferedReader br_fL = rF.files("fL",archivo);
            BufferedReader br_fM = rF.files("fM",archivo);
            BufferedReader br_fN = rF.files("fN",archivo);
            BufferedReader br_fO = rF.files("fO",archivo);
            BufferedReader br_fP = rF.files("fP",archivo);
            BufferedReader br_fQ = rF.files("fQ",archivo);
            BufferedReader br_fR = rF.files("fR",archivo);
            BufferedReader br_fS = rF.files("fS",archivo);
            BufferedReader br_fT = rF.files("fT",archivo);
            BufferedReader br_fU = rF.files("fU",archivo);
            BufferedReader br_fV = rF.files("fV",archivo);
            BufferedReader br_fW = rF.files("fW",archivo);
            BufferedReader br_fX = rF.files("fX",archivo);
            BufferedReader br_fY = rF.files("fY",archivo);
            BufferedReader br_fZ = rF.files("fZ",archivo);
            BufferedReader br_list = rF.files("list",archivo);
            
            Dato dato = new Dato();
            Alumno alumno;
            String string;
            
            // Se abre el archivo a ordenar para su lectura.
            File file = new File(archivo);
            FileReader reader_file = new FileReader(file);
            BufferedReader br = new BufferedReader(reader_file);
            
            // Se copian todos los datos en el archivo original a list.txt
            string = br.readLine();
            while(null != string && !string.isBlank() ){
                  alumno = dato.obtenerDato(string);
                  // máxima longitud de cadena.
                  maxLength = getMaxLength(alumno, maxLength,0);
                  dato.escribirDato(alumno, folderpath+"/list.txt");  
                  string = br.readLine();
                  // número de lineas leídas.
                  n++;
            }
            br.close();
            
            // Desde la longitud máxima hasta que se llegue a la mínima.
            for(int pos = maxLength-1 ; pos >= 0; pos--){
                
                // Se imprimen iteraciones en los archivos.
                for(char b = 'A'; b< 'Z';b++){
                    String a = String.valueOf("f"+b+".txt");
                    printIte(folderpath+"/f"+b+".txt",pos);
                }
                printIte(folderpath+"/list.txt",pos);
                
                // Se leen todos los elementos en la lista.
                    for(i = 0; i < n; i++){
                        string = br_list.readLine();
                        alumno = dato.obtenerDato(string);
                        // Si el nombre es lo suficientemente largo se coloca en la cola correspondiente.
                        if(alumno.getNombre().length() > pos){
                            char charValue = (alumno.getNombre().toUpperCase().charAt(pos));
                            // Si no es una letra, se salta el carácter.
                            if(!Character.isLetter(charValue)){
                                charValue = (alumno.getNombre().toUpperCase().charAt(pos-1));
                            }
                            dato.escribirDato(alumno,folderpath+"/f"+ charValue +".txt");
                        }
                        // Si no es lo suficientemente largo el nombre, se escribe al principio de la lista.
                        else{
                            dato.escribirDato(alumno,folderpath+"/list.txt");
                        }
                        
                    }
                
                // Se leen todos los elementos de cada uno de los archivos auxiliares de letras y se escriben en la lista. 
                readWrite(n,br_fA);
                readWrite(n,br_fB);
                readWrite(n,br_fC);
                readWrite(n,br_fD);
                readWrite(n,br_fE);
                readWrite(n,br_fF);
                readWrite(n,br_fG);
                readWrite(n,br_fH);
                readWrite(n,br_fI);
                readWrite(n,br_fJ);
                readWrite(n,br_fK);
                readWrite(n,br_fL);
                readWrite(n,br_fM);
                readWrite(n,br_fN);
                readWrite(n,br_fO);
                readWrite(n,br_fP);
                readWrite(n,br_fQ);
                readWrite(n,br_fR);
                readWrite(n,br_fS);
                readWrite(n,br_fT);
                readWrite(n,br_fU);
                readWrite(n,br_fV);
                readWrite(n,br_fW);
                readWrite(n,br_fX);
                readWrite(n,br_fY);
                readWrite(n,br_fZ);
                
                br_list.readLine();
                br_list.readLine();
            }
            
            // Se escriben los datos ordenados en un archivo de la carpeta con el mismo nombre que el original. 
            File file2 = rF.newFile(archivo);
            for(i = 0;i<n;i++){
                string = br_list.readLine();
                if(null != string && !string.isBlank()){
                    alumno = dato.obtenerDato(string);
                    dato.escribirDato(alumno, folderpath+"/"+archivo+".txt");
                }
            }
            
            System.out.println("Información de iteraciones y archivo ordenado: "+folderpath);
            
            // Se cierran todos los buffers. 
            br_list.close();
            br_fA.close();
            br_fB.close();
            br_fC.close();
            br_fD.close();
            br_fE.close();
            br_fF.close();
            br_fG.close();
            br_fH.close();
            br_fI.close();
            br_fJ.close();
            br_fK.close();
            br_fL.close();
            br_fM.close();
            br_fN.close();
            br_fO.close();
            br_fP.close();
            br_fQ.close();
            br_fR.close();
            br_fS.close();
            br_fT.close();
            br_fU.close();
            br_fV.close();            
            br_fW.close();
            br_fX.close();
            br_fY.close();
            br_fZ.close();
 
        }
        
        /**
         * Método que imprime la iteración indicada en un archivo.
         * @param archivoD Archivo en el que se quiere escribir.
         * @param ite Número de Iteración.
         * @throws IOException 
         */
        public void printIte(String archivoD, int ite) throws IOException{
            File archivo = new File(archivoD);     
            try (FileWriter escribir = new FileWriter(archivo,true)) {
            escribir.write("\n   *** ITERACION DE "+ite+"'s   ***   \n");
            }
        }
        
        /**
         * Método para leer lo que se encuentra en un archivo y escribirlo en la lista.
         * @param n Número máximo de líneas a leer.
         * @param read_file BufferedReader del archivo que se está leyendo.
         * @throws IOException 
         */
        public void readWrite(int n, BufferedReader read_file) throws IOException{
            Dato dato = new Dato();
            read_file.readLine();
            read_file.readLine();
            
            // Se leen todas las líneas del archivo y se copia su contenido a list.txt
            for(int i = 0; i<n;i++){
                String string = read_file.readLine();
                if(null != string && !string.isBlank()){
                    Alumno alumno = dato.obtenerDato(string);
                    dato.escribirDato(alumno, folderpath+"/list.txt");
                }
                else{
                    return;
                }
            }
        }   
    }
    

