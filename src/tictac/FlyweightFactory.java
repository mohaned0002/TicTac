/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictac;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Jana
 */
public class FlyweightFactory {

    private static Map<Character, GameSymbol> cmap = new HashMap();

    public FlyweightFactory() {
    }

    public GameSymbol getChar(char symbol) {
        if(!cmap.containsKey(symbol))
        {
            cmap.put(symbol, new GameSymbol(symbol));
        }
        return cmap.get(symbol) ;
                }

}
