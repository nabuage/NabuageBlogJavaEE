var org = org || {};
org.nabuage = org.nabuage || {};
org.nabuage.blog = org.nabuage.blog || {};
org.nabuage.blog.collection = org.nabuage.blog.collection || {};

org.nabuage.blog.collection.Articles = Backbone.Collection.extend({
    url: function() {
        return "/BlogApp/services/article/type/" + this.articleType;
    },
    model: org.nabuage.blog.model.Article,
    articleType: "blog",
    parse: function(response, xhr) {
        return response;
    },
    initialize: function(options) {
        var self = this;
        
        if (options) {
            $.extend(self, options);
        }
    }
});