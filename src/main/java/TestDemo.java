import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TestDemo {

    public static void main(String[] args) {
        List<ScoreItem2> list = new ArrayList<>();

        ScoreItem2 scoreItem = new ScoreItem2();
        scoreItem.setName("管理能力");
        scoreItem.setComment("1.具备完整孵化器硬件配套的情况，包括场地、公共服务等。2.具备完整孵化器软件配套的情况，包括提供创业服务等。3.拥有一支较高水平的孵化器运营团队，且达到一定的管理水平、专业化水平");
        scoreItem.setWeight(new BigDecimal(40));
        scoreItem.setScore(new BigDecimal(40));

        ScoreItem2 scoreItem2 = new ScoreItem2();
        scoreItem2.setName("投资能力");
        scoreItem2.setComment("孵化器运营方具有一定的投资服务能力");
        scoreItem2.setWeight(new BigDecimal(10));
        scoreItem2.setScore(new BigDecimal(10));
        list.add(scoreItem);
        list.add(scoreItem2);

        BigDecimal weight = list.stream().map(ScoreItem2::getWeight).reduce((a, b) -> a.add(b)).get();
        BigDecimal score = list.stream().map(ScoreItem2::getScore).reduce((a, b) -> a.add(b)).get();
        System.out.println(weight);
        System.out.println(score);

    }








}
