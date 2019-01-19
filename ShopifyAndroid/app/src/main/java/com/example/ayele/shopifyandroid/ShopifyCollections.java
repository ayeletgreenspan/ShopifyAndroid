package com.example.ayele.shopifyandroid;

public class ShopifyCollections {
    private String id;
    private String handle;
    private String title;
    private String updated_at;
    private String body_html;
    private String published_at;
    private String sort_order;
    private String template_suffix;
    private String published_scope;
    private String admin_graphql_api_id;
    private Images image;

    public ShopifyCollections(String id, String handle, String title, String updated_at, String body_html, String published_at, String sort_order, String template_suffix, String published_scope, String admin_graphql_api_id, Images image) {
        this.id = id;
        this.handle = handle;
        this.title = title;
        this.updated_at = updated_at;
        this.body_html = body_html;
        this.published_at = published_at;
        this.sort_order = sort_order;
        this.template_suffix = template_suffix;
        this.published_scope = published_scope;
        this.admin_graphql_api_id = admin_graphql_api_id;
        this.image = image;
    }

    public Images getImage() {
        return image;
    }

    public void setImage(Images image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getHandle() {
        return handle;
    }

    public String getTitle() {
        return title;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String getBody_html() {
        return body_html;
    }

    public String getPublished_at() {
        return published_at;
    }

    public String getSort_order() {
        return sort_order;
    }

    public String getTemplate_suffix() {
        return template_suffix;
    }

    public String getPublished_scope() {
        return published_scope;
    }

    public String getAdmin_graphql_api_id() {
        return admin_graphql_api_id;
    }
}
