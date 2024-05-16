package com.example.todolistapp;
import java.sql.*;

public class ImplTodoList implements TodoList{
    private Connection connection;
    public ImplTodoList(Connection connection){
        this.connection = connection;
    }
    @Override
    public void createTask(Task task) throws SQLException {
      String sql = "Insert into tasks (name,duedate,completed) values (?,?,?)";
      try (PreparedStatement statement = connection.prepareStatement(sql)) {
          statement.setString(1, task.getName());
          statement.setDate(2, new java.sql.Date(task.getDueDate().getTime()));
          statement.setBoolean(3, task.isCompleted());
          statement.executeUpdate();
      }catch (SQLException e){
          e.printStackTrace();
      }
    }
    @Override
    public Task readTask(int id) {
        String sql = "Select * from tasks where id = ?";
        Task task = null;
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                task=new Task(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDate("duedate"),
                        resultSet.getBoolean("completed")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return task;
    }


    @Override
    public void updateTask(Task task) throws SQLException {
    String sql = "UPDATE tasks SET name = ?, description = ?, due_date =?, completed = ? WHERE id = ?";
    try(PreparedStatement statement =connection.prepareStatement(sql)){
        statement.setInt(1, task.getId());
        statement.setString(2,task.getName());
        statement.setDate(3,task.getDueDate());
        statement.setBoolean(4,task.isCompleted());
        statement.executeUpdate();
    }catch (SQLException e){
        e.printStackTrace();
    }
    }
    @Override
    public void deleteTask(int id) {
        String sql = "DELETE FROM tasks WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
        }
    }
}
