import java.util.List;
public interface Retrievable<T> { // Generic interface for retrieving documents from database that implements this interface (HandlerClasses)
    List<T> getDocsAndSend();
}
