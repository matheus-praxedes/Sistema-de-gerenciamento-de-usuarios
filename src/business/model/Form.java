/****************************************************************************
 * Métodos de Projeto de Software - 2017.1
 * Alunos: Francisco Matheus Gonçalves de Souza - 11403723 
 *         Fabricio Leita Soares - 11311014
 *         Matheus Maranhão Rêgo Praxedes - 11403744
 * 
 * Data de entrega: ?? de novembro de 2017
 * 
 ****************************************************************************/
package business.model;

import java.io.Serializable;

public abstract class Form implements Serializable{

    public final void print(){
        printHeader();
        System.out.println("---------------------------------");
        printData();
        System.out.println("---------------------------------");
        printEnd();
    }

    public abstract void printHeader();

    public abstract void printData();

    public abstract void printEnd();
}