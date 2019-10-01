package discordbot;

import listener.RunchListener;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;

public class MainApp {
	public static void main(String[] args) {
		JDABuilder builder = new JDABuilder(AccountType.BOT);
		String token = "";
		
		builder.setToken(token);
		
		try {
			builder.addEventListeners(new RunchListener());
			builder.build();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
