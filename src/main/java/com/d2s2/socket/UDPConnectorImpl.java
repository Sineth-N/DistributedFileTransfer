package com.d2s2.socket;

import com.d2s2.message.tokenize.MessageTokenizerImpl;
import com.d2s2.models.RegistrationRequestModel;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Heshan Sandamal on 10/6/2017.
 */
public class UDPConnectorImpl implements UdpConnector {

    @Override
    public void send(RegistrationRequestModel message, DatagramSocket datagramSocket) throws IOException {
        byte[] buffer = message.toString().getBytes();
        InetAddress receiverAddress = InetAddress.getLocalHost();
        DatagramPacket packet = new DatagramPacket(
                buffer, buffer.length, receiverAddress, 55555);
        datagramSocket.send(packet);
    }

    /*
    Each request that comes to the node will be handed off to a separate async thread
    via the executor service.
     */
    @Override
    public void receive(DatagramSocket socket) throws IOException {
        byte[] bufferIncoming = new byte[100];
        DatagramPacket incomingPacket = new DatagramPacket(bufferIncoming, bufferIncoming.length);
        socket.receive(incomingPacket);
        String incomingMessage = new String(bufferIncoming);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(() -> {
            MessageTokenizerImpl tokenizer = new MessageTokenizerImpl();
            tokenizer.tokenizeMessage(incomingMessage);
        });
        executorService.shutdown(); // To keep the client alive comment out this line when necessary
    }
}
