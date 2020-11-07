var org = org || {};
org.nabuage = org.nabuage || {};
org.nabuage.blog = org.nabuage.blog || {};
org.nabuage.blog.router = org.nabuage.blog.router || {};

org.nabuage.blog.router.MainRouter = Backbone.Router.extend({
    routes: {        
        "blog(/*)": "showBlog",
        "about(/*)": "showAbout",
        "etc(/*)": "showEtc",
        "contactme(/*)": "showContactMe",
        "*default": "showBlog"
    },
    articlesView: undefined,
    contactUsView: undefined,
    showBlog: function() {
        var self = this;
        
        if (self.articlesView === undefined) {
            self.articlesView = new org.nabuage.blog.view.ArticlesView();
        }
        
        //self.articlesView = new org.nabuage.blog.view.ArticlesView({articles: new org.nabuage.blog.collection.Articles({articleType: "blog"})});
        self.articlesView.title = "Home";
        self.articlesView.articles.articleType = "blog";
        self.articlesView.refresh();
        //self.articlesView.articles.fetch({reset: true});
    },
    showAbout: function() {
        var self = this;
        
        if (self.articlesView === undefined) {
            self.articlesView = new org.nabuage.blog.view.ArticlesView();
        }
        
        self.articlesView.title = "About";
        self.articlesView.articles.articleType = "about";
        self.articlesView.refresh();
    },
    showEtc: function() {
        var self = this;
        
        if (self.articlesView === undefined) {
            self.articlesView = new org.nabuage.blog.view.ArticlesView();
        }
        
        self.articlesView.title = "Etc.";
        self.articlesView.articles.articleType = "etc";
        self.articlesView.refresh();
    },
    showContactMe: function() {
        var self = this;
        
        if (self.contactUsView === undefined) {
            self.contactUsView = new org.nabuage.blog.view.ContactUsView();
        }
        
        self.contactUsView.refresh();
    },
    showLogin: function() {
        var self = this;
        console.log("login");
        console.log(self);
    }
});