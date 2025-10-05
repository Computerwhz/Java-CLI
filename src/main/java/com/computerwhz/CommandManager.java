package com.computerwhz;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandManager {

    private Map<String, Command> commandMap = new HashMap<>();

    public CommandManager(){
        Scanner scanner = new Scanner(System.in);

        while (true){
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                continue;
            }

            String[] parts = input.split("\\s+");

            String command = parts[0];
            String[] arguments = java.util.Arrays.copyOfRange(parts, 1, parts.length);

            if (commandMap.containsKey(command)){
                commandMap.get(command).execute(arguments);
            }
        }
    }

    public void Register(String command, Command commandClass){
        commandMap.put(command, commandClass);
    }



}
