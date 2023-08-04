package me.theentropyshard.toserver.network.protocol;

public interface CommandHandler {
    void executeCommand(Object o);

    void onConnectionOpen();

    void onConnectionClose();
}
