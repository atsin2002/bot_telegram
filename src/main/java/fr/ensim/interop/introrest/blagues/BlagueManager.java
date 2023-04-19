package fr.ensim.interop.introrest.blagues;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
public class BlagueManager {
    private Map<Integer, String> blagues;

    public BlagueManager() {
        blagues = new HashMap<>();
        blagues.put(1, "Pourquoi les plongeurs plongent-ils toujours en arrière et jamais en avant ? Parce que sinon ils tombent dans le bateau.");
        blagues.put(2, "Pourquoi les plongeurs plongent-ils toujours avec un masque et un tuba ? Parce que s'ils prennent l'avion, ils peuvent le transformer en canot de sauvetage.");
        blagues.put(3, "Qu'est-ce qui est petit, carré et bleu ? Un petit carré bleu.");
        blagues.put(4, "Comment appelle-t-on un chat qui a quatre pattes et un bras ? Un chat heureux.");
        blagues.put(5, "Comment appelle-t-on un chat qui a huit pattes ? Un chat octopatte.");

    }

    @GetMapping("/joke")
    public String getBlagueAleatoire() {
        Random rand = new Random();
        int randomIndex = rand.nextInt(blagues.size()) + 1;
        return blagues.get(randomIndex);
    }
}