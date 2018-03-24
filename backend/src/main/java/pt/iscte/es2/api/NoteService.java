package pt.iscte.es2.api;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

public interface NoteService {

    @GetMapping("/hello")
    void hello();
}
