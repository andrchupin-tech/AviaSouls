import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Comparator;

public class AviaSoulsTest {

    @Test
    public void shouldCompareTicketsByPrice() {
        Ticket t1 = new Ticket("Москва", "Сочи", 5000, 10, 12);
        Ticket t2 = new Ticket("Москва", "Сочи", 7000, 10, 12);

        Assertions.assertTrue(t1.compareTo(t2) < 0); // t1 меньше t2 по цене
    }

    @Test
    public void shouldSortSearchResultsByPrice() {
        AviaSouls manager = new AviaSouls();
        Ticket t1 = new Ticket("Москва", "Сочи", 7000, 10, 12);
        Ticket t2 = new Ticket("Москва", "Сочи", 5000, 10, 12);
        Ticket t3 = new Ticket("Москва", "Сочи", 6000, 10, 12);

        manager.add(t1);
        manager.add(t2);
        manager.add(t3);

        Ticket[] expected = {t2, t3, t1}; // Отсортировано по цене: 5000, 6000, 7000
        Ticket[] actual = manager.search("Москва", "Сочи");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldCompareTicketsByFlightTime() {
        Ticket t1 = new Ticket("Москва", "Сочи", 5000, 10, 12); // Время 2
        Ticket t2 = new Ticket("Москва", "Сочи", 7000, 10, 14); // Время 4

        TicketTimeComparator comparator = new TicketTimeComparator();
        Assertions.assertTrue(comparator.compare(t1, t2) < 0);
    }

    @Test
    public void shouldSearchAndSortByFlightTime() {
        AviaSouls manager = new AviaSouls();
        Ticket t1 = new Ticket("Москва", "Сочи", 7000, 10, 14); // Время 4
        Ticket t2 = new Ticket("Москва", "Сочи", 5000, 10, 12); // Время 2

        manager.add(t1);
        manager.add(t2);

        TicketTimeComparator comparator = new TicketTimeComparator();
        Ticket[] expected = {t2, t1}; // Сначала быстрый (2), потом долгий (4)
        Ticket[] actual = manager.searchAndSortBy("Москва", "Сочи", comparator);

        Assertions.assertArrayEquals(expected, actual);
    }
}