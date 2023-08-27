package com.example.PrimeService.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseFormatter {

    public static String convertToXml(int initial, List<Integer> integerList) {
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xmlBuilder.append("<response>\n");
        xmlBuilder.append("    <initial>").append(initial).append("</initial>\n");
        xmlBuilder.append("    <primes>\n");

        for (Integer num : integerList) {
            xmlBuilder.append("        <integer>").append(num).append("</integer>\n");
        }

        xmlBuilder.append("    </primes>\n");
        xmlBuilder.append("</response>");
        return xmlBuilder.toString();
    }

    public static String convertToJson(int initial, List<Integer> integerList) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("initial", initial);
        responseMap.put("primes", integerList);
        return objectMapper.writeValueAsString(responseMap);
    }

    public static String convertToPlainText(int initial, List<Integer> integerList) {
        StringBuilder sb = new StringBuilder();
        sb.append("Initial: ").append(initial).append("\n");
        sb.append("Primes: ").append(integerList.toString());
        return sb.toString();
    }
}

