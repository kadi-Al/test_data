import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.*;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    // Getters and setters
}
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.xml.xpath.XPathExpressionException;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;

    // Endpoint to filter items based on XPath query
    @GetMapping("/filter")
    public ResponseEntity<?> filterItems(@RequestParam String xpath) throws XPathExpressionException {
        List<Item> items = itemRepository.findAll(Sort.by("id"));
        Object result = evaluateXPath(items, xpath);
        return ResponseEntity.ok(result);
    }

    private Object evaluateXPath(List<Item> items, String xpath) throws XPathExpressionException {
        // Implement your logic to evaluate the XPath query on the list of items
        // For demonstration purposes, we will just return the entire list as XML
        // In a real-world application, you would parse and transform this data based on the XPath results
        return items;
    }
}
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private ItemRepository itemRepository;

    @Override
    public void run(String... args) throws Exception {
        // Add some initial items to the database
        itemRepository.save(new Item("Item1", "Description for Item 1"));
        itemRepository.save(new Item("Item2", "Description for Item 2"));
    }
}