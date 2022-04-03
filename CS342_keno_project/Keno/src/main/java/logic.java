import java.util.ArrayList;
import java.util.Collections;

public class logic {

    public ArrayList<Integer> allGridNums = new ArrayList<>();
    public ArrayList<Integer> random20Round1 = new ArrayList<>();
    public ArrayList<Integer> random20Round2 = new ArrayList<>();
    public ArrayList<Integer> random20Round3 = new ArrayList<>();
    public ArrayList<Integer> random20Round4 = new ArrayList<>();

    public ArrayList<Integer> getRandom20(int roundNumber)
    {
        ArrayList toReturn = new ArrayList<String>();

        fillAndShuffle();

        switch(roundNumber){
            case 1:
                toReturn = getRandom20Round1();
                break;
            case 2:
                toReturn = getRandom20Round2();
                break;
            case 3:
                toReturn = getRandom20Round3();
                break;
            case 4:
                toReturn = getRandom20Round4();
                break;
            default:
                System.out.println("[WARNING] Reached Default statement in getRandom20()!");
                break;
        }

        return toReturn;
    }

    public void fillAndShuffle()
    {
        if(allGridNums.isEmpty()) {
            for (int i = 1; i <= 80; i++) {
                allGridNums.add(i);
            }
        }

        Collections.shuffle(allGridNums);

    }

    public ArrayList<Integer> getRandom20Round1()
    {
        for(int i=0; i<20;i++)
        {
            random20Round1.add(allGridNums.get(i));
        }

        for(int i=0; i<20;i++)
        {
            System.out.println(random20Round1.get(i));
        }

        return random20Round1;
    }

    public ArrayList<Integer> getRandom20Round2()
    {
        for(int i=20; i<40;i++)
        {
            random20Round2.add(allGridNums.get(i));
        }

        return random20Round2;
    }

    public ArrayList<Integer> getRandom20Round3()
    {
        for(int i=40; i<60;i++)
        {
            random20Round3.add(allGridNums.get(i));
        }


        return random20Round3;
    }

    public ArrayList<Integer> getRandom20Round4()
    {
        for(int i=60; i<80;i++)
        {
            random20Round4.add(allGridNums.get(i));
        }


        return random20Round4;
    }


    public ArrayList<Integer> getNumbersForUser(int spots)
    {
        ArrayList toReturn = new ArrayList<String>();
        ArrayList fill = new ArrayList<String>();

        if(fill.isEmpty()) {
            for (int i = 1; i <= 80; i++) {
                fill.add(i);
            }
        }

        Collections.shuffle(fill);

        switch(spots){
            case 1:
                for(int i=0;i<1;i++)
                {
                    toReturn.add(fill.get(i));
                }
                break;
            case 4:
                for(int i=0;i<4;i++)
                {
                    toReturn.add(fill.get(i));
                }
                break;
            case 8:
                for(int i=0;i<8;i++)
                {
                    toReturn.add(fill.get(i));
                }
                break;
            case 10:
                for(int i=0;i<10;i++)
                {
                    toReturn.add(fill.get(i));
                }
                break;
            default:
                System.out.println("[WARNING] Reached Default statement in getNumbersForUser()!");
                break;
        }

        for(int i=0;i< toReturn.size();i++)
        {
            System.out.println(i+": "+toReturn.get(i));
        }

        return toReturn;

    }

    ////////////////////////////////////////////////////////////////////////////////////////////


    public double getPrizeMoney(int Spots,int matches) {
        double money=-1;
        switch (Spots) {
            case 1:
                money = getMoneySpots1(matches);
                break;
            case 4:
                money = getMoneySpots4(matches);
                break;
            case 8:
                money = getMoneySpots8(matches);
                break;
            case 10:
                money = getMoneySpots10(matches);
                break;
        }
        return money;
    }





    public double getMoneySpots10(int matches)
    {
        double amount=-1;
        switch (matches){
            case 0:
                amount=4;
                break;
            case 5:
                amount=2;
                break;
            case 6:
                amount=15;
                break;
            case 7:
                amount=50;
                break;
            case 8:
                amount=500;
                break;
            case 9:
                amount=5000;
                break;
            case 10:
                amount=100000;
                break;
            default:
                amount=0;
        }
        return amount;
    }

    public double getMoneySpots8(int matches)
    {
        double amount=-1;
        switch (matches){
            case 4:
                amount=2;
                break;
            case 5:
                amount=10;
                break;
            case 6:
                amount=75;
                break;
            case 7:
                amount=500;
                break;
            case 8:
                amount=10000;
                break;
            default:
                amount=0;
        }
        return amount;
    }

    public double getMoneySpots4(int matches)
    {
        double amount=-1;
        switch (matches){
            case 4:
                amount=100;
                break;
            case 3:
                amount=3;
                break;
            case 2:
                amount=1;
                break;
            default:
                amount=0;
        }
        return amount;
    }

    public double getMoneySpots1(int matches)
    {
        double amount=-1;
        switch (matches){
            case 1:
                amount=2.50;
                break;
            default:
                amount=0;
        }
        return amount;
    }
}