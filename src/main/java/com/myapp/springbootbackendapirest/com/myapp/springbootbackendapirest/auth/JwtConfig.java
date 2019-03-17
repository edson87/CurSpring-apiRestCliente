package com.myapp.springbootbackendapirest.com.myapp.springbootbackendapirest.auth;

public class JwtConfig {
    public static final String LLAVE_SECRETA = "mi.clave.cegreta";

    public static final String RSA_PRIVATE = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEpAIBAAKCAQEAlrlkfTZ//2cPRJeRmn3MRnQf7aJKduJQMtOF+e4UviKUu4N/\n" +
            "IWafxmE/n0njnWe+3KZol9y4J/u6WuGHakrCQRyMjS+mxinUlXRpb4JY1jc8uRnS\n" +
            "MwbxXHHo0DUMcRih5xL0VldEyYS7c2cQ4K4K5llOsahof3xL89yJnBThuRum/Yru\n" +
            "0j7IViNvaLQHy3qNxG5T6P1Y2EWfOObNLXrwX9FwJeLnDc0dp4wef9wlPvcIUSvj\n" +
            "whBUOyJ6JXeH9XtaIYSrzmvEjK9WbAFQ31z2sNgtyNvTYUCc044CXla0btirdbS5\n" +
            "mI6H2QisA2lB2u5dHOpZ1t7grULnbY6Tj/ZkMwIDAQABAoIBADeWRBNRyD7/WUgo\n" +
            "xhA+GNpa9XOEiQXzTTSRgjW8snW09zl4s6gz2vUk5HvwmjtxCZkC3kwzoM9OLVpb\n" +
            "FsACtkWhK8zeKBmpT7eW6VOwtOa8FG8HsNgU1WsMr5oqFl6XgQ5hlS9KnBneE+vp\n" +
            "tPXH+kkd4sHupCtwQeq8CRxBL0Y+9Z4R5lMCsJApn2/iFgRC26DBkt0wtsE6aWDr\n" +
            "OU7Tcwwvke61xB4ZxiSrJU8OyePCvykiLIEt/XvzC8rwnZNhwQGFsQ4f1mgNRLp5\n" +
            "hkyNwwe7HhZCsGQTt15RdhYmpeQ04qci8sBjkYSM1EUSnbuaxmSPpP5M9Q2tgmWq\n" +
            "fp5GJWECgYEAxxtImxnvXynqdJFv7ke75SXLLcOBWrkD62ci3RHB4kDtFVH8NNSh\n" +
            "pngt+TJz61gDLFwSF8DWGZfutlZYCsSml7syGX2LlXe3LiBF+ojyjlmkkHoN7sMg\n" +
            "GhCywgk9lV/JxKpAm2wqknwTaqQWUbl4kk4fNJUPsOgAP+wiqytr0SsCgYEAwcrr\n" +
            "k8otm1jPuW6gKc0HmAmJX3tCi08unB9VCoCMGwZwoRb4ezug0YGAvP5kd3nk77kC\n" +
            "xkVebJwJYT5PS1NIPx0FX6BTI9Wyxh0wfmpeolMclxYm5fcd6T7ax+5ERW8S5WV7\n" +
            "QgBY1pUh5qqkcVit+Ga4O8vyMC//OiUd0rw7ZRkCgYEAkEQmoatzFCMM7JWM4rw7\n" +
            "Wte9llU4XwH74w+B5Wi+cS/WGUKkWatPzYz60rtJ3taQxg2YVnm1KCo8HwYFGRhQ\n" +
            "FVTgAjRDdXOe2N8tlVCxX494e+7UNZJQqlI7E/kaFCepzMQHuBXx6t3YO8bVHTYk\n" +
            "NO5CEhmHmwkfVTHGFPyLTK0CgYATSRhsmsAzJ4F0mLfDsS3CSaCsKd4TcwvWaZ/S\n" +
            "7bwDrH5k6Z5M1UBJ2TNwm3hwE2dfG/BUvu6IrHaagNLvUhhsf3zbaV8cv9WMtbbv\n" +
            "vbIxQUglGQVB+Xe2kJTBNMsChe1BevnrNZGhuTGofO7kgMi/YamP+dneRciGhs8S\n" +
            "+7yFcQKBgQCw1LuDJOQfZUpq9Hgm8XvhmYZnk7CUPo14yYk0GGmUQD4GRgHJaPht\n" +
            "HmFUPXiWuU4Rs5dVRk5I/yR6F3DkeNtQ4Wng+ywxgsbiQEUhKFekhoPfvf4ErMRr\n" +
            "38m2ZJ9c33JVqtHC1nXTMq3XB0gcVzlZVPAbdJCGu3lLkFB+ylnZ/Q==\n" +
            "-----END RSA PRIVATE KEY-----";

    public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlrlkfTZ//2cPRJeRmn3M\n" +
            "RnQf7aJKduJQMtOF+e4UviKUu4N/IWafxmE/n0njnWe+3KZol9y4J/u6WuGHakrC\n" +
            "QRyMjS+mxinUlXRpb4JY1jc8uRnSMwbxXHHo0DUMcRih5xL0VldEyYS7c2cQ4K4K\n" +
            "5llOsahof3xL89yJnBThuRum/Yru0j7IViNvaLQHy3qNxG5T6P1Y2EWfOObNLXrw\n" +
            "X9FwJeLnDc0dp4wef9wlPvcIUSvjwhBUOyJ6JXeH9XtaIYSrzmvEjK9WbAFQ31z2\n" +
            "sNgtyNvTYUCc044CXla0btirdbS5mI6H2QisA2lB2u5dHOpZ1t7grULnbY6Tj/Zk\n" +
            "MwIDAQAB\n" +
            "-----END PUBLIC KEY-----";
}
