package pt.iscte.es2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.iscte.es2.ApplicationConstants;
import pt.iscte.es2.api.NoteService;
import pt.iscte.es2.dao.NoteDao;
import pt.iscte.es2.exception.ResourceNotFoundException;
import pt.iscte.es2.jpa.NoteEntity;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(ApplicationConstants.APPLICATION_PATH)
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private NoteDao noteDao;

//    @RequestMapping("/notes")
//    private void helloNotes() {
//        noteService.hello();
//    }

    @GetMapping("/hello")
    public void hello() {
        System.out.println("Hello there!!!!");
    }

    // Get All Notes
    @GetMapping("/notes")
    public List<NoteEntity> getAllNotes() {
        return noteDao.findAll();
    }

    // Create a New Note
    @PostMapping("/notes")
    public NoteEntity createNote(@Valid @RequestBody NoteEntity note) {
        return noteDao.save(note);
    }

    // Get a Single Note
    @GetMapping("/notes/{id}")
    public NoteEntity getNoteById(@PathVariable(value = "id") Long noteId) {
        return noteDao.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId.toString()));
    }

    // Update a Note
    @PutMapping("/notes/{id}")
    public NoteEntity updateNote(@PathVariable(value = "id") Long noteId, @Valid @RequestBody NoteEntity noteDetails) {
        NoteEntity note = noteDao.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId.toString()));

        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());

        NoteEntity updatedNote = noteDao.save(note);
        return updatedNote;
    }

    // Delete a Note
    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
        NoteEntity note = noteDao.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId.toString()));

        noteDao.delete(note);

        return ResponseEntity.ok().build();
    }

}
