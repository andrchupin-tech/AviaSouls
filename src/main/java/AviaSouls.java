import java.util.Arrays;
import java.util.Comparator;

public class AviaSouls {
    private Ticket[] tickets = new Ticket[0];

    private Ticket[] addToArray(Ticket[] current, Ticket ticket) {
        Ticket[] tmp = new Ticket[current.length + 1];
        for (int i = 0; i < current.length; i++) {
            tmp[i] = current[i];
        }
        tmp[tmp.length - 1] = ticket;
        return tmp;
    }

    public void add(Ticket ticket) {
        tickets = addToArray(tickets, ticket);
    }

    public Ticket[] findAll() {
        return tickets;
    }

    // Поиск с сортировкой по умолчанию (по цене)
    public Ticket[] search(String from, String to) {
        Ticket[] result = new Ticket[0];
        for (Ticket ticket : tickets) {
            if (ticket.getFrom().equals(from) && ticket.getTo().equals(to)) {
                result = addToArray(result, ticket);
            }
        }
        // Сортируем массив (использует compareTo из Ticket)
        Arrays.sort(result);
        return result;
    }

    // Поиск с сортировкой по переданному компаратору
    public Ticket[] searchAndSortBy(String from, String to, Comparator<Ticket> comparator) {
        Ticket[] result = search(from, to); // Берем уже отфильтрованные билеты
        Arrays.sort(result, comparator); // Сортируем компаратором
        return result;
    }
}