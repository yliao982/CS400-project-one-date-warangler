// --== CS400 File Header Information ==--
// Name: Yiting Liao
// Email: liao52@wisc.edu
// Team: JF
// Role: Data Wrangler
// TA: Harper
// Lecturer: CS 400 002
// Notes to Grader: <optional extra notes>
import java.util.NoSuchElementException;
import java.util.LinkedList;


public class LinkeNode<BookName, BookYear> implements MapADT<BookName, BookYear> {
  private int bookTable = 0;
  private int capacity;
  private LinkedList<Element<BookName, BookYear>>[] theBook;

  /**
   * 
   * this is the constructor for the class
   * 
   * @param
   */
  public LinkeNode(int capacity) {
    this.capacity = capacity;
    theBook = new LinkedList[capacity];
  }

  /**
   * this is the constructor for the class
   */
  public LinkeNode() {
    this.capacity = 11;
    theBook = new LinkedList[11];
  }

  /**
   * 
   * 
   * This class is to define the element
   * 
   * @param <BookName>
   * @param <BookYear>
   */
  public static class Element<BookName, BookYear> {
    private BookName name;
    private BookYear year;
    private int hashkey;

    private Element(BookName book, BookYear year) {
      this.hashkey = book.hashCode();
      this.name = book;
      this.year = year;

    }

    private BookName getKey() {
      return this.name;
    }

    private BookYear getValue() {
      return year;
    }
  }

  /**
   * this method here is to resize the table
   * 
   * @return to the valueSize
   */
  private LinkedList<Element<BookName, BookYear>>[] resize(
      LinkedList<Element<BookName, BookYear>>[] preValue, int currSize) {
    int newSize = currSize * 2;
    bookTable = 0;
    this.theBook = new LinkedList[newSize];
    for (int i = 0; i < currSize; ++i) {
      if (preValue[i] != null) {
        for (int j = 0; j < preValue[i].size(); ++j) {
          put(preValue[i].get(j).getKey(), preValue[i].get(j).getValue());
        }
      }
    }
    return theBook;
  }

  /**
   * the method here is to insert the provide pair key value grow by doubling and rehashing,
   * whenever its load capacity becomes greater than or equal to 80%
   * 
   * @param bookname the key input in the array
   * @param year     corresponding value input
   * @return true if the input is success inserted
   */
  @Override
  public boolean put(BookName bookname, BookYear year) {
    Element<BookName, BookYear> elements = new Element(bookname, year);
    int idValue = Math.abs(bookname.hashCode()) % capacity;
    boolean wrong = false;
    if (containsKey(bookname)) {
      return wrong;
    }
    if (theBook[idValue] == null) {
      theBook[idValue] = new LinkedList();
    }
    theBook[idValue].add(elements);
    bookTable++;
    if (bookTable >= 0.8 * theBook.length) {
      theBook = resize(theBook, theBook.length);
    }
    return true;
  }

  /**
   * the method here to get the key value
   * 
   * @param name the key input in the array
   * @return valueType
   * @throws NoSuchElementException when the key doesn't exist in the array throw this exception
   */
  @Override
  public BookYear get(BookName name) throws NoSuchElementException {
    int idValue = Math.abs(name.hashCode()) % theBook.length;
    if (!containsKey(name)) {
      throw new NoSuchElementException("There is no such key in the table.");
    }
    if (theBook[idValue] != null) {
      for (int i = 0; i < theBook[idValue].size(); ++i) {
        if (theBook[idValue].get(i).getKey().equals(name)) {
          return theBook[idValue].get(i).getValue();
        }
      }

    }
    throw new NoSuchElementException();

  }

  /**
   * this method here is to return to the value in table value.
   * 
   * @return to table value
   */
  @Override
  public int size() {
    return bookTable;
  }

  /**
   * the method here to check if the array contains the key value
   * 
   * @param name the key input in the array
   * @return true if the vale exist
   */
  @Override
  public boolean containsKey(BookName name) {
    int idValue = Math.abs(name.hashCode()) % theBook.length;
    boolean wrong = false;
    if (theBook[idValue] == null) {
      return wrong;
    }
    if (theBook[idValue] != null) {
      for (int i = 0; i < theBook[idValue].size(); ++i) {
        if (theBook[idValue].get(i).getKey().equals(name)) {
          return true;
        }
      }
    }
    return false;

  }

  /**
   * The method here to removed the name value if the value not exist return to null
   * 
   * @param name the input value to be removed
   * @return valueType removed value
   */
  @Override
  public BookYear remove(BookName name) {
    int idValue = Math.abs(name.hashCode()) % capacity;
    if (this.theBook[idValue] == null) {
      return null;
    } else {
      for (int i = 0; i < theBook[idValue].size(); ++i) {
        if (theBook[idValue].get(i).getKey().equals(name)) {
          BookYear remove = theBook[idValue].get(i).getValue();
          theBook[idValue].remove(i);
          bookTable--;
          return remove;
        }
      }
    }
    return null;
  }

  /**
   * The method that clear all the array
   */
  @Override
  public void clear() {
    for (int i = 0; i < theBook.length; ++i) {
      theBook[i] = null;
    }
    bookTable = 0;
  }

}
