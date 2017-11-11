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

public class Product implements Serializable{

    private String name;
    private float value;

    public Product(String name, float value){
        this.name = name;
        this.value = value;
    }

    public String getName(){
        return name;
    }

    public float getValue(){
        return value;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setValue(float value){
        this.value = value;
    }

}