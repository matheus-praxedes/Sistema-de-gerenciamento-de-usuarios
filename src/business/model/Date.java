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

public class Date implements Serializable{

    private int day;
    private int month; 
    private int year;

    public Date(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }

    @Override
    public String toString(){
        return day + "/" + month + "/" + year;
    } 
}