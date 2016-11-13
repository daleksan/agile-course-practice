/**
 * Created by Yevtyushin Valery on 13.11.2016.
 */

import org.junit.Test;
import java.security.InvalidParameterException;
import static org.junit.Assert.*;


public class WhenCalculatingMortrage {

    private final double summ = 10000;
    private final double month = 12;
    private final double percents = 20;
    private final double difference = 0.4;

    @Test
    public void reallyCreateMortrageDeal() {
       Mortrage mortrage = new Mortrage(summ, month, percents);
        assertNotNull(mortrage);
    }

    @Test
            (expected = InvalidParameterException.class)
    public void CreateBadEqualOfDays() {
        Mortrage mortrage = new Mortrage(summ, -month, percents);
    }

    @Test
            (expected = InvalidParameterException.class)
    public void NegativeSummCount() {
        Mortrage mortrage = new Mortrage(-summ, month, percents);
    }

    @Test
            (expected = InvalidParameterException.class)
    public void NegativePercentsCount() {
        Mortrage mortrage = new Mortrage(summ, month, -percents);
    }

    @Test
            (expected = InvalidParameterException.class)
    public void ZeroDaysMortrageCreate() {
        Mortrage mortrage = new Mortrage(summ, 0, percents);
    }

    @Test
            (expected = InvalidParameterException.class)
    public void EmptyParametersMortrageCreate() {
        Mortrage mortrage = new Mortrage(0, 0, 0);
    }

    @Test
    public void PossibilityOfCountingDeal() {
        Mortrage mortrage = new Mortrage(summ, month, percents);
        double payment = mortrage.countPayment();
        assertEquals(926, payment, difference);
    }

    @Test
    public void DealSumCountingPossibility() {
        Mortrage mortrage = new Mortrage(summ, month, percents);
        double totalSum = mortrage.countTotalSum();
        assertEquals(11116, totalSum, difference);
    }

    @Test
    public void canCountOverpayment() {
        Mortrage mortrage = new Mortrage(summ, month, percents);
        double overpayment = mortrage.countOverpayment();
        assertEquals(1116, overpayment, difference);
    }
}
