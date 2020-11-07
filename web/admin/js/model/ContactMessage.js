var org = org || {};
org.nabuage = org.nabuage || {};
org.nabuage.blog = org.nabuage.blog || {};
org.nabuage.blog.model = org.nabuage.blog.model || {};

org.nabuage.blog.model.ContactMessage = Backbone.Model.extend({
    defaults: {
        id: undefined,
        name: "",
        emailAddress: "",
        message: ""
    },
    errors: {
        id: undefined,
        name: undefined,
        emailAddress: undefined,
        message: undefined
    },
    url: function() {
        return "/BlogApp/services/contactmessage";
    },
    validate: function(attributes, options) {
        var isValid = true;
        var self = this;
        self.errors.id = new Array();
        self.errors.name = new Array();
        self.errors.emailAddress = new Array();
        self.errors.message = new Array();
        
        if ($.trim(attributes.name) === "") {
            isValid = false;
            self.errors.name.push("Please enter your name.");
        }
        if ($.trim(attributes.emailAddress) === "") {
            isValid = false;
            self.errors.emailAddress.push("Please enter your email address.");
        }
        if ($.trim(attributes.message) === "") {
            isValid = false;
            self.errors.message.push("Please enter your message.");
        }
        
        if (!isValid) {
            return self.errors;
        }
    }
});