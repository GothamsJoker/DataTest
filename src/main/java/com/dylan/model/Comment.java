package com.dylan.model;

import java.util.Objects;

/**
 * A comment stored on a web page.
 */
public class Comment {


    private final String author;
    private final String content;
    private final int pageId;
    private final long timeStamp;

    public Comment(String author, String content, int pageId, long timeStamp) {
        this.timeStamp = timeStamp;
        this.author = author;
        this.content = content;
        this.pageId = pageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return pageId == comment.pageId &&
                timeStamp == comment.timeStamp &&
                Objects.equals(author, comment.author) &&
                Objects.equals(content, comment.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, content, pageId, timeStamp);
    }

    @Override
    public String toString() {
        return "com.dylan.model.Comment{" +
                "author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", pageId=" + pageId +
                ", timeStamp=" + timeStamp +
                '}';
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public int getPageId() {
        return pageId;
    }

    public long getTimeStamp() {
        return timeStamp;
    }
}