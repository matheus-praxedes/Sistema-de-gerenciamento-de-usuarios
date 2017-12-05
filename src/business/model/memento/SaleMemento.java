// Padrão Memento
package business.model.memento;

public class SaleMemento{

    private SaleState state;

    public SaleMemento(SaleState new_state){
        state = new_state;
    }

    public SaleState getState(){
        return state;
    }

    public void setState(SaleState new_state){
        state = new_state;
    }
}