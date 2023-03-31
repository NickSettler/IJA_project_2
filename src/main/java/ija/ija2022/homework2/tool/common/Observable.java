package ija.ija2022.homework2.tool.common;

public interface Observable {
    public static interface Observer {
        public void update(Observable o);
    }

    public void addObserver(Observer o);

    public void notifyObservers();

    public void removeObserver(Observer o);
}
