package org.example.check.symbols;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Symbols {

    private static final List<Character> SYMBOLS = Arrays.asList('-','+','=','*','/');
    private static final char OPENED_BRACKET ='(';
    private static final char CLOSED_BRACKET = ')';
    //Список запрещенных символов по индексам
    private static final List<? extends Serializable> BAD_SYMBOLS = Arrays.
            asList('!', '"', '#', '$', '%', '&', '\'', ',', '.', '/', ':', ';', '<', '=', '>', '?', '@',
                    '[', '\\', ']', '^', '_', '`', '{', '|', '}', '~');

    //метод проверят два символа на повторение
    public static boolean isOk(char symbol1,char symbol2){
        //если символ один из запрещенных знаков
        if (BAD_SYMBOLS.stream().anyMatch(e -> e.equals(symbol1) || e.equals(symbol2)))
            return false;
        //проверка, что за символом не идет ещё 1 символ
        else if(SYMBOLS.stream().anyMatch(e -> e.equals(symbol1))){
            Stream<Character> concatedStream = Stream.concat(SYMBOLS.stream(),Stream.of(OPENED_BRACKET,CLOSED_BRACKET));
            return concatedStream.noneMatch(e -> e.equals(symbol2));
        } else if (symbol1 == OPENED_BRACKET && symbol2 == CLOSED_BRACKET) {
            return false;
        } else if (symbol1 == CLOSED_BRACKET && symbol2 == OPENED_BRACKET){
            return false;
        }
        return true;
    }
}
