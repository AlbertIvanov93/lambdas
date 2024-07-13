import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class PuttingIntoPractice {

    public static void main(String... args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        //1. Найти все транзакции за 2011 год и отсортировать их по сумме (от меньшей
        //к большей).
        System.out.println("First task");
        List<Transaction> transactionsOf2011Ask = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted((tran1, tran2) -> tran1.getValue() - tran2.getValue())
                .toList();

        transactionsOf2011Ask.forEach(System.out::println);
        System.out.println("-------------------");

        //2. Вывести список неповторяющихся городов, в которых работают трейдеры.
        System.out.println("Second task");
        List<String> traderCitiesDistinct = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .toList();

        traderCitiesDistinct.forEach(System.out::println);
        System.out.println("-------------------");

        //3. Найти всех трейдеров из Кембриджа и отсортировать их по именам.
        System.out.println("Third task"); //tradersFromCambridgeSorted
        List<Trader> tradersFromCambridgeSorted = transactions.stream()
                .map(transaction -> transaction.getTrader())
                .distinct()
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .sorted((trad1, trad2) -> trad1.getName().compareTo(trad2.getName()))
                .toList();

        tradersFromCambridgeSorted.forEach(System.out::println);
        System.out.println("-------------------");

        //4. Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном
        //порядке.
        System.out.println("Fourth task");
        List<String> traderNames = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted((name1, name2) -> name1.compareTo(name2))
                .toList();

        traderNames.forEach(System.out::println);
        System.out.println("-------------------");

        //5. Выяснить, существует ли хоть один трейдер из Милана.
        System.out.println("Fifth task");
        boolean hasTraderFromMilan = transactions.stream()
                .map(transaction -> transaction.getTrader())
                .anyMatch(trader -> trader.getCity().equals("Milan"));

        System.out.println("Is there any trader from Milan: " + hasTraderFromMilan);
        System.out.println("-------------------");

        //6. Вывести суммы всех транзакций трейдеров из Кембриджа.

        System.out.println("Sixth task");
        int transactionSumTradersFromCambridge = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .mapToInt(transaction -> transaction.getValue())
                .sum();

        System.out.println("Traders from Cambridge transaction sum: " + transactionSumTradersFromCambridge);
        System.out.println("-------------------");

        //7. Какова максимальная сумма среди всех транзакций?
        System.out.println("Seventh task");
        OptionalInt maxTransactionValue = transactions.stream()
                .mapToInt(transaction -> transaction.getValue())
                .max();

        System.out.println("Max transaction value " + maxTransactionValue.getAsInt());
        System.out.println("-------------------");

        //8. Найти транзакцию с минимальной суммой.
        System.out.println("Eighth task");
        Optional<Transaction> minValueTransaction = transactions.stream()
                .min((tr1, tr2) -> tr1.getValue() - tr2.getValue());

        System.out.println("Min value transaction: " + minValueTransaction.get());
    }
}
