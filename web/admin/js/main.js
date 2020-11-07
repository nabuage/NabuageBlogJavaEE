var org = org || {};
org.nabuage = org.nabuage || {};
org.nabuage.blog = org.nabuage.blog || {};

org.nabuage.blog.Main = function Main(){
    var router = undefined;
    
    var self = this;
    
    this.router = router;
    this.rootURL = "BlogApp";
    this.start = function() {
        router = new org.nabuage.blog.router.MainRouter();
        Backbone.history.start();
    };
    this.setupNagivation = function() {
        $("#main-nav a[href=\"blog\"]").on("click", function(event){
            event.preventDefault();

            $(this).parents("#main-nav").find(".selected").removeClass("selected");
            $(this).addClass("selected");
            router.navigate("blog", {trigger: true, replace: true});
        });
        $("#main-nav a[href=\"etc\"]").on("click", function(event){
            event.preventDefault();

            $(this).parents("#main-nav").find(".selected").removeClass("selected");
            $(this).addClass("selected");
            router.navigate("etc", {trigger: true, replace: true});
        });
        $("#main-nav a[href=\"about\"]").on("click", function(event){
            event.preventDefault();

            $(this).parents("#main-nav").find(".selected").removeClass("selected");
            $(this).addClass("selected");
            router.navigate("about", {trigger: true, replace: true});
        });
        $("#main-nav a[href=\"contactme\"]").on("click", function(event){
            event.preventDefault();

            $(this).parents("#main-nav").find(".selected").removeClass("selected");
            $(this).addClass("selected");
            router.navigate("contactme", {trigger: true, replace: true});
        });
        
        //Get the main root path of the section
        var pathParts = getPath().split("/");
        var sectionPath = "blog";
        
        if (pathParts.length > 1) {
            if (pathParts[1] !== "") {
                sectionPath = pathParts[1];
            }
        }
        
        $("#main-nav a[href=\"" + sectionPath + "\"]").addClass("selected");
    };
        
    var getPath = function(){
        var path = window.location.pathname;
        
        if (path.indexOf(self.rootURL) === 1) {
            path = path.replace("/" + self.rootURL, "");
        }
        
        return path;
    };
};

var nabuageGlobal = new org.nabuage.blog.utility.Global();
var nabuageTemplate = new org.nabuage.blog.utility.Template({urlRoot: "/BlogApp/js/template/"});
var app = new org.nabuage.blog.Main();

$(function(){
    app.setupNagivation();
    app.start();
});