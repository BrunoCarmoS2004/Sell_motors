package br.com.c137.project.sellmotors.sync.listeners;

public interface ListenerConfig {

    void listenToLeadQueue(String message);
    void listenToVehicleQueue(String message);
}
