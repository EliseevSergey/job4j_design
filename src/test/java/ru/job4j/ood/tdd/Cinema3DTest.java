package ru.job4j.ood.tdd;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import java.util.Calendar;
import java.util.List;
import org.junit.jupiter.api.Disabled;

@Disabled
public class Cinema3DTest {
    @Test
    public void whenBuyThenGetTicket() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Session session = new Session3D();
        Ticket ticket = cinema.sell(account, session, 1, 1, date);
        assertThat(ticket).isEqualTo(new Ticket3D());
    }

    @Test
    public void whenAddSessionThenItExistsBetweenAllSessions() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        cinema.add(session);
        List<Session> sessions = cinema.find(ses -> true);
        assertThat(sessions).contains(session);
    }

    @Test
    public void whenBuyOnInvalidRowThenGetException() {
        Account account = new AccountCinema();
        Session session = new Session3D();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThatThrownBy(() -> cinema.sell(account, session, -1, 1, date)).
                isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenBoughtTicketGoToAccountList() {
        Account account = new AccountCinema();
        Session session = new Session3D();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Ticket ticket = cinema.sell(account, session, 1, 1, date);
        List<Ticket> tickets = account.find(t -> true);
        assertThat(tickets.contains(ticket));
    }

    @Test
    public void whenBuyOnNotExistSessionThenGetException() {
        Account account = new AccountCinema();
        Session session = new Session3D();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThatThrownBy(() -> cinema.sell(account, session, -1, 1, date)).
                isInstanceOf(IllegalArgumentException.class);
    }
}