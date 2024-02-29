package org.example.check;

import org.example.check.symbols.Symbols;

import java.util.ArrayList;
import java.util.List;

import static org.example.сontroller.IOController.setVariables;

public class Check {
    /**
     * Проверяет выражение на ошибки
     *
     * @param string строка с выражением
     * @return измененное выражение
     */
    public static String doCheck(String string){
        areBadSigns(string);

        List<String> listOfVariables = areVariables(string);

        if (!listOfVariables.isEmpty()){
            string = setVariables(string, listOfVariables);
        }
        return string;
    }

    /**
     * Ищет парные знаки и знаки, которых не должно быть в выражении
     *
     * @param string выражение
     * */
    private static void areBadSigns(String string){
        for(int i = 0; i < string.length() -1; i++){
            if(!Symbols.isOk(string.charAt(i),string.charAt(i+1)))
                throw new RuntimeException("BAD SYNTAX!");
        }
    }
    /**
     * Ищет переменные в строке
     *
     * @param string выражение
     * @return список переменных, которые требуется инициализировать
     */
    private static List<String> areVariables(String string){
        List<String> listOFVariables = new ArrayList<>();
        String variable;

        for(int i = 0; i < string.length();){
            if(Character.isLetter(string.charAt(i))){
                variable = String.valueOf(string.charAt(i));
                if(i + 1 < string.length()){
                    while(Character.isLetter(string.charAt(i + 1))){
                        variable += String.valueOf(string.charAt(i + 1));
                        i++;
                    }
                }

                listOFVariables.add(variable);

            }
            i++;
        }

        return listOFVariables;
    }
}

