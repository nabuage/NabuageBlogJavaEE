var org = org || {};
org.nabuage = org.nabuage || {};
org.nabuage.blog = org.nabuage.blog || {};
org.nabuage.blog.model = org.nabuage.blog.model || {};

org.nabuage.blog.model.Article = Backbone.Model.extend({
    parse: function(model) {
        return model;
    }
});