/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Producto.Rubro;

import java.util.Scanner;

/**
 *
 * @author Enzo
 */
public class Producto implements Comparable<Producto>{
    private int codigo;
    private String descripcion;
    private double precio;
    private int stock;
    private Rubro rubro;
    

    public Producto() {
    }

    public Producto(int codigo, String descripcion, double precio, int stock, Rubro rubro) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.rubro = rubro;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Rubro getRubro() {
        return rubro;
    }

    public void setRubro(Rubro rubro) {
        this.rubro = rubro;
    }

//    public void rubrosPermitidos(String rubro) {
//        Scanner ingreso = new Scanner(System.in);
//        while (!rubro.contains("comestible") && !rubro.contains("limpieza") && !rubro.contains("perfumeria")) {
//            System.out.println("ERROR! Ingresar rubros permitidos: ");
//            rubro = ingreso.nextLine();
//        }
//        System.out.println("Rubro permitido!");
//    }

    @Override
    public int compareTo(Producto p2){
        if(this.codigo == p2.codigo){
            return 0;
        }else if(this.codigo > codigo){
            return 1;
        }else{
            return -1;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Producto{");
        sb.append("codigo=").append(codigo);
        sb.append(", descripcion=").append(descripcion);
        sb.append(", precio=").append(precio);
        sb.append(", stock=").append(stock);
        sb.append(", rubro=").append(rubro);
        sb.append('}');
        return sb.toString();
    }
    

}
