package com.technivaaran.coe.utils;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JsonUtils used for Json related conversions.
 */

public class JsonUtils {

    private JsonUtils() {
    }

    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * @param object
     * @return converted object to the json string
     * @throws JsonGenerationException
     * @throws JsonMappingException
     * @throws IOException
     */
    public static String toJson(Object object) throws IOException {
        return mapper.writeValueAsString(object);
    }

    /**
     * Converts a json String into a java object
     * 
     * @param jsonString JSON string
     * @param clazz      class of target entity
     * @return Instance of class of given type
     * @throws IOException
     */
    public static <T> T fromJson(String jsonString, Class<T> clazz)
            throws IOException {
        return mapper.readValue(jsonString, clazz);
    }

    /**
     * Converts a object into another java object
     * 
     * @param fromValue JSON source
     * @param clazz     class of target entity
     * @return Instance of class of given type
     * @throws IOException
     */
    public static <T> T convertValue(Object fromValue, Class<T> clazz)
            throws IOException {
        return mapper.convertValue(fromValue, clazz);
    }

    /**
     * Converts a json String into a java object
     *
     * @param jsonString
     *                   JSON string
     * @param clazz
     *                   class of target entity
     * @param mixins
     *                   map of mixins (target, mixinSource) to be added to mapper
     * @return Instance of class of given type
     * @throws IOException
     */
    @SuppressWarnings("rawtypes")
    public static <T> T fromJson(String jsonString, Class<T> clazz, Map<Class, Class> mixins)
            throws IOException {
        for (Map.Entry<Class, Class> entry : mixins.entrySet()) {
            mapper.addMixIn(entry.getKey(), entry.getValue());
        }
        return mapper.readValue(jsonString, clazz);
    }

    /**
     * Converts a json String into a java object
     *
     * @param jsonString JSON string
     * @param type       class of target entity with template class
     * @return Instance of class of given type
     * @throws IOException
     */
    public static <T> T fromJson(String jsonString, JavaType type)
            throws IOException {
        return mapper.readValue(jsonString, type);
    }

    /**
     * @param jsonString
     * @param type
     * @return T
     * @throws IOException
     */
    public static <T> T fromJson(String jsonString, TypeReference<T> type)
            throws IOException {
        return mapper.readValue(jsonString, type);
    }

    /**
     * Check if json string has fieldName.
     * 
     * @param json
     * @param fieldName
     * @return true if json string has fieldName otherwise false.
     * @throws IOException
     */
    public static boolean hasFieldName(final String json, final String fieldName) throws IOException {
        JsonNode rootNode = mapper.readTree(json);
        return rootNode.has(fieldName);
    }

    /**
     * Finds a field name in JSON and returns it's value.
     * 
     * @param json
     * @param fieldName
     * @return value
     * @throws Exception
     */
    public static String getValue(final String json, final String fieldName) throws JsonProcessingException {
        JsonNode rootNode = mapper.readTree(json);
        if (rootNode.has(fieldName)) {
            return rootNode.findValue(fieldName).textValue();
        }
        return null;
    }
}