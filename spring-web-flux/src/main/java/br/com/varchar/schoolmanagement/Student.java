package br.com.varchar.schoolmanagement;

import lombok.Value;
import org.springframework.data.mongodb.core.mapping.Document;

@Value
@Document
public class Student {
    private String name;
}
