package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.Collections;
import java.util.List;
import static java.lang.Integer.parseInt;

public class Application {
    public static void main(String[] args) {
        System.out.println("구입금액을 입력해 주세요.");
        String moneyString =Console.readLine();
        int moneyInt = moneyExceptionHandling(moneyString);
        List<List<Integer>> lottoList = makeLottoList(moneyInt);

        System.out.printf("\n%d개를 구매했습니다.",moneyInt);
        printLottoList(lottoList);

        System.out.println("\n당첨 번호를 입력해주세요");
        String winNumberString = Console.readLine();
        System.out.println("\n보너스 번호를 입력해주세요");
        String bonusNumString = Console.readLine();
        List<Integer> winNumberList = makeWinNumberList(winNumberString);
        int bonusNumber = bonusNumber(bonusNumString);
        compareNumber(winNumberList, bonusNumber);
    }

    public static int moneyExceptionHandling(String moneyString){
        int moneyInt;
        try{
            moneyInt = parseInt(moneyString);
        }
        catch(NumberFormatException e){
            System.out.println("[ERROR] 숫자만 입력해주세요.");
            throw new IllegalArgumentException();
        }
        if(moneyInt%1000!=0){
            System.out.println("[ERROR] 금액을 1000원 단위로 입력해주세요.");
            throw new IllegalArgumentException();
        }
        return moneyInt/1000;
    }

    public static List<List<Integer>> makeLottoList(int num){
        List<List<Integer>> lottos = new java.util.ArrayList<>(Collections.emptyList());
        for(int i = 0; i<num;i++){
            List<Integer> newLotto = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            lottos.add(newLotto);
        }
        return lottos;
    }

    public static void printLottoList(List<List<Integer>> lottoList){
        for(int i = 0; i< lottoList.size(); i++){
            System.out.println(lottoList.get(i));
        }
    }

    public static List<Integer> makeWinNumberList(String winNumberString){
        List<Integer> winNumberList = new java.util.ArrayList<>(Collections.emptyList());
        try{
            String[] numListStr = winNumberString.split(",");
            for(int i = 0; i< numListStr.length;i++){
                winNumberList.add(Integer.parseInt(numListStr[i]));
            }
        }catch(Exception e){
            System.out.println("[ERROR] 당첨번호를 쉼표(,)를 사용해 구분해 입력해주세요.");
            throw new IllegalArgumentException();
        }
        return winNumberList;
    }

    public static int bonusNumber(String bonusNumStr){
        int bonusNumber=0;
        try{
            bonusNumber = Integer.parseInt(bonusNumStr);
        }catch(Exception e){
            System.out.println("[ERROR] 보너스 번호를 숫자로 입력해주세요.");
            throw new IllegalArgumentException();
        }
        if(bonusNumber<0 || bonusNumber > 45){
            System.out.println("[ERROR] 보너스 번호를 숫자로 입력해주세요.");
            throw new IllegalArgumentException();
        }
        return bonusNumber;
    }

    public static void compareNumber(List<Integer> winNumbers, int bonusNumber){
        int frequency=0;
        for(int i = 0; i<winNumbers.size();i++){
            frequency = Collections.frequency(winNumbers, winNumbers.get(i));
            if(frequency != 1){
                System.out.println("[ERROR] 당첨번호를 중복되지 않게 입력해주세요.");
                throw new IllegalArgumentException();
            }
        }
        if(Collections.frequency(winNumbers, bonusNumber) != 0){
            System.out.println("[ERROR] 보너스 번호를 중복되지 않게 입력해주세요.");
            throw new IllegalArgumentException();
        }
    }
}
