package com.example.sinta.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.Map;

public final class ResponseUtil {

    public static final ResponseUtil INSTANCE = new ResponseUtil();
    private ResponseUtil(){}

    public ResponseEntity<Map<String, Object>> sendResponse(String message, HttpStatus status, Boolean success, Map<String, Object> data){
        Map<String, Object> res = new LinkedHashMap<>();
        Map<String, Object> innerMap = new LinkedHashMap<>();
        res.put("message", message);
        res.put("success", success);
        if(data != null){
            data.forEach((k, v) -> {
                innerMap.put(k, v);
            });
            res.put("data", innerMap);
        } else {
            res.put("data", null);
        }
        return ResponseEntity.status(status.value()).body(res);
    }
}
