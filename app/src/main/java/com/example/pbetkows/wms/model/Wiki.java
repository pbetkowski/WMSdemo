package com.example.pbetkows.wms.model;

public class Wiki {

    private String content;
    private String format;
    private String slug;
    private String title;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Wiki{" +
                "content='" + content + '\'' +
                ", format='" + format + '\'' +
                ", slug='" + slug + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
