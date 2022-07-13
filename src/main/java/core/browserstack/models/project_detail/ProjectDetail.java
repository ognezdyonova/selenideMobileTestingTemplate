package core.browserstack.models.project_detail;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "project"
})

public class ProjectDetail {

    @JsonProperty("project")
    private Project project;

    public ProjectDetail() {
    }

    public ProjectDetail(Project project) {
        this.project = project;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "ProjectDetail{" +
                "project=" + project +
                '}';
    }
}
