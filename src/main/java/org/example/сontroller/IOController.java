package org.example.сontroller;

import org.example.entity.Pair;
import org.example.input.Input;

import java.util.List;

public class IOController {
    /**
     * Вставляет значения переменных в выражение
     *
     * @param equation выражение
     * @param signs список переменных
     * @return изменённое выражение
     */
    public static String setVariables(String equation, List<String> signs){
        List<Pair<String,Integer>> listOfPairs = Input.askUserForVariables(signs);

        for (int i = 0; i < listOfPairs.size(); i++){
            equation =
                    equation.replace(listOfPairs.get(i).getFirst(),Integer.toString(listOfPairs.get(i).getSecond()));
        }

        return equation;
    }
}
