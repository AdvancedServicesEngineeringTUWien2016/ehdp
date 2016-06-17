package at.ac.tuwien.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class Api {
    @Id
    private String id;
    private String path;
    private String description;
    private String termsOfUse;
    private String link;
    private List<ApiExample> examples;

    public Api() {
        examples = new ArrayList<ApiExample>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTermsOfUse() {
        return termsOfUse;
    }

    public void setTermsOfUse(String termsOfUse) {
        this.termsOfUse = termsOfUse;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<ApiExample> getExamples() {
        return examples;
    }

    public void setExamples(List<ApiExample> examples) {
        this.examples = examples;
    }

    public void addExample(ApiExample example) {
        this.examples.add(example);
    }
}
