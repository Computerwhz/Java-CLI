# Java CLI Tool

Simple CLI tool for java

### Install dependency

```
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```
```
<dependency>
    <groupId>com.github.Computerwhz</groupId>
    <artifactId>Java-CLI</artifactId>
    <version>Tag</version>
</dependency>
```

## How to use



### Create a Command

Your command class must implement the Command interface

```
public class MyCommand implements Command{
    @Override
    public void execute(String[] strings) {
        System.out.println("Hello World");
    }
}
```

### Create command manager

```
private static final CommandManager commandManager = new CommandManager();
```


### Register commands

Register accepts two arguments 

- arg one is a string and is the name of the command and what you type in the command line
- arg two is a class which must implement the Command interface


```
commandManager.Register("mycommand", new MyCommand());
```

### Run the Command Manager

```
commandManager.Run();
```

