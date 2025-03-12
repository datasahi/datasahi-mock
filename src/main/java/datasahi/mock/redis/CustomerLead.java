package datasahi.mock.redis;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@RedisHash("D2C_LEAD_TRACKER")
public class CustomerLead {
    @Id
    private String id;
    private String name;
    private int age;
    private String status;
    private LocalDateTime createdTs;
    private LocalDateTime updatedTs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getStatus() {
        return status;
    }

    public CustomerLead setStatus(String status) {
        this.status = status;
        return this;
    }

    public LocalDateTime getCreatedTs() {
        return createdTs;
    }

    public CustomerLead setCreatedTs(LocalDateTime createdTs) {
        this.createdTs = createdTs;
        return this;
    }

    public LocalDateTime getUpdatedTs() {
        return updatedTs;
    }

    public CustomerLead setUpdatedTs(LocalDateTime updatedTs) {
        this.updatedTs = updatedTs;
        return this;
    }
}