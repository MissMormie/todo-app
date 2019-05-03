/*
* Persists Todo,
*/

var TodoPersistence = function() {
    this.delaySaveTimeoutArray = new Array();
    this.delaySaveInMilliSeconds = 2000;
}


TodoPersistence.prototype.constructor = Todo;

/**
* Makes a request to save the todo. Saving is delayed for delaySaveInMilliSeconds, if any new changes are made within that time the old data is not saved, a new
* delaySaveInMilliSeconds interval starts before the latest information is saved, to prevent many consecutive saves.
*/
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

/*
* Makes a request to save the todo
*/
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

/**
* Makes a request to delete the todo
*/
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