package qword.struts.form;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Note {
  private Long id;
  private String title;
  private String description;
  private LocalDateTime created;
  private LocalDateTime updated;
}
