package fr.ensim.interop.introrest;

import fr.ensim.interop.introrest.blagues.BlagueManager;
import fr.ensim.interop.introrest.controller.FilmRatingController;
import fr.ensim.interop.introrest.controller.WeatherStackController;
import fr.ensim.interop.introrest.model.telegram.ApiResponseUpdateTelegram;
import fr.ensim.interop.introrest.model.telegram.Update;
import model.openweather.WeatherStackException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import fr.ensim.interop.introrest.controller.MessageRestController;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



@Component
public class ListenerUpdateTelegram implements CommandLineRunner {
	MessageRestController controler = new MessageRestController();
	String startText = "Bonjour à tous ! Vous pouvez me poser des questions sur la météo ou me demander une blague. . Il faut offre plusieurs fonctionnalité \n 1)Connaitre la meteo la méteo d'un ville en ecrivant meteo <nom de ville> \n 2)Si vous vous ennuyez ecrivrer blague pour rigoler un peu \n 3) Connaitre le synopsis d'un film en écrivant film <nom du film>";
	BlagueManager blagues = new BlagueManager();

	FilmRatingController filmcontroller = new FilmRatingController();

	String errorCommand="Je ne reconnais pas cette commande";
	WeatherStackController weatherserv = new WeatherStackController();
	@Override
	public void run(String... args) throws Exception {


		Logger.getLogger("ListenerUpdateTelegram").log(Level.INFO, "Démarage du listener d'updates Telegram...");
		controler.sendMessage("2020129742",startText);
		int offset = 0;
		while (true) {
			String apiUrl = "https://api.telegram.org/bot" + "6002365819:AAEDNFLH1_M-khwWCHH8ZoOXkXs-AuQ2Wu0" + "/getUpdates?offset=" + offset;
			RestTemplate restTemplate = new RestTemplate();
			ApiResponseUpdateTelegram response = restTemplate.getForObject(apiUrl, ApiResponseUpdateTelegram.class);
			List<Update> updates = response.getResult();
			for (Update update : updates) {
				if (update.getMessage() != null && update.getMessage().getText() != null) {
					String messageText = update.getMessage().getText();
					String chatId = update.getMessage().getChat().getId().toString();
					if (messageText.toLowerCase().contains("meteo")) {
						String[] tokens = messageText.split(" ");
						if (tokens.length > 1) {
							String ville = String.join(" ", Arrays.copyOfRange(tokens, 1, tokens.length));
							try {
								String weather = weatherserv.getWeather(ville);
								controler.sendMessage(chatId, weather);
							} catch (WeatherStackException e) {
								controler.sendMessage(chatId, "Impossible de trouver la météo pour " + ville + ": " + e.getMessage());
							}
						}
					} else if (messageText.toLowerCase().contains("blague")) {
						String joke = blagues.getBlagueAleatoire();
						controler.sendMessage(chatId, joke);
					} else if (messageText.toLowerCase().contains("film")) {
						String[] tokens = messageText.split(" ");
						if (tokens.length > 1) {
							String nom_film = String.join(" ", Arrays.copyOfRange(tokens, 1, tokens.length));
							String plot = filmcontroller.getPlot(nom_film);
							String urlPoster = filmcontroller.getPosterUrl(nom_film);
							controler.sendMessage("2020129742",urlPoster);
							controler.sendMessage("2020129742",plot);

						}



					} else {
						controler.sendMessage("2020129742",errorCommand);
						controler.sendMessage("2020129742",startText);
					}
					offset = update.getUpdateId() + 1;
				}
			}
			try {
				Thread.sleep(1000); // wait 1 second before polling again
			} catch (InterruptedException e) {
				e.printStackTrace();

				// Operation de pooling pour capter les evenements Telegram
			}
		}
	}
}




