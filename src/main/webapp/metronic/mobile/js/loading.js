var Class = {
    create: function() {
        return function() { this.init.apply(this,arguments); }
    }
}
var LoadingMsg = Class.create();
LoadingMsg.prototype = {
    init: function(spanId, spanMsg) {
        this.intervalID = -10000;
        this.spanId = spanId;
        this.spanMsg = spanMsg;
        this.timespan = 1000;
        this.pointNum = 3;
        this.initPointMsg = "...";
    },
    Loading: function() {
        var maxLength = this.spanMsg.length + this.pointNum;
        var currentSpanMsg = document.getElementById(this.spanId).innerHTML;
        if (currentSpanMsg.length < maxLength) {
            document.getElementById(this.spanId).innerHTML += ".";
        }
        else {
            document.getElementById(this.spanId).innerHTML = this.spanMsg;
        }
    },
    Start: function() {
        document.getElementById(this.spanId).innerHTML = this.spanMsg + this.initPointMsg;
        var callObj = this;
        this.intervalID = setInterval(function() { callObj.Loading(); }, this.timespan);
    },
    End: function() {
        document.getElementById(this.spanId).innerHTML = "";
        clearInterval(this.intervalID);
    }
};