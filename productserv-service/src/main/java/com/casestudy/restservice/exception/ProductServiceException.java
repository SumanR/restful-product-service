package com.casestudy.restservice.exception;


import org.apache.commons.lang.builder.ToStringBuilder;

public class ProductServiceException extends Exception {

    private String issue;

    public ProductServiceException() {
        this((Throwable) null);
    }

    public ProductServiceException(String message) {
        this((Throwable) null, message);
    }

    public ProductServiceException(Throwable throwable) {
        super(throwable);
    }

    public ProductServiceException(Throwable throwable, String message) {
        super(message, throwable);
        this.setIssue(message);
    }

    public final String getIssue() {
        return issue;
    }

    public final void setIssue(String issue) {
        this.issue = issue;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}