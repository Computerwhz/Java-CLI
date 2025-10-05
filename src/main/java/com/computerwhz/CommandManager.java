package com.computerwhz;

import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class CommandManager {

    private final Map<String, Command> commandMap = new ConcurrentHashMap<>();
    private boolean running;

    public void Run() {
        if (running) {
            System.err.println("CommandManager is already running.");
            return;
        }

        running = true;

        Thread inputThread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);

            while (running) {
                System.out.print("> ");
                String input = scanner.nextLine().trim();

                if (input.isEmpty()) continue;

                String[] parts = input.split("\\s+");
                String command = parts[0];
                String[] arguments = java.util.Arrays.copyOfRange(parts, 1, parts.length);

                if (command.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting command manager...");
                    running = false;
                    break;
                }

                if (commandMap.containsKey(command)) {
                    commandMap.get(command).execute(arguments);
                } else {
                    System.out.println("Unknown command: " + command);
                }
            }

            scanner.close();
        });

        inputThread.setDaemon(false);  // Ensure it keeps running until the loop ends
        inputThread.start();
    }

    public void Register(String command, Command commandClass){
        if (!commandMap.containsKey(command)){
            commandMap.put(command, commandClass);
        }
        else{
            System.err.println("Could not register command " + command + " is already registered");
        }
    }

    public void UnRegister(String command){
        if (commandMap.containsKey(command)){
            commandMap.remove(command);
        } else {
            System.err.println("Could not un-register command '" + command + "': not registered");
        }
    }
}
