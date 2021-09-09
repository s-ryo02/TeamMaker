package Main;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import static Main.token.TOKEN;

public class Bot {
    public static void main(String[] args)
            throws Exception{
        JDA jda = JDABuilder.createDefault(TOKEN)
                .addEventListeners(new Main())
                .addEventListeners(new ChannelManager())
                .build();
    }
}
