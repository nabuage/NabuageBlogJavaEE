var org = org || {};
org.nabuage = org.nabuage || {};
org.nabuage.blog = org.nabuage.blog || {};
org.nabuage.blog.view = org.nabuage.blog.view || {};

org.nabuage.blog.view.ContactUsView = Backbone.View.extend({    
    initialize: function(options) {
        var self = this;
        
        if (options) {
            $.extend(self, options);
        }
        
        if (self.contactMessage === undefined) {
            self.contactMessage = new org.nabuage.blog.model.ContactMessage();
        }
        
        self.template = _.template(nabuageTemplate.get("contact-us-template"));
        
        self.$el.on("blur", "#email-address", function() {
            self.contactMessage.set("emailAddress", $(this).val());
        });
        self.$el.on("blur", "#name", function() {
            self.contactMessage.set("name", $(this).val());
        });
        self.$el.on("blur", "#message", function() {
            self.contactMessage.set("message", $(this).val());
        });
    },
    el: "#content",
    template: undefined,
    title: "Contact Me",
    contactMessage: undefined,
    events: {
        "click button[type='submit']": "send"
    },
    refresh: function() {
        var self = this;
        
        self.$el.fadeOut("slow");
        
        self.render();
    },
    render: function() {
        var self = this;
        
        var output = self.template();
        
        self.$el.stop();
        
        self.$el.find("h1").html(self.title);
        self.$el.find(".article").remove();
        
        self.$el.append(output).fadeIn("slow");        
        
        return self;
    },
    clear: function() {
        var self = this;
        self.contactMessage.set("emailAddress", "");
        self.contactMessage.set("name", "");
        self.contactMessage.set("message", "");
        
        self.$el.find("#email-address").val("");
        self.$el.find("#name").val("");
        self.$el.find("#message").val("");
    },
    validate: function() {
        var self = this;
        var isValid = false;
        var message = "";
        
        if (!self.contactMessage.isValid()) {
            self.$el.find(".form-field-message").remove();
            if (self.contactMessage.errors.emailAddress.length > 0) {
                self.$el.find("#email-address").parent().append("<div class=\"form-field-message\">" + self.contactMessage.errors.emailAddress.join("<br/>") + "</div>");
                message += self.contactMessage.errors.emailAddress.join("<br/>");
            }
            if (self.contactMessage.errors.name.length > 0) {
                self.$el.find("#name").parent().append("<div class=\"form-field-message\">" + self.contactMessage.errors.name.join("<br/>") + "</div>");
                
                if (message !== "") {
                    message += "<br/>";
                }
                message += self.contactMessage.errors.name.join("<br/>");
            }
            if (self.contactMessage.errors.message.length > 0) {
                self.$el.find("#message").parent().append("<div class=\"form-field-message\">" + self.contactMessage.errors.message.join("<br/>") + "</div>");
                
                if (message !== "") {
                    message += "<br/>";
                }
                message += self.contactMessage.errors.message.join("<br/>");
            }
            
            $("<div>" + message + "</div>").dialog({
                title: "Message",
                buttons: {
                    Ok: function(){
                        $(this).dialog("close");
                    }
                },
                modal: true
            });
        }
        else {
            isValid = true;
        }        
        
        return isValid;
    },
    send: function(event) {
        event.preventDefault();
        
        var self = this;
        
        var confirmDialog = undefined;
        var save = function() {
            self.contactMessage.save(self.contactMessage.attributes, {
                success: function(model, response, options) {
                    confirmDialog.html("Thank you.").dialog({
                        buttons: {
                            "Close": function(){
                                self.clear();
                                $(this).dialog("close");
                            }
                        }
                    });                    
                },
                error: function(model, xhr, options) {
                    confirmDialog.html("There was an error sending your message.  Please try again.").dialog({
                        buttons: {
                            "Close": function(){
                                $(this).dialog("close");
                            }
                        }
                    });
                }
            });
        };        
        
        if (self.validate()) {
            var confirmHTML = _.template(nabuageTemplate.get("contact-us-confirmation-template"))({"contactMessage": self.contactMessage.toJSON()});
            confirmDialog = $(confirmHTML).dialog({
                title: "Confirm Message",
                buttons: {
                    "Confirm": save,
                    "Go Back and Edit": function(){
                        $(this).dialog("close");
                    }
                },
                modal: true
            });
        }        
    }
});