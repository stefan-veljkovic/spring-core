package epam.projects.spring_core.storage;

import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component("appStorage")
public class AppStorage {

    private final Map<String, Map<String, Object>> namespacedStorage = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public <T> Map<String, T> getNamespace(String namespace) {
        return (Map<String, T>) namespacedStorage.computeIfAbsent(namespace, k -> new ConcurrentHashMap<>());
    }

    public Map<String, Map<String, Object>> getAllData() {
        return Collections.unmodifiableMap(namespacedStorage);
    }
}
