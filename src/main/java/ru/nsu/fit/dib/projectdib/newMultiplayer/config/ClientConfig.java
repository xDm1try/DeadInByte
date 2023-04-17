package ru.nsu.fit.dib.projectdib.newMultiplayer.config;

import java.net.Socket;
import ru.nsu.fit.dib.projectdib.newMultiplayer.ClientState;
import ru.nsu.fit.dib.projectdib.newMultiplayer.threads.ClientReceiverThread;
import ru.nsu.fit.dib.projectdib.newMultiplayer.threads.ClientSenderThread;
import ru.nsu.fit.dib.projectdib.newMultiplayer.context.client.EMCClient;
import ru.nsu.fit.dib.projectdib.newMultiplayer.context.client.MCClient;

public final class ClientConfig {

  private ClientConfig() {
    throw new UnsupportedOperationException();
  }

  public static void init() {
    MCClient.addBean(EMCClient.CLIENT_SOCKET, new Socket());
    MCClient.addBean(EMCClient.CLIENT_STATE, new ClientState());
  }

  public static void addClientId(Integer id) {
    MCClient.addBean(EMCClient.CLIENT_ID, id);
  }

  public static void addClientSenderThread(ClientSenderThread thread) {
    MCClient.addBean(EMCClient.CLIENT_SENDER_THREAD, thread);
  }

  public static void addClientReceiverThread(ClientReceiverThread thread) {
    MCClient.addBean(EMCClient.CLIENT_RECEIVER_THREAD, thread);
  }
}
