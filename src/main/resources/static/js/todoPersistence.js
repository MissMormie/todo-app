var TodoPersistence = function() {
    this.delaySaveTimeoutArray = new Array();
    this.delaySaveInMilliSeconds = 2000;
}


TodoPersistence.prototype.constructor = Todo;

// Sets timeout of delaySaveInMilliSeconds before saving to prevent many consecutive saves.
TodoPersistence.prototype.saveTodo = function(todo, callBackSuccess, callBackFailure) {
    if(!todo instanceof Todo) {
        callBackFailure;
    }


    if(todo.id in this.delaySaveTimeoutArray ) {
        window.clearTimeout(this.delaySaveTimeoutArray[todo.id]);
    }
    var self = this;
    this.delaySaveTimeoutArray[todo.id] = window.setTimeout(self.performSave.bind(self), self.delaySaveInMilliSeconds, todo, callBackSuccess, callBackFailure);
}


TodoPersistence.prototype.performSave = function(todo, callBackSuccess, callBackFailure) {
    $.ajaxSetup({
       headers:{
         'Content-Type': 'application/json'
       }
    });

    $.post("/todo/" + todo.id, JSON.stringify(todo))
      .done(callBackSuccess)
      .fail(callBackFailure)
}


TodoPersistence.prototype.deleteTodo = function(todo, callBackSuccess, callBackFailure) {
    if(todo instanceof Todo) {
        callBackFailure;
    }

    $.ajax({
        url: "/todo/" + todo.id,
        type: "DELETE",
        success: callBackSuccess,
        fail: callBackFailure
    });
}