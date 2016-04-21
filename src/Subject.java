public interface Subject {
	void attach(Observer o);
	void notifyObservers();
}