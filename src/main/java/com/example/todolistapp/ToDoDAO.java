import java.util.ArrayList;
import java.util.List;

public class ToDoDAO {
	private List<String> toDoList = new ArrayList<>();

	public void addTask(String task) {
		toDoList.add(task);
	}

	public void removeTask(String task) {
		toDoList.remove(task);
	}

	
}
