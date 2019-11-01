package game.player;

import game.Manager;
import game.Player;
import game.Poker;

import java.util.Collections;
import java.util.List;

public class MyPlayer extends NimaPlayer implements Player {
    @Override
    public String getName() {
        return "Jessica";
    }

    @Override
    public String getStuNum() {
        return "2019211265";
    }

    @Override
    public void onGameStart(Manager manager, int totalPlayer) {

    }

    private boolean isSameColor(List<Poker> pokers) {//同花
        return pokers.get(0).getSuit() == pokers.get(1).getSuit() &&
                pokers.get(1).getSuit() == pokers.get(2).getSuit();
    }

    private boolean isPair(List<Poker> pokers) {//对子
        return pokers.get(0).getPoint().getNum() == pokers.get(1).getPoint().getNum()
                || pokers.get(1).getPoint().getNum() == pokers.get(2).getPoint().getNum()
                || pokers.get(0).getPoint().getNum() == pokers.get(2).getPoint().getNum();
    }

    private boolean isStraight(List<Poker> pokers) {//顺子
        Collections.sort(pokers);
        return Math.abs(pokers.get(0).getPoint().getNum() - pokers.get(1).getPoint().getNum()) == 1
                && Math.abs(pokers.get(1).getPoint().getNum() - pokers.get(2).getPoint().getNum()) == 1;

    }

    private boolean isSameColorStraight(List<Poker> handCards) {//同花顺
        return isSameColor(handCards) && isStraight(handCards);
    }

    private boolean isSamePoint(List<Poker> handCards) {//豹子
        return handCards.get(0).getPoint() == handCards.get(1).getPoint()
                && handCards.get(2).getPoint() == handCards.get(1).getPoint();

    }

    @Override
    public int bet(int time, int round, int lastPerson, int moneyOnDesk, int moneyYouNeedToPayLeast, List<Poker> pokers) {
        if (isSamePoint(pokers) )
            return 3 * moneyYouNeedToPayLeast;
        if (isSameColorStraight(pokers))
            return (int) (1.7 * moneyYouNeedToPayLeast);
        if (isSameColor(pokers) )
            return (int) (1.5 * moneyYouNeedToPayLeast);
        if (isStraight(pokers) )
            return (int) (1.3 * moneyYouNeedToPayLeast);
        if (isPair(pokers) )
            return moneyYouNeedToPayLeast;
        else
            return 0;
    }


    @Override
    public void onResult(int time, boolean isWin, List<Poker> pokers) {

    }
}