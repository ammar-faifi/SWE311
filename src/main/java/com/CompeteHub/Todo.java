import org.springframework.data.annotation.Id;

public class Todo {

  public Todo() {}

  public Todo(String description, String details, boolean done) {
    this.description = description;
    this.details = details;
    this.done = done;
  }

  @Id private Long id;

  private String description;

  private String details;

  private boolean done;
}
