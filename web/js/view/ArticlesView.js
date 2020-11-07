var org = org || {};
org.nabuage = org.nabuage || {};
org.nabuage.blog = org.nabuage.blog || {};
org.nabuage.blog.view = org.nabuage.blog.view || {};

org.nabuage.blog.view.ArticlesView = Backbone.View.extend({
    el: "#content",
    //template: _.template(nabuageTemplate.get("content-template")),
    title: "Home",
    articles: undefined,
    refresh: function(){
        var self = this;
        
        self.$el.fadeOut("slow");
        
        self.articles.fetch({reset: true});
    },
    render: function() {
        var self = this;
        
        self.$el.stop();
        
        var output = self.template({"articles": self.articles.toJSON()});
        
        self.$el.find("h1").html(self.title);
        self.$el.find(".article").remove();
        
        self.$el.append(output).fadeIn("slow");        
        
        return self;
    },
    initialize: function(options) {
        var self = this;
        
        if (options) {
            $.extend(self, options);
        }
        
        if (self.articles === undefined) {
            self.articles = new org.nabuage.blog.collection.Articles();
        }
        
        self.template = _.template(nabuageTemplate.get("content-template"));
        
        self.listenTo(self.articles, "reset", self.render);
    }
});