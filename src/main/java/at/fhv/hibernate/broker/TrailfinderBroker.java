package at.fhv.hibernate.broker;
import java.util.List;

public interface TrailfinderBroker<T> {
    public interface Broker<T> {
        void insert(T value);
        void delete(T value);
        List<T> getAll();
        T getById(String id);
    }
}
