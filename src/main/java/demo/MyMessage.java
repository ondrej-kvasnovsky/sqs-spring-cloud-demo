package demo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MyMessage {

    private final String message;

    private final int priority;

    @JsonCreator
    public MyMessage(@JsonProperty("message") String message, @JsonProperty("priority") int priority) {
        this.message = message;
        this.priority = priority;
    }

    public String getMessage() {
        return this.message;
    }

    public int getPriority() {
        return this.priority;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MyMessage{");
        sb.append("message='").append(message).append('\'');
        sb.append(", priority=").append(priority);
        sb.append('}');
        return sb.toString();
    }
}
