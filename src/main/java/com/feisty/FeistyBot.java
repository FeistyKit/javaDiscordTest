package com.feisty.javaDiscordTest;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.object.entity.User;
import java.io.FileNotFoundException;
import reactor.core.publisher.Mono;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;

public class FeistyBot {
  public DiscordClient client;
  public Mono login;

  public FeistyBot(String authFileName, int tokenLine) throws FileNotFoundException {
    String auth = (String) FileReader.readToArrayList(authFileName).toArray()[tokenLine];
    this.client = DiscordClient.create(auth);
    login =
        client.withGateway(
            (GatewayDiscordClient gateway) -> {
              Mono<Void> printOnLogin =
                  gateway
                      .on(
                          ReadyEvent.class,
                          event ->
                              Mono.fromRunnable(
                                  () -> {
                                    final User self = event.getSelf();
                                    System.out.printf(
                                        "Logged in as %s#%s%n",
                                        self.getUsername(), self.getDiscriminator());
                                  }))
                      .then();
              // MessageCreateEvent example
              Mono<Void> handlePingCommand =
                  gateway
                      .on(
                          MessageCreateEvent.class,
                          event -> {
                            Message message = event.getMessage();
                            if (message.getContent().equalsIgnoreCase("!ping")) {
                              return message
                                  .getChannel()
                                  .flatMap(channel -> channel.createMessage("pong!"));
                            }
                            return Mono.empty();
                          })
                      .then();
              // combine them!
              return printOnLogin.and(handlePingCommand);
            });
  }
}
