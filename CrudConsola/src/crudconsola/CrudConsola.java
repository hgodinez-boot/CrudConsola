/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package crudconsola;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
/**
 *
 * @author Usuario
 */
public class CrudConsola {

   PreparedStatement ps;
   ResultSet rs;
   Connection con;
   Conexion conectar = new Conexion();
   
   public void menu(){
       while (true){
           Scanner sp = new Scanner(System.in);
           Scanner sp2 = new Scanner(System.in);
           
           System.out.println("---------------------------------");
           System.out.println("|        MENU PRINCIPAL          |");
           System.out.println("|    1 | LISTAR                  |");
           System.out.println("|    2 | INSERTAR                |");
           System.out.println("|    3 | ACTUALIZAR              |");
           System.out.println("|    4 | ELIMINAR                |");
           System.out.println("|    5 | SALIR                   |");
           System.out.println("---------------------------------");
           System.out.println("Seleccione una de las opciones --->");
           int respuesta = sp.nextInt();
           
           switch (respuesta){
               case 1:
                      listar();
                   
                   break;
                case 2:
                      System.out.println("INGRESE EL CODIGO: ");
                      int codigo = sp.nextInt();
                      System.out.println("INGRESE EL NOMBRE: ");
                      String nombre = sp2.nextLine();
                      System.out.println("INGRESE LA DIRECCION: ");
                      String direccion = sp2.nextLine();
                      System.out.println("INGRESE EL TELEFONO: ");
                      int telefono = sp2.nextInt();
                      crear(codigo,nombre,direccion,telefono);
                   break;
                case 3:
                   System.out.println("INGRESE EL CODIGO: ");
                      int cod = sp.nextInt();
                      System.out.println("INGRESE EL NOMBRE: ");
                      String nom = sp2.nextLine();
                      System.out.println("INGRESE LA DIRECCION: ");
                      String dire = sp2.nextLine();
                      System.out.println("INGRESE EL TELEFONO: ");
                      int tel = sp2.nextInt();
                      modificar(cod,nom,dire,tel);
                   break;
                case 4:
                       System.out.println("INGRESE EL CODIGO A ELIMINAR --->");
                       int id = sp.nextInt();
                       eliminar(id);
                   break;
                case 5:
                    System.exit(0);
                   
                   break;
                default:
                    throw new AssertionError();
                  
           }
           
                      
           
       }
   }
   
   public void listar(){
       String instruccion = "select * from alumno";
       try{
           con = conectar.Conectar();
           ps = con.prepareStatement(instruccion);
           rs = ps.executeQuery();
           
           while(rs.next()){
               System.out.println(rs.getInt(1));
               System.out.println(rs.getString(2));
               System.out.println(rs.getString(3));
               System.out.println(rs.getInt(4));
               System.out.println("-----------------------");
           }
       } catch(Exception e){
           
       }
   }
   
   public void crear(int codigo, String nombre, String direccion, int telefono){
       String sql ="insert into alumno(codigo, nombre, direccion, telefono) values (?,?,?,?)";
       
       try{
           con = conectar.Conectar();
           ps = con.prepareStatement(sql);
           ps.setInt(1, codigo);
           ps.setString(2, nombre);
           ps.setString(3, direccion);
           ps.setInt(4, telefono);
           ps.executeUpdate();
           
       }catch(Exception e){
           
       }
   }
   
   public void modificar(int codigo, String nombre, String direccion, int telefono){

    String sql = "update alumno set nombre='" + nombre + "', direccion='" + direccion + "', telefono='" + telefono + "' where codigo ='" + codigo + "'";
        try {
            con = conectar.Conectar();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }


    }
   
   public void eliminar(int id){
        String sql = "delete from alumno where codigo = ?";
        try {

            con = conectar.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }


    }

   
   public static void main(String[] args){
        CrudConsola cc = new CrudConsola();
        cc.menu();
    }
    
}



