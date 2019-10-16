package qword.struts.form;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.struts.action.ActionForm;

@Data
@EqualsAndHashCode(callSuper = false)
public class NoteForm extends ActionForm {
  private Long id;
  private String title;
  private String description;
}
