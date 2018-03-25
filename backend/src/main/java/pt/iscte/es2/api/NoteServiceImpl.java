package pt.iscte.es2.api;

import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class NoteServiceImpl implements NoteService {

    @Override
    public void hello() {
        System.out.println("Hello There...!!!!!!!!");
    }

}
