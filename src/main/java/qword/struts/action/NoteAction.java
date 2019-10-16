package qword.struts.action;

import static java.lang.Long.parseLong;
import static java.util.Comparator.*;
import static java.util.logging.Level.INFO;
import static java.util.logging.Level.SEVERE;
import static java.util.stream.Collectors.toList;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import qword.struts.domain.Note;
import qword.struts.form.NoteForm;

public class NoteAction extends DispatchAction {

  public static final String NOTES_LIST_ATTRIBUTE = "notesList";
  public static final String SINGLE_NOTE_ATTRIBUTE = "singleNote";

  private static long i = 1;

  private static final Logger LOGGER = Logger.getLogger(NoteAction.class.getCanonicalName());

  /**
   * Temporary solution, a proper repository will be implemented next
   */
  private final List<Note> notes = new ArrayList<>();


  public ActionForward all(final ActionMapping mapping, final ActionForm form,
      final HttpServletRequest request, final HttpServletResponse response) {
    LOGGER.log(INFO, "Action: display all Notes");

    final List<Note> sortedNotes = notes.stream().sorted(comparing(Note::getId)).collect(toList());
    request.setAttribute(NOTES_LIST_ATTRIBUTE, sortedNotes);
    return mapping.findForward("showAll");
  }

  public ActionForward single(final ActionMapping mapping, final ActionForm form,
      final HttpServletRequest request, final HttpServletResponse response) {
    LOGGER.log(INFO, "Action: display one single note");

    long id = getIdFromRequest(request);
    final Note note = notes.stream()
        .filter(n -> n.getId().equals(id))
        .findFirst()
        .orElse(new Note());
    try {
      BeanUtils.copyProperties(form, note);
    } catch (ReflectiveOperationException e) {
      LOGGER.log(SEVERE, "Unable to populate form", e);
    }
    request.setAttribute(SINGLE_NOTE_ATTRIBUTE, note);

    return mapping.findForward("showSingle");
  }

  public ActionForward createOrUpdate(final ActionMapping mapping, final ActionForm form,
      final HttpServletRequest request, final HttpServletResponse response) {
    LOGGER.log(INFO, "Action: create or update single note");

    final Note note = new Note();
    final NoteForm noteForm = (NoteForm)form;

    try {
      BeanUtils.copyProperties(note, noteForm);
      if (note.getId().equals(0L)) {
        // Creating new note
        note.setCreated(LocalDateTime.now());
        note.setId(i++);
      } else {
        note.setUpdated(LocalDateTime.now());

        // remove previous version
        notes.stream()
            .filter(n -> n.getId().equals(note.getId()))
            .findFirst()
            .ifPresent(existingNote -> {
              notes.remove(existingNote);
              note.setCreated(existingNote.getCreated());
            });
      }
      notes.add(note);
    } catch (ReflectiveOperationException e) {
      LOGGER.log(SEVERE, "Unable to create Note from a form", e);
    }

    request.setAttribute("id", note.getId());
    return mapping.findForward("successCreateOrUpdate");
  }

  public ActionForward delete(final ActionMapping mapping, final ActionForm form,
      final HttpServletRequest request, final HttpServletResponse response) {
    LOGGER.log(INFO, "Action: delete one single note");

    long id = getIdFromRequest(request);
    notes.stream()
        .filter(n -> n.getId().equals(id))
        .findFirst()
        .ifPresent(notes::remove);

    return mapping.findForward("successDelete");
  }

  private long getIdFromRequest(HttpServletRequest request) {
    final String idStr = request.getParameter("id");
    if (idStr != null) {
      return parseLong(idStr);
    }
    return 0L;
  }
}
