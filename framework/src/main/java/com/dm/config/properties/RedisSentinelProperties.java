package com.dm.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@ConfigurationProperties(prefix = "spring.custom.redis")
public class RedisSentinelProperties {
    private String host = "localhost";

    private Integer port = 6379;

    private String password;

    private Integer database = 0;

    private Sentinel sentinel = new Sentinel();

    //private Set<String> nodes;

    private Pool pool = new Pool();

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getDatabase() {
        return database;
    }

    public void setDatabase(Integer database) {
        this.database = database;
    }

    public Sentinel getSentinel() {
        return sentinel;
    }

    public void setSentinel(Sentinel sentinel) {
        this.sentinel = sentinel;
    }

    public Pool getPool() {
        return pool;
    }

    public void setPool(Pool pool) {
        this.pool = pool;
    }

    public class Sentinel {
        private String master;

        private Set<String> nodes;

        public String getMaster() {
            return master;
        }

        public void setMaster(String master) {
            this.master = master;
        }

        public Set<String> getNodes() {
            return nodes;
        }

        public void setNodes(Set<String> nodes) {
            this.nodes = nodes;
        }
    }

    public class Pool {
        private Integer minIdle = 5;
        private Integer maxActive = 10;
        private Long maxWait = -1L;
        private Integer maxIdle = 5;

        public Integer getMinIdle() {
            return minIdle;
        }

        public void setMinIdle(Integer minIdle) {
            this.minIdle = minIdle;
        }

        public Integer getMaxActive() {
            return maxActive;
        }

        public void setMaxActive(Integer maxActive) {
            this.maxActive = maxActive;
        }

        public Long getMaxWait() {
            return maxWait;
        }

        public void setMaxWait(Long maxWait) {
            this.maxWait = maxWait;
        }

        public Integer getMaxIdle() {
            return maxIdle;
        }

        public void setMaxIdle(Integer maxIdle) {
            this.maxIdle = maxIdle;
        }
    }
}
