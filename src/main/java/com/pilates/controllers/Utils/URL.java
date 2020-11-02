package com.pilates.controllers.Utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

    public static List<Integer> converterIds(String sIdsSeparadoPorVirgula) {

        return  Arrays.asList(sIdsSeparadoPorVirgula.split(",")).
                stream().
                map(ids -> Integer.parseInt(ids)).
                collect(Collectors.toList());
    }

    public static String decodeParam(String sParametro) throws UnsupportedEncodingException {
        try {
            return URLDecoder.decode(sParametro, "UTF-8");
        } catch (UnsupportedEncodingException exception) {
            return "";
        }
    }
}
