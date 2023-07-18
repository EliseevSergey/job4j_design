package ru.job4j.ood.isp.badisp;

public interface PhotoCamera {
    Shot shot();
    void video();
    void connectWifi();
    void acceptMemoryCard(MemoryCard card);
    void loadFilm(Film roll);
}
