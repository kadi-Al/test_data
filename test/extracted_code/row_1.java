import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MessageController {
    private final InMemoryMessageRepository repository = new InMemoryMessageRepository();

    @PostMapping("/post")
    public String submitForm(@RequestParam String username, @RequestParam String content) {
        Message message = new Message(username, content);
        repository.save(message);
        return "redirect:/"; // Redirect to the main page after submission
    }

    @GetMapping("/")
    public String viewMessages(Model model) {
        List<Message> messages = repository.findAll();
        model.addAttribute("messages", messages);
        return "index"; // This should map to a Thymeleaf template or another view technology
    }
}


public class InMemoryMessageRepository {
    private List<Message> messages = new ArrayList<>();

    public void save(Message message) {
        messages.add(message);
    }

    public List<Message> findAll() {
        return messages;
    }
}


public class Message {
    private String username;
    private String content;

    // Getters and Setters
}
