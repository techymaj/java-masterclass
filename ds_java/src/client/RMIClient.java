package client;

import server.SequencerImpl;
import shared.Broadcast;
import shared.Sequencer;
import shared.SequencerJoinInfo;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import static client.Group.*;

public class RMIClient implements Broadcast {

    private Sequencer sequencer;
    private final Scanner scanner;
    private final SequencerImpl si;
    private final Scanner askMissing;
    private final Scanner groupScanner;

    public RMIClient() throws RemoteException {
        si = new SequencerImpl();
        scanner = new Scanner(System.in);
        askMissing = new Scanner(System.in);
        groupScanner = new Scanner(System.in);
    }

    public void start() throws RemoteException, NotBoundException {
        // start the client
        // connect to the serer
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        // get the sequencer from the server (the remote object on which to call the methods)
        sequencer = (Sequencer) registry.lookup("sequencer");

        aClientJoins();
        whileLoop:
        while (true) {
            var input = scanner.nextLine();
            switch (input) {
                case "exit" -> {
                    // tell sequencer that "client" will no longer need its services
                    cleanUpAndExit();
                    break whileLoop;
                }
                case "heartbeat" -> si.heartbeat("client", SequencerImpl.lastSequenceReceived);
                case "getMissing" -> getMessageFromSequenceNumber();
                case "joinGroup" -> aClientJoinsGroup();
                default -> sendMessage(input);
            }
        }
    }

    private void aClientJoinsGroup() {
        try {
            Group group = new Group("localhost", null, "client");
            new Thread(group).start();
            System.out.println("Joined group.");
            si.registerClient(this); // register client for broadcast
            System.out.println("Enter a message to send to the group.");
            while (true) {
                MsgHandler handler = (count, msg) -> {
                    SequencerImpl.updateClients(msg, this);
                };

                var input = groupScanner.nextLine();
                if (input.equals("exit")) {
                    group.leave();
                    break;
                }
                group.send(input.getBytes());
                handler.handle(1, input.getBytes());
            }
        } catch (Group.GroupException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendMessage(String input) throws RemoteException {
        var messageToSend = input.getBytes();
        long msgID = 1L;
        long lastSequenceReceived = SequencerImpl.lastSequenceReceived;
        lastSequenceReceived++;
        si.send("client", messageToSend, msgID, lastSequenceReceived);
    }

    private void getMessageFromSequenceNumber() throws RemoteException {
        System.out.println("Enter the sequence number of the missing message:");
        String missing = askMissing.nextLine();
        si.getMissing("client", Integer.parseInt(missing));
    }

    private void cleanUpAndExit() throws RemoteException {
        sequencer.leave("client");
        scanner.close();
        askMissing.close();
        System.out.println("Client has left the server.");
        System.exit(0);
    }

    private void aClientJoins() {
        try {
            // call the join method on the sequencer
            SequencerJoinInfo joinInfo = sequencer.join("client");
            System.out.println("Joined: " + joinInfo);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void update(byte[] msg) throws RemoteException {
        System.out.println("Received message: " + new String(msg));
    }
}