package io.stackroute.model;

public class GitUserRepos {
    String id;
    String node_id;
    String name;
    String full_name;
    String html_url;
    String description;
    Boolean fork;
    String url;

    public GitUserRepos(String id, String node_id, String name, String full_name, String html_url, String description, Boolean fork, String url) {
        this.id = id;
        this.node_id = node_id;
        this.name = name;
        this.full_name = full_name;
        this.html_url = html_url;
        this.description = description;
        this.fork = fork;
        this.url = url;
    }

    public GitUserRepos() {
    }

    public String getId() {
        return id;
    }

    public String getNode_id() {
        return node_id;
    }

    public String getName() {
        return name;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getHtml_url() {
        return html_url;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getFork() {
        return fork;
    }

    public String getUrl() {
        return url;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFork(Boolean fork) {
        this.fork = fork;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "GitUserRepos{" +
                "id='" + id + '\'' +
                ", node_id='" + node_id + '\'' +
                ", name='" + name + '\'' +
                ", full_name='" + full_name + '\'' +
                ", html_url='" + html_url + '\'' +
                ", description='" + description + '\'' +
                ", fork=" + fork +
                ", url='" + url + '\'' +
                '}';
    }
}
