package com.feisty.javaDiscordTest;

import com.feisty.javaDiscordTest.FileReader;
import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import reactor.core.publisher.Mono;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.object.entity.User;
import java.io.FileNotFoundException;

public class FeistyBot {
    public DiscordClient client;
    public Mono login;

    public FeistyBot(String authFileName, int tokenLine) throws FileNotFoundException {
        String auth = (String) FileReader.readToArrayList(authFileName).toArray()[tokenLine];
        this.client = DiscordClient.create(auth);
        login = client.withGateway((GatewayDiscordClient gateway) ->
            gateway.on(ReadyEvent.class, event ->
                        Mono.fromRunnable(() -> {
                                final User self = event.getSelf();
                                System.out.printf("Logged in as %s#%s%n", self.getUsername(), self.getDiscriminator());
                        }
        )));
    }
}
