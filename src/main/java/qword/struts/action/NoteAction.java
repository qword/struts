package qword.struts.action;

import static java.util.logging.Level.INFO;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import qword.struts.form.Note;

public class NoteAction extends DispatchAction {
  public static final String NOTES_LIST_ATTRIBUTE = "notesList";

  private static final Logger LOGGER = Logger.getLogger(NoteAction.class.getCanonicalName());

  /**
   * Temporary solution, a proper repository will be implemented next
   */
  private final List<Note> notes = new ArrayList<>();


  public ActionForward all(final ActionMapping mapping, final ActionForm form,
      final HttpServletRequest request, final HttpServletResponse response) {
    LOGGER.log(INFO, "Action: display all Notes");

    request.setAttribute(NOTES_LIST_ATTRIBUTE, notes);
    return mapping.findForward("showAll");
  }
}
