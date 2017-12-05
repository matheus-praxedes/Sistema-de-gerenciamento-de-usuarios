// Padrão Iterator
package business.model;

public interface IteratorInterface{

    public void first();
    public void next();
    public boolean isDone();
    public Product currentItemKey();
    public Integer currentItemValue();
}