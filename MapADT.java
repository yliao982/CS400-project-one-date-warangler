// --== CS400 File Header Information ==--
// Name: Yiting Liao
// Email: liao52@wisc.edu
// Team: JF
// Role: Data Wrangler
// TA: Harper
// Lecturer: CS 400 002
// Notes to Grader: <optional extra notes>
import java.util.NoSuchElementException;

public interface MapADT<KeyType,ValueType> {
    public boolean put(KeyType key, ValueType value);
    public ValueType get(KeyType key) throws NoSuchElementException;
    public int size();
    public boolean containsKey(KeyType key);
    public ValueType remove(KeyType key);
    public void clear();
}
